/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import model.KhachHang;
import model.LichSuGiaoDich;

/**
 *
 * @author Admin
 */
public class KhachHangService {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public ArrayList<KhachHang> getAll() {
        ArrayList<KhachHang> list = new ArrayList<>();
        sql = "select * from KhachHang";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public int add(KhachHang kh) {
        sql = """
                   INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SDT, Email, DiaChi, TrangThai)
                   VALUES (?, ?, ?, ?, ?, ?, ?)
                   """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, kh.getMa());
            ps.setString(2, kh.getTen());
            ps.setString(3, kh.getGioitinh());
            ps.setString(4, kh.getSdt());
            ps.setString(5, kh.getEmail());
            ps.setString(6, kh.getDiachi());
            ps.setString(7, kh.getTrangthai());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public KhachHang getMaKH(String ma) {
        KhachHang kh = null;
        sql = "select * from KhachHang where MaKH = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
            return kh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean update(KhachHang kh) {
        int check = 0;
        sql = "UPDATE KhachHang SET TrangThai = ? WHERE MaKH = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, kh.getTrangthai());
            ps.setObject(2, kh.getMa());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return check > 0;
    }

    public ArrayList<KhachHang> tim(String tim, int page, String gioiTinhString, String trangTthai) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = """
                     SELECT * FROM KhachHang 
                     WHERE (MaKH LIKE ? OR TenKH LIKE ? OR
                                          SDT LIKE ? or Email LIKE ? OR 
                                          DiaChi LIKE ?) AND
                     GioiTinh LIKE ? AND TrangThai LIKE ?
                     ORDER BY MaKH
                     OFFSET ? ROW FETCH NEXT 2 ROW ONLY
                     """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, "%" + tim + "%");
            ps.setObject(2, "%" + tim + "%");
            ps.setObject(3, "%" + tim + "%");
            ps.setObject(4, "%" + tim + "%");
            ps.setObject(5, "%" + tim + "%");
            ps.setObject(6, "%" + gioiTinhString + "%");
            ps.setObject(7, "%" + trangTthai + "%");
            ps.setObject(8, page * 2);
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

    public ArrayList<KhachHang> countSearch(String tim, String gioiTinh, String trangTthai) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = """
                    SELECT * FROM KhachHang 
                                          WHERE (MaKH LIKE ? OR TenKH LIKE ? OR
                                                               SDT LIKE ? or Email LIKE ? OR 
                                                               DiaChi LIKE ?) AND
                                          GioiTinh LIKE ? AND TrangThai LIKE ?
                     """;
        // A B C D E F
        // 0: A B
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, "%" + tim + "%");
            ps.setObject(2, "%" + tim + "%");
            ps.setObject(3, "%" + tim + "%");
            ps.setObject(4, "%" + tim + "%");
            ps.setObject(5, "%" + tim + "%");
            ps.setObject(6, "%" + gioiTinh + "%");
            ps.setObject(7, "%" + trangTthai + "%");
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

    public ArrayList<LichSuGiaoDich> getAllLSGD(String maKH) {
        ArrayList<LichSuGiaoDich> list = new ArrayList<>();
        String sql = "SELECT TenKH, MaHD, NgayGD, TongTien, TrangThaiHD FROM LichSuGiaoDich\n"
                + "WHERE MaKH = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            // Đặt giá trị cho tham số MaKH
            ps.setString(1, maKH);

            rs = ps.executeQuery();

            while (rs.next()) {
                String ten = rs.getString("TenKH");
                String mahd = rs.getString("MaHD");
                Date ngaygd = rs.getDate("NgayGD");
                Float tongtien = rs.getFloat("TongTien");
                String tt = rs.getString("TrangThaiHD");

                LichSuGiaoDich lsu = new LichSuGiaoDich(ten, mahd, tt, ngaygd, tongtien);
                list.add(lsu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên (result set, statement, connection) trong khối finally
            // để đảm bảo chúng được đóng ngay cả khi có lỗi xảy ra.
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
        return list;
    }

}
