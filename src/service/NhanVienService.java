/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;

/**
 *
 * @author Admin
 */
public class NhanVienService {

    ResultSet rs = null;
    PreparedStatement ps = null;
    Connection con = null;
    String sql = null;

    public List<NhanVien> getAll() {
        sql = "Select MaNV, HoVaTen, MatKhau,DiaChi,Email,SDT,GioiTinh,VaiTro from NhanVien";
        List<NhanVien> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int AddNV(NhanVien nv) {
        sql = "insert into NhanVien(MaNV,HoVaTen,MatKhau,DiaChi,Email,SDT,GioiTinh,VaiTro) values (?,?,?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getMaNV());
            ps.setObject(2, nv.getHoVaTen());
            ps.setObject(3, nv.getMatKhau());
            ps.setObject(4, nv.getDiaChi());
            ps.setObject(5, nv.getEmail());
            ps.setObject(6, nv.getsdt());
            ps.setObject(7, nv.isVaiTro());
            ps.setObject(8, nv.isGioiTinh());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public NhanVien getNV(String MaNV) {
        sql = "SELECT MaNV,HoVaTen,MatKhau,DiaChi,Email,SDT,GioiTinh,VaiTro from NhanVien where MaNV = ?";
        NhanVien nv = null;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, MaNV);
            rs = ps.executeQuery();
            while (rs.next()) {
                nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8));
            }
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NhanVien> FindNV(int MaNV) {
        List<NhanVien> list = new ArrayList<>();
        sql = "SELECT MaNV,HoVaTen,MatKhau,DiaChi,Email,SDT,GioiTinh,VaiTro FROM NhanVien WHERE MaNV = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, MaNV);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int deleteNV(String MaNV) {
        sql = "DELETE from NhanVien where MaNV = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, MaNV);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateNV(NhanVien nv, String ma) {
        String sql = "UPDATE NhanVien SET MaNV = ?,HoVaTen = ?,MatKhau = ?,DiaChi = ?,Email = ?,sdt = ?, VaiTro = ?,GioiTinh = ? WHERE MaNV = ?";
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHoVaTen());
            ps.setString(3, nv.getMatKhau());
            ps.setString(4, nv.getDiaChi());
            ps.setString(5, nv.getEmail());
            ps.setString(6, nv.getsdt());
            ps.setBoolean(7, nv.isVaiTro());
            ps.setBoolean(8, nv.isGioiTinh());
            ps.setString(9, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<NhanVien> timNV(String maNV) {
        List<NhanVien> list = new ArrayList<>();
        sql = "SELECT * FROM NhanVien WHERE MaNV LIKE ? ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + maNV + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   
}
