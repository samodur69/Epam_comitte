package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {

    private final String DB_URL="jdbc:oracle:thin:@firsttestbase_medium?TNS_ADMIN=/Users/apple/_Projects/Epam_comitte/Wallet_FirstTestBase";
    private final String DB_USER = "ADMIN";
    private final String DB_PASSWORD = "Loveloveyou87";

    private static DBConnection instance;

    private DBConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleConnection");
        } catch (ClassNotFoundException e) {
            System.out.println("Troubles with DB Driver");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (instance == null) {
            instance = new DBConnection();
            System.out.println("New DB connection created");
        }
        try {
            return DriverManager.getConnection(instance.DB_URL, instance.DB_USER, instance.DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Troubles with return connection");
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.out.println("Troubles with closing connection");
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement ps, Connection conn) {
        try {
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
}
