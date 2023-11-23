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
        sql = "SELECT * FROM MauSac";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                MauSac ms = new MauSac(rs.getInt(1), rs.getString(2), rs.getString(3));
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
        sql = "SELECT * FROM KichThuoc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                KichThuoc kt = new KichThuoc(rs.getInt(1), rs.getString(2), rs.getString(3));
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
        sql = "SELECT * FROM ChatLieu";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ChatLieu cl = new ChatLieu(rs.getInt(1), rs.getString(2), rs.getString(3));
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
        sql = "SELECT * FROM LoaiSanPham";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(lsp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MauSac getMau(String ten) {
        MauSac ms = null;
        sql = "SELECT * FROM MauSac WHERE TenMau = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            rs = ps.executeQuery();

            while (rs.next()) {
                ms = new MauSac(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            return ms;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public KichThuoc getKT(String ten) {
        KichThuoc kt = null;
        sql = "SELECT * FROM KichThuoc WHERE Size = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            rs = ps.executeQuery();

            while (rs.next()) {
                kt = new KichThuoc(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            return kt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ChatLieu getCL(String ten) {
        ChatLieu cl = null;
        sql = "SELECT * FROM ChatLieu WHERE ChatLieu = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            rs = ps.executeQuery();

            while (rs.next()) {
                cl = new ChatLieu(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            return cl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LoaiSanPham getLSP(String ten) {
        LoaiSanPham lsp = null;
        sql = "SELECT * FROM LoaiSanPham WHERE LoaiSanPham = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            rs = ps.executeQuery();

            while (rs.next()) {
                lsp = new LoaiSanPham(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            return lsp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MauSac getMaMau(String ma) {
        MauSac ms = null;
        sql = "SELECT * FROM MauSac WHERE MS = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();

            while (rs.next()) {
                ms = new MauSac(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            return ms;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public KichThuoc getMaKT(String ma) {
        KichThuoc kt = null;
        sql = "SELECT * FROM KichThuoc WHERE KT = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();

            while (rs.next()) {
                kt = new KichThuoc(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            return kt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ChatLieu getMaCL(String ma) {
        ChatLieu cl = null;
        sql = "SELECT * FROM ChatLieu WHERE CL = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();

            while (rs.next()) {
                cl = new ChatLieu(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            return cl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LoaiSanPham getMaLSP(String ma) {
        LoaiSanPham lsp = null;
        sql = "SELECT * FROM LoaiSanPham WHERE LSP = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();

            while (rs.next()) {
                lsp = new LoaiSanPham(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            return lsp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addMau(MauSac ms) {
        sql = "INSERT INTO MauSac (MS, TenMau) VALUES (?, ?)";
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
        sql = "INSERT INTO KichThuoc (KT, Size) VALUES (?, ?)";
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
        sql = "INSERT INTO ChatLieu (CL, ChatLieu) VALUES (?, ?)";
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
        sql = "INSERT INTO LoaiSanPham (LSP, LoaiSanPham) VALUES (?, ?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, lsp.getMaSP());
            ps.setObject(2, lsp.getTenSP());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateMau(MauSac ms, String ma) {
        sql = "UPDATE MauSac SET TenMau = ? WHERE MS = ?";
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
        sql = "UPDATE KichThuoc SET Size = ? WHERE KT = ?";
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
        sql = "UPDATE ChatLieu SET ChatLieu = ? WHERE CL = ?";
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
        sql = "UPDATE LoaiSanPham SET LoaiSanPham = ? WHERE LSP = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, lsp.getTenSP());
            ps.setObject(2, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteMau(String ma) {
        sql = "DELETE MauSac WHERE MS = ?";
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
        sql = "DELETE KichThuoc WHERE KT = ?";
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
        sql = "DELETE ChatLieu WHERE CL = ?";
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
        sql = "DELETE LoaiSanPham WHERE LSP = ?";
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
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getInt(9), rs.getBoolean(10));
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
            ps.setObject(1, "%" + ma + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getInt(9), rs.getBoolean(10));
                list.add(ctsp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addCTSP(ChiTietSanPham ctsp) {
        sql = "INSERT INTO ChiTietSanPham (MaSP, TenSP, LoaiSP, KT, MS, CL, Gia, SoLuong, TrangThai)\n"
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ctsp.getMa());
            ps.setObject(2, ctsp.getTen());
            ps.setObject(3, ctsp.getLoai());
            ps.setObject(4, ctsp.getKt());
            ps.setObject(5, ctsp.getMs());
            ps.setObject(6, ctsp.getCl());
            ps.setObject(7, ctsp.getGia());
            ps.setObject(8, ctsp.getSl());
            ps.setObject(9, ctsp.isTrangThai());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int addSPCTSP(String maSP, String tenSP) {
        sql = "INSERT INTO ChiTietSanPham (MaSP, TenSP, LoaiSP, KT, MS, CL) VALUES (?,?,'','','','')";
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

    public int updateSP(ChiTietSanPham ctsp, int stt) {
        sql = "UPDATE ChiTietSanPham SET MaSP = ?, TenSP = ?, LoaiSP = ?, KT = ?, MS = ?, CL = ?, Gia = ?, SoLuong = ?, TrangThai = ? WHERE STT = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ctsp.getMa());
            ps.setObject(2, ctsp.getTen());
            ps.setObject(3, ctsp.getLoai());
            ps.setObject(4, ctsp.getKt());
            ps.setObject(5, ctsp.getMs());
            ps.setObject(6, ctsp.getCl());
            ps.setObject(7, ctsp.getGia());
            ps.setObject(8, ctsp.getSl());
            ps.setObject(9, ctsp.isTrangThai());
            ps.setObject(10, stt);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int updateSLGIASP(ChiTietSanPham ctsp, int stt) {
        sql = "UPDATE ChiTietSanPham SET Gia = ?, SoLuong = ?, TrangThai = ? WHERE STT = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ctsp.getGia());
            ps.setObject(2, ctsp.getSl());
            ps.setObject(3, ctsp.isTrangThai());
            ps.setObject(4, stt);

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
