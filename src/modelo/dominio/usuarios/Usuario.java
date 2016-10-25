package modelo.dominio.usuarios;


/**
 * Clase que contiene los datos de un usuario de la aplicación y los métodos auxiliares necesarios.
 * @author Fabricio Isaac Maldonado, David Bolanios
 */
public class Usuario implements Comparable<Usuario> {
	private String contrasena;
	private String dni;
	private String rol;

	/**
	 * Crea un nuevo usuario.
	 * @param pass La contraseña.
	 * @param dni DNI del usuario.
	 * @param rol Rol del usuario.
	 */
	public Usuario(String pass, String dni, String rol){
		this.contrasena = pass;
		this.dni = dni;
		this.rol = rol;
	}
	
	/**
	 * Crea un nuevo usuario.
	 * @param pass La contraseña.
	 * @param dni DNI del usuario.
	 */
	public Usuario(String pass, String dni){
		this.contrasena = pass;
		this.dni = dni;
	}

	/**
	 * Crea un nuevo usuario.
	 * @param dni DNI del usuario.
	 */
	public Usuario(String dni) {
		this.dni = dni;
	}

	/**
	 * Este método reimplementa el método equals de la clase Object, para que se pueda comprobar si dos usuarios
	 * son iguales, en función de su DNI y contraseña.
	 * @param Object o (Usuario) El usuario con el que comprobar el DNI y la contraseña.
	 * @return true si los DNIs y contraseñas coinciden. False en caso contrario. 
	 */
	public boolean equals (Object o){
		if(contrasena == null || contrasena.isEmpty())
			return (this.getClass()==o.getClass())&&(dni.equalsIgnoreCase(((Usuario)o).dni));
		else 
			return (this.getClass()==o.getClass())&&(dni.equalsIgnoreCase(((Usuario)o).dni) &&
				(contrasena.equals(((Usuario)o).contrasena)));
	}
	
	/**
	 * Este método implementa el compareTo , para que se puedan comparar dos usuarios según su DNI.
	 * @param o El usuario con el que comparar.
	 * @return 0 si los DNIs son iguales.
	 */	
	public int compareTo(Usuario o) {
		return dni.compareToIgnoreCase(o.dni);
	}
	
	/**
	 * Devuelve el DNI del usuario.
	 * @return dni El DNI del usuario.
	 */
	public String getDni(){
		return dni;
	}
	
	/**
	 * Devuelve el rol del usuario.
	 * @return rol El rol del usuario.
	 */
	public String getRol(){
		return rol;
	}

	
	/**
	 * Cambia el valor del atributo DNI en base al par�metro de entrada.
	 * @param d Nuevo DNI.
	 */	
	public void setDni(String d){
		dni = d;
	}
	
	/**
	 * Devuelve la clave de acceso del usuario.
	 * @return contrasena La clave de acceso del usuario.
	 */
	public String getContrasenia() {
		return contrasena;
	}
	
	/**
	 * Cambia el valor del atributo contraseña en base al par�metro de entrada.
	 * @param c Nueva contraseña.
	 */
	public void setContrasena(String c){
		contrasena = c;
	}
	
	/**
	 * Cambia el valor del atributo rol en base al parámetro de entrada.
	 * @param r Nuevo rol.
	 */ 
	public void setRol(String r) {
		rol = r;
	}

	/**
	 * Devuelve todos los datos del usuario.
	 * @return Todos los datos del usuario.
	 */
	public String toString(){
		return "Usuario " + contrasena + " " + dni + " " + rol;
	}
}
