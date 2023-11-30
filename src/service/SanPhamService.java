/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import model.ChatLieu;
import model.KichThuoc;
import model.LoaiSanPham;
import model.MauSac;
import model.ChiTietSanPham;
import model.SanPham;
import model.ThuongHieu;

/**
 *
 * @author thinh
 */
public class SanPhamService {

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
                ChatLieu cl = new ChatLieu( rs.getString(1), rs.getString(2));
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

    public MauSac getMau(String ten) {
        MauSac ms = null;
        sql = "SELECT Ma, TenMau FROM MauSac WHERE TenMau = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            rs = ps.executeQuery();

            while (rs.next()) {
                ms = new MauSac(rs.getString(1), rs.getString(2));
            }
            return ms;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public KichThuoc getKT(String ten) {
        KichThuoc kt = null;
        sql = "SELECT Ma, KichThuoc FROM KichThuoc WHERE KichThuoc = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            rs = ps.executeQuery();

            while (rs.next()) {
                kt = new KichThuoc(rs.getString(1), rs.getString(2));
            }
            return kt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ChatLieu getCL(String ten) {
        ChatLieu cl = null;
        sql = "SELECT Ma, ChatLieu FROM ChatLieu WHERE ChatLieu = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            rs = ps.executeQuery();

            while (rs.next()) {
                cl = new ChatLieu(rs.getString(1), rs.getString(2));
            }
            return cl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LoaiSanPham getLSP(String ten) {
        LoaiSanPham lsp = null;
        sql = "SELECT Ma, LoaiSanPham FROM LoaiSanPham WHERE LoaiSanPham = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            rs = ps.executeQuery();

            while (rs.next()) {
                lsp = new LoaiSanPham(rs.getString(1), rs.getString(2));
            }
            return lsp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ThuongHieu getTH(String ten) {
        ThuongHieu th = null;
        sql = "SELECT Ma, TenThuongHieu FROM ThuongHieu WHERE TenThuongHieu = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            rs = ps.executeQuery();

            while (rs.next()) {
                th = new ThuongHieu(rs.getString(1), rs.getString(2));
            }
            return th;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MauSac getMaMau(String ma) {
        MauSac ms = null;
        sql = "SELECT Ma, TenMau FROM MauSac WHERE Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();

            while (rs.next()) {
                ms = new MauSac(rs.getString(1), rs.getString(2));
            }
            return ms;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public KichThuoc getMaKT(String ma) {
        KichThuoc kt = null;
        sql = "SELECT Ma, KichThuoc FROM KichThuoc WHERE Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();

            while (rs.next()) {
                kt = new KichThuoc(rs.getString(1), rs.getString(2));
            }
            return kt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ChatLieu getMaCL(String ma) {
        ChatLieu cl = null;
        sql = "SELECT Ma, ChatLieu FROM ChatLieu WHERE Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();

            while (rs.next()) {
                cl = new ChatLieu(rs.getString(1), rs.getString(2));
            }
            return cl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LoaiSanPham getMaLSP(String ma) {
        LoaiSanPham lsp = null;
        sql = "SELECT Ma, LoaiSanPham FROM LoaiSanPham WHERE Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();

            while (rs.next()) {
                lsp = new LoaiSanPham(rs.getString(1), rs.getString(2));
            }
            return lsp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ThuongHieu getMaTH(String ma) {
        ThuongHieu th = null;
        sql = "SELECT Ma, TenThuongHieu FROM ThuongHieu WHERE Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();

            while (rs.next()) {
                th = new ThuongHieu(rs.getString(1), rs.getString(2));
            }
            return th;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addMau(MauSac ms) {
        sql = "INSERT INTO MAUSAC (Ma, TenMau) VALUES (?, ?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ms.getMaMau());
            ps.setObject(2, ms.getTenMau());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int addKichThuoc(KichThuoc kt) {
        sql = "INSERT INTO KichThuoc (Ma, KichThuoc) VALUES (?, ?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kt.getMaKichThuoc());
            ps.setObject(2, kt.getTenKichThuoc());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int addChatLieu(ChatLieu cl) {
        sql = "INSERT INTO ChatLieu (Ma, ChatLieu) VALUES (?, ?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, cl.getMaChatLieu());
            ps.setObject(2, cl.getTenChatLieu());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int addLoaiSP(LoaiSanPham lsp) {
        sql = "INSERT INTO LoaiSanPham (Ma, LoaiSanPham) VALUES (?, ?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, lsp.getMaLoaiSanPham());
            ps.setObject(2, lsp.getLoaiSanPham());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int addThuongHieu(ThuongHieu th) {
        sql = "INSERT INTO ThuongHieu (Ma, TenThuongHieu) VALUES (?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, th.getMaThuongHieu());
            ps.setObject(2, th.getTenThuongHieu());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateMau(MauSac ms, String ma) {
        sql = "UPDATE MauSac SET TenMau = ? WHERE Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ms.getTenMau());
            ps.setObject(2, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateKichThuoc(KichThuoc kt, String ma) {
        sql = "UPDATE KichThuoc SET Size = ? WHERE Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kt.getTenKichThuoc());
            ps.setObject(2, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateChatLieu(ChatLieu cl, String ma) {
        sql = "UPDATE ChatLieu SET ChatLieu = ? WHERE Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, cl.getTenChatLieu());
            ps.setObject(2, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateLoaiSP(LoaiSanPham lsp, String ma) {
        sql = "UPDATE LoaiSanPham SET LoaiSanPham = ? WHERE Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, lsp.getLoaiSanPham());
            ps.setObject(2, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int updateThuongHieu(ThuongHieu th, String ma) {
        sql = "UPDATE ThuongHieu SET TenThuongHieu = ? WHERE Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, th.getTenThuongHieu());
            ps.setObject(2, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteMau(String ma) {
        sql = "DELETE MauSac WHERE Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteKT(String ma) {
        sql = "DELETE KichThuoc WHERE Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteCL(String ma) {
        sql = "DELETE ChatLieu WHERE Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteLSP(String ma) {
        sql = "DELETE LoaiSanPham WHERE Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int deleteTH(String ma) {
        sql = "DELETE ThuongHieu WHERE Ma = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<SanPham> getAllSP() {
        List<SanPham> list = new ArrayList<>();
        sql = "select * from SanPham";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SanPham> PhanTrangSP(int dong) {
        List<SanPham> list = new ArrayList<>();
        sql = "select * from SanPham \n"
                + "order by MaSP\n"
                + "offset ? rows\n"
                + "fetch next 5 rows only";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, dong);
            rs = ps.executeQuery();

            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SanPham getSP(String ma) {
        SanPham sp = null;
        sql = "SELECT * FROM SanPham WHERE MASP LIKE ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();

            while (rs.next()) {
                sp = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3));
            }
            return sp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ChiTietSanPham> getAllCTSP() {
        List<ChiTietSanPham> list = new ArrayList<>();
        sql = "SELECT * FROM ChiTietSanPham";
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

    public List<ChiTietSanPham> getMaSP(String ma) {
        List<ChiTietSanPham> list = new ArrayList<>();
        sql = "SELECT * FROM ChiTietSanPham WHERE MaSP LIKE ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%"+ma+"%");
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

    public int addCTSP(ChiTietSanPham ctsp) {
        sql = "INSERT INTO ChiTietSanPham (MaSP, TenSP, LoaiSanPham, KichThuoc, TenMau, ChatLieu,ThuongHieu, Gia, SoLuong, TrangThai)\n"
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ctsp.getMaSP());
            ps.setObject(2, ctsp.getTenSP());
            ps.setObject(3, ctsp.getMaLoai());
            ps.setObject(4, ctsp.getMaKichThuoc());
            ps.setObject(5, ctsp.getMaMauSac());
            ps.setObject(6, ctsp.getMaChatLieu());
            ps.setObject(7, ctsp.getMaThuongHieu());
            ps.setObject(8, ctsp.getGia());
            ps.setObject(9, ctsp.getSoLuong());
            ps.setObject(10, ctsp.isTrangThai());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int addSPCTSP(String maSP, String tenSP) {
        sql = "INSERT INTO ChiTietSanPham (MaSP, TenSP, LoaiSanPham, KichThuoc, TenMau, ChatLieu, ThuongHieu) VALUES (?,?,' ',' ',' ',' ',' ')";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maSP);
            ps.setObject(2, tenSP);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateSP(ChiTietSanPham ctsp, int id) {
        sql = "UPDATE ChiTietSanPham SET MaSP = ?, TenSP = ?, LoaiSanPham = ?, KichThuoc = ?, TenMau = ?, ChatLieu = ?,ThuongHieu = ?, Gia = ?, SoLuong = ?, TrangThai = ? WHERE ID = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ctsp.getMaSP());
            ps.setObject(2, ctsp.getTenSP());
            ps.setObject(3, ctsp.getMaLoai());
            ps.setObject(4, ctsp.getMaKichThuoc());
            ps.setObject(5, ctsp.getMaMauSac());
            ps.setObject(6, ctsp.getMaChatLieu());
            ps.setObject(7, ctsp.getMaThuongHieu());
            ps.setObject(8, ctsp.getGia());
            ps.setObject(9, ctsp.getSoLuong());
            ps.setObject(10, ctsp.isTrangThai());
            ps.setObject(11, id);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int updateSLGIASP(ChiTietSanPham ctsp, int id) {
        sql = "UPDATE ChiTietSanPham SET Gia = ?, SoLuong = ?, TrangThai = ? WHERE ID = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ctsp.getGia());
            ps.setObject(2, ctsp.getSoLuong());
            ps.setObject(3, ctsp.isTrangThai());
            ps.setObject(4, id);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int addSP(SanPham sp) {
        sql = "INSERT INTO SANPHAM (MASP, TENSP, Mota) VALUES (?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getMaSP());
            ps.setObject(2, sp.getTenSP());
            ps.setObject(3, sp.getMoTa());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<SanPham> timSP(String tim) {
        List<SanPham> list = new ArrayList<>();
        sql = "SELECT * FROM SANPHAM WHERE MaSP LIKE ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + tim + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
