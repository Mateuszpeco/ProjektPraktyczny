package ProjektPraktyczny;

import java.util.List;

public class CarService {
    private static CarDao carDao;

    public CarService() {
        carDao = new CarDao();
    }
    public void persist(Car entity) {
        carDao.openCurrentSessionwithTransaction();
        carDao.persist(entity);
        carDao.closeCurrentSessionwithTransaction();
    }

    public void update(Car entity) {
        carDao.openCurrentSessionwithTransaction();
        carDao.update(entity);
        carDao.closeCurrentSessionwithTransaction();
    }

    public Car findById(Integer id) {
        carDao.openCurrentSession();
        Car car = carDao.findById(id);
        carDao.closeCurrentSession();
        return car;
    }

    public void delete(Integer id) {
        carDao.openCurrentSessionwithTransaction();
        Car car = carDao.findById(id);
        carDao.delete(car);
        carDao.closeCurrentSessionwithTransaction();
    }

    public List<Car> findAll() {
        carDao.openCurrentSession();
        List<Car> cars = carDao.findAll();
        carDao.closeCurrentSession();
        return cars;
    }

    public void deleteAll() {
        carDao.openCurrentSessionwithTransaction();
        carDao.deleteAll();
        carDao.closeCurrentSessionwithTransaction();
    }

    public CarDao carDao() {
        return carDao;
    }
}
