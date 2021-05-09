/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haui.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author duyenthai
 */
public class BaseDAO {

    private static final DerbyUtil DERBY_UTIL = new DerbyUtil.Builder().build();

    private static List getAllDepartment() {
        String sql = "select *from KHOA";
        try {
            Connection connection = DERBY_UTIL.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    System.out.println("id:" + resultSet.getString("ma_khoa") + "\tname: " + resultSet.getString("ten_khoa"));
                }
            }
        } catch (Exception ex) {
            System.err.println("Query error: " + ex);
        }

        return null;
    }
}
