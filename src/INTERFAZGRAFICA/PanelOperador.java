package INTERFAZGRAFICA;

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
import COMPRAYSUBASTA.Registro;
import PERSISTENCIA.PersistenciaSerializar;
import PIEZAS.PiezaSubasta;
import PIEZAS.PiezaVenta;
import USUARIOS.Comprador;
import USUARIOS.Operador;
import galeria.Inventario;

public class PanelOperador extends JPanel implements ActionListener{
	
	private JButton subasta;
	
	private JButton historiaPieza,  salir, registrarS, volverS, consultar, volverH;
 
	
	private CardLayout cardLayout;
	
	private JPanel principal;
	
	private JTextField tituloP, id, tituloPH, idH;
	
	
	private Inventario inventario;
	
	private Operador operadorS;
	
	public PanelOperador(Operador operador) {
		
		operadorS = operador;
		cardLayout = new CardLayout();
	    principal = new JPanel(cardLayout);
	    
	    JPanel subastaPanel = registroSubasta();
	    JPanel historiaPiezaPanel = historiaPieza();
	    //JPanel loginPanel = createLoginPanel();
	    
	    principal.add(subastaPanel, "SubastaPanel");
	    principal.add(historiaPiezaPanel, "HistoriapPanel");
		
		 //JPanel panel = new JPanel();
	    setLayout(new GridLayout(3, 1, 10, 10));
	     
	    
	    JPanel menuPanel = new JPanel(new GridLayout(3, 1, 10, 10));
	    subasta = new JButton("Añadir registro subasta");
	     
	    historiaPieza = new JButton("Consultar historia pieza");
	     
	    salir = new JButton("Salir");
	     
	    menuPanel.add(subasta);
        menuPanel.add(historiaPieza);
        menuPanel.add(salir);

        // Añadir el panel de menú al principal
        principal.add(menuPanel, "MenuPanel");
	    
        subasta.addActionListener(this);
	    
	    historiaPieza.addActionListener(this);
	    
	    salir.addActionListener(this);
	    
	    setLayout(new GridLayout(1, 1));
        add(principal);
	    
	    cardLayout.show(principal, "MenuPanel");
	    
	    //setVisible(true);
	
	}
	
	public JPanel registroSubasta() {
		
		 JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
		 
		 JLabel compradorID = new JLabel("Comprador ID");
		 id = new JTextField();
		 
		 JLabel tituloPieza = new JLabel("Titulo pieza");
		 tituloP = new JTextField();
		 
		 registrarS = new JButton("Registrar subasta");
		 
		 volverS = new JButton("Volver");
		 
		 panel.add(compradorID);
		 panel.add(id);
		 panel.add(tituloPieza);
		 panel.add(tituloP);
		 panel.add(registrarS);
		 panel.add(volverS);
		 registrarS.addActionListener(this);
		 volverS.addActionListener(this);
		 
		return panel;
		
	}
	
	public JPanel historiaPieza() {
		
		
		JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
		
		JLabel compradorID = new JLabel("Comprador ID");
	 	idH = new JTextField();
		 
	 	JLabel tituloPieza = new JLabel("Titulo pieza");
	 	tituloPH = new JTextField();
	 	
	 	consultar = new JButton("Consultar historia");
		 
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
		
		//MENU REGISTRAR SUBASTA
		if (e.getSource()==subasta) {
			
			cardLayout.show(principal, "SubastaPanel");
			
		}
		//REGISTRAR SUBASTA
		else if (e.getSource()==registrarS){
			System.out.println("registro subasta");
			String pieza = tituloP.getText();
			String idC = id.getText();
			
			PiezaSubasta piezaS = null;
			try {
				piezaS = PersistenciaSerializar.getPiezaSubastaByTitulo(pieza);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			Comprador comprador =null;
			try {
				comprador = PersistenciaSerializar.getCompradorByID(idC);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Registro registro = new Registro(piezaS, comprador);
    		
    		operadorS.añadirRegistro(registro);
    		
    		JOptionPane.showMessageDialog(null, "Registro añadido correctamente");
            cardLayout.show(principal, "MenuPanel");
	
			
		}
		
		//VOLVER AL MENU PRINCIPAL DESDE REGISTRAR SUBASTA
		else if (e.getSource() == volverS || e.getSource() == volverH) {
			
			cardLayout.show(principal, "MenuPanel");
		}
		
		else if (e.getSource() == salir) {
			System.exit(0);
		}
		
		//HISTORIA PIEZA MENU
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
