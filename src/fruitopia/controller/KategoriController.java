/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fruitopia.controller;

import fruitopia.model.Kategori;
import fruitopia.model.KategoriDAO;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class KategoriController {
    final private KategoriDAO model;

    public KategoriController() {
        this.model = new KategoriDAO();
    }

    // Method untuk memuat daftar kategori
    public List<Kategori> loadCategoryList() {
        List<Kategori> categoryList = model.getAllCategories();
        return categoryList;
    }

    // Method untuk menambahkan kategori
    public void addCategory(String nama) {
        Kategori category = new Kategori();
        category.setNama(nama);

        model.addCategory(category);
    }

    // Method untuk memperbarui kategori
    public void updateCategory(int id, String nama) {
        Kategori category = model.getCategoryById(id);
        if (category != null) {
            category.setNama(nama);

            model.updateCategory(category);
        } else {
            // Handle jika kategori tidak ditemukan
            System.out.println("Kategori dengan ID " + id + " tidak ditemukan.");
        }
    }

    // Method untuk menghapus kategori
    public void deleteCategory(int id) {
        Kategori category = model.getCategoryById(id);
        if (category != null) {
            model.deleteCategory(id);
        } else {
            // Handle jika kategori tidak ditemukan
            System.out.println("Kategori dengan ID " + id + " tidak ditemukan.");
        }
    }

    // Method untuk mendapatkan kategori berdasarkan ID
    public Kategori getCategory(int id) {
        return model.getCategoryById(id);
    }
}
