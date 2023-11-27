
package model;


import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class KhuyenMai {

    private Integer ID;
    private String ma, tenKhuyenMai, trangThai;
    private Integer loaiKhuyenMai;
    private Double DonGiamToiThieu;
    private Date ngayBatDau, ngayKetThuc;
    private Double giaTri;

    public KhuyenMai() {
    }

    public KhuyenMai(Integer ID, String ma, String tenKhuyenMai, Integer loaiKhuyenMai, Double DonGiamToiThieu, Date ngayBatDau, Date ngayKetThuc, Double giaTri, String trangThai) {
        this.ID = ID;
        this.ma = ma;
        this.tenKhuyenMai = tenKhuyenMai;
        this.loaiKhuyenMai = loaiKhuyenMai;
        this.DonGiamToiThieu = DonGiamToiThieu;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaTri = giaTri;
        this.trangThai = trangThai;
    }

    public KhuyenMai(String ma, String tenKhuyenMai, Integer loaiKhuyenMai, Double DonGiamToiThieu, Date ngayBatDau, Date ngayKetThuc, Double giaTri, String trangThai) {
        this.ma = ma;
        this.tenKhuyenMai = tenKhuyenMai;
        this.loaiKhuyenMai = loaiKhuyenMai;
        this.DonGiamToiThieu = DonGiamToiThieu;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaTri = giaTri;
        this.trangThai = trangThai;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }


    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getLoaiKhuyenMai(Integer loaiKhuyenMai1) {
        return loaiKhuyenMai;
    }

    public void setLoaiKhuyenMai(Integer loaiKhuyenMai) {
        this.loaiKhuyenMai = loaiKhuyenMai;
    }

    public Double getDonGiamToiThieu() {
        return DonGiamToiThieu;
    }

    public void setDonGiamToiThieu(Double DonGiamToiThieu) {
        this.DonGiamToiThieu = DonGiamToiThieu;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Double giaTri) {
        this.giaTri = giaTri;
    }
    

    String getKhuyenMai(Integer loaiKhuyenMai){
        if (loaiKhuyenMai == 0) {
            return "Giảm Bằng %";
        }else {
            return "Giảm Bằng Tiền";
        }
    }
    

    public Object[] toDataRow() {  
        return new Object[]{this.ma, this.tenKhuyenMai, this.getKhuyenMai(loaiKhuyenMai), this.DonGiamToiThieu, this.ngayBatDau, this.ngayKetThuc, this.giaTri, this.trangThai};
    }

    

    
}
