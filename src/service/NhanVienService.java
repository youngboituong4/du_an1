/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.NhanVien;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

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
        sql = "Select MaNV, HoVaTen, MatKhau,DiaChi,Email,SDT,GioiTinh,VaiTro,TrangThai from NhanVien";
        List<NhanVien> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updateMaNV(int id) {
        // Cập nhật MaNV dựa trên ID
        sql = "UPDATE NhanVien SET MaNV = CONCAT('NV', RIGHT('00' + CAST(? AS VARCHAR(2)), 2)) WHERE ID = ?";

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setInt(2, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng PreparedStatement và Connection trong khối finally để đảm bảo luôn được đóng
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int AddNV(NhanVien nv) {
        // Thay đổi câu lệnh SQL để không chèn trực tiếp giá trị cho cột MaNV
        sql = "INSERT INTO NhanVien (HoVaTen, MatKhau, DiaChi, Email, SDT, GioiTinh, VaiTro, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Chèn giá trị cho các cột khác (không bao gồm MaNV)
            ps.setObject(1, nv.getHoVaTen());
            ps.setObject(2, nv.getMatKhau());
            ps.setObject(3, nv.getDiaChi());
            ps.setObject(4, nv.getEmail());
            ps.setObject(5, nv.getSdt());
            ps.setBoolean(6, nv.isGioiTinh());
            ps.setBoolean(7, nv.isVaiTro());
            ps.setBoolean(8, nv.isTrangThai());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    // Cập nhật MaNV sau khi có ID được sinh tự động
                    updateMaNV(id);
                }
            }

            return affectedRows;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            // Đóng PreparedStatement và Connection trong khối finally để đảm bảo luôn được đóng
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public NhanVien getNV(String MaNV) {
        sql = "SELECT MaNV,HoVaTen,MatKhau,DiaChi,Email,SDT,GioiTinh,VaiTro,TrangThai from NhanVien where MaNV = ?";
        NhanVien nv = null;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, MaNV);
            rs = ps.executeQuery();
            while (rs.next()) {
                nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9));
            }
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NhanVien> FindNV(int MaNV) {
        List<NhanVien> list = new ArrayList<>();
        sql = "SELECT MaNV,HoVaTen,MatKhau,DiaChi,Email,SDT,GioiTinh,VaiTro,TrangThai FROM NhanVien WHERE MaNV = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, MaNV);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9));
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
        String sql = "UPDATE NhanVien SET MaNV = ?,HoVaTen = ?,MatKhau = ?,DiaChi = ?,Email = ?,sdt = ?, VaiTro = ?,GioiTinh = ?,TrangThai = ? WHERE MaNV = ?";
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHoVaTen());
            ps.setString(3, nv.getMatKhau());
            ps.setString(4, nv.getDiaChi());
            ps.setString(5, nv.getEmail());
            ps.setString(6, nv.getSdt());
            ps.setBoolean(7, nv.isVaiTro());
            ps.setBoolean(8, nv.isGioiTinh());
            ps.setBoolean(9, nv.isTrangThai());
            ps.setString(10, ma);
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
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<NhanVien> timTheoDieuKien(String tim, Boolean vaiTro, Boolean trangThai) {
        ArrayList<NhanVien> list = new ArrayList<>();
        sql = "SELECT * FROM NhanVien WHERE (MaNV LIKE ? OR HoVaTen LIKE ? OR MatKhau LIKE ? OR DiaChi LIKE ? OR Email LIKE ? OR SDT LIKE ?)And VaiTro Like ? AND TrangThai like ? ORDER BY MaNV";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + tim + "%");
            ps.setObject(2, "%" + tim + "%");
            ps.setObject(3, "%" + tim + "%");
            ps.setObject(4, "%" + tim + "%");
            ps.setObject(5, "%" + tim + "%");
            ps.setObject(6, "%" + tim + "%");
            if (vaiTro != null) {
                ps.setBoolean(7, vaiTro);
            } else {
                ps.setNull(7, Types.BOOLEAN);
            }
            if (trangThai != null) {
                ps.setBoolean(8, trangThai);
            } else {
                ps.setNull(8, Types.BOOLEAN);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean maNVExistsInDatabase(String maNVValue) {
        String checkQuery = "SELECT COUNT(*) FROM NhanVien WHERE MaNV = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement checkPs = con.prepareStatement(checkQuery)) {
            checkPs.setString(1, maNVValue);
            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Trả về true nếu mã nhân viên đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }

        return false;
    }

    public void importDataFromExcelToDatabase(XSSFSheet sheet) {
        String insertQuery = "INSERT INTO NhanVien (MaNV, HoVaTen, MatKhau, DiaChi, Email,SDT,  GioiTinh, VaiTro, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = DBConnect.getConnection();

        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            for (int row = 1; row < sheet.getPhysicalNumberOfRows(); row++) {
                XSSFRow excelRow = sheet.getRow(row);

                XSSFCell MaNV = excelRow.getCell(0);
                XSSFCell HoVaTen = excelRow.getCell(1);
                XSSFCell MatKhau = excelRow.getCell(2);
                XSSFCell SDT = excelRow.getCell(5);
                XSSFCell Email = excelRow.getCell(4);
                XSSFCell DiaChi = excelRow.getCell(3);
                XSSFCell GioiTinh = excelRow.getCell(6);
                XSSFCell VaiTro = excelRow.getCell(7);
                XSSFCell TrangThai = excelRow.getCell(8);

                String maNVValue = (MaNV != null) ? MaNV.toString() : "";
                String hoVaTenValue = (HoVaTen != null) ? HoVaTen.toString() : "";
                String matKhauValue = (MatKhau != null) ? MatKhau.toString() : "";
                String sdtValue = (SDT != null) ? SDT.toString() : "";
                String emailValue = (Email != null) ? Email.toString() : "";
                String diaChiValue = (DiaChi != null) ? DiaChi.toString() : "";
                String gioiTinhValue = (GioiTinh != null) ? GioiTinh.toString() : "";
                String vaiTroValue = (VaiTro != null) ? VaiTro.toString() : "";
                String trangThaiValue = (TrangThai != null) ? TrangThai.toString() : "";

                // Kiểm tra xem mã nhân viên đã tồn tại hay chưa
                if (maNVExistsInDatabase(maNVValue)) {
                    // Thực hiện xử lý khi mã nhân viên đã tồn tại (ví dụ: thông báo, bỏ qua, hoặc cập nhật)
                    continue; // Bỏ qua dòng hiện tại và chuyển đến dòng tiếp theo
                }

                ps.setString(1, maNVValue);
                ps.setString(2, hoVaTenValue);
                ps.setString(3, matKhauValue);
                ps.setString(4, diaChiValue);
                ps.setString(5, emailValue);
                ps.setString(6, sdtValue);

                // Xử lý cột có kiểu dữ liệu bit (boolean)
                ps.setBoolean(7, "Nam".equals(gioiTinhValue));

                ps.setBoolean(8, "Quản lý".equals(vaiTroValue));

                // Xử lý cột có kiểu dữ liệu bit (boolean)
                ps.setBoolean(9, "Active".equals(trangThaiValue));

                // Thực hiện câu truy vấn
                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Dữ liệu đã được thêm vào cơ sở dữ liệu thành công!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại: " + e.getMessage());
        }
    }

    public void updateMaNVForImportedData() {
        try {
            con = DBConnect.getConnection();
            String sql = "UPDATE NhanVien SET MaNV = CONCAT('NV', RIGHT('00' + CAST(ID AS VARCHAR(2)), 2)) WHERE MaNV IS NULL OR MaNV = ''";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}