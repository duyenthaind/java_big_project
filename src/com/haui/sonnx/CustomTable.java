/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haui.sonnx;

import com.haui.common.Config;
import com.haui.entities.HocPhan;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class CustomTable extends AbstractTableModel {

    Connection connect = null;
    private List<HocPhan> list = null;
    private Map<String, String> map = null;
    HocPhan_DAO hp = new HocPhan_DAO();
    private static final String name[] = {"Ma hoc phan", "Ten hoc phan", "Thoi gian hoc", "Hoc phi"};

    public CustomTable() {
        try {
            Config.loadConfig();
            connect = DriverManager.getConnection(Config.DB_URL, Config.DB_USER_NAME, Config.DB_PASSWORD);
        } catch (Exception e) {
            System.out.println("Loi ket noi " + e);
        }
    }

    public void setGiaTriChuaDangKy(){
        list = hp.getAll(connect);
        map = hp.getTenMH(connect);
    }

    public void setGiaTriDaDangKy() {
        list = hp.getSubcribed(connect);
        map = hp.getTenMH(connect);
    }
    public void timkiem(HocPhan hocphan){
        list = new ArrayList<HocPhan>();
        list.add(hocphan);
        map=hp.getTenMH(connect);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getMaHP();
            case 1:
                return map.get(list.get(rowIndex).getMaHP());
            case 2:
                return list.get(rowIndex).getThoiGianHoc();
            case 3:
                return list.get(rowIndex).getHocPhi();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return name[column];
    }

    
  
}
