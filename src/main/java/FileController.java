package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileController {
    private static final String dircontactes = "contactes";

    public FileController() {
        // Crea dir de contactes si no hay
        File folder = new File(dircontactes);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    private String nombrearch(String name) {
        return dircontactes + File.separator + "Contacto de " + name + ".txt";
    }

    public void saveContact(Contacte contact) {
        String fileName = nombrearch(contact.getNom());
        String datos = contact.getId() + "\n" +
                     contact.getNom() + "\n" +
                     contact.getCognom() + "\n" +
                     contact.getTel() + "\n" +
                     contact.getEmail();

        try {
            FileWriter file = new FileWriter(fileName);
            file.write(datos);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void esborcontacte(String name) {
        String fileName = nombrearch(name);
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }
    // esto carga los contactos
    public HashMap<Integer, Contacte> loadAllContacts() {
        HashMap<Integer, Contacte> contacts = new HashMap<>();
        File folder = new File(dircontactes);

        // si no hay un dir no muestra nada
        if (!folder.exists() || !folder.isDirectory()) {
            return contacts;
        }

        File[] files = folder.listFiles((dir, name) -> name.startsWith("Contacto de ") && name.endsWith(".txt"));

        Contacte.resetid();

        if (files != null) {
            for (File file : files) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    int id = Integer.parseInt(reader.readLine().trim());
                    String nom = reader.readLine();
                    String cognom = reader.readLine();
                    String tel = reader.readLine();
                    String email = reader.readLine();

                    Contacte c = new Contacte(id, nom, cognom, tel, email);
                    contacts.put(c.getId(), c);

                    Contacte.actuid(id);
                } catch (IOException | NumberFormatException e) {
                    System.err.println("No es pot cargar la entrada de " + file.getName());
                }
            }
        }
        return contacts;
    }


}
