package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {
    private static Connection conn;
    
    public static Connection getKoneksi() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/db_inventori";
                String user = "root"; // Sesuaikan user MySQL Anda
                String pass = "";     // Sesuaikan password MySQL Anda
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                conn = DriverManager.getConnection(url, user, pass);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Koneksi ke Database Gagal: " + e.getMessage());
            }
        }
        return conn;
    }
}
