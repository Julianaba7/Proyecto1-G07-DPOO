package galeria;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import CONSIGNACION.Consignacion;
import CONSIGNACION.TipoMotivo;
import PIEZAS.EstadoPiezas;
import PIEZAS.Pieza;

public class Inventario {
	
	//El inventario tiene BODEGA y EXHIBICION, que son listas de piezas:
	private List<Pieza> bodega;
	
	private List<Pieza> exhibicion;
		
	//El inventario tiene una lista de piezas las cuales se devuelven a los propietario o ya se vendieron.
	private List<Pieza> noDisponible;
	
	//El inventario tiene unq lista de empleados
	private List<Pieza> empleados;
	
	//El inventario tiene un administrador
	private Administrador administrador;
	
	//constructor
	public Inventario(
			Administrador administrador) {
		this.bodega = new ArrayList<Pieza>();
		this.exhibicion = new ArrayList<Pieza>();
		this.noDisponible = new ArrayList<Pieza>();
		this.administrador = administrador;
	}
	
	public List<Pieza> getBodega() {
		return bodega;
	}

	public List<Pieza> getExhibicion() {
		return exhibicion;
	}

	public List<Pieza> getNoDisponible() {
		return noDisponible;
	}

	public List<Pieza> getEmpleados() {
		return empleados;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	//ESTE METODO CONSIGNA UNA PIEZA AL INVENTARIO. 
	//SE LE ASIGNA LA RESPONSABILIDAD AL ADMINISTRADOR SEGÚN EL REQUERIMIENTO DEL PROGRAMA
	 public void consignarPieza(Consignacion consignacion) {
		 try {
				Pieza pieza = consignacion.getPieza();
				administrador.consignar(pieza, bodega, exhibicion);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 //ESTE METODO DEVUELVE UNA PIEZA AL PROPIETARO
	 //LO QUE ES LO MISMO QUE PONERLO EN LA LISTA DE NO.DISPONIBLE
	 public void devolverPieza(Consignacion consignacion, Date currentDate) {
		 try {
				Pieza pieza = consignacion.getPieza();
				if (consignacion.consignacionTerminada(currentDate)) {	
					if (bodega.remove(pieza)) {
						pieza.setEstado(EstadoPiezas.DEVOLUCION);
						noDisponible.add(pieza);
					} else if (exhibicion.remove(pieza)) {
						pieza.setEstado(EstadoPiezas.DEVOLUCION);
						noDisponible.add(pieza);
					} else {
						throw new Exception("La pieza no se encuentra en el inventario");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
