import java.util.Scanner;

public class tui {
    Scanner sc = new Scanner(System.in);

    public tui() {

    }
    public void showmsg(String msg) {
        System.out.println(msg);
    }
    public String leermsg(String msg) {
        showmsg(msg);
        return sc.nextLine();
    }
    public int leerint(String msg) {
        showmsg(msg);
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
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

        // tui solo que tiene print y scanner, hacer que solo tui tenga print y scanner, tui y control estan raros, hacer que main sea el "gestor" de comunicacion entre los 2
    }

}
