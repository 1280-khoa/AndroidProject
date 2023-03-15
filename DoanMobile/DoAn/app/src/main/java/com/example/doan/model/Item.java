package com.example.doan.model;

public class Item {
    int Maxe;
    int MaHangXe;
    String TenXe;
    String hinhanh;
    String BienSoXe;
    String GioGiac;
    String Loai;
    String DiaDiem;
    String NgayDi;
    int idsoluong;

    public int getMaxe() {
        return Maxe;
    }

    public int getIdsoluong() {
        return idsoluong;
    }

    public void setIdsoluong(int idsoluong) {
        this.idsoluong = idsoluong;
    }

    public String getNgayDi() {
        return NgayDi;
    }

    public void setNgayDi(String ngayDi) {
        NgayDi = ngayDi;
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

    public String getGioGiac() {
        return GioGiac;
    }

    public void setGioGiac(String gioGiac) {
        GioGiac = gioGiac;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public String getDiaDiem() {
        return DiaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        DiaDiem = diaDiem;
    }
}
