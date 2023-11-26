/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ChiTietSanPham;
import model.HoaDon;
import model.HoaDonChiTiet;

/**
 *
 * @author thinh
 */
public class BanHangService {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> list = new ArrayList<>();
        sql = "SELECT ID, MaHoaDon, NgayTao, MaNhanVien, TrangThai FROM HOADON";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon> LocHoaDon(int trangThai) {
        List<HoaDon> list = new ArrayList<>();
        sql = "SELECT ID, MaHoaDon, NgayTao, MaNhanVien, TrangThai FROM HOADON WHERE TrangThai = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangThai);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon> LocHoaDon1(int id) {
        List<HoaDon> list = new ArrayList<>();
        sql = "SELECT ID, MaHoaDon, NgayTao, MaNhanVien, TrangThai FROM HOADON WHERE ID = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon> getAllShowHoaDon() {
        List<HoaDon> list = new ArrayList<>();
        sql = "SELECT * FROM HOADON";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10), rs.getDouble(11), rs.getInt(12));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ChiTietSanPham> getAllCTSP() {
        List<ChiTietSanPham> list = new ArrayList<>();
        sql = "SELECT * FROM ChiTietSanPham WHERE SoLuong > 0";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9), rs.getInt(10), rs.getBoolean(11));
                list.add(ctsp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addHoaDon(HoaDon hd) {
        sql = "INSERT INTO HoaDon (MaKhachHang, MaNhanVien, NgayTao, NgayThanhToan, TienKhachTra, TienKhachChuyenKhoan, TienThua, TienGiamGia, ThanhTien, TrangThai) VALUES \n"
                + "       ('', ?, GETDATE(), '', 0, 0, 0, 0, 0, 0)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hd.getMaNhanVien());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteGioHang(int id) {
        sql = "DELETE FROM HoaDonChiTiet WHERE ID = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<HoaDonChiTiet> getAllGioHang(int maHD) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        sql = "SELECT * FROM HoaDonChiTiet WHERE IDHoaDon = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHD);
            rs = ps.executeQuery();

            while (rs.next()) {
                HoaDonChiTiet ctsp = new HoaDonChiTiet(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5));
                list.add(ctsp);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addGioHang(int idsp, int sl, int idhd) {
        sql = "-- Lấy giá và số lượng từ bảng SanPham\n"
                + "DECLARE @Gia DECIMAL(18, 2);\n"
                + "DECLARE @SoLuongTrongSanPham INT;\n"
                + "SELECT @Gia = Gia, @SoLuongTrongSanPham = SoLuong FROM ChiTietSanPham WHERE ID = ?;\n"
                + "\n"
                + "\n"
                + "-- Thêm sản phẩm vào giỏ hàng và cập nhật số lượng trong bảng SanPham\n"
                + "MERGE INTO HoaDonChiTiet AS target\n"
                + "USING (SELECT ? AS HoaDonID, ? AS SanPhamID, ? AS SoLuong, @Gia AS Gia) AS source\n"
                + "ON target.IDChiTietSanPham = source.SanPhamID AND target.IDHoaDon = source.HoaDonID\n"
                + "WHEN MATCHED THEN\n"
                + "    UPDATE SET target.SoLuong = target.SoLuong + source.SoLuong\n"
                + "WHEN NOT MATCHED THEN\n"
                + "    INSERT (IDHoaDon, IDChiTietSanPham, SoLuong, DonGia)\n"
                + "    VALUES (source.HoaDonID, source.SanPhamID, source.SoLuong, source.Gia);\n"
                + "\n"
                + "-- Trừ số lượng sản phẩm từ bảng SanPham nếu sản phẩm chưa có trong giỏ hàng\n"
                + "UPDATE ChiTietSanPham\n"
                + "SET SoLuong = CASE\n"
                + "                WHEN SoLuong >= ? THEN SoLuong - ?\n"
                + "                ELSE SoLuong\n"
                + "             END\n"
                + "WHERE ID = ?;";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, idsp);
            ps.setObject(2, idhd);
            ps.setObject(3, idsp);
            ps.setObject(4, sl);
            ps.setObject(5, sl);
            ps.setObject(6, sl);
            ps.setObject(7, idsp);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateHoaDon(Double tienTra, Double tienCK, Double tienThua, int id) {
        sql = "UPDATE HOADON SET TrangThai = 1, TienKhachTra = ?, TienKhachChuyenKhoan = ?, TienThua = ? WHERE ID = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tienTra);
            ps.setObject(2, tienCK);
            ps.setObject(3, tienThua);
            ps.setObject(4, id);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
