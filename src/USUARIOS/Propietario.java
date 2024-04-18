package USUARIOS;

import java.util.ArrayList;
import java.util.List;

import PIEZAS.Pieza;

public class Propietario {
	
	private String id;
	
	private String nombre;
	
	private String login;
	
	private String password;
	
	private List<Pieza> piezasPropias; // Lista de piezas tiene el propietario

	public Propietario(String id, String nombre, String login, String password) {
		this.id = id;
		this.nombre = nombre;
		this.login = login;
		this.password = password;
		this.piezasPropias = new ArrayList<>();
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
	
	
	
	public List<Pieza> getPiezasPropias() {
		return piezasPropias;
	}


	public void agregarPieza(Pieza pieza) {
        if (pieza != null) {
            piezasPropias.add(pieza);
        }
    }
	
}
