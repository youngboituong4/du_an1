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

    private int stt;
    private String ma;
    private String ten, loai, kt, ms, cl;
    private double gia;
    private int sl;
    private boolean trangThai;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(int stt, String ma, String ten, String loai, String kt, String ms, String cl, double gia, int sl, boolean trangThai) {
        this.stt = stt;
        this.ma = ma;
        this.ten = ten;
        this.loai = loai;
        this.kt = kt;
        this.ms = ms;
        this.cl = cl;
        this.gia = gia;
        this.sl = sl;
        this.trangThai = trangThai;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getKt() {
        return kt;
    }

    public void setKt(String kt) {
        this.kt = kt;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }


    public boolean trangThai(int sl) {
        if(sl > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "SanPham{" + "ma=" + ma + ", ten=" + ten + ", loai=" + loai + ", kt=" + kt + ", ms=" + ms + ", cl=" + cl + ", gia=" + gia + ", sl=" + sl + ", trangThai=" + trangThai + '}';
    }
    
    public Object[] toDataRowCTSP(){
        return new Object[]{this.ma, this.ten, this.loai, this.kt, this.ms, this.cl, this.gia, this.sl, this.trangThai(sl)?"Còn hàng":"Hết hàng"};
    }
       
    public Object[] toDataRowSP(){
        return new Object[]{this.ma, this.ten, this.trangThai(sl)?"Còn hàng":"Hết hàng"};
    }
    
}
