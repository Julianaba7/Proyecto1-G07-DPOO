package PIEZAS;

public class Pintura extends TipoPieza{
	
	private double alto;
	
	private double ancho;
	
	private String tipoPintura;
	
	private String tecnicaUtilizada;
	
	private String estilo;
	
	public Pintura(double alto, double ancho, String tipoPintura, String tecnicaUtilizada, String estilo) {
		super();
		this.alto = alto;
		this.ancho = ancho;
		this.tipoPintura = tipoPintura;
		this.tecnicaUtilizada= tecnicaUtilizada;
		this.estilo = estilo;
	}
}
