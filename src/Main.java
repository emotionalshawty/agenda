import java.util.ArrayList;
import java.util.Scanner;

public class Main extends tui {
    Scanner sc = new Scanner(System.in);
    ArrayList<contacte> Contactes = new ArrayList<>();
    public static void main(String[] args) {

        boolean cont = true;


        private void crearcontact    ( sc, ArrayList<contacte> Contactes){
            System.out.println("POsa el nom del contacte que vols crear:");
            String nom = sc.nextLine();

            System.out.println("Posa el numero de telefon:");
            String tel = sc.nextLine();

            System.out.println("Posa el email");
            String email = sc.nextLine();
            contacte contactenou = new contacte(nom,tel,email);
            Contactes.add(contactenou );

            System.out.println("S'ha creat l'entrada de contacte de " + nom);
        }
        private void buscarcontacte( sc, ArrayList<contacte> Contactes){
            System.out.println("Posa el nom del conrtacte que vols buscar:");
            String buscar = sc.next();
            if (Contactes.contains(buscar)) {
                System.out.println("S'ha trobat"+ buscar);
            } else {
                System.out.println("No s'ha trobat res ");
            }
        }
        private void actucontacte( sc, ArrayList<contacte> Contactes) {
            System.out.println("posa el nom del contacte que vols actualitzar:");
            String actu = sc.next();
            if (Contactes.contains(actu)) {
                System.out.println("Posa el nou nom del contacte:");
                String nouNom = sc.next();
                Contactes.set(Contactes.indexOf(actu), nouNom);
                System.out.println("S'ha actualitzat el contacte a: " + nouNom);
            } else {
                System.out.println("No s'ha trobat un contacte amb el nom " + actu);
            }
        }
        private void elimcontact( sc, ArrayList<contacte> Contactes) {
            System.out.println("Posa el nom del contacte que vols esborrar");
            String esbor = sc.next();
            if (Contactes.remove(esbor)) {
                System.out.println("s'ha esborrat " + esbor);
            } else {
                System.out.println("No s'ha trobat un contacte amb aquest nom ");
            }
        }
        private void llistacontactes(sc, ArrayList<contacte> Contactes;) {
            System.out.println("\n== LLISTAT DE CONTACTES ==");
            ArrayList<contacte> contactes = gestor.getContactes();

            if (contactes.isEmpty()) {
                System.out.println("No hi ha contactes registrats.");
                return;
            }

            for (int i = 0; i < contactes.size(); i++) {
                System.out.println((i + 1) + ". " + contactes.get(i));
            }
        }

    }



    }
}
