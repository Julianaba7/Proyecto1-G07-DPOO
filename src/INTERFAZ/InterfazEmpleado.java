package INTERFAZ;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Random;
import java.util.Scanner;

import COMPRAYSUBASTA.Oferta;
import COMPRAYSUBASTA.Registro;
import PAGOS.MetodoPago;
import PAGOS.Pago;
import PERSISTENCIA.PersistenciaSerializar;
import PIEZAS.Escultura;
import PIEZAS.Fotografia;
import PIEZAS.Impresion;
import PIEZAS.Pieza;
import PIEZAS.PiezaExhibicion;
import PIEZAS.PiezaSubasta;
import PIEZAS.PiezaVenta;
import PIEZAS.Pintura;
import PIEZAS.Video;
import USUARIOS.Cajero;
import USUARIOS.Comprador;
import USUARIOS.Empleado;
import USUARIOS.Operador;
import USUARIOS.Propietario;
import USUARIOS.Usuario;
import galeria.Administrador;
import galeria.Inventario;

public class InterfazEmpleado {
	
	private static Scanner scanner = new Scanner(System.in);
	
	private static Inventario inventario;
	
	private static Pieza pieza;
	
	private static PiezaSubasta piezaS;
	
	private static PiezaVenta piezaVenta;
	
	private static Empleado empleado;
	
	private static Operador operador ;
	
	private static Cajero cajero;
	
	private static MetodoPago metp;
	
	
public InterfazEmpleado() {
	
}

public static void menuInicio() throws ParseException, IOException {
	boolean salir = false;
	while(!salir) {
		System.out.println("1. Registrarse");
		System.out.println("2. Iniciar sesión");
		System.out.println("3. Salir");
		System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        
        if(opcion == 1) {
        	registrarEmpleado();
        }
        else if (opcion == 2) {
        	logIn();
        }
        else if (opcion == 3) {
        	System.out.println("Ha salido del sistema.");
        	salir = true;
        	
        }
	}
}

public static void registrarEmpleado() throws ParseException, IOException {
	
	//String id, String nombre, String login, String password
	
	Random r = new Random();
	
	System.out.println("Ingrese su nombre: ");
	String nombre = scanner.next();
	
	System.out.println("Ingrese su usuario: ");
	String usuario = scanner.next();
	
	System.out.println("Ingrese su contraseña: ");
	String contraseña = scanner.next();
	
	System.out.println("Ingrese su rol (Empleado, Operador, Cajero): ");
	String rol = scanner.next();
	
	String id = new BigInteger(50, r).toString(32); 
	System.out.println("Su id es: " + id);
	
	if (rol.toLowerCase().equals("empleado")){
		
		empleado = new Empleado(id, nombre, usuario, contraseña);
		
	}else if (rol.toLowerCase().equals("operador")) {
		
		operador = new Operador(id, nombre, usuario, contraseña);
		
	}else if (rol.toLowerCase().equals("cajero")) {
		
		cajero = new Cajero(id, nombre, usuario, contraseña);
		
	}
	
	System.out.println("Se ha registrado con exito");
	//System.out.println("Escriba 'si' si desea regresar al menu principal");
	
	menuInicio();
}

public static void logIn() throws IOException {
	
	System.out.println("Ingrese su usuario: ");
	String usuario = scanner.next();
	
	System.out.println("Ingrese su contraseña: ");
	String contraseña = scanner.next();
	
	System.out.println("Ingrese su rol (Empleado, Operador, Cajero): ");
	String rol = scanner.next();
	
	if (rol.toLowerCase().equals("empleado")) {
		if (empleado.getLogin().equals(usuario) && empleado.getPassword().equals(contraseña)) {
			menuEmpleado();
		}
	}else if (rol.toLowerCase().equals("operador")) {
		if (operador.getLogin().equals(usuario) && operador.getPassword().equals(contraseña)) {
			menuOperador();
		}
	}else if (rol.toLowerCase().equals("cajero")) {
		if (cajero.getLogin().equals(usuario) && cajero.getPassword().equals(contraseña)) {
			menuCajero();
		}
	}
}
	
public static void menuEmpleado() throws IOException {
	
	boolean salir = false;
	while (!salir) {
		System.out.println("1. Consultar historia de una pieza");
		System.out.println("2. Consultar historia de un artista");
		System.out.print("Seleccione una opción: ");
        int opcion = Integer.parseInt(scanner.next());
        
        if (opcion == 1) {
        	
        	System.out.println("Ingrese el titulo de la pieza: ");
    		String titulo = scanner.next();
    		
    		System.out.println("Ingrese el año de creación de la pieza: ");
    		String año = scanner.next();
    		
    		System.out.println("Ingrese el lugar de creación de la pieza: ");
    		String lugarCreacion = scanner.next();
    		
    		System.out.println("Ingrese el autor de la pieza: ");
    		String autor = scanner.next();
    		
    		System.out.println("Ingrese el id del propietario de la pieza: ");
    		String idP = scanner.next();
    		
    		Propietario propietario = PersistenciaSerializar.getPropietarioByID(idP);
    		
    		System.out.println("Ingrese el ID del comprador: ");
    		String id= scanner.next();
    		
    		Comprador comprador = PersistenciaSerializar.getCompradorByID(id);
    		
    		//PIEZA
    		
    		System.out.println("Ingrese el tipo de pieza(pintura, fotografia, imperesion, video, escultura): ");
    		String tipo =scanner.next(); 
    		
    		if (tipo.toLowerCase().equals("pintura") ) {
    			
    			System.out.println("Ingrese el alto de la pintura: ");
        		double alto =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la pintura: ");
        		double ancho =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese la tecnica utilizada: ");
        		String tecnica =  scanner.next();
        		
        		System.out.println("Ingrese el estilo de la pintura: ");
        		String estilo =  scanner.next();
        		
        		System.out.println("Ingrese el tipo de pintura: ");
        		String tipoPintura =  scanner.next();
        		
        		
        		//pintura es de TipoPieza
        		Pintura pintura = new Pintura (alto, ancho, tipoPintura, tecnica, estilo);
        		
        		System.out.println("Ingrese el valor: ");
        		Double valorFijo = Double.parseDouble(scanner.next());
        		piezaVenta = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, pintura);
        		
        		
    		}
    		else if (tipo.toLowerCase().equals("fotografia")) {
    			
    			System.out.println("Ingrese el largo de la fotografia: ");
        		double largo =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la fotografia: ");
        		double ancho =  Double.parseDouble(scanner.next());
    			
        		System.out.println("Ingrese la tecnica utilizada: ");
        		String tecnica =  scanner.next();

        		
        		Fotografia fotografia = new Fotografia(largo, ancho, tecnica);
        		System.out.println("Ingrese el valor: ");
        		Double valorFijo = Double.parseDouble(scanner.next());
        		piezaVenta = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, fotografia);
        		
    		}
    		
    		else if (tipo.toLowerCase().equals("impresion")) {
    			
    			System.out.println("Ingrese el largo de la impresión: ");
        		double largo =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la impresión: ");
        		double ancho =  Double.parseDouble(scanner.next());
    			
        		System.out.println("Ingrese el tipo de impresión: ");
        		String tipoI =  scanner.next();
        		
        		System.out.println("Ingrese el tipo de papel: ");
        		String tipoP =  scanner.next();
        		
        		
        		Impresion impresion = new Impresion(largo, ancho, tipoI, tipoP);
        		
        		System.out.println("Ingrese el valor: ");
        		Double valorFijo = Double.parseDouble(scanner.next());
        		piezaVenta = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, impresion);
    		}
    		
    		else if (tipo.toLowerCase().equals("video")) {
    			
    			System.out.println("Ingrese la duración del video(en minutos): ");
        		double duracion =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el formato del video: ");
        		String formato =  scanner.next();
        		
        		System.out.println("Ingrese la resolución del video: ");
        		String resolucion =  scanner.next();
        		
        		Video video = new Video(duracion, formato, resolucion);
        		
        		System.out.println("Ingrese el valor: ");
        		Double valorFijo = Double.parseDouble(scanner.next());
        		piezaVenta = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, video);
    		}
    		
    		else if (tipo.toLowerCase().equals("escultura")) {
    			
    			System.out.println("Ingrese el alto de la escultura: ");
        		double alto =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la escultura: ");
        		double ancho =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese la profundidad de la escultura: ");
        		double profundidad =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el material de la escultura: ");
        		String material =  scanner.next();
        		
        		System.out.println("Ingrese la tecnica utilizada: ");
        		String tecnica =  scanner.next();
        	
        		
        		Escultura escultura = new Escultura(alto, ancho, profundidad, material, tecnica);
    			
        		System.out.println("Ingrese el valor: ");
        		Double valorFijo = Double.parseDouble(scanner.next());
        		piezaVenta = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, escultura);
    		}
    		
    		Oferta oferta = new Oferta(piezaVenta, comprador);
    		inventario.historiaDePiezaVendida(oferta);
    		System.out.println("Seleccione una opción" );
    		System.out.println("1. Volver al menu del empleado" );
    		System.out.println("2. Salir de la aplicación" );
    		int opcion2 = Integer.parseInt(scanner.next());
    		
    		if (opcion2 == 1) {
    			menuEmpleado();
    		}
    		
    		else if (opcion2 == 2) {
    			salir = true;
    		}	
        	
        }
        
        else if (opcion == 2) {
        	
        }
	}
	
	
	
}

public static void menuOperador() throws IOException {
	
	boolean salir = false;
	while(!salir) {
		
		System.out.println("1. Añadir registro subasta");
		System.out.println("2. Consultar historia de una pieza");
		System.out.println("3. Consultar historia de un artista");
		System.out.print("Seleccione una opción: ");
	    int opcion = Integer.parseInt(scanner.next());
	    
	    if (opcion == 1) {
	    	
	    	System.out.println("Ingreese el ID del comprador: ");
    		String idComp =  scanner.next();
    		
    		Comprador comprador = PersistenciaSerializar.getCompradorByID(idComp);
    		
    		//info pieza
        	System.out.println("Ingrese el titulo de la pieza: ");
    		String titulo = scanner.next();
    		
    		System.out.println("Ingrese el año de creación de la pieza: ");
    		String año = scanner.next();
    		
    		System.out.println("Ingrese el lugar de creación de la pieza: ");
    		String lugarCreacion = scanner.next();
    		
    		System.out.println("Ingrese el autor de la pieza: ");
    		String autor = scanner.next();
    		
    		System.out.println("Ingrese el id del propietario de la pieza: ");
    		String id = scanner.next();
    		
    		Propietario propietario = PersistenciaSerializar.getPropietarioByID(id);
    		
    		System.out.println("Ingrese el tipo de pieza(pintura, fotografia, imperesion, video, escultura): ");
    		String tipo =scanner.next(); 
    		
    		if (tipo.toLowerCase().equals("pintura") ) {
    			
    			System.out.println("Ingrese el alto de la pintura: ");
        		double alto =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la pintura: ");
        		double ancho =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese la tecnica utilizada: ");
        		String tecnica =  scanner.next();
        		
        		System.out.println("Ingrese el estilo de la pintura: ");
        		String estilo =  scanner.next();
        		
        		System.out.println("Ingrese el tipo de pintura: ");
        		String tipoPintura =  scanner.next();
        		
        		//pintura es de TipoPieza
        		Pintura pintura = new Pintura (alto, ancho, tipoPintura, tecnica, estilo);
        		//crea la pieza
        			System.out.println("Ingrese el valor inicial: ");
        			Double valorInicial = Double.parseDouble(scanner.next());
        			System.out.println("Ingrese el valor minimo: ");
        			Double valorMinimo = Double.parseDouble(scanner.next());
        			piezaS = new PiezaSubasta(titulo, año, lugarCreacion, autor, valorMinimo, valorInicial, propietario, pintura);
        			
    		}
    		
    		else if (tipo.toLowerCase().equals("fotografia")) {
    			
    			System.out.println("Ingrese el largo de la fotografia: ");
        		double largo =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la fotografia: ");
        		double ancho =  Double.parseDouble(scanner.next());
    			
        		System.out.println("Ingrese la tecnica utilizada: ");
        		String tecnica =  scanner.next();
        		
        		
        		Fotografia fotografia = new Fotografia(largo, ancho, tecnica);
        		
        		//crea la pieza
        			System.out.println("Ingrese el valor inicial: ");
        			Double valorInicial = Double.parseDouble(scanner.next());
        			System.out.println("Ingrese el valor minimo: ");
        			Double valorMinimo = Double.parseDouble(scanner.next());
        			piezaS = new PiezaSubasta(titulo, año, lugarCreacion, autor, valorMinimo, valorInicial, propietario, fotografia);
        			
        		
    		}
    		
    		else if (tipo.toLowerCase().equals("impresion")) {
    			
    			System.out.println("Ingrese el largo de la impresión: ");
        		double largo =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la impresión: ");
        		double ancho =  Double.parseDouble(scanner.next());
    			
        		System.out.println("Ingrese el tipo de impresión: ");
        		String tipoI =  scanner.next();
        		
        		System.out.println("Ingrese el tipo de papel: ");
        		String tipoP =  scanner.next();
        		
        		System.out.println("¿Desea consignar la pieza para exhibir, vender o subastar? (Exhibir, Vender, Subastar)");
        		String motivoPieza = scanner.next();
        		
        		Impresion impresion = new Impresion(largo, ancho, tipoI, tipoP);
        		
        		System.out.println("Ingrese el valor inicial: ");
    			Double valorInicial = Double.parseDouble(scanner.next());
    			System.out.println("Ingrese el valor minimo: ");
    			Double valorMinimo = Double.parseDouble(scanner.next());
    			piezaS = new PiezaSubasta(titulo, año, lugarCreacion, autor, valorMinimo, valorInicial, propietario, impresion);
    		}
    		
    		else if (tipo.toLowerCase().equals("video")) {
    			
    			System.out.println("Ingrese la duración del video(en minutos): ");
        		double duracion =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el formato del video: ");
        		String formato =  scanner.next();
        		
        		System.out.println("Ingrese la resolución del video: ");
        		String resolucion =  scanner.next();
        		
        		System.out.println("¿Desea consignar la pieza para exhibir, vender o subastar? (Exhibir, Vender, Subastar)");
        		String motivoPieza = scanner.next();
        		
        		Video video = new Video(duracion, formato, resolucion);
        		
        		//crea la pieza
        		
        			System.out.println("Ingrese el valor inicial: ");
        			Double valorInicial = Double.parseDouble(scanner.next());
        			System.out.println("Ingrese el valor minimo: ");
        			Double valorMinimo = Double.parseDouble(scanner.next());
        			piezaS = new PiezaSubasta(titulo, año, lugarCreacion, autor, valorMinimo, valorInicial, propietario, video);
        			
        		
    		}
    		
    		else if (tipo.toLowerCase().equals("escultura")) {
    			
    			System.out.println("Ingrese el alto de la escultura: ");
        		double alto =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la escultura: ");
        		double ancho =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese la profundidad de la escultura: ");
        		double profundidad =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el material de la escultura: ");
        		String material =  scanner.next();
        		
        		System.out.println("Ingrese la tecnica utilizada: ");
        		String tecnica =  scanner.next();
        		
        		System.out.println("¿Desea consignar la pieza para exhibir, vender o subastar? (Exhibir, Vender, Subastar)");
        		String motivoPieza = scanner.next();
        		
        		Escultura escultura = new Escultura(alto, ancho, profundidad, material, tecnica);
    			
        		//crea la pieza
        		
        			System.out.println("Ingrese el valor inicial: ");
        			Double valorInicial = Double.parseDouble(scanner.next());
        			System.out.println("Ingrese el valor minimo: ");
        			Double valorMinimo = Double.parseDouble(scanner.next());
        			piezaS = new PiezaSubasta(titulo, año, lugarCreacion, autor, valorMinimo, valorInicial, propietario, escultura);
        			
        		
    		}
    		
    		Registro registro = new Registro(piezaS, comprador);
    		
    		operador.añadirRegistro(registro);
    		
    		System.out.println("Se ha realizado el registro con exito!" );
    		System.out.println("1. Volver al menu del operador" );
    		System.out.println("2. Salir de la aplicación" );
    		int opcion2 = Integer.parseInt(scanner.next());
    		
    		if (opcion2 == 1) {
    			menuOperador();
    		}
    		
    		else if (opcion2 == 2) {
    			salir = true;
    		}	
	    	
	    }
	    
	    else if (opcion == 2) {
	    	
	    	System.out.println("Ingrese el titulo de la pieza: ");
    		String titulo = scanner.next();
    		
    		System.out.println("Ingrese el año de creación de la pieza: ");
    		String año = scanner.next();
    		
    		System.out.println("Ingrese el lugar de creación de la pieza: ");
    		String lugarCreacion = scanner.next();
    		
    		System.out.println("Ingrese el autor de la pieza: ");
    		String autor = scanner.next();
    		
    		System.out.println("Ingrese el id del propietario de la pieza: ");
    		String idP = scanner.next();
    		
    		Propietario propietario = PersistenciaSerializar.getPropietarioByID(idP);
    		
    		System.out.println("Ingrese el ID del comprador: ");
    		String id= scanner.next();
    		
    		Comprador comprador = PersistenciaSerializar.getCompradorByID(id);
    		
    		//PIEZA
    		
    		System.out.println("Ingrese el tipo de pieza(pintura, fotografia, imperesion, video, escultura): ");
    		String tipo =scanner.next(); 
    		
    		if (tipo.toLowerCase().equals("pintura") ) {
    			
    			System.out.println("Ingrese el alto de la pintura: ");
        		double alto =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la pintura: ");
        		double ancho =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese la tecnica utilizada: ");
        		String tecnica =  scanner.next();
        		
        		System.out.println("Ingrese el estilo de la pintura: ");
        		String estilo =  scanner.next();
        		
        		System.out.println("Ingrese el tipo de pintura: ");
        		String tipoPintura =  scanner.next();
        		
        		
        		//pintura es de TipoPieza
        		Pintura pintura = new Pintura (alto, ancho, tipoPintura, tecnica, estilo);
        		
        		System.out.println("Ingrese el valor: ");
        		Double valorFijo = Double.parseDouble(scanner.next());
        		piezaVenta = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, pintura);
        		
        		
    		}
    		else if (tipo.toLowerCase().equals("fotografia")) {
    			
    			System.out.println("Ingrese el largo de la fotografia: ");
        		double largo =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la fotografia: ");
        		double ancho =  Double.parseDouble(scanner.next());
    			
        		System.out.println("Ingrese la tecnica utilizada: ");
        		String tecnica =  scanner.next();

        		
        		Fotografia fotografia = new Fotografia(largo, ancho, tecnica);
        		System.out.println("Ingrese el valor: ");
        		Double valorFijo = Double.parseDouble(scanner.next());
        		piezaVenta = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, fotografia);
        		
    		}
    		
    		else if (tipo.toLowerCase().equals("impresion")) {
    			
    			System.out.println("Ingrese el largo de la impresión: ");
        		double largo =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la impresión: ");
        		double ancho =  Double.parseDouble(scanner.next());
    			
        		System.out.println("Ingrese el tipo de impresión: ");
        		String tipoI =  scanner.next();
        		
        		System.out.println("Ingrese el tipo de papel: ");
        		String tipoP =  scanner.next();
        		
        		
        		Impresion impresion = new Impresion(largo, ancho, tipoI, tipoP);
        		
        		System.out.println("Ingrese el valor: ");
        		Double valorFijo = Double.parseDouble(scanner.next());
        		piezaVenta = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, impresion);
    		}
    		
    		else if (tipo.toLowerCase().equals("video")) {
    			
    			System.out.println("Ingrese la duración del video(en minutos): ");
        		double duracion =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el formato del video: ");
        		String formato =  scanner.next();
        		
        		System.out.println("Ingrese la resolución del video: ");
        		String resolucion =  scanner.next();
        		
        		Video video = new Video(duracion, formato, resolucion);
        		
        		System.out.println("Ingrese el valor: ");
        		Double valorFijo = Double.parseDouble(scanner.next());
        		piezaVenta = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, video);
    		}
    		
    		else if (tipo.toLowerCase().equals("escultura")) {
    			
    			System.out.println("Ingrese el alto de la escultura: ");
        		double alto =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la escultura: ");
        		double ancho =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese la profundidad de la escultura: ");
        		double profundidad =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el material de la escultura: ");
        		String material =  scanner.next();
        		
        		System.out.println("Ingrese la tecnica utilizada: ");
        		String tecnica =  scanner.next();
        	
        		
        		Escultura escultura = new Escultura(alto, ancho, profundidad, material, tecnica);
    			
        		System.out.println("Ingrese el valor: ");
        		Double valorFijo = Double.parseDouble(scanner.next());
        		piezaVenta = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, escultura);
    		}
    		
    		Oferta oferta = new Oferta(piezaVenta, comprador);
    		inventario.historiaDePiezaVendida(oferta);
    		System.out.println("Seleccione una opción" );
    		System.out.println("1. Volver al menu del operador" );
    		System.out.println("2. Salir de la aplicación" );
    		int opcion2 = Integer.parseInt(scanner.next());
    		
    		if (opcion2 == 1) {
    			menuOperador();
    		}
    		
    		else if (opcion2 == 2) {
    			salir = true;
    		}	
	    	
	    }
	    
	    else if (opcion == 3) {
	    	
	    }	
	}
}

public static void menuCajero() throws IOException {
	
	boolean salir = false;
	while(!salir) {
		
		System.out.println("1. Procesar pago");
		System.out.println("2. Consultar historia de una pieza");
		System.out.println("3. Consultar historia de un artista");
		System.out.print("Seleccione una opción: ");
	    int opcion = Integer.parseInt(scanner.next());
	    
	    if (opcion == 1) {
	    	
	    	System.out.println("Ingrese el monto del pago: ");
    		double monto =  Double.parseDouble(scanner.next());
    		
    		System.out.println("Ingrese el metodo de pago(Trajeta de credito, tramsferencia, efectivo): ");
    		String metodo =  scanner.next();
    		
    		if (metodo.toLowerCase().equals("tarjeta de credito")){
    			
    			MetodoPago metp = MetodoPago.TARJETADECREDITO;
    			
    		}else if (metodo.toLowerCase().equals("transferencia")) {
    			
    			MetodoPago metp = MetodoPago.TRANSFERENCIA;
        		
    		}else if (metodo.toLowerCase().equals("efectivo")) {
    			
    			MetodoPago metp = MetodoPago.EFECTIVO;
        		
    		}
    		Pago pago = new Pago(monto, metp);
    				
    		cajero.procesarPago(pago);
    		
    		System.out.println("Seleccione una opción" );
    		System.out.println("1. Volver al menu del cajero" );
    		System.out.println("2. Salir de la aplicación" );
    		int opcion2 = Integer.parseInt(scanner.next());
    		
    		if (opcion2 == 1) {
    			menuCajero();
    		}
    		
    		else if (opcion2 == 2) {
    			salir = true;
    		}
	    	
	    }
	    
	    else if (opcion == 2) {
	    	System.out.println("Ingrese el titulo de la pieza: ");
    		String titulo = scanner.next();
    		
    		System.out.println("Ingrese el año de creación de la pieza: ");
    		String año = scanner.next();
    		
    		System.out.println("Ingrese el lugar de creación de la pieza: ");
    		String lugarCreacion = scanner.next();
    		
    		System.out.println("Ingrese el autor de la pieza: ");
    		String autor = scanner.next();
    		
    		System.out.println("Ingrese el id del propietario de la pieza: ");
    		String idP = scanner.next();
    		
    		Propietario propietario = PersistenciaSerializar.getPropietarioByID(idP);
    		
    		System.out.println("Ingrese el ID del comprador: ");
    		String id= scanner.next();
    		
    		Comprador comprador = PersistenciaSerializar.getCompradorByID(id);
    		
    		//PIEZA
    		
    		System.out.println("Ingrese el tipo de pieza(pintura, fotografia, imperesion, video, escultura): ");
    		String tipo =scanner.next(); 
    		
    		if (tipo.toLowerCase().equals("pintura") ) {
    			
    			System.out.println("Ingrese el alto de la pintura: ");
        		double alto =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la pintura: ");
        		double ancho =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese la tecnica utilizada: ");
        		String tecnica =  scanner.next();
        		
        		System.out.println("Ingrese el estilo de la pintura: ");
        		String estilo =  scanner.next();
        		
        		System.out.println("Ingrese el tipo de pintura: ");
        		String tipoPintura =  scanner.next();
        		
        		
        		//pintura es de TipoPieza
        		Pintura pintura = new Pintura (alto, ancho, tipoPintura, tecnica, estilo);
        		
        		System.out.println("Ingrese el valor: ");
        		Double valorFijo = Double.parseDouble(scanner.next());
        		piezaVenta = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, pintura);
        		
        		
    		}
    		else if (tipo.toLowerCase().equals("fotografia")) {
    			
    			System.out.println("Ingrese el largo de la fotografia: ");
        		double largo =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la fotografia: ");
        		double ancho =  Double.parseDouble(scanner.next());
    			
        		System.out.println("Ingrese la tecnica utilizada: ");
        		String tecnica =  scanner.next();

        		
        		Fotografia fotografia = new Fotografia(largo, ancho, tecnica);
        		System.out.println("Ingrese el valor: ");
        		Double valorFijo = Double.parseDouble(scanner.next());
        		piezaVenta = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, fotografia);
        		
    		}
    		
    		else if (tipo.toLowerCase().equals("impresion")) {
    			
    			System.out.println("Ingrese el largo de la impresión: ");
        		double largo =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la impresión: ");
        		double ancho =  Double.parseDouble(scanner.next());
    			
        		System.out.println("Ingrese el tipo de impresión: ");
        		String tipoI =  scanner.next();
        		
        		System.out.println("Ingrese el tipo de papel: ");
        		String tipoP =  scanner.next();
        		
        		
        		Impresion impresion = new Impresion(largo, ancho, tipoI, tipoP);
        		
        		System.out.println("Ingrese el valor: ");
        		Double valorFijo = Double.parseDouble(scanner.next());
        		piezaVenta = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, impresion);
    		}
    		
    		else if (tipo.toLowerCase().equals("video")) {
    			
    			System.out.println("Ingrese la duración del video(en minutos): ");
        		double duracion =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el formato del video: ");
        		String formato =  scanner.next();
        		
        		System.out.println("Ingrese la resolución del video: ");
        		String resolucion =  scanner.next();
        		
        		Video video = new Video(duracion, formato, resolucion);
        		
        		System.out.println("Ingrese el valor: ");
        		Double valorFijo = Double.parseDouble(scanner.next());
        		piezaVenta = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, video);
    		}
    		
    		else if (tipo.toLowerCase().equals("escultura")) {
    			
    			System.out.println("Ingrese el alto de la escultura: ");
        		double alto =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el ancho de la escultura: ");
        		double ancho =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese la profundidad de la escultura: ");
        		double profundidad =  Double.parseDouble(scanner.next());
        		
        		System.out.println("Ingrese el material de la escultura: ");
        		String material =  scanner.next();
        		
        		System.out.println("Ingrese la tecnica utilizada: ");
        		String tecnica =  scanner.next();
        	
        		
        		Escultura escultura = new Escultura(alto, ancho, profundidad, material, tecnica);
    			
        		System.out.println("Ingrese el valor: ");
        		Double valorFijo = Double.parseDouble(scanner.next());
        		piezaVenta = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, escultura);
    		}
    		
    		Oferta oferta = new Oferta(piezaVenta, comprador);
    		inventario.historiaDePiezaVendida(oferta);
    		System.out.println("Seleccione una opción" );
    		System.out.println("1. Volver al menu del cajero" );
    		System.out.println("2. Salir de la aplicación" );
    		int opcion2 = Integer.parseInt(scanner.next());
    		
    		if (opcion2 == 1) {
    			menuCajero();
    		}
    		
    		else if (opcion2 == 2) {
    			salir = true;
    		}	
	    	
	    }
	    	
	    else if (opcion == 3) {
	    	
	    }   
	}
}

public static void main(String[] args) throws ParseException, IOException {
	 InterfazEmpleado consola = new InterfazEmpleado();
	 consola.menuInicio();
   }


}



