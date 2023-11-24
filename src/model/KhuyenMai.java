/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class KhuyenMai {

    private Integer ID;
    private String ma, tenKhuyenMai;
    private Integer loaiKhuyenMai;
    private Double DonGiamToiThieu;
    private Date ngayBatDau, ngayKetThuc;
    private Double giaTri;

    public KhuyenMai() {
    }

    public KhuyenMai(Integer ID, String ma, String tenKhuyenMai, Integer loaiKhuyenMai, Double DonGiamToiThieu, Date ngayBatDau, Date ngayKetThuc, Double giaTri) {
        this.ID = ID;
        this.ma = ma;
        this.tenKhuyenMai = tenKhuyenMai;
        this.loaiKhuyenMai = loaiKhuyenMai;
        this.DonGiamToiThieu = DonGiamToiThieu;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaTri = giaTri;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public Integer getLoaiKhuyenMai() {
        return loaiKhuyenMai;
    }

    public void setLoaiKhuyenMai(Integer loaiKhuyenMai) {
        this.loaiKhuyenMai = loaiKhuyenMai;
    }

    public Double getDonGiamToiThieu() {
        return DonGiamToiThieu;
    }

    public void setDonGiamToiThieu(Double DonGiamToiThieu) {
        this.DonGiamToiThieu = DonGiamToiThieu;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Double giaTri) {
        this.giaTri = giaTri;
    }

    public Object[] toDataRow() {
        return new Object[]{this.ma, this.tenKhuyenMai, this.loaiKhuyenMai, this.DonGiamToiThieu, this.ngayBatDau, this.ngayKetThuc, this.giaTri};
    }
}
