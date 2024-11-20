/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package komponen;

import Awalan.jajal_peg;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import raven.glasspanepopup.GlassPanePopup;


public class pesan_akunberhasildibuat extends javax.swing.JPanel {
    

    
    public pesan_akunberhasildibuat() {
        initComponents();
        setOpaque(false);
    }

    
   

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2=(Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(),getHeight(), 15, 15));
         
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextPane1 = new javax.swing.JTextPane();
        sign = new komponen.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/komponen/idea (1).png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, 88));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 255));
        jLabel2.setText("Akun dibuat");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTextPane1.setBorder(null);
        jTextPane1.setForeground(new java.awt.Color(102, 102, 102));
        jTextPane1.setText("         Akun anda sudah berhasil di buat\n                 tekan oke untuk lanjut");
        add(jTextPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, 33));

        sign.setBackground(new java.awt.Color(153, 153, 255));
        sign.setForeground(new java.awt.Color(255, 255, 255));
        sign.setText("OKE");
        sign.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signActionPerformed(evt);
            }
        });
        add(sign, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 270, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void signActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signActionPerformed
        GlassPanePopup.closePopupLast();
        jajal_peg awalFrame=new jajal_peg();
                    awalFrame.setVisible(true);
                    awalFrame.pack();
                    awalFrame.setLocationRelativeTo(null);
                    
    }//GEN-LAST:event_signActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextPane jTextPane1;
    private komponen.Button sign;
    // End of variables declaration//GEN-END:variables
}
