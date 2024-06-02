package PASARELAS;

public class InfoTarjeta {
	
	private String numeroTarjeta;
    private String nombreTitular;
    private String fechaExpiracion;
    private String codigoSeguridad;
    
	public InfoTarjeta(String numeroTarjeta, String nombreTitular, String fechaExpiracion, String codigoSeguridad) {
		this.numeroTarjeta = numeroTarjeta;
		this.nombreTitular = nombreTitular;
		this.fechaExpiracion = fechaExpiracion;
		this.codigoSeguridad = codigoSeguridad;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public String getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setCodigoSeguridad(String codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}
    
	
    
}
