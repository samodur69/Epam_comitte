package dao.implementations;

import dao.model.Applicant;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.testng.Assert.*;

public class ApplicantImplTest {

    private static final Logger logger = LoggerFactory.getLogger(ApplicantImplTest.class);
    private Applicant applicant;
    private Applicant aplEmailTest;
    private ApplicantImpl applServiceUnderTest;
    private final String email = "test@testng.com";
    private final String emailGet = "asdfg@poiu.ru";
    private Statement st;
    private Connection conn;

    @BeforeClass
    public void setUp() throws SQLException {
        applServiceUnderTest = new ApplicantImpl();
        // applicant obj used for create/delete tests
        applicant = new Applicant("testName", "testName", email, 100, 10);
        // applicant obj used for email tests
        aplEmailTest = new Applicant("Jhon", "Dou", emailGet,55, 20);
        conn = DBConnection.getConnection();
        st = conn.createStatement();
    }

    @AfterClass
    public void close() {
        DBConnection.close(conn);
        if (conn == null) {
            logger.info("Connection closed");
        }

    }

    @Test(description = "create new Applicant in DB")
    public void testCreate() {
        final int id = applServiceUnderTest.create(applicant);
        applicant.setId(id);
        // id in DB start from 10000
        assertTrue(id > 10000, "Wrong id ");
        //  TODO assert to check row
    }

//    @Test
//    //  TODO
//    public void testUpdate() {
//        Applicant applicantUpdate = applicant;
//        applicantUpdate.setFirstName("");
//
//        // Run the test
//        final int result = applServiceUnderTest.update(applicant);
//
//        // Verify the results
//        assertEquals(0 , result);
//    }

    @Test(description = "delete row from DB", dependsOnMethods = {"testCreate"})
    public void testDelete() {
        if (applicant.getId() == 0) {
            applicant.setId(applServiceUnderTest.getIdByEmail(email));
        }
        final int result = applServiceUnderTest.delete(applicant.getId());
        assertEquals(result , 1, "Rows deleted: ");
    }


    @Test
    public void testGetAll() throws SQLException{
        int expected = -1;
        ResultSet rs = st.executeQuery("SELECT COUNT(id) FROM APPLICANTS");
        if (rs.next()) {
            expected = rs.getInt(1);
        }
        final List<Applicant> result = applServiceUnderTest.getAll();
        assertEquals(result.size(), expected, "Get rows from table: ");
    }


    @Test
    public void testGetById() {
        Applicant applicant1 = new Applicant("Jhon", "Dou", "qw@qe.com",55, 30);
        int expectedId = applServiceUnderTest.create(applicant1);
        applicant1.setId(expectedId);
        final Applicant result = applServiceUnderTest.getById(expectedId);
        assertEquals(result, applicant1);
    }



    @Test
    public void testGetByEmail() {
        int id = applServiceUnderTest.create(aplEmailTest);
        aplEmailTest.setId(id);
        final Applicant result = applServiceUnderTest.getByEmail(emailGet);
        assertEquals(result, aplEmailTest , "Diff when get by Email: ");
    }

    @Test(dependsOnMethods = {"testGetByEmail"})
    public void testGetIdByEmail() {
        final int result = applServiceUnderTest.getIdByEmail(emailGet);
        assertEquals(result, aplEmailTest.getId(), "Wrong Id returned");
    }

    @Test
    public void testGetByEnrolled() throws SQLException {
        ResultSet rs = st.executeQuery("SELECT COUNT(id) FROM APPLICANTS WHERE ENROLLED = 'Y'");
        int count = -1;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        final List<Applicant> result = applServiceUnderTest.getByEnrolled();
        assertEquals(result.size(), count, "Wrong count enrolled students");
    }

    @Test(dependsOnMethods = {"testGetByEmail"})
    public void testCheckEmailUnique() {
        // TODO sample emails list
        final boolean result = applServiceUnderTest.checkEmailUnique(emailGet);
        assertFalse(result);
    }




    // зависимые тесты продумать.
//    @Test
//    public void testGetTotalMark() {
//        // Setup
//
//        // Run the test
//        final int result = applServiceUnderTest.getTotalMark(0);
//
//        // Verify the results
//        assertEquals(0 , result);
//    }
//
//    @Test
//    public void testEnrollAllApplicants() {
//        // Setup
//
//        // Run the test
//        final int result = applServiceUnderTest.enrollAllApplicants();
//
//        // Verify the results
//        assertEquals(0 , result);
//    }


}
