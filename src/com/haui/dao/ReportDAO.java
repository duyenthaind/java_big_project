package com.haui.dao;

import com.haui.cache.UserManager;
import com.haui.entities.KetQua;
import com.haui.entities.Khoa;
import com.haui.entities.SinhVien;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * @author duyenthai
 */
public class ReportDAO {
    private String userId;
    private CustomStudent customStudent;
    private List<KetQua> listResults;
    private Connection connection;

    private static final String QUERY_USER = "select *from sinhvien where masv = ?";
    private static final String QUERY_RESULT = "select *from ketqua where maSV = ?";
    private static final String QUERY_DEPARTMENT = "select tenkhoa from khoa where makhoa = (select makhoa from lop where malop = ?)";

    public String getUserId() {
        return userId;
    }

    public CustomStudent getCustomStudent() {
        return customStudent;
    }

    public List<KetQua> getListResults() {
        return listResults;
    }

    public ReportDAO() {
        try {
            if (UserManager.instance().getUserName() != null || !UserManager.instance().getUserName().equals("")) {
                userId = UserManager.instance().getUserName();
                connection = new DerbyUtil.Builder().build().getConnection();
            }
        } catch (Exception ex) {
            throw new RuntimeException("init dao exception, trace: " + ex);
        }

    }

    class CustomStudent{
        private SinhVien sv;
        private String department;

        public CustomStudent(SinhVien sv, String department) {
            this.sv = sv;
            this.department = department;
        }

        public SinhVien getSv() {
            return sv;
        }

        public String getDepartment() {
            return department;
        }
    }

    public boolean fetchResult(){
        boolean result = false;
        try{
            this.customStudent = getInfo();
            this.listResults = getFinalResults();
            result = true;
        } catch(Exception ex){
            System.err.println("Fetch result error, trace: " + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }

    private List<KetQua> getFinalResults() {
        Map<String, KetQua> map = new HashMap<>();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_RESULT);
            statement.setString(1, this.userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                KetQua rs = new KetQua();
                rs.setMaSV(resultSet.getString("masv"));
                rs.setMaHP(resultSet.getString("mahp"));
                rs.setDiem(resultSet.getDouble("diem"));
                if (map.containsKey(rs.getMaSV())) {
                    map.put(rs.getMaSV(), rs);
                } else {
                    if (map.get(rs.getMaSV()).getDiem() < rs.getDiem()) {
                        map.put(rs.getMaSV(), rs);
                    }
                }
            }
            return (List<KetQua>)map.values();
        } catch (Exception ex) {
            System.err.println("Get all result error, trace: " + ex);
            ex.printStackTrace();
        }
        return null;
    }

    private CustomStudent getInfo(){
        CustomStudent sv = null;
        try{
            SinhVien student = null;
            PreparedStatement statementStudent = connection.prepareStatement(QUERY_USER);
            statementStudent.setString(1, userId);
            ResultSet resultSetSv = statementStudent.executeQuery();
            while(resultSetSv.next()){
                student = new SinhVien();
                student.setMaSV(userId);
                student.setTenSV(resultSetSv.getString("tensv"));
                student.setDiaChi(resultSetSv.getString("diachi"));
                student.setEmail(resultSetSv.getString("email"));
                student.setGioiTinh(resultSetSv.getInt("gioitinh"));
                student.setMaLop(resultSetSv.getString("malop"));
                student.setSdt(resultSetSv.getString("sdt"));
                student.setMatKhau(resultSetSv.getString("matkhau"));
                student.setNgaySinh(resultSetSv.getLong("ngaysinh"));
            }
            if(student != null){
                Khoa department = null;
                PreparedStatement statementDepartment = connection.prepareStatement(QUERY_DEPARTMENT);
                statementDepartment.setString(1, student.getMaLop());
                ResultSet resultSetKhoa = statementDepartment.executeQuery();
                while(resultSetKhoa.next()){
                    department = new Khoa();
                    department.setNgayTL(resultSetKhoa.getLong("ngaytl"));
                    department.setTenKhoa(resultSetKhoa.getString("tenkhoa"));
                    department.setMaKhoa(resultSetKhoa.getString("makhoa"));
                }
                sv = new CustomStudent(student, department.getTenKhoa());
            }

        } catch(Exception ex){
            System.err.println("Fetch student info error, trace: " + ex);
            ex.printStackTrace();
        }
        return sv;
    }
}


