package main.java;

import jakarta.persistence.*;

@Entity
@Table(name = "contactes")

public class Contacte {
    static int idcontador = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "nom")
    String nom;

    @Column(name = "cognom")
    String cognom;

    @Column(name = "telefon")
    String tel;

    @Column(name = "email")
    String email;

    public Contacte(String nom, String cognom, String tel, String email) {
        this.id = idcontador++;
        this.nom = nom;
        this.cognom = cognom;
        this.tel = tel;
        this.email = email;
    }

    // Construc pa archivos
    public Contacte(int id, String nom, String cognom, String tel, String email) {
        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
        this.tel = tel;
        this.email = email;
    }

    // constructor para el hibernat
    public Contacte() {
    }

    public static void resetid() {
        idcontador = 1;
    }


    public static void actuid(int loadedId) {
        if (loadedId >= idcontador) {
            idcontador = loadedId + 1;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
