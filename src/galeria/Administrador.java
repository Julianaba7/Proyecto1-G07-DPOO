package galeria;

import java.util.List;

import PIEZAS.EstadoPiezas;
import PIEZAS.Pieza;

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
		
	//El administrador es el unico que puede hacer la consignación
	public void consignar(Pieza pieza, List<Pieza> bodega, List<Pieza> exhibicion) {
		if (pieza.getEstado().equals(EstadoPiezas.EXHIBIDA)){
    		exhibicion.add(pieza);
    	} else {
    		bodega.add(pieza);
    	}
	}
	
	public boolean confirmarSale(Pieza pieza, List<Pieza> noDisponible ) {
		 
		 boolean confirm = false;
		 
		 if (noDisponible.contains(pieza)) {
			 if (pieza.getEstado().equals(EstadoPiezas.VENDIDA));
			 confirm = true;
		 }
		 return confirm;
	 }
	 
	 public boolean confirmarReturn(Pieza pieza, List<Pieza> noDisponible) {
		 
		 boolean confirm = false;
		 
		 if (noDisponible.contains(pieza)) {
			 if (pieza.getEstado().equals(EstadoPiezas.DEVOLUCION));
			 confirm = true;
		 }
		 return confirm;
	 }
	
	
}
