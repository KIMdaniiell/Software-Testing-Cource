package readProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfProperties {
    static FileInputStream fileInputStream;
    static Properties PROPERTIES;

    static {
        try {
            // Указание пути до файла с настройками
            fileInputStream = new FileInputStream("src/test/resources/tmp_conf.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка возможного исключения (нет файла и т.п.)
        } finally {
            if (null != fileInputStream)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * метод для возврата строки со значением из файла с настройками
     */
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}

