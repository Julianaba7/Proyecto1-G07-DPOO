package COMPRAYSUBASTA;

import java.util.ArrayList;
import java.util.HashMap;

import PIEZAS.Pieza;
import USUARIOS.Comprador;
import USUARIOS.Operador;

public class Subasta {
	
	private ArrayList<Pieza> piezas;//Una subasta tiene una lista de piezas a subastar
	
	private HashMap<String, Comprador> compradores; //Una subasta tiene una tabla de hash de compradores
	//Si nuestra estructura de datos es una tabla de hash facilita el acceso a los compradores que poseen un id único.
	
	private Operador operador; //Una subasta tiene un usuario operador que la administra
	
	public Subasta(Operador operador) {
		this.piezas = new ArrayList<Pieza>();
		this.compradores = new HashMap<String, Comprador>();
		this.operador = operador;
	}
}
