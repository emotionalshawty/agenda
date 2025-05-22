package main.java;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Arrays;

import static javax.swing.JOptionPane.showMessageDialog;

public class GUI {
    private final Controller ctrl;
    private final JTable table;
    private final DefaultTableModel model;

    public GUI(){
        this.ctrl = new DataBaseController();
        this.model = new DefaultTableModel();
        this.table = new JTable(model);

        JFrame frame = new JFrame("Agenda");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);

        setupMenu(frame);
        setupGrid(frame);
        frame.setVisible(true);
    }

    private void setupMenu(JFrame frame){
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem newContact = new JMenuItem("New Contact");
        fileMenu.add(newContact);
        newContact.addActionListener(e -> createContact());

        JMenuItem deleteContact = new JMenuItem("Delete Contact");
        fileMenu.add(deleteContact);
        deleteContact.addActionListener(e -> deleteContact());

        JMenuItem exit = new JMenuItem("Exit");
        fileMenu.add(exit);
        exit.addActionListener(e -> System.exit(0));

        JMenu searchMenu = new JMenu("Search");
        menuBar.add(searchMenu);

        JMenuItem searchAll = new JMenuItem("Show All");
        searchMenu.add(searchAll);
        searchAll.addActionListener(e -> searchAll());

        JMenuItem searchByName = new JMenuItem("Search by Name");
        searchMenu.add(searchByName);
        searchByName.addActionListener(e -> searchByField(1, "Name"));

        JMenuItem searchBySurname = new JMenuItem("Search by Surname");
        searchMenu.add(searchBySurname);
        searchBySurname.addActionListener(e -> searchByField(2, "Surname"));

        JMenuItem searchByPhone = new JMenuItem("Search by Phone");
        searchMenu.add(searchByPhone);
        searchByPhone.addActionListener(e -> searchByField(3, "Phone"));

        JMenuItem searchByEmail = new JMenuItem("Search by Email");
        searchMenu.add(searchByEmail);
        searchByEmail.addActionListener(e -> searchByField(4, "Email"));

        JMenuItem searchById = new JMenuItem("Search by ID");
        searchMenu.add(searchById);
        searchById.addActionListener(e -> searchById());
    }

    private void setupGrid(JFrame frame){
        this.model.addColumn("ID");
        this.model.addColumn("Nom");
        this.model.addColumn("Cognom");
        this.model.addColumn("Telèfon");
        this.model.addColumn("Email");

        JScrollPane scrollPane = new JScrollPane(this.table);
        scrollPane.setSize(frame.getSize());
        frame.add(scrollPane);

        JPopupMenu contextMenu = new JPopupMenu();
        this.table.setComponentPopupMenu(contextMenu);
        this.table.setFillsViewportHeight(true);

        JMenuItem deleteItem = new JMenuItem("Delete");
        contextMenu.add(deleteItem);
        deleteItem.addActionListener(e -> deleteContact());

        table.addPropertyChangeListener("tableCellEditor", e -> {
            if(!table.isEditing()){
               updateContact();
            }
        });

        //Loading all data.
        searchAll();
    }

    private void createContact(){
        // Crea un contacte buit i l'afegeix a la taula
        ctrl.crearcontacte("", "", "", "");
        searchAll();
    }

    private void updateContact(){
        int r = table.getSelectedRow();
        TableModel t = table.getModel();

        if (r < 0) return;

        // id que funcione
        Object idObj = t.getValueAt(r, 0);
        int id;
        if (idObj instanceof Integer) {
            id = (Integer) idObj;
        } else if (idObj instanceof String) {
            try {
                id = Integer.parseInt((String) idObj);
            } catch (NumberFormatException e) {
                // si id invalida
                return;
            }
        } else {
            // unknown type, cancela la actu
            return;
        }

        String nom = (String) t.getValueAt(r, 1);
        String cognom = (String) t.getValueAt(r, 2);
        String tel = (String) t.getValueAt(r, 3);
        String email = (String) t.getValueAt(r, 4);

        // Busca el contacte i actualitza els camps
        ctrl.actucontact(5, null, id, 1, nom);
        ctrl.actucontact(5, null, id, 2, cognom);
        ctrl.actucontact(5, null, id, 3, tel);
        ctrl.actucontact(5, null, id, 4, email);
        searchAll();
    }

    private void deleteContact(){
        int[] sr = this.table.getSelectedRows();
        if(sr.length == 0) JOptionPane.showMessageDialog(null, "No row selected.");
        else{
            for(int i : sr){
                int id = (int) this.table.getModel().getValueAt(i, 0);
                ctrl.elimcontacte(5, null, id);
            }
            searchAll();
        }
    }

    private void searchAll(){
        this.model.setRowCount(0);

        for (Contacte c : ctrl.getContactes().values()) {
            addRow(c);
        }
    }

    private void addRow(Contacte c){
        this.model.addRow(new Object[]{c.getId(), c.getNom(), c.getCognom(), c.getTel(), c.getEmail()});
    }

    private void searchByField(int fieldType, String label){
        String value = JOptionPane.showInputDialog("Enter " + label + ":");
        if (value == null || value.isEmpty()) return;

        Contacte c = ctrl.buscarcontacte(fieldType, value, -1);
        this.model.setRowCount(0);
        if (c != null) {
            addRow(c);
        } else {
            showMessageDialog(null, "No contact found.");
        }
    }

    private void searchById() {
        String value = JOptionPane.showInputDialog("Enter ID:");
        if (value == null || value.isEmpty()) return;
        int id;
        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            showMessageDialog(null, "Invalid ID.");
            return;
        }
        Contacte c = ctrl.buscarcontacte(5, null, id);
        this.model.setRowCount(0);
        if (c != null) {
            addRow(c);
        } else {
            showMessageDialog(null, "No contact found.");
        }
    }
}
