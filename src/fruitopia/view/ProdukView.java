/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fruitopia.view;

import fruitopia.controller.ProdukController;
import fruitopia.model.Kategori;
import fruitopia.model.Produk;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.File;
import java.nio.file.Files;

/**
 *
 * @author Asani
 */

class ComboBoxItem {
    private int id;
    private String nama;
    
    public ComboBoxItem(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }
    
    @Override
    public String toString() {
        return nama;
    }
    
    public int getId() {
        return id;
    }
}
public class ProdukView extends javax.swing.JFrame {
    final private ProdukController controller;
    private List<Kategori> categories;
    private int productIdSelected = 0;
    /**
     * Creates new form ProdukView
     */
    public ProdukView() {
        this.controller = new ProdukController();
        initComponents();
        this.setLocationRelativeTo(null);
        loadCategoriesData();
        loadData();
        txtImage.setEnabled(false);
        
        productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && productTable.getSelectedRow() != -1) {
                    System.out.println("clicked");
                    int id = Integer.parseInt(productTable.getValueAt(productTable.getSelectedRow(), 0).toString());
                    String productName = productTable.getValueAt(productTable.getSelectedRow(), 1).toString();
                    String productDescription = productTable.getValueAt(productTable.getSelectedRow(), 2).toString();
                    String productPrice = productTable.getValueAt(productTable.getSelectedRow(), 3).toString();
                    String productCategory = productTable.getValueAt(productTable.getSelectedRow(), 4).toString();
                    String productImage = productTable.getValueAt(productTable.getSelectedRow(), 5).toString();

                    productIdSelected = id;
                    txtProductName.setText(productName);
                    txtPrice.setText(productPrice);
                    txtImage.setText(productImage);
                    txtDescription.setText(productDescription);
                    
                    ComboBoxItem selectedCategory = findCategoryByName(productCategory);
                    if (selectedCategory != null) {
                        cmbKategori.setSelectedItem(selectedCategory);
                    }
                }
            }
        });
    }
    
    private ComboBoxItem findCategoryByName(String categoryName) {
        for (int i = 0; i < cmbKategori.getItemCount(); i++) {
            ComboBoxItem item = cmbKategori.getItemAt(i);
            if (item.toString().equals(categoryName)) {
                return item;
            }
        }
        return null;
    }
    
    public void reset() {
        txtProductName.setText("");
        txtPrice.setText("");
        txtDescription.setText("");
        txtImage.setText("");
        productIdSelected = 0;
    }
    
    public void displayProductList(List<Produk> productList) {
        String[] columnNames = {"ID", "Nama", "Deskripsi", "Harga", "Kategori", "Gambar"};
        Object[][] data = new Object[productList.size()][6];
        for (int i = 0; i < productList.size(); i++) {
            Produk product = productList.get(i);
            data[i][0] = product.getId();
            data[i][1] = product.getNama();
            data[i][2] = product.getDeskripsi();
            data[i][3] = product.getHarga();
            data[i][4] = product.getKategori();
            data[i][5] = product.getGambar();
        }

        productTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
    
    final public void loadData() {
        List<Produk> products = controller.loadProductList();
        displayProductList(products);
    }
    
    final void loadCategoriesData() {
        List<Kategori> categoryList = controller.loadCategoryList();
        for (int i = 0; i < categoryList.size(); i++) {
            Kategori category = categoryList.get(i);
            cmbKategori.addItem(new ComboBoxItem(category.getId(), category.getNama()));
        }
        this.categories = categoryList;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        txtImage = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbKategori = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnSave1 = new javax.swing.JButton();
        btnSave2 = new javax.swing.JButton();
        btnSave3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        kategoriMenu = new javax.swing.JMenu();
        pesananMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(productTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 308, 670, 326));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Management Produk");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, -1, -1));

        jLabel2.setText("Nama");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel3.setText("Deskripsi");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));
        getContentPane().add(txtImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 234, -1));

        jLabel4.setText("Gambar");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, -1, -1));
        getContentPane().add(txtProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 234, -1));

        jLabel5.setText("Kategori");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        getContentPane().add(cmbKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 234, -1));

        btnSave.setText("Reset");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 270, -1, -1));

        btnSave1.setText("Update");
        btnSave1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSave1MouseClicked(evt);
            }
        });
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, -1, -1));

        btnSave2.setText("Delete");
        btnSave2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSave2MouseClicked(evt);
            }
        });
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, -1, -1));

        btnSave3.setText("Simpan");
        btnSave3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSave3MouseClicked(evt);
            }
        });
        btnSave3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave3ActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, -1, -1));

        jLabel6.setText("Harga");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));
        getContentPane().add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 234, -1));

        jButton1.setText("Upload Gambar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, -1, -1));

        jMenu1.setText("Home");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Produk");
        jMenuBar1.add(jMenu2);

        kategoriMenu.setText("Kategori");
        kategoriMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kategoriMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(kategoriMenu);

        pesananMenu.setText("Pesanan");
        pesananMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pesananMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(pesananMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        new HomeView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenu1MouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        reset();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        
    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnSave1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSave1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSave1MouseClicked

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        if (productIdSelected != 0) {
            Object item = cmbKategori.getSelectedItem();
            int categoryId = ((ComboBoxItem)item).getId();

            String productName = txtProductName.getText();
            String productDescription = txtDescription.getText();
            int productPrice = Integer.parseInt(txtPrice.getText());
            String imagePath = "images/" + txtImage.getText();
            String imageName = saveImage(imagePath);
            
            controller.updateProduct(productIdSelected, productName, productDescription, productPrice, categoryId, imageName);
            loadData();
            reset();
        }
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
        if (productIdSelected != 0) {
            int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            
            if (response == JOptionPane.YES_OPTION) {
                Produk product = controller.getProduk(productIdSelected);
                deleteImage(product.getGambar());
                controller.deleteProduk(productIdSelected);
                reset();
                loadData();
            }
        }
    }//GEN-LAST:event_btnSave2ActionPerformed

    private void btnSave2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSave2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSave2MouseClicked

    private void btnSave3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSave3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSave3MouseClicked

    private void btnSave3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave3ActionPerformed
        if (
                txtProductName.getText().isEmpty() ||
                txtDescription.getText().isEmpty() ||
                txtPrice.getText().isEmpty() ||
                txtImage.getText().isEmpty()
        ) {
            JOptionPane.showMessageDialog(null, "Tolong isi semua data terlebih dahulu.", "Tidak ada data produk", JOptionPane.WARNING_MESSAGE);
        } else {
            int productPrice = 0;
            try {
                productPrice = Integer.parseInt(txtPrice.getText());
                Object item = cmbKategori.getSelectedItem();
                int categoryId = ((ComboBoxItem)item).getId();

                String productName = txtProductName.getText();
                String productDescription = txtDescription.getText();
                String imagePath = txtImage.getText();
                String imageName = saveImage(imagePath);

                controller.addProduct(productName, productDescription, productPrice, categoryId, imageName);
                reset();
                loadData();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Tolong isi hanya angka untuk harga.", "Harga hanya angka", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSave3ActionPerformed

    private void browseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            txtImage.setText(file.getAbsolutePath());
        }
    }
    
    private String saveImage(String imagePath) {
        File sourceFile = new File(imagePath);
        String fileName = sourceFile.getName();
        String destinationPath = "images/" + fileName;
        File destinationFile = new File(destinationPath);
        try {
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
    
    private void deleteImage(String imageName) {
        if (imageName != null && !imageName.isEmpty()) {
            String imagePath = "images/" + imageName;
            java.io.File file = new java.io.File(imagePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        browseImage();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void kategoriMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kategoriMenuMouseClicked
        new KategoriView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_kategoriMenuMouseClicked

    private void pesananMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pesananMenuMouseClicked
        new PesananView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_pesananMenuMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProdukView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProdukView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProdukView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProdukView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProdukView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSave2;
    private javax.swing.JButton btnSave3;
    private javax.swing.JComboBox<ComboBoxItem> cmbKategori;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu kategoriMenu;
    private javax.swing.JMenu pesananMenu;
    private javax.swing.JTable productTable;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtImage;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtProductName;
    // End of variables declaration//GEN-END:variables
}
