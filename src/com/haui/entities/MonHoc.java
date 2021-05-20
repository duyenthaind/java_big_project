/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haui.entities;

import java.util.Objects;

/**
 * @author duyenthai
 */
public class MonHoc {

    private String maMH;
    private String tenMH;
    private int soTinChi;
    private String maKhoa;

    public MonHoc() {
    }

    public MonHoc(String maMH, String maKhoa) {
        this.maMH = maMH;
        this.maKhoa = maKhoa;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    @Override
    public String toString() {
        return "MonHoc{" + "maMH=" + maMH + ", tenMH=" + tenMH + ", soTinChi=" + soTinChi + ", maKhoa=" + maKhoa + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonHoc monHoc = (MonHoc) o;
        return maMH.equals(monHoc.maMH);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maMH);
    }
}
