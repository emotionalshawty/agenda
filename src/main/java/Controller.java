package main.java;

import java.util.HashMap;


public interface Controller {

    void crearcontacte(String nom, String cognom, String tel, String email);

    Contacte buscarcontacte(int combuscar, String busca, int buscarid);

    void actucontact(int combuscar, String busca, int buscarid, int quin, String nouValor);

    boolean elimcontacte(int combuscar, String busca, int buscarid);

    HashMap<Integer, Contacte> getContactes();
}
