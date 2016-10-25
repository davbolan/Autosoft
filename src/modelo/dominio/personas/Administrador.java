package modelo.dominio.personas;



/**
 * Clase que contiene los datos de un adminsitrador y los métodos auxiliares necesarios.
 * @author Fabricio Isaac Maldonado, David Bolanios
 */
public class Administrador extends Personal {

	/**
	 * Crea un administrador a partir del DNI.
	 * @param dni DNI del administrador.
	 */
	public Administrador(String dni){
		super(dni);
	}
	
	
	/**
	 * Crea un administrador
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param sueldo
	 * @param nSS
	 */
	public Administrador(String dni, String nombre, String apellido, int sueldo,
			int nSS, String horario, String fechaNacimiento, int telefono,
			String cargo, String direccion) {
		super(dni, nombre, apellido, sueldo, nSS, horario, fechaNacimiento,
				telefono, cargo, direccion);
	}

	
	/**
	 * Devuelve todos los datos del administrador.
	 * @return Todos los datos del administrador.
	 */
	public String toString() {
		return "Administrador " + super.toString();
	}

	/**
	 * Llama al compareTo de personal.
	 */
	public int compareTo(Administrador o) {
		return super.compareTo(o);	
	}
}
