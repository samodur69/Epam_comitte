package dao.implementations;

import dao.model.Exam;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

public class ExamImplTest extends BaseTest{

    private ExamImpl examImplUnderTest;
    private static final Logger logger = LoggerFactory.getLogger(ExamImplTest.class);
    private Statement st;
    private Connection conn;
    private List<Exam> examList;
    private final SecureRandom r = new SecureRandom();



    @BeforeClass
    public void startSetUp() throws SQLException {
        examImplUnderTest = new ExamImpl();
        conn = DBConnection.getConnection();
        if (conn != null) {
            st = conn.createStatement();
        }
        logger.info("Start testing Exam DAO class");
    }

    @Test
    public void testCreate() throws SQLException {
        String examName = "Test Exam" + r.nextInt(10);
        final Exam exam = new Exam(examName);
        final int id = examImplUnderTest.create(exam);
        exam.setExamId(id);
        String sqlCheck = "SELECT * FROM EXAMS_LIST WHERE EXAM_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sqlCheck);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        assertTrue(rs.next(), "Exam not created");
    }

    @Test
    public void testGetAll() throws SQLException {
        int expectedResult = -1;
        ResultSet rs = st.executeQuery("SELECT COUNT(exam_id) FROM EXAMS_LIST");
        if (rs.next()) {
            expectedResult = rs.getInt(1);
        }
        examList = examImplUnderTest.getAll();
        assertEquals(expectedResult , examList.size(), "Wrong number of records from DB");
    }

    @Test
    public void testGetById() {
        SoftAssert softAssert = new SoftAssert();
        examList = examImplUnderTest.getAll();
        for (Exam el: examList) {
            int id = el.getExamId();
            Exam result = examImplUnderTest.getById(id);
            softAssert.assertEquals(el, result, "Exam objects not equals");
        }
        softAssert.assertAll();
    }

    @Test
    public void testUpdate() {
        examList = examImplUnderTest.getAll();
        Collections.shuffle(examList);
        Exam examUpdate = new Exam();
        if (examList.size() > 0) {
            examUpdate = examList.get(0);
        }
        examUpdate.setExamName("UpdateTest");
        final int result = examImplUnderTest.update(examUpdate);
        examList = examImplUnderTest.getAll();
        boolean isUpdated = false;
        for (Exam el: examList) {
            if (el.equals(examUpdate)) {
                isUpdated = true;
                break;
            }
        }
        assertEquals(result, 1, "Rows updated");
        assertTrue(isUpdated, "In DB not found updated object");
    }

    @Test
    public void testDelete() throws SQLException {
        // delete last row from DB
        ResultSet rs = st.executeQuery("SELECT MAX(EXAM_ID) FROM EXAMS_LIST");
        int idToDelete = 0;
        if (rs.next()) {
            idToDelete = rs.getInt("MAX(EXAM_ID)");
        }
        examImplUnderTest.delete(idToDelete);
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM EXAMS_LIST WHERE EXAM_ID = ?");
        ps.setInt(1, idToDelete);
        rs = ps.executeQuery();
        assertFalse(rs.next(), "Expected row was not deleted");
    }

    @Test
    public void testGetNameById() {
        examList = examImplUnderTest.getAll();
        int idToGet = 0;
        String expected = "";
        if (examList.size() > 0) {
            idToGet = examList.get(0).getExamId();
            expected = examList.get(0).getExamName();
        }
        final String result = examImplUnderTest.getNameById(idToGet);
        assertEquals(result , expected, "Wrong exam name");
    }
}
