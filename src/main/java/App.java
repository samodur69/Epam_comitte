import dao.model.ExaminationList;
import data.DBConnection;
import ui.MainMenu;

import java.sql.Connection;
import java.sql.SQLException;


public class App {
    public static void main(String[] args) {
        Connection connection;
        connection = DBConnection.getConnection();
        try {
            MainMenu.start();
        } catch (Exception e) {
            System.out.println("big troubles");
        } finally {
            DBConnection.close(connection);
        }

    }
}
