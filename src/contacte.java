public class contacte {
    String nom;
    String tel;
    String email;

    public contacte(String nom, String tel, String email) {
        this.nom = nom;
        this.tel = tel;
        this.email = email;
    }

    public String toString(){
        return "Nom: "+nom+" Telèfon: "+tel+" Email: "+email;
    }

    public String getNom() {
        return nom;
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

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
