package dao.implementations;

import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {
    @BeforeSuite
    public void beforeSuitSetUp() {
        System.out.println("BEFORE");
    }
}
