package CONSIGNACION;

import java.util.Date;
import PIEZAS.Pieza;
import USUARIOS.Propietario;

public class Consignacion {
	
	private final Pieza pieza;
	
	private final Propietario propietario;
	
	private final Date fechaInicio;
	
	private final Date fechaFinal;
	
	
	public Consignacion(Pieza pieza, Propietario propietario, Date fechaInicio, Date fechaFinal) {
		this.pieza = pieza;
		this.propietario = propietario;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
	}


	public Pieza getPieza() {
		return pieza;
	}


	public Propietario getPropietario() {
		return propietario;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public Date getFechaFinal() {
		return fechaFinal;
	}
	
	public boolean consignacionTerminada(Date currentDate) {
	    return currentDate.after(fechaFinal);
	}
}
	

