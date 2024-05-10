package PRUEBAS;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import PAGOS.MetodoPago;
import PERSISTENCIA.PersistenciaSerializar;
import USUARIOS.Cajero;
import USUARIOS.Comprador;
import USUARIOS.Propietario;
import USUARIOS.Usuario;
import galeria.Administrador;

public class PersistenciaTest {
	private Propietario propietario;
    private Cajero cajero;
    private Comprador comprador;
    private Administrador admin;

    @Before
    public void setUp() {
        propietario = new Propietario("i", "l", "n", "p");
        cajero = new Cajero("ie1", "le1", "ne1", "pe1");
        comprador = new Comprador("c1", "cc2", "ccc3", "cccc4", 200.0, MetodoPago.EFECTIVO);
        admin = new Administrador("ad", "ad", "ad", "ad");
    }

    @Test
    public void testPersistenciaSerializar() throws IOException {
        PersistenciaSerializar persistenciaPropietario = new PersistenciaSerializar("dataSerializacion/Propietarios.txt", propietario);
        Propietario propietarioDeserializado = PersistenciaSerializar.getPropietarioByID("i");
        assertEquals(propietario.getId(), propietarioDeserializado.getId());

        PersistenciaSerializar persistenciaCajero = new PersistenciaSerializar("dataSerializacion/Empleados.txt", cajero);
        Usuario usuarioDeserializado = PersistenciaSerializar.getEmpleadoByID("ie1");
        assertEquals(cajero.getId(), usuarioDeserializado.getId());

        PersistenciaSerializar persistenciaComprador = new PersistenciaSerializar("dataSerializacion/Compradores.txt", comprador);
        Comprador compradorDeserializado = PersistenciaSerializar.getCompradorByID("c1");
        assertEquals(comprador.getId(), compradorDeserializado.getId());

        PersistenciaSerializar persistenciaAdmin = new PersistenciaSerializar("dataSerializacion/Admin.txt", admin);
        Administrador adminDeserializado = PersistenciaSerializar.getAdministradorByID();
        assertEquals(admin.getId(), adminDeserializado.getId());
    }

}
