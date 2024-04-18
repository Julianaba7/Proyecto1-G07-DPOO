package COMPRAYSUBASTA;

import PIEZAS.PiezaSubasta;
import USUARIOS.Comprador;

public class Registro {
	
	private PiezaSubasta pieza;
	private Comprador comprador;
	
	
	
	public Registro(PiezaSubasta pieza, Comprador comprador) {
		this.pieza = pieza;
		this.comprador = comprador;
	}

	public PiezaSubasta getPieza() {
		return pieza;
	}
	
	public Comprador getComprador() {
		return comprador;
	}

	
}
