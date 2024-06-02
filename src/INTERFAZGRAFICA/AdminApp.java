package INTERFAZGRAFICA;

import javax.swing.*;

import CONSIGNACION.Consignacion;
import PERSISTENCIA.PersistenciaSerializar;
import PIEZAS.Escultura;
import PIEZAS.Fotografia;
import PIEZAS.Impresion;
import PIEZAS.Pieza;
import PIEZAS.Pintura;
import PIEZAS.TipoPieza;
import PIEZAS.Video;
import USUARIOS.Propietario;
import galeria.Administrador;
import galeria.Inventario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class AdminApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Administrador administrador;
    private Inventario inventario;

    public AdminApp() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Crear pantallas
        JPanel startPanel = createStartPanel();
        JPanel registerPanel = createRegisterPanel();
        JPanel loginPanel = createLoginPanel();
        JPanel adminPanel = createAdminPanel();

        // Añadir pantallas al panel principal
        mainPanel.add(startPanel, "StartPanel");
        mainPanel.add(registerPanel, "RegisterPanel");
        mainPanel.add(loginPanel, "LoginPanel");
        mainPanel.add(adminPanel, "AdminPanel");

        // Configurar la ventana principal
        add(mainPanel);
        setTitle("Administrador");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createStartPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton registerButton = new JButton("Registrarse");
        JButton loginButton = new JButton("Iniciar sesión");
        JButton exitButton = new JButton("Salir");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "RegisterPanel");
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginPanel");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(registerButton);
        panel.add(loginButton);
        panel.add(exitButton);

        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel nameLabel = new JLabel("Ingrese su nombre: ");
        JTextField nameField = new JTextField();

        JLabel userLabel = new JLabel("Ingrese su usuario: ");
        JTextField userField = new JTextField();

        JLabel passwordLabel = new JLabel("Ingrese su contraseña: ");
        JPasswordField passwordField = new JPasswordField();

        JButton registerButton = new JButton("Registrarse");
        JButton backButton = new JButton("Volver");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random r = new Random();
                String nombre = nameField.getText();
                String usuario = userField.getText();
                String contraseña = new String(passwordField.getPassword());
                String id = new BigInteger(50, r).toString(32);

                administrador = new Administrador(id, nombre, usuario, contraseña);
                inventario = new Inventario(administrador);
                new PersistenciaSerializar("dataSerializacion/Admin.txt", administrador);

                JOptionPane.showMessageDialog(null, "Se ha registrado con éxito. Su ID es: " + id);
                cardLayout.show(mainPanel, "StartPanel");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "StartPanel");
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(registerButton);
        panel.add(backButton);

        return panel;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel userLabel = new JLabel("Ingrese su usuario: ");
        JTextField userField = new JTextField();

        JLabel passwordLabel = new JLabel("Ingrese su contraseña: ");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Iniciar sesión");
        JButton backButton = new JButton("Volver");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = userField.getText();
                String contraseña = new String(passwordField.getPassword());

                if (administrador != null && administrador.getLogin().equals(usuario) && administrador.getPassword().equals(contraseña)) {
                    cardLayout.show(mainPanel, "AdminPanel");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "StartPanel");
            }
        });

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(backButton);

        return panel;
    }

    private JPanel createAdminPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton consignPieceButton = new JButton("Consignar pieza");
        JButton pieceHistoryButton = new JButton("Consultar historia de una pieza");
        JButton buyerHistoryButton = new JButton("Consultar historia de un comprador");

        consignPieceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JPanel consignPiecePanel = new ConsignacionPanel(inventario, cardLayout, mainPanel);
                mainPanel.add(consignPiecePanel, "ConsignPiecePanel");
                cardLayout.show(mainPanel, "ConsignPiecePanel");
            }
        });
        
        pieceHistoryButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
            	JPanel  pieceHistoryPanel = new consultarHistoriaPiezaPanel(inventario, cardLayout, mainPanel);
                mainPanel.add(pieceHistoryPanel, "PieceHistoryPanel");
                cardLayout.show(mainPanel, "PieceHistoryPanel");
            }
        	
        });
        
        buyerHistoryButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
            	JPanel consignPiecePanel = new ConsultarHistoriaComprador(inventario, cardLayout, mainPanel);
                mainPanel.add(consignPiecePanel, "BuyerHistoryButton");
                cardLayout.show(mainPanel, "BuyerHistoryButton");
            }
        });
        

        panel.add(consignPieceButton);
        panel.add(pieceHistoryButton);
        panel.add(buyerHistoryButton);

        return panel;
    }

    


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminApp().setVisible(true);
            }
        });
    }
}