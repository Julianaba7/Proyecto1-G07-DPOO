package INTERFAZGRAFICA;

import javax.swing.*;

import PERSISTENCIA.PersistenciaSerializar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Random;

public class CompradorApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Comprador comprador;

    public CompradorApp() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        
        JPanel startPanel = createStartPanel();
        JPanel registerPanel = createRegisterPanel();
        JPanel loginPanel = createLoginPanel();
        JPanel storePanel = createStorePanel();

        
        mainPanel.add(startPanel, "StartPanel");
        mainPanel.add(registerPanel, "RegisterPanel");
        mainPanel.add(loginPanel, "LoginPanel");
        mainPanel.add(storePanel, "StorePanel");

     
        add(mainPanel);
        setTitle("Comprador");
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

                comprador = new Comprador(id, nombre, usuario, contraseña);
                
                new PersistenciaSerializar("dataSerializacion/Compradores.txt",comprador);

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
                
                if (comprador != null && comprador.getLogin().equals(usuario) && comprador.getPassword().equals(contraseña)) {
                    cardLayout.show(mainPanel, "StorePanel");
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

    private JPanel createStorePanel() {
        JPanel panel = new StorePanel();
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CompradorApp().setVisible(true);
            }
        });
    }
}

class Comprador {
    private String id;
    private String name;
    private String login;
    private String password;

    public Comprador(String id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}