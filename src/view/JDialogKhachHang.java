/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import model.LichSuGiaoDich;
import service.BanHangService;
import service.KhachHangService;

/**
 *
 * @author thinh
 */
public class JDialogKhachHang extends javax.swing.JDialog {

    /**
     * Creates new form JDialogKhachHang
     */
    
    DefaultTableModel model;
    DefaultTableModel model2;
    KhachHangService service = new KhachHangService();
    BanHangService serviceBH = new BanHangService();
    BanHangForm banHang = new BanHangForm();
    ArrayList<KhachHang> list = new ArrayList<>();
    private Integer page = null;
    private Integer totalPages = null;
    private Integer size = 2;
    private String gioiTinh = null;
    private String trangThai = null;
    
    
    public JDialogKhachHang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        model = (DefaultTableModel) khTable.getModel();

        gioiTinh = "";
        trangThai = "";
        
        try {
            list = service.getAll();
            showData(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void showData(ArrayList<KhachHang> listKH) {
        model.setRowCount(0);
        for (KhachHang kh : listKH) {
            Object data[] = {
                kh.getMa(),
                kh.getTen(),
                kh.getGioitinh(),
                kh.getSdt(),
                kh.getEmail(),
                kh.getDiachi(),
                kh.getTrangthai()
            };
            model.addRow(data);
        }
    }

    private void showLSGD(ArrayList<LichSuGiaoDich> listGD) {
        model2.setRowCount(0);
        for (LichSuGiaoDich lsu : listGD) {
            Object dataGD[] = {
                lsu.getTenKH(),
                lsu.getMaHD(),
                lsu.getNgayGD(),
                lsu.getTongtien(),
                lsu.getTrangthaiHD()
            };
            model2.addRow(dataGD);
        }
    }

    private void getIndex(int index) {
        KhachHang kh = list.get(index);
        maTxt.setText(kh.getMa());
        tenTxt.setText(kh.getTen());
        String gt = kh.getGioitinh();
        if (gt.equals("Nam")) {
            namRB.setSelected(true);
        } else {
            nuRB.setSelected(true);
        }
        sdtTxt.setText(kh.getSdt());
        emailTxt.setText(kh.getEmail());
        diachiTP.setText(kh.getDiachi());
        String tt = kh.getTrangthai();
        if (tt.equals("Còn hoạt động")) {
            conhdRB.setSelected(true);
        } else {
            ngunghdRB.setSelected(true);
        }
    }
    
    private void getMa(String ma) {
        KhachHang kh = service.getMaKH(ma);
        maTxt.setText(kh.getMa());
        tenTxt.setText(kh.getTen());
        String gt = kh.getGioitinh();
        if (gt.equals("Nam")) {
            namRB.setSelected(true);
        } else {
            nuRB.setSelected(true);
        }
        sdtTxt.setText(kh.getSdt());
        emailTxt1.setText(kh.getEmail());
        diachiTP1.setText(kh.getDiachi());
        String tt = kh.getTrangthai();
        if (tt.equals("Còn hoạt động")) {
            conhdRB1.setSelected(true);
        } else {
            ngunghdRB1.setSelected(true);
        }
    }

    private KhachHang getData() {
        int dem = 0;
        String ma = maTxt.getText();
        String ten = tenTxt.getText();
        String sdt = sdtTxt.getText();
        String email = emailTxt1.getText();
        String dchi = diachiTP1.getText();
        String gt, tt;
        if (ma.trim().isEmpty() || ten.trim().isEmpty() || sdt.trim().isEmpty() || email.trim().isEmpty() || dchi.trim().isEmpty()) {
            dem++;
            JOptionPane.showMessageDialog(this, "Không được để trống thông tin");
        }
        if (!ten.matches("^[\\p{L} ]+$")) {
            dem++;
            JOptionPane.showMessageDialog(this, "Tên không hợp lệ");
        }

        if (!sdt.matches("^[0-9]{10}$")) {
            dem++;
            JOptionPane.showMessageDialog(this, "SĐT không đúng định dạng");
        }
        if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            dem++;
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng Gmail");
        }
        if (namRB.isSelected() == true) {
            gt = "Nam";
        } else {
            gt = "Nữ";
        }
        if (conhdRB1.isSelected() == true) {
            tt = "Còn hoạt động";
        } else {
            tt = "Ngừng hoạt động";
        }

        if (dem == 0) {
            return new KhachHang(ma, ten, gt, sdt, email, dchi, tt);
        } else {
            return null;
        }
    }

    public String maKhachHang() {
        int row = khTable.getSelectedRow();
        String ma = String.valueOf(khTable.getValueAt(row, 0));
        
        return ma;
    }
    
    public String tenKhachHang() {
        int row = khTable.getSelectedRow();
        String ten = String.valueOf(khTable.getValueAt(row, 1));
        
        return ten;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        khTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        timkiemTxt = new javax.swing.JTextField();
        search = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        maTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tenTxt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        namRB = new javax.swing.JRadioButton();
        nuRB = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        sdtTxt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        emailTxt1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        conhdRB1 = new javax.swing.JRadioButton();
        ngunghdRB1 = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        diachiTP1 = new javax.swing.JTextPane();
        themBtn1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        khTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Tên KH", "Giới tính", "SĐT", "Email", "Địa chỉ", "Trạng thái"
            }
        ));
        khTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                khTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(khTable);

        jLabel1.setText("Tìm kiếm");

        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        jButton1.setText("Chọn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(timkiemTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(search))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(245, 245, 245)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(timkiemTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(10, 10, 10))
        );

        jTabbedPane1.addTab("Danh sách khách hàng", jPanel2);

        jLabel11.setText("Mã khách hàng:");

        jLabel12.setText("Tên khách hàng:");

        jLabel13.setText("Giới tính:");

        buttonGroup1.add(namRB);
        namRB.setSelected(true);
        namRB.setText("Nam");

        buttonGroup1.add(nuRB);
        nuRB.setText("Nữ");

        jLabel14.setText("Số điện thoại:");

        jLabel18.setText("Email:");

        jLabel19.setText("Trạng thái");

        buttonGroup2.add(conhdRB1);
        conhdRB1.setSelected(true);
        conhdRB1.setText("Còn hoạt động");

        buttonGroup2.add(ngunghdRB1);
        ngunghdRB1.setText("Ngừng hoạt động");

        jLabel20.setText("Địa chỉ:");

        jScrollPane2.setViewportView(diachiTP1);

        themBtn1.setBackground(new java.awt.Color(0, 102, 255));
        themBtn1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        themBtn1.setForeground(new java.awt.Color(255, 255, 255));
        themBtn1.setText("Thêm");
        themBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(maTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                            .addComponent(tenTxt))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addGap(63, 63, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                                .addComponent(emailTxt1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(sdtTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(namRB)
                                .addGap(18, 18, 18)
                                .addComponent(nuRB))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(conhdRB1)
                                .addGap(42, 42, 42)
                                .addComponent(ngunghdRB1)))
                        .addGap(141, 141, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(themBtn1)
                .addGap(251, 251, 251))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tenTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(namRB)
                    .addComponent(nuRB))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(sdtTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(conhdRB1)
                        .addComponent(jLabel19))
                    .addComponent(ngunghdRB1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(themBtn1)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thêm khách hàng", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themBtn1ActionPerformed
        KhachHang kh = getData();
        if (kh != null) {
            KhachHang existingKH = service.getMaKH(kh.getMa());

            if (existingKH == null) {
                int result = service.add(kh);

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    showData(service.getAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Trùng mã");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ");
        }
    }//GEN-LAST:event_themBtn1ActionPerformed

    private void khTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khTableMouseClicked
        int row = khTable.getSelectedRow();
        String tim = timkiemTxt.getText();
        String ma = String.valueOf(khTable.getValueAt(row, 0));
        String ten = String.valueOf(khTable.getValueAt(row, 1));
        getMa(ma);
        System.out.println(ma);
        System.out.println(ten);
    }//GEN-LAST:event_khTableMouseClicked

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        String tim = timkiemTxt.getText();
        ArrayList<KhachHang> listKH = serviceBH.tim(tim);
        if (listKH != null) {
            list = listKH;
            showData(list);
        }
        if (tim.equalsIgnoreCase("")) {
            list = service.getAll();
            showData(list);
        }
    }//GEN-LAST:event_searchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int row = khTable.getSelectedRow();
        String ma = String.valueOf(khTable.getValueAt(row, 0));
        String ten = String.valueOf(khTable.getValueAt(row, 1));

        JOptionPane.showMessageDialog(this, "OK ");
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(JDialogKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogKhachHang dialog = new JDialogKhachHang(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton conhdRB;
    private javax.swing.JRadioButton conhdRB1;
    private javax.swing.JTextPane diachiTP;
    private javax.swing.JTextPane diachiTP1;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JTextField emailTxt1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable khTable;
    private javax.swing.JTextField maTxt;
    private javax.swing.JButton moiBtn;
    private javax.swing.JRadioButton namRB;
    private javax.swing.JRadioButton ngunghdRB;
    private javax.swing.JRadioButton ngunghdRB1;
    private javax.swing.JRadioButton nuRB;
    private javax.swing.JTextField sdtTxt;
    private javax.swing.JButton search;
    private javax.swing.JButton suaBtn;
    private javax.swing.JTextField tenTxt;
    private javax.swing.JButton themBtn;
    private javax.swing.JButton themBtn1;
    private javax.swing.JTextField timkiemTxt;
    // End of variables declaration//GEN-END:variables
}
