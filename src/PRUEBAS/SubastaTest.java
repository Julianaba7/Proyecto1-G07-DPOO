package PRUEBAS;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import COMPRAYSUBASTA.Subasta;
import CONSIGNACION.Consignacion;
import PIEZAS.Escultura;
import PIEZAS.Pieza;
import PIEZAS.PiezaSubasta;
import PIEZAS.PiezaVenta;
import PIEZAS.Pintura;
import PIEZAS.TipoPieza;
import USUARIOS.Comprador;
import USUARIOS.Propietario;
import galeria.Administrador;
import galeria.Inventario;

public class SubastaTest {

	private static Inventario inventario;
    private static List<PiezaSubasta> piezas;
    private static List<Consignacion> consignaciones;
    private static Subasta subasta1;
    private static List<Comprador> compradores;

    @Before
    public void setUp() throws ParseException {
        // Inicializar el administrador
        Administrador administrador = new Administrador("admin", "Admin", "admin", "admin");

        // Inicializar el inventario
        inventario = new Inventario(administrador);
        
        // Inicializar las listas
        piezas = new ArrayList<>();
        consignaciones = new ArrayList<>();
        compradores = new ArrayList<>();

        // Inicializar los propietarios
        Propietario propietario1 = new Propietario("P1", "Propietario 1", "PROP", "PROP");
        Propietario propietario2 = new Propietario("P2", "Propietario 2", "PROP", "PROP");
        Propietario propietario3 = new Propietario("P3", "Propietario 3", "PROP", "PROP");
        Propietario propietario4 = new Propietario("P4", "Propietario 4", "PROP", "PROP");
        Propietario propietario5 = new Propietario("P5", "Propietario 5", "PROP", "PROP");
        Propietario propietario6 = new Propietario("P6", "Propietario 6", "PROP", "PROP");
        
        // Inicializar los tipos de piezas
        TipoPieza tipoPieza1 = new Pintura(1.5, 2.5, "Acuarela", "Oleo sobre lienzo", "Barroco");
        TipoPieza tipoPieza2 = new Escultura(1.5, 2.5, 3.5, "Bronce", "Tallado");
        TipoPieza tipoPieza3 = new Pintura(1.5, 2.5, "Acuarela", "Oleo sobre lienzo", "Barroco");
        TipoPieza tipoPieza4 = new Escultura(1.5, 2.5, 3.5, "Bronce", "Tallado");
        TipoPieza tipoPieza5 = new Pintura(1.5, 2.5, "Acuarela", "Oleo sobre lienzo", "Barroco");
        TipoPieza tipoPieza6 = new Escultura(1.5, 2.5, 3.5, "Bronce", "Tallado");
        
        // Inicializar las fechas
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = format.parse("12/01/2023");
        Date date2 = format.parse("12/12/2023");
        Date date3 = format.parse("12/01/2024");
        Date date4 = format.parse("12/12/2024");
        Date date5 = format.parse("12/01/2025");
        Date date6 = format.parse("12/01/2026");
        Date date7 = format.parse("12/01/2027");
        Date date8 = format.parse("12/01/2028");
        Date date9 = format.parse("12/01/2029");
        Date date10 = format.parse("12/01/2030");
        Date date11 = format.parse("12/01/2031");
        Date date12 = format.parse("12/01/2032");

        // Inicializar las piezas
        piezas.add(new PiezaSubasta("P1", "1999", "Bogota", "Pepito", 200.0, 500.0, propietario1, tipoPieza1));
        piezas.add(new PiezaSubasta("P2", "2000", "Valle", "Pepita", 300.0, 500.0, propietario2, tipoPieza2));
        piezas.add(new PiezaSubasta("P3", "2001", "Cali", "Pepote", 300.0, 600.0, propietario3, tipoPieza3));
        piezas.add(new PiezaSubasta("P4", "2002", "Medellin", "Pepita", 500.0, 1000.0, propietario4, tipoPieza4));
        piezas.add(new PiezaSubasta("P5", "2003", "Barranquilla", "Pepote", 600.0, 800.0, propietario5,  tipoPieza5));
        piezas.add(new PiezaSubasta("P6", "2004", "Cartagena", "Pepito", 400.0, 600.0, propietario6,  tipoPieza6));

        // Inicializar las consignaciones
        consignaciones.add(new Consignacion(piezas.get(0), propietario1, date1, date2));
        consignaciones.add(new Consignacion(piezas.get(1), propietario2, date3, date4));
        consignaciones.add(new Consignacion(piezas.get(2), propietario3, date5, date6));
        consignaciones.add(new Consignacion(piezas.get(3), propietario4, date7, date8));
        consignaciones.add(new Consignacion(piezas.get(4), propietario5, date9, date10));
        consignaciones.add(new Consignacion(piezas.get(5), propietario6, date11, date12));
        
        //Inicializar los compradores
        compradores.add(new Comprador("comprador1", "Comprador 1", "comprador1", "comprador1", 500.0));
        compradores.add(new Comprador("comprador2", "Comprador 2", "comprador2", "comprador2", 100.0));
	    compradores.add(new Comprador("comprador3", "Comprador 3", "comprador3", "comprador3", 700.0));
	    compradores.add(new Comprador("comprador4", "Comprador 4", "comprador4", "comprador4", 200.0));
	    compradores.add(new Comprador("comprador5", "Comprador 5", "comprador5", "comprador5", 1000.0));
	    compradores.add(new Comprador("comprador6", "Comprador 6", "comprador6", "comprador6", 900.0));

        
        //Inicializar las subastas
        subasta1 = new Subasta();
        
        for (PiezaSubasta pieza : piezas) {
        	subasta1.agregarPieza(pieza);
        }
        
        for (Comprador comprador : compradores) {
        	subasta1.agregarComprador(comprador);
        }
        
    }
    
    @Test
	public void realizarSubastaTest() {
    	
    	//SE DEBEN CONSIGNAR LAS PIEZAS
    	for (Consignacion consignacion: consignaciones) {
        	inventario.consignarPieza(consignacion);
        }
    	
    	//LOS COMPRADORES DEBEN ESTAR VERIFICADOS
    	for (Comprador comprador: compradores) {
    		comprador.setVerificadoParaCompra(true);
    	}
    	
    	assertTrue(inventario.realizarSubasta(subasta1));
    }
    
    @Test
    public void verificarBodegaVaciaTest() {
    	//SE DEBEN CONSIGNAR LAS PIEZAS
    	for (Consignacion consignacion: consignaciones) {
        	inventario.consignarPieza(consignacion);
        }
    	
    	//LOS COMPRADORES DEBEN ESTAR VERIFICADOS
    	for (Comprador comprador: compradores) {
    		comprador.setVerificadoParaCompra(true);
    	}
    	
    	inventario.realizarSubasta(subasta1);
    	
    	assertTrue(inventario.getBodega().isEmpty());
    	assertFalse(inventario.getNoDisponible().isEmpty());
    	
    }
    
    
    @After
    public void tearDown() {
        // Clear the lists
        inventario.getBodega().clear();
        inventario.getExhibicion().clear();
        inventario.getNoDisponible().clear();
    }
}
