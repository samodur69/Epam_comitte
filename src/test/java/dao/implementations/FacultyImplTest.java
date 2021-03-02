package dao.implementations;

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

public class FacultyImplTest {

    private FacultyImpl facultyImplUnderTest;
    private static final Logger logger = LoggerFactory.getLogger(FacultyImplTest.class);
    private Statement st;
    private Connection conn;
    private List<Faculty> facultyList;
    private final SecureRandom r = new SecureRandom();
    private final String sqlCheckId = "SELECT * FROM FACULTY_LIST WHERE FACULTY_ID = ?";

    @BeforeClass
    public void setUp() throws SQLException {
        facultyImplUnderTest = new FacultyImpl();
        conn = DBConnection.getConnection();
        if (conn != null) {
            st = conn.createStatement();
        }
        logger.info("Start testing Faculty DAO class");
    }

    @Test
    public void testCreate() throws SQLException {
        String facultyName = "Faculty" + r.nextInt(10);
        // min grade and capacity are static for easy further testing
        Faculty facultyExpected = new Faculty(facultyName , 10 , 250);
        final int expectedId = facultyImplUnderTest.create(facultyExpected);
        facultyExpected.setFacultyId(expectedId);
        PreparedStatement ps = conn.prepareStatement(sqlCheckId);
        ps.setInt(1, expectedId);
        ResultSet rs = ps.executeQuery();
        assertTrue(rs.next(), "Faculty not created");
    }

    @Test
    public void testGetAll() throws SQLException {
        int expectedCount = -1;
        ResultSet rs = st.executeQuery("SELECT COUNT(FACULTY_ID) FROM FACULTY_LIST");
        if (rs.next()) {
            expectedCount = rs.getInt(1);
        }
        facultyList = facultyImplUnderTest.getAll();
        assertEquals(facultyList.size(), expectedCount, "Wrong number of records from DB");
    }

    @Test
    public void testUpdate() {
        facultyList = facultyImplUnderTest.getAll();
        Faculty facultyUpdate = new Faculty();
        if (facultyList.size() > 0) {
            facultyUpdate = facultyList.get(0);
        }
        facultyUpdate.setFacultyName("UpdatedFacultyName");
        facultyUpdate.setMinGrade(333);
        final int result = facultyImplUnderTest.update(facultyUpdate);
        facultyList = facultyImplUnderTest.getAll();
        boolean isUpdated = false;
        for (Faculty el: facultyList) {
            if (el.equals(facultyUpdate)) {
                isUpdated = true;
                break;
            }
        }
        assertEquals(result, 1, "Rows updated ");
        assertTrue(isUpdated, "In DB not found Updated object ");
    }

    @Test
    public void testDelete() throws SQLException {
        // delete last row only
        ResultSet rs = st.executeQuery("SELECT MAX(FACULTY_ID) FROM FACULTY_LIST");
        int idToDelete = 0;
        if (rs.next()) {
            idToDelete = rs.getInt("MAX(FACULTY_ID)");
        }
        facultyImplUnderTest.delete(idToDelete);
        PreparedStatement ps = conn.prepareStatement(sqlCheckId);
        ps.setInt(1, idToDelete);
        assertFalse(rs.next(), "Expected row not delete");
    }

    @Test
    public void testGetById() {
        SoftAssert softAssert = new SoftAssert();
        facultyList = facultyImplUnderTest.getAll();
        for (Faculty el: facultyList) {
            int id = el.getFacultyId();
            Faculty result = facultyImplUnderTest.getById(id);
            softAssert.assertEquals(result, el, "Object from ObjectsList equals Object from DB ");
        }
        softAssert.assertAll();
    }

    @Test
    public void testGetNameById() {
        facultyList = facultyImplUnderTest.getAll();
        Collections.shuffle(facultyList);
        int idToGet = 0;
        String expected = "";
        if (facultyList.size() > 0) {
            idToGet = facultyList.get(0).getFacultyId();
            expected = facultyList.get(0).getFacultyName();
        }
        final String result = facultyImplUnderTest.getNameById(idToGet);
        assertEquals(result , expected, "Wrong Faculty name");
    }
}
