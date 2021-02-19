package dao.interfaces;

import dao.model.Exam;
import java.util.List;

public interface ExamDao extends BaseDao<Exam> {

    String getNameById(int id);
    List<Integer> getIdListByFacultyId(int id);
}
