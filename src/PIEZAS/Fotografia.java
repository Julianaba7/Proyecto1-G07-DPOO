package PIEZAS;

public class Fotografia extends TipoPieza{
	
	private double largo;
	
	private double ancho;
	
	private String tecnicaUtilizada;

	public Fotografia(double largo, double ancho, String tecnicaUtilizada) {
		super();
		this.largo = largo;
		this.ancho = ancho;
		this.tecnicaUtilizada = tecnicaUtilizada;
	}
	
	
	
	public double getLargo() {
		return largo;
	}

	public double getAncho() {
		return ancho;
	}


	public String getTecnicaUtilizada() {
		return tecnicaUtilizada;
	}

	@Override
    public String toString() {
		return "Fotografia";
	}
}
