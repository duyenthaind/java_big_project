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
import java.util.Map;

/**
 *
 * @author duyenthai
 */
public interface BaseDAO<T> {
    public List<T> getAll(Connection connection);

    public T getWithId(String id, Connection connection);

    public boolean save(T object, Connection connection);

    public boolean update(T oldValue, T newValue, Connection connection);

    public boolean update(String id, T newValue, Connection connection);

    public boolean delete(String id, Connection connection);

    public boolean delete(T object, Connection connection);

    public boolean executeQuery(String query, Connection connection);

    public boolean change(Map<String, String> map, Connection connection);
}
