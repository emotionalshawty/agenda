
import java.util.ArrayList;


public class Control {
    private ArrayList<contacte> Contactes = new ArrayList<>();
    private tui tui;

    public Control(tui tui) {
        this.tui = tui;
    }
    public int menu(){
        tui.showmsg(tui.menu());
        return tui.leerint(tui.select());
    }

    public void crearcontacte() {
        String nom = tui.leermsg(tui.solnom());
        String cognom = tui.leermsg(tui.solcognom());

        String tel = tui.leermsg(tui.soltel());

        String email = tui.leermsg(tui.solemail());

        contacte contactenou = new contacte(nom,cognom, tel, email);
        Contactes.add(contactenou);

        tui.showmsg(tui.creat() + nom);
    }

    public void buscarcontacte() {
        int combuscar = tui.leerint(tui.combuscar());

            int buscarid = -1;
            String busca = null;
            switch (combuscar) {
            case 1 -> busca = tui.leermsg(tui.buscanom());
            case 2 -> busca = tui.leermsg(tui.buscacognom());
            case 3 -> busca = tui.leermsg(tui.buscatel());
            case 4 -> busca = tui.leermsg(tui.buscaemail());
            case 5 -> buscarid = tui.leerint(tui.buscaid());
            default -> {
                tui.showmsg(tui.cantsearch());
                return;
            }
        }
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
                tui.showmsg(tui.trobat() + trobat);
                return;
            }
        }

        tui.showmsg(tui.notrobat());
    }

    public void actucontact() {
        int combuscar = tui.leerint(tui.combuscar());

        int buscarid = -1;
        String actu = null;

        switch (combuscar) {
            case 1 -> actu = tui.leermsg(tui.buscanom());
            case 2 -> actu = tui.leermsg(tui.buscacognom());
            case 3 -> actu = tui.leermsg(tui.buscatel());
            case 4 -> actu = tui.leermsg(tui.buscaemail());
            case 5 -> buscarid = tui.leerint(tui.buscaid());
            default -> {
                tui.showmsg(tui.cantsearch());
                return;
            }
        }


        for (contacte c : Contactes) {
            boolean contactetrobat = switch (combuscar) {
                case 1 -> c.getNom().equalsIgnoreCase(actu);
                case 2 -> c.getCognom().equalsIgnoreCase(actu);
                case 3 -> c.getTel().equals(actu);
                case 4 -> c.getEmail().equalsIgnoreCase(actu);
                case 5 -> c.getId() == buscarid;
                default -> false;
            };

            if (contactetrobat) {
                int quin = tui.leerint(tui.queactu());
                switch (quin) {
                    case 1 -> {
                        String nouNom = tui.leermsg(tui.posnom());
                        c.setNom(nouNom);
                        tui.showmsg(tui.nounom() + nouNom);
                    }
                    case 2 -> {
                        String nouCognom = tui.leermsg(tui.poscognom());
                        c.setCognom(nouCognom);
                        tui.showmsg(tui.noucognom() + nouCognom);
                    }
                    case 3 -> {
                        String nouTel = tui.leermsg(tui.postel());
                        c.setTel(nouTel);
                        tui.showmsg(tui.nouTel() + nouTel);
                    }
                    case 4 -> {
                        String nouEmail = tui.leermsg(tui.posemail());
                        c.setEmail(nouEmail);
                        tui.showmsg(tui.nouemail() + nouEmail);
                    }
                    default -> tui.showmsg(tui.invalid());
                }
                return;
            }
        }
        tui.showmsg(tui.notrobat());
    }

    public void elimcontacte() {
        int combuscar = tui.leerint(tui.combuscar());

        int buscarid = -1;
        String supr = null;


        switch (combuscar) {
            case 1 -> supr = tui.leermsg(tui.buscanom());
            case 2 -> supr = tui.leermsg(tui.buscacognom());
            case 3 -> supr = tui.leermsg(tui.buscatel());
            case 4 -> supr = tui.leermsg(tui.buscaemail());
            case 5 -> buscarid = tui.leerint(tui.buscaid());
            default -> {
                tui.showmsg(tui.cantsearch());
                return;
            }
        }


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
                tui.showmsg(tui.eliminat() + c.getNom());
                return;
            }
        }
        tui.showmsg(tui.notrobat());
    }

    public void llistacontactes() {
        tui.showmsg(tui.llistat());
        if (Contactes.isEmpty()) {
            tui.showmsg(tui.res());
            return;
        }

        for (contacte c : Contactes) {
            tui.showmsg(c.toString());
        }
    }
    public void exit() {
        tui.showmsg(tui.exit());
        System.exit(0);
    }
    public void invalid() {
        tui.showmsg(tui.invalidtryagain());
    }
}
