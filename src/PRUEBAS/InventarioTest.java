package PRUEBAS;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import CONSIGNACION.Consignacion;
import PIEZAS.Escultura;
import PIEZAS.EstadoPiezas;
import PIEZAS.Pieza;
import PIEZAS.PiezaExhibicion;
import PIEZAS.PiezaVenta;
import PIEZAS.Pintura;
import PIEZAS.TipoPieza;
import USUARIOS.Propietario;
import galeria.Administrador;
import galeria.Inventario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InventarioTest {

    private static Inventario inventario;
    private static Pieza pieza1;
    private static Pieza pieza2;
    private static TipoPieza tipoPieza1;
    private static TipoPieza tipoPieza2;
    private static Consignacion consignacion1;
    private static Consignacion consignacion2;
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
        
        //Initialize the owner
        Propietario propietario1 = new Propietario("P1", "Propietario 1", "PROP", "PROP");
        Propietario propietario2 = new Propietario("P2", "Propietario 2", "PROP", "PROP");
        
        //Initialize the type of the pieces
        tipoPieza1 = new Pintura(1.5, 2.5, "Acuarela", "Oleo sobre lienzo", "Barroco");
        tipoPieza2 = new Escultura(1.5, 2.5, 3.5, "Bronce", "Tallado");
        
        // Initialize the pieces
        //NOTA: Creamos piezaExhibición para que la prueba de exhibición funcione y y una piezaVenta para que le prueba de bodega funcione
        pieza1 = new PiezaExhibicion("P1", "1999", "Bogota","Pepito", propietario1, tipoPieza1);
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
    }

    @Test
    public void consignarPiezaTest() {
        // Test that the piece is added to the correct list
        inventario.consignarPieza(consignacion1);
        assertTrue(inventario.getExhibicion().contains(pieza1));
        
        
        inventario.consignarPieza(consignacion2);
        assertTrue(inventario.getBodega().contains(pieza2));
    }

    @Test
    public void devolverPiezaTest() {
        // Test that the piece is removed from the list and added to the noDisponible list
    	//NOTA: aquí en devolverPieza se utiliza date 3 y date 5, ya que son fechas despues de fate 1 y date 4 respectivamente
        inventario.consignarPieza(consignacion1);
        inventario.devolverPieza(consignacion1, date3);
        assertFalse(inventario.getExhibicion().contains(pieza1));
        assertTrue(inventario.getNoDisponible().contains(pieza1));

        inventario.consignarPieza(consignacion2);
        inventario.devolverPieza(consignacion2, date5);
        assertFalse(inventario.getBodega().contains(pieza2));
        assertTrue(inventario.getNoDisponible().contains(pieza2));
    
    
        
    }
    @Test
    public void confirmarEstadoDevueltoTest() {
    	//Test that the piece has the DEVOLUCION state after it is removed from the list and added to the noDisponible List
    	inventario.consignarPieza(consignacion1);
    	inventario.devolverPieza(consignacion1, date3);
    	assertTrue(pieza1.getEstado().equals(EstadoPiezas.DEVOLUCION));
    	
    	inventario.consignarPieza(consignacion2);
    	inventario.devolverPieza(consignacion2, date5);
    	assertTrue(pieza2.getEstado().equals(EstadoPiezas.DEVOLUCION));
    }

    @Test
    public void devolverPiezaNotInInventoryTest() {
        // Test that an exception is thrown if the piece is not in the inventory
        assertThrows(IllegalArgumentException.class, () -> inventario.devolverPieza(consignacion1, date1));
    }
    
    //@Test
    //public void confirmarVentaTest() {
    	// Test that a piece has been sold
    	//TODO TODO TODO TODO TODO
    	//NO ESTA BIEN IMPLEMENTADO
    	//inventario.consignarPieza(consignacion1);
    	//inventario.devolverPieza(consignacion1, date3);
    	//assertTrue(inventario.confirmarVenta(pieza1));
    	
    	//inventario.consignarPieza(consignacion2);
    	//inventario.devolverPieza(consignacion2, date5);
    	//assertTrue(inventario.confirmarVenta(pieza2));
    //}
    
    @Test
    public void confirmarDevolucionTest() {
    	// Test that a piece has been returned
    	inventario.consignarPieza(consignacion1);
    	inventario.devolverPieza(consignacion1, date3);
    	assertTrue(inventario.confirmarDevolucion(pieza1));
    	
    	inventario.consignarPieza(consignacion2);
    	inventario.devolverPieza(consignacion2, date5);
    	assertTrue(inventario.confirmarDevolucion(pieza2));
    }
    
    @Test
    public void consignacionTerminadaTest() {
        // Test that the consignacionTerminada method returns true if the current date is after the consignment end date
        assertTrue(consignacion1.consignacionTerminada(date3));
        assertFalse(consignacion2.consignacionTerminada(date1));
    }

    @After
    public void tearDown() {
        // Clear the lists
        inventario.getBodega().clear();
        inventario.getExhibicion().clear();
        inventario.getNoDisponible().clear();
    }
    
}
