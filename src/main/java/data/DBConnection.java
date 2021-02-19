package data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.GetProperties;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBConnection {

    private final String DB_URL="jdbc:oracle:thin:@firsttestbase_medium?TNS_ADMIN=/Users/apple/_Projects/Epam_comitte/src/main/resources/Wallet_FirstTestBase";
    private final String DB_USER = "ADMIN";
    private final String DB_PASSWORD = "AdmissionCommitte1";
//    private String DB_URL;
//    private String DB_USER;
//    private String DB_PASSWORD;
    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);
    private static DBConnection instance;

    private DBConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleConnection");
//            Properties properties = new Properties();
//            try (FileInputStream is = new FileInputStream("/Users/apple/_Projects/Epam_comitte/src/main/resources/app.properties")) {
//                properties.load(is);
//                DB_URL = properties.getProperty("DB_URL");
//                DB_USER = properties.getProperty("DB_USER");
//                DB_PASSWORD = properties.getProperty("DB_PASSWORD");
//            } catch (IOException e) {
//                logger.info("can`t find properties file");
//            }
        } catch (ClassNotFoundException e) {
            System.out.println("Troubles with DB Driver");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (instance == null) {
            instance = new DBConnection();
            logger.info("New DB connection created");
        }
        try {
            return DriverManager.getConnection(instance.DB_URL, instance.DB_USER, instance.DB_PASSWORD);
        } catch (SQLException e) {
            logger.info("Troubles with return connection");
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Troubles with closing connection");
            e.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet, PreparedStatement ps, Connection conn) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Troubles with closing connection");
            e.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet, Statement statement, Connection conn) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Troubles with closing connection");
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement preparedStatement, Connection conn) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Troubles with closing connection");
            e.printStackTrace();
        }
    }
}
