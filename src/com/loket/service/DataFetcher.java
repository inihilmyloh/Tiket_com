package com.loket.service;

import com.loket.database.Database;
import com.loket.model.m_Card;
import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

public class DataFetcher {

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

    public static m_Card getJenisTiket(String iconPath, String title) {
        String description = "";
        int totalJenis = 0;

        String query = "SELECT jenis_tiket, stock FROM tiket";

        try (Connection conn = Database.getConnection(); java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            StringBuilder descBuilder = new StringBuilder();
            while (rs.next()) {
                String jenis = rs.getString("jenis_tiket");
                int stok = rs.getInt("stock");
                descBuilder.append(jenis).append(": ").append(stok).append("\n");

                totalJenis++;
            }

            if (descBuilder.length() > 0) {
                description = descBuilder.toString().trim(); // Hilangkan whitespace atau newline tambahan
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Description: " + description);
        // Kembalikan m_Card
        return new m_Card(
                new ImageIcon(DataFetcher.class.getResource(iconPath)),
                title,
                String.valueOf(totalJenis), // Konversi total jenis tiket ke String
                description
        );
    }

    public static m_Card getTotalStockAndPrices(String iconPath, String title) {
        String description = "";
        int totalStock = 0;

        String query = "SELECT SUM(stock) AS total_stok, GROUP_CONCAT(jenis_tiket, ': Rp ', harga SEPARATOR '\n') AS harga_detail FROM tiket";

        try (Connection conn = Database.getConnection(); java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                totalStock = rs.getInt("total_stok");
                description = rs.getString("harga_detail");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Kembalikan m_Card
        return new m_Card(
                new ImageIcon(DataFetcher.class.getResource(iconPath)),
                title,
                String.valueOf(totalStock), // Konversi total stok ke String
                description
        );
    }

    public static m_Card getStock1(String iconPath, String title) {
        m_Card card = null; // Inisialisasi card sebagai null untuk null safety
        String description = "";
        int totalJenis = 0;

        // Query untuk mengambil semua jenis tiket dan stoknya
        String query = "SELECT jenis_tiket, stock FROM tiket";

        try (Connection conn = Database.getConnection(); java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            StringBuilder descBuilder = new StringBuilder();

            while (rs.next()) {
                // Ambil data dari database
                String jenis = rs.getString("jenis_tiket");
                int stok = rs.getInt("stock");
                descBuilder.append(jenis).append(": ").append(stok).append("\n");
                totalJenis++;
            }

            if (descBuilder.length() > 0) {
                description = descBuilder.toString().trim(); // Trim whitespace
            } else {
                // Log jika data tidak ditemukan
                System.out.println("Data tidak ditemukan dalam tabel tiket.");
            }

            // Buat m_Card berdasarkan data
            card = new m_Card(
                    new ImageIcon(DataFetcher.class.getResource(iconPath)), // Ikon
                    title, // Judul
                    String.valueOf(totalJenis), // Total jenis tiket
                    description // Deskripsi detail
            );

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // Tangani error umum lainnya
            e.printStackTrace();
        }

        // Jika data null, kembalikan objek default (opsional untuk null safety)
        if (card == null) {
            card = new m_Card(
                    new ImageIcon(DataFetcher.class.getResource(iconPath)), // Ikon default
                    title, // Judul
                    "0", // Total stok default
                    "Data tidak ditemukan" // Deskripsi default
            );
        }

        return card;
    }

    public static m_Card getStock2(String iconPath, String title) {
        m_Card card = null; // Inisialisasi null untuk null safety
        try (Connection connection = Database.getConnection()) {
            // Query untuk mengambil total stok dan detail harga
            String query = "SELECT SUM(stock) AS total_stok, GROUP_CONCAT(jenis_tiket, ': Rp ', harga SEPARATOR '\n') AS harga_detail FROM tiket";
            PreparedStatement statement = connection.prepareStatement(query);

            // Eksekusi query
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Ambil data total stok dan detail harga
                int totalStock = resultSet.getInt("total_stok");
                String hargaDetail = resultSet.getString("harga_detail");

                // Format deskripsi
                String deskripsi = (hargaDetail != null && !hargaDetail.isEmpty()) ? hargaDetail : "Detail harga tidak tersedia";

                // Konversi total stok ke string
                String values = String.valueOf(totalStock);

                // Buat objek m_Card
                card = new m_Card(
                        new ImageIcon(DataFetcher.class.getResource(iconPath)), // Ikon
                        title, // Judul
                        values, // Nilai total stok
                        deskripsi // Deskripsi harga
                );
            } else {
                // Log jika data tidak ditemukan
                System.out.println("Data tidak ditemukan dalam tabel tiket.");
            }
        } catch (SQLException e) {
            // Tangani error SQL
            e.printStackTrace();
        } catch (Exception e) {
            // Tangani error umum lainnya
            e.printStackTrace();
        }

        // Jika data null, kembalikan objek default (opsional untuk null safety)
        if (card == null) {
            card = new m_Card(
                    new ImageIcon(DataFetcher.class.getResource(iconPath)), // Ikon default
                    title, // Judul
                    "0", // Total stok default
                    "Data tidak ditemukan" // Deskripsi default
            );
        }

        return card; // Kembalikan objek m_Card
    }
}
