/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import model.ChiTietSanPham;
import model.HoaDon;
import model.HoaDonChiTiet;
import service.BanHangService;

public class BanHangForm extends javax.swing.JPanel {

    /**
     * Creates new form BanHangForm
     */
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel model1 = new DefaultTableModel();
    DefaultTableModel model2 = new DefaultTableModel();
    BanHangService service = new BanHangService();
    int index = 0;
    int soLuongMua;
    private boolean selected;
    
    public BanHangForm() {
        initComponents();

        fillToTableHoaDon(service.getAllHoaDon());
        fillToTableChiTietSanPham(service.getAllCTSP());

        anDuLieu();
        tinhTongTien();
        showDataHoaDon(0);
        int ma = Integer.valueOf(txtIdHoaDon.getText());
        fillToTableGioHang(service.getAllGioHang(ma));

        tblChiTietSanPham.addMouseListener(new MouseAdapter() {
            private int clickCount = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                clickCount++;
                if (clickCount == 2) {
                    // Xử lý sự kiện bấm 2 lần vào bảng ở đây     
                    String soLuong = JOptionPane.showInputDialog("Mời nhập số lượng: ");
                    soLuongMua = Integer.valueOf(soLuong);
                    ChiTietSanPham ctsp = readFormAddSanPham();
                    if (soLuongMua <= 0) {
                        clickCount = 0; // Reset clickCount để chờ lần bấm tiếp theo
                        JOptionPane.showMessageDialog(null, "Số lượng lớn hơn 0 !");
                        return;
                    } else if (soLuongMua > ctsp.getSoLuong()) {
                        clickCount = 0; // Reset clickCount để chờ lần bấm tiếp theo
                        JOptionPane.showMessageDialog(null, "Số lượng không được lớn hơn số lượng có !");
                        return;
                    } else {
                        clickCount = 0; // Reset clickCount để chờ lần bấm tiếp theo
                        //ChiTietSanPham ctsp = readFormAddSanPham();
                        Integer maHD = Integer.valueOf(txtIdHoaDon.getText());
                        Integer maCTSP = Integer.valueOf(tblChiTietSanPham.getValueAt(index, 0) + "");
                        Double donGia = Double.valueOf(tblChiTietSanPham.getValueAt(index, 8) + "");
                        HoaDonChiTiet hdct = readFormHDCT();

                        service.addGioHang(maCTSP, soLuongMua, maHD);
                        //service.addGioHang(maHD, maCTSP, donGia, soLuongMua);                    
                        tinhTongTien();
                        fillToTableChiTietSanPham(service.getAllCTSP());
                        fillToTableGioHang(service.getAllGioHang(maHD));

                        tinhTongTien();
                    }
                } 
            }
        });
    }
    
    public void anDuLieu() {
        txtIdHoaDon.disable();
        txtMaHoaDon.disable();
        txtNgayTao.disable();
        txtTenNhanVien.disable();
        txtTongTien.disable();
        txtGiamGia.disable();
        txtTienThua.disable();
    }

    public void fillToTableHoaDon(List<HoaDon> list) {
        model.setRowCount(0);
        model = (DefaultTableModel) tblHoaDon.getModel();
        for (HoaDon hoaDon : list) {
            model.addRow(hoaDon.toDataRowHoaDon());
        }
    }

    public void fillToTableChiTietSanPham(List<ChiTietSanPham> list) {
        model1.setRowCount(0);
        model1 = (DefaultTableModel) tblChiTietSanPham.getModel();
        for (ChiTietSanPham chiTietSanPham : list) {
            model1.addRow(chiTietSanPham.toDataRowCTSPBanHang());
        }
    }

    public void fillToTableGioHang(List<HoaDonChiTiet> list) {
        model2.setRowCount(0);
        model2 = (DefaultTableModel) tblGioHang.getModel();
        for (HoaDonChiTiet hoaDonChiTiet : list) {
            model2.addRow(hoaDonChiTiet.toDataRow());
        }
    }

    public void showDataHoaDon(int index) {
        HoaDon hd = service.getAllShowHoaDon().get(index);
        Double tongTien = Double.valueOf(hd.getThanhTien());
        Double tienDua = Double.valueOf(hd.getTienKhachTra());
        Double tienCK = Double.valueOf(hd.getTienKhachChuyenKhoan());
        Double tienGiamGia = Double.valueOf(hd.getTienGiamGia());
        Double tienThua = (tienDua + tienCK + tienGiamGia) - (tongTien);
        txtIdHoaDon.setText(hd.getID() + "");
        txtMaHoaDon.setText(hd.getMaHoaDon());
        txtNgayTao.setText(String.valueOf(hd.getNgayTao()));
        txtTenNhanVien.setText(hd.getMaNhanVien());
        txtTenKhachHang.setText(hd.getMaKhachHang());
        txtTongTien.setText(String.valueOf(tongTien));
        txtGiamGia.setText(String.valueOf(tienGiamGia));
        txtTienKhachDua.setText(String.valueOf(tienDua));
        txtTienChuyenKhoan.setText(String.valueOf(tienCK));
        txtTienThua.setText(String.valueOf(tienThua));
    }

    public void showDataLocHoaDon(int index, int id) {
        HoaDon hd = service.LocHoaDon1(id).get(index);
        Double tongTien = Double.valueOf(hd.getThanhTien());
        Double tienDua = Double.valueOf(hd.getTienKhachTra());
        Double tienCK = Double.valueOf(hd.getTienKhachChuyenKhoan());
        Double tienGiamGia = Double.valueOf(hd.getTienGiamGia());
        Double tienThua = (tienDua + tienCK) - (tongTien - tienGiamGia);
        txtIdHoaDon.setText(hd.getID() + "");
        txtMaHoaDon.setText(hd.getMaHoaDon());
        txtNgayTao.setText(String.valueOf(hd.getNgayTao()));
        txtTenNhanVien.setText(hd.getMaNhanVien());
        txtTenKhachHang.setText(hd.getMaKhachHang());
        txtTongTien.setText(String.valueOf(tongTien));
        txtGiamGia.setText(String.valueOf(tienGiamGia));
        txtTienKhachDua.setText(String.valueOf(tienDua));
        txtTienChuyenKhoan.setText(String.valueOf(tienCK));
        txtTienThua.setText(String.valueOf(tienThua));
    }

    HoaDon readFormHoaDon() {
        String ten = lblTenNhanVien.getText();
        Integer tt = 0;

        return new HoaDon(ten, tt);
    }

    ChiTietSanPham readFormAddSanPham() {
        index = tblChiTietSanPham.getSelectedRow();
        Integer maCTSP = Integer.valueOf(tblChiTietSanPham.getValueAt(index, 0) + "");
        String maSP = String.valueOf(tblChiTietSanPham.getValueAt(index, 1));
        String tenSP = String.valueOf(tblChiTietSanPham.getValueAt(index, 2));
        Double donGia = Double.valueOf(tblChiTietSanPham.getValueAt(index, 8) + "");
        Integer soLuongTon = Integer.valueOf(tblChiTietSanPham.getValueAt(index, 9) + "");

        return new ChiTietSanPham(maCTSP, maSP, tenSP, donGia, soLuongTon);
    }

    HoaDonChiTiet readFormHDCT() {
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            Integer idHDCT = Integer.valueOf(tblGioHang.getValueAt(i, 0) + "");
            Integer idHD = Integer.valueOf(tblGioHang.getValueAt(i, 1) + "");
            Integer idCTSP = Integer.valueOf(tblGioHang.getValueAt(i, 2) + "");
            return new HoaDonChiTiet(idHDCT, idHD, idCTSP);
        }
        return null;
    }

    public void tinhTongTien() {
        Double tongTien = 0.0;
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            Double thanhTien = Double.valueOf(tblGioHang.getValueAt(i, 5) + "");

            tongTien = tongTien + thanhTien;
        }
        txtTongTien.setText(String.valueOf(tongTien));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChiTietSanPham = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtTienChuyenKhoan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        txtIdHoaDon = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lblTenNhanVien = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Hóa đơn chi tiết", "ID Hóa đơn", "ID Chi tiết sản phẩm", "Đơn giá", "Số lượng", "Thành tiền", "Select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblGioHangKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblGioHang);

        jCheckBox1.setText("Tất cả");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jCheckBox1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jCheckBox1)
                        .addGap(33, 33, 33)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        tblChiTietSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CTSP", "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Kích thước", "Màu sắc", "Chất liệu", "Thương hiệu", "Đơn giá", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblChiTietSanPham);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Hóa đơn", "Mã hóa đơn", "Ngày tạo", "Mã nhân viên", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDon);

        jButton1.setText("Tạo hóa đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jRadioButton1.setText("Chờ thanh toán");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });

        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Tất cả");
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });

        jRadioButton3.setText("Đã thanh toán");
        jRadioButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        jLabel1.setText("Mã hóa đơn");

        jLabel2.setText("Ngày tạo");

        jLabel3.setText("Tên nhân viên");

        jLabel4.setText("Tổng tiền");

        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyTyped(evt);
            }
        });

        jLabel5.setText("Tiền khách đưa");

        jLabel6.setText("Tiền thừa");

        jButton2.setText("Thanh toán");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Tiền chuyển khoản");

        txtTienChuyenKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienChuyenKhoanKeyTyped(evt);
            }
        });

        jLabel8.setText("Tiền giảm giá");

        jLabel9.setText("Tên khách hàng");

        jLabel10.setText("ID Hóa đơn");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jButton2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(77, 77, 77)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel9)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtGiamGia)
                                        .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtTenKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                                    .addComponent(txtTienChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2))
        );

        lblTenNhanVien.setText("Thinh");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(177, 177, 177)
                                .addComponent(lblTenNhanVien)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTenNhanVien)
                        .addGap(69, 69, 69)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        index = tblGioHang.getSelectedRow();
        Integer mahd = Integer.valueOf(txtIdHoaDon.getText());
        Integer macthd = Integer.valueOf(tblGioHang.getValueAt(index, 0) + "");
        int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa sản phẩm này ?");
        if (chon == JOptionPane.YES_OPTION) {
            if (service.deleteGioHang(macthd) > 0) {
                JOptionPane.showMessageDialog(this, "Xóa thành công !");
                fillToTableChiTietSanPham(service.getAllCTSP());
                fillToTableGioHang(service.getAllGioHang(mahd));
                tinhTongTien();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại !");
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSanPhamMouseClicked

    }//GEN-LAST:event_tblChiTietSanPhamMouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        index = tblHoaDon.getSelectedRow();

        Integer maHD = Integer.valueOf(tblHoaDon.getValueAt(index, 0) + "");

        fillToTableGioHang(service.getAllGioHang(maHD));

        showDataHoaDon(index);

        tinhTongTien();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        HoaDon hd = readFormHoaDon();
        try {
            if (service.addHoaDon(hd) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công !");
                fillToTableHoaDon(service.getAllHoaDon());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm hóa đơn lỗi !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
        fillToTableHoaDon(service.LocHoaDon(0));
    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseClicked
        fillToTableHoaDon(service.getAllHoaDon());
    }//GEN-LAST:event_jRadioButton2MouseClicked

    private void jRadioButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton3MouseClicked
        fillToTableHoaDon(service.LocHoaDon(1));
    }//GEN-LAST:event_jRadioButton3MouseClicked

    private void txtTienKhachDuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyTyped
        Double tienCK = 0.0;
        Double tienDua = 0.0;

        if (txtTienKhachDua.getText().equals("")) {
            txtTienKhachDua.setText("0");
        } else {
            tienDua = Double.valueOf(txtTienKhachDua.getText());
        }

        if (txtTienChuyenKhoan.getText().equals("")) {
            txtTienChuyenKhoan.setText("0");
        } else {
            tienCK = Double.valueOf(txtTienChuyenKhoan.getText());
        }
        Double tienGG = Double.valueOf(txtGiamGia.getText());
        Double tongTien = 0.0;
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            Double thanhTien = Double.valueOf(tblGioHang.getValueAt(i, 5) + "");

            tongTien = tongTien + thanhTien;
        }
        Double tienThua = (tienDua + tienCK + tienGG) - tongTien;
        txtTienThua.setText(String.valueOf(tienThua));
    }//GEN-LAST:event_txtTienKhachDuaKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Double tienDua = Double.valueOf(txtTienKhachDua.getText());
        Double tienCK = Double.valueOf(txtTienChuyenKhoan.getText());
        Double tienGG = Double.valueOf(txtGiamGia.getText());
        Double tongTien = 0.0;
        Double tienThua = 0.0;
        int id = Integer.valueOf(txtIdHoaDon.getText());
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            Double thanhTien = Double.valueOf(tblGioHang.getValueAt(i, 5) + "");

            tongTien = tongTien + thanhTien;
        }

        tienThua = (tienDua + tienCK + tienGG) - tongTien;

        if (tienThua >= 0) {
            JOptionPane.showMessageDialog(this, "Thanh toán thành công !");
            service.updateHoaDon(tienDua, tienCK, tienThua, id);
            fillToTableHoaDon(service.getAllHoaDon());

        } else {
            JOptionPane.showMessageDialog(this, "Thanh toán thất bại !");
            JOptionPane.showMessageDialog(this, "Yêu cầu trả thêm tiền !");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtTienChuyenKhoanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienChuyenKhoanKeyTyped
        Double tienCK = 0.0;
        Double tienDua = 0.0;

        if (txtTienKhachDua.getText().equals("")) {
            txtTienKhachDua.setText("0");
        } else {
            tienDua = Double.valueOf(txtTienKhachDua.getText());
        }

        if (txtTienChuyenKhoan.getText().equals("")) {
            txtTienChuyenKhoan.setText("0");
        } else {
            tienCK = Double.valueOf(txtTienChuyenKhoan.getText());
        }
        Double tienGG = Double.valueOf(txtGiamGia.getText());
        Double tongTien = 0.0;
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            Double thanhTien = Double.valueOf(tblGioHang.getValueAt(i, 5) + "");

            tongTien = tongTien + thanhTien;
        }
        Double tienThua = (tienDua + tienCK + tienGG) - tongTien;
        txtTienThua.setText(String.valueOf(tienThua));
    }//GEN-LAST:event_txtTienChuyenKhoanKeyTyped

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            
        }
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (!selected) {
            for (int i = 0; i < tblGioHang.getRowCount(); i++) {
                tblGioHang.setValueAt(true, i, 6);
            }
            selected = true;
        } else {
            for (int i = 0; i < tblGioHang.getRowCount(); i++) {
                tblGioHang.setValueAt(false, i, 6);
            }
            selected = false;
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void tblGioHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblGioHangKeyReleased
        int row = tblGioHang.getSelectedRow();
        Integer soLuongCurrent = null;
        row = tblGioHang.getSelectedRow();
        if (row >= 0) {
            soLuongCurrent = Integer.parseInt(tblGioHang.getValueAt(row, 4).toString());
        }
        System.out.println(""+soLuongCurrent);
    }//GEN-LAST:event_tblGioHangKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JTable tblChiTietSanPham;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtIdHoaDon;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTienChuyenKhoan;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
