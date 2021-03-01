package dao.implementations;

import dao.model.ExaminationList;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.*;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ExaminationListImplTest {

    private ExaminationListImpl recordListImplUnderTest;
    private static final Logger logger = LoggerFactory.getLogger(ExaminationListImplTest.class);
    private Statement st;
    private Connection conn;
    private List<ExaminationList> recordList;
    private Random random = new Random();
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
        int examId = 10;
        final ExaminationList record = new ExaminationList(studentId , examId , random.nextInt(100));
        final int result = recordListImplUnderTest.create(record);
        record.setRecordId(result);
        PreparedStatement ps = conn.prepareStatement(sqlCheckCreate);
        ps.setInt(1, result);
        ResultSet rs = ps.executeQuery();
        boolean isCreated = false;
        if (rs.next()) {
            isCreated = true;
        }
        assertTrue(isCreated);
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
        assertTrue(isCorrect);
    }

    @Test
    // testing time about 1,5 minutes. TODO check random records
    public void testGetById() {
        SoftAssert softAssert = new SoftAssert();
        recordList = recordListImplUnderTest.getAll();
        for (ExaminationList el: recordList) {
            int id = el.getRecordId();
            ExaminationList result = recordListImplUnderTest.getById(id);
            softAssert.assertEquals(result, el, "Object from ObjectsList equals Object from DB ");
        }
        softAssert.assertAll();
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
        assertTrue(isUpdated, "In DB found Updated object ");
    }

    @Test
    public void testDelete() throws SQLException {
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
        boolean isDeleted = false;
        if (!rs.next()) {
            isDeleted = true;
        }
        assertTrue(isDeleted, "Expected row deleted");
    }

    private int getRandomStudentId() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        int randomId = -1;
        try {
            conn = DBConnection.getConnection();
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
