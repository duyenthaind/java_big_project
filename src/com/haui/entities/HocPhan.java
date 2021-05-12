/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haui.entities;

/**
 * @author duyenthai
 */
public class HocPhan {

    private String maHP;
    private String maMH;
    private String maGV;
    private long ngayBD;
    private long ngayKT;
    private int soTietHoc;
    private int tietBD;

    public HocPhan() {
    }

    public HocPhan(String maHP, String maMH, String maGV) {
        this.maHP = maHP;
        this.maMH = maMH;
        this.maGV = maGV;
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

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public long getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(long ngayBD) {
        this.ngayBD = ngayBD;
    }

    public long getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(long ngayKT) {
        this.ngayKT = ngayKT;
    }

    public int getSoTietHoc() {
        return soTietHoc;
    }

    public void setSoTietHoc(int soTietHoc) {
        this.soTietHoc = soTietHoc;
    }

    public int getTietBD() {
        return tietBD;
    }

    public void setTietBD(int tietBD) {
        this.tietBD = tietBD;
    }

    @Override
    public String toString() {
        return "HocPhan{" + "maHP=" + maHP + ", maMH=" + maMH + ", maGV=" + maGV + ", ngayBD=" + ngayBD + ", ngayKT=" + ngayKT + ", soTietHoc=" + soTietHoc + ", tietBD=" + tietBD + '}';
    }
}
