package service;

import dao.implementations.ApplicantImpl;
import dao.implementations.BaseTest;
import dao.implementations.ExaminationListImpl;
import dao.model.Applicant;
import dao.model.ExaminationList;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ApplicantServiceTest extends BaseTest {

    private ApplicantService applicantServiceUnderTest;
    private ApplicantImpl aplImplService;
    private ExaminationListImpl recordService;
    private List<ExaminationList> records;
    private Connection conn;
    private Statement st;
    private final Logger logger = LoggerFactory.getLogger(ApplicantServiceTest.class);

    @BeforeClass
    public void setUp() throws SQLException {
        applicantServiceUnderTest = new ApplicantService();
        aplImplService = new ApplicantImpl();
        conn = DBConnection.getConnection();
        if (conn != null) {
            st = conn.createStatement();
        }
        logger.info("Start testing Applicant DAO class");
    }

//    @Test
//    public void testEnrollAllApplicants() {
//        // Setup
//
//        // Run the test
//        final int result = applicantServiceUnderTest.enrollAllApplicants();
//
//        // Verify the results
//        assertEquals(0 , result);
//    }
//

    /**
     * A random student is selected and his grades are summed with the results of his exams
     * @throws SQLException ...
     */
    @Test
    public void testGetTotalMark() throws SQLException {
        // take random student and his school average mark
        ResultSet rs = st.executeQuery("SELECT MAX(STUDENT_ID) FROM EXAMINATION_RECORDS");
        int studentId = 0;
        if (rs.next()) {
            studentId = rs.getInt(1);
        }
        Applicant applicant = aplImplService.getById(studentId);
        // get students exam results
        recordService = new ExaminationListImpl();
        records = recordService.getRecordsByStudent(studentId);
        System.out.println(records.size());
        int totalMarkExpected =  applicant.getSchoolAverage();
        for (ExaminationList el: records) {
            totalMarkExpected += el.getGrade();
        }
        // Run the test
        final int result = applicantServiceUnderTest.getTotalMark(studentId);
        // Verify the results
        assertEquals(result , totalMarkExpected);
    }
//
//    @Test
//    public void testShowTopTenStudents() {
//        // Setup
//
//        // Run the test
//        applicantServiceUnderTest.showTopTenStudents();
//
//        // Verify the results
//    }
}
