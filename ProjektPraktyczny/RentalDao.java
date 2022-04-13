package ProjektPraktyczny;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RentalDao implements RentalDaoInterface<Rental, String> {

    private Session currentSession;
    private Transaction currentTransaction;

    public RentalDao() {

    }
    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
//        Configuration configuration = new Configuration().configure();
//        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties());
//        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
//        return sessionFactory;

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
    public void persist(Rental entity) {
        getCurrentSession().save(entity);
    }

    public void update(Rental entity) {
        getCurrentSession().update(entity);
    }

    public Rental findById(String id) {
        Rental rental = (Rental) getCurrentSession().get(Rental.class, id);
        return rental;
    }

    public void delete(Rental entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Rental> findAll() {
        List<Rental> rentals = (List<Rental>) getCurrentSession().createQuery("from Rental").list();
        return rentals;
    }

    public void deleteAll() {
        List<Rental> entityList = findAll();
        for (Rental entity : entityList) {
            delete(entity);
        }
    }
}
