/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Awalan;

import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorListener;
import komponen.pesan_pwsalah;
import komponen.pesan_usernamedanpwsalah;
import komponen.pesan_usernamesalah;
import komponen.pesan_usernatidakditemukan;
import main.Main;
import raven.glasspanepopup.GlassPanePopup;

/**
 *
 * @author ASUS
 */
public class Login extends javax.swing.JPanel {
    
    public static Connection com;


 
    public Login() {
        initComponents();
        
    }
    
    public void addEventRegister(ActionListener event){
        register.addActionListener(event);
    }
    
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jeneng = new swing.TextField();
        pw = new swing.PasswordField();
        login = new komponen.Button();
        jLabel4 = new javax.swing.JLabel();
        register = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Login to your account");

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Belum punya akun?");

        jeneng.setForeground(new java.awt.Color(51, 51, 51));
        jeneng.setCaretColor(new java.awt.Color(51, 51, 51));
        jeneng.setHint("Username");
        jeneng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenengActionPerformed(evt);
            }
        });

        pw.setForeground(new java.awt.Color(51, 51, 51));
        pw.setCaretColor(new java.awt.Color(51, 51, 51));
        pw.setHint("Password");

        login.setBackground(new java.awt.Color(102, 0, 204));
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("LOGIN");
        login.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(203, 108, 230));
        jLabel4.setText("Sign");

        register.setForeground(new java.awt.Color(204, 51, 255));
        register.setText("Sign Up");
        register.setBorder(null);
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        register.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 74, 173));
        jLabel5.setText("In.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                        .addComponent(pw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jeneng, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(register))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(register)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jeneng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(pw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jenengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenengActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jenengActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
                                   
    String nama, password, passDB = null,level = null;
    String url = "jdbc:mysql://localhost:3306/loket_tiket";
    String suser = "root";
    String spass = "";
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        com = DriverManager.getConnection(url, suser, spass);
        
        if (jeneng.getText().isEmpty()) {
            GlassPanePopup.showPopup(new pesan_usernamesalah());
            return;
        }
        
        if (pw.getText().isEmpty()) {
            GlassPanePopup.showPopup(new pesan_pwsalah());
            return;
        }
        
        nama = jeneng.getText();
        password = passhash(pw.getText());
        
        String query = "SELECT * FROM login WHERE username = ?";
        
        PreparedStatement pstmt = com.prepareStatement(query);
        pstmt.setString(1, nama);
        ResultSet ler = pstmt.executeQuery();
        
        if (ler.next()) {
            level = ler.getString("level");
            passDB = ler.getString("password");
            if (password.equals(passDB)) {
                if (level.equalsIgnoreCase("Admin")) {
                    Main menuAdmin = new Main();
                    menuAdmin.setVisible(true);
                
                } else if (level.equalsIgnoreCase("Pegawai")) {
                    Main menuPegawai = new Main();
                    menuPegawai.setVisible(true);
                 
                }
            } else {
                GlassPanePopup.showPopup(new pesan_usernamedanpwsalah());
            }
        } else {
            GlassPanePopup.showPopup(new pesan_usernatidakditemukan());
        }
        
        jeneng.setText("");
        pw.setText("");
        
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("error: " + e.getMessage());
    }

    }//GEN-LAST:event_loginActionPerformed

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        
    }//GEN-LAST:event_registerActionPerformed

     public static String passhash(String password){
        try {
            MessageDigest md=MessageDigest.getInstance("SHA");
            md.update(password.getBytes());
            byte[] rbt=md.digest();
            StringBuilder sb=new StringBuilder();
            for(byte b:rbt){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
        }
         return null;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private swing.TextField jeneng;
    private komponen.Button login;
    private swing.PasswordField pw;
    private javax.swing.JButton register;
    // End of variables declaration//GEN-END:variables

   

    void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void pack() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
