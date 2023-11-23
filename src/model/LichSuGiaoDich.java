/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Dell
 */
public class LichSuGiaoDich {

    private String tenKH, maHD, trangthaiHD;
    private Date ngayGD;
    private float tongtien;

    public LichSuGiaoDich() {
    }

    public LichSuGiaoDich(String tenKH, String maHD, String trangthaiHD, Date ngayGD, float tongtien) {
        this.tenKH = tenKH;
        this.maHD = maHD;
        this.trangthaiHD = trangthaiHD;
        this.ngayGD = ngayGD;
        this.tongtien = tongtien;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTrangthaiHD() {
        return trangthaiHD;
    }

    public void setTrangthaiHD(String trangthaiHD) {
        this.trangthaiHD = trangthaiHD;
    }

    public Date getNgayGD() {
        return ngayGD;
    }

    public void setNgayGD(Date ngayGD) {
        this.ngayGD = ngayGD;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

}
