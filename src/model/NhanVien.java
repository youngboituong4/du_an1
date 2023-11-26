   /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class NhanVien {

    private String maNV, hoVaTen, matKhau, diaChi, email, sdt;
    private boolean gioiTinh, vaiTro, trangThai;

    public NhanVien() {
    }

    public NhanVien(String maNV, String hoVaTen, String matKhau, String diaChi, String email, String sdt, boolean gioiTinh, boolean vaiTro, boolean trangThai) {
        this.maNV = maNV;
        this.hoVaTen = hoVaTen;
        this.matKhau = matKhau;
        this.diaChi = diaChi;
        this.email = email;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNV=" + maNV + ", hoVaTen=" + hoVaTen + ", matKhau=" + matKhau + ", diaChi=" + diaChi + ", email=" + email + ", sdt=" + sdt + ", gioiTinh=" + gioiTinh + ", vaiTro=" + vaiTro + ", trangThai=" + trangThai + '}';
    }

    
    public Object[] toDataRow() {
        return new Object[]{this.maNV, this.hoVaTen, this.matKhau, this.diaChi, this.email, this.sdt, this.gioiTinh ? "Nam" : "Nữ", this.vaiTro ? "Quản lý" : "Nhân viên",this.trangThai ?"Đang làm" : "Nghỉ làm"};
    }
}
