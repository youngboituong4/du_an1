/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.KichThuoc;
import model.LoaiSanPham;
import model.MauSac;
import model.SanPham;
import response.LayRaNhanVien;
import service.SanPhamService;

/**
 *
 * @author Admin
 */
public class QuanLySanPhamNV extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel model1 = new DefaultTableModel();
    SanPhamService service = new SanPhamService();
    static LayRaNhanVien nhanVien = new LayRaNhanVien();
    int index = 0;
    JPanel view;
    String tenMenu;

    public QuanLySanPhamNV(LayRaNhanVien nv) {
        initComponents();
        setLocationRelativeTo(null);

        nhanVien = nv;

        view = new ThongKeFormNV();
        setView();
    }

    private void setView() {
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(view);
        jPanel1.validate();
        jPanel1.repaint();
    }

    public void setMenu() {
        switch (tenMenu) {
            case "SanPham":
                view = new SanPhamForm();
                setView();
                break;
            case "KhachHang":
                view = new KhachHangForm();
                setView();
                break;
            case "NhanVien":
                view = new NhanVienForm();
                setView();
                break;
            case "BanHang":
                view = new BanHangForm(nhanVien);
                setView();
                break;
            case "HoaDon":
                view = new HoaDonForm();
                setView();
                break;
            case "KhuyenMai":
                view = new KhuyenMaiForm();
                setView();
                break;
            case "ThongKe":
                view = new ThongKeFormNV();
                setView();
                break;
            case "DoiMatKhau":
                view = new DoiMatKhauForm();
                setView();
                break;
            default:
                break;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel16 = new javax.swing.JPanel();
        lblTrangChu = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        btnThongKe = new javax.swing.JPanel();
        lblThongKe = new javax.swing.JLabel();
        btnHoaDon = new javax.swing.JPanel();
        lblHoaDon = new javax.swing.JLabel();
        btnBanHang = new javax.swing.JPanel();
        lblBanHang = new javax.swing.JLabel();
        btnDoiMatKhau = new javax.swing.JPanel();
        lblDoiMatKhau = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JPanel();
        lblDangXuat = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnKhachHang = new javax.swing.JPanel();
        lblKhachHang = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Alone Wolves");
        setBackground(new java.awt.Color(0, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 1235, 779));
        setUndecorated(true);

        jPanel16.setBackground(new java.awt.Color(0, 102, 255));
        jPanel16.setForeground(new java.awt.Color(255, 255, 255));

        lblTrangChu.setBackground(new java.awt.Color(0, 255, 255));
        lblTrangChu.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lblTrangChu.setForeground(new java.awt.Color(255, 255, 255));
        lblTrangChu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgmain/logoAW.png"))); // NOI18N
        lblTrangChu.setText("ALONE WOLVES");
        lblTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseClicked(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2794770.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        btnThongKe.setBackground(new java.awt.Color(0, 102, 255));
        btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThongKeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThongKeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThongKeMouseExited(evt);
            }
        });

        lblThongKe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblThongKe.setForeground(new java.awt.Color(255, 255, 255));
        lblThongKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgmain/thong_ke.png"))); // NOI18N
        lblThongKe.setText("  Thống kê");

        javax.swing.GroupLayout btnThongKeLayout = new javax.swing.GroupLayout(btnThongKe);
        btnThongKe.setLayout(btnThongKeLayout);
        btnThongKeLayout.setHorizontalGroup(
            btnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnThongKeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnThongKeLayout.setVerticalGroup(
            btnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnThongKeLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblThongKe)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnHoaDon.setBackground(new java.awt.Color(0, 102, 255));
        btnHoaDon.setForeground(new java.awt.Color(0, 0, 0));
        btnHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseExited(evt);
            }
        });

        lblHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgmain/hoadon.png"))); // NOI18N
        lblHoaDon.setText("  Hóa đơn");

        javax.swing.GroupLayout btnHoaDonLayout = new javax.swing.GroupLayout(btnHoaDon);
        btnHoaDon.setLayout(btnHoaDonLayout);
        btnHoaDonLayout.setHorizontalGroup(
            btnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHoaDonLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnHoaDonLayout.setVerticalGroup(
            btnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHoaDonLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblHoaDon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnBanHang.setBackground(new java.awt.Color(0, 102, 255));
        btnBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBanHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBanHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBanHangMouseExited(evt);
            }
        });

        lblBanHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblBanHang.setForeground(new java.awt.Color(255, 255, 255));
        lblBanHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgmain/lichsu.png"))); // NOI18N
        lblBanHang.setText("   Bán hàng");

        javax.swing.GroupLayout btnBanHangLayout = new javax.swing.GroupLayout(btnBanHang);
        btnBanHang.setLayout(btnBanHangLayout);
        btnBanHangLayout.setHorizontalGroup(
            btnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBanHangLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        btnBanHangLayout.setVerticalGroup(
            btnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBanHangLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblBanHang)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnDoiMatKhau.setBackground(new java.awt.Color(0, 102, 255));
        btnDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDoiMatKhauMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDoiMatKhauMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDoiMatKhauMouseExited(evt);
            }
        });

        lblDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lblDoiMatKhau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgmain/doimatkhau.png"))); // NOI18N
        lblDoiMatKhau.setText("  Đổi mật khẩu");

        javax.swing.GroupLayout btnDoiMatKhauLayout = new javax.swing.GroupLayout(btnDoiMatKhau);
        btnDoiMatKhau.setLayout(btnDoiMatKhauLayout);
        btnDoiMatKhauLayout.setHorizontalGroup(
            btnDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDoiMatKhauLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(lblDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        btnDoiMatKhauLayout.setVerticalGroup(
            btnDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDoiMatKhauLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(lblDoiMatKhau)
                .addGap(14, 14, 14))
        );

        btnDangXuat.setBackground(new java.awt.Color(0, 102, 255));
        btnDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseExited(evt);
            }
        });

        lblDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        lblDangXuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgmain/dangxuat.png"))); // NOI18N
        lblDangXuat.setText("  Đăng xuất");

        javax.swing.GroupLayout btnDangXuatLayout = new javax.swing.GroupLayout(btnDangXuat);
        btnDangXuat.setLayout(btnDangXuatLayout);
        btnDangXuatLayout.setHorizontalGroup(
            btnDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDangXuatLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(lblDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        btnDangXuatLayout.setVerticalGroup(
            btnDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDangXuatLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblDangXuat)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 650));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnKhachHang.setBackground(new java.awt.Color(0, 102, 255));
        btnKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKhachHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKhachHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnKhachHangMouseExited(evt);
            }
        });

        lblKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lblKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgmain/khachhang.png"))); // NOI18N
        lblKhachHang.setText("  Khách hàng");
        lblKhachHang.setToolTipText("");
        lblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKhachHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblKhachHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblKhachHangMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnKhachHangLayout = new javax.swing.GroupLayout(btnKhachHang);
        btnKhachHang.setLayout(btnKhachHangLayout);
        btnKhachHangLayout.setHorizontalGroup(
            btnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnKhachHangLayout.setVerticalGroup(
            btnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnKhachHangLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(lblKhachHang)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTrangChu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnHoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBanHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThongKe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1409, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addGap(15, 15, 15))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1444, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(lblTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(507, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn thoát ?", "Thoát", 2);
        if (chon == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            return;
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btnThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseClicked
        tenMenu = "ThongKe";
        this.setMenu();
    }//GEN-LAST:event_btnThongKeMouseClicked

    private void btnHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseClicked
        tenMenu = "HoaDon";
        this.setMenu();
    }//GEN-LAST:event_btnHoaDonMouseClicked

    private void btnBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMouseClicked
        tenMenu = "BanHang";
        this.setMenu();
    }//GEN-LAST:event_btnBanHangMouseClicked

    private void btnDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoiMatKhauMouseClicked
        tenMenu = "DoiMatKhau";
        this.setMenu();
    }//GEN-LAST:event_btnDoiMatKhauMouseClicked

    private void lblTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseClicked

    }//GEN-LAST:event_lblTrangChuMouseClicked

    private void btnBanHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMouseEntered
        btnBanHang.setBackground(Color.WHITE);
        lblBanHang.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnBanHangMouseEntered

    private void btnBanHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMouseExited
        btnBanHang.setBackground(new java.awt.Color(0, 102, 255));
        lblBanHang.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnBanHangMouseExited

    private void btnHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseEntered
        btnHoaDon.setBackground(Color.WHITE);
        lblHoaDon.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnHoaDonMouseEntered

    private void btnHoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseExited
        btnHoaDon.setBackground(new java.awt.Color(0, 102, 255));
        lblHoaDon.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnHoaDonMouseExited

    private void btnThongKeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseEntered
        btnThongKe.setBackground(Color.WHITE);
        lblThongKe.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnThongKeMouseEntered

    private void btnThongKeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseExited
        btnThongKe.setBackground(new java.awt.Color(0, 102, 255));
        lblThongKe.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnThongKeMouseExited

    private void btnDoiMatKhauMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoiMatKhauMouseEntered
        btnDoiMatKhau.setBackground(Color.WHITE);
        lblDoiMatKhau.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnDoiMatKhauMouseEntered

    private void btnDoiMatKhauMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoiMatKhauMouseExited
        btnDoiMatKhau.setBackground(new java.awt.Color(0, 102, 255));
        lblDoiMatKhau.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnDoiMatKhauMouseExited

    private void btnDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseClicked
        int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn đăng xuất ?");
        if (chon == JOptionPane.YES_OPTION) {
            QuanLySanPhamNV.this.dispose();

            new DangNhap().setVisible(true);
        } else {
            return;
        }
    }//GEN-LAST:event_btnDangXuatMouseClicked

    private void btnDangXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseEntered
        btnDangXuat.setBackground(Color.WHITE);
        lblDangXuat.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnDangXuatMouseEntered

    private void btnDangXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseExited
        btnDangXuat.setBackground(new java.awt.Color(0, 102, 255));
        lblDangXuat.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnDangXuatMouseExited

    private void lblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachHangMouseClicked
        tenMenu = "KhachHang";
        this.setMenu();
    }//GEN-LAST:event_lblKhachHangMouseClicked

    private void lblKhachHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachHangMouseEntered
        btnKhachHang.setBackground(Color.WHITE);
        lblKhachHang.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblKhachHangMouseEntered

    private void lblKhachHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachHangMouseExited
        btnKhachHang.setBackground(new java.awt.Color(0, 102, 255));
        lblKhachHang.setForeground(Color.WHITE);
    }//GEN-LAST:event_lblKhachHangMouseExited

    private void btnKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMouseClicked
        tenMenu = "KhachHang";
        this.setMenu();
    }//GEN-LAST:event_btnKhachHangMouseClicked

    private void btnKhachHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMouseEntered
        btnKhachHang.setBackground(Color.WHITE);
        lblKhachHang.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnKhachHangMouseEntered

    private void btnKhachHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMouseExited
        btnKhachHang.setBackground(new java.awt.Color(0, 102, 255));
        lblKhachHang.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnKhachHangMouseExited

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
            java.util.logging.Logger.getLogger(QuanLySanPhamNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPhamNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPhamNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPhamNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLySanPhamNV(nhanVien).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnBanHang;
    private javax.swing.JPanel btnDangXuat;
    private javax.swing.JPanel btnDoiMatKhau;
    private javax.swing.JPanel btnHoaDon;
    private javax.swing.JPanel btnKhachHang;
    private javax.swing.JPanel btnThongKe;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JLabel lblBanHang;
    private javax.swing.JLabel lblDangXuat;
    private javax.swing.JLabel lblDoiMatKhau;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JLabel lblTrangChu;
    // End of variables declaration//GEN-END:variables

}
