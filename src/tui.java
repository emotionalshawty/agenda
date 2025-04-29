import java.util.ArrayList;
import java.util.Scanner;


public class tui {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<contacte> Contactes = new ArrayList<>();
        boolean cont = true;

        System.out.println("Benvolgut a la teva agenda, esscull una opció");
        System.out.println("1. Crea un contacte.");
        System.out.println("2. Busca un contacte existent.");
        System.out.println("3. Actualitza un contacte existent.");
        System.out.println("4. Esborra un contacte existent.");
        System.out.println("5. LListat de  tots els contactes");

        int opcio = sc.nextInt();

        System.out.println("Has escollit l'opció "+ opcio);
        switch (opcio) {
            case 1:

                break;
            case 2:
                System.out.println("Posa el nom del conrtacte que vols buscar:");
                String buscar = sc.next();
                if (Contactes.contains(buscar)) {
                    System.out.println("S'ha trobat"+ buscar);
                } else {
                    System.out.println("No s'ha trobat res ");
                }
                break;
            case 3:
                System.out.println("posa el nom del contacte que vols actualitzar:");
                String actu = sc.next();
                if (Contactes.contains(actu)) {
                    System.out.println("Posa el nou nom del contacte:");
                    String nouNom = sc.next();
                    Contactes.set(Contactes.indexOf(actu), nouNom);
                    System.out.println("S'ha actualitzat el contacte a: " + nouNom);
                } else {
                    System.out.println("No s'ha trobat un contacte amb el nom " + actu );
                }
                break;
            case 4:
                System.out.println("Posa el nom del contacte que vols esborrar");
                String esbor = sc.next();
                if (Contactes.remove(esbor)) {
                    System.out.println("s'ha esborrat " + esbor );
                } else {
                    System.out.println("No s'ha trobat un contacte amb aquest nom ");
                }
                break;
            case 5:
                System.out.println("Sortint...");
                break;
            default:
                System.out.println("L'opció que has escollit no es valida, prova'n una altra");
        }
    }
}
