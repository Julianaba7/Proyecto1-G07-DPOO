package PERSISTENCIA;

import USUARIOS.Cajero;
import USUARIOS.Comprador;
import USUARIOS.Empleado;
import USUARIOS.Operador;
import USUARIOS.Propietario;
import USUARIOS.Usuario;
import galeria.Administrador;

import java.io.*;
import java.util.Properties;

import PAGOS.MetodoPago;

public class PersistenciaSerializar {
	
	public PersistenciaSerializar(String rutaArchivo, Object object) {
		
		
		try {
			if (rutaArchivo.equals("dataSerializacion/Propietarios.txt")) {
				serializarPropietario(rutaArchivo, (Propietario) object);
			}
			
			else if(rutaArchivo.equals("dataSerializacion/Empleados.txt")) {
				serializarEmpleado(rutaArchivo, (Usuario) object);
			}
			
			else if(rutaArchivo.equals("dataSerializacion/Compradores.txt")) {
				serializarComprador(rutaArchivo, (Comprador) object);
			}
			
			else if (rutaArchivo.equals("dataSerializacion/Admin.txt")) {
				serializarAdministrador(rutaArchivo, (Administrador) object);
			}
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void serializarPropietario(String rutaArchivo, Propietario propietario) throws IOException{
		
		File archivo = new File(rutaArchivo);
		if (!archivo.exists()) {
			archivo.createNewFile();
			FileWriter escribir = new FileWriter(archivo, true);
			PrintWriter linea = new PrintWriter(escribir);
			linea.println(propietario.getId()+":"+propietario.getLogin()+":"+propietario.getNombre()+":"+propietario.getPassword());
			linea.close();
			escribir.close();
			
			
			
		}else {
			
			FileWriter escribir = new FileWriter(archivo, true);
			PrintWriter linea = new PrintWriter(escribir);
			linea.println(propietario.getId()+":"+propietario.getLogin()+":"+propietario.getNombre()+":"+propietario.getPassword());
			linea.close();
			escribir.close();
		}
	}
	
	public void serializarEmpleado(String rutaArchivo, Usuario usuario) throws IOException {
		File archivo = new File(rutaArchivo);
		if (!archivo.exists()) {
			archivo.createNewFile();
			FileWriter escribir = new FileWriter(archivo, true);
			PrintWriter linea = new PrintWriter(escribir);
			linea.println(usuario.getId()+":"+usuario.getLogin()+":"+usuario.getNombre()+":"+usuario.getPassword()+":"+usuario.getRol());
			linea.close();
			escribir.close();
		}else {
			FileWriter escribir = new FileWriter(archivo, true);
			PrintWriter linea = new PrintWriter(escribir);
			linea.println(usuario.getId()+":"+usuario.getLogin()+":"+usuario.getNombre()+":"+usuario.getPassword()+":"+usuario.getRol());
			linea.close();
			escribir.close();
			
		}
	}
	
	public void serializarComprador(String rutaArchivo, Comprador comprador) throws IOException{
		File archivo = new File(rutaArchivo);
		if (!archivo.exists()) {
			archivo.createNewFile();
			FileWriter escribir = new FileWriter(archivo, true);
			PrintWriter linea = new PrintWriter(escribir);
			linea.println(comprador.getId()+":"+comprador.getLogin()+":"+comprador.getNombre()+":"+comprador.getPassword()+":"+comprador.getRol()
				+":"+comprador.getPoderAdquisitivo()+":"+comprador.getMetodopago());
			linea.close();
			escribir.close();
		}else {
			FileWriter escribir = new FileWriter(archivo, true);
			PrintWriter linea = new PrintWriter(escribir);
			linea.println(comprador.getId()+":"+comprador.getLogin()+":"+comprador.getNombre()+":"+comprador.getPassword()+":"+comprador.getRol()
			+":"+comprador.getPoderAdquisitivo()+":"+comprador.getMetodopago());
			linea.close();
			escribir.close();
			
		}
	}
	
	public void serializarAdministrador(String rutaArchivo, Administrador administrador) throws IOException {
        File archivo = new File(rutaArchivo);
        File lockFile = new File(rutaArchivo + ".lock");

        if (!archivo.exists()) {
            archivo.createNewFile();
            escribirEnArchivo(archivo, administrador);
            crearBloqueo(lockFile);
        } else {
            if (!lockFile.exists()) {
                if (archivo.length() == 0) { // Si el archivo está vacío
                    escribirEnArchivo(archivo, administrador);
                    crearBloqueo(lockFile);
                } else {
                    System.out.println("El archivo no está vacío. No se puede escribir.");
                }
            } else {
                if (archivo.length() == 0) { // Si el archivo está vacío
                    eliminarBloqueo(lockFile);
                    escribirEnArchivo(archivo, administrador);
                    crearBloqueo(lockFile);
                } else {
                    System.out.println("El archivo ya está bloqueado y no está vacío. No se puede escribir.");
                }
            }
        }
    }

    private void escribirEnArchivo(File archivo, Administrador administrador) throws IOException {
        FileWriter escribir = new FileWriter(archivo, true);
        PrintWriter linea = new PrintWriter(escribir);
        linea.println(administrador.getId() + ":" + administrador.getLogin() + ":" + administrador.getNombre() + ":" + administrador.getPassword());
        linea.close();
        escribir.close();
    }

    private void crearBloqueo(File lockFile) throws IOException {
        lockFile.createNewFile();
    }

    private void eliminarBloqueo(File lockFile) {
        lockFile.delete();
    }
			
	
	public static Administrador getAdministradorByID() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("dataSerializacion/Admin.txt"))) {
			String linea;
			Boolean terminar = true;
			Administrador propietario = null;
			while ((linea = br.readLine()) != null && terminar == true) {
			       // Dividir la línea por el carácter ":"
				String[] datos = linea.split(":");
				terminar = false;
			    String ids = datos[0];
			    String nombre = datos[1];
			    String login = datos[2];
			    String password = datos[3];
			    propietario = new Administrador(ids, nombre, login, password);
				}
			return propietario;
			}

	}
		
	
	public static Comprador getCompradorByID(String id) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("dataSerializacion/Compradores.txt"))) {
			String linea;
			Boolean terminar = true;
			Comprador comprador = null;
			while ((linea = br.readLine()) != null && terminar == true) {
			       // Dividir la línea por el carácter ":"
				String[] datos = linea.split(":");
				if (datos[0].equals(id)) {
					terminar = false;
			        String ids = datos[0];
			        String nombre = datos[1];
			        String login = datos[2];
			        String password = datos[3];
			        Double poder =  Double.parseDouble(datos[5]);
			        MetodoPago metodo = MetodoPago.valueOf(datos[6]);
					comprador = new Comprador(ids, nombre, login, password, poder, metodo);
				}
			}
			return comprador;
		}
	}
	
	public static Propietario getPropietarioByID(String id) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("dataSerializacion/Propietarios.txt"))) {
			String linea;
			Boolean terminar = true;
			Propietario propietario = null;
			while ((linea = br.readLine()) != null && terminar == true) {
			       // Dividir la línea por el carácter ":"
				String[] datos = linea.split(":");
				if (datos[0].equals(id)) {
					terminar = false;
			        String ids = datos[0];
			        String nombre = datos[1];
			        String login = datos[2];
			        String password = datos[3];
					propietario = new Propietario(ids, nombre, login, password);
				}
			}
			return propietario;
		}
	}
	
	public static Usuario getEmpleadoByID(String id) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("dataSerializacion/Empleados.txt"))) {
			String linea;
			Boolean terminar = true;
			Usuario usuario = null;
			while ((linea = br.readLine()) != null && terminar == true) {
			       // Dividir la línea por el carácter ":"
				String[] datos = linea.split(":");
				if (datos[0].equals(id)) {
					terminar = false;
			        String ids = datos[0];
			        String nombre = datos[1];
			        String login = datos[2];
			        String password = datos[3];
			        String tipoTipo = datos[4];
					if (tipoTipo.equals("EMPLEADO")) {
						usuario = new Empleado(ids, nombre, login, password);
					}else if (tipoTipo.equals("CAJERO")) {
						usuario = new Cajero(ids, nombre, login, password);
					}else if(tipoTipo.equals("OPERADOR")) {
						usuario = new Operador(ids, nombre, login, password);
					}
				}
			}
			return usuario;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Propietario propietario = new Propietario("i", "l", "n", "p");
		Propietario propietario2 = new Propietario("i2", "l2", "n2", "p2");
		
		Cajero empleado1 = new Cajero("ie1", "le1", "ne1", "pe1");
		Empleado empleado2 = new Empleado("ie2", "le2", "ne2", "pe2");
		Operador empleado3 = new Operador("ie3", "le3", "ne3", "pe3");
		
		Comprador comprador1 = new Comprador("c1", "cc2", "ccc3", "cccc4", 200.0, MetodoPago.EFECTIVO);
		Comprador comprador2 = new Comprador("d1", "dd2", "ddd3", "dddd4", 200.0, MetodoPago.EFECTIVO);
		
		Administrador admin = new Administrador("ad", "ad", "ad", "ad");
		
		new PersistenciaSerializar("dataSerializacion/Admin.txt", admin);
		Administrador adminre = PersistenciaSerializar.getAdministradorByID();
		
		new PersistenciaSerializar("dataSerializacion/Compradores.txt", comprador1);
		new PersistenciaSerializar("dataSerializacion/Compradores.txt", comprador2);
		Comprador comprador = PersistenciaSerializar.getCompradorByID("c1");
		
		new PersistenciaSerializar("dataSerializacion/Empleados.txt", empleado1);
		new PersistenciaSerializar("dataSerializacion/Empleados.txt", empleado2);
		new PersistenciaSerializar("dataSerializacion/Empleados.txt", empleado3);
		Usuario usuario = PersistenciaSerializar.getEmpleadoByID("ie3");
		
		new PersistenciaSerializar("dataSerializacion/Propietarios.txt", propietario2);
		new PersistenciaSerializar("dataSerializacion/Propietarios.txt", propietario);
		Propietario proper = PersistenciaSerializar.getPropietarioByID("i2");
		
		System.out.println(proper.getId());
		System.out.println(usuario.getRol());
		System.out.println(comprador.getId());
		System.out.println(adminre.getId());
	}
	

}