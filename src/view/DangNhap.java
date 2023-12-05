/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import service.DBConnect;
import java.security.SecureRandom;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.mail.internet.MimeMessage;
import model.NhanVien;
import response.LayRaNhanVien;
import service.BanHangService;

/**
 *
 * @author Admin
 */
public class DangNhap extends javax.swing.JFrame {

    private Connection connection;
    BanHangService service = new BanHangService();

    /**
     * Creates new form NewJFrame
     */
    public DangNhap() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("ĐĂNG NHẬP HỆT THỐNG");
        connection = DBConnect.getConnection();
        lblQuenMK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                lblQuenMKMouseClicked(evt);
            }
        });
    }

    public static String getPasswordFromDatabase(String email) {
        String password = null;
        try (Connection connection = DBConnect.getConnection()) {
            String sql = "SELECT MatKhau FROM NhanVien WHERE Email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        password = resultSet.getString("MatKhau");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }

        return password;
    }

    private boolean updatePasswordInDatabase(String email, String newPassword) {
        // Kết nối đến cơ sở dữ liệu và thực hiện truy vấn cập nhật
        try (Connection connection = DBConnect.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE NhanVien SET MatKhau = ? WHERE Email = ?")) {

            // Thiết lập giá trị cho tham số truy vấn
            statement.setString(1, newPassword);
            statement.setString(2, email);

            // Thực hiện truy vấn cập nhật
            int rowsAffected = statement.executeUpdate();

            // Trả về true nếu có ít nhất một dòng bị ảnh hưởng, tức là cập nhật thành công
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi kết nối cơ sở dữ liệu hoặc truy vấn cập nhật
            return false;
        }
    }

    private String generateRandomPassword() {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8}";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Tạo mật khẩu ngẫu nhiên theo mẫu
        while (!password.toString().matches(pattern)) {
            password.setLength(0);  // Đặt lại chuỗi để tạo mật khẩu mới

            for (int i = 0; i < 8; i++) {
                int choice = random.nextInt(4);
                switch (choice) {
                    case 0:
                        password.append((char) (random.nextInt(10) + '0'));
                        break;
                    case 1:
                        password.append((char) (random.nextInt(26) + 'a'));
                        break;
                    case 2:
                        password.append((char) (random.nextInt(26) + 'A'));
                        break;
                    case 3:
                        password.append((char) (random.nextInt(6) + '@'));
                        break;
                }
            }
        }
        return password.toString();
    }

    private boolean updatePasswordInDatabase(String email) {
        // Tạo mật khẩu mới theo mẫu và độ dài mong muốn
        String newPassword = generateRandomPassword();

        // Kết nối đến cơ sở dữ liệu và thực hiện truy vấn cập nhật
        try (Connection connection = DBConnect.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE NhanVien SET MatKhau = ? WHERE Email = ?")) {

            // Thiết lập giá trị cho tham số truy vấn
            statement.setString(1, newPassword);
            statement.setString(2, email);

            // Thực hiện truy vấn cập nhật
            int rowsAffected = statement.executeUpdate();

            // Trả về true nếu có ít nhất một dòng bị ảnh hưởng, tức là cập nhật thành công
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi kết nối cơ sở dữ liệu hoặc truy vấn cập nhật
            return false;
        }
    }

    private boolean sendPasswordResetEmail(String email, String newPassword) {
        // Cài đặt thông tin của tài khoản email gửi đi
        final String senderEmail = "hoang.duc.200fc@gmail.com";
        final String senderPassword = "frytumibkpxvordb"; // Điều này là mật khẩu ứng dụng nếu bạn sử dụng Gmail, không phải mật khẩu email

        // Cài đặt thông tin máy chủ SMTP
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

        // Tạo phiên gửi email
//        Session session = Session.getInstance(properties, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(senderEmail, senderPassword);
//            }
//        });
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Tạo tin nhắn
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            message.setSubject("Khôi phục mật khẩu");

            // Nội dung email với mật khẩu mới
            String emailContent = "Xin chào,\n\nBạn đã yêu cầu khôi phục mật khẩu. Dưới đây là mật khẩu mới của bạn:\n\n" + newPassword + "\n\nCảm ơn bạn!";
            message.setText(emailContent);
            // Gửi tin nhắn
            Transport.send(message);
            // Gửi email thành công
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            // Gửi email thất bại
            return false;
        }
    }

    private boolean sendPasswordResetEmail(String email) {
        String password = getPasswordFromDatabase(email);

        if (password != null && !password.isEmpty()) {
            String newPassword = generateRandomPassword();

            // Cập nhật mật khẩu mới vào cơ sở dữ liệu
            boolean passwordUpdated = updatePasswordInDatabase(email, newPassword);

            if (passwordUpdated) {
                // Gửi email với mật khẩu mới
                boolean emailSent = sendPasswordResetEmail(email, newPassword);

                if (emailSent) {
                    JOptionPane.showMessageDialog(this, "Chúng tôi đã gửi một email khôi phục mật khẩu đến địa chỉ email của bạn.");
                } else {
                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình gửi email. Vui lòng thử lại sau.");
                }
                return emailSent; // Trả về kết quả gửi email
            } else {
                // Xử lý khi cập nhật mật khẩu vào cơ sở dữ liệu thất bại
                return false;
            }
        } else {
            // Mật khẩu không tồn tại trong cơ sở dữ liệu
            return false;
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

        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        btnOut = new javax.swing.JButton();
        lblQuenMK = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        cboPass = new javax.swing.JCheckBox();

        jPasswordField1.setText("jPasswordField1");

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ĐĂNG NHẬP");

        lblAnh.setBackground(new java.awt.Color(255, 255, 255));
        lblAnh.setText("jLabel2");
        lblAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("TÀI KHOẢN");

        jLabel5.setText("MẬT KHẨU");

        btnLogin.setText("Đăng nhập");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnOut.setText("Thoát");
        btnOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOutActionPerformed(evt);
            }
        });

        lblQuenMK.setText("Quên mật khẩu ?");
        lblQuenMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuenMKMouseClicked(evt);
            }
        });

        cboPass.setText("Hiện mật khẩu");
        cboPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboPassMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblQuenMK)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnLogin)
                            .addGap(18, 18, 18)
                            .addComponent(btnOut))
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtMatKhau)))
                    .addComponent(cboPass))
                .addGap(41, 41, 41))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLogin, btnOut});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboPass)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnOut))
                .addGap(18, 18, 18)
                .addComponent(lblQuenMK)
                .addContainerGap(11, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLogin, btnOut});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        String username = txtTaiKhoan.getText();
        char[] passwordChars = txtMatKhau.getPassword();
        String password = new String(passwordChars);

        // Kiểm tra xem các trường có được nhập không
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin tài khoản và mật khẩu.");
            return; // Kết thúc phương thức nếu thông tin không hợp lệ
        }

        // Kiểm tra trạng thái kết nối
        try {
            if (connection != null && !connection.isClosed()) {
                // Tiếp tục sử dụng kết nối
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi: Kết nối database chưa được mở hoặc đã đóng.");
                return;
            }

            // Thực hiện truy vấn SQL
            String sql = "SELECT * FROM NhanVien WHERE Email = ? AND MatKhau = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Đăng nhập thành công
                        JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");

                        // Mở form QuanLySanPham
                        String email = txtTaiKhoan.getText();
                        LayRaNhanVien nv = service.layNhanVien(email);

                        QuanLySanPham quanLySanPhamForm = new QuanLySanPham(nv);
                        quanLySanPhamForm.setVisible(true);

                        //BanHangForm banhangForm = new BanHangForm(nv);
                        // Đóng form đăng nhập (nếu bạn muốn)
                        dispose();
                    } else {
                        // Sai tên đăng nhập hoặc mật khẩu
                        JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu!");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi kết nối database: " + e.getMessage());
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOutActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnOutActionPerformed

    private void lblQuenMKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuenMKMouseClicked
        // TODO add your handling code here:
        String email = JOptionPane.showInputDialog(this, "Nhập địa chỉ email của bạn:");
        if (email != null && !email.isEmpty()) {
            // Gửi email khôi phục mật khẩu
            boolean emailSent = sendPasswordResetEmail(email);
            if (emailSent) {
                JOptionPane.showMessageDialog(this, "Chúng tôi đã gửi một email khôi phục mật khẩu đến địa chỉ email của bạn.");
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình gửi email. Vui lòng thử lại sau.");
            }
        }
    }//GEN-LAST:event_lblQuenMKMouseClicked

    private void cboPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboPassMouseClicked
        // TODO add your handling code here:
        if (cboPass.isSelected()) {
            txtMatKhau.setEchoChar((char) 0);
        } else {
            txtMatKhau.setEchoChar('*');
        }
    }//GEN-LAST:event_cboPassMouseClicked

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
            java.util.logging.Logger.getLogger(DangNhap.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnOut;
    private javax.swing.JCheckBox cboPass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblQuenMK;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
