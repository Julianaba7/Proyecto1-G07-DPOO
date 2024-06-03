package INTERFAZGRAFICA;

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
import javax.swing.JTextField;

import COMPRAYSUBASTA.Oferta;
import PAGOS.MetodoPago;
import PAGOS.Pago;
import PERSISTENCIA.PersistenciaSerializar;
import PIEZAS.PiezaVenta;
import USUARIOS.Cajero;
import USUARIOS.Comprador;
import galeria.Inventario;

public class PanelCajero extends JPanel implements ActionListener{
	
	private JButton pago;
	
	private JButton historiaPieza,  salir, registrarS, volverP, consultar, volverH, procesar;
 
	private CardLayout cardLayout;
	
	private JPanel principal;
	
	private JTextField tituloP, id, tituloPH, idH, monto;
	
	private Inventario inventario;
	
	private JComboBox<String> metodos;
	
	private Cajero cajeroS;
	
	public PanelCajero(Cajero cajero) {
		
		cajeroS = cajero;
		
		cardLayout = new CardLayout();
	    principal = new JPanel(cardLayout);
	    
	    JPanel pagoPanel = pago();
	    JPanel historiaPiezaPanel = historiaPieza();
	    //JPanel loginPanel = createLoginPanel();
	    
	    principal.add(pagoPanel, "PagoPanel");
	    principal.add(historiaPiezaPanel, "HistoriapPanel");
		
		 //JPanel panel = new JPanel();
	    setLayout(new GridLayout(3, 1, 10, 10));
	     
	    
	    JPanel menuPanel = new JPanel(new GridLayout(3, 1, 10, 10));
	    pago = new JButton("Registrar pago");
	     
	    historiaPieza = new JButton("Consultar historia pieza");
	     
	    salir = new JButton("Consultar historia artista");
	     
	    menuPanel.add(pago);
        menuPanel.add(historiaPieza);
        menuPanel.add(salir);

        // Añadir el panel de menú al principal
        principal.add(menuPanel, "MenuPanel");
	    
	    pago.addActionListener(this);
	    
	    historiaPieza.addActionListener(this);
	    
	    salir.addActionListener(this);
	    
	    setLayout(new GridLayout(1, 1));
        add(principal);
	    
	    cardLayout.show(principal, "MenuPanel");
	    
	    //setVisible(true);
	
		
	}
	
	public JPanel pago() {
		
		JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
		 
		 JLabel metodoPago = new JLabel("Metodo de pago");
		 metodos = new JComboBox<String>();
		 metodos.addItem("Tarjeta de credito");
		 metodos.addItem("Transferencia");
		 metodos.addItem("Efectivo");
		 
		 JLabel montoPago = new JLabel("Monto a pagar");
		 monto = new JTextField();
		 
		 procesar = new JButton("Procesar pago");
		 
		 volverP = new JButton("Volver");
		 
		 panel.add(metodoPago);
		 panel.add(metodos);
		 panel.add(montoPago);
		 panel.add(monto);
		 panel.add(procesar);
		 panel.add(volverP);
		 procesar.addActionListener(this);
		 volverP.addActionListener(this);
		 
		return panel;
		
	}
	
	public JPanel historiaPieza() {
		
		
		JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
		
		JLabel compradorID = new JLabel("Comprador ID");
	 	idH = new JTextField();
		 
	 	JLabel tituloPieza = new JLabel("Titulo pieza");
	 	tituloPH = new JTextField();
	 	
	 	consultar = new JButton("Registrar subasta");
		 
		volverH = new JButton("Volver");
		 
		panel.add(compradorID);
		panel.add(idH);
		panel.add(tituloPieza);
		panel.add(tituloPH);
		panel.add(consultar);
		panel.add(volverH);
		consultar.addActionListener(this);
		volverH.addActionListener(this);
		
		
		return panel;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == pago) {
			
			cardLayout.show(principal, "PagoPanel");
			
		}
		
		else if (e.getSource()==procesar) {
			
			String metodoPay = (String) metodos.getSelectedItem();
			double montoPag = Double.parseDouble(monto.getText());
			MetodoPago metp = null;
			
			if (metodoPay == "Tarjeta de credito") {
				metp = MetodoPago.TARJETADECREDITO; 	
			}else if (metodoPay == "Transferencia") {
				metp = MetodoPago.TRANSFERENCIA;
			}else if (metodoPay == "Efectivo") {
				metp = MetodoPago.EFECTIVO;
			}
			
			Pago pago = new Pago(montoPag, metp);
			Cajero.procesarPago(pago);
			
			JOptionPane.showMessageDialog(null, "Se proceso el pago con exito");
            cardLayout.show(principal, "MenuPanel"); 
			
		}
		
		else if(e.getSource() == historiaPieza) {
			
			cardLayout.show(principal, "HistoriapPanel");
			
		}
		
		//CONSULTAR HISTORIA PIEZA
		else if (e.getSource()== consultar) {
			
			String pieza = tituloPH.getText();
			String idC = idH.getText();
			
			PiezaVenta piezaVenta = null;
			try {
				piezaVenta = PersistenciaSerializar.getPiezaVentaByTitulo(pieza);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Comprador comprador = null;
			try {
				comprador = PersistenciaSerializar.getCompradorByID(idC);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Oferta oferta = new Oferta(piezaVenta, comprador);
    		String history = inventario.historiaDePiezaVendida(oferta);
    		
    		JOptionPane.showMessageDialog(null, "Mostrando historia pieza "+ history);
            cardLayout.show(principal, "MenuPanel"); 
			
		}
	
	}

}
