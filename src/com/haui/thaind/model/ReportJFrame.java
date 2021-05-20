/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haui.thaind.model;

import com.haui.thaind.cache.UserManager;
import com.haui.thaind.common.Ranking;
import com.haui.thaind.dao.ReportDAO;
import com.haui.thaind.processor.ExportExcelProcessor;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author duyenthai
 */
public class ReportJFrame extends javax.swing.JFrame {

    /**
     * Creates new form ReportJFrame
     */
    public ReportJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnRoot = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jlbDeparment = new javax.swing.JLabel();
        jlbName = new javax.swing.JLabel();
        jlbRanking = new javax.swing.JLabel();
        jlbDob = new javax.swing.JLabel();
        jlbYearGrad = new javax.swing.JLabel();
        btnExport = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbResult = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbResultAlternative = new javax.swing.JTable();
        jlbCreditsTitle = new javax.swing.JLabel();
        jlbTotalCreditsTitile = new javax.swing.JLabel();
        jlbAvgPointsTitle = new javax.swing.JLabel();
        jlbFinalRankingTitle = new javax.swing.JLabel();
        jlbCredits = new javax.swing.JLabel();
        jlbTotalCredits = new javax.swing.JLabel();
        jlbAvgPoints = new javax.swing.JLabel();
        jlbFinalRanking = new javax.swing.JLabel();
        jtoolbar = new javax.swing.JToolBar();
        btnReloadToolBar = new javax.swing.JButton();
        btnExportToolBar = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("THÔNG TIN IN BẰNG");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("THÔNG TIN KẾT QUẢ HỌC TẬP");

        jLabel3.setText("Họ và tên");

        jLabel4.setText("Ngành học");

        jLabel5.setText("Xếp loại");

        jLabel6.setText("Ngày sinh");

        jLabel7.setText("Năm tốt nghiệp");

        btnExport.setText("Xuất thông tin bản in kết quả học tập");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel8.setText("- Phần kết quả học tập");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel9.setText("- Kết quả đánh giá quá trình học tập");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel10.setText("- Học phần thay thế đồ án khóa luận tốt nghiệp");

        jtbResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtbResult);

        jtbResultAlternative.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jtbResultAlternative);

        jlbCreditsTitle.setText("Tổng số tín chỉ tích lũy");

        jlbTotalCreditsTitile.setText("Tổng số TC tích lũy dùng để tính điểm TBCTL");

        jlbAvgPointsTitle.setText("Điểm TBCTL");

        jlbFinalRankingTitle.setText("Xếp hạng tốt nghiệp");

        jtoolbar.setRollover(true);

        btnReloadToolBar.setIcon(new javax.swing.ImageIcon("./src/com/haui/thaind/icons/outline_autorenew_black_24dp.png"));
        btnReloadToolBar.setText("Reload");
        btnReloadToolBar.setFocusable(false);
        btnReloadToolBar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReloadToolBar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReloadToolBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadToolBarActionPerformed(evt);
            }
        });
        jtoolbar.add(btnReloadToolBar);

        btnExportToolBar.setIcon(new javax.swing.ImageIcon("./src/com/haui/thaind/icons/baseline_import_export_black_24dp.png"));
        btnExportToolBar.setText("Export");
        btnExportToolBar.setFocusable(false);
        btnExportToolBar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExportToolBar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExportToolBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportToolBarActionPerformed(evt);
            }
        });
        jtoolbar.add(btnExportToolBar);

        btnBack.setIcon(new javax.swing.ImageIcon("./src/com/haui/thaind/icons/baseline_exit_to_app_black_24dp.png"));
        btnBack.setText("Back");
        btnBack.setFocusable(false);
        btnBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBack.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jtoolbar.add(btnBack);

        javax.swing.GroupLayout jpnRootLayout = new javax.swing.GroupLayout(jpnRoot);
        jpnRoot.setLayout(jpnRootLayout);
        jpnRootLayout.setHorizontalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnRootLayout.createSequentialGroup()
                        .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpnRootLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpnRootLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jpnRootLayout.createSequentialGroup()
                                            .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jlbDeparment, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jlbName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jlbRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jlbDob, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jlbYearGrad, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(jpnRootLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpnRootLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpnRootLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpnRootLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jlbTotalCreditsTitile, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlbCredits, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlbTotalCredits, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnRootLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpnRootLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpnRootLayout.createSequentialGroup()
                                        .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlbAvgPointsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jlbFinalRankingTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlbAvgPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jlbFinalRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jlbCreditsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtoolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 222, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnRootLayout.setVerticalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnRootLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnRootLayout.createSequentialGroup()
                        .addComponent(jlbDeparment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbDob, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbYearGrad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbCredits, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnRootLayout.createSequentialGroup()
                        .addComponent(jlbCreditsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnRootLayout.createSequentialGroup()
                                .addComponent(jlbTotalCreditsTitile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jlbAvgPointsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jlbFinalRankingTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnRootLayout.createSequentialGroup()
                                .addComponent(jlbTotalCredits, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jlbAvgPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jlbFinalRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtoolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnRoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public boolean load() {
//        UserManager.init("SV2");
        boolean result = false;
        try {
            String userID = UserManager.instance().getUserName();
            if (userID == null || userID.isEmpty()) {
                return false;
            }
            ReportDAO dao = new ReportDAO();
            if (dao.fetchResult()) {
                CustomTableResult tableResultMain = new CustomTableResult();
                tableResultMain.setDaoItems(dao.getListResults(), dao.getMapSubject());
                jtbResult.setModel(tableResultMain);
                jtbResultAlternative.setModel(new CustomTableResult());
                jlbDeparment.setText(dao.getDepartment().getTenKhoa());
                jlbName.setText(dao.getStudent().getTenSV());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    // long value is timestamp(second) -> convert to milliseconds
                    jlbDob.setText(sdf.format(new Date(dao.getStudent().getNgaySinh() * 1000)));
                } catch (Exception ex) {
                    System.err.println("Format date error, trace: " + ex);
                    ex.printStackTrace();
                }
                jlbRanking.setText(getRanking(dao.getAvgPoints()));
                jlbCredits.setText(String.valueOf(149));
                jlbTotalCredits.setText(dao.getTotalCredits() + "");
                jlbAvgPoints.setText(String.format("%.2f", dao.getAvgPoints()));
                jlbFinalRanking.setText(getRanking(dao.getAvgPoints()));
                setBorder();
                result = true;
                System.out.println("Load information ok!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Error after login, cannot specify userId");
            System.err.println("Error null pointer, no user specified, trace: " + ex);
            ex.printStackTrace();
        } finally {
            return result;
        }
    }


    private void setBorder(){
        Border allThickBorder = BorderFactory.createLineBorder(Color.BLACK);
        jlbCredits.setBorder(allThickBorder);
        jlbTotalCredits.setBorder(allThickBorder);
        jlbAvgPoints.setBorder(allThickBorder);
        jlbFinalRanking.setBorder(allThickBorder);
        jlbCreditsTitle.setBorder(allThickBorder);
        jlbTotalCreditsTitile.setBorder(allThickBorder);
        jlbAvgPointsTitle.setBorder(allThickBorder);
        jlbFinalRankingTitle.setBorder(allThickBorder);
    }

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Pub job export from main button, time: " + new Date());
        ExportExcelProcessor.pubjob();
    }

    private void btnReloadToolBarActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Process reload from toolbar, time: " + new Date());
        load();
    }

    private void btnExportToolBarActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Pub job export from toolbar, time: " + new Date());
        ExportExcelProcessor.pubjob();
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Dispose frame from toolbar, time: " + new Date());
        this.setVisible(false);
        this.dispose();
    }

    private static String getRanking(float point) {
        String result;
        if (point == 0.0) {
            return "";
        }
        if (point < 2.5) {
            result = Ranking.BELOW_AVERAGE.get();
        } else if (point < 3.2) {
            result = Ranking.AVERAGE.get();
        } else if (point < 3.5) {
            result = Ranking.GOOD.get();
        } else {
            result = Ranking.EXCELLENT.get();
        }
        return result;
    }

    private static String getPointA(float point) {
        String result;
        if (point == 0.0) {
            return "";
        }
        if (point < 1.5) {
            result = "D";
        } else if (point < 2.0) {
            result = "D+";
        } else if (point < 2.5) {
            result = "C";
        } else if (point < 3) {
            result = "C+";
        } else if (point < 3.5) {
            result = "B";
        } else if (point < 4) {
            result = "B+";
        } else {
            result = "A";
        }
        return result;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReportJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReportJFrame frame = new ReportJFrame();
                try {
                    if (frame.load()) {
                        frame.setVisible(true);
                    }
                } catch (Exception ex) {
                    System.out.println("set frame");
                    System.err.println("Error: " + ex);
                    ex.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnExportToolBar;
    private javax.swing.JButton btnReloadToolBar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlbAvgPoints;
    private javax.swing.JLabel jlbAvgPointsTitle;
    private javax.swing.JLabel jlbCredits;
    private javax.swing.JLabel jlbCreditsTitle;
    private javax.swing.JLabel jlbDeparment;
    private javax.swing.JLabel jlbDob;
    private javax.swing.JLabel jlbFinalRanking;
    private javax.swing.JLabel jlbFinalRankingTitle;
    private javax.swing.JLabel jlbName;
    private javax.swing.JLabel jlbRanking;
    private javax.swing.JLabel jlbTotalCredits;
    private javax.swing.JLabel jlbTotalCreditsTitile;
    private javax.swing.JLabel jlbYearGrad;
    private javax.swing.JPanel jpnRoot;
    private javax.swing.JTable jtbResult;
    private javax.swing.JTable jtbResultAlternative;
    private javax.swing.JToolBar jtoolbar;
    // End of variables declaration//GEN-END:variables
}
