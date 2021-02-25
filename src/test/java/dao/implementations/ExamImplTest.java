package dao.implementations;

import dao.model.Exam;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ExamImplTest {

    private ExamImpl examImplUnderTest;

    @BeforeMethod
    public void setUp() {
        examImplUnderTest = new ExamImpl();
    }

    @Test
    public void testGetAll() {
        // Setup
        final Exam exam = new Exam();
        exam.setExamId(0);
        exam.setExamName("examName");
        final List<Exam> expectedResult = List.of(exam);

        // Run the test
        final List<Exam> result = examImplUnderTest.getAll();

        // Verify the results
        assertEquals(expectedResult , result);
    }

    @Test
    public void testGetById() {
        // Setup
        final Exam expectedResult = new Exam();
        expectedResult.setExamId(0);
        expectedResult.setExamName("examName");

        // Run the test
        final Exam result = examImplUnderTest.getById(0);

        // Verify the results
        assertEquals(expectedResult , result);
    }

    @Test
    public void testUpdate() {
        // Setup
        final Exam exam = new Exam();
        exam.setExamId(0);
        exam.setExamName("examName");

        // Run the test
        final int result = examImplUnderTest.update(exam);

        // Verify the results
        assertEquals(0 , result);
    }

    @Test
    public void testDelete() {
        // Setup

        // Run the test
        final int result = examImplUnderTest.delete(0);

        // Verify the results
        assertEquals(0 , result);
    }

    @Test
    public void testCreate() {
        // Setup
        final Exam exam = new Exam();
        exam.setExamId(0);
        exam.setExamName("examName");

        // Run the test
        final int result = examImplUnderTest.create(exam);

        // Verify the results
        assertEquals(0 , result);
    }

    @Test
    public void testGetIdListByFacultyId() {
        // Setup

        // Run the test
        final List<Integer> result = examImplUnderTest.getIdListByFacultyId(0);

        // Verify the results
        assertEquals(List.of(0) , result);
    }

    @Test
    public void testGetNameById() {
        // Setup

        // Run the test
        final String result = examImplUnderTest.getNameById(0);

        // Verify the results
        assertEquals("result" , result);
    }
}
