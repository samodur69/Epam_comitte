package ui;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;

public class NewApplicantTest {

    private NewApplicant newApplicantUnderTest;

    @BeforeMethod
    public void setUp() {
        newApplicantUnderTest = new NewApplicant();
    }

    @Test
    public void testStart() throws Exception {
        // Setup

        // Run the test
        newApplicantUnderTest.start();

        // Verify the results
    }

    @Test
    public void testStart_ThrowsInterruptedException() {
        // Setup

        // Run the test
        assertThrows(InterruptedException.class , () -> newApplicantUnderTest.start());
    }
}
