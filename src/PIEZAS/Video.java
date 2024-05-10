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
	
	
	
	public double getDuracion() {
		return duracion;
	}



	public String getFormato() {
		return formato;
	}



	public String getResolucion() {
		return resolucion;
	}



	@Override
    public String toString() {
		return "Video";
	}
}
