package com.haui.thaind.dao;

import com.haui.thaind.cache.UserManager;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author duyenthai
 */
public class LoginDAO implements SimpleDAO {

    private static final String GET_USER_BY_ID = "select *from sinhvien where masv = ? and matkhau = ?";

    @Override
    public List getAll(Connection connection) {
        return null;
    }

    @Override
    public boolean isExist(String id, Connection connection) {
        return false;
    }

    @Override
    public boolean isExist(String id, String password, Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID);
            statement.setString(1, id);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("masv") != null && !resultSet.getString("masv").equals("")) {
                    UserManager.init(id);
                    return true;
                }
            }
        } catch (Exception ex) {
            System.err.println("Error check account, trace: " + ex);
            ex.printStackTrace();
        } finally{
            if(connection != null){
                try{
                    connection.close();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error: Close connection error, see logs for more details!", "Error", JOptionPane.ERROR_MESSAGE);
                    System.err.println("Close connection error, trace: " + ex);
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean update(Object object, Connection connection) {
        return false;
    }
}
