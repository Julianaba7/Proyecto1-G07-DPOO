package INTERFAZGRAFICA;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import COMPRAYSUBASTA.Oferta;
import PAGOS.MetodoPago;
import PERSISTENCIA.PersistenciaSerializar;
import PIEZAS.PiezaVenta;
import PIEZAS.TipoPieza;
import USUARIOS.Comprador;
import galeria.Inventario;

public class consultarHistoriaPiezaPanel extends JPanel{
	private Inventario inventario;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    public consultarHistoriaPiezaPanel(Inventario inventario, CardLayout cardLayout, JPanel mainPanel) {
        this.inventario = inventario;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());

        JPanel formPanel = createFormPanel();

        add(formPanel, BorderLayout.CENTER);
    }
    
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        
        JLabel titleLabel = new JLabel("Título Pieza:");
        JTextField titleField = new JTextField();
        
        JLabel idLabel = new JLabel("ID Comprador: ");
        JTextField idField = new JTextField();
        
        JLabel nombreLabel = new JLabel("Nombre Comprador: ");
        JTextField nombreField = new JTextField();
        
        JLabel loginLabel = new JLabel("Usuario Comprador: ");
        JTextField loginField = new JTextField();
        
        JLabel compradorPasswordLabel = new JLabel("Contraseña del comprador:");
        JPasswordField compradorPasswordField = new JPasswordField();
        
        JLabel poderLabel = new JLabel("Poder Adquisitivo comprador: ");
        JTextField poderField = new JTextField();
        
        JLabel metodoLabel = new JLabel("Método de pago:");
        JComboBox<String> metodoComboBox = new JComboBox<>(new String[]{"Tarjeta de crédito", "Transferencia", "Efectivo"});
        
        JButton consignButton = new JButton("Buscar Historia");
        consignButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		String titulo = titleField.getText();
        		
        		PiezaVenta pieza = null;
        		try {
					pieza = PersistenciaSerializar.getPiezaByTitulo(titulo);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		//Creacion Comprador:
        		String id = idField.getText();
        		String nombre = nombreField.getText();
        		String login = loginField.getText();
        		String password = new String(compradorPasswordField.getPassword());
        		double poder = Double.parseDouble(poderField.getText());
        		
        		String tipoPieza = (String) metodoComboBox.getSelectedItem();
                Comprador comprador = null;

                switch (tipoPieza) {
                	case("Tarjeta de crédito"):
                		comprador = new Comprador(id, nombre, login, password, poder, MetodoPago.TARJETADECREDITO);
                	
                	case("Transferencia"):
                		comprador = new Comprador(id, nombre, login, password, poder, MetodoPago.TRANSFERENCIA);
                	
                	case("Efectivo"):
                		comprador = new Comprador(id, nombre, login, password, poder, MetodoPago.EFECTIVO);
                }
                
                Oferta oferta = new Oferta(pieza, comprador);
                inventario.historiaDePiezaVendida(oferta);
                
                JOptionPane.showMessageDialog(consultarHistoriaPiezaPanel.this, "Historia Realizada Con exito");
                cardLayout.show(mainPanel, "AdminPanel");
        		
        		
        		
        	}
        });
        
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(loginLabel);
        panel.add(loginField);
        panel.add(poderLabel);
        panel.add(poderField);
        panel.add(metodoLabel);
        panel.add(metodoComboBox);
        
        panel.add(new JLabel()); // Placeholder
        panel.add(consignButton);
        
        return panel;
    }
}
