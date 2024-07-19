/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fruitopia.model;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asani
 */
public class PesananDAO {
    private Connection connection;
    
    public PesananDAO() {
        try {
            this.connection = DatabaseConfig.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addPesanan(Pesanan pesanan) {
        try {
            String query = "INSERT INTO pesanan (user_id, tanggal_pesan, total_harga) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, pesanan.getUserId());
            stmt.setDate(2, pesanan.getTanggalPesan());
            stmt.setBigDecimal(3, pesanan.getTotalHarga());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pesanan> getAllPesanan() {
        List<Pesanan> listPesanan = new ArrayList<>();
        try {
            String query = "SELECT * FROM pesanan";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Pesanan pesanan = new Pesanan();
                pesanan.setId(rs.getInt("id"));
                pesanan.setUserId(rs.getInt("user_id"));
                pesanan.setTanggalPesan(rs.getDate("tanggal_pesan"));
                pesanan.setTotalHarga(rs.getBigDecimal("total_harga"));
                listPesanan.add(pesanan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPesanan;
    }

    public Pesanan getPesananById(int id) {
        Pesanan pesanan = null;
        try {
            String query = "SELECT * FROM pesanan WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pesanan = new Pesanan();
                pesanan.setId(rs.getInt("id"));
                pesanan.setUserId(rs.getInt("user_id"));
                pesanan.setTanggalPesan(rs.getDate("tanggal_pesan"));
                pesanan.setTotalHarga(rs.getBigDecimal("total_harga"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pesanan;
    }

    public void updatePesanan(Pesanan pesanan) {
        try {
            String query = "UPDATE pesanan SET user_id = ?, tanggal_pesan = ?, total_harga = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, pesanan.getUserId());
            stmt.setDate(2, pesanan.getTanggalPesan());
            stmt.setBigDecimal(3, pesanan.getTotalHarga());
            stmt.setInt(4, pesanan.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePesanan(int id) {
        try {
            String query = "DELETE FROM pesanan WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
