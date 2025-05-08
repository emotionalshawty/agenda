
import java.util.ArrayList;


public class Control {
    private ArrayList<contacte> Contactes = new ArrayList<>();




    public Control() {

    }

    // menu de contactos
    public ArrayList<contacte> getContactes() {
        return Contactes;
    }

    public void crearcontacte(String nom, String cognom, String tel, String email) {
        contacte contactenou = new contacte(nom, cognom, tel, email);
        Contactes.add(contactenou);


    }

    public contacte buscarcontacte(int combuscar, String busca, int buscarid) {
        for (contacte trobat : Contactes) {
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
        for (contacte c : Contactes) {
            boolean contactetrobat = switch (combuscar) {
                case 1 -> c.getNom().equalsIgnoreCase(supr);
                case 2 -> c.getCognom().equalsIgnoreCase(supr);
                case 3 -> c.getTel().equals(supr);
                case 4 -> c.getEmail().equalsIgnoreCase(supr);
                case 5 -> c.getId() == buscarid;
                default -> false;
            };
            if (contactetrobat) {
                Contactes.remove(c);
                return true;
            }
        }
        return false;
    }
}
