package dao.interfaces;

import dao.model.Exam;

import java.util.List;

public interface ExamDao extends BaseDao<Exam> {
    int getIdByName(String name);
    String getNameById(int id);
    List<Integer> getByFaculty(int id);

}
