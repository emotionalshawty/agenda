import java.util.HashMap;

public class MemoryController {

    private HashMap<Integer, Contacte> Contactes = new HashMap<>();
    private FileController fileController;

    // controlador memoria, lo carga todo
    public MemoryController() {
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
        // si no buscamos con id
        for (Contacte c : Contactes.values()) {
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
}
