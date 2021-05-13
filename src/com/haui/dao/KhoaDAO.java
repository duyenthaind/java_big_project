package com.haui.dao;

import com.haui.entities.Khoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class KhoaDAO implements BaseDAO {

    private static final String QUERY_BASE_FETCH = "select * from khoa";
    private static final String QUERY_BASE_UPDATE = "update khoa set";
    private static final String QUERY_INSERT = "insert into khoa(makhoa, tenkhoa, ngaytl) values (?,?,?)";
    private static final String QUERY_UPDATE = "update khoa set tenkhoa=? and ngaytl=? where makhoa=?";
    private static final String QUERY_DELETE = "delete from khoa where makhoa = ?";

    @Override
    public List getAll(Connection connection) {
        List<Khoa> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_BASE_FETCH);
            while (resultSet.next()) {
                Khoa entity = new Khoa();
                entity.setMaKhoa(resultSet.getString("makhoa"));
                entity.setTenKhoa(resultSet.getString("tenkhoa"));
                entity.setNgayTL(resultSet.getLong("ngaytl"));
                list.add(entity);
            }
        } catch (Exception ex) {
            System.err.println("Error fetch data: " + ex);
        }
        return list;
    }

    @Override
    public Object getWithId(String id, Connection connection) {
        String query = QUERY_BASE_FETCH + " where makhoa = ?";
        Khoa khoa = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                khoa = new Khoa();
                khoa.setMaKhoa(resultSet.getString("makhoa"));
                khoa.setTenKhoa(resultSet.getString("tenkhoa"));
                khoa.setNgayTL(resultSet.getLong("ngaytl"));
                break;
            }
        } catch (Exception ex) {
            System.err.println("Error fetch data with id " + id + ",trace: " + ex);
        }
        return khoa;
    }

    @Override
    public boolean save(Object object, Connection connection) {
        if (object instanceof Khoa) {
            Khoa khoa = (Khoa) object;
            try {
                /*
                    Check null value from gui, backend will not check it, avoid escaping object in construction phase
                 */
                PreparedStatement statement = connection.prepareStatement(QUERY_INSERT);
                statement.setString(1, khoa.getMaKhoa());
                statement.setString(2, khoa.getTenKhoa());
                statement.setLong(3, khoa.getNgayTL());
                return statement.execute();
            } catch (Exception ex) {
                System.err.println("Insert error: " + ex);
            }
        }
        return false;
    }

    @Override
    public boolean update(Object oldValue, Object newValue, Connection connection) {
        if (oldValue instanceof Khoa && newValue instanceof Khoa) {
            Khoa old = (Khoa) oldValue;
            Khoa ne = (Khoa) newValue;
            Map<String, String> map = new HashMap<>();
            if (!old.getTenKhoa().equals(ne.getTenKhoa())) {
                map.put("tenkhoa", ne.getTenKhoa());
            }
            if (old.getNgayTL() != ne.getNgayTL()) {
                map.put("ngaytl", ne.getNgayTL() + "");
            }
            return change(map, connection);
        }
        return false;
    }

    @Override
    public boolean update(String id, Object newValue, Connection connection) {
        if (newValue instanceof Khoa) {
            try {
                Khoa khoa = (Khoa) newValue;
                PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE);
                statement.setString(1, khoa.getTenKhoa());
                statement.setLong(2, khoa.getNgayTL());
                statement.setString(3, id);
                return statement.execute();
            } catch (Exception ex) {
                System.err.println("Update with id " + id + "error, trace: " + ex);
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id, Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_DELETE);
            statement.setString(1, id);
            return statement.execute();
        } catch (Exception ex) {
            System.err.println("Delete error, trace: " + ex);
        }
        return false;
    }

    @Override
    public boolean delete(Object object, Connection connection) {
        try {
            Khoa khoa = (Khoa) object;
            String id = khoa.getMaKhoa();
            return delete(id, connection);
        } catch (Exception ex) {
            System.err.println("Delete object from db error, trace: " + ex);
        }
        return false;
    }

    @Override
    public boolean executeQuery(String query, Connection connection) {
        try {
            return connection.createStatement().execute(query);
        } catch (Exception ex) {
            System.err.println("Execute query error, trace: " + ex);
        }
        return false;
    }

    @Override
    public boolean change(Map map, Connection connection) {
        HashMap<String, String> hashMap = new HashMap<>(map);
        String id = map.get("makhoa").toString();
        String fullQueryUpdate = QUERY_BASE_UPDATE;
        try {
            Statement statement = connection.createStatement();
            Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entrySet = iterator.next();
                switch (entrySet.getKey()) {
                    case "tenkhoa":
                        fullQueryUpdate = fullQueryUpdate + " " + entrySet.getKey() + "='" + entrySet.getValue() + "'";
                        break;
                    case "ngaytl":
                        fullQueryUpdate = fullQueryUpdate + " " + entrySet.getKey() + "=" + entrySet.getValue();
                        break;
                    default:
                        break;
                }
                if (iterator.hasNext()) {
                    fullQueryUpdate += " and ";
                }
            }
            fullQueryUpdate += "where makhoa = " + id;
            System.out.println("Full query update using custom map update: " + fullQueryUpdate);
            return statement.execute(fullQueryUpdate);
        } catch (Exception ex) {
            System.err.println("Update error, trace: " + ex);
        }
        return false;
    }
}
