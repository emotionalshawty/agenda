import java.util.Scanner;

public class tui {
    Scanner sc = new Scanner(System.in);

    public tui() {

    }

    public int menu() {
        System.out.println("Benvingut a la teva agenda de contactes, que vols fer?");
        System.out.println("\n1. Crear contacte");
        System.out.println("2. Buscar contacte");
        System.out.println("3. Actualitzar contacte");
        System.out.println("4. Eliminar contacte");
        System.out.println("5. Llistar els contactes");
        System.out.println("6. Sortir");
        System.out.print("Selecciona una opció: ");
        int opcio = sc.nextInt();
        sc.nextLine();
        return opcio;
    }

}
