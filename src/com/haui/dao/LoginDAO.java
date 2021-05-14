package com.haui.dao;

import com.haui.cache.UserManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author duyenthai
 */
public class LoginDAO implements SimpleDAO {

    private static final String GET_USER_BY_ID = "select *from sinhvien where masv = ?";

    @Override
    public List getAll(Connection connection) {
        return null;
    }

    @Override
    public boolean isExist(String id, Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.wasNull()) {
                UserManager.init(id);
                return true;
            }
        } catch (Exception ex) {
            System.err.println("Error check account, trace: " + ex);
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Object object, Connection connection) {
        return false;
    }
}
