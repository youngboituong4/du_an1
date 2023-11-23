/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thinh
 */
public class SanPham {
    private String maSP;
    private String tenSP;
    private String moTa;

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP, String moTa) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.moTa = moTa;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    public Object[] toDataRowSP(){
        return new Object[]{this.maSP, this.tenSP, this.moTa};
    }
}
