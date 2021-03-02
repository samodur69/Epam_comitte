package service;

import dao.model.Exam;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ExaminationRecordsServiceTest {

    private ExaminationRecordsService examinationRecordsServiceUnderTest;

    @BeforeMethod
    public void setUp() {
        examinationRecordsServiceUnderTest = new ExaminationRecordsService();
    }

    @Test
    public void testCreateRandomExamRecords() {
        // Setup

        // Run the test
        examinationRecordsServiceUnderTest.createRandomExamRecords(0);

        // Verify the results
    }

    @Test
    public void testCreateRandomExamRecordsForAll() {
        // Setup

        // Run the test
        examinationRecordsServiceUnderTest.createRandomExamRecordsForAll();

        // Verify the results
    }



    @Test
    public void testGetExamsByFaculty() {
        // Setup
        final List<Exam> expectedResult = List.of(new Exam("examName"));

        // Run the test
        final List<Exam> result = examinationRecordsServiceUnderTest.getExamsByFaculty(0);

        // Verify the results
        assertEquals(expectedResult , result);
    }

    @Test
    public void testShowTopTwentyRecords() {
        // Setup

        // Run the test
        examinationRecordsServiceUnderTest.showTopTwentyRecords();

        // Verify the results
    }

    @Test
    public void testShowAverageMarkByExams() {
        // Setup

        // Run the test
        ExaminationRecordsService.showAverageMarkByExams();

        // Verify the results
    }
}
