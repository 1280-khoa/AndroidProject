package com.example.doan.model;

import java.io.Serializable;

public class ChuyenXePhoBien implements Serializable {
    int Maxe;
    int MaHangXe;
    String TenXe;
    String hinhanh;
    String BienSoXe;
    String GioGiac;
    String Loai;
    long GiaTien;
    String DiaDiem;
    String NgayDi;
    int soluong;

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public long getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(long giaTien) {
        GiaTien = giaTien;
    }

    public String getNgaiDi() {
        return NgayDi;
    }

    public void setNgaiDi(String ngaiDi) {
        NgayDi = ngaiDi;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public String getGioGiac() {
        return GioGiac;
    }

    public void setGioGiac(String gioGiac) {
        GioGiac = gioGiac;
    }

    public String getDiaDiem() {
        return DiaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        DiaDiem = diaDiem;
    }

    public int getMaxe() {
        return Maxe;
    }

    public void setMaxe(int maxe) {
        Maxe = maxe;
    }

    public int getMaHangXe() {
        return MaHangXe;
    }

    public void setMaHangXe(int maHangXe) {
        MaHangXe = maHangXe;
    }

    public String getTenXe() {
        return TenXe;
    }

    public void setTenXe(String tenXe) {
        TenXe = tenXe;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getBienSoXe() {
        return BienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        BienSoXe = bienSoXe;
    }








}
