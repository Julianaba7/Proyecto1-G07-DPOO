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
	
	
	
	// Método toString() sobrescrito
    public double getAlto() {
		return alto;
	}
	public double getAncho() {
		return ancho;
	}
	public double getProfundidad() {
		return profundidad;
	}
	public String getMaterial() {
		return material;
	}
	public String getTecnicaUtilizada() {
		return tecnicaUtilizada;
	}

	@Override
    public String toString() {
		return "Escultura";
	}
}
