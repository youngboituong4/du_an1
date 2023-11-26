/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import response.HDCTResponse;
import response.HoaDonResponse;
import service.HoaDonService;

/**
 *
 * @author Dell
 */
public class HoaDonForm extends javax.swing.JFrame {

    /**
     * Creates new form HoaDonForm
     */
    DefaultTableModel model;
    DefaultTableModel model2;
    ArrayList<HoaDonResponse> list = new ArrayList<>();
    ArrayList<HDCTResponse> listCT = new ArrayList<>();
    HoaDonService service = new HoaDonService();
    private Integer trangthai = null;
    private Date ngayBD = null;
    private Date ngayKT = null;
    private Integer totalPage = null;
    private Integer size = 2;
    private Integer page = null;

    public HoaDonForm() {
        initComponents();
        this.setLocationRelativeTo(null);
        model = (DefaultTableModel) TbHoaDon.getModel();
        model2 = (DefaultTableModel) HDCTtable.getModel();
        page = 1;
        totalPage = (int) Math.ceil((double) service.getAll().size() / size);
        tongTrangLb.setText(totalPage + "");
        trangLb.setText(page + "");
        trangthai = null;
        ngayBD = null;
        ngayKT = null;
        try {
            list = service.loc("", 0, null, null, null);
            showData(list);
        } catch (Exception e) {
        }
    }

    private void showData(ArrayList<HoaDonResponse> listHDS) {
        model.setRowCount(0);
        for (HoaDonResponse hds : listHDS) {
            Object data[] = {
                hds.getMaHD(),
                hds.getNgayTao(),
                hds.getNgayThanhToan(),
                hds.getThanhTien(),
                hds.getMaNV(),
                hds.getTenKH(),
                (hds.getTrangThai() == 1) ? "Đã thanh toán" : "Chưa thanh toán"
            };
            model.addRow(data);
        }
    }

    private void showHDCT(ArrayList<HDCTResponse> listHDCT) {
        model2.setRowCount(0);
        for (HDCTResponse hdct : listHDCT) {
            Object data[] = {
                hdct.getMaSP(),
                hdct.getTenSP(),
                hdct.getHang(),
                hdct.getMau(),
                hdct.getSize(),
                hdct.getSoLuong(),
                hdct.getDonGia(),
                hdct.getGiamGiaKM(),
                hdct.getTongTien()
            };
            model2.addRow(data);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        timTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        trangThaiCbo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        thanhToanCbo = new javax.swing.JComboBox<>();
        locBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TbHoaDon = new javax.swing.JTable();
        prevBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        tongTrangLb = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        trangLb = new javax.swing.JLabel();
        ngayBDDate = new com.toedter.calendar.JDateChooser();
        ngayKTDate = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        HDCTtable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(null);
        setMinimumSize(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setText("Tìm kiếm hóa đơn:");

        jLabel4.setText("Trạng thái hóa đơn:");

        trangThaiCbo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chờ thanh toán", "Đã thanh toán" }));

        jLabel5.setText("Hình thức thanh toán:");

        thanhToanCbo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Tiền mặt", "Chuyển khoản", "Tích hợp" }));

        locBtn.setText("Lọc");
        locBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locBtnActionPerformed(evt);
            }
        });

        TbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Ngày tạo", "Ngày thanh toán", "Tổng tiền", "Mã NV", "Tên KH", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TbHoaDon);

        prevBtn.setText("Prev");
        prevBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBtnActionPerformed(evt);
            }
        });

        nextBtn.setText("Next");

        tongTrangLb.setText("jLabel6");

        jLabel7.setText("/");

        trangLb.setText("jLabel8");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(28, 28, 28)
                        .addComponent(trangThaiCbo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(thanhToanCbo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(locBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(timTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(ngayBDDate, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(ngayKTDate, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(prevBtn)
                .addGap(40, 40, 40)
                .addComponent(trangLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tongTrangLb)
                .addGap(42, 42, 42)
                .addComponent(nextBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(timTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ngayBDDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ngayKTDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(trangThaiCbo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(thanhToanCbo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locBtn))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prevBtn)
                    .addComponent(nextBtn)
                    .addComponent(tongTrangLb)
                    .addComponent(jLabel7)
                    .addComponent(trangLb))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn chi tiết"));

        HDCTtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Hãng", "Màu", "Size", "Số lượng", "Đơn giá", "Giảm giá KM", "Tổng tiền"
            }
        ));
        jScrollPane2.setViewportView(HDCTtable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbHoaDonMouseClicked
        int row = TbHoaDon.getSelectedRow();
        HoaDonResponse hd = list.get(row);
        showHDCT(service.getAllHDCT(hd.getMaHD()));
    }//GEN-LAST:event_TbHoaDonMouseClicked

    private void prevBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevBtnActionPerformed
        if (page == 1) {
            return;
        }
        page = page - 1;
        trangLb.setText(page + "");
//        showData(service.loc(timTxt.getText(), page-1, trangthai, ngayBD, ngayKT));
    }//GEN-LAST:event_prevBtnActionPerformed

    private void locBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locBtnActionPerformed
        
    }//GEN-LAST:event_locBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoaDonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDonForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable HDCTtable;
    private javax.swing.JTable TbHoaDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton locBtn;
    private javax.swing.JButton nextBtn;
    private com.toedter.calendar.JDateChooser ngayBDDate;
    private com.toedter.calendar.JDateChooser ngayKTDate;
    private javax.swing.JButton prevBtn;
    private javax.swing.JComboBox<String> thanhToanCbo;
    private javax.swing.JTextField timTxt;
    private javax.swing.JLabel tongTrangLb;
    private javax.swing.JLabel trangLb;
    private javax.swing.JComboBox<String> trangThaiCbo;
    // End of variables declaration//GEN-END:variables
}
