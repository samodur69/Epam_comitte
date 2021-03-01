package dao.interfaces;

import dao.model.ExamForFaculty;

public interface ExamForFacultyDao extends BaseDao<ExamForFaculty> {

    @Override
    ExamForFaculty getById(int id);
}
