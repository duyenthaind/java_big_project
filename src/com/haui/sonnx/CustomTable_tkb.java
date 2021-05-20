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
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class CustomTable_tkb extends AbstractTableModel {

    Connection connect = null;
    HocPhan_DAO hp_dao = new HocPhan_DAO();
    private List<HocPhan> list = null;
    private Map<String, String> map = null;
    private static final String name[] = {"Thu 2", "Thu 3", "Thu 4", "Thu 5", "Thu 6", "Thu 7", "Chu nhat"};

    public CustomTable_tkb() {
        try {
            Config.loadConfig();
            connect = DriverManager.getConnection(Config.DB_URL, Config.DB_USER_NAME, Config.DB_PASSWORD);
        } catch (Exception e) {
            System.out.println("Loi ket noi " + e);
        }
    }

    public void getMap() {
        map = hp_dao.getThoiKhoaBieu(connect);
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return map.get("Thu 2");
            case 1:
                return map.get("Thu 3");

            case 2:
                return map.get("Thu 4");

            case 3:
                return map.get("Thu 5");
            case 4:
                return map.get("Thu 6");
            case 5:
                return map.get("Thu 7");
            case 6:
                return map.get("Chu nhat");
            default:

                return "";

        }
    }

    @Override
    public String getColumnName(int column) {
        return name[column];
    }

}
