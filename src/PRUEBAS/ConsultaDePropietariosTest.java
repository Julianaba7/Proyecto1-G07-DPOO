package PRUEBAS;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import COMPRAYSUBASTA.Oferta;
import CONSIGNACION.Consignacion;
import PAGOS.MetodoPago;
import PIEZAS.Escultura;
import PIEZAS.EstadoPiezas;
import PIEZAS.Pieza;
import PIEZAS.PiezaExhibicion;
import PIEZAS.PiezaVenta;
import PIEZAS.Pintura;
import PIEZAS.TipoPieza;
import USUARIOS.Cajero;
import USUARIOS.Comprador;
import USUARIOS.Propietario;
import galeria.Administrador;
import galeria.Inventario;

public class ConsultaDePropietariosTest {
	
	
	private static Inventario inventario;
	private static Propietario propietario1;
	private static Oferta oferta1;
    private static PiezaVenta pieza1;
    private static PiezaVenta pieza2;
    private static TipoPieza tipoPieza1;
    private static TipoPieza tipoPieza2;
    private static Consignacion consignacion1;
    private static Consignacion consignacion2;
    private static Date date1;
    private static Date date2;
    private static Date date3;
    private static Date date4;
    private static Date date5;
    private static Cajero cajero;
    
    @Before
    public void setUp() throws ParseException {
        // Initialize the administrator
        Administrador administrador = new Administrador("admin", "Admin", "admin", "admin");

        // Initialize the inventory
        inventario = new Inventario(administrador);
        
        //Initialize the owner
        propietario1 = new Propietario("P1", "Propietario 1", "PROP", "PROP");
        
        //Initialize the type of the pieces
        tipoPieza1 = new Pintura(1.5, 2.5, "Acuarela", "Oleo sobre lienzo", "Barroco");
        tipoPieza2 = new Escultura(1.5, 2.5, 3.5, "Bronce", "Tallado");
        
        // Initialize the pieces
        //NOTA: Creamos piezaExhibición para que la prueba de exhibición funcione y y una piezaVenta para que le prueba de bodega funcione
        pieza1 = new PiezaVenta("P1", "1999", "Bogota","Pepito", propietario1, 400.0, tipoPieza1);
        pieza2 = new PiezaVenta("P2", "2000", "Valle", "Pepita", propietario1, 200.0, tipoPieza2);
        
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
        consignacion2 = new Consignacion(pieza2, propietario1, date3, date4);
        
      //Initialize the buyers
        Comprador comprador1 = new Comprador("comprador1", "comprador", "comprador1", "comprador1", 500.0, MetodoPago.EFECTIVO);
        
        //Initialize offer
        oferta1 = new Oferta(pieza1, comprador1);
        
      //Initialize Casher
        cajero = new Cajero("CASH", "CASH", "CASH", "CASH");
    }
    
    @Test
    public void confirmarEstadoPieza() throws Exception {
    	// Test that a piece has been returned
    	propietario1.agregarPieza(pieza1);
    	inventario.consignarPieza(consignacion1);
    	inventario.venderPieza(oferta1);
    	assertTrue(inventario.consultarEstadoPiezaPropietario(propietario1, pieza1).equals(EstadoPiezas.VENDIDA));
    	
    	propietario1.agregarPieza(pieza2);
    	inventario.consignarPieza(consignacion2);
    	inventario.devolverPieza(consignacion2, date5);
    	assertTrue(inventario.consultarEstadoPiezaPropietario(propietario1, pieza2).equals(EstadoPiezas.DEVOLUCION));
    }
}
