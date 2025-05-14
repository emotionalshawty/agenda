import java.util.HashMap;

public class Control {
    private HashMap<Integer, contacte> contactes;

    public Control() {
        contactes = new HashMap<>();
    }



    public void crearcontacte(String nom, String cognom, String tel, String email) {
        contacte contactenou = new contacte(nom, cognom, tel, email);
        contactes.put(contactenou.getId(), contactenou);
    }

    public contacte buscarcontacte(int combuscar, String busca, int buscarid) {
        for (contacte trobat : contactes.values()) {
            boolean trobatContacte = switch (combuscar) {
                case 1 -> trobat.getNom().equalsIgnoreCase(busca);
                case 2 -> trobat.getCognom().equalsIgnoreCase(busca);
                case 3 -> trobat.getTel().equals(busca);
                case 4 -> trobat.getEmail().equalsIgnoreCase(busca);
                case 5 -> trobat.getId() == buscarid;
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
        contacte c = buscarcontacte(combuscar, supr, buscarid);
        if (c != null) {
            contactes.remove(c.getId());
            return true;
        }
        return false;
    }

    public HashMap<Integer, contacte> getContactes() {
        return contactes;
    }
}
