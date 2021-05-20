/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haui.taind;

import com.haui.common.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Connect {

//    void getconnect() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    Connection con = null;
    ResultSet res = null;
    Statement sta = null;
    private String masv = "";

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public void getConnect() {
        try {
            Config.loadConfig();
            String url = Config.DB_URL;
            String user = Config.DB_USER_NAME;
            String pass = Config.DB_PASSWORD;
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = (Connection) DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Khong the ket noi voi database \n" + e);
        }

    }

    protected Statement getStatement() throws Exception {
        if (this.sta == null || this.sta.isClosed()) {
            //khoi tao statement moi
            this.sta = this.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
        }
        return this.sta;
    }

    public ResultSet executeQuery(String qr) throws Exception {
        try {
            //thuc thi cau lenh
            this.res = getStatement().executeQuery(qr);
        } catch (Exception e) {
            throw new Exception("Cau lenh Query khong duoc thuc thi");
        }
        return this.res;
        //van mo ket noi de lay thong tin
    }

    public int executeUpdate(String qr) throws Exception {
        int ketqua = 0;
        try {
            // thuc thi cau lenh
            ketqua = getStatement().executeUpdate(qr);
        } catch (Exception e) {
            throw new Exception("Loi queryUpdate khong duoc thuc hien");
        } finally {
            this.con.close();
        }
        return ketqua;
    }

    public ResultSet GetData(String jtable) throws SQLException {
        ResultSet kq = null;
        Statement st = con.createStatement();
        kq = st.executeQuery("select * from SINHVIEN where masv =" + "'" + masv + "'");
        return kq;
    }

    public int add(String maSV, String tenSV, long ngaySinh, int gioiTinh,
            String diaChi, String email, String matKhau, String sdt,
            String maLop) throws Exception {
        int t = 0;
        t = getStatement().executeUpdate("insert into SINHVIEN values('" + maSV + "'"
                + "," + tenSV + "," + ngaySinh + "," + gioiTinh + "," + diaChi + ","
                + "" + email + "," + matKhau + "," + sdt + "," + maLop + ") ");
        return t;
    }

    public int update(String tenSV, long ngaySinh, int gioiTinh,
            String diaChi, String email, String sdt) throws Exception {
        int t;
        t = getStatement().executeUpdate("update SinhVien set  TenSV= '" + tenSV + "'"
                + ",NgaySinh= " + ngaySinh + " ,GioiTinh= " + gioiTinh + ",DiaChi= '" + diaChi + "', Email= '" + email + "' ,"
                + "SDT= '" + sdt + "' where masv = '" + masv + "'");
        return t;
    }

}
