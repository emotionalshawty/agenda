import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<contacte> Contactes = new ArrayList<>();

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    private void run() {
        boolean cont = true;

        while (cont) {
            System.out.println("\n1. Crear contacte");
            System.out.println("2. Buscar contacte");
            System.out.println("3. Actualitzar contacte");
            System.out.println("4. Eliminar contacte");
            System.out.println("5. Llistar contactes");
            System.out.println("6. Sortir");
            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();
            sc.nextLine();

            switch (opcio) {
                case 1 -> crearcontacte();
                case 2 -> buscarcontacte();
                case 3 -> actucontact();
                case 4 -> elimcontacte();
                case 5 -> llistacontactes();
                case 6 -> cont = false;
                default -> System.out.println("Opció no vàlida.");
            }
        }
    }

    private void crearcontacte() {
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

    private void buscarcontacte() {
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

    private void actucontact() {
        System.out.println("Qiun es el nom del contacte que vols actualitzar?");
        String actu = sc.nextLine();

        for (contacte c : Contactes) {
            if (c.getNom().equalsIgnoreCase(actu)) {
                System.out.println("Posa el nou nom del contacte:");
                String nouNom = sc.nextLine();
                c.setNom(nouNom);
                System.out.println("S'ha actualitzat el contacte a: " + nouNom);
                return;
            }
        }
        System.out.println("No s'ha trobat un contacte que es digui " + actu);
    }

    private void elimcontacte() {
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

    private void llistacontactes() {
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