/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dailycheck;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.KhuyenMai;
import service.KhuyenMaiservice;

/**
 *
 * @author LENOVO
 */
public class DailyCheckingKhuyenMai extends Thread {

    KhuyenMaiservice service = new KhuyenMaiservice();

    /**
     *
     */
//    @Override
//        public synchronized void run(){
//            while (true) {            
//
//                try {
//                    Thread.sleep(10000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//    public synchronized void run() {
//    while (true) {            
//        // Lấy ngày hiện tại
//        Date currentDate = new Date();
//
//        // Lấy danh sách các đối tượng từ cơ sở dữ liệu
//        List<KhuyenMai> objects = new ArrayList<>(); // Gọi phương thức để lấy danh sách đối tượng
//
//        for (KhuyenMai object : objects) {
//            // Lấy ngày từ cơ sở dữ liệu
//            Date dateFromDatabase = object.getNgayBatDau();
//
//            // So sánh ngày hiện tại với ngày từ cơ sở dữ liệu
//            if (currentDate.after(dateFromDatabase)) {
//                // Nếu ngày hiện tại sau ngày từ cơ sở dữ liệu, thì chuyển đổi trạng thái
//                String ma = object.getMa(); // Mã cần cập nhật
//                String newTrangThai = "Không áp dụng"; // Trạng thái mới
//
//                // Cập nhật trạng thái
//                String updated = object.getTrangThai(); // Gọi phương thức để cập nhật trạng thái
//
//                // Kiểm tra nếu cập nhật thành công
//                if (updated != null) {
//                    System.out.println("Đã cập nhật trạng thái thành công cho mã: " + ma);
//                } else {
//                    System.out.println("Cập nhật trạng thái thất bại cho mã: " + ma);
//                }
//            }
//        }
//
//        try {
//            Thread.sleep(10000); // Dừng luồng trong 10 giây
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
    public synchronized void run() {
        while (true) {
            // Lấy ngày hiện tại
            Date currentDate = new Date();

            // Lấy danh sách các đối tượng từ cơ sở dữ liệu
            List<KhuyenMai> objects = service.getAllKM(); // Gọi phương thức để lấy danh sách đối tượng

            for (KhuyenMai object : objects) {
                // Lấy ngày từ cơ sở dữ liệu
                Date dateFromDatabase = object.getNgayKetThuc();
                
                // So sánh ngày hiện tại với ngày từ cơ sở dữ liệu
                if (currentDate.after(dateFromDatabase)) {
                    // Nếu ngày hiện tại sau ngày từ cơ sở dữ liệu, thì chuyển đổi trạng thái
                    String ma = object.getMa(); // Mã cần cập nhật
                    String newTrangThai = "Không Áp dụng"; // Trạng thái mới

                    // Cập nhật trạng thái
                    int updated = service.HuyKM(ma, newTrangThai); // Gọi phương thức để cập nhật trạng thái

                    // Kiểm tra nếu cập nhật thành công
                    if (updated > 0) {
                      
                    } else {
                        
                    }
                }
            }

            try {
                Thread.sleep(10000); // Dừng luồng trong 10 giây
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
