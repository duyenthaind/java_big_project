package com.haui.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author duyenthai
 */
public class Config {
    public static String DB_USER_NAME;
    public static String DB_PASSWORD;
    public static String DB_URL;
    public static String BASE_DIR_EXPORT;

    public static void loadConfig() {
        String fileName = "./conf/config.properties";
        try {
            InputStream fileInputStream = new FileInputStream(fileName);
            if (fileInputStream != null) {
                Properties prop = new Properties();
                prop.load(fileInputStream);
                DB_USER_NAME = prop.getProperty("username");
                DB_PASSWORD = prop.getProperty("password");
                DB_URL = prop.getProperty("url");
                BASE_DIR_EXPORT = prop.getProperty("exportDir");
            }
        } catch (IOException ex) {
            System.err.println("Get properties error" + ex);
            ex.printStackTrace();
        }
    }
}
