package PIEZAS;

public class Video extends TipoPieza{
	
	private double duracion;
	
	private String formato;
	
	private String resolucion;

	public Video(double duracion, String formato, String resolucion) {
		super();
		this.duracion = duracion;
		this.formato = formato;
		this.resolucion = resolucion;
	}
	
	
}
