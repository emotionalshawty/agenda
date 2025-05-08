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
        int num = sc.nextInt();
        sc.nextLine();
        return num;
    }

    public String menu() {
        return """ 
                Benvingut a la teva agenda de contactes, que vols fer?
                1. Crear contacte
                2. Buscar contacte
                3. Actualitzar contacte
                4. Eliminar contacte
                5. Llistar els contactes
                6. Sortir
                """;
    }

    public String select() {
        return "Selecciona una opció: ";
    }


    public String notrobat() {
        return "NO es pot buscar un contacte d'aquesta manera";
    }

    public String exit() {
        return "Estas sortint de l'agenda";
    }

    public String solnom() {
        return "POsa el nom de la persona:";
    }

    public String solcognom() {
        return "Posa el seu cognom:";
    }

    public String soltel() {
        return "Introdueix el seu número de telèfon:";
    }

    public String solemail() {
        return "Quin email té? ";
    }

    public String creat() {
        return "S'ha creat l'entrada per a ";
    }

    public String combuscar() {
        return """
                DE quina manera vols buscar el contacte?
                1. Nom
                2. Cognom
                3. Telèfon
                4. Email
                5. ID
                """;
    }

    public String buscanom() {
        return "COm es diu el contacte?";
    }

    public String buscacognom() {
        return "Quin cognom té?";
    }

    public String buscatel() {
        return "Quin numero de telèfon té el contacte?";
    }

    public String buscaemail() {
        return "Quin email té?";
    }

    public String buscaid() {
        return "Quin es el id del contacte?";
    }

    public String cantsearch() {
        return "NO es pot buscar un contacte d'aquesta manera";
    }

    public String trobat() {
        return "S'ha trobat a: ";
    }


    public String queactu() {
        return """
                Què vols actualitzar?
                1. Nom
                2. Cognom
                3. Telèfon
                4. Email
                """;
    }

    public String posnom() {
        return "POsa el nou nom";
    }

    public String poscognom() {
        return "Posa el nou cognom";
    }

    public String postel() {
        return "Posa el nou # de telèfon";
    }

    public String posemail() {
        return "Quin sera elnou correu electrònic?";
    }


    public String nounom() {
        return "EL nom nou de la persona es: ";
    }

    public String noucognom() {
        return "El nou cognom es: ";
    }

    public String nouemail() {
        return "El nou email es: ";
    }

    public String nouTel() {
        return "S'ha actualitzat al seguent nou telèfon: ";
    }
    public String invalid() {
        return "Opció no vàlida.";
    }




    public String eliminat() {
        return "S'ha esborraty el contacte";
    }

    public String llistat() {
        return "\n== LLISTAT DE CONTACTES ==";
    }
    public String res() {
        return "No hi ha cap contacte guardat.";
    }


    public String invalidtryagain(){
        return "L'opció que has escollit no es valida, prova de nou";
    }
}
