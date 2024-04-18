package USUARIOS;

import java.util.ArrayList;
import java.util.List;

import PAGOS.MetodoPago;
import PIEZAS.EstadoPiezas;
import PIEZAS.Pieza;

public class Comprador extends Usuario{
	
	//El administrador debe verificar si el comprador puede comprar una pieza
	private boolean verificadoParaCompra;
	
	//Es la cantidad máxima que está dispuesto a pagar un Comprador por una obra
	private double poderAdquisitivo;
	
	//METODO DE PAGO DE COMPRADOR
	private MetodoPago metodopago;
	
	
	private List<Pieza> compras;
	
	public Comprador(String id, String nombre, String login, String password, double poderAdquisitivo, MetodoPago metodoPago) {
		super(id, nombre, login, password);
		this.rol = RolUsuarios.COMPRADOR;
		this.verificadoParaCompra = false; //Ningun comprador comienza con una verificación para comprar
		this.poderAdquisitivo = poderAdquisitivo;
		this.compras = new ArrayList<Pieza>();
		this.metodopago = metodoPago;
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

	public List<Pieza> getCompras() {
		return compras;
		
	}

	public MetodoPago getMetodopago() {
		return metodopago;
	}


	
	
	
	
}
