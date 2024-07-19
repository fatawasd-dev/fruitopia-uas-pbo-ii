/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fruitopia.model;

import java.math.BigDecimal;

public class DetailPesanan {
    private int id;
    private int pesananId;
    private int produkId;
    private int jumlah;
    private BigDecimal hargaSatuan;

    public DetailPesanan() {
    }

    public DetailPesanan(int id, int pesananId, int produkId, int jumlah, BigDecimal hargaSatuan) {
        this.id = id;
        this.pesananId = pesananId;
        this.produkId = produkId;
        this.jumlah = jumlah;
        this.hargaSatuan = hargaSatuan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPesananId() {
        return pesananId;
    }

    public void setPesananId(int pesananId) {
        this.pesananId = pesananId;
    }

    public int getProdukId() {
        return produkId;
    }

    public void setProdukId(int produkId) {
        this.produkId = produkId;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public BigDecimal getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(BigDecimal hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }
}
