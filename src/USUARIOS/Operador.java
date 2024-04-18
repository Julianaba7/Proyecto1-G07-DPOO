package USUARIOS;

import java.util.ArrayList;
import java.util.List;

import COMPRAYSUBASTA.Oferta;
import COMPRAYSUBASTA.Registro;

public class Operador extends Usuario{
	
	private List<Registro> registros;
	
	
	public Operador(String id, String nombre, String login, String password) {
		super(id, nombre, login, password);
		this.rol = RolUsuarios.OPERADOR;
		this.registros = new ArrayList<Registro>();
	}
	
	
	
	public List<Registro> getRegistros() {
		return registros;
	}

	public void añadirRegistro(Registro registro) {
		
		registros.add(registro);
	}
}
