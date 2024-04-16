package PIEZAS;

import USUARIOS.Propietario;

public class PiezaVenta extends Pieza{
	
	//El valorFijo es para piezas en venta
	private double valorFijo;
	
	public PiezaVenta(String titulo, String año, String lugarCreacion, String autor,Propietario propietario, double valorFijo, TipoPieza tipoPieza) {
		super(titulo, año, lugarCreacion, autor,  propietario, tipoPieza);
		this.valorFijo = valorFijo;
		this.estado = EstadoPiezas.DISPONIBLE;
		
	}
}
