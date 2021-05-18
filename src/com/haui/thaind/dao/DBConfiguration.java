/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haui.thaind.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author duyenthai
 */
public class DBConfiguration {

    public static String DB_USER_NAME;

    public static String DB_PASSWORD;

    public static String DB_URL;
    
    public static Properties getDBProperties(){
        Properties prop = new Properties();
        prop.put("user", DB_USER_NAME);
        prop.put("password", DB_PASSWORD);
        return prop;
    }

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
            }
        } catch (IOException ex) {
            System.err.println("Get properties error" + ex);
            ex.printStackTrace();
        }
    }
    
}
