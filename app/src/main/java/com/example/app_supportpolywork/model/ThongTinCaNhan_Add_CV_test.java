package com.example.app_supportpolywork.model;

import java.io.Serializable;

public class ThongTinCaNhan_Add_CV_test implements Serializable {
    private String ten;
    private String nghe;
    private String goiTinh;
    private String diaChi;
    private String email;
    private int sdt;
    private String namSinh;
    private int imgUser;

    public ThongTinCaNhan_Add_CV_test(String ten, String nghe, String goiTinh, String diaChi, String email, int sdt, String namSinh, int imgUser) {
        this.ten = ten;
        this.nghe = nghe;
        this.goiTinh = goiTinh;
        this.diaChi = diaChi;
        this.email = email;
        this.sdt = sdt;
        this.namSinh = namSinh;
        this.imgUser = imgUser;
    }
    public ThongTinCaNhan_Add_CV_test(String ten, String nghe, String goiTinh, String diaChi, String email, int sdt, String namSinh) {
        this.ten = ten;
        this.nghe = nghe;
        this.goiTinh = goiTinh;
        this.diaChi = diaChi;
        this.email = email;
        this.sdt = sdt;
        this.namSinh = namSinh;

    }

//    public int getImgUser() {
//        return imgUser;
//    }
//
//    public void setImgUser(int imgUser) {
//        this.imgUser = imgUser;
//    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNghe() {
        return nghe;
    }

    public void setNghe(String nghe) {
        this.nghe = nghe;
    }

    public String getGoiTinh() {
        return goiTinh;
    }

    public void setGoiTinh(String goiTinh) {
        this.goiTinh = goiTinh;
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

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
