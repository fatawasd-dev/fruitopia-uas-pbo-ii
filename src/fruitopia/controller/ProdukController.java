/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fruitopia.controller;

import fruitopia.model.Kategori;
import fruitopia.model.Produk;
import fruitopia.model.ProdukDAO;
import fruitopia.view.ProdukView;
import java.util.List;
import javax.swing.JOptionPane;
import java.math.BigDecimal;
/**
 *
 * @author Asani
 */
public class ProdukController {
    final private ProdukDAO model;
    
    public ProdukController() {
        this.model = new ProdukDAO();
    }
    
    public List<Produk> loadProductList() {
        List<Produk> productList = model.getAllProduct();
        return productList;
    }
    
    public void addProduct(String nama, String deskripsi, BigDecimal harga, int kategoriId, String gambar) {
        Produk produk = new Produk();
        produk.setNama(nama);
        produk.setDeskripsi(deskripsi);
        produk.setHarga(harga);
        produk.setKategoriId(kategoriId);
        produk.setGambar(gambar);

        model.addProduct(produk);
    }
    
        public void updateProduct(int id, String nama, String deskripsi, BigDecimal harga, int kategoriId, String gambar) {
        Produk produk = model.getProductById(id);
        if (produk != null) {
            produk.setNama(nama);
            produk.setDeskripsi(deskripsi);
            produk.setHarga(harga);
            produk.setKategoriId(kategoriId);
            produk.setGambar(gambar);

            model.updateProduct(produk);
        } else {
        }
    }
        
    public void deleteProduk(int id) {
        Produk produk = model.getProductById(id);
        if (produk != null) {
            model.deleteProduct(id);
            loadProductList();
        } else {
        }
    }
    
    public List<Kategori> loadCategoryList() {
        List<Kategori> categories = model.getCategories();
        return categories;
    }
}
