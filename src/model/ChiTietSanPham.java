/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class ChiTietSanPham {

    private Integer ID;
    private String maSP;
    private String tenSP, maLoai, maKichThuoc, maMauSac, maChatLieu, maThuongHieu;
    private Double gia;
    private Integer soLuong;
    private boolean trangThai;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(int ID, String maSP, String tenSP, String maLoai, String maKichThuoc, String maMauSac, String maChatLieu, String maThuongHieu, double gia, int soLuong, boolean trangThai) {
        this.ID = ID;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLoai = maLoai;
        this.maKichThuoc = maKichThuoc;
        this.maMauSac = maMauSac;
        this.maChatLieu = maChatLieu;
        this.maThuongHieu = maThuongHieu;
        this.gia = gia;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }
    
    public ChiTietSanPham(int ID,String maSP, String tenSP, double gia, int soLuong) {
        this.ID = ID;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.gia = gia;
        this.soLuong = soLuong;       
    }
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getMaKichThuoc() {
        return maKichThuoc;
    }

    public void setMaKichThuoc(String maKichThuoc) {
        this.maKichThuoc = maKichThuoc;
    }

    public String getMaMauSac() {
        return maMauSac;
    }

    public void setMaMauSac(String maMauSac) {
        this.maMauSac = maMauSac;
    }

    public String getMaChatLieu() {
        return maChatLieu;
    }

    public void setMaChatLieu(String maChatLieu) {
        this.maChatLieu = maChatLieu;
    }

    public String getMaThuongHieu() {
        return maThuongHieu;
    }

    public void setMaThuongHieu(String maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public boolean trangThai(int soLuong) {
        if(soLuong > 0){
            return true;
        } else {
            return false;
        }
    }

    
    public Object[] toDataRowCTSP(){
        return new Object[]{this.maSP, this.tenSP, this.maLoai, this.maKichThuoc, this.maMauSac, this.maChatLieu, this.maThuongHieu, this.gia, this.soLuong, this.trangThai(soLuong)?"Còn hàng":"Hết hàng"};
    }
       
    public Object[] toDataRowCTSPBanHang(){
        return new Object[]{this.ID, this.maSP, this.tenSP, this.maLoai, this.maKichThuoc, this.maMauSac, this.maChatLieu, this.maThuongHieu, this.gia, this.soLuong};
    }
    
    public Object[] toDataRowSP(){
        return new Object[]{this.maSP, this.tenSP, this.trangThai(soLuong)?"Còn hàng":"Hết hàng"};
    }
    
}
