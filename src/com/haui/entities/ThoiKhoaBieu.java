package com.haui.entities;

/**
 * @author duyenthai
 */

public class ThoiKhoaBieu {
    private String maSV;
    private String maHP;
    private String maMH;

    public ThoiKhoaBieu() {
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getMaHP() {
        return maHP;
    }

    public void setMaHP(String maHP) {
        this.maHP = maHP;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    @Override
    public String toString() {
        return "ThoiKhoaBieu{" + "maSV='" + maSV + ", maHP='" + maHP + ", maMH='" + maMH + '}';
    }
}
