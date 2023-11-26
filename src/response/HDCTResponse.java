/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

/**
 *
 * @author Dell
 */
public class HDCTResponse {

    private String maSP, tenSP, hang, mau, size;
    private int SoLuong;
    private Float DonGia, GiamGiaKM, TongTien;

    public HDCTResponse() {
    }

    public HDCTResponse(String maSP, String tenSP, String hang, String mau, String size, int SoLuong, Float DonGia, Float GiamGiaKM, Float TongTien) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.hang = hang;
        this.mau = mau;
        this.size = size;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.GiamGiaKM = GiamGiaKM;
        this.TongTien = TongTien;
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

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public Float getDonGia() {
        return DonGia;
    }

    public void setDonGia(Float DonGia) {
        this.DonGia = DonGia;
    }

    public Float getGiamGiaKM() {
        return GiamGiaKM;
    }

    public void setGiamGiaKM(Float GiamGiaKM) {
        this.GiamGiaKM = GiamGiaKM;
    }

    public Float getTongTien() {
        return TongTien;
    }

    public void setTongTien(Float TongTien) {
        this.TongTien = TongTien;
    }

}
