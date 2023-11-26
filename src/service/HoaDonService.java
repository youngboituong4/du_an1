/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import model.HoaDon;
import response.HDCTResponse;
import response.HoaDonResponse;

/**
 *
 * @author Dell
 */
public class HoaDonService {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public ArrayList<HoaDonResponse> getAll() {
        ArrayList<HoaDonResponse> list = new ArrayList<>();
        String sql = """
                 SELECT HD.MaHoaDon, HD.NgayTao, HD.NgayThanhToan, HD.ThanhTien, HD.MaNhanVien, KH.TenKH, HD.TrangThai
                 FROM HoaDon HD
                 LEFT JOIN KhachHang KH
                 ON HD.MaKhachHang = KH.MaKH
                 """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonResponse hd = new HoaDonResponse(rs.getString("MaHoaDon"), rs.getString("MaNhanVien"), rs.getString("TenKH"), rs.getFloat("ThanhTien"), rs.getInt("TrangThai"), rs.getDate("NgayTao"), rs.getDate("NgayThanhToan"));

                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return null;
    }

    public ArrayList<HDCTResponse> getAllHDCT(String MaHD) {
        ArrayList<HDCTResponse> list = new ArrayList<>();
        String sql = """
                SELECT SPCT.MaSP, SPCT.TenSP, SPCT.ThuongHieu, SPCT.TenMau, SPCT.KichThuoc, 
                 HDCT.SoLuong, HDCT.DonGia, HD.TienGiamGia, HD.ThanhTien
                 FROM HoaDonChiTiet HDCT
                 JOIN ChiTietSanPham SPCT
                 ON HDCT.IDChiTietSanPham = SPCT.ID
                 JOIN HoaDon HD
                 ON HD.ID = HDCT.IDHoaDon
                 WHERE HD.MaHoaDon = ?
                """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            // Thiết lập giá trị tham số
            ps.setString(1, MaHD);

            rs = ps.executeQuery();

            while (rs.next()) {
                HDCTResponse hdct = new HDCTResponse(
                        rs.getString("MaSP"), rs.getString("TenSP"), rs.getString("MaThuongHieu"),
                        rs.getString("MaMauSac"), rs.getString("MaKichThuoc"), rs.getInt("SoLuong"),
                        rs.getFloat("DonGia"), rs.getFloat("TienGiamGia"), rs.getFloat("ThanhTien"));

                list.add(hdct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng tài nguyên
            closeResources();
        }
        return null;
    }

// Hàm hỗ trợ để đóng tài nguyên
    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
                    
     */
    public ArrayList<HoaDonResponse> loc(String tim, int page, Integer trangthaiHD, Date ngayBatDau, Date ngayKetThuc) {
        ArrayList<HoaDonResponse> list = new ArrayList<>();
        String sql = """
                 SELECT HD.MaHoaDon, HD.NgayTao, HD.NgayThanhToan, HD.ThanhTien, HD.MaNhanVien, KH.TenKH, HD.TrangThai
                                  FROM HoaDon HD
                                  LEFT JOIN KhachHang KH
                                  ON HD.MaKhachHang = KH.MaKH
                        WHERE (? IS NULL OR ? LIKE '' OR 
                        HD.MaHoaDon LIKE ? OR HD.ThanhTien LIKE ? OR HD.MaNhanVien LIKE ? OR KH.TenKH LIKE ?) AND
                        (? IS NULL OR ? IS NULL OR HD.NgayTao BETWEEN ? AND ? OR HD.NgayThanhToan BETWEEN ? AND ?) AND
                        (? IS NULL OR HD.TrangThai = ?)
                 ORDER BY HD.MaHoaDon
                 OFFSET ? ROW FETCH NEXT 2 ROW ONLY
                 """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, tim);
            ps.setObject(2, tim);
            ps.setObject(3, "%" + tim + "%");
            ps.setObject(4, "%" + tim + "%");
            ps.setObject(5, "%" + tim + "%");
            ps.setObject(6, "%" + tim + "%");
            ps.setObject(7, ngayBatDau);
            ps.setObject(8, ngayKetThuc);
            ps.setObject(9, ngayBatDau);
            ps.setObject(10, ngayKetThuc);
            ps.setObject(11, ngayBatDau);
            ps.setObject(12, ngayKetThuc);
            ps.setObject(13, trangthaiHD);
            ps.setObject(14, trangthaiHD);
            ps.setObject(15, page * 2);

            rs = ps.executeQuery();

            while (rs.next()) {
                HoaDonResponse hd = new HoaDonResponse(rs.getString("MaHoaDon"), rs.getString("MaNhanVien"), rs.getString("TenKH"), rs.getFloat("ThanhTien"), rs.getInt("TrangThai"), rs.getDate("NgayTao"), rs.getDate("NgayThanhToan"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng tài nguyên
            closeResources();
        }
        return list;
    }

    public ArrayList<HoaDonResponse> countSearch(String tim, Integer trangthaiHD, Date ngayBatDau, Date ngayKetThuc) {
        ArrayList<HoaDonResponse> list = new ArrayList<>();
        String sql = """
                    SELECT HD.MaHoaDon, HD.NgayTao, HD.NgayThanhToan, HD.ThanhTien, HD.MaNhanVien, KH.TenKH, HD.TrangThai
                                                      FROM HoaDon HD
                                                      JOIN KhachHang KH
                                                      ON HD.MaKhachHang = KH.MaKH
                                            WHERE (? IS NULL OR ? LIKE '' OR 
                                            HD.MaHoaDon LIKE ? OR HD.ThanhTien LIKE ? OR HD.MaNhanVien LIKE ? OR KH.TenKH LIKE ?) AND
                                            (? IS NULL OR ? IS NULL OR HD.NgayTao BETWEEN ? AND ? OR HD.NgayThanhToan BETWEEN ? AND ?) AND
                                            (? IS NULL OR HD.TrangThai = ?)
                    """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, tim);
            ps.setObject(2, tim);
            ps.setObject(3, "%" + tim + "%");
            ps.setObject(4, "%" + tim + "%");
            ps.setObject(5, "%" + tim + "%");
            ps.setObject(6, "%" + tim + "%");
            ps.setObject(7, ngayBatDau);
            ps.setObject(8, ngayKetThuc);
            ps.setObject(9, ngayBatDau);
            ps.setObject(10, ngayKetThuc);
            ps.setObject(11, ngayBatDau);
            ps.setObject(12, ngayKetThuc);
            ps.setObject(13, trangthaiHD);
            ps.setObject(14, trangthaiHD);

            rs = ps.executeQuery();

            while (rs.next()) {
                HoaDonResponse hd = new HoaDonResponse(rs.getString("MaHoaDon"), rs.getString("MaNhanVien"), rs.getString("TenKH"), rs.getFloat("ThanhTien"), rs.getInt("TrangThai"), rs.getDate("NgayTao"), rs.getDate("NgayThanhToan"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng tài nguyên 123:))
            closeResources();
        }
        return list;
    }

}
