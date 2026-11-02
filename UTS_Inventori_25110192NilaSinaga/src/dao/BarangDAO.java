package dao;

import model.Barang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarangDAO {
    private Connection conn;

    public BarangDAO() {
        conn = Koneksi.getKoneksi();
    }

    // Tampilkan / Read All Data
    public List<Barang> getAll() {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Barang b = new Barang(
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("kategori"),
                    rs.getInt("stok")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Simpan / Insert
    public boolean insert(Barang b) throws SQLException {
        String sql = "INSERT INTO barang VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, b.getKodeBarang());
        ps.setString(2, b.getNamaBarang());
        ps.setString(3, b.getKategori());
        ps.setInt(4, b.getStok());
        return ps.executeUpdate() > 0;
    }

    // Ubah / Update
    public boolean update(Barang b) throws SQLException {
        String sql = "UPDATE barang SET nama_barang=?, kategori=?, stok=? WHERE kode_barang=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, b.getNamaBarang());
        ps.setString(2, b.getKategori());
        ps.setInt(3, b.getStok());
        ps.setString(4, b.getKodeBarang());
        return ps.executeUpdate() > 0;
    }

    // Hapus / Delete
    public boolean delete(String kodeBarang) throws SQLException {
        String sql = "DELETE FROM barang WHERE kode_barang=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, kodeBarang);
        return ps.executeUpdate() > 0;
    }
}
