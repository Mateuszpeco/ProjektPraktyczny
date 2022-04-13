package ProjektPraktyczny;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class CarDao {

    private Session currentSession;
    private Transaction currentTransaction;

    public CarDao() {

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
    public void persist(Car entity) {
        getCurrentSession().save(entity);
    }

    public void update(Car entity) {
        getCurrentSession().update(entity);
    }

    public Car findById(String id) {
        Car car = (Car) getCurrentSession().get(Car.class, id);
        return car;
    }

    public void delete(Car entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Car> findAll() {
        List<Car> cars = (List<Car>) getCurrentSession().createQuery("from Car").list();
        return cars;
    }

    public void deleteAll() {
        List<Car> entityList = findAll();
        for (Car entity : entityList) {
            delete(entity);
        }
    }
}
