
CREATE DATABASE IF NOT EXISTS fruitopia;

USE fruitopia;

CREATE TABLE IF NOT EXISTS kategori (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS produk (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(255) NOT NULL,
    deskripsi TEXT,
    harga INT NOT NULL,
    kategori_id INT,
    gambar VARCHAR(255),
    FOREIGN KEY (kategori_id) REFERENCES kategori(id)
);

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    alamat TEXT,
    phone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS pesanan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    tanggal_pesan DATE NOT NULL,
    total_harga INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS detail_pesanan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pesanan_id INT,
    produk_id INT,
    jumlah INT NOT NULL,
    harga_satuan INT NOT NULL,
    FOREIGN KEY (pesanan_id) REFERENCES pesanan(id),
    FOREIGN KEY (produk_id) REFERENCES produk(id)
);

INSERT INTO users(nama, email, password) VALUES("Super Admin", "super@admin.com", md5("superadmin"));
