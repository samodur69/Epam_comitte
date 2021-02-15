package dao.implementations;

import dao.interfaces.ExamDao;
import data.DBConnection;
import dao.model.Exam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
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
    public List<Integer> getByFaculty(int facultyId) {
        Exam exam;
        String sqlGetExams = "SELECT EXAM_ID FROM EXAMS_FACULTY WHERE FACULTY_ID = ?";
        List<Exam> exams = new ArrayList<>();
        List<Integer> exams_id = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sqlGetExams);
            ps.setInt(1, facultyId);
            rs = ps.executeQuery();
            while (rs.next()) {
//                exam = new Exam();
//                exam = this.getById(rs.getInt("EXAM_ID"));
//                exams.add(exam);
                exams_id.add(rs.getInt(1));
            }
            return exams_id;
        } catch (SQLException e) {
            logger.info("Bug when extract exams for chosen faculty");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return null;
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

    @Override
    public String getNameById(int id) {
        String sqlGetId = "SELECT EXAM_NAME FROM EXAMS_LIST WHERE EXAM_ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String examName = "";
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sqlGetId);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                examName = rs.getString("EXAM_NAME");
            }
        } catch (SQLException | NullPointerException e) {
            logger.info("Bug when get Exam name by its id");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return examName;
    }
}
