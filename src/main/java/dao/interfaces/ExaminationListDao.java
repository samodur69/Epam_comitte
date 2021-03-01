package dao.interfaces;

import dao.model.ExaminationList;

import java.util.List;

public interface ExaminationListDao extends BaseDao<ExaminationList> {

    List<ExaminationList> getRecordsByStudent(int studentId);
    double getAverageMarkByExam(int examId);
}
