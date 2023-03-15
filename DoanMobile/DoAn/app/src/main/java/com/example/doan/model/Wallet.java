package com.example.doan.model;

public class Wallet {
    int Maxe;
    String TenXe;
    String hinhanh;
    long GiaTien;
    int soluong;

    public Wallet() {
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getMaxe() {
        return Maxe;
    }

    public void setMaxe(int maxe) {
        Maxe = maxe;
    }

    public String getTenXe() {
        return TenXe;
    }

    public void setTenXe(String tenXe) {
        TenXe = tenXe;
    }

    public long getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(long giaTien) {
        GiaTien = giaTien;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
