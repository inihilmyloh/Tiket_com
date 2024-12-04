/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.loket.form;

import com.loket.main.Main;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KSM
 */
public class CRUD_ADE extends javax.swing.JPanel implements Main.Refreshable {

    public Statement st;
    public ResultSet rs;
    Connection cn = com.loket.database.Database.getConnection();

    /**
     * Creates new form tiket
     */
    public CRUD_ADE() {
        initComponents();
        loadData();
        TampilData();
    }

    public interface Refreshable {

        void refresh();
    }

    @Override
    public void refresh() {
        TampilData(); // Panggil metode untuk menampilkan data terbaru
    }

    private void loadData() {
        // Logika untuk memuat data dari database
        System.out.println("Data CRUD_ADE diperbarui.");
    }

    private void Bersih() {
        txtID.setText("");
        txtJenis.setText("");
        txtHarga.setText("");
        txtStok.setText("");

        btnSimpan.setText("Simpan");
        txtID.setEditable(true);
    }

    private void CariData() {
        try {
            st = cn.createStatement();
            String sql = "SELECT * FROM tiket WHERE "
                    + cmbCari.getSelectedItem().toString()
                    + " LIKE '%" + txtCari.getText() + "%'";
            rs = st.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Jenis");
            model.addColumn("Harga");
            model.addColumn("Stok");

            while (rs.next()) {
                Object[] data = {
                    rs.getString("Id_tiket"),
                    rs.getString("jenis_tiket"),
                    rs.getString("harga"),
                    rs.getString("stock")
                };
                model.addRow(data);
            }

            tblData.setModel(model);

        } catch (Exception e) {
            e.printStackTrace(); // Debugging error
            JOptionPane.showMessageDialog(null, "Gagal menampilkan data: " + e.getMessage());
        }
    }

    private void TampilData() {
        try {
            st = cn.createStatement();
            // Menjalankan query untuk mendapatkan semua data dari tabel tiket
            rs = st.executeQuery("SELECT * FROM tiket");

            // Membuat model untuk tabel
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Jenis");
            model.addColumn("Harga");
            model.addColumn("Stok");

            // Membersihkan data sebelumnya
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();

            // Mengisi model dengan data dari database
            while (rs.next()) {
                Object[] data = {
                    rs.getInt("Id_tiket"), // Sesuaikan nama kolom dengan database
                    rs.getString("jenis_tiket"),
                    rs.getDouble("harga"),
                    rs.getInt("stock") // Sesuaikan nama kolom dengan database
                };
                model.addRow(data);
            }

            // Menampilkan model ke dalam JTable
            tblData.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menampilkan data: " + e.getMessage());
            e.printStackTrace(); // Debugging jika terjadi error
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cmbCari = new javax.swing.JComboBox<>();
        txtCari = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnBatal = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtStok = new javax.swing.JTextField();
        txtJenis = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();

        setBackground(new java.awt.Color(230, 240, 250));

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Cari Data");

        cmbCari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id_tiket", "jenis_tiket", "harga", "stok" }));

        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setText("PENGATURAN TIKET");

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("ID");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Jenis");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Harga");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Jumlah");

        txtID.setEditable(false);
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });

        txtStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStokActionPerformed(evt);
            }
        });

        txtJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJenisActionPerformed(evt);
            }
        });

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(131, 131, 131)
                                .addComponent(cmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        if (txtID.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Silahkan pilih data yang akan dihapus");
        } else {
            int jawab = JOptionPane.showConfirmDialog(null, "Data ini akan dihapus, lanjutkan?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

            if (jawab == 0) {
                try {
                    st = cn.createStatement();
                    String sql = "DELETE FROM tiket WHERE Id_tiket = '" + txtID.getText() + "'";
                    st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                    TampilData();
                    Bersih();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        try {
            st = cn.createStatement();
            if (txtJenis.getText().equals("")
                    || txtHarga.getText().equals("")
                    || txtStok.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Data tidak boleh kosong", "Validasi Data", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // aksi simpan dan merubah data
            if (btnSimpan.getText().equals("Simpan")) {
                // Logika aksi simpan data baru
                String sql = "INSERT INTO tiket (jenis_tiket, harga, stock) VALUES ('"
                        + txtJenis.getText() + "', '"
                        + txtHarga.getText() + "', '"
                        + txtStok.getText() + "')";
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
                Bersih();
                TampilData();
            } else {
                // Logika aksi ubah data
                String update = "UPDATE tiket SET jenis_tiket = '" + txtJenis.getText()
                        + "', harga = '" + txtHarga.getText()
                        + "', stock = '" + txtStok.getText()
                        + "' WHERE Id_tiket = '" + txtID.getText() + "'";
                st.executeUpdate(update);
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
                Bersih();
                TampilData();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        // TODO add your handling code here:
        CariData();
    }//GEN-LAST:event_txtCariKeyPressed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        Bersih();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJenisActionPerformed

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        // TODO add your handling code here:
        int row = tblData.getSelectedRow();

        if (row != -1) { // Pastikan baris yang diklik valid
            txtID.setText(tblData.getValueAt(row, 0).toString());
            txtJenis.setText(tblData.getValueAt(row, 1).toString());
            txtHarga.setText(tblData.getValueAt(row, 2).toString());
            txtStok.setText(tblData.getValueAt(row, 3).toString());

            // Set tombol simpan untuk mode edit
            btnSimpan.setText("Update");
            txtID.setEditable(false); // ID tidak bisa diubah saat update
        }
    }//GEN-LAST:event_tblDataMouseClicked

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void txtStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStokActionPerformed
    public static void main(String[] args) {
        // Membuat frame untuk menampilkan JPanel
        JFrame frame = new JFrame("CRUD ADE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500); // Atur ukuran sesuai kebutuhan

        // Menambahkan JPanel CRUD_ADE ke dalam frame
        frame.add(new CRUD_ADE());

        // Menampilkan frame
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbCari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtJenis;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}
