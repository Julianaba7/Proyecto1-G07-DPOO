package galeria;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import COMPRAYSUBASTA.Oferta;
import COMPRAYSUBASTA.Registro;
import COMPRAYSUBASTA.Subasta;
import CONSIGNACION.Consignacion;
import CONSIGNACION.TipoMotivo;
import PIEZAS.EstadoPiezas;
import PIEZAS.Pieza;
import PIEZAS.PiezaSubasta;
import PIEZAS.PiezaVenta;
import USUARIOS.Comprador;
import USUARIOS.Operador;

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
	 
	//ESTE METODO PERMITE AL INVENTARIO CONFIRMAR QUE SE REALIZÓ UNA VENTA
	 public boolean confirmarVenta(Pieza pieza) {
		 
		 //ES RESPONSABILIDAD DEL ADMINISTRADAOR DE CONFIRMAR LA VENTA
		 boolean respuesta = administrador.confirmarSale(pieza, noDisponible);
		 
		 return respuesta;
	 }
	 
	//ESTE METODO PERMITE AL INVENTARIO CONFIRMAR QUE SE REALIZÓ UNA DEVOLUCION
	 public boolean confirmarDevolucion(Pieza pieza) {
		 
		//ES RESPONSABILIDAD DEL ADMINISTRADAOR DE CONFIRMAR LA DEVOLUCION
		 boolean respuesta = administrador.confirmarReturn(pieza, noDisponible);
		 
		 return respuesta;
		 
	 }
	 
	 //ESTE METODO PERMITE REALIZA UNA VENTA
	 public boolean venderPieza(Oferta oferta) {
		 Boolean retorno = false;
		 PiezaVenta pieza = oferta.getPieza();
		 EstadoPiezas estadoPieza = pieza.getEstado();
		 
		 if (bodega.contains(pieza)) {
		 
			 if(estadoPieza.equals(EstadoPiezas.BLOQUEADA)) {
				 retorno = false;
			 }else if(estadoPieza.equals(EstadoPiezas.DISPONIBLE)) {
				 pieza.setEstado(EstadoPiezas.BLOQUEADA);
				 if(oferta.confirmarVerificacionComprador(administrador)) {
					 pieza.setEstado(EstadoPiezas.VENDIDA);
					 bodega.remove(pieza);
					 noDisponible.add(pieza);
					 retorno = true;
				 }else {
					 pieza.setEstado(EstadoPiezas.DISPONIBLE);
					 retorno = false;
				 }
			 }else {
				 retorno = false;
			 }
		 }else {
			 retorno = false;
		 }return retorno;
	 }
	
	 //ESTE METODO PERMITE REALIZAR UNA SUBASTA
	 public boolean realizarSubasta(Subasta subasta) {
		 
		 Boolean retorno = true;
		 List<PiezaSubasta> piezas= subasta.getPiezas();
		 Operador operador = subasta.getOperador();
		 
		 //Verifica que todas las piezas esten en la bodega
		 for(Pieza pieza: piezas) {
			 if(!bodega.contains(pieza)) {
				 retorno = false;
			 }
		 }
		 
		 if (retorno == true) {
			 List<PiezaSubasta> piezasParaVender = subasta.calcularPiezasParaVender(operador);
			 for (Pieza pieza: piezasParaVender) {
				 pieza.setEstado(EstadoPiezas.VENDIDA);
				 bodega.remove(pieza);
				 noDisponible.add(pieza);
			 	}
	            
		 }
		 
		 return retorno;
	 }
}
