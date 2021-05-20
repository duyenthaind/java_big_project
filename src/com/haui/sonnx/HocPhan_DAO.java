/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haui.sonnx;

import com.haui.dao.BaseDAO;
import com.haui.entities.HocPhan;
import com.haui.entities.ThoiKhoaBieu;
import com.haui.thaind.cache.UserManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Admin
 */
public class HocPhan_DAO implements BaseDAO {

    private static final String INSERT_THOI_KHOA_BIEU = "insert into thoikhoabieu(masv,mahp,mamh) values (?,?,?)";
    private static final String GET_HOCPHAN = "select * FROM HocPhan";
    private static final String GET_TIME_TABLE = "select hocphan.* from hocphan join thoikhoabieu on hocphan.mahp = thoikhoabieu.mahp where masv = ";
    private static final String DELETE_HOCPHAN = "delete from thoikhoabieu where masv=? and mahp=?";
    private static final String CHECK_HOCPHAN = "SELEct thoikhoabieu.* from thoikhoabieu join hocphan on thoikhoabieu.mahp=hocphan.mahp"
            + " where masv=? and thoigianhoc=?";
    private static final String GET_CHITIET = "select thoikhoabieu.MAHP, tenmh, tengv, thoigianhoc, sotiethoc from hocphan join monhoc on hocphan.MAMH=monhoc.MAMH join giangvien on hocphan.MAGV=giangvien.MAGV join thoikhoabieu\n"
            + "on hocphan.mahp = thoikhoabieu.mahp where thoikhoabieu.masv=? and thoikhoabieu.mahp=?";

    public boolean check_tkb(Connection connection, String thoigianhoc, String mahp) {
        boolean check = true;
        String userID = UserManager.instance().getUserName();
        try {
            PreparedStatement statement = connection.prepareStatement(CHECK_HOCPHAN);
            statement.setString(1, userID);
            statement.setString(2, thoigianhoc);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String sv = resultSet.getString("masv");
                if (sv != null && !sv.isEmpty()) {
                    check = false;
                }
            }
        } catch (Exception e) {
            System.out.println("loi ");
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public List getAll(Connection connection) {
        List<HocPhan> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_HOCPHAN);
            while (resultSet.next()) {
                HocPhan hocPhan = new HocPhan();
                hocPhan.setMaHP(resultSet.getString("mahp"));
                hocPhan.setMaMH(resultSet.getString("mamh"));
                hocPhan.setMaGV(resultSet.getString("magv"));
                hocPhan.setNgayBD(resultSet.getLong("ngaybd"));
                hocPhan.setNgayKT(resultSet.getLong("ngaykt"));
                hocPhan.setSoTietHoc(resultSet.getInt("sotiethoc"));
                hocPhan.setThoiGianHoc(resultSet.getString("thoigianhoc"));
                hocPhan.setHocPhi(resultSet.getFloat("hocphi"));
                list.add(hocPhan);
            }
        } catch (Exception e) {
            System.out.println("Loi khi lay du lieu " + e);
            e.printStackTrace();
        }
        return list;
    }

    public Map<String, String> getTenMH(Connection connection) {
        String GET_TENMH = "select mahp,tenmh from monhoc inner join hocphan on monhoc.mamh =hocphan.mamh";
        Map<String, String> map = new HashMap<String, String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_TENMH);
            while (resultSet.next()) {
                String mahp = resultSet.getString("mahp");
                String tenmh = resultSet.getString("tenmh");
                map.put(mahp, tenmh);
            }
        } catch (Exception e) {
            System.out.println("Loi" + e);
        }
        return map;
    }

    public Map<String, String> getThoiKhoaBieu(Connection connection) {
        String userID = UserManager.instance().getUserName();
        String GET_THOI_GIAN_HOC = "select thoigianhoc, thoikhoabieu.mahp from hocphan join thoikhoabieu on hocphan.mahp=thoikhoabieu.mahp where masv= ?";
        Map<String, String> map_tkb = new HashMap<String, String>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_THOI_GIAN_HOC);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String thoikhoabieu = resultSet.getString("thoigianhoc");
                String mahp = resultSet.getString("mahp");
                map_tkb.put(thoikhoabieu, mahp);
            }
        } catch (Exception e) {
            System.out.println("Loi" + e);
        }
        return map_tkb;
    }

    @Override
    public Object getWithId(String id, Connection connection) {
        String query = "select *from hocphan where mahp=?";
        HocPhan hocPhan = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                hocPhan = new HocPhan();
                hocPhan.setMaHP(resultSet.getString("mahp"));
                hocPhan.setMaMH(resultSet.getString("mamh"));
                hocPhan.setMaGV(resultSet.getString("magv"));
                hocPhan.setNgayBD(resultSet.getLong("ngaybd"));
                hocPhan.setNgayKT(resultSet.getLong("ngaykt"));
                hocPhan.setSoTietHoc(resultSet.getInt("sotiethoc"));
                hocPhan.setThoiGianHoc(resultSet.getString("thoigianhoc"));
                hocPhan.setHocPhi(resultSet.getFloat("hocphi"));
            }
        } catch (Exception e) {
            System.out.println("Loi khi lay du lieu theo id" + id + "voi" + e);

        }
        return hocPhan;
    }

    public List getSubcribed(Connection connection) {
        List<HocPhan> list = new ArrayList<>();
        String userID = UserManager.instance().getUserName();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_TIME_TABLE + "'" + userID + "'");
            while (resultSet.next()) {
                HocPhan hocPhan = new HocPhan();
                hocPhan.setMaHP(resultSet.getString("mahp"));
                hocPhan.setMaMH(resultSet.getString("mamh"));
                hocPhan.setMaGV(resultSet.getString("magv"));
                hocPhan.setNgayBD(resultSet.getLong("ngaybd"));
                hocPhan.setNgayKT(resultSet.getLong("ngaykt"));
                hocPhan.setSoTietHoc(resultSet.getInt("sotiethoc"));
                hocPhan.setThoiGianHoc(resultSet.getString("thoigianhoc"));
                hocPhan.setHocPhi(resultSet.getFloat("hocphi"));
                list.add(hocPhan);
            }
        } catch (Exception e) {
            System.out.println("Loi");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Object object, Connection connection) {
        boolean kq = false;
        HocPhan tkb = (HocPhan) object;
        String userID = UserManager.instance().getUserName();
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_THOI_KHOA_BIEU);
            statement.setString(1, userID);
            statement.setString(2, tkb.getMaHP());
            statement.setString(3, tkb.getMaMH());
            kq = statement.execute();

        } catch (Exception e) {
            System.err.println("Loi");
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public boolean update(Object oldValue, Object newValue, Connection connection) {
        return false;

    }

    @Override
    public boolean update(String id, Object newValue, Connection connection) {
        return false;

    }

    @Override
    public boolean delete(String id, Connection connection) {
        boolean kq = false;
        String userID = UserManager.instance().getUserName();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_HOCPHAN);
            statement.setString(1, userID);
            statement.setString(2, id);
            kq = statement.execute();
        } catch (Exception e) {
            System.err.println("Loi");
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public boolean delete(Object object, Connection connection) {
        return false;

    }

    @Override
    public boolean executeQuery(String query, Connection connection) {
        return false;

    }

    @Override
    public boolean change(Map map, Connection connection) {
        return false;
    }

    public void xemChiTiet(String mahp, Connection connection) {
        String userID = UserManager.instance().getUserName();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_CHITIET);
            statement.setString(1, userID);
            statement.setString(2, mahp);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String mahocphan = resultSet.getString("mahp");
                String tenhocphan = resultSet.getString("tenmh");
                String tengiangvien = resultSet.getString("tengv");
                String tgh = resultSet.getString("thoigianhoc");
                int sth = resultSet.getInt("sotiethoc");
                XemChiTiet_Frame frame = new XemChiTiet_Frame(mahocphan, tenhocphan, tengiangvien, tgh, sth);
                frame.setVisible(true);
                return;
            }
        } catch (Exception e) {
            System.out.println("Loi lay du lieu ");
            e.printStackTrace();
        }
    }

}
