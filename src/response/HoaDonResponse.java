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
public class HoaDonResponse {

    private String maHD, maNV, tenKH;
    private Float thanhTien, GiamGiaKM;
    private int trangThai, HinhThucThanhToan;
    private Date ngayTao, ngayThanhToan;

    public HoaDonResponse() {
    }

    public HoaDonResponse(String maHD, String maNV, String tenKH, Float thanhTien, Float GiamGiaKM, int trangThai, int HinhThucThanhToan, Date ngayTao, Date ngayThanhToan) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.tenKH = tenKH;
        this.thanhTien = thanhTien;
        this.GiamGiaKM = GiamGiaKM;
        this.trangThai = trangThai;
        this.HinhThucThanhToan = HinhThucThanhToan;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
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

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Float getGiamGiaKM() {
        return GiamGiaKM;
    }

    public void setGiamGiaKM(Float GiamGiaKM) {
        this.GiamGiaKM = GiamGiaKM;
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

    public int getHinhThucThanhToan() {
        return HinhThucThanhToan;
    }

    public void setHinhThucThanhToan(int HinhThucThanhToan) {
        this.HinhThucThanhToan = HinhThucThanhToan;
    }

}
