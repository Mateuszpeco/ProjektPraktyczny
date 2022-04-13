package ProjektPraktyczny;

import java.io.Serializable;
import java.util.List;

public interface RentalDaoInterface <T, Id extends Serializable> {

    void persist(T entity);

    void update(T entity);

    T findById(Id id);

    void delete(T entity);

    List<T> findAll();

    void deleteAll();
}
