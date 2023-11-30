/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author thinh
 */
public class HoaDon {
    private Integer ID;
    private String maKhachHang,maNhanVien,maHoaDon;
    private Date ngayTao, ngayThanhToan;
    private Double tienKhachTra, tienKhachChuyenKhoan,tienThua, tienGiamGia, thanhTien;
    private Integer trangThai;

    public HoaDon() {
    }

    public HoaDon(Integer ID, String maKhachHang, String maNhanVien, String maHoaDon, Date ngayTao, Date ngayThanhToan, Double tienKhachTra, Double tienKhachChuyenKhoan, Double tienThua, Double tienGiamGia, Double thanhTien, Integer trangThai) {
        this.ID = ID;
        this.maKhachHang = maKhachHang;
        this.maNhanVien = maNhanVien;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tienKhachTra = tienKhachTra;
        this.tienKhachChuyenKhoan = tienKhachChuyenKhoan;
        this.tienThua = tienThua;
        this.tienGiamGia = tienGiamGia;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
    }

    public HoaDon(Integer ID, String maHoaDon, Date ngayTao, String maNhanVien, Integer trangThai) {
        this.ID = ID;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.maNhanVien = maNhanVien;        
        this.trangThai = trangThai;
    }
    
    public HoaDon(String maNhanVien, Integer trangThai) {
        this.maNhanVien = maNhanVien;        
        this.trangThai = trangThai;
    }
    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public Double getTienKhachTra() {
        return tienKhachTra;
    }

    public void setTienKhachTra(Double tienKhachTra) {
        this.tienKhachTra = tienKhachTra;
    }

    public Double getTienKhachChuyenKhoan() {
        return tienKhachChuyenKhoan;
    }

    public void setTienKhachChuyenKhoan(Double tienKhachChuyenKhoan) {
        this.tienKhachChuyenKhoan = tienKhachChuyenKhoan;
    }

    public Double getTienThua() {
        return tienThua;
    }

    public void setTienThua(Double tienThua) {
        this.tienThua = tienThua;
    }

    public Double getTienGiamGia() {
        return tienGiamGia;
    }

    public void setTienGiamGia(Double tienGiamGia) {
        this.tienGiamGia = tienGiamGia;
    }

    public Double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
    
    public boolean trangThaiTT(Integer trangThai){
        if(trangThai == 0){
            return false;
        }
        else if (trangThai == 1){
            return true;
        }
        return false;
    }

    public Object[] toDataRowHoaDon() {
        return new Object[]{this.ID, this.maHoaDon, this.ngayTao, this.maNhanVien, this.trangThaiTT(trangThai)?"Đã thanh toán":"Chờ thanh toán"};
    }
    
}
