package USUARIOS;

//CLASE USUARIO-ESTA CLASE ES UNA SUPERCLASE PARA LOS DISTINTOS TIPOS DE USUARIO:
//COMPRADOR, OPERADOR, CAJERO Y EMPLEADO
public class Usuario {
	
	//ID es un valor numerico único para cada usuario (facilita el acceso a un usuario determinado)
	protected String id;
	
	protected String nombre;
	
	//El login es obligatorio para todos los usuarios del sistema incluso el administrador
	protected String login;
	
	//La password es obligatoria para todos los usuarios del sistema incluso el administrador
	protected String password;
	
	//Rol, facilita saber que rol realiza cada usuario-ESTE SE INICIALIZA CON CADA INSTANCIA DE SU SUBCLASE CORRESPONDIENTE 
	//Y NO DIRECAMENTE DESDE EL CONSTRUCTOR DE USUARIO
	protected RolUsuarios rol;
	
	public Usuario(String id, String nombre, String login, String password) {
		this.id = id;
		this.nombre = nombre;
		this.login = login;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public RolUsuarios getRol() {
		return rol;
	}

	
}
