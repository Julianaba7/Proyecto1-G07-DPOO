package USUARIOS;

public class Comprador extends Usuario{
	
	//El administrador debe verificar si el comprador puede comprar una pieza
	private boolean verificadoParaCompra;
	
	public Comprador(String id, String nombre, String login, String password) {
		super(id, nombre, login, password);
		this.rol = RolUsuarios.COMPRADOR;
		this.verificadoParaCompra = false; //Ningun comprador comienza con una verificación para comprar
	}
}
