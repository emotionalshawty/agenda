package main.java;

import java.lang.reflect.Field;
import java.util.HashMap;

public class MemoryController implements Controller {
    private HashMap<Integer, Contacte> contactes;
    private FileController fileController;

    // contructor que inicializa el controlador de todoas los arch
    public MemoryController() {
        fileController = new FileController();
        contactes = fileController.loadAllContacts();
    }

    // Crea un nou contacte i el guarda

    public void crearcontacte(String nom, String cognom, String tel, String email) {
        Contacte c = new Contacte(nom, cognom, tel, email);

        // reflection para poner lo de la id
        try {
            Field idField = Contacte.class.getDeclaredField("id");
            idField.setAccessible(true);
            int nextId = getNextId();
            idField.set(c, nextId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println("HI Hahagut un error al posar un id amb reflection " + e.getMessage());
        }

        contactes.put(c.getId(), c);
        fileController.saveContact(c);
    }

    // para crear ids
    private int getNextId() {
        int maxId = 0;
        for (Integer id : contactes.keySet()) {
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId + 1;
    }

    // para busscar los contactos
    public Contacte buscarcontacte(int combuscar, String busca, int buscarid) {
        if (buscarid > 0 && combuscar == 5) {
            return contactes.get(buscarid);
        }

        for (Contacte c : contactes.values()) {
            switch (combuscar) {
                case 1:  // busca con nombre
                    if (c.getNom().equalsIgnoreCase(busca)) {
                        return c;
                    }
                    break;
                case 2: // busca con apellido
                    if (c.getCognom().equalsIgnoreCase(busca)) {
                        return c;
                    }
                    break;
                case 3:  // busca x telefono
                    if (c.getTel().equalsIgnoreCase(busca)) {
                        return c;
                    }
                    break;
                case 4:  // busca x mail
                    if (c.getEmail().equalsIgnoreCase(busca)) {
                        return c;
                    }
                    break;
            }
        }
        // no hay nada
        return null;
    }

    // para actu los contactos
    public void actucontact(int combuscar, String busca, int buscarid, int quin, String nouValor) {
        Contacte c = buscarcontacte(combuscar, busca, buscarid);
        if (c == null) return;

        String oldNom = c.getNom();

        switch (quin) {
            case 1:
                c.setNom(nouValor);
                break;
            case 2:
                c.setCognom(nouValor);
                break;
            case 3:
                c.setTel(nouValor);
                break;
            case 4:
                c.setEmail(nouValor);
                break;
        }

        // Si canvia el nom, borra el arch antic
        if (quin == 1) {
            fileController.esborcontacte(oldNom);
        }

        // Guarda el contacte actualitzat
        fileController.saveContact(c);
    }


    // BORRA EL CONTACTO
    public boolean elimcontacte(int combuscar, String busca, int buscarid) {
        Contacte c = buscarcontacte(combuscar, busca, buscarid);
        if (c != null) {
            fileController.esborcontacte(c.getNom());
            contactes.remove(c.getId());
            return true;
        }
        return false;
    }


    public HashMap<Integer, Contacte> getContactes() {
        return contactes;
    }

    // Prova unitaria
    public static void main(String[] args) {
        MemoryController test = new MemoryController();

        // Prova de crearcontacte
        test.crearcontacte("Frodo", "Baggins", "3333333", "bolson@anillo.com");
        assert test.getContactes().size() == 1 : "Deberia de guardarse el contacto del portador del anillo";

        // Busca contacte con combre
        Contacte c = test.buscarcontacte(1, "Frodo", -1);
        assert c != null && c.getNom().equals("Elson") : "Deberia de encontrar a frodo";

        // Busca el nombre de frodo y le cambia el apellido
        test.actucontact(1, "Frodo", -1, 2, "Bolson");
        c = test.buscarcontacte(1, "Frodo", -1);
        assert c != null && c.getCognom().equals("Bolson") : "Deberia de cambiarse su apellido a su apodo";

        // Frodo ha muerto, hayq eue liminarle el contacto :(
        boolean deleted = test.elimcontacte(1, "Frodo", -1);
        assert deleted : "Se ha borrado el contacto de Frodo";
        assert test.getContactes().isEmpty() : "LOs contactos deberian de estar vacios";

        System.out.println("Se ha pasado todas las pruebvas");
        // ahora gollum ha matado a frodo y se ha quedado con el anillo
    }



}