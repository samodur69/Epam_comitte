import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.MainMenu;

import java.sql.Connection;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Connection connection = DBConnection.getConnection();
        try {
            MainMenu.start();
        } catch (Exception e) {
            logger.info("big troubles");
        } finally {
            DBConnection.close(connection);
        }

    }
}
