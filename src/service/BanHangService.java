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
import model.ChatLieu;
import model.ChiTietSanPham;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.KichThuoc;
import model.LoaiSanPham;
import model.MauSac;
import model.ThuongHieu;

/**
 *
 * @author thinh
 */
public class BanHangService {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<MauSac> getAllMau() {
        List<MauSac> list = new ArrayList<>();
        sql = "SELECT Ma, TenMau FROM MauSac";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                MauSac ms = new MauSac(rs.getString(1), rs.getString(2));
                list.add(ms);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KichThuoc> getAllKT() {
        List<KichThuoc> list = new ArrayList<>();
        sql = "SELECT Ma, KichThuoc FROM KichThuoc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                KichThuoc kt = new KichThuoc(rs.getString(1), rs.getString(2));
                list.add(kt);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ChatLieu> getAllCL() {
        List<ChatLieu> list = new ArrayList<>();
        sql = "SELECT Ma, ChatLieu FROM ChatLieu";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ChatLieu cl = new ChatLieu(rs.getString(1), rs.getString(2));
                list.add(cl);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<LoaiSanPham> getAllLSP() {
        List<LoaiSanPham> list = new ArrayList<>();
        sql = "SELECT Ma, LoaiSanPham FROM LoaiSanPham";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham(rs.getString(1), rs.getString(2));
                list.add(lsp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ThuongHieu> getAllTH() {
        List<ThuongHieu> list = new ArrayList<>();
        sql = "SELECT Ma, TenThuongHieu FROM ThuongHieu";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ThuongHieu th = new ThuongHieu(rs.getString(1), rs.getString(2));
                list.add(th);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

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

    public HoaDon getHoaDonTheoMa(int id) {
        HoaDon hd = null;
        sql = "SELECT ID, MaHoaDon, NgayTao, MaNhanVien, TrangThai FROM HOADON WHERE ID = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                hd = new HoaDon(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5));
            }
            return hd;
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

    public HoaDon showLocHoaDon(int trangThai, int id) {
        HoaDon hd = null;
        sql = "SELECT ID, MaHoaDon, NgayTao, MaNhanVien, TrangThai FROM HOADON WHERE TrangThai = ? AND ID = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangThai);
            ps.setObject(2, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                hd = new HoaDon(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5));
            }
            return hd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon> getAllShowHoaDon() {
        List<HoaDon> list = new ArrayList<>();
        sql = "SELECT * FROM HoaDon";
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

    public HoaDon getHoaDonTheoID(int id) {
        HoaDon hd = null;
        sql = "SELECT ID, MaKhachHang, MaNhanVien, MaHoaDon, NgayTao, NgayThanhToan, TienKhachTra, TienKhachChuyenKhoan, TienThua, TienGiamGia, ThanhTien, TrangThai FROM HoaDon WHERE ID = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                hd = new HoaDon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10), rs.getDouble(11), rs.getInt(12));
            }
            return hd;
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

    public ChiTietSanPham readFormCTSP(int id) {
        ChiTietSanPham ctsp = null;
        sql = "SELECT * FROM ChiTietSanPham WHERE SoLuong > 0 AND ID = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                ctsp = new ChiTietSanPham(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9), rs.getInt(10), rs.getBoolean(11));
            }
            return ctsp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addHoaDon(HoaDon hd, int idKM) {
        sql = "INSERT INTO HoaDon (MaKhachHang, MaNhanVien, IdKM, NgayTao, NgayThanhToan, TienKhachTra, TienKhachChuyenKhoan, TienThua, TienGiamGia, ThanhTien, HinhThucThanhToan, TrangThai) VALUES \n"
                + "       ('', ?, ?, GETDATE(), '', 0, 0, 0, 0, 0, 0, 0)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hd.getMaNhanVien());
            ps.setObject(2, idKM);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteGioHang(int idsp, int idhd) {
        sql = "-- Lấy số lượng sản phẩm trong giỏ hàng\n"
                + "DECLARE @SoLuongTrongGioHang INT;\n"
                + "SELECT @SoLuongTrongGioHang = SoLuong\n"
                + "FROM HoaDonChiTiet\n"
                + "WHERE IDHoaDon = ? AND IDChiTietSanPham = ?;\n"
                + "\n"
                + "-- Xóa sản phẩm khỏi giỏ hàng\n"
                + "DELETE FROM HoaDonChiTiet\n"
                + "WHERE IDHoaDon = ? AND IDChiTietSanPham = ?;\n"
                + "\n"
                + "-- Cập nhật số lượng sản phẩm trong bảng SanPham\n"
                + "UPDATE ChiTietSanPham\n"
                + "SET SoLuong = SoLuong + @SoLuongTrongGioHang\n"
                + "WHERE ID = ?;";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, idhd);
            ps.setObject(2, idsp);
            ps.setObject(3, idhd);
            ps.setObject(4, idsp);
            ps.setObject(5, idsp);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteAllGioHang(int idsp, int idhd) {
        sql = "-- Lấy số lượng sản phẩm trong giỏ hàng\n"
                + "DECLARE @SoLuongTrongGioHang INT;\n"
                + "SELECT @SoLuongTrongGioHang = SoLuong\n"
                + "FROM HoaDonChiTiet\n"
                + "WHERE IDHoaDon = ? AND IDChiTietSanPham = ?;\n"
                + "\n"
                + "-- Xóa sản phẩm khỏi giỏ hàng\n"
                + "DELETE FROM HoaDonChiTiet\n"
                + "WHERE IDHoaDon = ?;\n"
                + "\n"
                + "-- Cập nhật số lượng sản phẩm trong bảng SanPham\n"
                + "UPDATE ChiTietSanPham\n"
                + "SET SoLuong = SoLuong + @SoLuongTrongGioHang\n"
                + "WHERE ID = ?;";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, idhd);
            ps.setObject(2, idsp);
            ps.setObject(3, idhd);
            ps.setObject(4, idsp);

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

    public int updateHoaDon(Double tienTra, Double tienCK, Double tienThua, Double thanhTien, int id, String makh) {
        sql = "UPDATE HOADON SET TrangThai = 1, TienKhachTra = ?, TienKhachChuyenKhoan = ?, TienThua = ?, NgayThanhToan = GETDATE(), ThanhTien = ?, MaKhachHang = ? WHERE ID = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tienTra);
            ps.setObject(2, tienCK);
            ps.setObject(3, tienThua);
            ps.setObject(4, thanhTien);
            ps.setObject(5, makh);
            ps.setObject(6, id);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int xoaHetGioHang(int id) {
        sql = "DELETE FROM HoaDonChiTiet WHERE IDHoaDon = ?";
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

    public int updateSoLuong(int idsp, int sl, int idhd) {
        sql = "DECLARE @SoLuongCu INT;\n"
                + "SELECT @SoLuongCu = SoLuong FROM HoaDonChiTiet WHERE IDHoaDon = ? AND IDChiTietSanPham = ?;\n"
                + "\n"
                + "-- Cập nhật số lượng trong bảng GioHang\n"
                + "UPDATE HoaDonChiTiet\n"
                + "SET SoLuong = ?\n"
                + "WHERE IDHoaDon = ? AND IDChiTietSanPham = ?;\n"
                + "\n"
                + "-- Cập nhật số lượng trong bảng SanPham\n"
                + "UPDATE ChiTietSanPham\n"
                + "SET SoLuong = SoLuong - (? - @SoLuongCu)\n"
                + "WHERE ID = ?;";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, idhd);
            ps.setObject(2, idsp);
            ps.setObject(3, sl);
            ps.setObject(4, idhd);
            ps.setObject(5, idsp);
            ps.setObject(6, sl);
            ps.setObject(7, idsp);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<KhachHang> tim(String tim) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = """
                     SELECT * FROM KhachHang 
                     WHERE (MaKH LIKE ? OR TenKH LIKE ? OR
                                          SDT LIKE ? or Email LIKE ? OR 
                                          DiaChi LIKE ?) 
                     ORDER BY MaKH
                     """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, "%" + tim + "%");
            ps.setObject(2, "%" + tim + "%");
            ps.setObject(3, "%" + tim + "%");
            ps.setObject(4, "%" + tim + "%");
            ps.setObject(5, "%" + tim + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ChiTietSanPham> LocSanPham(String lsp, String kt, String mau, String cl, String th) {
        List<ChiTietSanPham> list = new ArrayList<>();
        sql = "SELECT ID, MaSP, TenSP, LoaiSanPham, KichThuoc, TenMau, ChatLieu, ThuongHieu, Gia, SoLuong, TrangThai\n"
                + "                                  FROM ChiTietSanPham\n"
                + "                        WHERE ( ? IS NULL OR ? LIKE '' OR\n"
                + "                        LoaiSanPham LIKE ?) AND ( ? IS NULL OR ? LIKE '' OR KichThuoc LIKE ?) AND ( ? IS NULL OR ? LIKE ''  OR TenMau LIKE ?) AND (? IS NULL OR ? LIKE '' OR ChatLieu LIKE ?) AND (? IS NULL OR ? LIKE '' OR ThuongHieu LIKE ?) AND\n"
                + "                        SoLuong > 0";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + lsp + "%");
            ps.setObject(2, "%" + lsp + "%");
            ps.setObject(3, "%" + lsp + "%");
            ps.setObject(4, "%" + kt + "%");
            ps.setObject(5, "%" + kt + "%");
            ps.setObject(6, "%" + kt + "%");
            ps.setObject(7, "%" + mau + "%");
            ps.setObject(8, "%" + mau + "%");
            ps.setObject(9, "%" + mau + "%");
            ps.setObject(10, "%" + cl + "%");
            ps.setObject(11, "%" + cl + "%");
            ps.setObject(12, "%" + cl + "%");
            ps.setObject(13, "%" + th + "%");
            ps.setObject(14, "%" + th + "%");
            ps.setObject(15, "%" + th + "%");
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

    public List<ChiTietSanPham> LocSanPhamLoai(String lsp) {
        List<ChiTietSanPham> list = new ArrayList<>();
        sql = "SELECT ID, MaSP, TenSP, LoaiSanPham, KichThuoc, TenMau, ChatLieu, ThuongHieu, Gia, SoLuong, TrangThai\n"
                + "                                  FROM ChiTietSanPham\n"
                + "                        WHERE ( ? IS NULL OR ? LIKE '' OR\n"
                + "                        LoaiSanPham LIKE ?) AND\n"
                + "                        SoLuong > 0";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + lsp + "%");
            ps.setObject(2, "%" + lsp + "%");
            ps.setObject(3, "%" + lsp + "%");

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

}
