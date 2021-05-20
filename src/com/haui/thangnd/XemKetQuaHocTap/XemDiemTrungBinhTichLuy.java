/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haui.thangnd.XemKetQuaHocTap;

import com.haui.thaind.cache.UserManager;
import com.sun.istack.internal.localization.NullLocalizable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * @author TAVPLTNDTSUN
 */
public class XemDiemTrungBinhTichLuy extends javax.swing.JPanel {
    ResultSet rs = null;
    Statement sta = null;
    Connect connect = new Connect();
    DefaultTableModel defaultTableModel;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String MaSV;//Chưa Lấy Từ Login

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String maSV) {
        MaSV = maSV;
    }

    /**
     * Creates new form XemDiemTrungBinhTichLuy
     */


    public XemDiemTrungBinhTichLuy() {
        initComponents();
        setMaSV(UserManager.instance().getUserName());
        loadLabel();
        loadTable("");
        loadComboBox();
    }

    public void loadTable(String nameFilter) {
        try {
            connect.getConnect();
            jtb_DSDiemTrungBinhTichLuy.removeAll();
            String[] name = {"STT", "Mã HP", "Mã MH", "Tên MH", "Số TC", "Điểm H4"};
            defaultTableModel = new DefaultTableModel(name, 0);
            jtb_DSDiemTrungBinhTichLuy.setModel(defaultTableModel);
            String query = "";
            if (nameFilter.isEmpty()) {
                query = "Select *\n"
                        + "from SinhVien sv inner join KetQua kq on sv.MASV = kq.MASV\n"
                        + "inner join HocPhan hp on kq.MAHP = hp.MAHP\n"
                        + "inner join MonHoc mh on hp.MAMH = mh.MAMH\n"
                        + "Where sv.MASV = '" + getMaSV() + "'";
            }
            if (!nameFilter.isEmpty()) {
                query = "Select *\n"
                        + "from SinhVien sv inner join KetQua kq on sv.MASV = kq.MASV\n"
                        + "inner join HocPhan hp on kq.MAHP = hp.MAHP\n"
                        + "inner join MonHoc mh on hp.MAMH = mh.MAMH\n"
                        + "Where sv.MASV = '" + getMaSV() + "'\n"
                        + "Order By " + nameFilter;
            }
            rs = connect.executeQuery(query);
            System.out.println("V ! " + query);
            int sd = 1;
            while (rs.next()) {
                Vector v = new Vector();
                v.add(sd);
                v.add(rs.getString("MAHP"));
                v.add(rs.getString("MAMH"));
                v.add(rs.getString("TENMH"));
                v.add(rs.getInt("SOTINCHI"));
                v.add(rs.getFloat("DIEM"));
                v.add(getRanking(rs.getFloat("DIEM")));
                defaultTableModel.addRow(v);
                sd = sd + 1;
                System.out.println("V ! " + rs.getString("MAMH"));
            }
            jtb_DSDiemTrungBinhTichLuy.setModel(defaultTableModel);
        } catch (Exception ex) {
            Logger.getLogger(XemDiemTongKet.class.getName()).log(Level.SEVERE, "Error", ex);
        }
    }

    public void loadLabel() {
        try {
            String query = "select * from sinhvien sv inner join LOP l on sv.MALOP = l.MALOP"
                    + " inner join GIANGVIEN gv on l.MAGV = gv.MAGV"
                    + " Where sv.MASV = '" + getMaSV() + "'";
            connect.getConnect();
            rs = connect.executeQuery(query);
            while (rs.next()) {
                jlb_maSinhVien.setText(rs.getString("MASV"));
                jlb_hoTen.setText(rs.getString("TENSV"));
                jlb_lop.setText(rs.getString("TENLOP"));
                jlb_giaoVienChuNhiem.setText(rs.getString("TENGV"));
            }
//            rs = null;
            query = "Select  SOTINCHI * DIEM AS TONGDIEM, SOTINCHI\n"
                    + "from SinhVien sv inner join KetQua kq on sv.MASV = kq.MASV\n"
                    + "inner join HocPhan hp on kq.MAHP = hp.MAHP\n"
                    + "inner join MonHoc mh on hp.MAMH = mh.MAMH\n"
                    + "Where sv.MASV = '" + getMaSV() + "'";
            rs = connect.executeQuery(query);
            double tongDiem = 0;
            int tongTinChi = 0;
            while (rs.next()) {
                tongDiem = tongDiem + rs.getDouble("TONGDIEM");
                tongTinChi = tongTinChi + rs.getInt("SOTINCHI");
            }
            float trungBinhTichLuy = (float) tongDiem / tongTinChi;
            jlb_diemTrungBinhTichLuy.setText(trungBinhTichLuy + "");
            jlb_tongTinChi.setText(tongTinChi + "");
            jlb_xepLoai.setText(getRanking(trungBinhTichLuy));
        } catch (Exception ex) {
            Logger.getLogger(XemDiemTongKet.class.getName()).log(Level.SEVERE, "ERROR 1", ex);
        }
    }

    private static String getRanking(float point) {
        String result;
        if (point == 0.0) {
            return "";
        }
        if (point < 2.5) {
            result = "Trung Bình";
        } else if (point < 3.2) {
            result = "Khá";
        } else if (point < 3.5) {
            result = "Giỏi";
        } else {
            result = "Xuất Sắc";
        }
        return result;
    }

    private static String getDay(long day) {
        Date d;
        d = Date.from(Instant.ofEpochSecond(day));
        return sdf.format(d);
    }

    public void loadComboBox() {
        String[] name = {"Mã HP", "Mã MH", "Tên MH", "Số TC", "Điểm H4"};
        for (String b : name) {
            jcb_locDuLieu.addItem(b);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupmn_chiTiet = new javax.swing.JPopupMenu();
        jmn_chiTietMonHoc = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jlb_maSinhVien = new javax.swing.JLabel();
        jlb_hoTen = new javax.swing.JLabel();
        jlb_lop = new javax.swing.JLabel();
        jlb_giaoVienChuNhiem = new javax.swing.JLabel();
        jlb_diemTrungBinhTichLuy = new javax.swing.JLabel();
        jlb_tongTinChi = new javax.swing.JLabel();
        jlb_xepLoai = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jcb_locDuLieu = new javax.swing.JComboBox<String>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb_DSDiemTrungBinhTichLuy = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();

        jmn_chiTietMonHoc.setText("Chi Tiết");
        jmn_chiTietMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmn_chiTietMonHocActionPerformed(evt);
            }
        });
        jPopupmn_chiTiet.add(jmn_chiTietMonHoc);

        jPanel1.setBackground(new java.awt.Color(240, 243, 215));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Điểm Trung Bình Tích Lũy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(408, 408, 408)
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel1)
                                .addContainerGap(60, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(240, 243, 215));

        jLabel2.setText("Mã Sinh Viên : ");

        jLabel3.setText("Họ Tên : ");

        jLabel4.setText("Lớp : ");

        jLabel5.setText("Giáo Viên Chủ Nhiệm : ");

        jLabel6.setText("Điểm Trung Bình Tích Lũy :");

        jLabel7.setText("Tổng Tín Chỉ :");

        jLabel8.setText("Xếp Loại : ");

        jlb_maSinhVien.setText("jLabel9");

        jlb_hoTen.setText("jLabel10");

        jlb_lop.setText("jLabel11");

        jlb_giaoVienChuNhiem.setText("jLabel12");

        jlb_diemTrungBinhTichLuy.setText("jLabel13");

        jlb_tongTinChi.setText("jLabel14");

        jlb_xepLoai.setText("jLabel15");

        jLabel9.setText("Hiện Thị Theo : ");

        jcb_locDuLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_locDuLieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlb_xepLoai)
                                        .addComponent(jlb_tongTinChi)
                                        .addComponent(jlb_diemTrungBinhTichLuy)
                                        .addComponent(jlb_giaoVienChuNhiem)
                                        .addComponent(jlb_lop)
                                        .addComponent(jlb_hoTen)
                                        .addComponent(jlb_maSinhVien)
                                        .addComponent(jcb_locDuLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel8))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jlb_maSinhVien)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jlb_hoTen)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jlb_lop)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jlb_giaoVienChuNhiem)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jlb_diemTrungBinhTichLuy)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jlb_tongTinChi)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jlb_xepLoai)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addComponent(jcb_locDuLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(240, 243, 215));

        jtb_DSDiemTrungBinhTichLuy.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jtb_DSDiemTrungBinhTichLuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_DSDiemTrungBinhTichLuyMouseClicked(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jtb_DSDiemTrungBinhTichLuyMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(jtb_DSDiemTrungBinhTichLuy);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcb_locDuLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_locDuLieuActionPerformed
        // TODO add your handling code here:
        String nameFilter = "";
        if (String.valueOf(jcb_locDuLieu.getSelectedItem()) == "Tên MH") {
            nameFilter = "TENMH";
        }
        if (String.valueOf(jcb_locDuLieu.getSelectedItem()) == "Mã MH") {
            nameFilter = "mh.MAMH";
        }
        if (String.valueOf(jcb_locDuLieu.getSelectedItem()) == "Mã HP") {
            nameFilter = "hp.MAHP";
        }
        if (String.valueOf(jcb_locDuLieu.getSelectedItem()) == "Sô TC") {
            nameFilter = "SOTINCHI";
        }
        if (String.valueOf(jcb_locDuLieu.getSelectedItem()) == "Điểm H4") {
            nameFilter = "DIEM";
        }
        loadTable(nameFilter);
    }//GEN-LAST:event_jcb_locDuLieuActionPerformed

    private void jtb_DSDiemTrungBinhTichLuyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_DSDiemTrungBinhTichLuyMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_jtb_DSDiemTrungBinhTichLuyMouseExited

    private void jtb_DSDiemTrungBinhTichLuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_DSDiemTrungBinhTichLuyMouseClicked
        // TODO add your handling code here:
        jPopupmn_chiTiet.show(jtb_DSDiemTrungBinhTichLuy, evt.getX(), evt.getY());
    }//GEN-LAST:event_jtb_DSDiemTrungBinhTichLuyMouseClicked

    private void jmn_chiTietMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmn_chiTietMonHocActionPerformed
        // TODO add your handling code here:
        int rowIndexSelected = jtb_DSDiemTrungBinhTichLuy.getSelectedRow();
        String maMH = jtb_DSDiemTrungBinhTichLuy.getValueAt(rowIndexSelected, 2).toString();
        String maHP = jtb_DSDiemTrungBinhTichLuy.getValueAt(rowIndexSelected, 1).toString();
        ChiTietMonHoc chiTietMonHoc = new ChiTietMonHoc(maHP, maMH);
        chiTietMonHoc.show();
    }//GEN-LAST:event_jmn_chiTietMonHocActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupmn_chiTiet;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> jcb_locDuLieu;
    private javax.swing.JLabel jlb_diemTrungBinhTichLuy;
    private javax.swing.JLabel jlb_giaoVienChuNhiem;
    private javax.swing.JLabel jlb_hoTen;
    private javax.swing.JLabel jlb_lop;
    private javax.swing.JLabel jlb_maSinhVien;
    private javax.swing.JLabel jlb_tongTinChi;
    private javax.swing.JLabel jlb_xepLoai;
    private javax.swing.JMenuItem jmn_chiTietMonHoc;
    private javax.swing.JTable jtb_DSDiemTrungBinhTichLuy;
    // End of variables declaration//GEN-END:variables
}
