package COMPRAYSUBASTA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import PAGOS.Pago;
import PIEZAS.Pieza;
import PIEZAS.PiezaSubasta;
import USUARIOS.Cajero;
import USUARIOS.Comprador;
import USUARIOS.Operador;
import galeria.Administrador;

public class Subasta {
	
	private List<PiezaSubasta> piezas;//Una subasta tiene una lista de piezas a subastar
	
	private List<Comprador> compradores; //Una subasta tiene una lista de compradores
	
	private Operador operador; //Una subasta tiene un usuario operador que la administra
	
	public Subasta(Operador operador) {
		this.piezas = new ArrayList<PiezaSubasta>();
		this.compradores = new ArrayList<Comprador>();
		this.operador = operador;
	}

	public List<PiezaSubasta> getPiezas() {
		return piezas;
	}

	public List<Comprador> getCompradores() {
		return compradores;
	}
	
	public Operador getOperador() {
		return operador;
	}
	
	public void agregarPieza(PiezaSubasta pieza) {
		piezas.add(pieza);
	}
	
	public void agregarComprador(Comprador comprador) {
		compradores.add(comprador);
	}
	
	
	public List<PiezaSubasta> calcularPiezasParaVender(Operador operador) {
		
		List<PiezaSubasta> piezasParaVender = new ArrayList<PiezaSubasta>();
	
		
		for (PiezaSubasta pieza : piezas) {//Recorre las piezas
	        double ofertaMaxima = 0; // 
	        Comprador mejorComprador = null;
	        
	        for (Comprador comprador : compradores) {//Recorremos compradores
	        	if (comprador.isVerificadoParaCompra()) {//Verifica si el comprador esta verificado con anterioridad
	        		double ofertaComprador = comprador.getPoderAdquisitivo();
	        		
	        		if (ofertaComprador >= pieza.getValorInicial()) {//Verifica si la oferta del comprador es mayor o igual al valor inicial de la pieza
	        			if (ofertaComprador > ofertaMaxima) {
	        				ofertaMaxima = ofertaComprador;
	        				mejorComprador = comprador;
	        			}
	        		}
	        	}
	        }
	        if (mejorComprador != null && ofertaMaxima >= pieza.getValorMinimo()) {
	        	Registro registro = new Registro(pieza, mejorComprador);
	        	operador.añadirRegistro(registro);	  
	        	mejorComprador.getCompras().add(pieza);
	        	
	        	Pago pago = new Pago(mejorComprador.getPoderAdquisitivo(), mejorComprador.getMetodopago());
				Cajero.procesarPago(pago);
	        	
	        	piezasParaVender.add(pieza);
	        }
		}
		return piezasParaVender;
			
		
	}

}
