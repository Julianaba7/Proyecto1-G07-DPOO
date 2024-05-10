package PIEZAS;

public class Impresion extends TipoPieza {
	
	private double largo;
	
	private double ancho;
	
	private String tipoImpresion;
	
	private String tipoPapel;

	public Impresion(double largo, double ancho, String tipoImpresion, String tipoPapel) {
		super();
		this.largo = largo;
		this.ancho = ancho;
		this.tipoImpresion = tipoImpresion;
		this.tipoPapel = tipoPapel;
	}
	
	
	
	public double getLargo() {
		return largo;
	}



	public double getAncho() {
		return ancho;
	}



	public String getTipoImpresion() {
		return tipoImpresion;
	}



	public String getTipoPapel() {
		return tipoPapel;
	}



	@Override
    public String toString() {
		return "Impresion";
	}

}
