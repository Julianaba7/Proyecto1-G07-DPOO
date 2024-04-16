package USUARIOS;

//Empleado es una clase que representa a los empleados que pueden realizar acciones
//para administrar las piezas dell inventario
public class Empleado extends Usuario{
	
	public Empleado(String id, String nombre, String login, String password) {
		super(id, nombre, login, password);
		this.rol = RolUsuarios.EMPLEADO;
	}
	
	
}
