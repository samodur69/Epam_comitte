package dao.implementations;

import dao.model.Faculty;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        st = conn.createStatement();
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
    public void testDelete() {
        // Setup

        // Run the test
        final int result = facultyImplUnderTest.delete(0);

        // Verify the results
        assertEquals(0 , result);
    }

    @Test
    public void testGetById() {
        // Setup

        // Run the test
        final Faculty result = facultyImplUnderTest.getById(0);

        // Verify the results
    }

    @Test
    public void testGetNameById() {
        // Setup

        // Run the test
        final String result = facultyImplUnderTest.getNameById(0);

        // Verify the results
        assertEquals("result" , result);
    }
}
