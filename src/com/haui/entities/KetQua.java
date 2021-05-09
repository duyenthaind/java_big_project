/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haui.entities;

/**
 *
 * @author duyenthai
 */
public class KetQua {

    private String maSV;
    private String maHP;
    private double diem;

    public KetQua() {
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

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    @Override
    public String toString() {
        return "KetQua{" + "maSV=" + maSV + ", maHP=" + maHP + ", diem=" + diem + '}';
    }

}
