
import java.util.ArrayList;


public class Control {
    private ArrayList<contacte> Contactes = new ArrayList<>();
    private tui tui;

    public Control(tui tui) {
        this.tui = tui;
    }
    public int menu(){
        tui.showmsg("Benvingut a la teva agenda de contactes, que vols fer?");
        tui.showmsg("\n1. Crear contacte");
        tui.showmsg("2. Buscar contacte");
        tui.showmsg("3. Actualitzar contacte");
        tui.showmsg("4. Eliminar contacte");
        tui.showmsg("5. Llistar els contactes");
        tui.showmsg("6. Sortir");

        return tui.leerint("Selecciona una opció: ");
    }

    public void crearcontacte() {
        String nom = tui.leermsg("POsa el nom de la persona:");
        String cognom = tui.leermsg("Posa el seu cognom:");

        String tel = tui.leermsg("Introdueix el seu número de telèfon:");

        String email = tui.leermsg("Quin correu electronic té? ");

        contacte contactenou = new contacte(nom,cognom, tel, email);
        Contactes.add(contactenou);

        tui.showmsg("S'ha creat l'entrada de contacte de " + nom);
    }

    public void buscarcontacte() {
        int combuscar = tui.leerint("""
                 DE quina manera vols buscar el contacte?
                 1. Nom
                 2. Cognom
                 3. Telèfon
                 4. Email\s
                \s""");


        String busca;
        switch (combuscar) {
            case 1 -> busca = tui.leermsg("Quin es el nom del contacte?");
            case 2 -> busca = tui.leermsg("Quin cognom té el contacte?");
            case 3 -> busca = tui.leermsg("Quin numero de telèfon té el contacte?");
            case 4 -> busca = tui.leermsg("Quin email té?");
            default -> {
                tui.showmsg("No es pot buscar un contacte d'aquesta manera");
                return;
            }
        }
        for (contacte trobat : Contactes) {
            if ((combuscar == 1 && trobat.getNom().equalsIgnoreCase(busca)) || (combuscar == 2 && trobat.getCognom().equalsIgnoreCase(busca)) || (combuscar == 3 && trobat.getTel().equals(busca)) || (combuscar == 4 && trobat.getEmail().equalsIgnoreCase(busca))) {
                tui.showmsg("S'ha trobat a: " + trobat);
                return;
            }
            tui.showmsg("No s'ha trobat a ninguna persona amb el nom de " + busca);
        }
    }

    public void actucontact() {
        String actu = tui.leermsg("Qiun es el nom del contacte que vols actualitzar?");


        for (contacte c : Contactes) {
            if (c.getNom().equalsIgnoreCase(actu)) {

                int quin = tui.leerint(
                               """
                               Quin camp vols actualitzar?
                                1. Nom
                                2. Telèfon
                                3. Email
                                Selecciona una opció:
                                """);
                switch (quin) {
                    case 1 -> {
                        String nouNom = tui.leermsg("Posa el nou nom");

                        c.setNom(nouNom);
                        tui.showmsg("S'ha actualitzat el nom a " + nouNom);
                    }
                    case 2 -> {
                        String nouTel = tui.leermsg("Posa el nou # de telèfon:");

                        c.setTel(nouTel);
                        tui.showmsg("El nou telèfon es: " + nouTel);
                    }
                    case 3 -> {
                        String nouEmail = tui.leermsg("Quin serà el nou correu electrònic?");

                        c.setEmail(nouEmail);
                        tui.showmsg("S'ha actualitzat al seguent nou correu: " + nouEmail);
                    }
                    default -> tui.showmsg("Opció no vàlida.");
                }
                return;
            }
        }
        tui.showmsg("No s'ha trobat un contacte que es digui " + actu);
    }

    public void elimcontacte() {
        String esbor = tui.leermsg("Posa el nom del contacte que vols esborrar:");


        for (contacte c : Contactes) {
            if (c.getNom().equalsIgnoreCase(esbor)) {
                Contactes.remove(c);
                tui.showmsg("S'ha esborrat la entrada de " + esbor);
                return;
            }
        }
        tui.showmsg("No s'ha trobat un contacte amb aquest nom.");
    }

    public void llistacontactes() {
        tui.showmsg("\n== LLISTAT DE CONTACTES ==");
        if (Contactes.isEmpty()) {
            tui.showmsg("No hi ha ningun contacte a la llista.");
            return;
        }

        for (contacte c : Contactes) {
            tui.showmsg(c.toString());
        }
    }
    public void exit() {
        tui.showmsg("Estàs sortint de l'aplicació");
        System.exit(0);
    }
    public void invalid() {
        tui.showmsg("L'opció que has escollit no es valida, prova de nou");
    }
}
