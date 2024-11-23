package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaksi_dewa {
 public static Connection com;
    public static Statement stm;
    public static ResultSet rs;
    public static String sql;

    public Transaksi_dewa() {
        try {
            String url = "jdbc:mysql://localhost:3306/loket_tiket";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            com = DriverManager.getConnection(url, user, pass);
            stm = com.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Koneksi gagal " + e.getMessage());
        }
    }

}
