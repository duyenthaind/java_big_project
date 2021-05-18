package com.haui.thaind.dao;

import java.sql.Connection;
import java.util.List;

/**
 * @author duyenthai
 */
public interface SimpleDAO<T> {
    public List<T> getAll(Connection connection);

    public boolean isExist(String id, Connection connection);

    public boolean isExist(String id, String password, Connection connection);

    public boolean update(T object, Connection connection);
}
