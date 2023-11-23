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

    private String maLoaiSanPham;
    private String loaiSanPham;

    public LoaiSanPham() {
    }

    public LoaiSanPham(String maLoaiSanPham, String loaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
        this.loaiSanPham = loaiSanPham;
    }

    public String getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(String maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }


    public Object[] toDataRow() {
        return new Object[]{this.maLoaiSanPham, this.loaiSanPham};
    }
}
