package com.loket.service;

import com.loket.database.Database;
import com.loket.model.m_Card;
import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataFetcher {

    public static m_Card getCardData(String jenisTiket, String iconPath, String title) {
        m_Card card = null;
        try (Connection connection = Database.getConnection()) {
            // Query ke tabel tiket
            String query = "SELECT harga, stock FROM tiket WHERE jenis_tiket = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, jenisTiket);

            // Eksekusi query
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Ambil data dari database
                String values = String.valueOf(resultSet.getInt("stock")); // Stock sebagai value
                String deskripsi = "Harga: Rp" + resultSet.getInt("harga"); // Harga sebagai deskripsi

                // Buat m_Card
                card = new m_Card(new ImageIcon(DataFetcher.class.getResource(iconPath)), title, values, deskripsi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return card;
    }
    public static m_Card getPendapatan(String iconPath, String title) {
        m_Card card = null;
        try (Connection connection = Database.getConnection()) {
            // Query ke tabel transaksi untuk menjumlahkan uang_masuk
            String query = "SELECT SUM(uang_masuk) AS total_pendapatan FROM transaksi";
            PreparedStatement statement = connection.prepareStatement(query);

            // Eksekusi query
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Ambil data total pendapatan
                String values = "Rp" + resultSet.getInt("total_pendapatan"); // Total Pendapatan sebagai value
                String deskripsi = "Increased by 70%"; // Contoh deskripsi

                // Buat m_Card
                card = new m_Card(new ImageIcon(DataFetcher.class.getResource(iconPath)), title, values, deskripsi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return card;
    }

}