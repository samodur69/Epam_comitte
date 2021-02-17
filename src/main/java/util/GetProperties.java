package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
