import java.util.Scanner;

public class tui {
    private Scanner sc = new Scanner(System.in);
    private Main app;

    public void ui(Main app) {
        this.app = app;
    }
    public void menu() {
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
                case 1 -> app.crearcontacte();
                case 2 -> app.buscarcontacte();
                case 3 -> app.actucontact();
                case 4 -> app.elimcontacte();
                case 5 -> app.llistacontactes();
                case 6 -> cont = false;
                default -> System.out.println("Opció no vàlida.");
            }
        }
    }
}
