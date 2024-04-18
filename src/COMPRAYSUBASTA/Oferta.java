package COMPRAYSUBASTA;


import PIEZAS.Pieza;
import PIEZAS.PiezaVenta;
import USUARIOS.Comprador;
import galeria.Administrador;

//Esta Clase ayuda a controlar las ofertas en la subasta
public class Oferta {
	
	private PiezaVenta pieza;
	
	private Comprador comprador;
	
	public Oferta(PiezaVenta pieza, Comprador comprador) {
		this.pieza = pieza;
		this.comprador = comprador;
	}
	
	

	public PiezaVenta getPieza() {
		return pieza;
	}
	
	public Comprador getComprador() {
		return comprador;
	}

	public boolean confirmarVerificacionComprador(Administrador administrador) {
		administrador.verificarComprador(comprador, pieza);
		
		boolean verificadoParaCompra = comprador.isVerificadoParaCompra();
		
		if (verificadoParaCompra == true) {
			return true;
		}else {
			return false;
		}
	}
}
