package PIEZAS;

import USUARIOS.Propietario;

public class PiezaExhibicion extends Pieza{

	//CONSTRUCTOR PIEZASUBASTA
	public PiezaExhibicion(String titulo, String año, String lugarCreacion, String autor, Propietario propietario, TipoPieza tipoPieza) {
		super(titulo, año, lugarCreacion, autor, propietario, tipoPieza);
		this.estado = EstadoPiezas.EXHIBIDA;
	}
}
