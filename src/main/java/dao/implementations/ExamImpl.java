package dao.implementations;

import dao.interfaces.ExamDao;
import dao.model.Exam;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.AppException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExamImpl implements ExamDao {
    private static final Logger logger = LoggerFactory.getLogger(ExamImpl.class);

    @Override
    public List<Exam> getAll() {
        List<Exam> examsList = new ArrayList<> ();
        Exam exam;
        String sqlGetAll = "SELECT * FROM EXAMS_LIST";
        Connection conn = null;
        Statement st;
        ResultSet rs;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            st = conn.createStatement();
            st.execute(sqlGetAll);
            rs = st.getResultSet();
            while (rs.next()) {
                exam = new Exam ();
                exam.setExamId(rs.getInt("EXAM_ID"));
                exam.setExamName(rs.getString("EXAM_NAME"));
                examsList.add(exam);
            }
        } catch (SQLException | NullPointerException e) {
            logger.warn ("Error when extract all exams");
            e.printStackTrace();
        } finally {
            DBConnection.close(conn);
        }
        return examsList;
    }

    @Override
    public Exam getById(int id) {
        Exam exam;
        String sqlGetById = "SELECT * FROM EXAMS_LIST WHERE EXAM_ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement (sqlGetById);
            ps.setInt (1, id);
            rs = ps.executeQuery ();
            if (rs.next()) {
                exam = new Exam();
                exam.setExamId(rs.getInt("EXAM_ID"));
                exam.setExamName(rs.getString("EXAM_NAME"));
                return exam;
            }
        } catch (SQLException e) {
            logger.warn("Troubles with getting faculty by his ID");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return null;
    }

    @Override
    public int update(Exam exam) {
        String sqlUpdate = "UPDATE " +
                "EXAMS_LIST " +
                "SET " +
                "EXAM_NAME = ? " +
                "WHERE EXAM_ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        int rows;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement (sqlUpdate);
            ps.setString (1, exam.getExamName ());
            ps.setInt (2, exam.getExamId ());
            rows = ps.executeUpdate();
            return rows;
        } catch (SQLException e) {
            logger.warn ("Error when update exam list");
            e.printStackTrace ();
        } finally {
            DBConnection.close (ps, conn);
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        String sqlDelete = "DELETE " +
                "FROM EXAMS_LIST " +
                "WHERE EXAM_ID = ?";
        int rows;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement (sqlDelete);
            ps.setInt (1 , id);
            rows = ps.executeUpdate();
            return rows;
        } catch (SQLException e) {
            logger.warn("Error when try to delete record from exams list by id");
            e.printStackTrace();
        } finally {
            DBConnection.close (ps, conn);
        }
        return 0;
    }

    @Override
    public int create(Exam exam) {
        String sqlCreate = "INSERT INTO EXAMS_LIST " +
                "(EXAM_NAME) " +
                "VALUES (?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement(sqlCreate);
            ps.setString(1, exam.getExamName ());
            ps.executeUpdate();
            rs = ps.executeQuery("SELECT SQ_EXAM_ID.CURRVAL FROM DUAL");
            if (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (SQLException | NullPointerException e) {
            logger.info("Can`t add new faculty to DB");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return id;
    }

    @Override
    public List<Integer> getIdListByFacultyId(int facultyId) {
        String sqlGetExams = "SELECT EXAM_ID FROM EXAMS_FACULTY WHERE FACULTY_ID = ?";
        List<Integer> exams_id = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement(sqlGetExams);
            ps.setInt(1, facultyId);
            rs = ps.executeQuery();
            while (rs.next()) {
                exams_id.add(rs.getInt(1));
            }
            return exams_id;
        } catch (SQLException e) {
            logger.warn("Bug when extract exams ID for chosen faculty");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return null;
    }

    @Override
    public String getNameById(int id) {
        String sqlGetId = "SELECT EXAM_NAME FROM EXAMS_LIST WHERE EXAM_ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String examName = "";
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
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
