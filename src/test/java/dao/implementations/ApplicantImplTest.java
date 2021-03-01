package dao.implementations;

import dao.model.Applicant;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ApplicantImplTest extends BaseTest{

    private static final Logger logger = LoggerFactory.getLogger(ApplicantImplTest.class);
    private Applicant applicant;
    private Applicant aplEmailTest;
    private ApplicantImpl applServiceUnderTest;
    List<Applicant> apList;
    private final String email = "test@testng.com";
    private final String emailGet = "asdfg@poiu.ru";
    private Statement st;
    private Connection conn;
    private final SecureRandom r = new SecureRandom();
    private final String[] names = {"Alex", "Michael", "Boris"};
    private final String[] lastNames = {"First", "Ivanov", "Petrov", "Sidorov"};

    @BeforeClass
    public void setUp() throws SQLException {
        applServiceUnderTest = new ApplicantImpl();
        // applicant obj used for create/delete tests
        applicant = new Applicant("testName", "testName", email, 100, 10);
        // applicant obj used for email tests
        aplEmailTest = new Applicant("Jhon", "Dou", emailGet,55, 20);
        conn = DBConnection.getConnection();
        System.out.println("tets test");
        st = conn != null ? conn.createStatement() : null;
        apList = new ArrayList<>();
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
        String firstName = "";
        String lastName = "";
        String email = "";
        int schoolAverage = r.nextInt(101);



        final int id = applServiceUnderTest.create(applicant);
        applicant.setId(id);
        // id in DB start from 10000
        assertTrue(id > 10000, "Wrong id ");
        //  TODO assert to check row
    }

    @Test
    public void testUpdate() {
        apList = applServiceUnderTest.getAll();
        Applicant aplUpdate = new Applicant();
        if (apList.size() > 0) {
            aplUpdate = apList.get(0);
        }
        aplUpdate.setFirstName("Test");
        aplUpdate.setLastName("Testovich");
        aplUpdate.setEmail("test@testng.ru");
        final int result = applServiceUnderTest.update(aplUpdate);
        apList = applServiceUnderTest.getAll();
        boolean isUpdated = false;
        for (Applicant el: apList) {
            if (el.equals(aplUpdate)) {
                isUpdated = true;
                break;
            }
        }
        assertEquals(result, 1, "Rows updated ");
        assertTrue(isUpdated, "In DB found Updated object ");
    }

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
}
