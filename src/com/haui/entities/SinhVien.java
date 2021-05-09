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
public class SinhVien {

    private String maSV;
    private String tenSV;
    private long ngaySinh;
    private int gioiTinh;
    private String diaChi;
    private String email;
    private String matKhau;
    private String sdt;
    private String maLop;

    public SinhVien() {
    }

    public SinhVien(String maSV, String tenSV, String matKhau, String maLop) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.matKhau = matKhau;
        this.maLop = maLop;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public long getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(long ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    @Override
    public String toString() {
        return "SinhVien{" + "maSV=" + maSV + ", tenSV=" + tenSV + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", email=" + email + ", matKhau=" + matKhau + ", sdt=" + sdt + ", maLop=" + maLop + '}';
    }

}
