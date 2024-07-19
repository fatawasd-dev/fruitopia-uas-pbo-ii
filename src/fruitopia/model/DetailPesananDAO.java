/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fruitopia.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailPesananDAO {
    private Connection connection;

    public DetailPesananDAO() {
        this.connection = DatabaseConfig.getConnection();
    }

    public void addDetailPesanan(DetailPesanan detailPesanan) {
        try {
            String query = "INSERT INTO detail_pesanan (pesanan_id, produk_id, jumlah, harga_satuan) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, detailPesanan.getPesananId());
            stmt.setInt(2, detailPesanan.getProdukId());
            stmt.setInt(3, detailPesanan.getJumlah());
            stmt.setBigDecimal(4, detailPesanan.getHargaSatuan());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DetailPesanan> getAllDetailPesanan() {
        List<DetailPesanan> listDetailPesanan = new ArrayList<>();
        try {
            String query = "SELECT * FROM detail_pesanan";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                DetailPesanan detailPesanan = new DetailPesanan();
                detailPesanan.setId(rs.getInt("id"));
                detailPesanan.setPesananId(rs.getInt("pesanan_id"));
                detailPesanan.setProdukId(rs.getInt("produk_id"));
                detailPesanan.setJumlah(rs.getInt("jumlah"));
                detailPesanan.setHargaSatuan(rs.getBigDecimal("harga_satuan"));
                listDetailPesanan.add(detailPesanan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDetailPesanan;
    }

    public DetailPesanan getDetailPesananById(int id) {
        DetailPesanan detailPesanan = null;
        try {
            String query = "SELECT * FROM detail_pesanan WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                detailPesanan = new DetailPesanan();
                detailPesanan.setId(rs.getInt("id"));
                detailPesanan.setPesananId(rs.getInt("pesanan_id"));
                detailPesanan.setProdukId(rs.getInt("produk_id"));
                detailPesanan.setJumlah(rs.getInt("jumlah"));
                detailPesanan.setHargaSatuan(rs.getBigDecimal("harga_satuan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detailPesanan;
    }

    public List<DetailPesanan> getDetailPesananByPesananId(int pesananId) {
        List<DetailPesanan> listDetailPesanan = new ArrayList<>();
        try {
            String query = "SELECT * FROM detail_pesanan WHERE pesanan_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, pesananId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DetailPesanan detailPesanan = new DetailPesanan();
                detailPesanan.setId(rs.getInt("id"));
                detailPesanan.setPesananId(rs.getInt("pesanan_id"));
                detailPesanan.setProdukId(rs.getInt("produk_id"));
                detailPesanan.setJumlah(rs.getInt("jumlah"));
                detailPesanan.setHargaSatuan(rs.getBigDecimal("harga_satuan"));
                listDetailPesanan.add(detailPesanan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDetailPesanan;
    }

    public void updateDetailPesanan(DetailPesanan detailPesanan) {
        try {
            String query = "UPDATE detail_pesanan SET pesanan_id = ?, produk_id = ?, jumlah = ?, harga_satuan = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, detailPesanan.getPesananId());
            stmt.setInt(2, detailPesanan.getProdukId());
            stmt.setInt(3, detailPesanan.getJumlah());
            stmt.setBigDecimal(4, detailPesanan.getHargaSatuan());
            stmt.setInt(5, detailPesanan.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDetailPesanan(int id) {
        try {
            String query = "DELETE FROM detail_pesanan WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
