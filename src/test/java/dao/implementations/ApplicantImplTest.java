package dao.implementations;

import dao.model.Applicant;
import dao.model.Faculty;
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

public class ApplicantImplTest extends BaseTest{

    private static final Logger logger = LoggerFactory.getLogger(ApplicantImplTest.class);
    private ApplicantImpl applServiceUnderTest;
    private Statement st;
    private Connection conn;
    private final SecureRandom r = new SecureRandom();
    private final String[] names = {"Alex", "Michael", "Boris", "John"};
    private final String[] lastNames = {"First", "Ivanov", "Petrov", "Sidorov", "Dou"};
    List<Applicant> apList;


    @BeforeClass
    public void setUp() throws SQLException {
        applServiceUnderTest = new ApplicantImpl();
        conn = DBConnection.getConnection();
        if (conn != null) {
            st = conn.createStatement();
        }
        logger.info("Start testing Applicant DAO class");
    }

    /**
     * create new object. get from db its DB sequence ID. After check it in DB by it`s id
     * @throws SQLException
     */
    @Test(description = "create new Applicant in DB")
    public void testCreate() throws SQLException {
        Applicant applicant = applicantGenerator();
        final int id = applServiceUnderTest.create(applicant);
        applicant.setId(id);
        String sql = "SELECT * FROM APPLICANTS WHERE ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        assertTrue(rs.next(), "Applicant not created");
    }

    /**
     * get list of objects from DB -> Shuffle list to randomize choice ->
     * -> get first object -> update it -> get all objects -> check our updated object.
     *  Another way -  find object by ID
     */
    @Test
    public void testUpdate() {
        Applicant aplUpdate = new Applicant();
        apList = applServiceUnderTest.getAll();
        Collections.shuffle(apList);
        if (apList.size() > 0) {
            aplUpdate = apList.get(0);
        }
        aplUpdate.setFirstName("Test");
        aplUpdate.setLastName("Testovich");
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

    /**
     * get last id from table -> delete -> try to select deleted row
     * @throws SQLException
     */
    @Test(description = "delete row from DB")
    public void testDelete() throws SQLException {
        // delete last row
        ResultSet rs = st.executeQuery("SELECT MAX(ID) FROM APPLICANTS");
        int idToDelete = 0;
        if (rs.next()) {
            idToDelete = rs.getInt(1);
        }
        applServiceUnderTest.delete(idToDelete);
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM APPLICANTS WHERE ID = ?");
        ps.setInt(1, idToDelete);
        assertFalse(rs.next(), "Expected row did`t deleted");
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
        apList = applServiceUnderTest.getAll();
        Collections.shuffle(apList);
        int id = apList.get(0).getId();
        Applicant applicant = applServiceUnderTest.getById(id);
        assertEquals(applicant, apList.get(0), "Applicant from DB not equals expected");
    }


    @Test
    public void testGetByEmail() {
        Applicant expected = applicantGenerator();
        int id = applServiceUnderTest.create(expected);
        expected.setId(id);
        final Applicant result = applServiceUnderTest.getByEmail(expected.getEmail());
        assertEquals(result, expected , "Diff when get by Email: ");
    }

    @Test
    public void testGetIdByEmail() {
        Applicant expected = applicantGenerator();
        int expectedId = applServiceUnderTest.create(expected);
        String emailToCheck = expected.getEmail();
        final int result = applServiceUnderTest.getIdByEmail(emailToCheck);
        assertEquals(result, expectedId, "Wrong Id returned");
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

    @Test
    public void testCheckEmailUnique() throws SQLException {
        SoftAssert softAssert = new SoftAssert();
        String correctEmail = "";
        ResultSet rs = st.executeQuery("SELECT " +
                "ST_EMAIL " +
                "FROM " +
                "(SELECT ST_EMAIL FROM APPLICANTS ORDER BY dbms_random.value) " +
                "WHERE ROWNUM = 1");
        if (rs.next()) {
            correctEmail = rs.getString("ST_EMAIL");
        }
        String randomEmail = "notexist@email.com";
        final boolean resultNotUnique = applServiceUnderTest.checkEmailUnique(correctEmail);
        final boolean resultUnique = applServiceUnderTest.checkEmailUnique(randomEmail);
        softAssert.assertTrue(resultUnique, "not-existing email must be unique");
        softAssert.assertFalse(resultNotUnique, "Existing address cannot be unique");
        softAssert.assertAll();
    }

    /**
     *  func to create random Object for create/update Test
     * @return randomized Applicant Object
     */
    public Applicant applicantGenerator() {
        int index = r.nextInt(names.length);
        String firstName = names[index];
        index = r.nextInt(lastNames.length);
        String lastName = lastNames[index];
        int schoolAverage = r.nextInt(101);

        FacultyImpl facultyS = new FacultyImpl();
        List<Faculty> facultyList = facultyS.getAll();
        Collections.shuffle(facultyList);
        int facultyId = facultyList.get(0).getFacultyId();

        String email = firstName +
                r.nextInt(10) +
                "@testng.com";

        return new Applicant(firstName, lastName, email , schoolAverage, facultyId);
    }
}
