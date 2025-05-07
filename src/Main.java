import java.util.ArrayList;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        control app = new control(tui);
        tui tui = new tui();
        while (true) {
            int opcio = tui.menu();
            switch (opcio) {
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
                    System.out.println("Estàs sortint de l'aplicació");
                    System.exit(0);
                    break;
                default:
                    System.out.println("No has escollit una opció valida");
            }
        }

    }



}