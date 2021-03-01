package dao.implementations;

import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.sql.Connection;

public abstract class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    Connection conn;

    @BeforeSuite
    public void beforeSuitSetUp() {
        // there is place for make connection to test base, but i didn`t implements it
        logger.info("Get connection for TestNg");
        conn = DBConnection.getConnection();
    }

    @AfterSuite
    public void shutdownTestConnection() {
        DBConnection.close(conn);
    }
}
