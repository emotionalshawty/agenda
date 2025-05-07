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


}
