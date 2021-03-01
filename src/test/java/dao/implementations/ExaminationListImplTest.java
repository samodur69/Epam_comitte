package dao.implementations;

import dao.model.ExaminationList;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.AppException;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.testng.Assert.*;

public class ExaminationListImplTest {

    private ExaminationListImpl recordListImplUnderTest;
    private static final Logger logger = LoggerFactory.getLogger(ExaminationListImplTest.class);
    private Statement st;
    private Connection conn;
    private List<ExaminationList> recordList;
    private final Random random = new Random();
    private final static String sqlRandomStudentId = "SELECT " +
            "ID " +
            "FROM " +
            "(SELECT ID FROM APPLICANTS " +
            "ORDER BY dbms_random.value) " +
            "WHERE ROWNUM = 1";

    @BeforeClass
    public void setUp() throws SQLException {
        recordListImplUnderTest = new ExaminationListImpl();
        conn = DBConnection.getConnection();
        if (conn != null) {
            st = conn.createStatement();
        }
        logger.info("Start Testing Examination List Implementation");
    }

    @Test
    public void testCreate() throws SQLException {
        String sqlCheckCreate = "SELECT * FROM EXAMINATION_RECORDS WHERE EXAM_RECORD_ID = ?";
        int studentId = getRandomStudentId();
        int examId = 10; // for test use static exam_id
        final ExaminationList record = new ExaminationList(studentId , examId , random.nextInt(100));
        final int result = recordListImplUnderTest.create(record);
        record.setRecordId(result);
        PreparedStatement ps = conn.prepareStatement(sqlCheckCreate);
        ps.setInt(1, result);
        ResultSet rs = ps.executeQuery();
        assertTrue(rs.next(), "Record not created");
    }

    @Test
    public void testGetAll() throws SQLException {
        int expectedCount = -1;
        ResultSet rs = st.executeQuery("SELECT COUNT(EXAM_RECORD_ID) FROM EXAMINATION_RECORDS");
        if (rs.next()) {
            expectedCount = rs.getInt(1);
        }
        recordList = recordListImplUnderTest.getAll();
        assertEquals(recordList.size(), expectedCount, "Wrong number of records from DB");
    }

    @Test
    public void testGetRecordsByStudent() throws SQLException{
        int studentId = -1;
        // test works only when DB table with examination records not empty
        ResultSet rs = st.executeQuery(sqlRandomStudentId);
        if (rs.next()) {
            studentId = rs.getInt(1);
        }
        recordList = recordListImplUnderTest.getRecordsByStudent(studentId);
        boolean isCorrect = true;
        for (ExaminationList el: recordList) {
            if (el.getStudentId() != studentId) {
                isCorrect = false;
                break;
            }
        }
        assertTrue(isCorrect, "Error in Getting exam records by student id");
    }

    @Test
    public void testGetById() {
        // check one random record
        recordList = recordListImplUnderTest.getAll();
        Collections.shuffle(recordList);
        int id = recordList.get(0).getRecordId();
        ExaminationList result = recordListImplUnderTest.getById(id);
        assertEquals(result, recordList.get(0), "Object from ObjectsList not equals Object from DB ");
    }

    @Test
    public void testUpdate() {
        recordList = recordListImplUnderTest.getAll();
        ExaminationList recordUpdate = new ExaminationList();
        if (recordList.size() > 0) {
            recordUpdate = recordList.get(0);
        }
        recordUpdate.setGrade(1);
        final int result = recordListImplUnderTest.update(recordUpdate);
        recordList = recordListImplUnderTest.getAll();
        boolean isUpdated = false;
        for (ExaminationList el: recordList) {
            if (el.equals(recordUpdate)) {
                isUpdated = true;
                break;
            }
        }
        assertEquals(result, 1, "Rows updated ");
        assertTrue(isUpdated, "In DB not found Updated object ");
    }

    @Test
    public void testDelete() throws SQLException {
        // get last record id (last row) to delete
        ResultSet rs = st.executeQuery("SELECT " +
                "MAX(EXAM_RECORD_ID) " +
                "FROM EXAMINATION_RECORDS");
        int idToDelete = 0;
        if (rs.next()) {
            idToDelete = rs.getInt("MAX(EXAM_RECORD_ID)");
        }
        recordListImplUnderTest.delete(idToDelete);
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM EXAMINATION_RECORDS WHERE EXAM_RECORD_ID = ?");
        ps.setInt(1, idToDelete);
        rs = ps.executeQuery();
        assertFalse(rs.next(), "Expected row not deleted");
    }

    @Test
    public void testGetAverageMarkByExam() {
        List<ExaminationList> records = recordListImplUnderTest.getAll();
        int examId = 10; // test for only one exam,
        double sum = 0;
        double count = 0;
        double averageMark = 0;
        for (ExaminationList el : records) {
            if (el.getExamId() == examId) {
                sum += el.getGrade();
                count++;
            }
        }
        try {
            averageMark = sum / count;
        } catch (ArithmeticException e) {
            logger.warn("Division by 0! in average mark test");
        }
        double result = recordListImplUnderTest.getAverageMarkByExam(examId);
        assertEquals(Double.compare(averageMark, result), 0, "Wrong average mark");
    }

    private int getRandomStudentId() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        int randomId = -1;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            st = conn.createStatement();
            rs = st.executeQuery(sqlRandomStudentId);
            if (rs.next()) {
                randomId = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.warn("Error when get Random Student ID");
        } finally {
            DBConnection.close(rs, st, conn);
        }
        return randomId;
    }
}
