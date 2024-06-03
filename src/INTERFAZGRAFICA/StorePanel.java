package INTERFAZGRAFICA;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class StorePanel extends JPanel {
    private JTextField nameField;
    private JTextField emailField;
    private JTable productTable;
    private JTextArea transactionArea;
    private JButton buyButton;
    private JButton historyButton;
    private JComboBox<String> paymentMethodBox;

    public StorePanel() {
        setLayout(new BorderLayout());

        
        JPanel personalInfoPanel = new JPanel(new GridLayout(2, 2));
        personalInfoPanel.setBorder(BorderFactory.createTitledBorder("Información Personal"));
        personalInfoPanel.setBackground(new Color(173, 216, 230));
        personalInfoPanel.add(new JLabel("Nombre:"));
        nameField = new JTextField();
        personalInfoPanel.add(nameField);

        personalInfoPanel.add(new JLabel("Correo Electrónico:"));
        emailField = new JTextField();
        personalInfoPanel.add(emailField);

        add(personalInfoPanel, BorderLayout.NORTH);

        
        JLabel imageLabel = new JLabel(loadImage("images/image.png"));
        add(imageLabel, BorderLayout.WEST);

        // Tabla de productos pdd: no logre subir las imagenes :(
        String[] columnNames = {"Imagen", "ID", "Nombre", "Precio"};
        Object[][] data = {
            {loadImage("images/image1.png"), "1", "Producto A", "10.00"},
            {loadImage("images/image2.png"), "2", "Producto B", "20.00"},
            {loadImage("images/image3.png"), "3", "Producto C", "30.00"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) {
                    return ImageIcon.class;
                }
                return String.class;
            }
        };

        productTable = new JTable(model);
        productTable.setRowHeight(100);
        productTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        // métodos de pago
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(new Color(224, 255, 255));

        JPanel paymentPanel = new JPanel(new FlowLayout());
        paymentPanel.setBackground(new Color(224, 255, 255));
        paymentPanel.add(new JLabel("Método de Pago:"));
        paymentMethodBox = new JComboBox<>(new String[]{"Tarjeta de Crédito", "PayPal", "Apple Pay"});
        paymentPanel.add(paymentMethodBox);
        buttonPanel.add(paymentPanel, BorderLayout.NORTH);

        JPanel actionPanel = new JPanel();
        actionPanel.setBackground(new Color(224, 255, 255));
        buyButton = new JButton("Comprar");
        buyButton.setBackground(new Color(60, 179, 113));
        buyButton.setForeground(Color.WHITE);
        historyButton = new JButton("Ver Historial");
        historyButton.setBackground(new Color(70, 130, 180));
        historyButton.setForeground(Color.WHITE);
        actionPanel.add(buyButton);
        actionPanel.add(historyButton);
        buttonPanel.add(actionPanel, BorderLayout.SOUTH);

        add(buttonPanel, BorderLayout.SOUTH);

        // Área de transacciones
        transactionArea = new JTextArea();
        transactionArea.setEditable(false);
        transactionArea.setBorder(BorderFactory.createTitledBorder("Transacciones"));
        transactionArea.setBackground(new Color(245, 245, 245));
        add(new JScrollPane(transactionArea), BorderLayout.EAST);

        // Acciones de los botones
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performPurchase();
            }
        });

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewHistory();
            }
        });
    }

    private ImageIcon loadImage(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            return new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void performPurchase() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            String productId = (String) productTable.getValueAt(selectedRow, 1);
            String productName = (String) productTable.getValueAt(selectedRow, 2);
            String productPrice = (String) productTable.getValueAt(selectedRow, 3);
            String paymentMethod = (String) paymentMethodBox.getSelectedItem();

            
            String result = "Compra realizada:\n" +
                            "Producto ID: " + productId + "\n" +
                            "Nombre: " + productName + "\n" +
                            "Precio: " + productPrice + "\n" +
                            "Método de Pago: " + paymentMethod + "\n";

            transactionArea.append(result + "\n");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto para comprar.");
        }
    }

    private void viewHistory() {
        
        String history = "Historial de Compras:\n" +
                         "1. Producto A - $10.00\n" +
                         "2. Producto B - $20.00\n";

        transactionArea.append(history + "\n");
    }
}