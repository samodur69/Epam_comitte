package dao;

import java.util.List;

public interface ExamDao extends BaseDao {
    @Override
    List getAll();

    @Override
    Object update(Object entity);

    @Override
    boolean delete(Object id);

    @Override
    boolean create(Object exam);
}
