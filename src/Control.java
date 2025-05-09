
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Control {
    private HashMap<Integer, contacte> Contactes = new HashMap<>();

    public Control() {
        loadContactsFromFiles();
    }

    private void loadContactsFromFiles() {
        File folder = new File(".");
        File[] files = folder.listFiles((dir, name) -> name.startsWith("Contacto - ") && name.endsWith(".txt"));

        contacte.resetid();

        if (files != null) {
            for (File file : files) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    int id = Integer.parseInt(reader.readLine());
                    String nom = reader.readLine();
                    String cognom = reader.readLine();
                    String tel = reader.readLine();
                    String email = reader.readLine();

                    contacte c = new contacte(id, nom, cognom, tel, email, false);
                    Contactes.put(c.getId(), c);

                    contacte.actuid(id);
                } catch (IOException | NumberFormatException e) {
                    System.err.println("Error loading contact from file: " + file.getName());
                }
            }
        }
    }

    // menu de contactos
    public ArrayList<contacte> getContactes() {
        return new ArrayList<>(Contactes.values());
    }

    public void crearcontacte(String nom, String cognom, String tel, String email) {
        contacte contactenou = new contacte(nom, cognom, tel, email);
        // utilizar id contactos en hasmpa
        Contactes.put(contactenou.getId(), contactenou);
    }

    public contacte buscarcontacte(int combuscar, String busca, int buscarid) {
        // buscar con id
        if (combuscar == 5) {
            return Contactes.get(buscarid);
        }

        // si se utiliza otras cosas que no sea id
        for (contacte trobat : Contactes.values()) {
            boolean trobatContacte = switch (combuscar) {
                case 1 -> trobat.getNom().equalsIgnoreCase(busca);
                case 2 -> trobat.getCognom().equalsIgnoreCase(busca);
                case 3 -> trobat.getTel().equals(busca);
                case 4 -> trobat.getEmail().equalsIgnoreCase(busca);
                default -> false;
            };

            if (trobatContacte) {
                return trobat;
            }
        }
        return null;
    }

    public boolean actucontact(int combuscar, String actu, int buscarid, int quin, String nouValor) {
        contacte c = buscarcontacte(combuscar, actu, buscarid);

        if (c != null) {
            // acordarse del nombre viejo para que funcione bien
            String oldNom = c.getNom();

            switch (quin) {
                case 1 -> c.setNom(nouValor);
                case 2 -> c.setCognom(nouValor);
                case 3 -> c.setTel(nouValor);
                case 4 -> c.setEmail(nouValor);
                default -> { return false; }
            }


        }
        return false;
    }

    public boolean elimcontacte(int combuscar, String supr, int buscarid) {
        contacte contacteToDelete = buscarcontacte(combuscar, supr, buscarid);

        if (contacteToDelete != null) {
            contacteToDelete.borracontact();
            Contactes.remove(contacteToDelete.getId());
            return true;
        }
        return false;
    }
}
