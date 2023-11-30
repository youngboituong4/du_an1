/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dailycheck.DailyCheckingKhuyenMai;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.KhuyenMai;
import service.KhuyenMaiservice;

public class KhuyenMaiFrom extends javax.swing.JFrame {

    private KhuyenMaiservice service = new KhuyenMaiservice();
    ArrayList<KhuyenMai> lsst = new ArrayList<>();
    private DefaultTableModel model = new DefaultTableModel();
    private int index = -1;
    private JComboBox<String> comboBox;
    private DailyCheckingKhuyenMai daily = new DailyCheckingKhuyenMai();
    int page = 0;
    int demsotrang = 1;
    double tongtrang  = Math.ceil((double) service.getAllKM().size() / 5);
    int sotrang  = (int) tongtrang;

    /**
     * Creates new form KhuyenMai
     */
    public KhuyenMaiFrom() {
        initComponents();
        setLocationRelativeTo(null);
        cbHT.removeAllItems();
        cbTT.removeAllItems();
        cbFindTT.removeAllItems();
        cbHT.addItem("Giảm Bằng %");
        cbHT.addItem("Giảm Bằng Tiền");
        cbTT.addItem("Áp Dụng");
        cbTT.addItem("Không Áp Dụng");
        cbFindTT.addItem("Tất Cả");
        cbFindTT.addItem("Áp Dụng");
        cbFindTT.addItem("Không Áp Dụng");
        fillTable(service.getAllKM());
        // daily.run();
        new Thread(daily::run).start();
        JBtext.setText(String.valueOf(demsotrang + " / " + sotrang));
    }

    void fillTable(List<KhuyenMai> lst) {
        model = (DefaultTableModel) tbKM.getModel();
        model.setRowCount(0);
        for (KhuyenMai km : lst) {
            model.addRow(km.toDataRow());
        }
    }

    void ShowData(int index) {
        KhuyenMai km = service.getAllKM().get(index);
        txtMa.setText(km.getMa());
        txtTen.setText(km.getTenKhuyenMai());
        txtGia.setText(Double.toString(km.getGiaTri()));
        txtDK.setText(Double.toString(km.getDonGiamToiThieu()));
        Date.setDate(km.getNgayBatDau());
        Date2.setDate(km.getNgayKetThuc());
        cbHT.setSelectedIndex(km.getLoaiKhuyenMai(0));
        if (km.getTrangThai().equals("Áp Dụng")) {
            cbTT.setSelectedIndex(0);
        } else {
            cbTT.setSelectedIndex(1);
        }
        // cbTT.setSelectedItem(km.getTrangThai());
        tbKM.setRowSelectionInterval(index, index);
    }

    //KhuyenMai readFrom() {
//        String ma = txtMa.getText();
//        String ten = txtTen.getText();
//        int HTG = Integer.parseInt((String) cbHT.getSelectedItem());
//        Double Gia = Double.parseDouble(txtGia.getText());
//        Date NBD = Date.getDate();
//        Date NKT = Date2.getDate();
//        String TT = (String) cbTT.getSelectedItem();
//        Double DK = Double.parseDouble(txtDK.getText());
    KhuyenMai readFrom() {
        String ma = txtMa.getText();
        String ten = txtTen.getText();
        String HTGStr = (String) cbHT.getSelectedItem();
        Integer HTG = null;
        try {
            if (HTGStr.equals("Giảm Bằng %")) {
                HTG = 0;
            } else {
                HTG = 1;
            }
        } catch (NumberFormatException e) {
            // Xử lý lỗi ở đây, ví dụ:
            System.out.println("Không thể chuyển đổi " + HTGStr + " thành số nguyên.");
        }
        Double Gia = Double.parseDouble(txtGia.getText());
        Date NBD = Date.getDate();
        Date NKT = Date2.getDate();
        String TT = (String) cbTT.getSelectedItem();
        Double DK = Double.parseDouble(txtDK.getText());

        if (HTG != null) {
            return new KhuyenMai(ma, ten, HTG, DK, NBD, NKT, Gia, TT);
        } else {

            return null;
        }
    }

    //  return new KhuyenMai(ma, ten, HTG, Gia, NBD, NKT, Gia, TT);
    // }
    boolean checkTrong() {
        if (txtMa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời Nhập Mã");
            return false;
        }
        if (txtTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời Nhập Tên");
            return false;
        }
        if (txtDK.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời Nhập Điều Kiệm Giảm");
            return false;
        }
        if (txtGia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời Nhập Giá Giảm");
            return false;
        }
        if (Date.getDate().equals("")) {
            JOptionPane.showMessageDialog(this, "Mời Nhập Hoặc Chọn Ngày Bắt Đầu");
            return false;
        }
        if (Date2.getDate().equals("")) {
            JOptionPane.showMessageDialog(this, "Mời Nhập Hoặc Chọn Ngày Kết Thúc");
            return false;
        }
        return true;
    }

    void clear() {
        txtTen.setText(null);
        txtMa.setText(null);
        txtGia.setText(null);
        cbHT.setSelectedIndex(0);
        Date.setDate(new Date());
        Date2.setDate(new Date());
        txtDK.setText(null);
        cbTT.setSelectedIndex(0);
    }

    public String LayThongTin() {
        return "RECORD: " + (index + 1) + " of " + lsst.size();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDK = new javax.swing.JTextField();
        cbHT = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbTT = new javax.swing.JComboBox<>();
        Date = new com.toedter.calendar.JDateChooser();
        Date2 = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        btnTK = new javax.swing.JButton();
        cbFindTT = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        txtFind2 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbKM = new javax.swing.JTable();
        btnPRE = new javax.swing.JButton();
        JBtext = new javax.swing.JLabel();
        btnNEXT = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnHuy1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Chương Trình Khuyến Mãi", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết Lập Khuyến Mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel1.setText("Mã Khuyết Mãi:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel2.setText("Tên Khuyến Mãi:");

        txtMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaActionPerformed(evt);
            }
        });

        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel3.setText("Điều Kiện Giảm:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel4.setText("Hình Thức Giảm Giá:");

        txtDK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDKActionPerformed(evt);
            }
        });

        cbHT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel8.setText("Giá Giảm:");

        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addGap(46, 46, 46)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                .addComponent(txtTen)
                                .addComponent(txtDK)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel8))
                            .addGap(28, 28, 28)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbHT, 0, 165, Short.MAX_VALUE)
                                .addComponent(txtGia))))
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbHT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thời Gian Sử Dụng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jLabel5.setText("Thời Gian Bắt Đầu Giảm Giá:");

        jLabel6.setText("Thời GIan Kết Thúc Giảm Giá:");

        jLabel7.setText("Trạng Thái:");

        cbTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Date.setDateFormatString("yyyy-MM-dd");

        Date2.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 50, Short.MAX_VALUE))
                    .addComponent(Date2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(4, 4, 4)
                .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(8, 8, 8)
                .addComponent(Date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(cbTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng Khuyến Mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        btnTK.setText("Tìm Kiếm");
        btnTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKActionPerformed(evt);
            }
        });

        cbFindTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbFindTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFindTTItemStateChanged(evt);
            }
        });
        cbFindTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFindTTActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setText("Tìm kiếm");

        txtFind2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFind2ActionPerformed(evt);
            }
        });

        tbKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khuyễn Mãi", "Tên KM", "HT Giảm", "Điều Kiện Giảm", "Ngày Bắt Đầu ", "Ngày Kết Thúc ", "Giá Trị", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKMMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbKM);

        btnPRE.setText("PRE");
        btnPRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPREActionPerformed(evt);
            }
        });

        JBtext.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        JBtext.setText("1");

        btnNEXT.setText("NEX");
        btnNEXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNEXTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFind2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTK, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPRE, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBtext, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNEXT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbFindTT, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTK, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbFindTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFind2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34)
                        .addComponent(btnPRE)
                        .addComponent(JBtext)
                        .addComponent(btnNEXT)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNew.setText("MỚI");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnAdd.setText("THÊM");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnHuy1.setText("HỦY");
        btnHuy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuy1ActionPerformed(evt);
            }
        });

        jButton1.setText("KÍCH HOẠT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSua)
                                .addGap(18, 18, 18)
                                .addComponent(btnNew))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addGap(18, 18, 18)
                                .addComponent(btnHuy1))
                            .addComponent(jButton1))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnHuy1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNew)
                            .addComponent(btnSua))
                        .addGap(5, 5, 5))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void txtDKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDKActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:

//        if (checkTrong()) {
//            if (service.FindKM(km.getMa()) != null) {
//                JOptionPane.showMessageDialog(this, "Mã Trùng Không Thêm Được");
//            } else {
//                if (service.addKM(km) > 0) {
//                    JOptionPane.showMessageDialog(this, "Thêm Thành Công");
//                    fillTable(service.getAllKM());
//
//                } else {
//                    JOptionPane.showMessageDialog(this, "Thêm Thất Bại");
//                }
//            }
//        }
        if (checkTrong()) {
            KhuyenMai km = readFrom();
            if (service.FindKM(km.getMa()) != null) {
                JOptionPane.showMessageDialog(this, "Mã Trùng Không Thêm Được");
            } else {
                if (service.addKM(km) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm Thành Công");
                    fillTable(service.getAllKM());
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm Thất Bại");
                }
            }

        } else {
            // Xử lý trường hợp 'km' là null
        }


    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:

        if (checkTrong()) {
            String MaKM = tbKM.getValueAt(index, 0).toString();
            KhuyenMai km = readFrom();
            if (service.updateKM(MaKM, km) > 0) {
                JOptionPane.showMessageDialog(this, "UpDate TC");
                fillTable(service.getAllKM());

            } else {
                JOptionPane.showMessageDialog(this, "UpDate TB");

            }
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void cbFindTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFindTTActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbFindTTActionPerformed

    private void cbFindTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbFindTTItemStateChanged
        // TODO add your handling code here:
        String ML = String.valueOf(cbFindTT.getSelectedItem());

        fillTable(service.getKM(ML));
    }//GEN-LAST:event_cbFindTTItemStateChanged

    private void btnTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKActionPerformed
        // TODO add your handling code here:
        String MaKM = txtFind2.getText().trim();
        if (MaKM.equalsIgnoreCase("")) {
            fillTable((service.getAllKM()));
        } else if (service.timKM(MaKM) != null) {
            fillTable(service.timKM(MaKM));
            index = 0;
            tbKM.setRowSelectionInterval(index, index);
            ShowData(index);
        }

    }//GEN-LAST:event_btnTKActionPerformed

    private void tbKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKMMouseClicked
        // TODO add your handling code here:
        index = tbKM.getSelectedRow();
        ShowData(index);
        Object selectedData = tbKM.getValueAt(index, 0); // Thay đổi 0 thành chỉ số cột tương ứng
        Object selectedData1 = tbKM.getValueAt(index, 1);
        Object selectedData2 = tbKM.getValueAt(index, 2);
        Object selectedData3 = tbKM.getValueAt(index, 3);
        Object selectedData4 = tbKM.getValueAt(index, 4);
        Object selectedData5 = tbKM.getValueAt(index, 5);
        Object selectedData6 = tbKM.getValueAt(index, 6);
        Object selectedData7 = tbKM.getValueAt(index, 7);
        txtMa.setText(selectedData.toString());
        txtTen.setText(selectedData1.toString());
        cbHT.setSelectedItem(selectedData2.toString());
        txtDK.setText(String.valueOf(selectedData3.toString()));
        txtGia.setText(String.valueOf(selectedData6));
        Date.setDate((Date) selectedData4);
        Date2.setDate((Date) selectedData5);
        cbTT.setSelectedItem(String.valueOf(selectedData7));

    }//GEN-LAST:event_tbKMMouseClicked

    private void txtFind2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFind2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFind2ActionPerformed

    private void btnHuy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuy1ActionPerformed
        // TODO add your handling code here:
        String MaKM = tbKM.getValueAt(index, 0).toString();
        String trangThai = tbKM.getValueAt(index, 7).toString();
        String newTrangThai = "Không Áp Dụng";
        if (service.HuyKM(MaKM, newTrangThai) > 0) {
            JOptionPane.showMessageDialog(this, "Hủy Thành Công");
            fillTable(service.getAllKM());
        } else {
            JOptionPane.showMessageDialog(this, "Hủy Thất Bại");
        }
    }//GEN-LAST:event_btnHuy1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String MaKM = tbKM.getValueAt(index, 0).toString();
        String trangThai = tbKM.getValueAt(index, 7).toString();
        String newTrangThai = "Áp Dụng";
        if (service.HuyKM(MaKM, newTrangThai) > 0) {
            JOptionPane.showMessageDialog(this, "Kích Hoạt Thành Công");
            fillTable(service.getAllKM());
        } else {
            JOptionPane.showMessageDialog(this, "Kích Hoạt Thất Bại");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnPREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPREActionPerformed
        // TODO add your handling code here:
        if (demsotrang > 1) {
            page -= 5;
            demsotrang -= 1;
            tongtrang = Math.ceil((double) service.getAllKM().size() / 5);
            sotrang = (int) tongtrang;
            JBtext.setText(String.valueOf(demsotrang + " / " + sotrang));
            fillTable(service.PhanTrang(page));
        }
    }//GEN-LAST:event_btnPREActionPerformed

    private void btnNEXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNEXTActionPerformed
        // TODO add your handling code here:
        if (demsotrang < sotrang) {
            page += 5;
            demsotrang += 1;
            tongtrang = Math.ceil((double) service.getAllKM().size() / 5);
            sotrang = (int) tongtrang;
            JBtext.setText(String.valueOf(demsotrang + " / " + sotrang));
            fillTable(service.PhanTrang(page));
        }

    }//GEN-LAST:event_btnNEXTActionPerformed

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
            java.util.logging.Logger.getLogger(KhuyenMaiFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhuyenMaiFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhuyenMaiFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhuyenMaiFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhuyenMaiFrom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Date;
    private com.toedter.calendar.JDateChooser Date2;
    private javax.swing.JLabel JBtext;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnHuy1;
    private javax.swing.JButton btnNEXT;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnPRE;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTK;
    private javax.swing.JComboBox<String> cbFindTT;
    private javax.swing.JComboBox<String> cbHT;
    private javax.swing.JComboBox<String> cbTT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tbKM;
    private javax.swing.JTextField txtDK;
    private javax.swing.JTextField txtFind2;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
