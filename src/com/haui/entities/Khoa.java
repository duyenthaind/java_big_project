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
public class Khoa {

    private String maKhoa;
    private String tenKhoa;
    private long ngayTL;

    public Khoa() {
    }

    public Khoa(String maKhoa, String tenKhoa, long ngayTL) {
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.ngayTL = ngayTL;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public long getNgayTL() {
        return ngayTL;
    }

    public void setNgayTL(long ngayTL) {
        this.ngayTL = ngayTL;
    }

    @Override
    public String toString() {
        return "Khoa{" + "maKhoa=" + maKhoa + ", tenKhoa=" + tenKhoa + ", ngayTL=" + ngayTL + '}';
    }

}
