package PRUEBAS;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import COMPRAYSUBASTA.Oferta;
import CONSIGNACION.Consignacion;
import PIEZAS.Escultura;
import PIEZAS.EstadoPiezas;
import PIEZAS.Pieza;
import PIEZAS.PiezaExhibicion;
import PIEZAS.PiezaVenta;
import PIEZAS.Pintura;
import PIEZAS.TipoPieza;
import USUARIOS.Comprador;
import USUARIOS.Propietario;
import galeria.Administrador;
import galeria.Inventario;

public class VentaTest {

	private static Inventario inventario;
	private static PiezaVenta pieza1;
    private static PiezaVenta pieza2;
    private static TipoPieza tipoPieza1;
    private static TipoPieza tipoPieza2;
    private static Consignacion consignacion1;
    private static Consignacion consignacion2;
    private static Oferta oferta1;
    private static Oferta oferta2;
    private static Date date1;
    private static Date date2;
    private static Date date3;
    private static Date date4;
    private static Date date5;
	
	@Before
    public void setUp() throws ParseException {
        // Initialize the administrator
		Administrador administrador = new Administrador("admin", "Admin", "admin", "admin");

        // Initialize the inventory
        inventario = new Inventario(administrador);
        
      //Initialize the owners
        Propietario propietario1 = new Propietario("P1", "Propietario 1", "PROP", "PROP");
        Propietario propietario2 = new Propietario("P2", "Propietario 2", "PROP", "PROP");
        
        //Initialize the type of the pieces
        tipoPieza1 = new Pintura(1.5, 2.5, "Acuarela", "Oleo sobre lienzo", "Barroco");
        tipoPieza2 = new Escultura(1.5, 2.5, 3.5, "Bronce", "Tallado");
        
        // Initialize the pieces
        //NOTA: Creamos piezaExhibición para que la prueba de exhibición funcione y y una piezaVenta para que le prueba de bodega funcione
        pieza1 = new PiezaVenta("P1", "1999", "Bogota","Pepito", propietario1, 400.0, tipoPieza1);
        pieza2 = new PiezaVenta("P2", "2000", "Valle", "Pepita", propietario2, 200.0, tipoPieza2);
        
        //Initialize Data
        String dateString = "12/01/2023";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        date1 = format.parse(dateString);
        
        String dateString1 = "12/12/2023";
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        date2 = format1.parse(dateString1);
        
        String dateString2 = "12/01/2024";
        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
        date3 = format2.parse(dateString2);
        
        String dateString3 = "12/12/2024";
        SimpleDateFormat format3 = new SimpleDateFormat("dd/MM/yyyy");
        date4 = format3.parse(dateString3);
        
        String dateString4 = "12/01/2025";
        SimpleDateFormat format4 = new SimpleDateFormat("dd/MM/yyyy");
        date5 = format4.parse(dateString4);

        // Initialize the consignations
        consignacion1 = new Consignacion(pieza1, propietario1, date1, date2);
        consignacion2 = new Consignacion(pieza2, propietario2, date3, date4);
        
        //Initialize the buyers
        Comprador comprador1 = new Comprador("comprador1", "comprador", "comprador1", "comprador1", 500.0);
        Comprador comprador2 = new Comprador("comprador2", "comprador", "comprador2", "comprador2", 100.0);
        
        //Initialize the offers
        oferta1 = new Oferta(pieza1, comprador1);
        oferta2 =new Oferta(pieza2, comprador2);
	}
	@Test
    public void venderPiezaTest() {
		
		inventario.consignarPieza(consignacion1);
		assertTrue(inventario.venderPieza(oferta1));
		
		//NO SE DEBERIA VENDER LA PIEZA YA QUE EL COMPRADOR 2 TIENE MENOR PODER ADQUISITIVO DE LO QUE VALE LA PIEZA
		inventario.consignarPieza(consignacion2);
		assertFalse(inventario.venderPieza(oferta2));
	}
	
	@Test
    public void confirmarEstadoVendidoTest() {
		
		//CONFIRMA QUE AL TERMINAR LA VENTA EL ESTADO DE LA PIEZA SEA VENDIDA
		inventario.consignarPieza(consignacion1);
		inventario.venderPieza(oferta1);
		assertTrue(pieza1.getEstado().equals(EstadoPiezas.VENDIDA));
		
		//CONFIRMA QUE SI UNA VENTA NO SE REALIZA EL ESTADO SEA DISPONIBLE
		inventario.consignarPieza(consignacion2);
		inventario.venderPieza(oferta2);
		assertTrue(pieza2.getEstado().equals(EstadoPiezas.DISPONIBLE));
	}
	
	@Test
	public void confirmarPiezaNoEnBodegaTest() {
		
		//CONFIRMA QUE AL FINALIZAR UNA VENTA LA PIEZA NO ESTÁ EN BODEGA PERO SI EN LA LISTA DE NODISPONIBLE
		inventario.consignarPieza(consignacion1);
		inventario.venderPieza(oferta1);
		assertFalse(inventario.getBodega().contains(pieza1));
		assertTrue(inventario.getNoDisponible().contains(pieza1));
		
		//CONFIRMA QUE SI NO SE REALIZA UNA VENTA ESTA DEBERIA ESTAR EN BODEGA Y NO EN LA LISTA DE NODISPONIBLES
		inventario.consignarPieza(consignacion2);
		inventario.venderPieza(oferta2);
		assertFalse(inventario.getNoDisponible().contains(pieza2));
		assertTrue(inventario.getBodega().contains(pieza2));
		
	
	}
	
	@After
    public void tearDown() {
        // Clear the lists
        inventario.getBodega().clear();
        inventario.getExhibicion().clear();
        inventario.getNoDisponible().clear();
    }
    
}

