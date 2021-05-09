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
public class Lop {

    private String maLop;
    private String tenLop;
    private String maKhoa;
    private String maGV;

    public Lop() {
    }

    public Lop(String maLop, String maKhoa, String maGV) {
        this.maLop = maLop;
        this.maKhoa = maKhoa;
        this.maGV = maGV;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    @Override
    public String toString() {
        return "Lop{" + "maLop=" + maLop + ", tenLop=" + tenLop + ", maKhoa=" + maKhoa + ", maGV=" + maGV + '}';
    }

}
