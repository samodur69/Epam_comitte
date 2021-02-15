package dao.implementations;

import dao.interfaces.ExamDao;
import data.DBConnection;
import dao.model.Exam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class ExamImpl implements ExamDao {
    private static final Logger logger = LoggerFactory.getLogger(ExamImpl.class);

    @Override
    public List<Exam> getAll() {
        return null;
    }

    @Override
    public Exam getById(int id) {
        return null;
    }

    @Override
    public void update(Exam obj) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void create(Exam obj) {

    }

    @Override
    public int getIdByName(String examName) {
        String sqlGetId = "SELECT EXAM_ID FROM EXAMS_LIST WHERE EXAM_NAME = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int examId = 0;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sqlGetId);
            ps.setString(1, examName);
            rs = ps.executeQuery();
            while (rs.next()) {
                examId = rs.getInt("EXAM_ID");
            }
        } catch (SQLException | NullPointerException e) {
            logger.info("Bug when get Exam id by it name");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return examId;
    }
}
