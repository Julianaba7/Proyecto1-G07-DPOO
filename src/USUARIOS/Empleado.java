package USUARIOS;

import PIEZAS.Pieza;

//Empleado es una clase que representa a los empleados que pueden realizar acciones
//para administrar las piezas dell inventario
public class Empleado extends Usuario{
	
	public Empleado(String id, String nombre, String login, String password) {
		super(id, nombre, login, password);
		this.rol = RolUsuarios.EMPLEADO;
	}
	
	public String getInformacion(Pieza pieza) {
		
		String info = null;
		
		String titulo = pieza.getTitulo();
		String anio = pieza.getAño();
		String autor = pieza.getAutor();
		String lugarCreacion = pieza.getLugarCreacion();
		String propietario = pieza.getPropietario().getNombre();

		info = titulo + "," + anio + "," + autor + "," + lugarCreacion + "," + propietario ;
		return info;
	}
}
