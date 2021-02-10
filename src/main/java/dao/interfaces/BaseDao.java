package dao.interfaces;

import java.util.List;

public interface BaseDao<T> {
    List<T> getAll();
    T getById(int id);
    void update(T obj);
    void delete(int id);
    void create(T obj); //create
}
