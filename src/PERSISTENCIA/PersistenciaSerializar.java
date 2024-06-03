package PERSISTENCIA;

import USUARIOS.Cajero;
import USUARIOS.Comprador;
import USUARIOS.Empleado;
import USUARIOS.Operador;
import USUARIOS.Propietario;
import USUARIOS.Usuario;
import galeria.Administrador;
import PIEZAS.Escultura;
import PIEZAS.Fotografia;
import PIEZAS.Impresion;
import PIEZAS.Video;
import PIEZAS.Pintura;
import PIEZAS.TipoPieza;

import java.io.*;
import java.util.Properties;

import PAGOS.MetodoPago;
import PIEZAS.Pieza;
import PIEZAS.PiezaExhibicion;
import PIEZAS.PiezaSubasta;
import PIEZAS.PiezaVenta;

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
			
			else if (rutaArchivo.equals("dataSerializacion/PiezasVenta.txt")) {
				serializarPiezaVenta(rutaArchivo, (PiezaVenta) object);
			}
			
			else if(rutaArchivo.equals("dataSerializacion/PiezasExhibicion.txt")) {
				serializarPiezaExhibicion(rutaArchivo, (PiezaExhibicion) object);
			}
			
			else if(rutaArchivo.equals("dataSerializacion/PiezasSubasta.txt")) {
				serializarPiezaSubasta(rutaArchivo, (PiezaSubasta) object);
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
    
    public void serializarPiezaSubasta(String rutaArchivo, PiezaSubasta pieza) throws IOException {
		File archivo = new File(rutaArchivo);
		if (!archivo.exists()) {
			archivo.createNewFile();
			FileWriter escribir = new FileWriter(archivo, true);
			PrintWriter linea = new PrintWriter(escribir);
			
			if (pieza.getTipoPieza().toString().equals("Video")) {
				Video tipoPieza = (Video) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorMinimo())+":"+String.valueOf(pieza.getValorInicial())+":"+"Video"+":"
						+String.valueOf(tipoPieza.getDuracion())+":"+tipoPieza.getFormato()+":"+tipoPieza.getResolucion()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());
				
			} else if(pieza.getTipoPieza().toString().equals("Pintura")) {
				Pintura tipoPieza = (Pintura) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorMinimo())+":"+String.valueOf(pieza.getValorInicial())+":"+"Pintura"+":"
						+String.valueOf(tipoPieza.getAlto())+":"+String.valueOf(tipoPieza.getAncho())+":"+tipoPieza.getTecnicaUtilizada()+":"+tipoPieza.getTipoPintura()+":"+tipoPieza.getEstilo()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Impresion")) {
				Impresion tipoPieza = (Impresion) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorMinimo())+":"+String.valueOf(pieza.getValorInicial())+":"+"Impresion"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getLargo())+":"+tipoPieza.getTipoImpresion()+":"+tipoPieza.getTipoPapel()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Fotografia")) {
				Fotografia tipoPieza = (Fotografia) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorMinimo())+":"+String.valueOf(pieza.getValorInicial())+":"+"Fotografia"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getLargo())+":"+tipoPieza.getTecnicaUtilizada()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Escultura")) {
				Escultura tipoPieza = (Escultura) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorMinimo())+":"+String.valueOf(pieza.getValorInicial())+":"+"Escultura"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getProfundidad())+":"+tipoPieza.getMaterial()+":"+tipoPieza.getTecnicaUtilizada()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());
			}
			
			linea.close();
			escribir.close();
			
		}else {
			FileWriter escribir = new FileWriter(archivo, true);
			PrintWriter linea = new PrintWriter(escribir);
			if (pieza.getTipoPieza().toString().equals("Video")) {
				Video tipoPieza = (Video) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorMinimo())+":"+String.valueOf(pieza.getValorInicial())+":"+"Video"+":"
						+String.valueOf(tipoPieza.getDuracion())+":"+tipoPieza.getFormato()+":"+tipoPieza.getResolucion()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());
				
			} else if(pieza.getTipoPieza().toString().equals("Pintura")) {
				Pintura tipoPieza = (Pintura) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorMinimo())+":"+String.valueOf(pieza.getValorInicial())+":"+"Pintura"+":"
						+String.valueOf(tipoPieza.getAlto())+":"+String.valueOf(tipoPieza.getAncho())+":"+tipoPieza.getTecnicaUtilizada()+":"+tipoPieza.getTipoPintura()+":"+tipoPieza.getEstilo()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Impresion")) {
				Impresion tipoPieza = (Impresion) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorMinimo())+":"+String.valueOf(pieza.getValorInicial())+":"+"Impresion"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getLargo())+":"+tipoPieza.getTipoImpresion()+":"+tipoPieza.getTipoPapel()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Fotografia")) {
				Fotografia tipoPieza = (Fotografia) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorMinimo())+":"+String.valueOf(pieza.getValorInicial())+":"+"Fotografia"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getLargo())+":"+tipoPieza.getTecnicaUtilizada()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Escultura")) {
				Escultura tipoPieza = (Escultura) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorMinimo())+":"+String.valueOf(pieza.getValorInicial())+":"+"Escultura"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getProfundidad())+":"+tipoPieza.getMaterial()+":"+tipoPieza.getTecnicaUtilizada()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());
			}
			
			linea.close();
			escribir.close();
			
		}
	}
			
	public void serializarPiezaVenta(String rutaArchivo, PiezaVenta pieza) throws IOException {
		File archivo = new File(rutaArchivo);
		if (!archivo.exists()) {
			archivo.createNewFile();
			FileWriter escribir = new FileWriter(archivo, true);
			PrintWriter linea = new PrintWriter(escribir);
			
			if (pieza.getTipoPieza().toString().equals("Video")) {
				Video tipoPieza = (Video) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorFijo())+":"+"Video"+":"
						+String.valueOf(tipoPieza.getDuracion())+":"+tipoPieza.getFormato()+":"+tipoPieza.getResolucion()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());
				
			} else if(pieza.getTipoPieza().toString().equals("Pintura")) {
				Pintura tipoPieza = (Pintura) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorFijo())+":"+"Pintura"+":"
						+String.valueOf(tipoPieza.getAlto())+":"+String.valueOf(tipoPieza.getAncho())+":"+tipoPieza.getTecnicaUtilizada()+":"+tipoPieza.getTipoPintura()+":"+tipoPieza.getEstilo()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Impresion")) {
				Impresion tipoPieza = (Impresion) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorFijo())+":"+"Impresion"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getLargo())+":"+tipoPieza.getTipoImpresion()+":"+tipoPieza.getTipoPapel()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Fotografia")) {
				Fotografia tipoPieza = (Fotografia) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorFijo())+":"+"Fotografia"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getLargo())+":"+tipoPieza.getTecnicaUtilizada()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Escultura")) {
				Escultura tipoPieza = (Escultura) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorFijo())+":"+"Escultura"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getProfundidad())+":"+tipoPieza.getMaterial()+":"+tipoPieza.getTecnicaUtilizada()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());
			}
			
			linea.close();
			escribir.close();
			
		}else {
			FileWriter escribir = new FileWriter(archivo, true);
			PrintWriter linea = new PrintWriter(escribir);
			if (pieza.getTipoPieza().toString().equals("Video")) {
				Video tipoPieza = (Video) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorFijo())+":"+"Video"+":"
						+String.valueOf(tipoPieza.getDuracion())+":"+tipoPieza.getFormato()+":"+tipoPieza.getResolucion()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());
				
			} else if(pieza.getTipoPieza().toString().equals("Pintura")) {
				Pintura tipoPieza = (Pintura) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorFijo())+":"+"Pintura"+":"
						+String.valueOf(tipoPieza.getAlto())+":"+String.valueOf(tipoPieza.getAncho())+":"+tipoPieza.getTecnicaUtilizada()+":"+tipoPieza.getTipoPintura()+":"+tipoPieza.getEstilo()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Impresion")) {
				Impresion tipoPieza = (Impresion) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorFijo())+":"+"Impresion"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getLargo())+":"+tipoPieza.getTipoImpresion()+":"+tipoPieza.getTipoPapel()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Fotografia")) {
				Fotografia tipoPieza = (Fotografia) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorFijo())+":"+"Fotografia"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getLargo())+":"+tipoPieza.getTecnicaUtilizada()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Escultura")) {
				Escultura tipoPieza = (Escultura) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+String.valueOf(pieza.getValorFijo())+":"+"Escultura"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getProfundidad())+":"+tipoPieza.getMaterial()+":"+tipoPieza.getTecnicaUtilizada()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());
			}
			
			linea.close();
			escribir.close();
			
		}
	}
	public void serializarPiezaExhibicion(String rutaArchivo, PiezaExhibicion pieza) throws IOException {
		File archivo = new File(rutaArchivo);
		if (!archivo.exists()) {
			archivo.createNewFile();
			FileWriter escribir = new FileWriter(archivo, true);
			PrintWriter linea = new PrintWriter(escribir);
			
			if (pieza.getTipoPieza().toString().equals("Video")) {
				Video tipoPieza = (Video) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+"Video"+":"
						+String.valueOf(tipoPieza.getDuracion())+":"+tipoPieza.getFormato()+":"+tipoPieza.getResolucion()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());
				
			} else if(pieza.getTipoPieza().toString().equals("Pintura")) {
				Pintura tipoPieza = (Pintura) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+"Pintura"+":"
						+String.valueOf(tipoPieza.getAlto())+":"+String.valueOf(tipoPieza.getAncho())+":"+tipoPieza.getTecnicaUtilizada()+":"+tipoPieza.getTipoPintura()+":"+tipoPieza.getEstilo()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Impresion")) {
				Impresion tipoPieza = (Impresion) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+"Impresion"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getLargo())+":"+tipoPieza.getTipoImpresion()+":"+tipoPieza.getTipoPapel()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Fotografia")) {
				Fotografia tipoPieza = (Fotografia) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+"Fotografia"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getLargo())+":"+tipoPieza.getTecnicaUtilizada()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Escultura")) {
				Escultura tipoPieza = (Escultura) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+"Escultura"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getProfundidad())+":"+tipoPieza.getMaterial()+":"+tipoPieza.getTecnicaUtilizada()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());
			}
			
			linea.close();
			escribir.close();
			
		}else {
			FileWriter escribir = new FileWriter(archivo, true);
			PrintWriter linea = new PrintWriter(escribir);
			if (pieza.getTipoPieza().toString().equals("Video")) {
				Video tipoPieza = (Video) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+"Video"+":"
						+String.valueOf(tipoPieza.getDuracion())+":"+tipoPieza.getFormato()+":"+tipoPieza.getResolucion()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());
				
			} else if(pieza.getTipoPieza().toString().equals("Pintura")) {
				Pintura tipoPieza = (Pintura) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+"Pintura"+":"
						+String.valueOf(tipoPieza.getAlto())+":"+String.valueOf(tipoPieza.getAncho())+":"+tipoPieza.getTecnicaUtilizada()+":"+tipoPieza.getTipoPintura()+":"+tipoPieza.getEstilo()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Impresion")) {
				Impresion tipoPieza = (Impresion) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+"Impresion"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getLargo())+":"+tipoPieza.getTipoImpresion()+":"+tipoPieza.getTipoPapel()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Fotografia")) {
				Fotografia tipoPieza = (Fotografia) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+"Fotografia"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getLargo())+":"+tipoPieza.getTecnicaUtilizada()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());		
				
			}else if(pieza.getTipoPieza().toString().equals("Escultura")) {
				Escultura tipoPieza = (Escultura) pieza.getTipoPieza();
				linea.println(pieza.getTitulo()+":"+pieza.getAño()+":"+pieza.getLugarCreacion()+":"+pieza.getAutor()+":"+pieza.getEstado()+":"+"Escultura"+":"
						+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getAncho())+":"+String.valueOf(tipoPieza.getProfundidad())+":"+tipoPieza.getMaterial()+":"+tipoPieza.getTecnicaUtilizada()+":"+
						pieza.getPropietario().getId()+":"+pieza.getPropietario().getNombre()+":"+pieza.getPropietario().getLogin()+":"
						+pieza.getPropietario().getPassword());
			}
			
			linea.close();
			escribir.close();
			
		}
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
	
	public static PiezaVenta getPiezaVentaByTitulo(String titulo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("dataSerializacion/PiezasVenta.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(":");
                String piezaTitulo = datos[0];
                
                if (piezaTitulo.equals(titulo)) {
                    String año = datos[1];
                    String lugarCreacion = datos[2];
                    String autor = datos[3];
                    String estado = datos[4];
                    double valorFijo = Double.parseDouble(datos[5]);
                    String tipoPiezaStr = datos[6];
                    
                    String idPropietario = datos[datos.length - 4];
                    String nombrePropietario = datos[datos.length - 3];
                    String loginPropietario = datos[datos.length - 2];
                    String passwordPropietario = datos[datos.length - 1];
                    Propietario propietario = new Propietario(idPropietario, nombrePropietario, loginPropietario, passwordPropietario);

                    TipoPieza tipoPieza = null;

                    if (tipoPiezaStr.equals("Video")) {
                        double duracion = Double.parseDouble(datos[7]);
                        String formato = datos[8];
                        String resolucion = datos[9];
                        tipoPieza = new Video(duracion, formato, resolucion);
                    } else if (tipoPiezaStr.equals("Pintura")) {
                        double alto = Double.parseDouble(datos[7]);
                        double ancho = Double.parseDouble(datos[8]);
                        String tecnicaUtilizada = datos[9];
                        String tipoPintura = datos[10];
                        String estilo = datos[11];
                        tipoPieza = new Pintura(alto, ancho, tipoPintura, tecnicaUtilizada, estilo);
                    } else if (tipoPiezaStr.equals("Impresion")) {
                        double ancho = Double.parseDouble(datos[7]);
                        double largo = Double.parseDouble(datos[8]);
                        String tipoImpresion = datos[9];
                        String tipoPapel = datos[10];
                        tipoPieza = new Impresion(ancho, largo, tipoImpresion, tipoPapel);
                    } else if (tipoPiezaStr.equals("Fotografia")) {
                        double ancho = Double.parseDouble(datos[7]);
                        double largo = Double.parseDouble(datos[8]);
                        String tecnicaUtilizada = datos[9];
                        tipoPieza = new Fotografia(ancho, largo, tecnicaUtilizada);
                    } else if (tipoPiezaStr.equals("Escultura")) {
                        double ancho = Double.parseDouble(datos[7]);
                        double alto = Double.parseDouble(datos[8]);
                        double profundidad = Double.parseDouble(datos[9]);
                        String material = datos[10];
                        String tecnicaUtilizada = datos[11];
                        tipoPieza = new Escultura(alto, ancho, profundidad, material, tecnicaUtilizada);
                    }

                    if (tipoPieza != null) {
                        PiezaVenta pieza = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, tipoPieza);
                        return pieza;
                    }
                }
            }
        }
        return null; // Si no se encuentra la pieza con el título especificado
    }
	
	public static PiezaExhibicion getPiezaExhibicionByTitulo(String titulo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("dataSerializacion/PiezasExhibicion.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(":");
                String piezaTitulo = datos[0];
                
                if (piezaTitulo.equals(titulo)) {
                    String año = datos[1];
                    String lugarCreacion = datos[2];
                    String autor = datos[3];
                    String estado = datos[4];
                    String tipoPiezaStr = datos[5];
                    
                    String idPropietario = datos[datos.length - 4];
                    String nombrePropietario = datos[datos.length - 3];
                    String loginPropietario = datos[datos.length - 2];
                    String passwordPropietario = datos[datos.length - 1];
                    Propietario propietario = new Propietario(idPropietario, nombrePropietario, loginPropietario, passwordPropietario);

                    TipoPieza tipoPieza = null;

                    if (tipoPiezaStr.equals("Video")) {
                        double duracion = Double.parseDouble(datos[6]);
                        String formato = datos[7];
                        String resolucion = datos[8];
                        tipoPieza = new Video(duracion, formato, resolucion);
                    } else if (tipoPiezaStr.equals("Pintura")) {
                        double alto = Double.parseDouble(datos[6]);
                        double ancho = Double.parseDouble(datos[7]);
                        String tecnicaUtilizada = datos[8];
                        String tipoPintura = datos[9];
                        String estilo = datos[10];
                        tipoPieza = new Pintura(alto, ancho, tipoPintura, tecnicaUtilizada, estilo);
                    } else if (tipoPiezaStr.equals("Impresion")) {
                        double ancho = Double.parseDouble(datos[6]);
                        double largo = Double.parseDouble(datos[7]);
                        String tipoImpresion = datos[8];
                        String tipoPapel = datos[9];
                        tipoPieza = new Impresion(ancho, largo, tipoImpresion, tipoPapel);
                    } else if (tipoPiezaStr.equals("Fotografia")) {
                        double ancho = Double.parseDouble(datos[6]);
                        double largo = Double.parseDouble(datos[7]);
                        String tecnicaUtilizada = datos[8];
                        tipoPieza = new Fotografia(ancho, largo, tecnicaUtilizada);
                    } else if (tipoPiezaStr.equals("Escultura")) {
                        double ancho = Double.parseDouble(datos[6]);
                        double alto = Double.parseDouble(datos[7]);
                        double profundidad = Double.parseDouble(datos[8]);
                        String material = datos[9];
                        String tecnicaUtilizada = datos[10];
                        tipoPieza = new Escultura(alto, ancho, profundidad, material, tecnicaUtilizada);
                    }

                    if (tipoPieza != null) {
                        PiezaExhibicion pieza = new PiezaExhibicion(titulo, año, lugarCreacion, autor, propietario, tipoPieza);
                        return pieza;
                    }
                }
            }
        }
        return null; // Si no se encuentra la pieza con el título especificado
    }
	
	public static PiezaSubasta getPiezaSubastaByTitulo(String titulo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("dataSerializacion/PiezasSubasta.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(":");
                String piezaTitulo = datos[0];
                
                if (piezaTitulo.equals(titulo)) {
                    String año = datos[1];
                    String lugarCreacion = datos[2];
                    String autor = datos[3];
                    String estado = datos[4];
                    double valorMinimo = Double.parseDouble(datos[5]);
                    double valorInicial = Double.parseDouble(datos[6]);
                    String tipoPiezaStr = datos[7];
                    
                    String idPropietario = datos[datos.length - 4];
                    String nombrePropietario = datos[datos.length - 3];
                    String loginPropietario = datos[datos.length - 2];
                    String passwordPropietario = datos[datos.length - 1];
                    Propietario propietario = new Propietario(idPropietario, nombrePropietario, loginPropietario, passwordPropietario);

                    TipoPieza tipoPieza = null;

                    if (tipoPiezaStr.equals("Video")) {
                        double duracion = Double.parseDouble(datos[8]);
                        String formato = datos[9];
                        String resolucion = datos[10];
                        tipoPieza = new Video(duracion, formato, resolucion);
                    } else if (tipoPiezaStr.equals("Pintura")) {
                        double alto = Double.parseDouble(datos[8]);
                        double ancho = Double.parseDouble(datos[9]);
                        String tecnicaUtilizada = datos[10];
                        String tipoPintura = datos[11];
                        String estilo = datos[12];
                        tipoPieza = new Pintura(alto, ancho, tipoPintura, tecnicaUtilizada, estilo);
                    } else if (tipoPiezaStr.equals("Impresion")) {
                        double ancho = Double.parseDouble(datos[8]);
                        double largo = Double.parseDouble(datos[9]);
                        String tipoImpresion = datos[10];
                        String tipoPapel = datos[11];
                        tipoPieza = new Impresion(ancho, largo, tipoImpresion, tipoPapel);
                    } else if (tipoPiezaStr.equals("Fotografia")) {
                        double ancho = Double.parseDouble(datos[8]);
                        double largo = Double.parseDouble(datos[9]);
                        String tecnicaUtilizada = datos[10];
                        tipoPieza = new Fotografia(ancho, largo, tecnicaUtilizada);
                    } else if (tipoPiezaStr.equals("Escultura")) {
                        double ancho = Double.parseDouble(datos[8]);
                        double alto = Double.parseDouble(datos[9]);
                        double profundidad = Double.parseDouble(datos[10]);
                        String material = datos[11];
                        String tecnicaUtilizada = datos[12];
                        tipoPieza = new Escultura(alto, ancho, profundidad, material, tecnicaUtilizada);
                    }

                    if (tipoPieza != null) {
                        PiezaSubasta pieza = new PiezaSubasta(titulo, año, lugarCreacion, autor, valorInicial, valorMinimo, propietario, tipoPieza);
                        return pieza;
                    }
                }
            }
        }
        return null; // Si no se encuentra la pieza con el título especificado
    }
	
	
}