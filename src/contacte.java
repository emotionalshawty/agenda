import java.io.FileWriter;
import java.io.IOException;

public class contacte {
    static int idcontador = 1;
    int id;
    String nom;
    String cognom;
    String tel;
    String email;

    public contacte(String nom, String cognom, String tel, String email) {
        this.id = idcontador++;
        this.nom = nom;
        this.cognom = cognom;
        this.tel = tel;
        this.email = email;
        String datos = "ID: "+ id + "Nom: "+ nom + "Cognom: "+cognom+ "Telefono"+tel+ "Email: "+email;
        try {
            FileWriter file = new FileWriter("Contactos.txt");
            file.write(datos);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public int getId() {
        return id;
    }
    public String toString(){
        return "ID: "+id+" Nom: "+nom+ " Cognom: "+cognom+" Telèfon: "+tel+" Email: "+email;
    }

    public String getNom() {
        return nom;
    }
    public String getCognom() {
        return cognom;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
