package dao.implementations;

import dao.interfaces.ExaminationListDao;
import dao.model.ExaminationList;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.AppException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExaminationListImpl implements ExaminationListDao {

    private static final Logger logger = LoggerFactory.getLogger(ExaminationList.class);

    @Override
    public int create(ExaminationList record) {
        String sqlCreateRecord = "INSERT INTO EXAMINATION_RECORDS " +
                "(STUDENT_ID, EXAM_ID, GRADE) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement(sqlCreateRecord);
            ps.setInt(1, record.getStudentId());
            ps.setInt(2, record.getExamId());
            ps.setInt(3, record.getGrade());
            ps.executeUpdate();
            rs = ps.executeQuery("SELECT SQ_EXAM_RECORD_ID.CURRVAL FROM DUAL");
            int id = -1;
            if (rs.next()) {
                id = (int) rs.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            logger.info("Can`t add new exam record to DB");
            e.printStackTrace();
        } finally {
            DBConnection.close(ps, conn);
        }
        return -1;
    }

    @Override
    public List<ExaminationList> getAll() {
        String sqlGetAll = "SELECT * FROM EXAMINATION_RECORDS";
        List<ExaminationList> recordList = new ArrayList<>();
        ExaminationList record;
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            st = conn.createStatement();
            rs = st.executeQuery(sqlGetAll);
            while (rs.next()) {
                record = new ExaminationList();
                record.setRecordId(rs.getInt("EXAM_RECORD_ID"));
                record.setStudentId(rs.getInt("STUDENT_ID"));
                record.setExamId(rs.getInt("EXAM_ID"));
                record.setGrade(rs.getInt("GRADE"));
                recordList.add(record);
            }
        } catch (SQLException e) {
            logger.warn("Can`t get all examination records");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, st, conn);
        }
        return recordList;
    }

    @Override
    public List<ExaminationList> getRecordsByStudent(int studentId) {
        String sqlGetAll = "SELECT * FROM EXAMINATION_RECORDS WHERE STUDENT_ID = ?";
        List<ExaminationList> recordList = new ArrayList<>();
        ExaminationList record;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement(sqlGetAll);
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                record = new ExaminationList();
                record.setRecordId(rs.getInt("EXAM_RECORD_ID"));
                record.setStudentId(rs.getInt("STUDENT_ID"));
                record.setExamId(rs.getInt("EXAM_ID"));
                record.setGrade(rs.getInt("GRADE"));
                recordList.add(record);
            }
        } catch (SQLException e) {
            logger.warn("Can`t get all examination records");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return recordList;
    }

    @Override
    public ExaminationList getById(int id) {
        ExaminationList record;
        String sqlGetById = "SELECT * FROM EXAMINATION_RECORDS WHERE EXAM_RECORD_ID = ?";
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
                record = new ExaminationList();
                record.setRecordId(rs.getInt("EXAM_RECORD_ID"));
                record.setStudentId(rs.getInt("STUDENT_ID"));
                record.setExamId(rs.getInt("EXAM_ID"));
                record.setGrade(rs.getInt("GRADE"));
                return record;
            }
        } catch (SQLException e) {
            logger.warn("Troubles with getting exam record by his ID");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return null;
    }

    @Override
    public int update(ExaminationList record) {
        String sqlUpdate = "UPDATE " +
                "EXAMINATION_RECORDS " +
                "SET " +
                "STUDENT_ID = ?, " +
                "EXAM_ID = ?, " +
                "GRADE = ?" +
                "WHERE EXAM_RECORD_ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        int rows;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement (sqlUpdate);
            ps.setInt(1, record.getStudentId());
            ps.setInt(2, record.getExamId());
            ps.setInt (3, record.getGrade());
            ps.setInt (4, record.getRecordId());
            rows = ps.executeUpdate();
            return rows;
        } catch (SQLException e) {
            logger.warn ("Error when update examination records list");
            e.printStackTrace ();
        } finally {
            DBConnection.close (ps, conn);
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        String sqlDelete = "DELETE " +
                "FROM EXAMINATION_RECORDS " +
                "WHERE EXAM_RECORD_ID = ?";
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
            logger.warn("Error when try to delete record from examination records list by id");
            e.printStackTrace();
        } finally {
            DBConnection.close (ps, conn);
        }
        return 0;
    }

    @Override
    public double getAverageMarkByExam(int examId) {
        String sql = "SELECT AVG(grade) FROM EXAMINATION_RECORDS WHERE EXAM_ID = ?";
        double avgMark = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement(sql);
            ps.setInt(1, examId);
            rs = ps.executeQuery();
            if (rs.next()) {
                avgMark = rs.getDouble(1);
            }
        } catch (SQLException e) {
            logger.warn("Error when ask for average mark by exam");
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return avgMark;
    }
}