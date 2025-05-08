
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class Control {
    private HashMap<Integer, contacte> Contactes = new HashMap<>();

    public Control() {

    }

    // menu de contactos
    public ArrayList<contacte> getContactes() {
        return new ArrayList<>(Contactes.values());
    }

    public void crearcontacte(String nom, String cognom, String tel, String email) {
        contacte contactenou = new contacte(nom, cognom, tel, email);
        // Use contact ID as key in HashMap
        Contactes.put(contactenou.getId(), contactenou);
    }

    public contacte buscarcontacte(int combuscar, String busca, int buscarid) {
        // For ID search, we can directly look up in the HashMap
        if (combuscar == 5) {
            return Contactes.get(buscarid);
        }

        // For other search criteria, we need to iterate through values
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
            switch (quin) {
                case 1 -> c.setNom(nouValor);
                case 2 -> c.setCognom(nouValor);
                case 3 -> c.setTel(nouValor);
                case 4 -> c.setEmail(nouValor);
                default -> { return false; }
            }
            return true;
        }
        return false;
    }

    public boolean elimcontacte(int combuscar, String supr, int buscarid) {
        // Find the contact first
        contacte contacteToDelete = buscarcontacte(combuscar, supr, buscarid);

        // If found, remove it from HashMap using its ID
        if (contacteToDelete != null) {
            Contactes.remove(contacteToDelete.getId());
            return true;
        }
        return false;
    }
}
