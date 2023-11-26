/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thinh
 */
public class HoaDonChiTiet {
    private Integer ID;
    private Integer IDHoaDon;
    private Integer IDChiTietSanPham;
    private Double donGia;
    private Integer soLuong;
    
    public HoaDonChiTiet() {
}

    public HoaDonChiTiet(Integer ID, Integer IDHoaDon, Integer IDChiTietSanPham, Double donGia, Integer soLuong) {
        this.ID = ID;
        this.IDHoaDon = IDHoaDon;
        this.IDChiTietSanPham = IDChiTietSanPham;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }
    
    public HoaDonChiTiet(Integer ID, Integer IDHoaDon, Integer IDChiTietSanPham) {
        this.ID = ID;
        this.IDHoaDon = IDHoaDon;
        this.IDChiTietSanPham = IDChiTietSanPham;
    }
//ThinDo
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIDHoaDon() {
        return IDHoaDon;
    }

    public void setIDHoaDon(Integer IDHoaDon) {
        this.IDHoaDon = IDHoaDon;
    }

    public Integer getIDChiTietSanPham() {
        return IDChiTietSanPham;
    }

    public void setIDChiTietSanPham(Integer IDChiTietSanPham) {
        this.IDChiTietSanPham = IDChiTietSanPham;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double tongTien(Double donGia, Integer soLuong){
        Double tongTien = donGia * soLuong;
        return tongTien;
    }
    
    public Object[] toDataRow(){
        return new Object[]{this.ID, this.IDHoaDon, this.IDChiTietSanPham, this.donGia, this.soLuong, tongTien(donGia, soLuong)};
    }
}
