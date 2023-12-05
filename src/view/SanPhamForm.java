/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.table.DefaultTableModel;
import service.SanPhamService;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.ChatLieu;
import model.ChiTietSanPham;
import model.KichThuoc;
import model.LoaiSanPham;
import model.MauSac;
import model.SanPham;
import model.ThuongHieu;
import view.QuanLySanPham;
import service.SanPhamService;

public class SanPhamForm extends javax.swing.JPanel {

    /**
     * Creates new form SanPhamForm
     */
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel model1 = new DefaultTableModel();
    SanPhamService service = new SanPhamService();
    int index = 0;
    int page = 0;
    int demsotrang = 1;
    int sotrang = 0;
    double tongtrang;
    JDialog view;
    String tenMenu;

    public SanPhamForm() {
        initComponents();

        jpSanPham.setVisible(true);
        jpChiTiet.setVisible(false);
        fillCbo();
        fillToTableSanPham(service.PhanTrangSP(0));
        showDataSP(index);

        tongtrang = Math.ceil((double) service.getAllSP().size() / 5);
        sotrang = (int) tongtrang;
        lblSoTrang.setText(String.valueOf(demsotrang + " / " + sotrang));
    }

    public void fillCbo() {
        cboMau.removeAllItems();
        List<MauSac> listMau = service.getAllMau();
        for (MauSac mauSac : listMau) {
            cboMau.addItem(mauSac.getTenMau());
        }

        cboKichThuoc.removeAllItems();
        List<KichThuoc> listKT = service.getAllKT();
        for (KichThuoc kichThuoc : listKT) {
            cboKichThuoc.addItem(kichThuoc.getTenKichThuoc());
        }

        cboChatLieu.removeAllItems();
        List<ChatLieu> listCL = service.getAllCL();
        for (ChatLieu chatLieu : listCL) {
            cboChatLieu.addItem(chatLieu.getTenChatLieu());
        }

        cboLoaiSP.removeAllItems();
        List<LoaiSanPham> listLoai = service.getAllLSP();
        for (LoaiSanPham loaiSanPham : listLoai) {
            cboLoaiSP.addItem(loaiSanPham.getLoaiSanPham());
        }

        cboThuongHieu.removeAllItems();
        List<ThuongHieu> listTH = service.getAllTH();
        for (ThuongHieu thuongHieu : listTH) {
            cboThuongHieu.addItem(thuongHieu.getTenThuongHieu());
        }
    }

    public void fillCboMau() {
        cboMau.removeAllItems();
        String ma = txtMaSP.getText();
        ChiTietSanPham ctsp = service.getMaSP(ma).get(index);
        List<MauSac> listMau = service.getAllMau();
        for (MauSac mauSac : listMau) {
            cboMau.addItem(mauSac.getTenMau());
        }
        cboMau.setSelectedItem(ctsp.getMaMauSac());
    }

    public void fillCboKichThuoc() {
        cboKichThuoc.removeAllItems();
        String ma = txtMaSP.getText();
        ChiTietSanPham ctsp = service.getMaSP(ma).get(index);
        List<KichThuoc> listKT = service.getAllKT();
        for (KichThuoc kichThuoc : listKT) {
            cboKichThuoc.addItem(kichThuoc.getTenKichThuoc());
        }
        cboKichThuoc.setSelectedItem(ctsp.getMaChatLieu());
    }

    public void fillCboCL() {
        cboChatLieu.removeAllItems();
        String ma = txtMaSP.getText();
        ChiTietSanPham ctsp = service.getMaSP(ma).get(index);
        List<ChatLieu> listCL = service.getAllCL();
        for (ChatLieu chatLieu : listCL) {
            cboChatLieu.addItem(chatLieu.getTenChatLieu());
        }
        cboChatLieu.setSelectedItem(ctsp.getMaChatLieu());
    }

    public void fillCboLSP() {
        cboLoaiSP.removeAllItems();
        String ma = txtMaSP.getText();
        ChiTietSanPham ctsp = service.getMaSP(ma).get(index);
        List<LoaiSanPham> listLoai = service.getAllLSP();
        for (LoaiSanPham loaiSanPham : listLoai) {
            cboLoaiSP.addItem(loaiSanPham.getLoaiSanPham());
        }
        cboLoaiSP.setSelectedItem(ctsp.getMaLoai());
    }

    public void fillCboTH() {
        cboThuongHieu.removeAllItems();
        String ma = txtMaSP.getText();
        ChiTietSanPham ctsp = service.getMaSP(ma).get(index);
        List<ThuongHieu> listTH = service.getAllTH();
        for (ThuongHieu thuongHieu : listTH) {
            cboThuongHieu.addItem(thuongHieu.getTenThuongHieu());
        }
        cboThuongHieu.setSelectedItem(ctsp.getMaThuongHieu());
    }

    public void fillToTableSanPham(List<SanPham> list) {
        model.setRowCount(0);
        model = (DefaultTableModel) tblSanPham.getModel();
        for (SanPham sanPham : list) {
            model.addRow(sanPham.toDataRowSP());
        }
    }

    public void fillToTableChiTietSanPham(List<ChiTietSanPham> list) {
        model.setRowCount(0);
        model = (DefaultTableModel) tblChiTietSanPham.getModel();
        for (ChiTietSanPham chiTietSanPham : list) {
            model.addRow(chiTietSanPham.toDataRowCTSP());
        }
    }

    public void clearSanPham() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtMoTa.setText("");
    }

    ChiTietSanPham readFormCTSP() {
        int stt = Integer.parseInt(lblSTT.getText());
        String ma = txtMaSP.getText();
        String ten = txtTenSP.getText();
        String loai = String.valueOf(cboLoaiSP.getSelectedItem());
        String kt = String.valueOf(cboKichThuoc.getSelectedItem());
        String mau = String.valueOf(cboMau.getSelectedItem());
        String cl = String.valueOf(cboChatLieu.getSelectedItem());
        String th = String.valueOf(cboThuongHieu.getSelectedItem());
        double gia = Double.valueOf(txtDonGia.getText());
        int sl = Integer.parseInt(txtSoLuong.getText());

        return new ChiTietSanPham(stt, ma, ten, loai, kt, mau, cl, th, gia, sl);
    }
    
    ChiTietSanPham readFormAddCTSP() {
        String ma = txtMaSP.getText();
        String ten = txtTenSP.getText();
        String loai = String.valueOf(cboLoaiSP.getSelectedItem());
        String kt = String.valueOf(cboKichThuoc.getSelectedItem());
        String mau = String.valueOf(cboMau.getSelectedItem());
        String cl = String.valueOf(cboChatLieu.getSelectedItem());
        String th = String.valueOf(cboThuongHieu.getSelectedItem());
        double gia = Double.valueOf(txtDonGia.getText());
        int sl = Integer.parseInt(txtSoLuong.getText());

        return new ChiTietSanPham(WIDTH, ma, ten, loai, kt, mau, cl, th, gia, sl);
    }

    SanPham readFormSP() {
        String ma = txtMaSP.getText();
        String ten = txtTenSP.getText();
        String mota = txtMoTa.getText();

        return new SanPham(ma, ten, mota);
    }

    private void showDataSP(int index) {
        SanPham sp = service.PhanTrangSP(page).get(index);
        txtMaSP.setText(sp.getMaSP());
        txtTenSP.setText(sp.getTenSP());
        txtMoTa.setText(sp.getMoTa());
    }

    private void showDataCTSP(int index) {
        String ma = txtMaSP.getText();
        List<ChiTietSanPham> listCTSP = service.getMaSP(ma);
        if (!listCTSP.isEmpty()) {
            ChiTietSanPham ctsp = service.getMaSP(ma).get(index);
            if (ctsp != null) {
                lblSTT.setText(String.valueOf(ctsp.getID()));
                cboLoaiSP.setSelectedItem(ctsp.getMaLoai());
                cboKichThuoc.setSelectedItem(ctsp.getMaKichThuoc());
                cboMau.setSelectedItem(ctsp.getMaMauSac());
                cboChatLieu.setSelectedItem(ctsp.getMaChatLieu());
                cboThuongHieu.setSelectedItem(ctsp.getMaThuongHieu());
                txtDonGia.setText(String.valueOf(ctsp.getGia()));
                txtSoLuong.setText(String.valueOf(ctsp.getSoLuong()));
                lblMATEN.setText(txtMaSP.getText() + " - " + txtTenSP.getText());
            }
        }
    }

    private void showDataTimKiemSP(int index) {
        String tim = txtTim.getText();
        SanPham sp = service.timSP(tim).get(index);
        txtMaSP.setText(String.valueOf(sp.getMaSP()));
        txtTenSP.setText(sp.getTenSP());
        txtMoTa.setText(sp.getMoTa());
    }

    public boolean checkTrongSP() {
        if (txtMaSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm trống !");
            return false;
        }
        if (txtTenSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm trống !");
            return false;
        }

        return true;
    }

    public boolean checkTrongCTSP() {
        if (txtSoLuong.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Số lượng trống !");
            return false;
        }
        if (txtDonGia.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Đơn giá trống !");
            return false;
        }
        return true;
    }

    public void setMenu() {
        switch (tenMenu) {
            case "Mau":
                JDialogMau dialog = new JDialogMau((Frame) SwingUtilities.getWindowAncestor(this), true);
                dialog.setVisible(true);
                fillCboMau();
                break;
            case "KichThuoc":
                JDialogKichThuoc dialog1 = new JDialogKichThuoc((Frame) SwingUtilities.getWindowAncestor(this), true);
                dialog1.setVisible(true);
                fillCboKichThuoc();
                break;
            case "ChatLieu":
                JDialogChatLieu dialog2 = new JDialogChatLieu((Frame) SwingUtilities.getWindowAncestor(this), true);
                dialog2.setVisible(true);
                fillCboCL();
                break;
            case "LSP":
                JDialogLSP dialog3 = new JDialogLSP((Frame) SwingUtilities.getWindowAncestor(this), true);
                dialog3.setVisible(true);
                fillCboLSP();
                break;
            case "ThuongHieu":
                JDialogThuongHieu dialog4 = new JDialogThuongHieu((Frame) SwingUtilities.getWindowAncestor(this), true);
                dialog4.setVisible(true);
                fillCboTH();
                break;
            default:
                break;
        }
    }

    public boolean checkTrungCTSanPham() {
        ChiTietSanPham ctsp = readFormAddCTSP();
        List<ChiTietSanPham> listctsp = service.getAllCTSP();
        for (ChiTietSanPham chiTietSanPham : listctsp) {
            if (chiTietSanPham.getTenSP().equals(ctsp.getTenSP())
                    && chiTietSanPham.getMaMauSac().equals(ctsp.getMaMauSac())
                    && chiTietSanPham.getMaChatLieu().equals(ctsp.getMaChatLieu())
                    && chiTietSanPham.getMaKichThuoc().equals(ctsp.getMaKichThuoc())
                    && chiTietSanPham.getMaLoai().equals(ctsp.getMaLoai())
                    && chiTietSanPham.getMaThuongHieu().equals(ctsp.getMaThuongHieu())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkTrungSanPham() {
        SanPham sp = readFormSP();
        List<SanPham> listsp = service.getAllSP();
        for (SanPham sanPham : listsp) {
            if (sanPham.getTenSP().equals(sp.getTenSP())) {
                return false;
            }
        }
        return true;
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
        jpSanPham = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        btnChiTietSP = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtMoTa = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        btnClear1 = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lblSoTrang = new javax.swing.JLabel();
        btnClear2 = new javax.swing.JButton();
        jpChiTiet = new javax.swing.JPanel();
        btnSua1 = new javax.swing.JButton();
        btnQuayVeSP = new javax.swing.JButton();
        txtDonGia = new javax.swing.JTextField();
        lblMATEN = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        cboKichThuoc = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        cboChatLieu = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        cboMau = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cboLoaiSP = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChiTietSanPham = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cboThuongHieu = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        lblSTT = new javax.swing.JLabel();
        btnThem1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(900, 650));

        jpSanPham.setBackground(new java.awt.Color(255, 255, 255));
        jpSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N

        jLabel16.setText("Mã sản phẩm");

        jLabel17.setText("Tên sản phẩm");

        btnChiTietSP.setBackground(new java.awt.Color(0, 102, 255));
        btnChiTietSP.setForeground(new java.awt.Color(255, 255, 255));
        btnChiTietSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new.png"))); // NOI18N
        btnChiTietSP.setText("Chi tiết sản phẩm");
        btnChiTietSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChiTietSPMouseClicked(evt);
            }
        });
        btnChiTietSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietSPActionPerformed(evt);
            }
        });

        jLabel1.setText("Mô tả");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(txtMoTa))
                        .addGap(157, 157, 157)
                        .addComponent(btnChiTietSP)))
                .addContainerGap(177, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtMaSP, txtMoTa, txtTenSP});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnChiTietSP)))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtMaSP, txtMoTa, txtTenSP});

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel6.setName(""); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        btnClear.setBackground(new java.awt.Color(0, 102, 255));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new.png"))); // NOI18N
        btnClear.setText("Mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnTim.setBackground(new java.awt.Color(0, 102, 255));
        btnTim.setForeground(new java.awt.Color(255, 255, 255));
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/timkiem.png"))); // NOI18N
        btnTim.setText("Tìm kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnClear1.setBackground(new java.awt.Color(0, 102, 255));
        btnClear1.setForeground(new java.awt.Color(255, 255, 255));
        btnClear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/load.png"))); // NOI18N
        btnClear1.setText("Load Table");
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        btnPre.setBackground(new java.awt.Color(0, 102, 255));
        btnPre.setForeground(new java.awt.Color(255, 255, 255));
        btnPre.setText("<");
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(0, 102, 255));
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        lblSoTrang.setText("SOTRANG");

        btnClear2.setBackground(new java.awt.Color(0, 102, 255));
        btnClear2.setForeground(new java.awt.Color(255, 255, 255));
        btnClear2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/them.png"))); // NOI18N
        btnClear2.setText("Thêm");
        btnClear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(btnClear2)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear1)
                        .addGap(59, 59, 59)
                        .addComponent(btnTim)
                        .addGap(18, 18, 18)
                        .addComponent(txtTim, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPre)
                        .addGap(18, 18, 18)
                        .addComponent(lblSoTrang)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext)
                        .addGap(14, 14, 14)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTim)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear)
                    .addComponent(btnClear1)
                    .addComponent(btnClear2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoTrang)
                    .addComponent(btnPre)
                    .addComponent(btnNext))
                .addGap(131, 131, 131))
        );

        javax.swing.GroupLayout jpSanPhamLayout = new javax.swing.GroupLayout(jpSanPham);
        jpSanPham.setLayout(jpSanPhamLayout);
        jpSanPhamLayout.setHorizontalGroup(
            jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpSanPhamLayout.setVerticalGroup(
            jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpChiTiet.setBackground(new java.awt.Color(255, 255, 255));
        jpChiTiet.setPreferredSize(new java.awt.Dimension(900, 650));

        btnSua1.setBackground(new java.awt.Color(0, 102, 255));
        btnSua1.setForeground(new java.awt.Color(255, 255, 255));
        btnSua1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sua.png"))); // NOI18N
        btnSua1.setText("Sửa");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        btnQuayVeSP.setBackground(new java.awt.Color(0, 102, 255));
        btnQuayVeSP.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayVeSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgmain/sanpham.png"))); // NOI18N
        btnQuayVeSP.setText("Sản phẩm");
        btnQuayVeSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayVeSPActionPerformed(evt);
            }
        });

        lblMATEN.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblMATEN.setText("MASP - TENSP");

        jLabel27.setText("Đơn giá");

        cboKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "L", "XL", "XXL" }));
        cboKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboKichThuocMouseClicked(evt);
            }
        });

        jLabel26.setText("Kích thước");

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cotton", "Nỉ" }));
        cboChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboChatLieuMouseClicked(evt);
            }
        });

        jLabel28.setText("Chất liệu");

        cboMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Xanh", "Do", "Tim", "Vang" }));
        cboMau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboMauMouseClicked(evt);
            }
        });

        jLabel23.setText("Màu sắc");

        jLabel21.setText("Số lượng");

        jLabel22.setText("Loại sản phẩm");

        cboLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoaiSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLoaiSPMouseClicked(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/them.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/them.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(0, 102, 255));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/them.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/them.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/them.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tblChiTietSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Kích thước", "Màu sắc", "Chất liệu", "Thương hiệu", "Đơn giá", "Số lượng", "Trạng thái"
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

        jLabel3.setText("Mã CTSP");

        jLabel29.setText("Thương hiệu");

        cboThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "L", "XL", "XXL" }));
        cboThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboThuongHieuMouseClicked(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 102, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/them.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        lblSTT.setText("...");

        btnThem1.setBackground(new java.awt.Color(0, 102, 255));
        btnThem1.setForeground(new java.awt.Color(255, 255, 255));
        btnThem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new.png"))); // NOI18N
        btnThem1.setText("Làm mới");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpChiTietLayout = new javax.swing.GroupLayout(jpChiTiet);
        jpChiTiet.setLayout(jpChiTietLayout);
        jpChiTietLayout.setHorizontalGroup(
            jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpChiTietLayout.createSequentialGroup()
                .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpChiTietLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(jpChiTietLayout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpChiTietLayout.createSequentialGroup()
                                .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpChiTietLayout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpChiTietLayout.createSequentialGroup()
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton2))
                                    .addGroup(jpChiTietLayout.createSequentialGroup()
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboMau, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1))
                                    .addGroup(jpChiTietLayout.createSequentialGroup()
                                        .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jpChiTietLayout.createSequentialGroup()
                                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jpChiTietLayout.createSequentialGroup()
                                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton3))
                                    .addGroup(jpChiTietLayout.createSequentialGroup()
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton5)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                                .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnQuayVeSP, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnThem)
                                        .addComponent(btnSua1))
                                    .addComponent(btnThem1))
                                .addGap(121, 121, 121))
                            .addGroup(jpChiTietLayout.createSequentialGroup()
                                .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpChiTietLayout.createSequentialGroup()
                                        .addComponent(lblSTT)
                                        .addGap(44, 44, 44)
                                        .addComponent(lblMATEN))
                                    .addGroup(jpChiTietLayout.createSequentialGroup()
                                        .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton4)))))))
                .addContainerGap())
        );

        jpChiTietLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboChatLieu, cboKichThuoc, cboLoaiSP, cboMau, txtDonGia, txtSoLuong});

        jpChiTietLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnQuayVeSP, btnSua1, btnThem, btnThem1});

        jpChiTietLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4});

        jpChiTietLayout.setVerticalGroup(
            jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpChiTietLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpChiTietLayout.createSequentialGroup()
                        .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpChiTietLayout.createSequentialGroup()
                                .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblMATEN)
                                    .addComponent(jLabel3)
                                    .addComponent(lblSTT))
                                .addGap(18, 18, 18)
                                .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton4))
                        .addGap(18, 18, 18)
                        .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpChiTietLayout.createSequentialGroup()
                                .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboMau, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton1))
                                .addGap(18, 18, 18)
                                .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpChiTietLayout.createSequentialGroup()
                                        .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton2)
                                            .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(16, 16, 16)
                                        .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpChiTietLayout.createSequentialGroup()
                                .addComponent(btnQuayVeSP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSua1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThem1))))
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(jpChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpChiTietLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboChatLieu, cboKichThuoc, cboLoaiSP, cboMau, txtDonGia, txtSoLuong});

        jpChiTietLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnQuayVeSP, btnSua1, btnThem, btnThem1});

        jpChiTietLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 3, Short.MAX_VALUE)
                    .addComponent(jpChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 894, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 3, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 747, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 65, Short.MAX_VALUE)
                    .addComponent(jpSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 65, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 54, Short.MAX_VALUE)
                    .addComponent(jpChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 55, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChiTietSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChiTietSPMouseClicked

    }//GEN-LAST:event_btnChiTietSPMouseClicked

    private void btnChiTietSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietSPActionPerformed
        // TODO add your handling code here:
        tblSanPham.enable();
        btnPre.setEnabled(true);
        btnNext.setEnabled(true);
        lblSoTrang.setText(String.valueOf(demsotrang + " / " + sotrang));
        String ma = txtMaSP.getText();
        jpSanPham.setVisible(false);
        jpChiTiet.setVisible(true);
        fillToTableChiTietSanPham(service.getMaSP(ma));
        lblMATEN.setText(txtMaSP.getText() + " - " + txtTenSP.getText());
        if (service.getMaSP(ma) != null) {
            showDataCTSP(0);
//            tblChiTietSanPham.setRowSelectionInterval(0, 0);
        }
    }//GEN-LAST:event_btnChiTietSPActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        index = tblSanPham.getSelectedRow();
        String tim = txtTim.getText().trim();
        if (tim.equalsIgnoreCase("")) {
            showDataSP(index);
        } else {
            showDataTimKiemSP(index);
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.clearSanPham();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        String timKiem = txtTim.getText();
        if (timKiem.equalsIgnoreCase("")) {
            fillToTableSanPham(service.PhanTrangSP(page));
            tblSanPham.setRowSelectionInterval(index, index);
            lblSoTrang.setText(String.valueOf(demsotrang + " / " + sotrang));
            btnPre.setEnabled(true);
            btnNext.setEnabled(true);
        } else if (service.timSP(timKiem) != null) {
            fillToTableSanPham(service.timSP(timKiem));
            index = 0;
            tblSanPham.setRowSelectionInterval(index, index);
            showDataTimKiemSP(index);
            lblSoTrang.setText("1 / 1");
            btnPre.setEnabled(false);
            btnNext.setEnabled(false);
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        fillToTableSanPham(service.PhanTrangSP(page));
        btnPre.setEnabled(true);
        btnNext.setEnabled(true);
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        if (demsotrang > 1) {
            page -= 5;
            demsotrang -= 1;
            tongtrang = Math.ceil((double) service.getAllSP().size() / 5);
            sotrang = (int) tongtrang;
            lblSoTrang.setText(String.valueOf(demsotrang + " / " + sotrang));
            fillToTableSanPham(service.PhanTrangSP(page));
        }
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (demsotrang < sotrang) {
            page += 5;
            demsotrang += 1;
            tongtrang = Math.ceil((double) service.getAllSP().size() / 5);
            sotrang = (int) tongtrang;
            lblSoTrang.setText(String.valueOf(demsotrang + " / " + sotrang));
            fillToTableSanPham(service.PhanTrangSP(page));
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnClear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear2ActionPerformed
        SanPham sp = readFormSP();
        if (service.getSP(sp.getMaSP()) == null) {
            if (checkTrungSanPham()) {
                if (service.addSP(sp) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công !");
                    fillToTableSanPham(service.PhanTrangSP(page));

                    //service.addSPCTSP(sp.getMaSP(), sp.getTenSP());
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại !");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Đã có tên sản phẩm !");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã có mã sản phẩm !");
        }
    }//GEN-LAST:event_btnClear2ActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        if (checkTrongCTSP()) {
            ChiTietSanPham ctsp = readFormCTSP();
            int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa sản phẩm này ?");
            if (chon == JOptionPane.YES_OPTION) {
                if (checkTrungCTSanPham()) {
                    if (service.updateSP(ctsp, ctsp.getID()) > 0) {
                        JOptionPane.showMessageDialog(this, "Sửa thành công !");
                        fillToTableChiTietSanPham(service.getMaSP(ctsp.getMaSP()));
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa thất bại !");
                    }
                } else if (checkTrungCTSanPham() == false) {
                    if (service.updateSLGIASP(ctsp, ctsp.getID()) > 0) {
                        JOptionPane.showMessageDialog(this, "Sửa thành công !");
                        fillToTableChiTietSanPham(service.getMaSP(ctsp.getMaSP()));
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa thất bại !");
                    }
                }
            } else {
                return;
            }           
        }
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void btnQuayVeSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayVeSPActionPerformed
        String timKiem = txtTim.getText().trim();
        index = tblSanPham.getSelectedRow();
        jpSanPham.setVisible(true);
        jpChiTiet.setVisible(false);
        fillToTableSanPham(service.PhanTrangSP(page));
    }//GEN-LAST:event_btnQuayVeSPActionPerformed

    private void cboKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboKichThuocMouseClicked

    }//GEN-LAST:event_cboKichThuocMouseClicked

    private void cboChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboChatLieuMouseClicked

    }//GEN-LAST:event_cboChatLieuMouseClicked

    private void cboMauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboMauMouseClicked

    }//GEN-LAST:event_cboMauMouseClicked

    private void cboLoaiSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLoaiSPMouseClicked

    }//GEN-LAST:event_cboLoaiSPMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tenMenu = "Mau";
        this.setMenu();
        fillCboMau();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        tenMenu = "ChatLieu";
        this.setMenu();
        fillCboCL();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkTrongCTSP()) {
            ChiTietSanPham ctsp = readFormAddCTSP();
            if (checkTrungCTSanPham()) {
                if (service.addCTSP(ctsp) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công !");
                    fillToTableChiTietSanPham(service.getMaSP(ctsp.getMaSP()));
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại !");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Đã có sản phẩm !");
                return;
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        tenMenu = "KichThuoc";
        this.setMenu();
        fillCboKichThuoc();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        tenMenu = "LSP";
        this.setMenu();
        fillCboLSP();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSanPhamMouseClicked
        index = tblChiTietSanPham.getSelectedRow();
        showDataCTSP(index);
    }//GEN-LAST:event_tblChiTietSanPhamMouseClicked

    private void cboThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboThuongHieuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboThuongHieuMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        tenMenu = "ThuongHieu";
        this.setMenu();
        fillCboTH();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        cboLoaiSP.setSelectedIndex(1);
        cboMau.setSelectedIndex(1);
        cboChatLieu.setSelectedIndex(1);
        cboKichThuoc.setSelectedIndex(1);
        cboThuongHieu.setSelectedIndex(1);
        txtSoLuong.setText("");
        txtDonGia.setText("");
    }//GEN-LAST:event_btnThem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChiTietSP;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnClear2;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnQuayVeSP;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnTim;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JComboBox<String> cboMau;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpChiTiet;
    private javax.swing.JPanel jpSanPham;
    private javax.swing.JLabel lblMATEN;
    private javax.swing.JLabel lblSTT;
    private javax.swing.JLabel lblSoTrang;
    private javax.swing.JTable tblChiTietSanPham;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
