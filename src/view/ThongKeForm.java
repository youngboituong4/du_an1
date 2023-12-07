/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

/**
 *
 * @author thinh
 */
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ThongKe;
import model.ThongKe1;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import service.ThongKeService;

public class ThongKeForm extends javax.swing.JPanel {

    /**
     * Creates new form ThongKeFormm
     */
    DefaultTableModel model = new DefaultTableModel();
    ThongKeService service = new ThongKeService();
    int index = -1;
    int id = 0;
    int nam = 0;
    int namm = 0;
    int thang = 0;
    int ngay = 0;
    int tu = 0;
    int den = 0;
    String ngayy = null;
    
    
    public ThongKeForm() {
        initComponents();
        
        BDThongKe();
        HienThi();
        ThongKe0();
        
        this.fillThongKe(service.getThongKe());

        this.fillThongKe1(service.getT5ThongKe());
        themNamCbo();
        themThangCbo();
        jButton3.setEnabled(false);
        jButton3.setBackground(Color.cyan);
    }

    public void themNamCbo() {
        jComboBox1.removeAllItems();
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        jComboBox1.addItem("5 năm gần nhất");
        for (int i = currentYear; i >= currentYear - 4; i--) {
            jComboBox1.addItem(i + "");
        }
    }

    public void themThangCbo() {
        jComboBox2.removeAllItems();
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        jComboBox2.addItem("Tất cả các tháng");
        for (int i = 1; i <= 12; i++) {
            jComboBox2.addItem(i + "");
        }

    }

    public void Nam() {
        // Lấy đối tượng Calendar cho múi giờ địa phương
        Calendar calendar = Calendar.getInstance();

        // Lấy múi giờ địa phương
        TimeZone localTimeZone = calendar.getTimeZone();

        // Lấy năm hiện tại theo địa phương
        int localYear = calendar.get(Calendar.YEAR);
    }

    public void BDThongKe() {PanelChart.removeAll();
        DefaultCategoryDataset BarChartData = new DefaultCategoryDataset();
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        for (int i = currentYear; i >= currentYear - 4; i--) {
            nam = i;
            BarChartData.setValue(service.DoanhThuNam(nam), "Doanh thu", i + "");
        }

//        BarChartData.setValue(45000000, "Doanh thu", "2021");
//        
        JFreeChart barChart = ChartFactory.createBarChart("Doanh thu", "Năm", "", BarChartData, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot barchrt = barChart.getCategoryPlot();
        barchrt.setRangeGridlinePaint(getBackground());

        ChartPanel barPanel = new ChartPanel(barChart);
        PanelChart.removeAll();
        PanelChart.add(barPanel);
        PanelChart.validate();
    }

    public void HienThi() {
        jLabel11.setText(service.SoKhachHang() + "");
        int amount = service.DoanhThu();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String formattedAmount = decimalFormat.format(amount);
        jLabel9.setText(formattedAmount + "" + "" + " VNĐ");
        
        jLabel1.setText("Doanh thu");
        jLabel10.setText(service.HoaDonDaThanhToan() + "");
        
    }
    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
    public void ThongKe0() {
        model = (DefaultTableModel) tblThongKe.getModel();
        model.setRowCount(0);
    }

    private void fillThongKe(List<ThongKe> thongKe) {
        model = (DefaultTableModel) tblThongKe.getModel();
        for (ThongKe thongKe1 : thongKe) {
            model.addRow(thongKe1.toData());
        }
    }
    private void fillThongKe1(List<ThongKe1> t5ThongKe) {
        model = (DefaultTableModel) tblT5ThongKe.getModel();
        for (ThongKe1 thongKe1 : t5ThongKe) {
            model.addRow(thongKe1.toData());
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Date = new com.toedter.calendar.JDateChooser();
        Date2 = new com.toedter.calendar.JDateChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelChart = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblT5ThongKe = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Doanh thu");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("l");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 153));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Số Khách Hàng");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("l");
        jLabel11.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel11AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(153, 255, 153));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Số Hóa Đơn");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("l");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("  Sản Phẩm Đã Bán");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả các năm", "2023", "2022", "2021", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Năm");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, 0, 157, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel5.setText("Từ");

        jLabel6.setText("Đến");

        Date.setDateFormatString("yyyy-MM-dd");

        Date2.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Date2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Date2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        PanelChart.setBackground(new java.awt.Color(255, 0, 0));
        PanelChart.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Biểu đồ thống kê ", PanelChart);

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Kích thước", "Màu sắc", "Chất liệu", "Thương hiệu", "Giá bán", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblThongKe);

        jTabbedPane1.addTab("  Sản Phẩm Đã Bán", jScrollPane1);

        tblT5ThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Kích thước", "Màu sắc", "Chất liệu", "Thương hiệu", "Giá bán", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblT5ThongKe);

        jTabbedPane1.addTab("Top 5 bán chạy nhất", jScrollPane2);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel8.setText("Tháng");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, 0, 160, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgmain/thong_ke.png"))); // NOI18N
        jButton1.setText("DT hôm nay");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 255));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/timkiem.png"))); // NOI18N
        jButton2.setText("Tìm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 255));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/them.png"))); // NOI18N
        jButton4.setText("Xuất file Excel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 255));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/them.png"))); // NOI18N
        jButton3.setText("Báo cáo DTHN");
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
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jTabbedPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(43, 43, 43)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel11AncestorAdded

    }//GEN-LAST:event_jLabel11AncestorAdded

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String selectedValue = (String) jComboBox1.getSelectedItem();
        if (selectedValue != null) {
            DefaultCategoryDataset BarChartData = new DefaultCategoryDataset();
            if (jComboBox1.getSelectedItem().equals("5 năm gần nhất")) {
                HienThi();
                LocalDate now = LocalDate.now();
                int currentYear = now.getYear();
                for (int i = currentYear; i >= currentYear - 4; i--) {
                    nam = i;
                    BarChartData.setValue(service.DoanhThuNam(nam), "Doanh thu", i + "");

                }

                JFreeChart barChart = ChartFactory.createBarChart("Doanh thu", "Năm", "", BarChartData, PlotOrientation.VERTICAL, false, true, false);

                CategoryPlot barchrt = barChart.getCategoryPlot();
                barchrt.setRangeGridlinePaint(getBackground());

                ChartPanel barPanel = new ChartPanel(barChart);

                PanelChart.removeAll();
                PanelChart.add(barPanel);
                PanelChart.validate();

                jComboBox2.setSelectedIndex(0);
                jComboBox2.setEnabled(false);
                jComboBox2.setBackground(Color.LIGHT_GRAY);
            } else {
                if(selectedValue != "5 năm gần nhất"){
                    jComboBox2.setEnabled(true);
                    jComboBox2.setBackground(Color.WHITE);

                    namm = Integer.parseInt(selectedValue);
                    jLabel1.setText("Doanh thu năm " + namm);

                    int amount = service.DoanhThuNam(namm);
                    DecimalFormat decimalFormat = new DecimalFormat("#,##0");
                    String formattedAmount = decimalFormat.format(amount);
                    jLabel9.setText(formattedAmount + "" + "" + " VNĐ");
                    jLabel10.setText("" + service.HoaDonNam(namm));
                    System.out.println(selectedValue);
                    for (int i = 1; i <= 12; i++) {
                        thang = i;
                        BarChartData.setValue(service.DoanhThuThangTrongNam(thang, namm), "Doanh thu", i + "");

                    }
                    JFreeChart barChart = ChartFactory.createBarChart("Doanh thu", "Tháng", "", BarChartData, PlotOrientation.VERTICAL, false, true, false);
                    CategoryPlot barchrt = barChart.getCategoryPlot();
                    barchrt.setRangeGridlinePaint(getBackground());

                    ChartPanel barPanel = new ChartPanel(barChart);
                    PanelChart.removeAll();
                    PanelChart.add(barPanel);
                    PanelChart.validate();
                    jComboBox2.setSelectedIndex(0);
                }
            }

        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String selectedValue = (String) jComboBox1.getSelectedItem();
        String selectedValues = (String) jComboBox2.getSelectedItem();
        DefaultCategoryDataset BarChartData = new DefaultCategoryDataset();
        if (selectedValue != "5 năm gần nhất") {

            if (jComboBox2.getSelectedItem().equals("Tất cả các tháng")) {
                namm = Integer.parseInt(selectedValue);
                jLabel1.setText("Doanh thu năm " + namm);

                int amount = service.DoanhThuNam(namm);
                DecimalFormat decimalFormat = new DecimalFormat("#,##0");
                String formattedAmount = decimalFormat.format(amount);
                jLabel9.setText(formattedAmount + "" + "" + " VNĐ");
                jLabel10.setText("" + service.HoaDonNam(namm));

                for (int i = 1; i <= 12; i++) {
                    thang = i;
                    BarChartData.setValue(service.DoanhThuThangTrongNam(thang, namm), "Doanh thu", thang + "");
                }
                JFreeChart barChart = ChartFactory.createBarChart("Doanh thu", "Tháng", "", BarChartData, PlotOrientation.VERTICAL, false, true, false);
                CategoryPlot barchrt = barChart.getCategoryPlot();
                barchrt.setRangeGridlinePaint(getBackground());

                ChartPanel barPanel = new ChartPanel(barChart);
                PanelChart.removeAll();
                PanelChart.add(barPanel);
                PanelChart.validate();

            } else {

                namm = Integer.parseInt(selectedValue);
                thang = Integer.parseInt(selectedValues);
                jLabel1.setText("Doanh thu tháng " + thang + " " + "năm " + namm + "");

                int amount = service.DoanhThuThangTrongNam(thang, namm);
                DecimalFormat decimalFormat = new DecimalFormat("#,##0");
                String formattedAmount = decimalFormat.format(amount);
                jLabel9.setText(formattedAmount + "" + "" + " VNĐ");
                jLabel10.setText(service.HoaDonThang(namm, thang)+"");
                YearMonth yearMonth = YearMonth.of(namm, thang);
                int daysInMonth = yearMonth.lengthOfMonth();
                for (int j = 1; j <= daysInMonth; j++) {
                    ngay = j;
                    BarChartData.setValue(service.DoanhThuNgayThangNam(ngay, thang, namm), "Doanh thu", j + "");
                }
                JFreeChart barChart = ChartFactory.createBarChart("Doanh thu", "Ngày", "", BarChartData, PlotOrientation.VERTICAL, false, true, false);
                CategoryPlot barchrt = barChart.getCategoryPlot();
                barchrt.setRangeGridlinePaint(getBackground());

                ChartPanel barPanel = new ChartPanel(barChart);
                PanelChart.removeAll();
                PanelChart.add(barPanel);
                PanelChart.validate();
            }
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButton3.setEnabled(true);
        jButton3.setBackground(Color.BLUE);
        Date.setDate(null);
        Date2.setDate(null);
        LocalDate currentDateTime = LocalDate.now();
        LocalDate ngay = currentDateTime;
        service.DoanhThuHomNay(ngay);
        int amount = service.DoanhThuHomNay(ngay);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String formattedAmount = decimalFormat.format(amount);
        jLabel1.setText("Doanh thu hôm nay");

        jLabel9.setText(formattedAmount + "" + " " + "VNĐ");
        jLabel10.setText(service.HoaDonDaThanhToanHomNay(ngay)+"");
        jLabel11.setText(service.SoKhachHangHomNay(ngay) + "");

        DefaultCategoryDataset BarChartData = new DefaultCategoryDataset();

        BarChartData.setValue(service.DoanhThuHomNay(ngay), "Doanh thu", ngay);

        JFreeChart barChart = ChartFactory.createBarChart("Doanh thu", "Năm", "", BarChartData, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot barchrt = barChart.getCategoryPlot();
        barchrt.setRangeGridlinePaint(getBackground());

        ChartPanel barPanel = new ChartPanel(barChart);
        PanelChart.removeAll();
        PanelChart.add(barPanel);
        PanelChart.validate();
        ;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (Date.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Chưa chọn ngày bắt đầu tìm");

        } else {
            if (Date2.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Chưa chọn ngày kết thúc tìm");
            } else {
                jComboBox1.setSelectedIndex(0);
                jComboBox2.setSelectedIndex(0);

                SimpleDateFormat From = new SimpleDateFormat("yyyy-MM-dd");
                String from = From.format(Date.getDate());

                SimpleDateFormat dayFrom = new SimpleDateFormat("dd");
                SimpleDateFormat monthFrom = new SimpleDateFormat("MM");
                SimpleDateFormat yearFrom = new SimpleDateFormat("yyyy");
                String date = dayFrom.format(Date.getDate());
                String month = monthFrom.format(Date.getDate());
                String year = yearFrom.format(Date.getDate());

                SimpleDateFormat To = new SimpleDateFormat("yyyy-MM-dd");
                String to = To.format(Date2.getDate());

                SimpleDateFormat dayTo = new SimpleDateFormat("dd");
                SimpleDateFormat monthTo = new SimpleDateFormat("MM");
                SimpleDateFormat yearTo = new SimpleDateFormat("yyyy");
                String date2 = dayTo.format(Date2.getDate());
                String month2 = monthTo.format(Date2.getDate());
                String year2 = yearTo.format(Date2.getDate());

                ngay = Integer.parseInt(date);
                namm = Integer.parseInt(year);
                thang = Integer.parseInt(month);
                DefaultCategoryDataset BarChartData = new DefaultCategoryDataset();

                LocalDate startDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
                LocalDate endDate = LocalDate.of(Integer.parseInt(year2), Integer.parseInt(month2), Integer.parseInt(date2));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter formatterr = DateTimeFormatter.ofPattern("MM/dd");

                LocalDate currentDate = startDate;
                while (!currentDate.isAfter(endDate)) {
                    String formattedDate = currentDate.format(formatter);
                    String formattedDatee = currentDate.format(formatterr);

                    ngayy = formattedDate;
                    BarChartData.setValue(service.TimFromTo(ngayy), "Doanh thu", formattedDatee + "");

                    currentDate = currentDate.plusDays(1);

                }

                JFreeChart barChart = ChartFactory.createBarChart("Doanh thu", "Ngày", "", BarChartData, PlotOrientation.VERTICAL, false, true, false);
                CategoryPlot barchrt = barChart.getCategoryPlot();
                barchrt.setRangeGridlinePaint(getBackground());

                ChartPanel barPanel = new ChartPanel(barChart);
                PanelChart.removeAll();
                PanelChart.add(barPanel);
                PanelChart.validate();
                PanelChart.validate();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString());
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("customer");
                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tblThongKe.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblThongKe.getColumnName(i));
                }
                for (int j = 0; j < tblThongKe.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tblThongKe.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (tblThongKe.getValueAt(j, k) != null) {
                            cell.setCellValue(tblThongKe.getValueAt(j, k).toString());
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
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            
        LocalDate currentDateTime = LocalDate.now();
        LocalDate ngay = currentDateTime;

        // Sender's email address and password
        final String senderEmail = "hoang.duc.200fc@gmail.com";
        final String senderPassword = "frytumibkpxvordb";
        // Receiver's email address
        String receiverEmail = "hung06278@gmail.com";

        // Create properties for the SMTP session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
//        properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
//        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.user", senderEmail);
        properties.put("mail.smtp.password", senderPassword);

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        int amount = service.DoanhThuHomNay(ngay);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String formattedAmount = decimalFormat.format(amount);
        try {
            // Create an email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
            message.setSubject("BÁO CÁO DOANH THU HÔM NAY");
            message.setText("Doanh thu hôm nay ngày " + ngay + ": " + formattedAmount + "" + ",000 " + "VNĐ"
                    + "\nHóa đơn đã thanh toán: " + service.HoaDonDaThanhToanHomNay(ngay) + ""
                    + "\nLượng khách hàng hôm nay: " + service.SoKhachHangHomNay(ngay) + ""
            );

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");
            JOptionPane.showMessageDialog(this, "Đã gửi tới email: "+receiverEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "ok");
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Date;
    private com.toedter.calendar.JDateChooser Date2;
    private javax.swing.JPanel PanelChart;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblT5ThongKe;
    private javax.swing.JTable tblThongKe;
    // End of variables declaration//GEN-END:variables
}
