/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NhanVien;
import service.NhanVienService;
/**
 *
 * @author thinh
 */
public class NhanVienForm extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienForm
     */
    NhanVienService service = new NhanVienService();
    DefaultTableModel model = new DefaultTableModel();
    ArrayList<NhanVien> list = new ArrayList<>();
    int index = -1;
    private Boolean vaiTro = Boolean.TRUE;
    private Boolean trangThai = Boolean.TRUE;
    
    public NhanVienForm() {
        initComponents();
        this.findTable(service.getAll());
    }

    void findTable(List<NhanVien> list) {
        model = (DefaultTableModel) tblBang.getModel();
        model.setRowCount(0);
        for (NhanVien nv : list) {
            model.addRow(nv.toDataRow());
        }
    }

    public void clearNV() {
        txtMaNV.setText("");
        txtName.setText("");
        txtPass.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        txtTim.setText("");
        ButtonGroup groupGT = new ButtonGroup();
        groupGT.add(rdoNam);
        groupGT.add(rdoNu);
        groupGT.clearSelection();
        ButtonGroup groupNVQL = new ButtonGroup();
        groupNVQL.add(rdoNV);
        groupNVQL.add(rdoQL);
        groupNVQL.clearSelection();
        ButtonGroup groupTT = new ButtonGroup();
        groupTT.add(rdoDl);
        groupTT.add(rdoNl);
        groupTT.clearSelection();

    }

    void ShowData(int index) {
        NhanVien nv = service.getAll().get(index);
        txtMaNV.setText(String.valueOf(nv.getMaNV()));
        txtName.setText(nv.getHoVaTen());
        txtPass.setText(nv.getMatKhau());
        txtSDT.setText(nv.getSdt());
        txtDiaChi.setText(nv.getDiaChi());
        txtEmail.setText(nv.getEmail());
        rdoNam.setSelected(nv.isGioiTinh());
        rdoNu.setSelected(!nv.isGioiTinh());
        rdoQL.setSelected(nv.isVaiTro());
        rdoNV.setSelected(!nv.isVaiTro());
        rdoDl.setSelected(nv.isTrangThai());
        rdoNl.setSelected(!nv.isTrangThai());
    }

    private void showDataTimKiemNV(int index) {
        String tim = txtTim.getText();
        NhanVien nv = service.timNV(tim).get(index);
        txtMaNV.setText(String.valueOf(nv.getMaNV()));
        txtName.setText(nv.getHoVaTen());
        txtPass.setText(nv.getMatKhau());
        txtSDT.setText(nv.getSdt());
        txtDiaChi.setText(nv.getDiaChi());
        txtEmail.setText(nv.getEmail());
        rdoNam.setSelected(nv.isGioiTinh());
        rdoNu.setSelected(!nv.isGioiTinh());
        rdoQL.setSelected(nv.isVaiTro());
        rdoNV.setSelected(!nv.isVaiTro());
    }

    NhanVien readForm() {
        String maNV = txtMaNV.getText().trim();
        String name = txtName.getText().trim();
        String pass = txtPass.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String email = txtEmail.getText().trim();
        String sdt = txtSDT.getText().trim();
        boolean gioiTinh;
        if (rdoNam.isSelected()) {
            gioiTinh = true;
        } else {
            gioiTinh = false;
        }
        boolean vaiTro;
        if (rdoQL.isSelected()) {
            vaiTro = true;
        } else {
            vaiTro = false;
        }
        boolean trangThai;
        if (rdoDl.isSelected()) {
            trangThai = true;
        } else {
            trangThai = false;
        }
        return new NhanVien(maNV, name, pass, diaChi, email, sdt, gioiTinh, vaiTro, trangThai);
    }

    boolean testData() {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,12}";
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        if (txtMaNV.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời nhập mã nhân viên mới");
            return false;
        }
        if (txtName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống");
            return false;
        }
        if (txtPass.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời Viết Mật Khẩu");
            return false;
        }
        if (!txtPass.getText().matches(pattern)) {
            JOptionPane.showMessageDialog(this, "Mật Khẩu Phải Nhập Chữ Hoa,Thường,Kí Tự Đặt Biệt,Số Và Từ 8 - 12 kí tự");
            return false;
        }
        if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời nhập Email của bạn");
            return false;
        }
        if (!txtEmail.getText().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng");
            return false;
        }
        if (txtSDT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời nhập số điện thoại");
            return false;
        }
        if (txtDiaChi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời nhập địa chỉ của bạn");
            return false;
        }
        if (!rdoNam.isSelected() && !rdoNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính");
            return false;
        }
        if (!rdoNV.isSelected() && !rdoQL.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vai trò");
            return false;
        }
        if (!rdoDl.isSelected() && !rdoNl.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái");
            return false;
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        rdoNV = new javax.swing.JRadioButton();
        rdoQL = new javax.swing.JRadioButton();
        txtDiaChi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        rdoDl = new javax.swing.JRadioButton();
        rdoNl = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        cboVaiTro = new javax.swing.JComboBox<>();
        cboTrangThai = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        jLabel2.setText("Mã nhân viên");

        jLabel3.setText("Họ và tên");

        jLabel4.setText("Mật khẩu");

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel5.setText("Giới tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Họ và tên", "Mật khẩu", "Địa chỉ", "Email", "SĐT", "Giới tính", "Vai trò", "Trạng thái"
            }
        ));
        tblBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBang);

        jLabel6.setText("Số điện thoại");

        jLabel7.setText("Email");

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel9.setText("Vai trò");

        buttonGroup2.add(rdoNV);
        rdoNV.setSelected(true);
        rdoNV.setText("Nhân viên");

        buttonGroup2.add(rdoQL);
        rdoQL.setText("Quản lý");

        jLabel10.setText("Địa chỉ");

        btnClear.setText("Làm mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnTim.setText("Tìm kiếm nhân viên");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        jLabel11.setText("Trạng thái");

        buttonGroup3.add(rdoDl);
        rdoDl.setSelected(true);
        rdoDl.setText("Đang làm");

        buttonGroup3.add(rdoNl);
        rdoNl.setText("Nghỉ làm");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cboVaiTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân viên" }));
        cboVaiTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboVaiTroActionPerformed(evt);
            }
        });

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang làm", "Nghỉ làm" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Bộ lọc");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel13.setText("Vai trò :");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel14.setText("Trạng thái :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTim)
                        .addGap(25, 25, 25)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1044, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(46, 46, 46)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                    .addComponent(txtMaNV))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(135, 135, 135)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(80, 80, 80)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(rdoNV)
                                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(rdoNu)
                                                .addComponent(rdoQL, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(rdoDl)
                                            .addGap(18, 18, 18)
                                            .addComponent(rdoNl)))))
                            .addGap(37, 37, 37)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(rdoNam)
                                            .addComponent(rdoNu))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(rdoNV)
                                            .addComponent(rdoQL))))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoDl)
                                .addComponent(rdoNl))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSua)
                        .addComponent(btnAdd)
                        .addComponent(btnXoa)
                        .addComponent(btnClear)
                        .addComponent(btnTim))
                    .addComponent(txtTim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        NhanVien nv = readForm();
        if (testData()) {
            if (service.getNV(nv.getMaNV()) != null) {
                JOptionPane.showMessageDialog(this, "Mã bị trùng");
            } else {
                if (service.AddNV(nv) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
                    this.findTable(service.getAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked
        // TODO add your handling code here:
        index = tblBang.getSelectedRow();
        this.ShowData(index);
        showDataTimKiemNV(index);

        Object selectedData = tblBang.getValueAt(index, 0);
        Object selectedData1 = tblBang.getValueAt(index, 1);
        Object selectedData2 = tblBang.getValueAt(index, 2);
        Object selectedData3 = tblBang.getValueAt(index, 3);
        Object selectedData4 = tblBang.getValueAt(index, 4);
        Object selectedData5 = tblBang.getValueAt(index, 5);
        Object selectedData6 = tblBang.getValueAt(index, 6);
        Object selectedData7 = tblBang.getValueAt(index, 7);
        Object selectedData8 = tblBang.getValueAt(index, 8);

        txtMaNV.setText(selectedData.toString());
        txtName.setText(selectedData1.toString());
        txtPass.setText(selectedData2.toString());
        txtDiaChi.setText(selectedData3.toString());
        txtEmail.setText(selectedData4.toString());
        txtSDT.setText(selectedData5.toString());

        boolean gioiTinh = Boolean.parseBoolean(selectedData6.toString());
        rdoNam.setSelected(gioiTinh);
        rdoNu.setSelected(!gioiTinh);
        boolean vaiTro = Boolean.parseBoolean(selectedData7.toString());
        rdoQL.setSelected(vaiTro);
        rdoNV.setSelected(!vaiTro);
        boolean trangThai = Boolean.parseBoolean(selectedData8.toString());
        rdoDl.setSelected(trangThai);
        rdoNl.setSelected(!trangThai);
    }//GEN-LAST:event_tblBangMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (testData()) {
            NhanVien nv = readForm();
            int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa thông tin nhân viên này ?");
            if (chon == JOptionPane.YES_OPTION) {
                if (service.updateNV(nv, String.valueOf(nv.getMaNV())) > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công !");
                    this.findTable(service.getAll());
                    tblBang.setRowSelectionInterval(index, index);
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại !");
                }
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        String MaNV = (String) tblBang.getValueAt(index, 0);
        int result = service.deleteNV(MaNV);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công");
            this.findTable(service.getAll());
            txtMaNV.setText(null);
            txtName.setText(null);
            txtPass.setText(null);
            txtEmail.setText(null);
            txtSDT.setText(null);
            txtDiaChi.setText(null);
            rdoNV.setSelectedIcon(null);
            rdoNam.setSelectedIcon(null);
            rdoQL.setSelectedIcon(null);
            rdoNu.setSelectedIcon(null);
            rdoDl.setSelectedIcon(null);
            rdoNl.setSelectedIcon(null);
        } else {
            JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        this.clearNV();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        String timKiem = txtTim.getText().trim();
        if (timKiem.equalsIgnoreCase("")) {
            findTable(service.getAll());
        } else if (service.timNV(timKiem) != null) {
            findTable(service.timNV(timKiem));
            index = 0;
            tblBang.setRowSelectionInterval(index, index);
            showDataTimKiemNV(index);
            index = 0;
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void cboVaiTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboVaiTroActionPerformed
        // TODO add your handling code here:
        String loctt = (String) cboVaiTro.getSelectedItem();
        if (loctt.equals("Quản lý")) {
            vaiTro = Boolean.TRUE; // Chọn quản lý
        } else if (loctt.equals("Nhân viên")) {
            vaiTro = Boolean.FALSE; // Chọn nhân viên
        }
        list = service.timTheoDieuKien(txtTim.getText(), vaiTro, trangThai);
        findTable(list);
    }//GEN-LAST:event_cboVaiTroActionPerformed

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
        String loctt = (String) cboTrangThai.getSelectedItem();
        if (loctt.equals("Đang làm")) {
            trangThai = Boolean.TRUE; // Chọn đang làm
        } else if (loctt.equals("Nghỉ làm")) {
            trangThai = Boolean.FALSE; // Chọn nghỉ làm
        }
        list = service.timTheoDieuKien(txtTim.getText(), vaiTro, trangThai);
        findTable(list);
    }//GEN-LAST:event_cboTrangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JComboBox<String> cboVaiTro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoDl;
    private javax.swing.JRadioButton rdoNV;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNl;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoQL;
    private javax.swing.JTable tblBang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
