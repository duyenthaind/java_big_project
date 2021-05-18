package com.haui.thaind.dao;

import com.haui.thaind.cache.UserManager;
import com.haui.entities.KetQua;
import com.haui.entities.Khoa;
import com.haui.entities.MonHoc;
import com.haui.entities.SinhVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * @author duyenthai
 */
public class ReportDAO {
    private String userId;
    private SinhVien student;
    private Khoa department;
    private List<KetQua> listResults;
    private Connection connection;
    private float avgPoints = 0.0f;
    private int totalCredits = 0;
    private Map<String, MonHoc> mapSubject;

    private static final String QUERY_USER = "select *from sinhvien where masv = ?";
    private static final String QUERY_RESULT = "select ketqua.masv, ketqua.mahp, ketqua.diem, monhoc.mamh from ketqua " +
            "join hocphan on ketqua.mahp = hocphan.mahp join monhoc on hocphan.mamh = monhoc.mamh where maSV = ?";
    private static final String QUERY_DEPARTMENT = "select * from khoa where makhoa = (select makhoa from lop where malop = ?)";
    private static final String QUERY_SUBJECT = "select h.mahp, m.* from hocphan h join monhoc m on h.mamh = m.mamh " +
            "join ketqua k on h.mahp = k.mahp where k.masv = ?";

    public String getUserId() {
        return userId;
    }

    public SinhVien getStudent() {
        return student;
    }

    public Khoa getDepartment() {
        return department;
    }

    public List<KetQua> getListResults() {
        return listResults;
    }

    public Float getAvgPoints() {
        return avgPoints;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public Map<String, MonHoc> getMapSubject() {
        return mapSubject;
    }

    public ReportDAO() {
        try {
            UserManager.init("SV2");
            if (UserManager.instance().getUserName() != null || !UserManager.instance().getUserName().equals("")) {
                userId = UserManager.instance().getUserName();
                connection = new DerbyUtil.Builder().build().getConnection();
            }
        } catch (Exception ex) {
            throw new RuntimeException("init dao exception, trace: " + ex);
        }

    }

    public boolean fetchResult() {
        boolean result = false;
        try {
            getInfo();
            this.mapSubject = getMapSubjectResults();
            this.listResults = getFinalResults();
            if (mapSubject != null && mapSubject.size() > 0) {
                int sum = mapSubject.values().stream().map(x -> x.getSoTinChi()).reduce(0, (a, b) -> a + b);
                totalCredits = sum;
            }
            if (totalCredits > 0) {
                Float sum = listResults.stream().map(x -> x.getDiem() * mapSubject.get(x.getMaHP()).getSoTinChi()).reduce(0.0f, (a, b) -> a + b);
                avgPoints = sum / totalCredits;
            }
            result = true;
        } catch (Exception ex) {
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
                rs.setDiem(resultSet.getFloat("diem"));
                String key = resultSet.getString("mamh");
                if (!map.containsKey(key)) {
                    map.put(key, rs);
                } else {
                    if (map.get(key).getDiem() < rs.getDiem()) {
                        map.put(key, rs);
                    }
                }
            }
            return new ArrayList<KetQua> (map.values()) ;
        } catch (Exception ex) {
            System.err.println("Get all result error, trace: " + ex);
            ex.printStackTrace();
        }
        return null;
    }

    private boolean getInfo() {
        boolean result = false;
        try {
            PreparedStatement statementStudent = connection.prepareStatement(QUERY_USER);
            statementStudent.setString(1, userId);
            ResultSet resultSetSv = statementStudent.executeQuery();
            while (resultSetSv.next()) {
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
            if (student != null) {
                PreparedStatement statementDepartment = connection.prepareStatement(QUERY_DEPARTMENT);
                statementDepartment.setString(1, student.getMaLop());
                ResultSet resultSetKhoa = statementDepartment.executeQuery();
                while (resultSetKhoa.next()) {
                    department = new Khoa();
                    department.setNgayTL(resultSetKhoa.getLong("ngaytl"));
                    department.setTenKhoa(resultSetKhoa.getString("tenkhoa"));
                    department.setMaKhoa(resultSetKhoa.getString("makhoa"));
                }
            }
            result = true;
        } catch (Exception ex) {
            System.err.println("Fetch student info error, trace: " + ex);
            ex.printStackTrace();
        }
        return result;
    }

    private Map<String, MonHoc> getMapSubjectResults() {
        Map<String, MonHoc> result = new HashMap<>();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_SUBJECT);
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MonHoc subject = new MonHoc();
                subject.setMaMH(resultSet.getString("mamh"));
                subject.setMaKhoa(resultSet.getString("makhoa"));
                subject.setSoTinChi(resultSet.getInt("sotinchi"));
                subject.setTenMH(resultSet.getString("tenmh"));
                String hpId = resultSet.getString("mahp");
                if (subject.getMaMH() != null && !subject.getMaMH().trim().equals("") && hpId != null && !hpId.equals("")) {
                    result.put(hpId, subject);
                }
            }

        } catch (Exception ex) {
            System.err.println("Get all subject error, trace: " + ex);
            ex.printStackTrace();
        }
        return result;
    }
}


