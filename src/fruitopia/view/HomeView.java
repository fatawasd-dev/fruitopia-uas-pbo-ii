/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fruitopia.view;

import fruitopia.controller.PesananController;
import fruitopia.controller.ProdukController;
import fruitopia.model.Pesanan;
import fruitopia.model.Produk;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javax.swing.Timer;


/**
 *
 * @author Asani
 */
public class HomeView extends javax.swing.JFrame {
    private ProdukController productController;
    private PesananController controller;
    private JTextField productCodeField;
    private JTextField quantityField;
    private JButton addButton;
    private JTable cartTable;
    private JButton checkoutButton;
    private JLabel totalLabel;
    private JPanel productPanel;
    private DefaultTableModel tableModel;
    private JLabel movingLabel;
    private int xPos = 10;
    private int yPos = 10;
    
    /**
     * Creates new form HomeView
     */
    public HomeView() {
        this.productController = new ProdukController();
        this.controller = new PesananController();
        initComponents();
        this.setLocationRelativeTo(null);
        setupPOSPanel();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setVisible(true);
    }
    
    private void setupPOSPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel(new BorderLayout());
        productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        JScrollPane productScrollPane = new JScrollPane(productPanel);
        leftPanel.add(productScrollPane, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(null);

        // Panel untuk logo dan teks FRUITOPIA
        JPanel logoPanel = new JPanel(null);
        logoPanel.setBounds(10, 10, 400, 50);

        ImageIcon logoIcon = new ImageIcon("src/fruitopia/assets/logo-transparent.png");
        Image logoImg = logoIcon.getImage();
        Image scaledLogoImg = logoImg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledLogoImg);

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(0, 0, 40, 40);
        logoPanel.add(logoLabel);

        movingLabel = new JLabel("<html><span style='font-size:24px; font-weight:bold;'>FRUITOPIA</span></html>");
        movingLabel.setBounds(xPos, yPos, 300, 40);
        logoPanel.add(movingLabel);

        rightPanel.add(logoPanel);

        String[] columnNames = { "Product Code", "Product Name", "Quantity", "Price", "Total" };
        tableModel = new DefaultTableModel(columnNames, 0);
        cartTable = new JTable(tableModel);

        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartScrollPane.setBounds(10, 80, 400, 200);
        rightPanel.add(cartScrollPane);

        totalLabel = new JLabel("Total: Rp 0");
        totalLabel.setBounds(10, 290, 200, 25);
        rightPanel.add(totalLabel);

        checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(290, 290, 120, 25);
        rightPanel.add(checkoutButton);

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.pack();

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalHarga = 0;

                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    totalHarga += (int) tableModel.getValueAt(i, 4);
                }

                // Buat objek Pesanan (misalnya, dengan userId 1 untuk sementara)
                Pesanan pesanan = new Pesanan(1, new java.util.Date(), totalHarga);

                // Simpan pesanan ke database
                controller.addPesanan(pesanan);

                JOptionPane.showMessageDialog(null, "Checkout successful!");
                tableModel.setRowCount(0); // Clear cart
                updateTotal();

                // Putar suara setelah checkout berhasil
                playSound("src/fruitopia/assets/pesanan-diterima.mp3");
            }
        });

        loadProducts();
        startMovingLabel();
    }
    
    private void startMovingLabel() {
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xPos += 5;
                if (xPos > 210) {
                    xPos = -movingLabel.getWidth();
                }
                movingLabel.setBounds(xPos, yPos, movingLabel.getPreferredSize().width, movingLabel.getPreferredSize().height);
                movingLabel.revalidate();
                movingLabel.repaint();
            }
        });
        timer.start();
    }

    private void loadProducts() {
        List<Produk> products = productController.loadProductList();

        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
        productPanel.add(rowPanel);

        for (int i = 0; i < products.size(); i++) {
            Produk product = products.get(i);
            int productCode = product.getId();
            String productName = product.getNama();
            int productPrice = product.getHarga();

            ImageIcon productImage = new ImageIcon("images/"+product.getGambar());
            Image img = productImage.getImage();
            Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            productImage = new ImageIcon(scaledImg);

            JButton productButton = new JButton("<html><center>" + productName + "<br>Rp " + productPrice + "</center></html>", productImage);
            productButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            productButton.setHorizontalTextPosition(SwingConstants.CENTER);
            productButton.setPreferredSize(new Dimension(200, 200));
            productButton.setMaximumSize(new Dimension(200, 200)); // Ensures button size remains constant
            productButton.setMinimumSize(new Dimension(200, 200));
            productButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addProductToCart(productCode, productName, 1, productPrice);
                }
            });

            rowPanel.add(productButton);
            if ((i + 1) % 8 == 0) {
                rowPanel = new JPanel();
                rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
                productPanel.add(rowPanel);
            }
        }

        this.revalidate();
        this.repaint();
    }


    private void addProductToCart(int productCode, String productName, int quantity, int price) {
        int total = price * quantity;

        tableModel.addRow(new Object[] { productCode, productName, quantity, price, total });

        updateTotal();
    }

    private void updateTotal() {
        int total = 0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            total += (int) tableModel.getValueAt(i, 4);
        }

        totalLabel.setText("Total: Rp " + total);
    }
    
    private void playSound(String filePath) {
    new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                FileInputStream fileInputStream = new FileInputStream(filePath);
                Player player = new Player(fileInputStream);
                player.play();
            } catch (FileNotFoundException | JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }).start();
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        productMenu = new javax.swing.JMenu();
        kategoriMenu = new javax.swing.JMenu();
        pesananMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Home");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        menuBar.add(jMenu1);

        productMenu.setText("Produk");
        productMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productMenuMouseClicked(evt);
            }
        });
        menuBar.add(productMenu);

        kategoriMenu.setText("Kategori");
        kategoriMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kategoriMenuMouseClicked(evt);
            }
        });
        menuBar.add(kategoriMenu);

        pesananMenu.setText("Pesanan");
        pesananMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pesananMenuMouseClicked(evt);
            }
        });
        menuBar.add(pesananMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void productMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productMenuMouseClicked
        new ProdukView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_productMenuMouseClicked

    private void kategoriMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kategoriMenuMouseClicked
        new KategoriView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_kategoriMenuMouseClicked

    private void pesananMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pesananMenuMouseClicked
        new PesananView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_pesananMenuMouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        new HomeView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenu1MouseClicked

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
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu kategoriMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu pesananMenu;
    private javax.swing.JMenu productMenu;
    // End of variables declaration//GEN-END:variables
}
