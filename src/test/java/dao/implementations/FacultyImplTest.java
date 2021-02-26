package dao.implementations;

import dao.model.Faculty;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FacultyImplTest {

    private FacultyImpl facultyImplUnderTest;
    private static final Logger logger = LoggerFactory.getLogger(FacultyImplTest.class);
    private Statement st;
    private Connection conn;
    private List<Faculty> facultyList;

    @BeforeClass
    public void setUp() throws SQLException {
        facultyImplUnderTest = new FacultyImpl();
        conn = DBConnection.getConnection();
        if (conn != null) {
            st = conn.createStatement();
        }
    }

    @BeforeMethod
    public void setUpBeforeMethod() {
    }

    @Test
    public void testCreate() {
        final Faculty faculty = new Faculty("TestFaculty" , 10 , 250);
        final int result = facultyImplUnderTest.create(faculty);
        assertTrue(result > 0);
    }

    @Test
    public void testGetAll() throws SQLException {
        int expectedCount = -1;
        ResultSet rs = st.executeQuery("SELECT COUNT(FACULTY_ID) FROM FACULTY_LIST");
        if (rs.next()) {
            expectedCount = rs.getInt(1);
        }
        facultyList = facultyImplUnderTest.getAll();
        assertEquals(facultyList.size(), expectedCount);
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
        assertTrue(isUpdated, "In DB found Updated object ");
    }

    @Test
    public void testDelete() throws SQLException {
        ResultSet rs = st.executeQuery("SELECT COUNT(FACULTY_ID), MAX(FACULTY_ID) FROM FACULTY_LIST");
        int rowsBefore = 0;
        int idToDelete = 0;
        if (rs.next()) {
            rowsBefore = rs.getInt("COUNT(FACULTY_ID)");
            idToDelete = rs.getInt("MAX(FACULTY_ID)");
        }
        final int result = facultyImplUnderTest.delete(idToDelete);
        int rowsAfter = 0;
        rs = st.executeQuery("SELECT COUNT(FACULTY_ID) FROM FACULTY_LIST");
        if (rs.next()) {
            rowsAfter = rs.getInt(1);
        }
        assertEquals(result , 1);
        assertEquals(rowsAfter + 1, rowsBefore);
    }

    @Test
    public void testGetById() {
        SoftAssert softAssert = new SoftAssert();
        facultyList = facultyImplUnderTest.getAll();
        for (Faculty el: facultyList) {
            int id = el.getFacultyId();
            Faculty result = facultyImplUnderTest.getById(id);
            softAssert.assertEquals(el, result, "Object from ObjectsList equals Object from DB ");
        }
        softAssert.assertAll();
    }

    @Test
    public void testGetNameById() {
        facultyList = facultyImplUnderTest.getAll();
        int idToGet = 0;
        String expected = "";
        if (facultyList.size() > 0) {
            idToGet = facultyList.get(0).getFacultyId();
            expected = facultyList.get(0).getFacultyName();
        }
        final String result = facultyImplUnderTest.getNameById(idToGet);
        assertEquals(result , expected);
    }

    @AfterClass
    public void finish() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.warn("Error when close testNG connection");
                e.printStackTrace();
            } finally {
                conn = null;
            }

        }
    }
}
