package com.haui.thaind.model;

import com.haui.entities.KetQua;
import com.haui.entities.MonHoc;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CustomTableResult extends AbstractTableModel {

    private final String columnNames[] = {"STT", "Tên HP", "Số TC", "Điểm"};

    private final List<TableItem> listItem = new ArrayList<>();

    public void setDaoItems(List<KetQua> list, Map<String, MonHoc> map) {
        listItem.clear();
        Iterator<KetQua> iterator = list.iterator();
        int counter = -1;
        while (iterator.hasNext()) {
            KetQua result = iterator.next();
            TableItem item = new TableItem();
            item.setPosition(++counter);
            if (map.get(result.getMaHP()) != null) {
                MonHoc sub = map.get(result.getMaHP());
                item.setCredit(sub.getSoTinChi());
            }
            item.setMark(validMark(result.getDiem()));
            listItem.add(item);
        }
    }

    @Override
    public int getRowCount() {
        return listItem.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listItem.get(rowIndex).getPosition();
            case 1:
                return listItem.get(rowIndex).getSubjectName();
            case 2:
                return listItem.get(rowIndex).getCredit();
            case 3:
                return listItem.get(rowIndex).getMark();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    private String validMark(float point) {
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
}

class TableItem {
    private int position;
    private String subjectName;
    private int credit;
    private String mark;

    public TableItem() {
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}