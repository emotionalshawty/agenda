
public class Main {


    public static void main(String[] args) {
        tui tui = new tui();
        control app = new control(tui);

        while (true) {
            int opcio = app.menu();
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
                    app.exit();
                    break;
                default:
                    app.invalid();
            }
        }

    }



}