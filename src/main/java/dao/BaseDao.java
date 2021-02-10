package dao;

import java.util.List;

public interface BaseDao<E, K> {
    public List<E> getAll();
    public E update(E entity);
    public boolean delete(K id);
    public boolean create(E entity);
}
