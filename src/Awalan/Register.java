/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Awalan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komponen.pesan_akunberhasildibuat;
import komponen.pesan_pwsalah;
import komponen.pesan_usernamesalah;
import raven.glasspanepopup.GlassPanePopup;

/**
 *
 * @author ASUS
 */
public class Register extends javax.swing.JPanel {

    public static Connection com;
     public static PreparedStatement pst;

   
    public Register() {
        initComponents();
        
    }
    
     public void addEventlogin(ActionListener event){
        login.addActionListener(event);   
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jeneng = new swing.TextField();
        pw = new swing.PasswordField();
        registrasi = new komponen.Button();
        login = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Create to your account");

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Saya sudah punya akun!");

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

        registrasi.setBackground(new java.awt.Color(102, 102, 255));
        registrasi.setForeground(new java.awt.Color(255, 255, 255));
        registrasi.setText("REGISTRASI");
        registrasi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        registrasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                registrasiMousePressed(evt);
            }
        });
        registrasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrasiActionPerformed(evt);
            }
        });

        login.setBackground(new java.awt.Color(102, 0, 255));
        login.setForeground(new java.awt.Color(255, 102, 255));
        login.setText("Login");
        login.setBorder(null);
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 74, 173));
        jLabel4.setText("Sign");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(203, 108, 230));
        jLabel5.setText("Up.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(registrasi, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                        .addComponent(pw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jeneng, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(login)))
                    .addComponent(jLabel1))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(login))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jeneng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(pw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(registrasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jenengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenengActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jenengActionPerformed

    private void registrasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrasiActionPerformed
      String nama, password, query;
      String url, user, pass;
      url = "jdbc:mysql://localhost:3306/loket_tiket";
    user = "root";
    pass = "";

    Connection com = null;
    PreparedStatement pst = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        com = DriverManager.getConnection(url, user, pass);

        if (jeneng.getText().isEmpty()) {
            GlassPanePopup.showPopup(new pesan_usernamesalah());
        } else if (pw.getText().isEmpty()) {
            GlassPanePopup.showPopup(new pesan_pwsalah());
        } else {
            nama = jeneng.getText();
            password = passhash(pw.getText());
            System.out.println(password);

            query = "INSERT INTO login (username, password) VALUES (?, ?)";
            pst = com.prepareStatement(query);
            pst.setString(1, nama);
            pst.setString(2, password);

            pst.executeUpdate(); 
            jeneng.setText("");
            pw.setText("");
            GlassPanePopup.showPopup(new pesan_akunberhasildibuat());
        }
    } catch (ClassNotFoundException e) {
        System.out.println("Driver tidak ditemukan: " + e.getMessage());
    }       catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_registrasiActionPerformed
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
    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginActionPerformed

    private void registrasiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registrasiMousePressed

        
    }//GEN-LAST:event_registrasiMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private swing.TextField jeneng;
    private javax.swing.JButton login;
    private swing.PasswordField pw;
    private komponen.Button registrasi;
    // End of variables declaration//GEN-END:variables

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

