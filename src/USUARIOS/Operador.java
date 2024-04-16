package USUARIOS;

import java.util.ArrayList;

import COMPRAYSUBASTA.Oferta;

public class Operador extends Usuario{
	
	private ArrayList<Oferta> ofertas;//Es comodo que cada operador tenga una lista de ofertas de la subasta,
	//Facilita la lógica
	
	
	public Operador(String id, String nombre, String login, String password) {
		super(id, nombre, login, password);
		this.rol = RolUsuarios.OPERADOR;
		this.ofertas = new ArrayList<Oferta>();
	}
}
