/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fruitopia.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Asani
 */
public class HomeView extends javax.swing.JFrame {
    private JTextField productCodeField;
    private JTextField quantityField;
    private JButton addButton;
    private JTable cartTable;
    private JButton checkoutButton;
    private JLabel totalLabel;
    private JPanel productPanel;
    private DefaultTableModel tableModel;
    /**
     * Creates new form HomeView
     */
    public HomeView() {
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

        JLabel productCodeLabel = new JLabel("Product Code:");
        productCodeLabel.setBounds(10, 10, 100, 25);
        rightPanel.add(productCodeLabel);

        productCodeField = new JTextField(20);
        productCodeField.setBounds(120, 10, 160, 25);
        rightPanel.add(productCodeField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(10, 40, 100, 25);
        rightPanel.add(quantityLabel);

        quantityField = new JTextField(20);
        quantityField.setBounds(120, 40, 160, 25);
        rightPanel.add(quantityField);

        addButton = new JButton("Add to Cart");
        addButton.setBounds(290, 10, 120, 55);
        rightPanel.add(addButton);

        String[] columnNames = { "Product Code", "Product Name", "Quantity", "Price", "Total" };
        tableModel = new DefaultTableModel(columnNames, 0);
        cartTable = new JTable(tableModel);

        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartScrollPane.setBounds(10, 80, 400, 200);
        rightPanel.add(cartScrollPane);

        totalLabel = new JLabel("Total: $0.00");
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

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProductToCart(productCodeField.getText(), "Sample Product", Integer.parseInt(quantityField.getText()), 10.0);
            }
        });

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Checkout successful!");
                tableModel.setRowCount(0); // Clear cart
                updateTotal();
            }
        });

        loadProducts();
    }

    private void loadProducts() {
        // Sample product data
       String[] productCodes = { "P001", "P002", "P003", "P003", "P003", "P003", "P003", "P003", "P003", "P003"};
        String[] productNames = { "Apple", "Banana", "Orange", "Grape", "Peach", "Mango", "Pineapple", "Cherry", "Kiwi", "Watermelon", 
                                  "Strawberry", "Blueberry", "Raspberry", "Blackberry", "Papaya", "Guava", "Lychee", "Lemon", "Lime", "Coconut" };
        double[] productPrices = { 1.0, 0.5, 0.75, 1.5, 2.0, 1.25, 3.0, 0.2, 0.6, 0.9, 
                                   1.1, 2.5, 1.3, 2.8, 1.4, 2.3, 2.6, 0.7, 0.8, 1.7 };

        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
        productPanel.add(rowPanel);

        for (int i = 0; i < productCodes.length; i++) {
            String productCode = productCodes[i];
            String productName = productNames[i];
            double productPrice = productPrices[i];

            // Load image from folder
            String imagePath = "images/image.jpg";
            ImageIcon productImage = new ImageIcon(imagePath);
            Image img = productImage.getImage();
            Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            productImage = new ImageIcon(scaledImg);

            JButton productButton = new JButton("<html><center>" + productName + "<br>$" + productPrice + "</center></html>", productImage);
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
            if ((i + 1) % 4 == 0) {
                rowPanel = new JPanel();
                rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
                productPanel.add(rowPanel);
            }
        }

        this.revalidate();
        this.repaint();
    }


    private void addProductToCart(String productCode, String productName, int quantity, double price) {
        double total = price * quantity;

        tableModel.addRow(new Object[] { productCode, productName, quantity, price, total });

        updateTotal();
    }

    private void updateTotal() {
        double total = 0.0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            total += (double) tableModel.getValueAt(i, 4);
        }

        totalLabel.setText("Total: $" + String.format("%.2f", total));
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
