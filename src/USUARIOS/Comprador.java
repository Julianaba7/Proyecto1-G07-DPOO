package USUARIOS;

public class Comprador extends Usuario{
	
	//El administrador debe verificar si el comprador puede comprar una pieza
	private boolean verificadoParaCompra;
	
	//Es la cantidad máxima que está dispuesto a pagar un Comprador por una obra
	private double poderAdquisitivo;
	
	public Comprador(String id, String nombre, String login, String password, double poderAdquisitivo) {
		super(id, nombre, login, password);
		this.rol = RolUsuarios.COMPRADOR;
		this.verificadoParaCompra = false; //Ningun comprador comienza con una verificación para comprar
		this.poderAdquisitivo = poderAdquisitivo;
	}

	public boolean isVerificadoParaCompra() {
		return verificadoParaCompra;
	}

	public void setVerificadoParaCompra(boolean verificadoParaCompra) {
		this.verificadoParaCompra = verificadoParaCompra;
	}

	public double getPoderAdquisitivo() {
		return poderAdquisitivo;
	}
	
	
}
