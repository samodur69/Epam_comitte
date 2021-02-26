package dao.implementations;

import dao.model.Exam;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ExamImplTest extends BaseTest{

    private ExamImpl examImplUnderTest;
    private static final Logger logger = LoggerFactory.getLogger(ExamImplTest.class);
    private Statement st;
    private Connection conn;
    private List<Exam> examsList;



    @BeforeClass
    public void startSetUp() throws SQLException {
        examImplUnderTest = new ExamImpl();
        conn = DBConnection.getConnection();
        st = conn.createStatement();
    }

    @Test
    public void testCreate() {
        final Exam exam = new Exam("TestExam");
        final int result = examImplUnderTest.create(exam);
        assertTrue(result > 0);
        // TODO check created exam
    }

    @Test
    public void testGetAll() throws SQLException {
        int expectedResult = 0;
        ResultSet rs = st.executeQuery("SELECT COUNT(exam_id) FROM EXAMS_LIST");
        if (rs.next()) {
            expectedResult = rs.getInt(1);
        }
        final List<Exam> examsList = examImplUnderTest.getAll();
        assertEquals(expectedResult , examsList.size());
    }

    @Test(dependsOnMethods = {"testGetAll"})
    public void testGetById() {
        SoftAssert softAssert = new SoftAssert();
        List<Exam> examList = examImplUnderTest.getAll();
        for (Exam el: examList) {
            int id = el.getExamId();
            Exam result = examImplUnderTest.getById(id);
            softAssert.assertEquals(el, result);
        }
        softAssert.assertAll();
    }

    @Test (dependsOnMethods = {"testCreate"})
    public void testUpdate() {
        List<Exam> examList = examImplUnderTest.getAll();
        Exam examUpdate = new Exam();
        if (examList.size() > 1) {
            examUpdate = examList.get(0);
        }
        examUpdate.setExamName("UpdateTest");
        final int result = examImplUnderTest.update(examUpdate);
        examList = examImplUnderTest.getAll();
        boolean updated = false;
        for (Exam el: examList) {
            if (el.getExamName().equals(examUpdate.getExamName())) {
                updated = true;
            }
        }
        assertTrue(updated);
    }

    @Test
    public void testDelete() throws SQLException {
        ResultSet rs = st.executeQuery("SELECT COUNT(EXAM_ID), MAX(EXAM_ID) FROM EXAMS_LIST");
        int rowsBefore = 0;
        int idToDelete = 0;
        if (rs.next()) {
            rowsBefore = rs.getInt("COUNT(EXAM_ID)");
            idToDelete = rs.getInt("MAX(EXAM_ID)");
        }
        final int result = examImplUnderTest.delete(idToDelete);
        rs = null;
        int rowsAfter = 0;
        rs = st.executeQuery("SELECT COUNT(EXAM_ID) FROM EXAMS_LIST");
        if (rs.next()) {
            rowsAfter = rs.getInt(1);
        }
        assertEquals(result , 1);
        assertTrue(rowsAfter + 1 == rowsBefore);
    }

    @Test
    public void testGetNameById() {
        examsList = examImplUnderTest.getAll();
        int idToGet = 0;
        String expected = "";
        if (examsList.size() > 0) {
            idToGet = examsList.get(0).getExamId();
            expected = examsList.get(0).getExamName();
        }
        final String result = examImplUnderTest.getNameById(idToGet);
        assertEquals(result , expected);
    }
}
