package com.loket.service;

import com.loket.database.Database;
import com.loket.model.m_Card;
import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Locale;

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
                int harga = resultSet.getInt("harga");
                int stock = resultSet.getInt("stock");
                NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("id", "ID"));
                String formattedHarga = "Rp " + formatter.format(harga);
                // Buat string untuk deskripsi
                String deskripsi = "Harga: " + formattedHarga;
                // Stock sebagai value
                String values = String.valueOf(stock);
                // Buat m_Card
                card = new m_Card(new ImageIcon(DataFetcher.class.getResource(iconPath)), title, values, deskripsi);
            } else {
                // Tambahkan log jika tidak ada data
                System.out.println("Data tidak ditemukan untuk jenis tiket: " + jenisTiket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Jika data null, kembalikan objek default (opsional)
        if (card == null) {
            card = new m_Card(
                    new ImageIcon(DataFetcher.class.getResource(iconPath)),
                    title,
                    "0",
                    "Data tidak ditemukan"
            );
        }
        return card;
    }

    public static m_Card getPendapatan(String iconPath, String title) {
        m_Card card = null;
        try (Connection connection = Database.getConnection()) {
            // Query untuk menghitung total uang masuk dari tabel transaksi
            String query = "SELECT SUM(total_harga) AS total_pendapatan FROM transaksi";
            PreparedStatement statement = connection.prepareStatement(query);

            // Eksekusi query
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Ambil total pendapatan
                int totalPendapatan = resultSet.getInt("total_pendapatan");

                // Format angka dengan Locale Indonesia
                NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("id", "ID"));
                String formattedPendapatan = "Rp " + formatter.format(totalPendapatan);

                // Buat deskripsi
                String deskripsi = "Total Pendapatan yang diterima";

                // Buat m_Card
                card = new m_Card(new ImageIcon(DataFetcher.class.getResource(iconPath)), title, formattedPendapatan, deskripsi);
            } else {
                // Tambahkan log jika tidak ada data
                System.out.println("Data tidak ditemukan untuk pendapatan: ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        // Jika data null, kembalikan objek default (opsional)
        if (card == null) {
            card = new m_Card(
                    new ImageIcon(DataFetcher.class.getResource(iconPath)),
                    title,
                    "0",
                    "Data tidak ditemukan"
            );
        }
        return card;
    }

}
