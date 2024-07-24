/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fruitopia.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Asani
 */
public class Pesanan {
    private int id;
    private int userId;
    private Date tanggalPesan;
    private int totalHarga;
    
    public Pesanan() {
    }

    public Pesanan(int userId, Date tanggalPesan, int totalHarga) {
        this.userId = userId;
        this.tanggalPesan = tanggalPesan;
        this.totalHarga = totalHarga;
    }
    
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getTanggalPesan() {
        return tanggalPesan;
    }

    public void setTanggalPesan(Date tanggalPesan) {
        this.tanggalPesan = tanggalPesan;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }
}
