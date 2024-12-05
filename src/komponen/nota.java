/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package komponen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import com.loket.main.Main;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import raven.glasspanepopup.GlassPanePopup;

public class nota extends javax.swing.JPanel {

    public nota() {
        initComponents();
        setOpaque(false);
    }
    
    
    public void setNotaData(String Nama, String Tanggal, String JenisTiket, String JumlahTiket, 
                            String HargaSatuan, String Total, String Tunai, String Kembalian, String IdTransaksi) {
        // Set data ke komponen nota
     lblNama.setText(Nama);
    lblTanggal.setText(Tanggal);
    lblJenisTiket.setText(JenisTiket);
    lblJumlah.setText(String.valueOf(JumlahTiket));
    lblHargaSatuan.setText(HargaSatuan);
    lblTotal.setText(Total);
    lblTunai.setText(Tunai);
    lblKembalian.setText(Kembalian);
    id_transaksi.setText(IdTransaksi);
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblKembalian = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        lblJenisTiket = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblHargaSatuan = new javax.swing.JLabel();
        lblJumlah = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblTunai = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblNama1 = new javax.swing.JLabel();
        lblTanggal1 = new javax.swing.JLabel();
        lblJenisTiket1 = new javax.swing.JLabel();
        lblJumlah1 = new javax.swing.JLabel();
        lblHargaSatuan1 = new javax.swing.JLabel();
        lblTotal1 = new javax.swing.JLabel();
        lblTunai1 = new javax.swing.JLabel();
        lblKembalian1 = new javax.swing.JLabel();
        id_transaksi = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/loket/icon/t.aom.png"))); // NOI18N
        jLabel1.setText(".aom");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 6, -1, -1));

        lblKembalian.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblKembalian.setText("Kembali");
        add(lblKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("________________________________________________________________");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, 20));

        lblTanggal.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblTanggal.setText("Tanggal Pembelian ");
        add(lblTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, -1, -1));

        lblJenisTiket.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblJenisTiket.setText("Jenis Tiket ");
        add(lblJenisTiket, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, -1, -1));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotal.setText("TOTAL ");
        add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, -1));

        lblHargaSatuan.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblHargaSatuan.setText("Harga satuan ");
        add(lblHargaSatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));

        lblJumlah.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblJumlah.setText("Jumlah Tiket ");
        add(lblJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("________________________________________________________________");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 20));

        lblTunai.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblTunai.setText("Tunai ");
        add(lblTunai, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Pembayaran Berhasil");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblNama.setText("Nama ");
        add(lblNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, 20));

        lblNama1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblNama1.setText("Nama                     :");
        add(lblNama1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 20));

        lblTanggal1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblTanggal1.setText("Tanggal Pembelian :");
        add(lblTanggal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        lblJenisTiket1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblJenisTiket1.setText("Jenis Tiket     :");
        add(lblJenisTiket1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        lblJumlah1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblJumlah1.setText("Jumlah Tiket :");
        add(lblJumlah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        lblHargaSatuan1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblHargaSatuan1.setText("Harga satuan :");
        add(lblHargaSatuan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        lblTotal1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotal1.setText("TOTAL :");
        add(lblTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        lblTunai1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblTunai1.setText("Tunai     :");
        add(lblTunai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        lblKembalian1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblKembalian1.setText("Kembali :");
        add(lblKembalian1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        id_transaksi.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        id_transaksi.setText("TRANS");
        add(id_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel id_transaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblHargaSatuan;
    private javax.swing.JLabel lblHargaSatuan1;
    private javax.swing.JLabel lblJenisTiket;
    private javax.swing.JLabel lblJenisTiket1;
    private javax.swing.JLabel lblJumlah;
    private javax.swing.JLabel lblJumlah1;
    private javax.swing.JLabel lblKembalian;
    private javax.swing.JLabel lblKembalian1;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNama1;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblTanggal1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotal1;
    private javax.swing.JLabel lblTunai;
    private javax.swing.JLabel lblTunai1;
    // End of variables declaration//GEN-END:variables
}
