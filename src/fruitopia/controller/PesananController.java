package fruitopia.controller;

import fruitopia.model.Pesanan;
import fruitopia.model.PesananDAO;
import java.util.List;

public class PesananController {
    final private PesananDAO model;

    public PesananController() {
        this.model = new PesananDAO();
    }

    public List<Pesanan> loadPesananList() {
        return model.getAllPesanan();
    }

    public void addPesanan(Pesanan pesanan) {
        model.addPesanan(pesanan);
    }

    public Pesanan getPesananById(int id) {
        return model.getPesananById(id);
    }

    public void updatePesanan(Pesanan pesanan) {
        model.updatePesanan(pesanan);
    }

    public void deletePesanan(int id) {
        model.deletePesanan(id);
    }
}