package ProjektPraktyczny;

import java.util.List;

public class CustomerService {
    private static CustomersDao customersDao;

    public CustomerService() {
        customersDao = new CustomersDao();
    }
    public void persist(Customers entity) {
        customersDao.openCurrentSessionwithTransaction();
        customersDao.persist(entity);
        customersDao.closeCurrentSessionwithTransaction();
    }

    public void update(Customers entity) {
        customersDao.openCurrentSessionwithTransaction();
        customersDao.update(entity);
        customersDao.closeCurrentSessionwithTransaction();
    }

    public Customers findById(Integer id) {
        customersDao.openCurrentSession();
        Customers customers = customersDao.findById(id);
        customersDao.closeCurrentSession();
        return customers;
    }

    public void delete(Integer id) {
        customersDao.openCurrentSessionwithTransaction();
        Customers customers = customersDao.findById(id);
        customersDao.delete(customers);
        customersDao.closeCurrentSessionwithTransaction();
    }

    public List<Customers> findAll() {
        customersDao.openCurrentSession();
        List<Customers> customers = customersDao.findAll();
        customersDao.closeCurrentSession();
        return customers;
    }

    public void deleteAll() {
        customersDao.openCurrentSessionwithTransaction();
        customersDao.deleteAll();
        customersDao.closeCurrentSessionwithTransaction();
    }

    public CustomersDao customersDao() {
        return customersDao;
    }
}
