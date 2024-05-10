package PIEZAS;

import USUARIOS.Propietario;

public class PiezaSubasta extends Pieza{
	
	//El valor Minimo solo es para piezas en subasta
	private double valorMinimo;
	
	//El valor Inicial solo es para piezas en subasta
	private double valorInicial;
	
	//CONSTRUCTOR PIEZASUBASTA
	public PiezaSubasta(String titulo, String año, String lugarCreacion, String autor, double valorMinimo, double valorInicial, Propietario propietario, TipoPieza tipoPieza) {
		super(titulo, año, lugarCreacion, autor, propietario, tipoPieza);
		this.valorInicial = valorInicial;
		this.valorMinimo = valorMinimo;
		this.estado = EstadoPiezas.SUBASTA;
	}

	public double getValorMinimo() {
		return valorMinimo;
	}

	public double getValorInicial() {
		return valorInicial;
	}

	public double getValorFijo() {
		return 0.0;
	}
	
	@Override
    public String toString() {
		return "PiezaSubasta";
	}
	
}




