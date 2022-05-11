package academy.kata.dao;

import java.util.List;

public interface Crud <T> {
    void save(T t);

    void update(T t);

    List<T> findAll();

    void delete(T t);

    T findById(Long id);

    void deleteById(long id);
}
