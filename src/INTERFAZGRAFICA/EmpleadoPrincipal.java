package INTERFAZGRAFICA;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import PERSISTENCIA.PersistenciaSerializar;
import USUARIOS.Cajero;
import USUARIOS.Empleado;
import USUARIOS.Operador;
import USUARIOS.Usuario;

public class EmpleadoPrincipal extends JFrame implements ActionListener {

    private CardLayout cardLayout;

    private JPanel principal;

    private JComboBox<String> empleadosR;

    private JComboBox<String> empleadosL;

    private JButton registerButton, backButtonR, backButtonL, logInButton;

    private JTextField nameField, userField, idField, userFieldL;

    private JPasswordField passwordFieldR;

    private JPasswordField passwordFieldL;

    private Usuario authenticatedUser;

    public EmpleadoPrincipal() {

        cardLayout = new CardLayout();
        principal = new JPanel(cardLayout);

        // Crear pantallas
        JPanel startPanel = createStartPanel();
        JPanel registerPanel = createRegisterPanel();
        JPanel loginPanel = createLoginPanel();

        // Añadir pantallas al panel principal
        principal.add(startPanel, "StartPanel");
        principal.add(registerPanel, "RegisterPanel");
        principal.add(loginPanel, "LoginPanel");

        // Configurar la ventana principal
        add(principal);
        setTitle("Empleado");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    public JPanel createStartPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton registrar = new JButton("Registrarse");
        JButton login = new JButton("Iniciar sesión");
        JButton salir = new JButton("Salir");

        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(principal, "RegisterPanel");
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(principal, "LoginPanel");
            }
        });

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(registrar);
        panel.add(login);
        panel.add(salir);

        return panel;

    }

    private JPanel createRegisterPanel() {

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel tipoEmpleado = new JLabel("Rol:");

        empleadosR = new JComboBox<String>();
        empleadosR.addItem("Operador");
        empleadosR.addItem("Cajero");

        JLabel nameLabel = new JLabel("Nombre: ");
        nameField = new JTextField();

        JLabel userLabel = new JLabel("Usuario: ");
        userField = new JTextField();

        JLabel passwordLabel = new JLabel("Contraseña: ");
        passwordFieldR = new JPasswordField();

        registerButton = new JButton("Registrarse");
        backButtonR = new JButton("Volver");

        panel.add(tipoEmpleado);
        panel.add(empleadosR);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordFieldR);
        panel.add(registerButton);
        panel.add(backButtonR);
        registerButton.addActionListener(this);
        backButtonR.addActionListener(this);

        return panel;
    }

    private JPanel createLoginPanel() {

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel tipoEmpleado = new JLabel("Rol:");

        empleadosL = new JComboBox<String>();
        empleadosL.addItem("Operador");
        empleadosL.addItem("Cajero");

        JLabel userLabel = new JLabel("Usuario: ");
        userFieldL = new JTextField();

        JLabel passwordLabel = new JLabel("Contraseña: ");
        passwordFieldL = new JPasswordField();

        JLabel idLabel = new JLabel("ID: ");
        idField = new JTextField();

        logInButton = new JButton("Iniciar Sesión");
        backButtonL = new JButton("Volver");

        panel.add(tipoEmpleado);
        panel.add(empleadosL);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(userLabel);
        panel.add(userFieldL);
        panel.add(passwordLabel);
        panel.add(passwordFieldL);
        panel.add(logInButton);
        panel.add(backButtonL);
        logInButton.addActionListener(this);
        backButtonL.addActionListener(this);

        return panel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {

            String userType = (String) empleadosR.getSelectedItem();

            String nombre = nameField.getText();
            String usuario = userField.getText();
            String contraseña = new String(passwordFieldR.getPassword());
            Random r = new Random();
            String id = new BigInteger(50, r).toString(32);

            if (userType.equals("Operador")) {

                Operador operador = new Operador(id, usuario, nombre, contraseña);
                new PersistenciaSerializar("dataSerializacion/Empleados.txt", operador);

                JOptionPane.showMessageDialog(null, "Se ha registrado con éxito. Su ID es: " + id);
                cardLayout.show(principal, "StartPanel");

            } else if (userType.equals("Cajero")) {

                Cajero cajero = new Cajero(id, usuario, nombre, contraseña);
                new PersistenciaSerializar("dataSerializacion/Empleados.txt", cajero);

                JOptionPane.showMessageDialog(null, "Se ha registrado con éxito. Su ID es: " + id);
                cardLayout.show(principal, "StartPanel");

            }

        } else if (e.getSource() == backButtonR) {
            cardLayout.show(principal, "StartPanel");
        } else if (e.getSource() == backButtonL) {
            cardLayout.show(principal, "StartPanel");
        } else if (e.getSource() == logInButton) {

            String userType = (String) empleadosL.getSelectedItem();
            String id = idField.getText();
            String usuarioL = userFieldL.getText();
            String contraseñaL = new String(passwordFieldL.getPassword());

            Usuario user = null;
            try {
                user = PersistenciaSerializar.getEmpleadoByID(id);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            if (user != null) {
                if (userType.equals("Operador") && user instanceof Operador) {
                    if (user.getLogin().equals(usuarioL) && user.getPassword().equals(contraseñaL)) {
                        authenticatedUser = user;
                        principal.add(new PanelOperador((Operador) authenticatedUser), "OperadorPanel");
                        cardLayout.show(principal, "OperadorPanel");
                    }
                } else if (userType.equals("Cajero") && user instanceof Cajero) {
                    if (user.getLogin().equals(usuarioL) && user.getPassword().equals(contraseñaL)) {
                        authenticatedUser = user;
                        principal.add(new PanelCajero((Cajero) authenticatedUser), "CajeroPanel");
                        cardLayout.show(principal, "CajeroPanel");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmpleadoPrincipal().setVisible(true);
            }
        });
    }
}