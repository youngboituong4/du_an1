/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

/**
 *
 * @author thinh
 */
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.NhanVienService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import model.NhanVien;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class NhanVienForm extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienForm
     */
    ArrayList<NhanVien> list = new ArrayList<>();
    NhanVienService service = new NhanVienService();
    DefaultTableModel model = new DefaultTableModel();
    int index = -1;

    /**
     * Creates new form View
     */
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
        ButtonGroup group = new ButtonGroup();
        group.add(rdoNam);
        group.add(rdoNu);
        group.clearSelection();
        ButtonGroup groupNVQL = new ButtonGroup();
        groupNVQL.add(rdoQL);
        groupNVQL.add(rdoNV);
        groupNVQL.clearSelection();
        ButtonGroup groupDlNl = new ButtonGroup();
        groupDlNl.add(rdoDl);
        groupDlNl.add(rdoNl);
        groupDlNl.clearSelection();
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
        rdoDl.setSelected(nv.isTrangThai());
        rdoNl.setSelected(!nv.isTrangThai());
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
        if (txtSDT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời nhập số điện thoại");
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
        if (txtDiaChi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời nhập địa chỉ của bạn");
            return false;
        }
        if (!rdoNam.isSelected() && !rdoNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính");
            return false;
        }
        if (!rdoQL.isSelected() && !rdoNV.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vai trò");
            return false;
        }
        if (!rdoDl.isSelected() && !rdoNl.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái");
            return false;
        }
        return true;
    }

    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    private void exportTableToExcel(JTable table, File file) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Table Data");
        Row headerRow = sheet.createRow(0);
        for (int col = 0; col < table.getColumnCount(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(table.getColumnName(col));
        }
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
            JOptionPane.showMessageDialog(null, "Tải mẫu thành công !");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Tải mẫu thất bại.");
        }
    }

    private boolean isNullOrBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean isDataValid(XSSFSheet sheet) {
        // Lặp qua từng dòng của sheet
        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            XSSFRow row = sheet.getRow(rowIndex);
            if (row != null) {
                // Lấy giá trị từ từng ô của dòng
                for (int columnIndex = 1; columnIndex < row.getLastCellNum(); columnIndex++) {
                    XSSFCell cell = row.getCell(columnIndex);
                    if (cell != null) {
                        String cellValue = cell.getStringCellValue();
                        // Kiểm tra xem giá trị có giá trị null hoặc trống không
                        if (isNullOrBlank(cellValue)) {
                            return false; // Nếu có giá trị null hoặc trống, trả về false
                        }
                    }
                }
            }
        }
        return true; // Dữ liệu hợp lệ, không có giá trị null hoặc trống
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
        jPanel1 = new javax.swing.JPanel();
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
        jPanel2 = new javax.swing.JPanel();
        cboVaiTro = new javax.swing.JComboBox<>();
        cboTrangThai = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnExport = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        btnTaiMau = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        btnExport.setText("Export file Excel");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        btnImport.setText("Import file Excel");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        btnTaiMau.setText("Tải mẫu Excel");
        btnTaiMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiMauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExport)
                        .addGap(18, 18, 18)
                        .addComponent(btnImport)
                        .addGap(18, 18, 18)
                        .addComponent(btnTaiMau))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(804, 804, 804)
                            .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1099, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(319, 319, 319)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(46, 46, 46)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtName)
                                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(69, 69, 69)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(135, 135, 135)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(80, 80, 80)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(rdoNV)
                                                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(18, 18, 18)
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(rdoNu)
                                                                .addComponent(rdoQL, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                            .addComponent(rdoDl)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(rdoNl)))))
                                            .addGap(37, 37, 37)))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(rdoNam)
                                            .addComponent(rdoNu))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(rdoNV)
                                            .addComponent(rdoQL))))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoDl)
                                .addComponent(rdoNl)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua)
                    .addComponent(btnAdd)
                    .addComponent(btnClear)
                    .addComponent(btnExport)
                    .addComponent(btnImport)
                    .addComponent(btnTaiMau))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

        boolean gioiTinh = selectedData6.equals("Nam");
        rdoNam.setSelected(gioiTinh);
        rdoNu.setSelected(!gioiTinh);
        boolean vaiTro = selectedData7.equals("Quản lý");
        rdoNV.setSelected(!vaiTro);
        rdoQL.setSelected(vaiTro);
        boolean trangThai = selectedData8.equals("Đang làm");
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
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void cboVaiTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboVaiTroActionPerformed
        // TODO add your handling code here:
        boolean vaiTro = true;
        boolean trangThai = true;
        String loctt = (String) cboVaiTro.getSelectedItem();
        if ("Quản lý".equals(loctt)) {
            vaiTro = Boolean.TRUE;
        } else if ("Nhân viên".equals(loctt)) {
            vaiTro = Boolean.FALSE;
        }
        list = service.timTheoDieuKien(txtTim.getText(), vaiTro, trangThai);
        findTable(list);
    }//GEN-LAST:event_cboVaiTroActionPerformed

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
        boolean trangThai = true;
        boolean vaiTro = true;
        String loctt = (String) cboTrangThai.getSelectedItem();
        if ("Đang làm".equals(loctt)) {
            trangThai = Boolean.TRUE;
        } else if ("Nghỉ làm".equals(loctt)) {
            trangThai = Boolean.FALSE;
        }
        list = service.timTheoDieuKien(txtTim.getText(), vaiTro, trangThai);
        findTable(list);
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString());
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("customer");
                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tblBang.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblBang.getColumnName(i));
                }
                for (int j = 0; j < tblBang.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tblBang.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (tblBang.getValueAt(j, k) != null) {
                            cell.setCellValue(tblBang.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Đã hủy xuất file");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        // TODO add your handling code here:
        File excelFile;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        XSSFWorkbook excelImport = null;
        String Path = "C:/Users/Admin/Documents/Test.xlsx";
        JFileChooser excel = new JFileChooser(Path);
        int excelChooser = excel.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excel.getSelectedFile();
                if (!excelFile.exists()) {
                    JOptionPane.showMessageDialog(null, "Tệp không tồn tại");
                    return;
                }

                fis = new FileInputStream(excelFile);
                bis = new BufferedInputStream(fis);
                excelImport = new XSSFWorkbook(bis);
                XSSFSheet sheet = excelImport.getSheetAt(0);
                if (!isDataValid(sheet)) {
                    JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ. Vui lòng kiểm tra lại.");
                } else {
                    service.importDataFromExcelToDatabase(sheet);
                    JOptionPane.showMessageDialog(null, "Import dữ liệu thành công.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } finally {
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnImportActionPerformed

    private void btnTaiMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiMauActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();

        // Thiết lập bộ lọc file để chỉ hiển thị file Excel
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);

        // Hiển thị hộp thoại và lấy sự lựa chọn của người dùng
        int userChoice = fileChooser.showSaveDialog(this);

        // Nếu người dùng chọn một file, xuất bảng dữ liệu vào file đó
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // Đảm bảo file có phần mở rộng ".xlsx"
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                fileToSave = new File(filePath + ".xlsx");
            }

            // Gọi phương thức exportTableToExcel với file đã chọn
            exportTableToExcel(tblBang, fileToSave);
        }
    }//GEN-LAST:event_btnTaiMauActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaiMau;
    private javax.swing.JButton btnTim;
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
