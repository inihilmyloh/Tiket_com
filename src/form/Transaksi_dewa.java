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
import java.time.LocalDate;
import javax.swing.SpinnerNumberModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaksi_dewa extends javax.swing.JPanel {

    public Transaksi_dewa() {
        initComponents();
        FlatLaf.registerCustomDefaultsSource("crazypanel");
        addListeners();
        hitungsatuan();
        tunai.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            hitungKembalian(); // Hitung kembalian setiap kali tunai diubah
        }
    });
       tanggal.setText("");
       Jumlah.setValue(0);
       satuan.setVisible(true);

        hitungKembalian(); // Hitung kembalian otomatis saat aplikasi pertama kali dijalankan
        setSpinnerStock();
         pesan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ambil data dari field input
                String Nama = nama.getText();
                String Total = total.getText();
                String Tanggal = tanggal.getText();
                String Tunai = tunai.getText();
                String Kembalian = kembalian.getText();

                // Simpan transaksi
                simpanTransaksi(Nama, Total, Tanggal, Tunai, Kembalian);
            }
        });
         setTanggalOtomatis();
         
         
    }
    
    private void setTanggalOtomatis() {
    LocalDate today = LocalDate.now(); // Ambil tanggal hari ini
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Format tanggal
    tanggal.setText(today.format(formatter)); // Set teks tanggal ke input field
  }
    
    private void hitungsatuan(){
        if (jenis.getSelectedItem().toString().equalsIgnoreCase("Silver")) {
        satuan.setText("50000");
    } else if (jenis.getSelectedItem().toString().equalsIgnoreCase("Gold")) {
        satuan.setText("100000");
    }
    }
 
    
    private void setSpinnerStock() {
    String query = "SELECT stock FROM tiket WHERE jenis_tiket = ?";
        try (Connection com = Database.getConnection()) {
            if (com == null) {
                throw new SQLException("Koneksi ke database gagal.");
            }

            if (jenis.getSelectedItem() != null) {
                PreparedStatement ps = com.prepareStatement(query);
                ps.setString(1, jenis.getSelectedItem().toString());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int stock = rs.getInt("stock");
                    Jumlah.setModel(new SpinnerNumberModel(0, 0, stock, 1));
                } else {
                    System.out.println("Data tidak ditemukan untuk jenis tiket: " + jenis.getSelectedItem());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error saat mengatur spinner stock: " + e.getMessage());
        }
    }
    
    
   
private void addListeners() {
    // Listener untuk JSpinner (Jumlah)
    Jumlah.addChangeListener(e -> {
        hitungTotal();
        hitungKembalian();
    });

    // Listener untuk JComboBox (jenis)
    jenis.addActionListener(e -> {
        hitungTotal();
        hitungKembalian();
        setSpinnerStock(); 
    });

    // Listener untuk JTextField (tunai)
    tunai.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            hitungKembalian();
        }
    });
}


// Fungsi untuk menghitung total biaya
public void hitungTotal() {
    try {
        if (jenis.getSelectedItem() != null) { // Validasi JComboBox
            int hargaSatuan = jenis.getSelectedItem().toString().equalsIgnoreCase("Silver") ? 50000 : 100000;
            int jumlahBarang = (int) Jumlah.getValue(); // Ambil nilai dari spinner
            
            int totalBiaya = jumlahBarang * hargaSatuan;
            total.setText(String.valueOf(totalBiaya)); // Set total harga
        } else {
            total.setText("0"); // Default jika tidak ada item dipilih
        }
    } catch (Exception e) {
        total.setText("0"); // Jika ada error, tampilkan 0
        e.printStackTrace();
    }
}

public String generateIdTransaksi(String jenisTiket) {
    String idTransaksi = "";
    try {
        // Koneksi database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/loket_tiket", "root", "");

        // Ambil nilai auto-increment terakhir dari database
        String query = "SELECT COUNT(*) FROM transaksi";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1) + 1; // Nilai auto-increment selanjutnya
        }

        // Format auto-increment menjadi 2 digit
        String autoIncrement = String.format("%02d", count);

        // Ambil 2 digit terakhir dari tanggal saat ini
        LocalDate today = LocalDate.now();
        String tanggal = String.format("%02d", today.getDayOfMonth());

        // Buat ID transaksi
        idTransaksi = jenisTiket + autoIncrement + tanggal; // Contoh: G0113
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return idTransaksi;
}


private void hitungKembalian() {
    try {
        if (tunai.getText().isEmpty() || total.getText().isEmpty()) {
            kembalian.setText("0");
            return; // Jika kosong, langsung keluar
        }

        int tunaiPembeli = Integer.parseInt(tunai.getText());
        int totalBiaya = Integer.parseInt(total.getText());

        // Validasi jika tunai kurang dari total
        if (tunaiPembeli < totalBiaya) {
            kembalian.setText("0");
        } else {
            int kembalianPembeli = tunaiPembeli - totalBiaya;
            kembalian.setText(String.valueOf(kembalianPembeli));
        }
    } catch (NumberFormatException e) {
        kembalian.setText("0"); // Error format input
    }
}

    public void simpanTransaksi(String Nama, String Total, String Tanggal, String Tunai, String Kembalian) {
    if (Nama.isEmpty() || Total.isEmpty() || Tanggal.isEmpty() || Tunai.isEmpty()) {
        System.out.println("Semua field harus diisi.");
        return;
    }
    
    try (Connection com = Database.getConnection()) {
        // Ubah jenis tiket menjadi angka 1 untuk Silver dan 2 untuk Gold
        String jenisTiket = jenis.getSelectedItem().toString();
        String kodeTiket = jenisTiket.equalsIgnoreCase("Silver") ? "1" : "2"; 
        
        // Ambil nomor transaksi terakhir
        String lastIdQuery = "SELECT id_transaksi FROM transaksi WHERE id_transaksi LIKE ? ORDER BY id_transaksi DESC LIMIT 1";
        PreparedStatement lastIdPs = com.prepareStatement(lastIdQuery);
        lastIdPs.setString(1, kodeTiket + "%");
        ResultSet rs = lastIdPs.executeQuery();
        
        int nextNumber = 1;
        if (rs.next()) {
            String lastId = rs.getString("id_transaksi");
            int lastNumber = Integer.parseInt(lastId.substring(1, 3)); // Ambil angka autoincrement
            nextNumber = lastNumber + 1;
        }
        
        String formattedIncrement = String.format("%02d", nextNumber);
        String day = Tanggal.substring(8, 10); // Ambil tanggal (2 digit terakhir dari tanggal)
        String idTransaksi = kodeTiket + formattedIncrement + day; 
        
        com.setAutoCommit(false);
        
        // Simpan transaksi
        String insertQuery = "INSERT INTO transaksi (id_transaksi, nama_pelanggan, total_harga, tanggal, uang_kembali, uang_masuk) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = com.prepareStatement(insertQuery)) {
            ps.setString(1, idTransaksi);
            ps.setString(2, Nama);
            ps.setString(3, Total);
            ps.setString(4, Tanggal);
            ps.setString(5, Kembalian);
            ps.setString(6, Tunai);
            ps.executeUpdate();
        }
        
        // Update stok
        int jumlahTiket = (int) Jumlah.getValue();
        String updateStockQuery = "UPDATE tiket SET stock = stock - ? WHERE jenis_tiket = ?";
        try (PreparedStatement updatePs = com.prepareStatement(updateStockQuery)) {
            updatePs.setInt(1, jumlahTiket);
            updatePs.setString(2, jenisTiket);
            updatePs.executeUpdate();
        }
        
        com.commit();
        System.out.println("Transaksi berhasil dengan ID: " + idTransaksi);
        setSpinnerStock();
    } catch (SQLException e) {
        e.printStackTrace();
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
        total = new javax.swing.JTextField();
        kembalian = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tunai = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Jumlah = new javax.swing.JSpinner();

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

        jLabel10.setText("Jumlah Tiket");
        panelRound1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));
        panelRound1.add(Jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 70, -1));

        add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 750, 440));
    }// </editor-fold>//GEN-END:initComponents

    private void namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaActionPerformed

    private void tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalActionPerformed
        
    }//GEN-LAST:event_tanggalActionPerformed

    private void jenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenisActionPerformed
        if (jenis.getSelectedItem().toString().equalsIgnoreCase("Silver")) {
        satuan.setText("50000");
    } else if (jenis.getSelectedItem().toString().equalsIgnoreCase("Gold")) {
        satuan.setText("100000");
    }
    }//GEN-LAST:event_jenisActionPerformed

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
  
    }//GEN-LAST:event_totalActionPerformed

    private void pesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesanActionPerformed

    }//GEN-LAST:event_pesanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner Jumlah;
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
