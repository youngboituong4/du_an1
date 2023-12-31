/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import response.HoaDonResponse;
import response.XemCTHDResponse;
import service.HoaDonService;

/**
 *
 * @author Dell
 */
public class JDialogCTHD extends javax.swing.JDialog {

    /**
     * Creates new form JDialogCTHD
     */
    private XemCTHDResponse obj;
    HoaDonService service = new HoaDonService();

    public JDialogCTHD(java.awt.Frame parent, boolean modal, XemCTHDResponse obj) {
        super(parent, modal);
        this.obj = obj; // 
        initComponents();
        initInfo();
    }

    private void initInfo() {
        //System.out.println(obj.getMaHD());
        maHDTxt.setText(obj.getMaHD());

        // Định dạng ngày và hiển thị
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        String ngayTaoFormatted = dateFormat.format(obj.getNgayTao());
        ngayTaoTxt.setText(ngayTaoFormatted);
        String ngayThanhToanFormatted = dateFormat.format(obj.getNgayThanhToan());
        ngayThanhToanTxt.setText(ngayThanhToanFormatted);
        if (obj.getHTthanhtoan() == 0) {
            HTthanhtoanTxt.setText("Tiền mặt");
        }
        if (obj.getHTthanhtoan() == 1) {
            HTthanhtoanTxt.setText("Chuyển khoản");
        }
        if (obj.getHTthanhtoan() == 2) {
            HTthanhtoanTxt.setText("Kết hợp");
        }
        tongTienTxt.setText(obj.getTongTien() + "");
        tienkhachCKTxt.setText(obj.getTienkhachCK() + "");
        tienThuaTxt.setText(obj.getTienThua() + "");
        maNvTxt.setText(obj.getMaNV());
        tenNvTxt.setText(obj.getTenNV());
        tenKhTxt.setText(obj.getTenKH());
        dchiTxt.setText(obj.getDiaChi());
        sdtTxt.setText(obj.getSdt());
        tienGiamTtx.setText(obj.getTienGiam() + "");
        if (obj.getTrangThai() == 0) {
            trangthaiTxt.setText("Chờ thanh toán");
        }
        if (obj.getTrangThai() == 1) {
            trangthaiTxt.setText("Đã thanh toán");
        }
        tienKhachTraTxt.setText(obj.getTienKhachTra() + "");
        maKMtxt.setText(obj.getMaKM());
        tenKmTxt.setText(obj.getTenKM());
        if (obj.getLoaiKM() == 0) {
            HTgiamTtx.setText("Giảm bằng %");
        }
        if (obj.getLoaiKM() == 1) {
            HTgiamTtx.setText("Giảm bằng tiền");
        }
        gtriGiamTtx.setText(obj.getGiaTriGiam() + "");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        maHDTxt = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        ngayTaoTxt = new javax.swing.JLabel();
        ngayThanhToanTxt = new javax.swing.JLabel();
        HTthanhtoanTxt = new javax.swing.JLabel();
        tongTienTxt = new javax.swing.JLabel();
        tienkhachCKTxt = new javax.swing.JLabel();
        tienThuaTxt = new javax.swing.JLabel();
        maNvTxt = new javax.swing.JLabel();
        tenNvTxt = new javax.swing.JLabel();
        tenKhTxt = new javax.swing.JLabel();
        dchiTxt = new javax.swing.JLabel();
        sdtTxt = new javax.swing.JLabel();
        tienGiamTtx = new javax.swing.JLabel();
        trangthaiTxt = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tienKhachTraTxt = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        maKMtxt = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        tenKmTxt = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        HTgiamTtx = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        gtriGiamTtx = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Thông tin hoá đơn tại quầy");

        jLabel2.setText("Mã hoá đơn:");

        maHDTxt.setText(" ");

        jLabel4.setText("Ngày tạo:");

        jLabel5.setText("Ngày thanh toán:");

        jLabel6.setText("Hình thức thanh toán:");

        jLabel7.setText("Tổng tiền:");

        jLabel8.setText("Tiền khách CK:");

        jLabel9.setText("Tiền thừa:");

        jLabel10.setText("Mã NV:");

        jLabel11.setText("Tên NV:");

        jLabel12.setText("Tên khách hàng:");

        jLabel13.setText("Địa chỉ:");

        jLabel14.setText("Số điện thoại:");

        jLabel15.setText("Tiền giảm giá:");

        jLabel16.setText("Trạng thái:");

        ngayTaoTxt.setText("jLabel17");

        ngayThanhToanTxt.setText("jLabel18");

        HTthanhtoanTxt.setText("jLabel19");

        tongTienTxt.setText("jLabel20");

        tienkhachCKTxt.setText("jLabel21");

        tienThuaTxt.setText("jLabel22");

        maNvTxt.setText("jLabel23");

        tenNvTxt.setText("jLabel24");

        tenKhTxt.setText("jLabel25");

        dchiTxt.setText("jLabel26");

        sdtTxt.setText("jLabel27");

        tienGiamTtx.setText("jLabel28");

        trangthaiTxt.setText("jLabel29");

        jLabel3.setText("Tiền khách trả:");

        tienKhachTraTxt.setText("jLabel17");

        jLabel17.setText("Mã khuyến mãi:");

        maKMtxt.setText("jLabel18");

        jLabel19.setText("Tên khuyến mãi:");

        tenKmTxt.setText("jLabel20");

        jLabel21.setText("Hình thức giảm:");

        HTgiamTtx.setText("jLabel22");

        jLabel23.setText("Giá trị giảm:");

        gtriGiamTtx.setText("jLabel24");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel3)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19)
                            .addComponent(jLabel21)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(maHDTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ngayTaoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                            .addComponent(ngayThanhToanTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(HTthanhtoanTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tongTienTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tienkhachCKTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tienThuaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(maNvTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tenNvTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tenKhTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dchiTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sdtTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tienGiamTtx, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(trangthaiTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tienKhachTraTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(maKMtxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tenKmTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(HTgiamTtx, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gtriGiamTtx, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(maHDTxt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ngayTaoTxt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ngayThanhToanTxt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(HTthanhtoanTxt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tongTienTxt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tienkhachCKTxt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tienKhachTraTxt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tienThuaTxt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(maNvTxt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tenNvTxt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tenKhTxt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(dchiTxt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(sdtTxt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(maKMtxt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(tenKmTxt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(HTgiamTtx))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(gtriGiamTtx))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tienGiamTtx))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(trangthaiTxt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(JDialogCTHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JDialogCTHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JDialogCTHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JDialogCTHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                JDialogCTHD dialog = new JDialogCTHD(new javax.swing.JFrame(), true, obj);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HTgiamTtx;
    private javax.swing.JLabel HTthanhtoanTxt;
    private javax.swing.JLabel dchiTxt;
    private javax.swing.JLabel gtriGiamTtx;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel maHDTxt;
    private javax.swing.JLabel maKMtxt;
    private javax.swing.JLabel maNvTxt;
    private javax.swing.JLabel ngayTaoTxt;
    private javax.swing.JLabel ngayThanhToanTxt;
    private javax.swing.JLabel sdtTxt;
    private javax.swing.JLabel tenKhTxt;
    private javax.swing.JLabel tenKmTxt;
    private javax.swing.JLabel tenNvTxt;
    private javax.swing.JLabel tienGiamTtx;
    private javax.swing.JLabel tienKhachTraTxt;
    private javax.swing.JLabel tienThuaTxt;
    private javax.swing.JLabel tienkhachCKTxt;
    private javax.swing.JLabel tongTienTxt;
    private javax.swing.JLabel trangthaiTxt;
    // End of variables declaration//GEN-END:variables

}
