package main.java;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

// Sources:
//      https://docs.jboss.org/hibernate/orm/6.6/quickstart/html_single/
//      https://www.baeldung.com/hibernate-criteria-queries
//      https://hibernate.org/orm/quickly/

public class DataBaseController implements Controller, AutoCloseable {
    private final SessionFactory factory;
    private final Session session;
    private final CriteriaBuilder criteriaBuilder;

    public DataBaseController(){
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        this.factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        this.session = this.factory.openSession();
        this.criteriaBuilder = this.factory.getCriteriaBuilder();
    }

    public void close() throws Exception {
        this.session.close();
        this.factory.close();
    }


    public void crearcontacte(String nom, String cognom, String tel, String email) {
        Contacte c = new Contacte(nom, cognom, tel, email);
        Transaction transaction = this.session.beginTransaction();
        this.session.persist(c);
        transaction.commit();
    }

    public Contacte buscarcontacte(int combuscar, String busca, int buscarid) {
        if (buscarid > 0 && combuscar == 5) {
            return this.session.get(Contacte.class, buscarid);
        }

        switch (combuscar) {
            case 1: // Nom
                return cercarPrimerPerCamp("nom", busca);
            case 2: // Cognom
                return cercarPrimerPerCamp("cognom", busca);
            case 3: // Telèfon
                return cercarPrimerPerCamp("tel", busca);
            case 4: // Email
                return cercarPrimerPerCamp("email", busca);
            default:
                return null;
        }
    }

    public void actucontact(int combuscar, String busca, int buscarid, int quin, String nouValor) {
        Contacte c = buscarcontacte(combuscar, busca, buscarid);
        if (c == null) return;

        Transaction transaction = this.session.beginTransaction();

        switch (quin) {
            case 1:
                c.setNom(nouValor);
                break;
            case 2:
                c.setCognom(nouValor);
                break;
            case 3:
                c.setTel(nouValor);
                break;
            case 4:
                c.setEmail(nouValor);
                break;
        }

        this.session.merge(c);
        transaction.commit();
    }

    public boolean elimcontacte(int combuscar, String busca, int buscarid) {
        Contacte c = buscarcontacte(combuscar, busca, buscarid);
        if (c != null) {
            Transaction transaction = this.session.beginTransaction();
            this.session.remove(c);
            transaction.commit();
            return true;
        }
        return false;
    }

    public HashMap<Integer, Contacte> getContactes() {
        HashMap<Integer, Contacte> contactes = new HashMap<>();

        CriteriaQuery<Contacte> cr = this.criteriaBuilder.createQuery(Contacte.class);
        Root<Contacte> root = cr.from(Contacte.class);

        List<Contacte> llista = this.session.createQuery(cr.select(root)).getResultList();

        for (Contacte c : llista) {
            contactes.put(c.getId(), c);
        }

        return contactes;
    }

    // Mètode auxiliar

    private Contacte cercarPrimerPerCamp(String camp, String valor) {
        CriteriaQuery<Contacte> cr = this.criteriaBuilder.createQuery(Contacte.class);
        Root<Contacte> root = cr.from(Contacte.class);

        cr.select(root).where(
            this.criteriaBuilder.equal(root.get(camp), valor)
        );

        List<Contacte> resultats = this.session.createQuery(cr).setMaxResults(1).getResultList();
        return resultats.isEmpty() ? null : resultats.get(0);
    }
}
