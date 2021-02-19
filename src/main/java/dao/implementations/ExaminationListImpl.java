package dao.implementations;

import dao.interfaces.ExaminationListDao;
import dao.model.ExaminationList;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExaminationListImpl implements ExaminationListDao {

    private static final Logger logger = LoggerFactory.getLogger(ExaminationList.class);

    @Override
    public int create(ExaminationList record) {
        String sqlCreateRecord = "INSERT INTO EXAMINATION_RECORDS " +
                "(STUDENT_ID, EXAM_ID, GRADE) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sqlCreateRecord);
            ps.setInt(1, record.getStudentId());
            ps.setInt(2, record.getExamId());
            ps.setInt(3, record.getGrade());
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.info("Can`t add new exam record to DB");
            e.printStackTrace();
        } finally {
            DBConnection.close(ps, conn);
        }
        return rows;
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
            conn = DBConnection.getConnection();
            st = conn.prepareStatement(sqlGetAll);
            rs = st.getResultSet();
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

    @Override // TODO !!!!!!!!!!!!!!!!!!!
    public List<ExaminationList> getByFaculty(int facultyId) {
        String sqlGetAll = "SELECT * FROM EXAMINATION_RECORDS WHERE FACULTY_ID = ?";
        List<ExaminationList> recordList = new ArrayList<>();
        ExaminationList record;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sqlGetAll);
            ps.setInt(1, facultyId);
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
        return null;
    }

    @Override
    public int update(ExaminationList record) {
        String sqlUpdate = "UPDATE" +
                "EXAMINATION_RECORDS" +
                "SET" +
                "STUDENT_ID = ?, " +
                "EXAM_ID = ?, " +
                "GRADE = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        int rows;
        try {
            conn = DBConnection.getConnection ();
            ps = conn.prepareStatement (sqlUpdate);
            ps.setInt(1, record.getStudentId());
            ps.setInt(2, record.getExamId());
            ps.setInt (3, record.getGrade());
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
        String sqlDelete = "DELETE" +
                "FROM EXAMINATION_RECORDS" +
                "WHERE ID = ?";
        int rows;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
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
}
