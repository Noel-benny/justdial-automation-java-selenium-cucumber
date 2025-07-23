package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class configReader {
    private static Properties prop;

    public static void loadProperties() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop.load(fis);
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
