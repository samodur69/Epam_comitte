package data;


import oracle.jdbc.datasource.impl.OracleDataSource;
import oracle.jdbc.driver.OracleConnection;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

public class DataAccess_garbage {

    final static String DB_URL="jdbc:oracle:thin:@firsttestbase_medium?TNS_ADMIN=/Users/apple/_Projects/Epam_comitte/Wallet_FirstTestBase";
    final static String DB_USER = "ADMIN";
    final static String DB_PASSWORD = "Loveloveyou87";

    public static Connection getConnection() throws SQLException {
        Properties info = new Properties();
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);

        OracleDataSource ods = new OracleDataSource();
        ods.setURL(DB_URL);
        ods.setConnectionProperties(info);
        try (Connection conn = ods.getConnection()) {
            DatabaseMetaData dbmd = conn.getMetaData();
            System.out.println("Driver Name: " + dbmd.getDriverName());
            System.out.println("Driver Version: " + dbmd.getDriverVersion());
//            System.out.println("Database Username is: " + conn.getUserName());
            System.out.println("Connection OK!");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can`t connect");
            return null;
        }
    }
}
