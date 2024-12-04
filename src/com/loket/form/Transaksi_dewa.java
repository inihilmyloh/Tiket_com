/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.loket.form;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.formdev.flatlaf.FlatLaf;

import com.loket.main.Main.Refreshable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.sql.*;

import database.Database;
import javax.swing.SpinnerNumberModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;

import komponen.nota;

public class Transaksi_dewa extends javax.swing.JPanel implements Refreshable {

    public Transaksi_dewa() {
        initComponents();
        FlatLaf.registerCustomDefaultsSource("crazypanel");
        addListeners();
        hitungsatuan();
        setTanggalOtomatis();
        // Ambil data tiket dari database untuk menampilkan jenis tiket dan harga secara dinamis
        loadTiketData();

        // Listener untuk menyesuaikan harga saat jenis tiket dipilih
        jenis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jenis.getSelectedItem() != null) { // Pastikan tidak null
                    updateHarga();
                    hitungsatuan();
                }
            }
        });

        tunai.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                hitungKembalian(); // Hitung kembalian setiap kali tunai diubah
            }
        });
        Jumlah.setValue(0);
        satuan.setVisible(true);

        hitungKembalian(); // Hitung kembalian otomatis saat aplikasi pertama kali dijalankan

        pesan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ambil data dari field input
                String Nama = nama.getText();
                String Total = total.getText();
                String Tanggal = tanggal.getText();
                String Tunai = tunai.getText();
                String JenisTiket = jenis.getSelectedItem().toString();
                int JumlahTiket = (int) Jumlah.getValue();

                simpanTransaksi(Nama, Total, Tanggal, Tunai, JenisTiket, JumlahTiket);
            }
        });

    }

    @Override
    public void refresh() {
        // Muat ulang data dari database
        loadTiketData();
        stock1.updateStock();
        hitungsatuan();
    }

    private void loadTiketData() {
        String url = "jdbc:mysql://localhost:3306/loket_tiket";
        String suser = "root";
        String spass = "";

        try (Connection com = DriverManager.getConnection(url, suser, spass)) {
            String query = "SELECT * FROM tiket";
            PreparedStatement pstmt = com.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            // Kosongkan combo box jenis tiket
            jenis.removeAllItems();

            boolean dataDitemukan = false; // Tambahkan flag untuk data

            while (rs.next()) {
                dataDitemukan = true;
                String jenisTiket = rs.getString("jenis_tiket");
                jenis.addItem(jenisTiket); // Tambahkan jenis tiket ke combo box
            }

            if (!dataDitemukan) {
                System.out.println("Data tiket tidak ditemukan.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal memuat data tiket: " + e.getMessage());
        }
    }

    private void updateHarga() {
        String url = "jdbc:mysql://localhost:3306/loket_tiket";
        String suser = "root";
        String spass = "";

        String JenisTiket = jenis.getSelectedItem().toString();

        try (Connection com = DriverManager.getConnection(url, suser, spass)) {
            String query = "SELECT harga FROM tiket WHERE jenis_tiket = ?";
            PreparedStatement pstmt = com.prepareStatement(query);
            pstmt.setString(1, JenisTiket);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int harga = rs.getInt("harga");
                satuan.setText(String.valueOf(harga)); // Tampilkan harga di field "satuan"
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal memperbarui harga: " + e.getMessage());
        }
    }

    private void setTanggalOtomatis() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);
        System.out.println("Tanggal hari ini: " + formattedDate);
        tanggal.setText(formattedDate);
        tanggal.revalidate();
        tanggal.repaint();

    }

    private void hitungsatuan() {
        if (jenis.getSelectedItem() != null) { // Pastikan tidak null
            String selectedItem = jenis.getSelectedItem().toString(); // Item yang dipilih di ComboBox

            String url = "jdbc:mysql://localhost:3306/loket_tiket"; // Sesuaikan nama database
            String suser = "root"; // Username database
            String spass = ""; // Password database

            try (Connection com = DriverManager.getConnection(url, suser, spass)) {
                // Query untuk mendapatkan harga berdasarkan jenis tiket
                String query = "SELECT harga FROM tiket WHERE jenis_tiket = ?";
                PreparedStatement pstmt = com.prepareStatement(query);
                pstmt.setString(1, selectedItem); // Set parameter jenis tiket
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int harga = rs.getInt("harga"); // Ambil harga dari hasil query
                    satuan.setText(String.valueOf(harga)); // Set harga ke field "satuan"
                } else {
                    satuan.setText("0"); // Default jika tidak ada data
                    System.out.println("Data harga tidak ditemukan untuk jenis tiket: " + selectedItem);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Gagal mengambil data harga: " + e.getMessage());
            }
        } else {
            satuan.setText("0"); // Default jika tidak ada item dipilih
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
                String selectedItem = jenis.getSelectedItem().toString(); // Ambil jenis tiket yang dipilih
                int jumlahBarang = (int) Jumlah.getValue(); // Ambil nilai dari spinner

                // Ambil harga satuan dari database
                String url = "jdbc:mysql://localhost:3306/loket_tiket"; // Nama database
                String user = "root"; // Username database
                String password = ""; // Password database

                int hargaSatuan = 0;

                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String query = "SELECT harga FROM tiket WHERE jenis_tiket = ?";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, selectedItem); // Set parameter jenis tiket
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        hargaSatuan = rs.getInt("harga"); // Ambil harga dari hasil query
                    } else {
                        System.out.println("Data harga tidak ditemukan untuk jenis tiket: " + selectedItem);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Gagal mengambil data harga: " + e.getMessage());
                }

                // Hitung total biaya
                int totalBiaya = jumlahBarang * hargaSatuan;
                total.setText(String.valueOf(totalBiaya)); // Set total harga ke field "total"
            } else {
                total.setText("0"); // Default jika tidak ada item dipilih
            }
        } catch (Exception e) {
            total.setText("0"); // Jika ada error, tampilkan 0
            e.printStackTrace();
        }
    }

    public String generateIdTransaksi(String jenisTiket, String tanggal) {
        String idTransaksi = "";
        String url = "jdbc:mysql://localhost:3306/loket_tiket";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Tentukan angka pertama berdasarkan jenis tiket
            String angkaPertama = "";
            if ("silver".equalsIgnoreCase(jenisTiket)) {
                angkaPertama = "1"; // Silver
            } else if ("gold".equalsIgnoreCase(jenisTiket)) {
                angkaPertama = "2"; // Gold
            } else {
                angkaPertama = "3"; // Selain Silver dan Gold
            }

            // Ambil ID transaksi terakhir untuk jenis tiket dan tanggal tertentu
            String query = "SELECT id_transaksi FROM transaksi WHERE id_transaksi LIKE ? ORDER BY id_transaksi DESC LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, angkaPertama + "%" + tanggal); // Format pencarian: <JenisTiket>%<Tanggal>

            ResultSet rs = stmt.executeQuery();
            int autoIncrement = 1; // Default jika tidak ada data sebelumnya

            if (rs.next()) {
                String lastId = rs.getString("id_transaksi");
                // Pecah ID terakhir, contoh: "10223" -> ["1", "02", "23"]
                String autoIncrementPart = lastId.substring(1, 3); // Ambil 2 digit auto-increment
                autoIncrement = Integer.parseInt(autoIncrementPart) + 1; // Tambahkan 1 ke nilai terakhir
            }

            // Format auto-increment menjadi 2 digit
            String autoIncrementStr = String.format("%02d", autoIncrement);

            // Gabungkan menjadi ID transaksi
            idTransaksi = angkaPertama + autoIncrementStr + tanggal;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idTransaksi;
    }

    private void hitungKembalian() {
        try {
            if (tunai.getText().isEmpty() || total.getText().isEmpty()) {
                kembalian.setText("0");
                return;
            }

            int tunaiPembeli = Integer.parseInt(tunai.getText());
            int totalBiaya = Integer.parseInt(total.getText());

            // Debug output
            System.out.println("Tunai Pembeli: " + tunaiPembeli);
            System.out.println("Total Biaya: " + totalBiaya);

            if (tunaiPembeli < totalBiaya) {
                kembalian.setText("0");
            } else {
                int kembalianPembeli = tunaiPembeli - totalBiaya;
                kembalian.setText(String.valueOf(kembalianPembeli));

                // Debug output
                System.out.println("Kembalian: " + kembalianPembeli);
            }
        } catch (NumberFormatException e) {

        }
    }

    private boolean simpanTransaksi(String Nama, String Total, String Tanggal, String Tunai, String JenisTiket, int JumlahTiket) {
        boolean berhasil = false;
        if (Nama.isEmpty() || Total.isEmpty() || Tanggal.isEmpty() || Tunai.isEmpty() || JenisTiket.isEmpty() || JumlahTiket <= 0) {
            System.out.println("Semua field harus diisi dengan benar.");
            return false;
        }
        try {
            // Simpan transaksi ke database
            // (sesuaikan dengan implementasi penyimpanan Anda)
            berhasil = true; // Ubah ini berdasarkan logika simpan Anda
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (berhasil) {
            tampilkanNota(Nama, Total, Tanggal, Tunai, JenisTiket, JenisTiket);
        }

        String url = "jdbc:mysql://localhost:3306/loket_tiket";
        String suser = "root";
        String spass = "";

        try (Connection com = DriverManager.getConnection(url, suser, spass)) {
            // Cek keberadaan jenis tiket dan ambil harga serta stok
            String hargaQuery = "SELECT harga, stock FROM tiket WHERE jenis_tiket = ?";
            PreparedStatement hargaStmt = com.prepareStatement(hargaQuery);
            hargaStmt.setString(1, JenisTiket);
            ResultSet hargaRs = hargaStmt.executeQuery();

            if (!hargaRs.next()) {
                System.out.println("Jenis tiket tidak ditemukan di tabel tiket.");
                return false;
            }

            int harga = hargaRs.getInt("harga");
            int stok = hargaRs.getInt("stock");

            if (JumlahTiket > stok) {
                System.out.println("Stok tiket tidak mencukupi.");
                return false;
            }

            int totalHarga = harga * JumlahTiket;
            int tunaiPembeli = Integer.parseInt(Tunai);

            if (tunaiPembeli < totalHarga) {
                System.out.println("Tunai yang dimasukkan tidak cukup.");
                return false;
            }

            int kembalian = tunaiPembeli - totalHarga;

            // Generate ID transaksi
            String idTransaksi = generateIdTransaksi(JenisTiket, Tanggal.substring(8, 10)); // Ambil 2 digit terakhir tanggal

            // Masukkan data transaksi ke tabel transaksi
            String transaksiQuery = "INSERT INTO transaksi (id_transaksi, nama_pelanggan, total_harga, tanggal, uang_masuk, uang_kembali) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement transaksiStmt = com.prepareStatement(transaksiQuery);
            transaksiStmt.setString(1, idTransaksi); // Masukkan ID transaksi manual
            transaksiStmt.setString(2, Nama);
            transaksiStmt.setInt(3, totalHarga);
            transaksiStmt.setString(4, Tanggal);
            transaksiStmt.setInt(5, tunaiPembeli);
            transaksiStmt.setInt(6, kembalian);
            transaksiStmt.executeUpdate();

            // Perbarui stok tiket
            String updateStokQuery = "UPDATE tiket SET stock = stock - ? WHERE jenis_tiket = ?";
            PreparedStatement updateStokStmt = com.prepareStatement(updateStokQuery);
            updateStokStmt.setInt(1, JumlahTiket);
            updateStokStmt.setString(2, JenisTiket);
            updateStokStmt.executeUpdate();

            System.out.println("Transaksi berhasil disimpan dengan ID: " + idTransaksi);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal menyimpan transaksi: " + e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Format angka tidak valid: " + e.getMessage());
            return false;
        }
    }

    private void tampilkanNota(String Nama, String Total, String Tanggal, String Tunai, String JenisTiket, String JumlahTiket) {
        // Buat panel nota baru
        nota notaPanel = new nota();
        notaPanel.setNotaData(Nama, Tanggal, JenisTiket, JumlahTiket, Tanggal, Total, Tunai, Tunai);

        // Tampilkan di JFrame baru
        JFrame frame = new JFrame("Nota");
        frame.setContentPane(notaPanel);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.dispose();
        super.paintComponent(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new komponen.PanelRound();
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
        jLabel11 = new javax.swing.JLabel();
        stock1 = new com.loket.komponen.stock();

        setBackground(new java.awt.Color(230, 240, 250));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(230, 240, 250));
        panelRound1.setRoundBottomLeft(16);
        panelRound1.setRoundBottomRight(16);
        panelRound1.setRoundTopLeft(16);
        panelRound1.setRoundTopRight(16);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Stock Tiket");
        panelRound1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, -1, -1));

        satuan.setEditable(false);
        panelRound1.add(satuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 210, 40));

        jenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenisActionPerformed(evt);
            }
        });
        panelRound1.add(jenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 80, 30));

        tanggal.setEditable(false);
        tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalActionPerformed(evt);
            }
        });
        panelRound1.add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 210, 40));

        nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaActionPerformed(evt);
            }
        });
        panelRound1.add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 330, 40));

        pesan.setBackground(new java.awt.Color(102, 0, 255));
        pesan.setForeground(new java.awt.Color(255, 255, 255));
        pesan.setText("Pesan");
        pesan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesanActionPerformed(evt);
            }
        });
        panelRound1.add(pesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 290, 50));

        jLabel3.setText("Tanggal Pembelian");
        panelRound1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jLabel4.setText("Nama pembeli");
        panelRound1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel5.setText("Jenis Tiket");
        panelRound1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel6.setText("Satuan Harga");
        panelRound1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, -1));

        jLabel7.setText("Kembali");
        panelRound1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, -1, -1));

        total.setEditable(false);
        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });
        panelRound1.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 250, 40));

        kembalian.setEditable(false);
        panelRound1.add(kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 200, 40));

        jLabel8.setText("Total Harga");
        panelRound1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));
        panelRound1.add(tunai, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 190, 40));

        jLabel9.setText("Tunai");
        panelRound1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel10.setText("Jumlah Tiket");
        panelRound1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));
        panelRound1.add(Jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 80, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("TRANSAKSI PEMBELIAN");
        panelRound1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));
        panelRound1.add(stock1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 410, -1));

        add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 570));
    }// </editor-fold>//GEN-END:initComponents

    private void namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaActionPerformed

    private void tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalActionPerformed

    }//GEN-LAST:event_tanggalActionPerformed

    private void jenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenisActionPerformed


    }//GEN-LAST:event_jenisActionPerformed

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed

    }//GEN-LAST:event_totalActionPerformed

    private void pesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesanActionPerformed


    }//GEN-LAST:event_pesanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner Jumlah;
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
    private javax.swing.JComboBox<String> jenis;
    private javax.swing.JTextField kembalian;
    private javax.swing.JTextField nama;
    private komponen.PanelRound panelRound1;
    private komponen.Button pesan;
    private javax.swing.JTextField satuan;
    private com.loket.komponen.stock stock1;
    private javax.swing.JTextField tanggal;
    private javax.swing.JTextField total;
    private javax.swing.JTextField tunai;
    // End of variables declaration//GEN-END:variables
}
