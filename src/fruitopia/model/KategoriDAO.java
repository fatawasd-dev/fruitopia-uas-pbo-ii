/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fruitopia.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class KategoriDAO {
     private Connection connection;

    public KategoriDAO() {
        this.connection = DatabaseConfig.getConnection();
    }

    // Method untuk menambah kategori baru
    public void addCategory(Kategori category) {
        try {
            String query = "INSERT INTO kategori (nama) VALUES (?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, category.getNama());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method untuk mengambil semua kategori
    public List<Kategori> getAllCategories() {
        List<Kategori> listKategori = new ArrayList<>();
        try {
            String query = "SELECT * FROM kategori";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Kategori category = new Kategori();
                category.setId(rs.getInt("id"));
                category.setNama(rs.getString("nama"));
                listKategori.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listKategori;
    }

    // Method untuk mengambil kategori berdasarkan ID
    public Kategori getCategoryById(int id) {
        Kategori category = null;
        try {
            String query = "SELECT * FROM kategori WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                category = new Kategori();
                category.setId(rs.getInt("id"));
                category.setNama(rs.getString("nama"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    // Method untuk memperbarui kategori
    public void updateCategory(Kategori category) {
        try {
            String query = "UPDATE kategori SET nama = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, category.getNama());
            stmt.setInt(2, category.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method untuk menghapus kategori
    public void deleteCategory(int id) {
        try {
            String query = "DELETE FROM kategori WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
