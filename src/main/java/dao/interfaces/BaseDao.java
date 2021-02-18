package dao.interfaces;

import java.util.List;

public interface BaseDao<T> {
    List<T> getAll();
    T getById(int id);
    int update(T obj);
    int delete(int id);
    int create(T obj); //create
}
