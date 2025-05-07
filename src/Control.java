
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
                 4. Email
                 5. ID
                \s""");

            int buscaid = -1;
            String busca = null;
            switch (combuscar) {
            case 1 -> busca = tui.leermsg("Quin es el nom del contacte?");
            case 2 -> busca = tui.leermsg("Quin cognom té el contacte?");
            case 3 -> busca = tui.leermsg("Quin numero de telèfon té el contacte?");
            case 4 -> busca = tui.leermsg("Quin email té?");
            case 5 -> buscaid = tui.leerint("Quin es el id del contacte?");
            default -> {
                tui.showmsg("No es pot buscar un contacte d'aquesta manera");
                return;
            }
        }
        for (contacte trobat : Contactes) {
            boolean trobatContacte = switch (combuscar) {
                case 1 -> trobat.getNom().equalsIgnoreCase(busca);
                case 2 -> trobat.getCognom().equalsIgnoreCase(busca);
                case 3 -> trobat.getTel().equals(busca);
                case 4 -> trobat.getEmail().equalsIgnoreCase(busca);
                case 5 -> trobat.getId() == buscaid;
                default -> false;
            };

            if (trobatContacte) {
                tui.showmsg("S'ha trobat a: " + trobat);
                return;
            }
        }

        tui.showmsg("No s'ha trobat cap contacte amb el criteri proporcionat.");
    }

    public void actucontact() {
        int combuscar = tui.leerint("""
                 DE quina manera vols buscar el contacte?
                 1. Nom
                 2. Cognom
                 3. Telèfon
                 4. Email
                 5. ID
                \s""");

        String actu;

        switch (combuscar) {
            case 1 -> actu = tui.leermsg("Quin es el nom del contacte?");
            case 2 -> actu = tui.leermsg("Quin cognom té el contacte?");
            case 3 -> actu = tui.leermsg("Quin numero de telèfon té el contacte?");
            case 4 -> actu = tui.leermsg("Quin email té?");
            case 5 -> actu = tui.leermsg("Quin es el id del contacte?");
            default -> {
                tui.showmsg("No es pot buscar un contacte d'aquesta manera");
                return;
            }
        }


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
        tui.showmsg("No s'ha trobat cap contacte amb el criteri proporcionat.");
    }

    public void elimcontacte() {
        int combuscar = tui.leerint("""
                 DE quina manera vols buscar el contacte?
                 1. Nom
                 2. Cognom
                 3. Telèfon
                 4. Email
                 5. ID
                \s""");

        String supr = null;
        int buscaid = -1;

        switch (combuscar) {
            case 1 -> supr = tui.leermsg("Quin es el nom del contacte?");
            case 2 -> supr = tui.leermsg("Quin cognom té el contacte?");
            case 3 -> supr = tui.leermsg("Quin numero de telèfon té el contacte?");
            case 4 -> supr = tui.leermsg("Quin email té?");
            case 5 -> buscaid = tui.leerint("Quin es el id del contacte?");
            default -> {
                tui.showmsg("No es pot buscar un contacte d'aquesta manera");
                return;
            }
        }


        for (contacte c : Contactes) {
            if (c.getNom().equalsIgnoreCase(supr)) {
                Contactes.remove(c);
                tui.showmsg("S'ha esborrat el contacte ");
                return;
            }
        }
        tui.showmsg("No s'ha trobat cap contacte amb el criteri proporcionat.");
    }

    public void llistacontactes() {
        tui.showmsg("\n== LLISTAT DE CONTACTES ==");
        if (Contactes.isEmpty()) {
            tui.showmsg("No hi ha cap contacte guardat.");
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
