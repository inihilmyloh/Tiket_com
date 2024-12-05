/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.loket.form;

import com.loket.main.Main;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class laporan_diva extends JPanel implements Main.Refreshable {

    public static Connection con;
    public static Statement stm;
    public static ResultSet res;

    /**
     * Konstruktor untuk kelas laporan_diva
     */
    public laporan_diva() {
        initComponents();
        tableDiva();
        koneksimysql();
        tampiltabel();
    }

    public interface Refreshable {

        void refresh();
    }

    @Override
    public void refresh() {
        System.out.println("Refresh laporan_diva");
        tampiltabel();
    }

    public void koneksimysql() {
        try {
            String url = "jdbc:mysql://localhost:3306/loket_tiket";
            String user = "root";
            String pass = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            stm = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void tampiltabel() {
        DefaultTableModel tb = new DefaultTableModel();
        tb.addColumn("ID Transaksi");
        tb.addColumn("Nama Pelanggan");
        tb.addColumn("Jenis Tiket"); // Kolom baru untuk jenis tiket
        tb.addColumn("Total Harga");
        tb.addColumn("Tanggal");
        tb.addColumn("Uang Kembali");
        tb.addColumn("Uang Masuk");
        jTable1.setModel(tb);

        try {
            // Perbarui query untuk join tabel transaksi dan tiket
            String query = "SELECT transaksi.id_transaksi, transaksi.nama_pelanggan, tiket.jenis_tiket, "
                    + "transaksi.total_harga, transaksi.tanggal, transaksi.uang_kembali, transaksi.uang_masuk "
                    + "FROM transaksi "
                    + "JOIN tiket ON transaksi.id_tiket = tiket.id_tiket";

            res = stm.executeQuery(query);
            while (res.next()) {
                tb.addRow(new Object[]{
                    res.getString("id_transaksi"),
                    res.getString("nama_pelanggan"),
                    res.getString("jenis_tiket"),
                    res.getString("total_harga"),
                    res.getString("tanggal"),
                    res.getString("uang_kembali"),
                    res.getString("uang_masuk")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    private void tableDiva() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(230, 240, 250));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(new java.awt.Color(230, 240, 250)); // Background tabel
        jTable1.setForeground(new java.awt.Color(0, 0, 0));       // Teks tabel
        jTable1.setGridColor(new java.awt.Color(200, 200, 200));  // Warna grid

        // Header style
        jTable1.getTableHeader().setBackground(new java.awt.Color(200, 200, 255));
        jTable1.getTableHeader().setForeground(new java.awt.Color(50, 50, 50));
        jTable1.getTableHeader().setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));

        jTable1.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jTable1.setGridColor(new java.awt.Color(200, 200, 200));
        jScrollPane1.setViewportView(jTable1);
        jPanel1.setBackground(new java.awt.Color(230, 240, 250));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setText("Laporan Hasil Transaksi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Laporan Diva");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new laporan_diva());
            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(230, 240, 250));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(new java.awt.Color(230, 240, 250));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(200, 200, 200));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(230, 240, 250));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setText("Laporan Hasil Transaksi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
