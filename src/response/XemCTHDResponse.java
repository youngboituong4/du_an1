/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

import java.util.Date;

/**
 *
 * @author Dell
 */
public class XemCTHDResponse {

    private String maHD, maNV, tenNV, tenKH, diaChi, sdt;
    private Date ngayTao, ngayThanhToan;
    private Float tongTien, tienkhachCK, tienKhachTra, tienThua, tienGiam;
    private int HTthanhtoan, trangThai;

    public XemCTHDResponse() {
    }

    public XemCTHDResponse(String maHD, String maNV, String tenNV, String tenKH, String diaChi, String sdt, Date ngayTao, Date ngayThanhToan, Float tongTien, Float tienkhachCK, Float tienKhachTra, Float tienThua, Float tienGiam, int HTthanhtoan, int trangThai) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tongTien = tongTien;
        this.tienkhachCK = tienkhachCK;
        this.tienKhachTra = tienKhachTra;
        this.tienThua = tienThua;
        this.tienGiam = tienGiam;
        this.HTthanhtoan = HTthanhtoan;
        this.trangThai = trangThai;
    }

    

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    
    

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
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

    public Float getTongTien() {
        return tongTien;
    }

    public void setTongTien(Float tongTien) {
        this.tongTien = tongTien;
    }

    public Float getTienkhachCK() {
        return tienkhachCK;
    }

    public void setTienkhachCK(Float tienkhachCK) {
        this.tienkhachCK = tienkhachCK;
    }

    public Float getTienKhachTra() {
        return tienKhachTra;
    }

    public void setTienKhachTra(Float tienKhachTra) {
        this.tienKhachTra = tienKhachTra;
    }

    public Float getTienThua() {
        return tienThua;
    }

    public void setTienThua(Float tienThua) {
        this.tienThua = tienThua;
    }

    public Float getTienGiam() {
        return tienGiam;
    }

    public void setTienGiam(Float tienGiam) {
        this.tienGiam = tienGiam;
    }

    public int getHTthanhtoan() {
        return HTthanhtoan;
    }

    public void setHTthanhtoan(int HTthanhtoan) {
        this.HTthanhtoan = HTthanhtoan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
