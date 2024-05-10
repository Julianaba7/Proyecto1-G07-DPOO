package galeria;

import java.util.List;

import PIEZAS.EstadoPiezas;
import PIEZAS.Pieza;
import PIEZAS.PiezaSubasta;
import PIEZAS.PiezaVenta;
import USUARIOS.Comprador;
import USUARIOS.Usuario;

public class Administrador {
	
	private String id;
	
	private String nombre;
	
	private String login;
	
	private String password;

	public Administrador(String id, String nombre, String login, String password) {
		this.id = id;
		this.nombre = nombre;
		this.login = login;
		this.password = password;
	}
	
	
		
	public String getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}

	//El administrador es el unico que puede hacer la consignación
	public void consignar(Pieza pieza, List<Pieza> bodega, List<Pieza> exhibicion) {
		if (pieza.getEstado().equals(EstadoPiezas.EXHIBIDA)){
    		exhibicion.add(pieza);
    	} else {
    		bodega.add(pieza);
    	}
	}
	
	//EL administrador puede confirmar si una pieza es vendida
	public boolean confirmarSale(Pieza pieza, List<Pieza> noDisponible ) {
		 
		 boolean confirm = false;
		 
		 if (noDisponible.contains(pieza)) {
			 if (pieza.getEstado().equals(EstadoPiezas.VENDIDA));
			 confirm = true;
		 }
		 return confirm;
	 }
	 
	//El administrador puede confirmar si una pieza se le devolvio al propietario
	 public boolean confirmarReturn(Pieza pieza, List<Pieza> noDisponible) {
		 
		 boolean confirm = false;
		 
		 if (noDisponible.contains(pieza)) {
			 if (pieza.getEstado().equals(EstadoPiezas.DEVOLUCION));
			 confirm = true;
		 }
		 return confirm;
	 }
	
	public void verificarComprador(Comprador comprador, PiezaVenta pieza) {
		double valorPieza = pieza.getValorFijo();
		double dineroComprador = comprador.getPoderAdquisitivo();
		
		if (valorPieza <= dineroComprador) {
			comprador.setVerificadoParaCompra(true);
		}else {
			comprador.setVerificadoParaCompra(false);
		}
	}
	
	
}
