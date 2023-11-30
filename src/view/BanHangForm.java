/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.ChiTietSanPham;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KichThuoc;
import model.LoaiSanPham;
import model.MauSac;
import model.ThuongHieu;
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
    private String loaiSanPham = "";
    private String kichThuoc = "";
    private String mau = "";
    private String chatLieu = "";
    private String thuongHieu = "";
    private String regex = "\\d+";

    public BanHangForm() {
        initComponents();

        fillToTableHoaDon(service.LocHoaDon(0));
        fillToTableChiTietSanPham(service.getAllCTSP());
        fillCbo();
        anDuLieu();
        tinhTongTien();
        //       showDataHoaDon(0);
        //       tblHoaDon.setRowSelectionInterval(index, index);
//        int ma = Integer.valueOf(txtIdHoaDon.getText());
//        fillToTableGioHang(service.getAllGioHang(ma));
        txtTienKhachDua.setText("0.0");
        txtTienChuyenKhoan.setText("0.0");
        txtGiamGia.setText("0.0");
        txtTienThua.setText("0.0");

        tblChiTietSanPham.addMouseListener(new MouseAdapter() {
            private int clickCount = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                clickCount++;
                int ID = Integer.valueOf(txtIdHoaDon.getText());
                HoaDon hd = service.getHoaDonTheoID(ID);
                if (clickCount == 2) {
                    // Xử lý sự kiện bấm 2 lần vào bảng ở đây
                    String soLuong = JOptionPane.showInputDialog("Mời nhập số lượng: ");
                    if (Pattern.matches(regex, soLuong)) {
                        clickCount = 0;
                        if (soLuong != null) {
                            soLuongMua = Integer.valueOf(soLuong);
                        }
                    } else {
                        clickCount = 0;
                        JOptionPane.showMessageDialog(null, "Dữ liệu không phù hợp !");
                        return;
                    }
                    ChiTietSanPham ctsp = readFormAddSanPham();
                    if (soLuongMua <= 0) {
                        clickCount = 0; // Reset clickCount để chờ lần bấm tiếp theo
                        JOptionPane.showMessageDialog(null, "Số lượng lớn hơn 0 !");
                        return;
                    } else if (soLuongMua > ctsp.getSoLuong()) {
                        clickCount = 0; // Reset clickCount để chờ lần bấm tiếp theo
                        JOptionPane.showMessageDialog(null, "Số lượng không được lớn hơn số lượng có !");
                        return;
                    } else if (soLuongMua <= ctsp.getSoLuong() && hd.getTrangThai() == 0 && soLuongMua >= 0) {
                        clickCount = 0; // Reset clickCount để chờ lần bấm tiếp theo
                        //ChiTietSanPham ctsp = readFormAddSanPham();
                        Integer maHD = Integer.valueOf(txtIdHoaDon.getText());
                        Integer maCTSP = Integer.valueOf(tblChiTietSanPham.getValueAt(index, 0) + "");

                        service.addGioHang(maCTSP, soLuongMua, maHD);
                        //service.addGioHang(maHD, maCTSP, donGia, soLuongMua);                    
                        tinhTongTien();
                        fillToTableChiTietSanPham(service.getAllCTSP());
                        fillToTableGioHang(service.getAllGioHang(maHD));

                        tinhTongTien();
                    } else if (hd.getTrangThai() == 1) {
                        clickCount = 0; // Reset clickCount để chờ lần bấm tiếp theo
                        JOptionPane.showMessageDialog(null, "Hóa đơn đã thanh toán !");
                        return;
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
        txtMaKhachHang.disable();
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
        Double tienThua = 0.0;
        txtIdHoaDon.setText(hd.getID() + "");
        txtMaHoaDon.setText(hd.getMaHoaDon());
        txtNgayTao.setText(String.valueOf(hd.getNgayTao()));
        txtTenNhanVien.setText(hd.getMaNhanVien());
        txtMaKhachHang.setText(hd.getMaKhachHang());
        txtTongTien.setText(String.valueOf(tongTien));
        txtGiamGia.setText(String.valueOf(tienGiamGia));
        txtTienKhachDua.setText("0.0");
        txtTienChuyenKhoan.setText("0.0");
        txtTienThua.setText("0.0");
        if (hd.getTrangThai() == 0) {
            btnThanhToan.setEnabled(true);
            btnKhachHang.setEnabled(true);
        } else if (hd.getTrangThai() == 1) {
            btnThanhToan.setEnabled(false);
            btnKhachHang.setEnabled(false);
        }
    }

    public void showDataLocHoaDon(int id, int trangThai) {
        HoaDon hd = service.showLocHoaDon(trangThai, id);
//        Double tongTien = Double.valueOf(hd.getThanhTien());
//        Double tienDua = Double.valueOf(hd.getTienKhachTra());
//        Double tienCK = Double.valueOf(hd.getTienKhachChuyenKhoan());
//        Double tienGiamGia = Double.valueOf(hd.getTienGiamGia());
//        Double tienThua = (tienDua + tienCK) - (tongTien - tienGiamGia);
        txtIdHoaDon.setText(hd.getID() + "");
        txtMaHoaDon.setText(hd.getMaHoaDon());
        txtNgayTao.setText(String.valueOf(hd.getNgayTao()));
        txtTenNhanVien.setText(hd.getMaNhanVien());
        txtMaKhachHang.setText(hd.getMaKhachHang());
//        txtTongTien.setText(String.valueOf(tongTien));
//        txtGiamGia.setText(String.valueOf(tienGiamGia));
        txtTienKhachDua.setText("0.0");
        txtTienChuyenKhoan.setText("0.0");
        txtTienThua.setText("0.0");
        if (hd.getTrangThai() == 0) {
            btnThanhToan.setEnabled(true);
            btnKhachHang.setEnabled(true);
        } else if (hd.getTrangThai() == 1) {
            btnThanhToan.setEnabled(false);
            btnKhachHang.setEnabled(false);
        }
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

    public void tinhTongTien() {
        Double tongTien = 0.0;
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            Double thanhTien = Double.valueOf(tblGioHang.getValueAt(i, 5) + "");

            tongTien = tongTien + thanhTien;
        }
        txtTongTien.setText(String.valueOf(tongTien));
    }

    public void fillCbo() {
        cboMau.removeAllItems();
        cboMau.addItem("Tất cả");
        List<MauSac> listMau = service.getAllMau();
        for (MauSac mauSac : listMau) {
            cboMau.addItem(mauSac.getTenMau());
        }

        cboKichThuoc.removeAllItems();
        cboKichThuoc.addItem("Tất cả");
        List<KichThuoc> listKT = service.getAllKT();
        for (KichThuoc kichThuoc : listKT) {
            cboKichThuoc.addItem(kichThuoc.getTenKichThuoc());
        }

        cboChatLieu.removeAllItems();
        cboChatLieu.addItem("Tất cả");
        List<ChatLieu> listCL = service.getAllCL();
        for (ChatLieu chatLieu : listCL) {
            cboChatLieu.addItem(chatLieu.getTenChatLieu());
        }

        cboLoaiSP.removeAllItems();
        cboLoaiSP.addItem("Tất cả");
        List<LoaiSanPham> listLoai = service.getAllLSP();
        for (LoaiSanPham loaiSanPham : listLoai) {
            cboLoaiSP.addItem(loaiSanPham.getLoaiSanPham());
        }

        cboThuongHieu.removeAllItems();
        cboThuongHieu.addItem("Tất cả");
        List<ThuongHieu> listTH = service.getAllTH();
        for (ThuongHieu thuongHieu : listTH) {
            cboThuongHieu.addItem(thuongHieu.getTenThuongHieu());
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChiTietSanPham = new javax.swing.JTable();
        cboLoaiSP = new javax.swing.JComboBox<>();
        cboMau = new javax.swing.JComboBox<>();
        cboChatLieu = new javax.swing.JComboBox<>();
        cboThuongHieu = new javax.swing.JComboBox<>();
        cboKichThuoc = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        rdoCho = new javax.swing.JRadioButton();
        rdoAll = new javax.swing.JRadioButton();
        rdoDone = new javax.swing.JRadioButton();
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
        btnThanhToan = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtTienChuyenKhoan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        txtIdHoaDon = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblKhachHang = new javax.swing.JLabel();
        btnKhachHang = new javax.swing.JButton();
        lblTenNhanVien = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Hóa đơn chi tiết", "ID Hóa đơn", "ID Chi tiết sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

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

        btnXoa.setBackground(new java.awt.Color(0, 102, 255));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 102, 255));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa)
                    .addComponent(btnSua))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSua))
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

        cboLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSPActionPerformed(evt);
            }
        });

        cboMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Xanh", "Do", "Tim", "Vang" }));
        cboMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMauActionPerformed(evt);
            }
        });

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cotton", "Nỉ" }));
        cboChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChatLieuActionPerformed(evt);
            }
        });

        cboThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "L", "XL", "XXL" }));
        cboThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThuongHieuActionPerformed(evt);
            }
        });

        cboKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "L", "XL", "XXL" }));
        cboKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKichThuocActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 255));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Reset");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setText("Lọc");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)))
                .addGap(11, 11, 11))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboChatLieu, cboKichThuoc, cboLoaiSP, cboMau, cboThuongHieu});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(cboMau, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tạo hóa đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoCho);
        rdoCho.setSelected(true);
        rdoCho.setText("Chờ thanh toán");
        rdoCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoChoMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdoAll);
        rdoAll.setText("Tất cả");
        rdoAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoAllMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdoDone);
        rdoDone.setText("Đã thanh toán");
        rdoDone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoDoneMouseClicked(evt);
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
                .addComponent(rdoCho)
                .addGap(18, 18, 18)
                .addComponent(rdoDone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdoAll)
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
                    .addComponent(rdoCho)
                    .addComponent(rdoAll)
                    .addComponent(rdoDone))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        jLabel1.setText("Mã hóa đơn");

        jLabel2.setText("Ngày tạo");

        jLabel3.setText("Mã nhân viên");

        jLabel4.setText("Tổng tiền");

        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyTyped(evt);
            }
        });

        jLabel5.setText("Tiền khách đưa");

        jLabel6.setText("Tiền thừa");

        btnThanhToan.setBackground(new java.awt.Color(0, 102, 255));
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel7.setText("Tiền chuyển khoản");

        txtTienChuyenKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienChuyenKhoanKeyTyped(evt);
            }
        });

        jLabel8.setText("Tiền giảm giá");

        jLabel9.setText("Mã khách hàng");

        jLabel10.setText("ID Hóa đơn");

        jLabel11.setText("Tên khách hàng");

        lblKhachHang.setText("...");

        btnKhachHang.setText("KH");
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblKhachHang)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtTienChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnThanhToan))))
                            .addComponent(jLabel10))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel9))
                                .addGap(43, 43, 43)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(txtIdHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(txtMaKhachHang))
                                .addGap(18, 18, 18)
                                .addComponent(btnKhachHang)))
                        .addGap(19, 61, Short.MAX_VALUE))))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtGiamGia, txtIdHoaDon, txtMaHoaDon, txtMaKhachHang, txtNgayTao, txtTenNhanVien, txtTienChuyenKhoan, txtTienKhachDua, txtTienThua, txtTongTien});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
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
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(btnKhachHang))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblKhachHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btnThanhToan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTenNhanVien.setText("Thinh");

        jLabel13.setText("jLabel13");

        jLabel14.setText("jLabel14");

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
                                .addGap(48, 48, 48)
                                .addComponent(lblTenNhanVien)
                                .addGap(78, 78, 78)
                                .addComponent(jLabel13)
                                .addGap(64, 64, 64)
                                .addComponent(jLabel14)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenNhanVien)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(110, 110, 110))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        Integer mahd = Integer.valueOf(txtIdHoaDon.getText());
        int chon;
        index = tblGioHang.getSelectedRow();
        HoaDon hd = service.getHoaDonTheoID(mahd);
        int idsp = (int) tblGioHang.getValueAt(index, 2);
        ChiTietSanPham ctsp = service.readFormCTSP(idsp);
        chon = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa sản phẩm này ?");
        if (chon == JOptionPane.YES_OPTION) {
            if (service.deleteGioHang(idsp, mahd) > 0) {
                JOptionPane.showMessageDialog(this, "Xóa thành công !");
                fillToTableChiTietSanPham(service.getAllCTSP());
                fillToTableGioHang(service.getAllGioHang(mahd));
                tinhTongTien();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại !");
            }
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSanPhamMouseClicked

    }//GEN-LAST:event_tblChiTietSanPhamMouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        index = tblHoaDon.getSelectedRow();

        Integer maHD = Integer.valueOf(tblHoaDon.getValueAt(index, 0) + "");

        fillToTableGioHang(service.getAllGioHang(maHD));

        if (rdoAll.isSelected()) {
            showDataHoaDon(index);
        }
        if (rdoCho.isSelected()) {
            showDataLocHoaDon(maHD, 0);
        }
        if (rdoDone.isSelected()) {
            showDataLocHoaDon(maHD, 1);
        }

        tinhTongTien();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        HoaDon hd = readFormHoaDon();
        try {
            if (service.addHoaDon(hd) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công !");
                fillToTableHoaDon(service.LocHoaDon(0));
            } else {
                JOptionPane.showMessageDialog(this, "Thêm hóa đơn lỗi !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void rdoChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoChoMouseClicked
        fillToTableHoaDon(service.LocHoaDon(0));
    }//GEN-LAST:event_rdoChoMouseClicked

    private void rdoAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoAllMouseClicked
        fillToTableHoaDon(service.getAllHoaDon());
    }//GEN-LAST:event_rdoAllMouseClicked

    private void rdoDoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoDoneMouseClicked

        fillToTableHoaDon(service.LocHoaDon(1));

    }//GEN-LAST:event_rdoDoneMouseClicked

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

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        Double tienDua;
        Double tienCK;       
        if(Pattern.matches(regex, txtTienKhachDua.getText())){
            tienDua = Double.valueOf(txtTienKhachDua.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ !");
            return;
        }
        if(Pattern.matches(regex, txtTienKhachDua.getText())){
            tienCK = Double.valueOf(txtTienChuyenKhoan.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ !");
            return;
        }      
        Double tienGG = Double.valueOf(txtGiamGia.getText());
        Double tongTien = 0.0;
        Double tienThua = 0.0;
        int id = Integer.valueOf(txtIdHoaDon.getText());
        String makh = txtMaKhachHang.getText();
        String tenKhach = lblKhachHang.getText();
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            Double thanhTien = Double.valueOf(tblGioHang.getValueAt(i, 5) + "");

            tongTien = tongTien + thanhTien;
        }

        tienThua = (tienDua + tienCK + tienGG) - tongTien;

        if (tienThua >= 0 && tongTien > 0 && !tenKhach.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thanh toán thành công !");
            service.updateHoaDon(tienDua, tienCK, tienThua, tongTien, id, makh);
            fillToTableHoaDon(service.LocHoaDon(1));
            Integer maHD = Integer.valueOf(tblGioHang.getValueAt(1, 1) + "");
            index = (service.LocHoaDon(1).size()) - 1;
            tblHoaDon.setRowSelectionInterval(index, index);
            lblKhachHang.setText("...");
            showDataLocHoaDon(maHD, 1);
            btnThanhToan.setEnabled(false);
            btnKhachHang.setEnabled(false);
            rdoDone.setSelected(true);
        } else if (tongTien == 0) {
            JOptionPane.showMessageDialog(this, "Không thể thanh toán");
        } else {
            JOptionPane.showMessageDialog(this, "Thanh toán thất bại !");
            JOptionPane.showMessageDialog(this, "Yêu cầu trả thêm tiền !");
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

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

    private void tblGioHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblGioHangKeyReleased
        int row = tblGioHang.getSelectedRow();
        Integer soLuongCurrent = null;
        row = tblGioHang.getSelectedRow();
        if (row >= 0) {
            soLuongCurrent = Integer.parseInt(tblGioHang.getValueAt(row, 4).toString());
        }
        System.out.println("" + soLuongCurrent);
    }//GEN-LAST:event_tblGioHangKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        fillToTableChiTietSanPham(service.getAllCTSP());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cboLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSPActionPerformed
        loaiSanPham = (String) cboLoaiSP.getSelectedItem();
        System.out.print(loaiSanPham);
        if (loaiSanPham == null) {
            loaiSanPham = "";
        } else if (loaiSanPham.equals("Tất cả")) {
            loaiSanPham = "";
            System.out.print(loaiSanPham);
        } else {
            loaiSanPham = String.valueOf(cboLoaiSP.getSelectedItem());
        }
        fillToTableChiTietSanPham(service.LocSanPham(loaiSanPham, kichThuoc, mau, chatLieu, thuongHieu));
    }//GEN-LAST:event_cboLoaiSPActionPerformed

    private void cboMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMauActionPerformed
        mau = (String) cboMau.getSelectedItem();
        System.out.println(mau);
        if (mau == null) {
            mau = "";
        } else if (mau.equals("Tất cả")) {
            mau = "";
        } else {
            mau = String.valueOf(cboMau.getSelectedItem());
        }
        fillToTableChiTietSanPham(service.LocSanPham(loaiSanPham, kichThuoc, mau, chatLieu, thuongHieu));
    }//GEN-LAST:event_cboMauActionPerformed

    private void cboChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChatLieuActionPerformed
        chatLieu = (String) cboChatLieu.getSelectedItem();
        System.out.println(chatLieu);
        if (chatLieu == null) {
            chatLieu = "";
        } else if (chatLieu.equals("Tất cả")) {
            chatLieu = "";
        } else {
            chatLieu = String.valueOf(cboChatLieu.getSelectedItem());
        }
        fillToTableChiTietSanPham(service.LocSanPham(loaiSanPham, kichThuoc, mau, chatLieu, thuongHieu));
    }//GEN-LAST:event_cboChatLieuActionPerformed

    private void cboThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThuongHieuActionPerformed
        thuongHieu = (String) cboThuongHieu.getSelectedItem();
        System.out.println(thuongHieu);
        if (thuongHieu == null) {
            thuongHieu = "";
        } else if (thuongHieu.equals("Tất cả")) {
            thuongHieu = "";
        } else {
            thuongHieu = String.valueOf(cboThuongHieu.getSelectedItem());
        }
        fillToTableChiTietSanPham(service.LocSanPham(loaiSanPham, kichThuoc, mau, chatLieu, thuongHieu));
    }//GEN-LAST:event_cboThuongHieuActionPerformed

    private void cboKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKichThuocActionPerformed
        kichThuoc = (String) cboKichThuoc.getSelectedItem();
        System.out.println(kichThuoc);
        if (kichThuoc == null) {
            kichThuoc = "";
        } else if (kichThuoc.equals("Tất cả")) {
            kichThuoc = "";
        } else {
            kichThuoc = String.valueOf(cboKichThuoc.getSelectedItem());
        }
        fillToTableChiTietSanPham(service.LocSanPham(loaiSanPham, kichThuoc, mau, chatLieu, thuongHieu));
    }//GEN-LAST:event_cboKichThuocActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked

    }//GEN-LAST:event_jButton4MouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        Integer mahd = Integer.valueOf(txtIdHoaDon.getText());
        index = tblGioHang.getSelectedRow();
        int chon;
        HoaDon hd = service.getHoaDonTheoID(mahd);
        int idsp = (int) tblGioHang.getValueAt(index, 2);
        int slgh = (int) tblGioHang.getValueAt(index, 4);
        ChiTietSanPham ctsp = service.readFormCTSP(idsp);
        String sl = JOptionPane.showInputDialog("Nhập số lượng: ");
        Integer soLuong = Integer.valueOf(sl);
        if (soLuong <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0 !");
            return;
        } else if (soLuong > ctsp.getSoLuong() + slgh) {
            JOptionPane.showMessageDialog(this, "Số lượng không được lớn hơn số lượng có !");
            return;
        } else {
            service.updateSoLuong(idsp, soLuong, mahd);
            tinhTongTien();
            fillToTableChiTietSanPham(service.getAllCTSP());
            fillToTableGioHang(service.getAllGioHang(mahd));
            tinhTongTien();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        JDialogKhachHang dialogKH = new JDialogKhachHang((Frame) SwingUtilities.getWindowAncestor(this), true);
        dialogKH.setVisible(true);

        String ma = dialogKH.maKhachHang();
        String ten = dialogKH.tenKhachHang();

        txtMaKhachHang.setText(ma);
        lblKhachHang.setText(ten);
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        loaiSanPham = (String) cboLoaiSP.getSelectedItem();
        kichThuoc = (String) cboKichThuoc.getSelectedItem();
        mau = (String) cboMau.getSelectedItem();
        chatLieu = (String) cboChatLieu.getSelectedItem();
        thuongHieu = (String) cboThuongHieu.getSelectedItem();

        fillToTableChiTietSanPham(service.LocSanPham(loaiSanPham, kichThuoc, mau, chatLieu, thuongHieu));
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JComboBox<String> cboMau;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JRadioButton rdoAll;
    private javax.swing.JRadioButton rdoCho;
    private javax.swing.JRadioButton rdoDone;
    private javax.swing.JTable tblChiTietSanPham;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtIdHoaDon;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTienChuyenKhoan;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
