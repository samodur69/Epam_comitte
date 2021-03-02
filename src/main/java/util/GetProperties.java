package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Garbage class. I had troubles with Oracle connection and with properties it don`t work correctly
 */
public class GetProperties {

    public String getDbProperties(String property) {
        String propertyValue = "";
        Properties properties = new Properties();
        try {
            FileInputStream is = new FileInputStream("src/main/resources/app.properties");
            properties.load(is);
            propertyValue = properties.getProperty(property);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyValue;
    }
}
