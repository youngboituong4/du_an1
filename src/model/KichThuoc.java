/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thinh
 */
public class KichThuoc {

    private int stt;
    private String maKichThuoc;
    private String tenKichThuoc;

    public KichThuoc() {
    }

    public KichThuoc(int stt, String maKichThuoc, String tenKichThuoc) {
        this.stt = stt;
        this.maKichThuoc = maKichThuoc;
        this.tenKichThuoc = tenKichThuoc;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getMaKichThuoc() {
        return maKichThuoc;
    }

    public void setMaKichThuoc(String maKichThuoc) {
        this.maKichThuoc = maKichThuoc;
    }

    public String getTenKichThuoc() {
        return tenKichThuoc;
    }

    public void setTenKichThuoc(String tenKichThuoc) {
        this.tenKichThuoc = tenKichThuoc;
    }

    @Override
    public String toString() {
        return "KichThuoc{" + "stt=" + stt + ", maKichThuoc=" + maKichThuoc + ", tenKichThuoc=" + tenKichThuoc + '}';
    }

    public Object[] toDataRow() {
        return new Object[]{this.stt, this.maKichThuoc, this.tenKichThuoc};
    }
}
