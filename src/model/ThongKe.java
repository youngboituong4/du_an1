/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class ThongKe {
    public String masp, tensp, loaisp, kichthuoc, tenmau, chatlieu, thuonghieu;
    public Double gia;
    public int soluongdaban;

    public ThongKe() {
    }

    public ThongKe(String masp, String tensp, String loaisp, String kichthuoc, String tenmau, String chatlieu, String thuonghieu, Double gia, int soluongdaban) {
        this.masp = masp;
        this.tensp = tensp;
        this.loaisp = loaisp;
        this.kichthuoc = kichthuoc;
        this.tenmau = tenmau;
        this.chatlieu = chatlieu;
        this.thuonghieu = thuonghieu;
        this.gia = gia;
        this.soluongdaban = soluongdaban;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getLoaisp() {
        return loaisp;
    }

    public void setLoaisp(String loaisp) {
        this.loaisp = loaisp;
    }

    public String getKichthuoc() {
        return kichthuoc;
    }

    public void setKichthuoc(String kichthuoc) {
        this.kichthuoc = kichthuoc;
    }

    public String getTenmau() {
        return tenmau;
    }

    public void setTenmau(String tenmau) {
        this.tenmau = tenmau;
    }

    public String getChatlieu() {
        return chatlieu;
    }

    public void setChatlieu(String chatlieu) {
        this.chatlieu = chatlieu;
    }

    public String getThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(String thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public int getSoluongdaban() {
        return soluongdaban;
    }

    public void setSoluongdaban(int soluongdaban) {
        this.soluongdaban = soluongdaban;
    }

    @Override
    public String toString() {
        return "ThongKe{" + "masp=" + masp + ", tensp=" + tensp + ", loaisp=" + loaisp + ", kichthuoc=" + kichthuoc + ", tenmau=" + tenmau + ", chatlieu=" + chatlieu + ", thuonghieu=" + thuonghieu + ", gia=" + gia + ", soluongdaban=" + soluongdaban + '}';
    }
    
    public Object[] toData(){
        return new Object[]{
            this.masp,
            this.tensp,
            this.loaisp,
            this.kichthuoc,
            this.tenmau,
            this.chatlieu,
            this.thuonghieu,
            this.gia,
            this.soluongdaban
           
        };
    }
}