/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thinh
 */
public class LoaiSanPham {

    private int stt;
    private String maSP;
    private String tenSP;

    public LoaiSanPham() {
    }

    public LoaiSanPham(int stt, String maSP, String tenSP) {
        this.stt = stt;
        this.maSP = maSP;
        this.tenSP = tenSP;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
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

    @Override
    public String toString() {
        return "LoaiSanPham{" + "stt=" + stt + ", maSP=" + maSP + ", tenSP=" + tenSP + '}';
    }

    public Object[] toDataRow() {
        return new Object[]{this.stt, this.maSP, this.tenSP};
    }
}
