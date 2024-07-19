/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fruitopia.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdukDAO {
    private Connection connection;

    public ProdukDAO() {
        this.connection = DatabaseConfig.getConnection();
    }

    public void addProduct(Produk product) {
        try {
            String query = "INSERT INTO produk (nama, deskripsi, harga, kategori_id, gambar) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, product.getNama());
            stmt.setString(2, product.getDeskripsi());
            stmt.setBigDecimal(3, product.getHarga());
            stmt.setInt(4, product.getKategoriId());
            stmt.setString(5, product.getGambar());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produk> getAllProduct() {
        List<Produk> listProduk = new ArrayList<>();
        try {
            String query = "SELECT p.*, k.nama AS kategori FROM produk p LEFT JOIN kategori k ON p.kategori_id = k.id";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Produk product = new Produk();
                product.setId(rs.getInt("id"));
                product.setNama(rs.getString("nama"));
                product.setDeskripsi(rs.getString("deskripsi"));
                product.setHarga(rs.getBigDecimal("harga"));
                product.setKategoriId(rs.getInt("kategori_id"));
                product.setGambar(rs.getString("gambar"));
                product.setKategori(rs.getString("kategori"));
                listProduk.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProduk;
    }

    public Produk getProductById(int id) {
        Produk product = null;
        try {
            String query = "SELECT p.*, k.nama AS kategori FROM produk p LEFT JOIN kategori k ON p.kategori_id = k.id WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = new Produk();
                product.setId(rs.getInt("id"));
                product.setNama(rs.getString("nama"));
                product.setDeskripsi(rs.getString("deskripsi"));
                product.setHarga(rs.getBigDecimal("harga"));
                product.setKategoriId(rs.getInt("kategori_id"));
                product.setGambar(rs.getString("gambar"));
                product.setKategori(rs.getString("kategori"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void updateProduct(Produk produk) {
        try {
            String query = "UPDATE produk SET nama = ?, deskripsi = ?, harga = ?, kategori_id = ?, gambar = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, produk.getNama());
            stmt.setString(2, produk.getDeskripsi());
            stmt.setBigDecimal(3, produk.getHarga());
            stmt.setInt(4, produk.getKategoriId());
            stmt.setString(5, produk.getGambar());
            stmt.setInt(6, produk.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        try {
            String query = "DELETE FROM produk WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // TODO: remove when kategori dao is exist
    public List<Kategori> getCategories() {
        List<Kategori> listCategoy = new ArrayList<>();
        try {
            String query = "SELECT * FROM kategori";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Kategori category = new Kategori();
                category.setId(rs.getInt("id"));
                category.setNama(rs.getString("nama"));
                listCategoy.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCategoy;
    }
}
