/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import database.Database;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import java.sql.*;
import java.util.*;
import java.text.*;
import database.Database;

public class Transaksi_dewa extends javax.swing.JPanel {

    public Transaksi_dewa() {
        initComponents();
        FlatLaf.registerCustomDefaultsSource("crazypanel");
        addListeners();
        hitungTotal();
        tunai.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            hitungKembalian(); // Hitung kembalian setiap kali tunai diubah
        }
    });
       tanggal.setText("");

        hitungKembalian(); // Hitung kembalian otomatis saat aplikasi pertama kali dijalankan
        
        pesan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ambil data dari field input
                String Nama = nama.getText();
                String Total = total.getText();
                String Tanggal = tanggal.getText();
                String Tunai = tunai.getText();
                String Kembalian = kembalian.getText();

              
                simpanTransaksi(Nama, Total, Tanggal, Tunai, Kembalian);
            }
        });
    }
    
    // Tambahkan listener untuk perhitungan otomatis
private void addListeners() {
    // Event listener untuk jumlah barang
    jumlah1.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            hitungKembalian();
            hitungTotal(); // Panggil fungsi perhitungan
        }

        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            hitungKembalian();
            hitungTotal(); // Panggil fungsi perhitungan
        }

        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            hitungKembalian();
            hitungTotal(); // Panggil fungsi perhitungan
  

        }
    });
    tunai.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            hitungKembalian(); // Hitung kembalian ketika tunai berubah
                   }
    });

    // Event listener untuk jenis barang (JComboBox)
    jenis.addActionListener(e -> hitungTotal());
}

// Fungsi untuk menghitung total biaya
public void hitungTotal() {
    try {
        
        if(jenis.getSelectedItem().toString().equalsIgnoreCase("Silver")){
           satuan.setText("50000");
       }else if(jenis.getSelectedItem().toString().equalsIgnoreCase("Gold")){
           satuan.setText("100000");
       }
        // Ambil jumlah barang dari textfield
        int jumlahBarang = Integer.parseInt(jumlah1.getText());

        // Ambil jenis barang dari JComboBox
        String jenisBarang = jenis.getSelectedItem().toString();

        // Tentukan harga satuan berdasarkan jenis barang
        int hargaSatuan = 0;
        if (jenisBarang.equalsIgnoreCase("Silver")) {
            hargaSatuan = 50000; // Harga untuk jenis Silver
        } else if (jenisBarang.equalsIgnoreCase("Gold")) {
            hargaSatuan = 100000; // Harga untuk jenis Gold
        }

        // Hitung total biaya
        int totalBiaya = jumlahBarang * hargaSatuan;
        
       ;

        // Tampilkan hasil ke textfield
        total.setText(String.valueOf(totalBiaya)); // Hasil numerik

    } catch (NumberFormatException e) {
        // Jika input tidak valid (textfield kosong atau tidak angka), kosongkan total biaya
        total.setText("0");
        
    }
}
    private void hitungKembalian() {
    try {
        // Ambil nilai tunai dari text field
        if (tunai.getText().isEmpty()) {
            kembalian.setText("0");
            return; // Keluar jika tunai kosong
        }

        int tunaiPembeli = Integer.parseInt(tunai.getText());
        
        // Ambil nilai total yang sudah dihitung
        int totalBiaya = Integer.parseInt(total.getText());
        
         if (tunaiPembeli < totalBiaya) {
            kembalian.setText("0"); // Jangan tampilkan kembalian negatif, tampilkan 0
        } else {
            // Hitung kembalian
            int kembalianPembeli = tunaiPembeli - totalBiaya;
            kembalian.setText(String.valueOf(kembalianPembeli)); // Tampilkan hasil di text field kembalian
        }
       

    } catch (NumberFormatException e) {
        // Tangani jika tunai kosong atau salah format
        kembalian.setText("0");
    }
}
    public void simpanTransaksi(String Nama, String Total, String Tanggal, String Kembalian, String Tunai) {
        Connection com = Database.getConnection();
        String query = "INSERT INTO transaksi (nama_pelanggan, total_harga, tanggal, uang_kembali, uang_masuk) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = com.prepareStatement(query);
            ps.setString(1, Nama);
            ps.setString(2, Total);
            ps.setString(3, Tanggal);
            ps.setString(4, Kembalian);
            ps.setString(5, Tunai);
            ps.executeUpdate();
            System.out.println("Data berhasil disimpan!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (com != null) {
                    com.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2=(Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.dispose();
        super.paintComponent(g); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new tanggal.DateChooser();
        panelRound1 = new komponen.PanelRound();
        panelRound3 = new komponen.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        satuan = new javax.swing.JTextField();
        jenis = new javax.swing.JComboBox<>();
        tanggal = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        pesan = new komponen.Button();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        total = new javax.swing.JTextField();
        kembalian = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tunai = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jumlah1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        dateChooser1.setForeground(new java.awt.Color(102, 102, 255));
        dateChooser1.setTextRefernce(tanggal);

        setBackground(new java.awt.Color(228, 228, 228));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(16);
        panelRound1.setRoundBottomRight(16);
        panelRound1.setRoundTopLeft(16);
        panelRound1.setRoundTopRight(16);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound3.setBackground(new java.awt.Color(244, 244, 246));
        panelRound3.setRoundBottomLeft(15);
        panelRound3.setRoundBottomRight(15);
        panelRound3.setRoundTopLeft(15);
        panelRound3.setRoundTopRight(15);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Stock Tiket");

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        panelRound1.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 180, 230));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("TRANSAKSI PEMBELIAN");
        panelRound1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        satuan.setEditable(false);
        panelRound1.add(satuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 200, -1));

        jenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Silver", "Gold" }));
        jenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenisActionPerformed(evt);
            }
        });
        panelRound1.add(jenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalActionPerformed(evt);
            }
        });
        panelRound1.add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 120, -1));

        nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaActionPerformed(evt);
            }
        });
        panelRound1.add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 310, -1));

        pesan.setBackground(new java.awt.Color(102, 0, 255));
        pesan.setForeground(new java.awt.Color(255, 255, 255));
        pesan.setText("Pesan");
        pesan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesanActionPerformed(evt);
            }
        });
        panelRound1.add(pesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 220, -1));

        jLabel3.setText("Tanggal Pembelian");
        panelRound1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jLabel4.setText("Nama pembeli");
        panelRound1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel5.setText("Jenis Tiket");
        panelRound1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel6.setText("Satuan Harga");
        panelRound1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, -1));

        jLabel7.setText("Kembali");
        panelRound1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, -1, -1));

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelRound1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, -1, -1));

        total.setEditable(false);
        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });
        panelRound1.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 210, -1));

        kembalian.setEditable(false);
        panelRound1.add(kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 170, -1));

        jLabel8.setText("Total Harga");
        panelRound1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));
        panelRound1.add(tunai, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 170, -1));

        jLabel9.setText("Tunai");
        panelRound1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));
        panelRound1.add(jumlah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 90, -1));

        jLabel10.setText("Jumlah Tiket");
        panelRound1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));

        add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 750, 440));
    }// </editor-fold>//GEN-END:initComponents

    private void namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaActionPerformed

    private void tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalActionPerformed
        
    }//GEN-LAST:event_tanggalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dateChooser1.showPopup();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenisActionPerformed
       
    }//GEN-LAST:event_jenisActionPerformed

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
  
    }//GEN-LAST:event_totalActionPerformed

    private void pesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesanActionPerformed

    }//GEN-LAST:event_pesanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private tanggal.DateChooser dateChooser1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> jenis;
    private javax.swing.JTextField jumlah1;
    private javax.swing.JTextField kembalian;
    private javax.swing.JTextField nama;
    private komponen.PanelRound panelRound1;
    private komponen.PanelRound panelRound3;
    private komponen.Button pesan;
    private javax.swing.JTextField satuan;
    private javax.swing.JTextField tanggal;
    private javax.swing.JTextField total;
    private javax.swing.JTextField tunai;
    // End of variables declaration//GEN-END:variables
}
