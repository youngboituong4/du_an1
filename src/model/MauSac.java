/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thinh
 */
public class MauSac {

    private int stt;
    private String maMau;
    private String tenMau;

    public MauSac() {
    }

    public MauSac(int stt, String maMau, String tenMau) {
        this.stt = stt;
        this.maMau = maMau;
        this.tenMau = tenMau;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getMaMau() {
        return maMau;
    }

    public void setMaMau(String maMau) {
        this.maMau = maMau;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    @Override
    public String toString() {
        return "MauSac{" + "stt=" + stt + ", maMau=" + maMau + ", tenMau=" + tenMau + '}';
    }

    public Object[] toDataRow() {
        return new Object[]{this.stt, this.maMau, this.tenMau};
    }
}
