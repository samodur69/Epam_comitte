package dao.implementations;

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
import java.util.Random;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ExaminationListImplTest {

    private ExaminationListImpl recordListImplUnderTest;
    private static final Logger logger = LoggerFactory.getLogger(ExaminationListImplTest.class);
    private Statement st;
    private Connection conn;
    private List<ExaminationList> recordList;
    private Random random;

    @BeforeClass
    public void setUp() throws SQLException {
        recordListImplUnderTest = new ExaminationListImpl();
        conn = DBConnection.getConnection();
        if (conn != null) {
            st = conn.createStatement();
        }
        logger.info("Start Testing Examination List Implementation");
    }

    @Test
    public void testCreate() {
        int studentId = 0;
        int examId = 0;
//        Select ID
//        from (select ID
//                from APPLICANTS
//                order by dbms_random.value)
//        where
//                rownum = 1;
        // TODO get studentId and examId
        final ExaminationList record = new ExaminationList(studentId , examId , random.nextInt(100));
        final int result = recordListImplUnderTest.create(record);
        assertTrue(result > 0);
    }

    @Test
    public void testGetAll() throws SQLException {
        int expectedCount = -1;
        ResultSet rs = st.executeQuery("SELECT COUNT(EXAM_RECORD_ID) FROM EXAMINATION_RECORDS");
        if (rs.next()) {
            expectedCount = rs.getInt(1);
        }
        recordList = recordListImplUnderTest.getAll();
        assertEquals(recordList.size(), expectedCount);
    }

    @Test
    public void testGetByFaculty() {
        // Setup

        // Run the test
        final List<ExaminationList> result = recordListImplUnderTest.getByFaculty(0);

        // Verify the results
    }

    @Test
    public void testGetById() {
        // Setup

        // Run the test
        final ExaminationList result = recordListImplUnderTest.getById(0);

        // Verify the results
    }

    @Test
    public void testUpdate() {
        // Setup
        final ExaminationList record = new ExaminationList(0 , 0 , 0);

        // Run the test
        final int result = recordListImplUnderTest.update(record);

        // Verify the results
        assertEquals(0 , result);
    }

    @Test
    public void testDelete() {
        // Setup

        // Run the test
        final int result = recordListImplUnderTest.delete(0);

        // Verify the results
        assertEquals(0 , result);
    }
}
