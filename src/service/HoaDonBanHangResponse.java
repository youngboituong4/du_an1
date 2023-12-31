/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author thinh
 */
public class HoaDonBanHangResponse {
    private Integer ID;
    private String maKhachHang,maNhanVien,maHoaDon;
    private Long ngayTao, ngayThanhToan;
    private Double tienKhachTra, tienKhachChuyenKhoan,tienThua, tienGiamGia, thanhTien;
    private Integer trangThai, hinhThucThanhToan;

    public HoaDonBanHangResponse() {
    }

    public HoaDonBanHangResponse(Integer ID, String maKhachHang, String maNhanVien, String maHoaDon, Long ngayTao, Long ngayThanhToan, Double tienKhachTra, Double tienKhachChuyenKhoan, Double tienThua, Double tienGiamGia, Double thanhTien, Integer trangThai, Integer hinhThucThanhToan) {
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
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public HoaDonBanHangResponse(Integer ID, String maKhachHang, String maNhanVien, String maHoaDon, Long ngayTao, Long ngayThanhToan, Double tienKhachTra, Double tienKhachChuyenKhoan, Double tienThua, Double tienGiamGia, Double thanhTien, Integer trangThai) {
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

    public HoaDonBanHangResponse(Integer ID, String maHoaDon, Long ngayTao, String maNhanVien, Integer trangThai) {
        this.ID = ID;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.maNhanVien = maNhanVien;        
        this.trangThai = trangThai;
    }
    
    public HoaDonBanHangResponse(Integer ID, String maHoaDon, Long ngayTao, String maNhanVien, Integer trangThai, String maKhachHang) {
        this.ID = ID;
        this.maHoaDon = maHoaDon;
        this.maKhachHang = maKhachHang;
        this.ngayTao = ngayTao;
        this.maNhanVien = maNhanVien;        
        this.trangThai = trangThai;
    }
    
    public HoaDonBanHangResponse(String maNhanVien, Integer trangThai) {
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

    public Long getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Long ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Long getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Long ngayThanhToan) {
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        System.out.println(sdf.format(this.ngayTao));
        return new Object[]{this.ID, this.maHoaDon, sdf.format(this.ngayTao), this.maNhanVien, this.trangThaiTT(trangThai)?"Đã thanh toán":"Chờ thanh toán"};
    }
    
}
