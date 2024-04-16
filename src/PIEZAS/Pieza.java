package PIEZAS;

import USUARIOS.Propietario;

//CLASE PIEZA-ESTA CLASE ES EL PILAR DE LA MAYORIA DEL PROGRAMA
public class Pieza {
	
	protected String titulo;
	
	protected String año;
	
	protected String lugarCreacion;
	
	protected String autor;
	
	
	//COMPRA Y SUBASTA DE PIEZAS: Hay 3 posibles estados:
	//Dispobible: Si la pieza puede ser vendida por un valor fijo
	//Bloqueada: Si la pieza no se puede vender porque el comprador esta en revision
	//Vendida: Si la pieza ya se vendió
	//Exhibida: Si la pieza no es vendida, solo exhibida
	//Subasta: Si la pieza está para subasta
	
	protected EstadoPiezas estado;
	
	//El tipo de pieza de la pieza
	protected TipoPieza tipoPieza;
	
	protected Propietario propietario;
	
    // Constructor para piezas que se entregan para exhibir
    public Pieza(String titulo, String año, String lugarCreacion, String autor, Propietario propietario, TipoPieza tipoPieza) {
    	this.titulo = titulo;
    	this.año = año;
    	this.lugarCreacion = lugarCreacion;
    	this.autor = autor;
    	this.propietario = propietario;
    	this.tipoPieza = tipoPieza;
    }


	public String getTitulo() {
		return titulo;
	}

	public String getAño() {
		return año;
	}

	public String getLugarCreacion() {
		return lugarCreacion;
	}

	public String getAutor() {
		return autor;
	}

	public EstadoPiezas getEstado() {
		return estado;
	}

	public void setEstado(EstadoPiezas estado) {
		this.estado = estado;
	}


	public TipoPieza getTipoPieza() {
		return tipoPieza;
	}

	public Propietario getPropietario() {
		return propietario;
	}

    
}
