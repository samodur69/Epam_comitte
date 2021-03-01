import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.MainMenu;

import java.sql.Connection;

public class App {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(App.class);
        Connection connection = DBConnection.getConnection();
        try {
            MainMenu.start();
        } catch (Exception e) {
            logger.error("fatal crash. Use debug");
            e.printStackTrace();
        } finally {
            DBConnection.close(connection);
        }

    }
}
