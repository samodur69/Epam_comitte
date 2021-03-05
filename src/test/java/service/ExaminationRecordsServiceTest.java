package service;

import dao.implementations.ExamImpl;
import dao.model.Exam;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ExaminationRecordsServiceTest {

    private Connection conn;
    private Statement st;
    private ExaminationRecordsService examinationRecordsServiceUnderTest;
    private ExamImpl examImpl;
    private final Logger logger = LoggerFactory.getLogger(ExaminationRecordsServiceTest.class);

    @BeforeClass
    public void setUp() throws SQLException {
        examinationRecordsServiceUnderTest = new ExaminationRecordsService();
        conn = DBConnection.getConnection();
        if (conn != null) {
            st = conn.createStatement();
        }
        logger.info("Start testing Applicant DAO class");
    }

    /**
     * TESTING ONLY ON EMPTY TABLE "EXAMINATION_RECORDS"
     * first count students.
     * each student takes three exams,
     * so we can calculate the expected number of exam records.
     * @throws SQLException
     */
    @Test
    public void testCreateRandomExamRecordsForAll() throws SQLException {
        int countStudent = 0;
        ResultSet rs = st.executeQuery("SELECT COUNT(1) FROM APPLICANTS");
        if (rs.next()) {
            countStudent = rs.getInt(1);
        }
        examinationRecordsServiceUnderTest.createRandomExamRecordsForAll();
        int result = 0;
        rs = st.executeQuery("SELECT COUNT(1) FROM EXAMINATION_RECORDS");
        if (rs.next()) {
            result = rs.getInt(1);
        }
        // for each student - 3 exam
        assertEquals(result, countStudent * 3, "The number of entries is not as expected");
    }

    @Test
    public void testGetExamsByFaculty() throws SQLException {
        // Setup
        final List<Exam> expectedResult = new ArrayList<>();
        examImpl = new ExamImpl();
        int facultyId = 10;
        PreparedStatement ps = conn.prepareStatement("SELECT EXAM_ID FROM EXAMS_FACULTY WHERE FACULTY_ID = ?");
        ps.setInt(1, facultyId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Exam exam = examImpl.getById(rs.getInt(1));
            expectedResult.add(exam);
        }
        // Run the test
        final List<Exam> result = examinationRecordsServiceUnderTest.getExamsByFaculty(facultyId);

        // Verify the results
        assertEquals(result, expectedResult);
    }

//    @Test
//    public void testShowTopTwentyRecords() {
//        // Setup
//
//        // Run the test
//        examinationRecordsServiceUnderTest.showTopTwentyRecords();
//
//        // Verify the results
//    }
//
//    @Test
//    public void testShowAverageMarkByExams() {
//        // Setup
//
//        // Run the test
//        ExaminationRecordsService.showAverageMarkByExams();
//
//        // Verify the results
//    }
}
