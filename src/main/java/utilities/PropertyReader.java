package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    Properties properties = new Properties();

    public PropertyReader(String path){
        try {
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getKey(String key){
        return properties.getProperty(key);
    }

}
