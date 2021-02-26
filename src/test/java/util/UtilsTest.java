package util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void testGeneratePin() {
        assertEquals("result" , Utils.generatePin());
    }

    @Test
    public void testCreateExamRecords() {
        // Setup

        // Run the test
        Utils.createExamRecords();

        // Verify the results
    }
}
