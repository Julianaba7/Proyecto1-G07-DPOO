package INTERFAZGRAFICA;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import COMPRAYSUBASTA.Oferta;
import PAGOS.MetodoPago;
import PERSISTENCIA.PersistenciaSerializar;
import PIEZAS.PiezaVenta;
import USUARIOS.Comprador;
import galeria.Inventario;

public class ConsultarHistoriaComprador extends JPanel{
	private Inventario inventario;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    public ConsultarHistoriaComprador(Inventario inventario, CardLayout cardLayout, JPanel mainPanel) {
        this.inventario = inventario;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());

        JPanel formPanel = createFormPanel();

        add(formPanel, BorderLayout.CENTER);
    }
    
    private JPanel createFormPanel() {
    	JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
    	
    	JLabel idLabel = new JLabel("ID Comprador: ");
        JTextField idField = new JTextField();
        
        JButton consignButton = new JButton("Buscar Historia");
        consignButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		String id = idField.getText();
        		Comprador comprador = null;
        		
        		try {
					comprador = PersistenciaSerializar.getCompradorByID(id);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		String mensaje = inventario.historiaComprador(comprador);
        		
        		JOptionPane.showMessageDialog(ConsultarHistoriaComprador.this, mensaje);
                cardLayout.show(mainPanel, "AdminPanel");
        	
        	}
        });
        panel.add(idLabel);
        panel.add(idField);
        
        panel.add(new JLabel()); // Placeholder
        panel.add(consignButton);
        
        return panel;
    }

}
