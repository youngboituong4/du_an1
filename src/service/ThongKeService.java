/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.ThongKe;
import model.ThongKe1;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author PC
 */
public class ThongKeService {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    
    public int SoKhachHang(){
        int count = 0;
        sql = "select count(*) as count from khachhang";
        
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                count = rs.getInt("count");
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    public int KhachHangNam(int namm){
        int count = 0;
            sql = "select count(*) as count from khachhang WHERE YEAR(ngaythanhtoan) like ?";
        
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, namm);
            rs = ps.executeQuery();
            while (rs.next()) {                
                count = rs.getInt("count");
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    
 
   
    public int HoaDonDaThanhToan(){
        int countt = 0;
        sql = "select count(*) as countt from hoadon where TrangThai like '1'";
        
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                countt = rs.getInt("countt");
            }
            return countt;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    public int HoaDonNam(int namm){
        int counttt = 0;
        sql = "select count(*) as counttt from hoadon WHERE YEAR(ngaythanhtoan) like ?";
        
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, namm);
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                counttt = rs.getInt("counttt");
            }
            return counttt;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    public int HoaDonThang(int namm, int thang){
        int counttt = 0;
        sql = "select count(*) as counttt from hoadon WHERE YEAR(ngaythanhtoan) like ? and MONTH(ngaythanhtoan) like ?";
        
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, namm);
            ps.setObject(2, thang);
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                counttt = rs.getInt("counttt");
            }
            return counttt;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    public int TimFromTo(String ngayy){
        int counnt = 0;
        sql = "select sum(ThanhTien) as total_monthh from hoadon where NgayThanhToan like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ngayy);  
            rs = ps.executeQuery();
            while (rs.next()) {                
                counnt = rs.getInt("total_monthh");
            }
            return counnt;
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;   
        }
    }
    
    public int DemSoSp(){
        int counttt = 0;
        sql = "select count(*) as dem from sanpham";
        
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                counttt = rs.getInt("dem");
            }
            return counttt;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    
    public List<ThongKe> getThongKe(){
        List<ThongKe> list = new ArrayList<>();
        sql = " select masp, tensp, loaisanpham, kichthuoc, tenmau,chatlieu, thuonghieu, gia,sum(hoadonchitiet.soluong)  from ChiTietSanPham\n" +
"	   left join hoadonchitiet on chitietsanpham.id = hoadonchitiet.idchitietsanpham \n" +
"	    group by masp, tensp, loaisanpham, kichthuoc, tenmau,chatlieu, thuonghieu, gia";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                ThongKe tke = new ThongKe(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getInt(9));
                list.add(tke);
                        
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<ThongKe1> getT5ThongKe(){
        List<ThongKe1> list = new ArrayList<>();
        sql = "select top 5 masp, tensp, loaisanpham, kichthuoc, tenmau,chatlieu, thuonghieu, gia,sum(hoadonchitiet.soluong) as tongsl  from ChiTietSanPham\n" +
"		join hoadonchitiet on chitietsanpham.id = hoadonchitiet.idchitietsanpham\n" +
"    group by masp, tensp, loaisanpham, kichthuoc, tenmau,chatlieu, thuonghieu, gia order by tongsl desc ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                ThongKe1 tke = new ThongKe1(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDouble(8), (int) rs.getDouble(9));
                list.add(tke);
                        
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    

   public int DoanhThu(){
        int total = 0;
        sql = "SELECT SUM(thanhtien) AS total FROM hoadon";
        
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                total = rs.getInt("total");
            }
            return total;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    public int DoanhThuNam(int nam){
        int total = 0;
        sql = "SELECT SUM(thanhtien) AS total_sales FROM hoadon WHERE YEAR(NgayThanhToan) like ?";                
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nam);
            rs = ps.executeQuery();
            while (rs.next()) {                
                total = rs.getInt("total_sales");
            }
            return total;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;   
        }
    }
    public int DoanhThuThangTrongNam(int thang, int namm){
        int ok = 0;
        sql = "SELECT SUM(thanhtien) AS total_month FROM hoadon WHERE MONTH(NgayThanhToan) like ? and YEAR(NgayThanhToan) like ?";                
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, thang);
            ps.setObject(2, namm);
            rs = ps.executeQuery();
            while (rs.next()) {                
                ok = rs.getInt("total_month");
            }
            return ok;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;   
        }
    }
    public int DoanhThuNgayThangNam(int ngay, int thang, int namm){
        int oke = 0;
        sql = "SELECT SUM(thanhtien) AS total_days FROM hoadon WHERE DAY(Ngaythanhtoan) like ? and MONTH(Ngaythanhtoan) like ? and YEAR(Ngaythanhtoan) like ?";                
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ngay);
            ps.setObject(2, thang);
            ps.setObject(3, namm);
            
            rs = ps.executeQuery();
            while (rs.next()) {                
                oke = rs.getInt("total_days");
            }
            return oke;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;   
        }
    }
    
    public int DoanhThuHomNay(LocalDate ngay){
        int tienhomnay = 0;
        sql="select sum(ThanhTien) as tienhomnay from hoadon where  ngayThanhToan like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ngay);
            rs = ps.executeQuery();
            while (rs.next()){
                               
                tienhomnay = rs.getInt("tienhomnay");
                
            }
            return tienhomnay;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int HoaDonDaThanhToanHomNay(LocalDate ngay){
        int countt = 0;
        sql = "select count(*) as countt from hoadon where NgayThanhToan like ? and TrangThai like '1'";
        
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ngay    );
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                countt = rs.getInt("countt");
            }
            return countt;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    public int SoKhachHangHomNay(LocalDate ngay){
        int count = 0;
        sql = "select count(*) as count from khachhang where NgayThanhToan like ?";
        
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ngay);
            rs = ps.executeQuery();
            while (rs.next()) {                
                count = rs.getInt("count");
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}