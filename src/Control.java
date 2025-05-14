import java.util.HashMap;

public class Control {

    private HashMap<Integer, Contacte> Contactes = new HashMap<>();
    private FileController fileController;

    public Control() {
        fileController = new FileController();
        Contactes = fileController.loadAllContacts();
    }

    // menu de contactos
    public HashMap<Integer, Contacte> getContactes() {
        return Contactes;
    }

    public void crearcontacte(String nom, String cognom, String tel, String email) {
        Contacte contactenou = new Contacte(nom, cognom, tel, email);
        // utilizar id contactos en hasmpa
        Contactes.put(contactenou.getId(), contactenou);
        fileController.saveContact(contactenou);
    }

    public Contacte buscarcontacte(int combuscar, String busca, int buscarid) {
        // buscar con id
        if (combuscar == 5) {
            return Contactes.get(buscarid);
        }

        // si se utiliza otras cosas que no sea id
        for (Contacte trobat : Contactes.values()) {

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
        Contacte c = buscarcontacte(combuscar, actu, buscarid);

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

            return true;
        }
        return false;
    }

    public boolean elimcontacte(int combuscar, String supr, int buscarid) {

        Contacte quinborrar = buscarcontacte(combuscar, supr, buscarid);

        if (quinborrar != null) {
             fileController.esborcontacte(quinborrar.getNom());
            Contactes.remove(quinborrar.getId());

       
            return true;
        }
        return false;
    }

    public HashMap<Integer, contacte> getContactes() {
        return contactes;
    }
}
