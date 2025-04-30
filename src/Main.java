import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    Scanner sc = new Scanner(System.in);
    private ArrayList<contacte> Contactes = new ArrayList<>();

    public static void main(String[] args) {
        Main app = new Main();
        tui tui = new tui();
        while (true) {
            switch (tui.menu()) {
                case 1:
                    app.crearcontacte();
                    break;
                case 2:
                    app.buscarcontacte();
                    break;
                case 3:
                    app.actucontact();
                    break;
                case 4:
                    app.elimcontacte();
                    break;
                case 5:
                    app.llistacontactes();
                    break;
                case 6:
                    System.out.println("Sortint de l'aplicació...");
                    System.exit(0);
                    break;
                default: // Opció no vàlida
                    System.out.println("Opció no vàlida.");
            }
        }

    }


    public void crearcontacte() {
        System.out.println("POsa el nom de la persona:");
        String nom = sc.nextLine();

        System.out.println("Introdueix el seu número de telèfon:");
        String tel = sc.nextLine();

        System.out.println("Quin correu electronic té? ");
        String email = sc.nextLine();

        contacte contactenou = new contacte(nom, tel, email);
        Contactes.add(contactenou);

        System.out.println("S'ha creat l'entrada de contacte de " + nom);
    }

    public void buscarcontacte() {
        System.out.println("P0sa el nom del contacte que vols buscar");
        String busca = sc.nextLine();

        for (contacte trobat : Contactes) {
            if (trobat.getNom().equalsIgnoreCase(busca)) {
                System.out.println("S'ha trobat: " + trobat);
                return;
            }
        }
        System.out.println("No s'ha trobat a ninguna persona amb el nom de " + busca);
    }

    public void actucontact() {
        System.out.println("Qiun es el nom del contacte que vols actualitzar?");
        String actu = sc.nextLine();

        for (contacte c : Contactes) {
            if (c.getNom().equalsIgnoreCase(actu)) {

                System.out.println("Quin camp vols actualitzar?");
                System.out.println("1. Nom");
                System.out.println("2. Telèfon");
                System.out.println("3. Correu electrònic");
                System.out.print("Selecciona una opció: ");
                int quin = sc.nextInt();
                switch (quin) {
                    case 1 -> {
                        System.out.println("Posa el nou nom");
                        String nouNom = sc.nextLine();
                        c.setNom(nouNom);
                        System.out.println("S'ha actualitzat el nom a " + nouNom);
                    }
                    case 2 -> {
                        System.out.println("Posa el nou # de telèfon:");
                        String nouTel = sc.nextLine();
                        c.setTel(nouTel);
                        System.out.println("El nou telèfon es" + nouTel);
                    }
                    case 3 -> {
                        System.out.println("Quin serà el nou correu electrònic?");
                        String nouEmail = sc.nextLine();
                        c.setEmail(nouEmail);
                        System.out.println("S'ha actualitzat al seguent nou correu: " + nouEmail);
                    }
                    default -> System.out.println("Opció no vàlida.");
                }
                sc.nextLine();
                return;
            }
        }
        System.out.println("No s'ha trobat un contacte que es digui " + actu);
    }

    public void elimcontacte() {
        System.out.println("Posa el nom del contacte que vols esborrar:");
        String esbor = sc.nextLine();

        for (contacte c : Contactes) {
            if (c.getNom().equalsIgnoreCase(esbor)) {
                Contactes.remove(c);
                System.out.println("S'ha esborrat la entrada de " + esbor);
                return;
            }
        }
        System.out.println("No s'ha trobat un contacte amb aquest nom.");
    }

    public void llistacontactes() {
        System.out.println("\n== LLISTAT DE CONTACTES ==");
        if (Contactes.isEmpty()) {
            System.out.println("No hi ha ningun contacte a la llista.");
            return;
        }

        for (int i = 0; i < Contactes.size(); i++) {
            System.out.println((i + 1) + ". " + Contactes.get(i));
        }
    }
}