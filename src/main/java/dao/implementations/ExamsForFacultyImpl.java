package dao.implementations;

import dao.interfaces.ExamForFacultyDao;
import dao.model.ExamForFaculty;
import dao.model.ExaminationList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ExamsForFacultyImpl implements ExamForFacultyDao {

    private static final Logger logger = LoggerFactory.getLogger(ExamsForFacultyImpl.class);

    @Override
    public List<ExamForFaculty> getAll() {
        return null;
    }

    /**
     * don`t use id in table. method return null
     */
    @Override
    public ExamForFaculty getById(int id) {
        return null;
    }

    @Override
    public int update(ExamForFaculty obj) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int create(ExamForFaculty obj) {
        return 0;
    }
}
