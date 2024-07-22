-- Drop tables if they exist to start fresh
DROP TABLE IF EXISTS detail_pesanan;
DROP TABLE IF EXISTS pesanan;
DROP TABLE IF EXISTS produk;
DROP TABLE IF EXISTS kategori;
DROP TABLE IF EXISTS users;

-- Create users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    alamat TEXT NOT NULL,
    phone VARCHAR(20) NOT NULL
);

-- Create kategori table
CREATE TABLE kategori (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100) NOT NULL
);

-- Create produk table
CREATE TABLE produk (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(255) NOT NULL,
    deskripsi TEXT NOT NULL,
    harga DECIMAL(10, 2) NOT NULL,
    kategori_id INT NOT NULL,
    gambar VARCHAR(255),
    FOREIGN KEY (kategori_id) REFERENCES kategori(id) ON DELETE CASCADE
);

-- Create pesanan table
CREATE TABLE pesanan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    tanggal_pesan DATE NOT NULL,
    total_harga DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create detail_pesanan table
CREATE TABLE detail_pesanan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pesanan_id INT NOT NULL,
    produk_id INT NOT NULL,
    jumlah INT NOT NULL,
    harga_satuan DECIMAL(10, 2) NOT NULL
);

-- Now, let's assume we forgot to add the foreign key constraint for produk_id in detail_pesanan
-- We correct this by altering the table and adding the constraint
ALTER TABLE detail_pesanan
ADD CONSTRAINT fk_produk_id
FOREIGN KEY (produk_id) REFERENCES produk(id) ON DELETE CASCADE;

-- Insert dummy data into kategori
INSERT INTO kategori (nama) VALUES ('Buah'), ('Sayur'), ('Daging');

-- Insert dummy data into produk
INSERT INTO produk (nama, deskripsi, harga, kategori_id, gambar) VALUES
('Apel', 'Apel merah segar', 5000, 1, 'apel.jpg'),
('Bayam', 'Bayam hijau segar', 2000, 2, 'bayam.jpg'),
('Daging Sapi', 'Daging sapi premium', 50000, 3, 'daging_sapi.jpg');

-- Insert dummy data into users
INSERT INTO users (nama, email, password, alamat, phone) VALUES
('Super Admin', 'super@admin.com', md5('superadmin'), '123 Main St, Anytown', '123-456-7890'),
('Jane Smith', 'jane.smith@example.com', md5('password123'), '456 Elm St, Anycity', '098-765-4321');

-- Insert dummy data into pesanan
INSERT INTO pesanan (user_id, tanggal_pesan, total_harga) VALUES
(1, '2023-01-01', 10000),
(2, '2023-01-02', 15000);

-- Insert dummy data into detail_pesanan
INSERT INTO detail_pesanan (pesanan_id, produk_id, jumlah, harga_satuan) VALUES
(1, 1, 2, 5000),
(1, 2, 1, 2000),
(2, 3, 1, 50000);