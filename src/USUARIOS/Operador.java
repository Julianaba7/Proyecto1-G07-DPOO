package USUARIOS;

import java.util.ArrayList;
import java.util.List;

import COMPRAYSUBASTA.Oferta;
import COMPRAYSUBASTA.Registro;

public class Operador extends Usuario{
	
	private List<Oferta> ofertas;//Es comodo que cada operador tenga una lista de ofertas de la subasta,
	//Facilita la lógica
	
	private List<Registro> registros;
	
	
	public Operador(String id, String nombre, String login, String password) {
		super(id, nombre, login, password);
		this.rol = RolUsuarios.OPERADOR;
		this.ofertas = new ArrayList<Oferta>();
		this.registros = new ArrayList<Registro>();
	}
	
	public void añadirRegistro(Registro registro) {
		
		registros.add(registro);
	}
}
