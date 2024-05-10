package galeria;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import COMPRAYSUBASTA.Oferta;
import COMPRAYSUBASTA.Registro;
import COMPRAYSUBASTA.Subasta;
import CONSIGNACION.Consignacion;
import CONSIGNACION.TipoMotivo;
import PAGOS.Pago;
import PIEZAS.EstadoPiezas;
import PIEZAS.Pieza;
import PIEZAS.PiezaSubasta;
import PIEZAS.PiezaVenta;
import PIEZAS.TipoPieza;
import USUARIOS.Cajero;
import USUARIOS.Comprador;
import USUARIOS.Empleado;
import USUARIOS.Operador;
import USUARIOS.Propietario;

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
					 oferta.getComprador().getCompras().add(pieza);
					 
					 //SE DEBE PROCESAR EL PAGO CUANDO SE REALIZA UNA VENTA
					 Pago pago = new Pago(pieza.getValorFijo(), oferta.getComprador().getMetodopago());
					 Cajero.procesarPago(pago);
					 
					 
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
	 
	 //ESTE METODO PERMITE A UN CAJERO CONSULTAR EL ESTADO DE UNA DE SUS PIEZAS
	 public EstadoPiezas consultarEstadoPiezaPropietario(Propietario propietario, Pieza pieza) throws Exception {
		 
		 if (propietario.getPiezasPropias().contains(pieza)) {
			 return pieza.getEstado();
		 }else {
			 throw new Exception("La pieza no está las piezas del propietario");
		 }
	 }
	 
	 public String consultarInformacionPieza(Pieza pieza, Empleado empleado) {
		 String info = null;
		 
		 info = empleado.getInformacion(pieza);
		 
		 return info;
	 }
	 
	 
	 //NUEVO REQ: SE ENCARGA DE VER LA HISTORIA DE UNA PIEZAS
	 public String historiaDePiezaVendida(Oferta oferta) {
		 String historia = "NO HAY INFORMACIÓN DE LA PIEZA";
		 String comprador = oferta.getComprador().getNombre();
		 PiezaVenta pieza = oferta.getPieza();
		 if (pieza.getEstado().equals(EstadoPiezas.VENDIDA)) {
			 String titulo = pieza.getTitulo();
			 String año = pieza.getAño();
			 String lugar = pieza.getLugarCreacion();
			 String autor = pieza.getAutor();
			 String tipoPieza = pieza.getTipoPieza().toString();
			 Propietario propietarioAntiguo = pieza.getPropietario();
			 String precio = String.valueOf(pieza.getValorFijo());
			 
			 historia = titulo + " es una/un " + tipoPieza + " creada por: " + autor + ", en el año: " + año +
					 ", en: " + lugar + ". Le perteneció orginalmente a: " + propietarioAntiguo.getNombre() + " y fue vendida a: " +
					 comprador + " por: " + precio; 
		 }else {
			 historia = "La pieza no ha sido vendida";
		 }
		 return historia;
	 }
	 
	 //NUEVO REQ: SE ENCARGA DE VER LA HISTORIA DE UN COMPRADOR
	 public String historiaComprador(Comprador comprador) {
		 List<Pieza> compras = comprador.getCompras();
		 Double precioDeLaColeccion = 0.0;
		 String historia = "Las piezas del comprador son: "; 
		 
		 for (Pieza compra : compras) {
			 
			 historia += compra.getTitulo() + ",";	
			 if (compra.toString().equals("PiezaVenta")) {
				 precioDeLaColeccion += compra.getValorFijo();
				 
			 } else {
				 precioDeLaColeccion += comprador.getPoderAdquisitivo();
			 }
		 }
		 System.out.println(historia + ". La coleccion entera tiene un precio de: "+ String.valueOf(precioDeLaColeccion));
		 return historia + ". La coleccion entera tiene un precio de: " + String.valueOf(precioDeLaColeccion);
	 }

}
