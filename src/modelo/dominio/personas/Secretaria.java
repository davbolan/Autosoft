package modelo.dominio.personas;


/**
 * Clase que contiene los datos de una secretaria y los métodos auxiliares necesarios.
 * @author Fabricio Isaac Maldonado, David Bolanios
 */
public class Secretaria extends Personal{

	/**
	 * Crea una secretaria partir del DNI.
	 * @param dni DNI de la secretaria.
	 */
	public Secretaria(String dni) {
		super(dni);
	}
	
	/**
	 * Crea una secretaria
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param sueldo
	 * @param nSS
	 * @param horario
	 * @param fechaNacimiento
	 * @param telefono
	 * @param cargo
	 * @param direccion
	 */
	public Secretaria(String dni, String nombre, String apellido, int sueldo,
			int nSS, String horario, String fechaNacimiento, int telefono,
			String cargo, String direccion) {
		super(dni, nombre, apellido, sueldo, nSS, horario, fechaNacimiento,
				telefono, cargo, direccion);
	}

	/** 
	 * Llama al compareTo de personal
	 */
	public int compareTo(Personal o) {
		return super.compareTo(o);		
	}
	
	/**
	 * Devuelve todos los datos de la secretaria.
	 * @return Todos los datos de la secretaria.
	 */
	public String toString() {
		return "Secretaria " + super.toString();
	}
}
