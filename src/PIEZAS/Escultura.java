package PIEZAS;

public class Escultura extends TipoPieza{
	
	private double alto;
	
	private double ancho;
	
	private double profundidad;
	
	private String material;
	
	private String tecnicaUtilizada;

	public Escultura(double alto, double ancho, double profundidad, String material, String tecnicaUtilizada) {
		super();
		this.alto = alto;
		this.ancho = ancho;
		this.profundidad = profundidad;
		this.material = material;
		this.tecnicaUtilizada = tecnicaUtilizada;
	}
	
	
}
