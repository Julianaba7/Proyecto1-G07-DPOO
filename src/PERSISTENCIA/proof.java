package PERSISTENCIA;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import PIEZAS.Escultura;
import PIEZAS.Pieza;
import PIEZAS.PiezaExhibicion;
import PIEZAS.PiezaVenta;
import PIEZAS.Pintura;
import PIEZAS.TipoPieza;
import USUARIOS.Propietario;

public class proof {
	
	private static Pieza pieza1;
    private static Pieza pieza2;
    private static TipoPieza tipoPieza1;
    private static TipoPieza tipoPieza2;
    
    @Before
    public void setUp() throws ParseException {
	    Propietario propietario1 = new Propietario("P1", "Propietario 1", "PROP", "PROP");
	    Propietario propietario2 = new Propietario("P2", "Propietario 2", "PROP", "PROP");
	    
	    //Initialize the type of the pieces
	    tipoPieza1 = new Pintura(1.5, 2.5, "Acuarela", "Oleo sobre lienzo", "Barroco");
	    tipoPieza2 = new Escultura(1.5, 2.5, 3.5, "Bronce", "Tallado");
	    
	    // Initialize the pieces
	    //NOTA: Creamos piezaExhibición para que la prueba de exhibición funcione y y una piezaVenta para que le prueba de bodega funcione
	    pieza1 = new PiezaVenta("P1", "1999", "Bogota","Pepito", propietario1,300, tipoPieza1);
	    pieza2 = new PiezaExhibicion("P2", "2000", "Valle", "Pepita", propietario2, tipoPieza2);
    }
    
    @Test
    public void Peristir() throws IOException {
    	PersistenciaSerializar persistenciaPropietario = new PersistenciaSerializar("dataSerializacion/PiezasVenta.txt", pieza1);
        PiezaVenta propietarioDeserializado = PersistenciaSerializar.getPiezaVentaByTitulo("P1");
        assertEquals(pieza1.getTitulo(), propietarioDeserializado.getTitulo());
        
        
        PersistenciaSerializar persistenciaPropietario2 = new PersistenciaSerializar("dataSerializacion/PiezasExhibicion.txt", pieza2);
        PiezaExhibicion propietarioDeserializado2= PersistenciaSerializar.getPiezaExhibicionByTitulo("P2");
        assertEquals(pieza2.getTitulo(), propietarioDeserializado2.getTitulo());
        
    	
    }
}
