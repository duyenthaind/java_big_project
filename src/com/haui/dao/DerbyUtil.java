/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haui.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author duyenthai
 */
public class DerbyUtil {

    private Connection connection = null;

    private DerbyUtil(Builder builder) {
        this.connection = builder.connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public static class Builder {

        public Connection connection;

        public Builder() {
            init();
        }

        private void init() {
            try {
                DBConfiguration.loadConfig();
                connection = DriverManager.getConnection(DBConfiguration.DB_URL, DBConfiguration.getDBProperties());
            } catch (Exception ex) {
                System.err.println("Connection error: " + ex);
                ex.printStackTrace();
            }
        }
        
        public DerbyUtil build(){
            return new DerbyUtil(this);
        }
    }
}
