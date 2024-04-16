package USUARIOS;

public class Propietario {
	
	private String id;
	
	private String nombre;
	
	private String login;
	
	private String password;

	public Propietario(String id, String nombre, String login, String password) {
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

}
