package INTERFAZ;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import COMPRAYSUBASTA.Oferta;
import CONSIGNACION.Consignacion;
import PERSISTENCIA.PersistenciaSerializar;
import PIEZAS.Escultura;
import PIEZAS.EstadoPiezas;
import PIEZAS.Fotografia;
import PIEZAS.Impresion;
import PIEZAS.Pieza;
import PIEZAS.PiezaExhibicion;
import PIEZAS.PiezaSubasta;
import PIEZAS.PiezaVenta;
import PIEZAS.Pintura;
import PIEZAS.TipoPieza;
import PIEZAS.Video;
import USUARIOS.Comprador;
import USUARIOS.Propietario;
import galeria.Administrador;
import galeria.Inventario;


public class InterfazAdmin {
	
	//private PersistenciaCliente perC;
	
	private static Scanner scanner = new Scanner(System.in);
	
	private static Administrador administrador;
	
	private static Inventario inventario;
	
	private static Pieza pieza;
	
	private static PiezaVenta piezaVenta;
	public InterfazAdmin() {
        inventario = new Inventario(administrador);
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
            	registrarAdmin();
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
	

	public static void registrarAdmin() throws ParseException, IOException {
		
		Random r = new Random();
		
		System.out.println("Ingrese su nombre: ");
		String nombre = scanner.next();
		
		System.out.println("Ingrese su usuario: ");
		String usuario = scanner.next();
		
		System.out.println("Ingrese su contraseña: ");
		String contraseña = scanner.next();
		
		String id = new BigInteger(50, r).toString(32); 
		System.out.println("Su id es: " + id);
		
		administrador = new Administrador(id, nombre, usuario, contraseña);
		new PersistenciaSerializar("dataSerializacion/Admin.txt", administrador);
		
		System.out.println("Se ha registrado con exito");
		//System.out.println("Escriba 'si' si desea regresar al menu principal");
		
		menuInicio();
		
	}
	
	public static void logIn() throws ParseException, IOException {
		
		System.out.println("Ingrese su usuario: ");
		String usuario = scanner.next();
		
		System.out.println("Ingrese su contraseña: ");
		String contraseña = scanner.next();
		
		//verificar que se encuentre registrado
		//si se encuentra mostrar el menu del administrador
		
		if (administrador.getLogin().equals(usuario) && administrador.getPassword().equals(contraseña)) {
			menuAdmin();
		}
	}
	
	public static void menuAdmin() throws ParseException, IOException {
		boolean salir = false;
		while(!salir) {
			System.out.println("1. Consignar pieza");
			System.out.println("2. Verificar comprador");
			System.out.println("3. Consultar historia de una pieza");
			System.out.println("4. Consultar historia de un artista");
			System.out.println("5. Consultar historia de un comprador");
			System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(scanner.next());
            
            //CONSIGNAR PIEZA
            if (opcion == 1) {
            	
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
            		
            		System.out.println("¿Desea consignar la pieza para exhibir, vender o subastar? (Exhibir, Vender, Subastar)");
            		String motivoPieza = scanner.next();
            		
            		
            		//pintura es de TipoPieza
            		Pintura pintura = new Pintura (alto, ancho, tipoPintura, tecnica, estilo);
            		//crea la pieza
            		if (motivoPieza.equals("Exhibir")){
            			pieza = new PiezaExhibicion(titulo, año, lugarCreacion, autor, propietario, pintura);
            		}else if (motivoPieza.equals("Vender")) {
            			System.out.println("Ingrese el valor: ");
            			Double valorFijo = Double.parseDouble(scanner.next());
            			pieza = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, pintura);
            		}else if (motivoPieza.equals("Subastar")) {
            			System.out.println("Ingrese el valor inicial: ");
            			Double valorInicial = Double.parseDouble(scanner.next());
            			System.out.println("Ingrese el valor minimo: ");
            			Double valorMinimo = Double.parseDouble(scanner.next());
            			pieza = new PiezaSubasta(titulo, año, lugarCreacion, autor, valorMinimo, valorInicial, propietario, pintura);
            			
            		}
        		}
        		
        		else if (tipo.toLowerCase().equals("fotografia")) {
        			
        			System.out.println("Ingrese el largo de la fotografia: ");
            		double largo =  Double.parseDouble(scanner.next());
            		
            		System.out.println("Ingrese el ancho de la fotografia: ");
            		double ancho =  Double.parseDouble(scanner.next());
        			
            		System.out.println("Ingrese la tecnica utilizada: ");
            		String tecnica =  scanner.next();
            		System.out.println("¿Desea consignar la pieza para exhibir, vender o subastar? (Exhibir, Vender, Subastar)");
            		String motivoPieza = scanner.next();
            		
            		Fotografia fotografia = new Fotografia(largo, ancho, tecnica);
            		
            		//crea la pieza
            		if (motivoPieza.equals("Exhibir")){
            			pieza = new PiezaExhibicion(titulo, año, lugarCreacion, autor, propietario, fotografia);
            		}else if (motivoPieza.equals("Vender")) {
            			System.out.println("Ingrese el valor: ");
            			Double valorFijo = Double.parseDouble(scanner.next());
            			pieza = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, fotografia);
            		}else if (motivoPieza.equals("Subastar")) {
            			System.out.println("Ingrese el valor inicial: ");
            			Double valorInicial = Double.parseDouble(scanner.next());
            			System.out.println("Ingrese el valor minimo: ");
            			Double valorMinimo = Double.parseDouble(scanner.next());
            			pieza = new PiezaSubasta(titulo, año, lugarCreacion, autor, valorMinimo, valorInicial, propietario, fotografia);
            			
            		}
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
            		
            		//crea la pieza
            		if (motivoPieza.equals("Exhibir")){
            			pieza = new PiezaExhibicion(titulo, año, lugarCreacion, autor, propietario, impresion);
            		}else if (motivoPieza.equals("Vender")) {
            			System.out.println("Ingrese el valor: ");
            			Double valorFijo = Double.parseDouble(scanner.next());
            			pieza = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, impresion);
            		}else if (motivoPieza.equals("Subastar")) {
            			System.out.println("Ingrese el valor inicial: ");
            			Double valorInicial = Double.parseDouble(scanner.next());
            			System.out.println("Ingrese el valor minimo: ");
            			Double valorMinimo = Double.parseDouble(scanner.next());
            			pieza = new PiezaSubasta(titulo, año, lugarCreacion, autor, valorMinimo, valorInicial, propietario, impresion);
            			
            		}
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
            		if (motivoPieza.equals("Exhibir")){
            			pieza = new PiezaExhibicion(titulo, año, lugarCreacion, autor, propietario, video);
            		}else if (motivoPieza.equals("Vender")) {
            			System.out.println("Ingrese el valor: ");
            			Double valorFijo = Double.parseDouble(scanner.next());
            			pieza = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, video);
            		}else if (motivoPieza.equals("Subastar")) {
            			System.out.println("Ingrese el valor inicial: ");
            			Double valorInicial = Double.parseDouble(scanner.next());
            			System.out.println("Ingrese el valor minimo: ");
            			Double valorMinimo = Double.parseDouble(scanner.next());
            			pieza = new PiezaSubasta(titulo, año, lugarCreacion, autor, valorMinimo, valorInicial, propietario, video);
            			
            		}
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
            		if (motivoPieza.equals("Exhibir")){
            			pieza = new PiezaExhibicion(titulo, año, lugarCreacion, autor, propietario, escultura);
            		}else if (motivoPieza.equals("Vender")) {
            			System.out.println("Ingrese el valor: ");
            			Double valorFijo = Double.parseDouble(scanner.next());
            			pieza = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, escultura);
            		}else if (motivoPieza.equals("Subastar")) {
            			System.out.println("Ingrese el valor inicial: ");
            			Double valorInicial = Double.parseDouble(scanner.next());
            			System.out.println("Ingrese el valor minimo: ");
            			Double valorMinimo = Double.parseDouble(scanner.next());
            			pieza = new PiezaSubasta(titulo, año, lugarCreacion, autor, valorMinimo, valorInicial, propietario, escultura);
            			
            		}
        		}
        		
        		
            	//el administrador debe pedir el ID del propietario para la pieza        		
        		//crear pieza
        		
        		
        		
            	//fecha inicio
        		
        		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        		
        		System.out.println("Ingrese la fecha de ingreso de la pieza (\"dd/MM/yyyy\"): ");
        		Date fechai = dateFormat.parse(scanner.next());
        		
            	//fecha fin
        		
        		System.out.println("Ingrese la fecha de salida de la pieza(\"dd/MM/yyyy\"): ");
        		Date fechaf = dateFormat.parse(scanner.next());
        		
            	//crear consignacion
        		
        		Consignacion consignacion = new Consignacion(pieza, propietario, fechai, fechaf);
        		
            	//llamar inventario para consignar pieza
        		
        		inventario.consignarPieza(consignacion);
        		
        		System.out.println("La pieza se ha consignado con exito! ");
        		System.out.println("Seleccione una opción" );
        		System.out.println("1. Volver al menu del administrador" );
        		System.out.println("2. Slir de la aplicación" );
        		int opcion2 = Integer.parseInt(scanner.next());
        		
        		if (opcion2 == 1) {
        			menuAdmin();
        		}
        		
        		else if (opcion2 == 2) {
        			salir = true;
        		}
            }
            
            //VERIFICAR COMPRADOR
            else if(opcion == 2) {
            	
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
            	
        		
        		
        		administrador.verificarComprador(comprador, piezaVenta);
        		System.out.println("Se ha verificado el comprador con exito! ");
        		System.out.println("Seleccione una opción" );
        		System.out.println("1. Volver al menu del administrador" );
        		System.out.println("2. Salir de la aplicación" );
        		int opcion2 = Integer.parseInt(scanner.next());
        		
        		if (opcion2 == 1) {
        			menuAdmin();
        		}
        		
        		else if (opcion2 == 2) {
        			salir = true;
        		}	
            }
            
            //CONSULTAR HISTORIA PIEZA
            else if (opcion == 3) {
            	
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
        		System.out.println("1. Volver al menu del administrador" );
        		System.out.println("2. Salir de la aplicación" );
        		int opcion2 = Integer.parseInt(scanner.next());
        		
        		if (opcion2 == 1) {
        			menuAdmin();
        		}
        		
        		else if (opcion2 == 2) {
        			salir = true;
        		}	
            	
            }
            
            //CONSULTAR HISTORIA ARTISTA
            else if (opcion == 4) {
            	
            }
			
            //CONSULTAR HISTORIA COMPRADOR
            else if (opcion == 5) {
            	
            	System.out.println("Ingrese el ID del comprador: ");
        		String id =  scanner.next();
        		
        		Comprador comprador = PersistenciaSerializar.getCompradorByID(id);
        		
        		inventario.historiaComprador(comprador);
        		System.out.println("1. Volver al menu del administrador" );
        		System.out.println("2. Salir de la aplicación" );
        		int opcion2 = Integer.parseInt(scanner.next());
        		
        		if (opcion2 == 1) {
        			menuAdmin();
        		}
        		
        		else if (opcion2 == 2) {
        			salir = true;
        		}	
            	
            }
		}
		
	}
	
	 public static void main(String[] args) throws ParseException, IOException {
		 InterfazAdmin consola = new InterfazAdmin();
		 consola.menuInicio();
	    }

}
