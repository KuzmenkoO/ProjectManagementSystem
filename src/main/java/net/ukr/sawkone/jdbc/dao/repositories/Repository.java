package net.ukr.sawkone.jdbc.dao.repositories;

import java.util.List;

public interface Repository<T> {

    T findById(long id);

    T create(T entity);

    void update(T entity);

    void deleteById(long id);

    List<T> findAll();
}
