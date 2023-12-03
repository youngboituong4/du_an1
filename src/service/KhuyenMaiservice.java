/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.KhuyenMai;

/**
 *
 * @author LENOVO
 */
public class KhuyenMaiservice {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<KhuyenMai> getAllKM() {
        sql = "select Ma, TenKhuyenMai, LoaiKhuyenMai, DonGiaToiThieu, NgayBatDau, NgayKetThuc, GiaTri, TrangThai from KhuyenMai";
        List<KhuyenMai> lst = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDate(5), rs.getDate(6), rs.getDouble(7), rs.getString(8));
                lst.add(km);
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<KhuyenMai> getKM(String TrangThai) {
        ArrayList<KhuyenMai> lst = new ArrayList<>();
        sql = "select Ma, TenKhuyenMai, LoaiKhuyenMai, DonGiaToiThieu, NgayBatDau, NgayKetThuc, GiaTri, TrangThai from KhuyenMai where TrangThai = ?";
        if (TrangThai == "Tất Cả") {
            TrangThai = "";
            sql = "select Ma, TenKhuyenMai, LoaiKhuyenMai, DonGiaToiThieu, NgayBatDau, NgayKetThuc, GiaTri, TrangThai from KhuyenMai where TrangThai <>?";
        }

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, TrangThai);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDate(5), rs.getDate(6), rs.getDouble(7), rs.getString(8));
                lst.add(km);
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<KhuyenMai> timKM(String MaKM) {
        List<KhuyenMai> lst = new ArrayList<>();
        sql = "select Ma, TenKhuyenMai, LoaiKhuyenMai, DonGiaToiThieu, NgayBatDau, NgayKetThuc, GiaTri, TrangThai from KhuyenMai where Ma LIke ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, MaKM);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDate(5), rs.getDate(6), rs.getDouble(7), rs.getString(8));
                lst.add(km);
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addKM(KhuyenMai km) {
        sql = "INSERT INTO KhuyenMai (Ma, TenKhuyenMai, LoaiKhuyenMai, DonGiaToiThieu, NgayBatDau, NgayKetThuc, GiaTri, TrangThai) VALUES (?,?,?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, km.getMa());
            ps.setObject(2, km.getTenKhuyenMai());
            ps.setObject(3, km.getLoaiKhuyenMai(Integer.SIZE));
            ps.setObject(4, km.getDonGiamToiThieu());
            ps.setObject(5, km.getNgayBatDau());
            ps.setObject(6, km.getNgayKetThuc());
            ps.setObject(7, km.getGiaTri());
            ps.setObject(8, km.getTrangThai());
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public KhuyenMai FindKM(String MaKM) {
        sql = "select Ma, TenKhuyenMai, LoaiKhuyenMai, DonGiaToiThieu, NgayBatDau, NgayKetThuc, GiaTri, TrangThai from KhuyenMai where Ma = ?";
        KhuyenMai km = null;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, MaKM);
            rs = ps.executeQuery();
            while (rs.next()) {
                km = new KhuyenMai(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDate(5), rs.getDate(6), rs.getDouble(7), rs.getString(8));

            }
            return km;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int updateKM(String MaKM, KhuyenMai km) {
        sql = "Update KhuyenMai set TenKhuyenMai = ?,LoaiKhuyenMai = ?,DonGiaToiThieu = ?,NgayBatDau = ?,NgayKetThuc = ?,GiaTri = ?,TrangThai = ? Where Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, km.getTenKhuyenMai());
            ps.setObject(2, km.getLoaiKhuyenMai(Integer.SIZE));
            ps.setObject(3, km.getDonGiamToiThieu());
            ps.setObject(4, km.getNgayBatDau());
            ps.setObject(5, km.getNgayKetThuc());
            ps.setObject(6, km.getGiaTri());
            ps.setObject(7, km.getTrangThai());
            ps.setObject(8, MaKM);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int HuyKM(String ma, String trangThai) {
        sql = "Update KhuyenMai set TrangThai = ? where Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangThai);
            ps.setObject(2, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public List<KhuyenMai> PhanTrang( int page) {
        sql = "select Ma, TenKhuyenMai, LoaiKhuyenMai, DonGiaToiThieu, NgayBatDau, NgayKetThuc, GiaTri, TrangThai from KhuyenMai \n"
                + "order by Ma\n"
                + "offset ? rows\n"
                + "fetch next 5 rows only";
        List<KhuyenMai> lst = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, page);
            rs = ps.executeQuery();
            while (rs.next()) {
               KhuyenMai km = new KhuyenMai(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDate(5), rs.getDate(6), rs.getDouble(7), rs.getString(8));
                lst.add(km);
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
}
