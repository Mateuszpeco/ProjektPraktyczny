package ProjektPraktyczny;

import java.util.List;

public class RentalService {

    private static RentalDao rentalDao;
    public RentalService() {
        rentalDao = new RentalDao();
    }
    public void persist(Rental entity) {
        rentalDao.openCurrentSessionwithTransaction();
        rentalDao.persist(entity);
        rentalDao.closeCurrentSessionwithTransaction();
    }

    public void update(Rental entity) {
        rentalDao.openCurrentSessionwithTransaction();
        rentalDao.update(entity);
        rentalDao.closeCurrentSessionwithTransaction();
    }

    public Rental findById(Integer id) {
        rentalDao.openCurrentSession();
        Rental rental = rentalDao.findById(id);
        rentalDao.closeCurrentSession();
        return rental;
    }

    public void delete(Integer id) {
        rentalDao.openCurrentSessionwithTransaction();
        Rental rental = rentalDao.findById(id);
        rentalDao.delete(rental);
        rentalDao.closeCurrentSessionwithTransaction();
    }

    public List<Rental> findAll() {
        rentalDao.openCurrentSession();
        List<Rental> rentals = rentalDao.findAll();
        rentalDao.closeCurrentSession();
        return rentals;
    }

    public void deleteAll() {
        rentalDao.openCurrentSessionwithTransaction();
        rentalDao.deleteAll();
        rentalDao.closeCurrentSessionwithTransaction();
    }

    public RentalDao rentalDao() {
        return rentalDao;
    }

}
