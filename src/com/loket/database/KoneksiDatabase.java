/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loket.database;

/**
 *
 * @author KSM
 */
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class KoneksiDatabase {

    public static Connection BukaKoneksi() {
        try {
            // Memuat driver JDBC terbaru
            Class.forName("com.mysql.cj.jdbc.Driver");

            // URL koneksi ke database
            String url = "jdbc:mysql://localhost:3306/loket_tiket";
            String user = "root"; // Sesuaikan dengan username MySQL Anda
            String password = ""; // Sesuaikan dengan password MySQL Anda

            // Membuka koneksi ke database
            Connection cn = DriverManager.getConnection(url, user, password);
            return cn;

        } catch (Exception e) {
            // Menampilkan pesan kesalahan jika koneksi gagal
            JOptionPane.showMessageDialog(null, "Koneksi gagal: " + e.getMessage());
            return null;
        }
    }
}
