package pe.gob.osce.rnp.seg.generic;

import java.util.List;

public interface BaseService <T> {

    T save(T entity);

    T update(T entity);

    T findOne(int id);

    T findOneWithFT(int id);

    void delete(int id);

    List<T> findAll();

}
