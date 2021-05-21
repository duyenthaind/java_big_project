/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haui.thangnd.XemKetQuaHocTap;

import com.haui.entities.HocPhan;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author TAVPLTNDTSUN
 */
public class TableChiTietMonHoc extends AbstractTableModel{
    private String[] name = {"STT", "Mã HP", "Mã MH", "Mã GV", "NGAY BD", "Ngày KT", "Số Tiết Học", "Thời Gian Học", "Học Phí"};
    private Class [] classed = {Integer.class,String.class,String.class,String.class,String.class,String.class,Integer.class,String.class,Float.class};
    private ArrayList<HocPhan> al_hocPhan = new ArrayList<HocPhan>();
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public TableChiTietMonHoc(ArrayList<HocPhan> al_hPhans) {
        this.al_hocPhan = al_hPhans;
    }
    
    @Override
    public int getRowCount() {
        return  al_hocPhan.size(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return name.length; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex , int columnIndex) {
        switch(columnIndex)
        {
            case 0:
                return rowIndex + 1;
            case 1:
                return al_hocPhan.get(rowIndex).getMaHP();
            case 2:
                return al_hocPhan.get(rowIndex).getMaMH();
            case 3:
                return al_hocPhan.get(rowIndex).getMaGV();
            case 4:
                return getDay(al_hocPhan.get(rowIndex).getNgayBD());
            case 5:
                return getDay(al_hocPhan.get(rowIndex).getNgayKT());
            case 6:
                return al_hocPhan.get(rowIndex).getSoTietHoc();
            case 7:
                return al_hocPhan.get(rowIndex).getThoiGianHoc();
            case 8:
                return al_hocPhan.get(rowIndex).getHocPhi();
            default:
                return null;
        } //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classed[columnIndex]; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnName(int columnIndex) {
        return name[columnIndex]; //To change body of generated methods, choose Tools | Templates.
    }
    
    private static String getDay(long day)
    {
        Date d;
        d = Date.from(Instant.ofEpochSecond(day));
        return sdf.format(d);
    }
    
}

