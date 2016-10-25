package modelo.dominio.personas;

import org.apache.commons.lang3.StringUtils;


/**
 * @author samsung
 *
 */
public abstract class Profesor extends Personal {

	protected boolean disponibilidad;
	protected String tipo;
	
	/**
	 * Crea un profesor a partir del DNI.
	 * @param nif Nif del profesor.
	 */
	public Profesor(String dni){
		super(dni);
	}
	
	/**
	 * Crea un profesor.
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
	 * @param disponibilidad
	 * @param tipo
	 */
	public Profesor(String dni, String nombre, String apellido, int sueldo,
			int nSS, String horario, String fechaNacimiento, int telefono,
			String cargo, String direccion, boolean disponibilidad, String tipo) {
		super(dni, nombre, apellido, sueldo, nSS, horario, fechaNacimiento,
				telefono, cargo, direccion);
		this.disponibilidad = disponibilidad;
		this.tipo = tipo;
	}

	/**
	 * Devuelve todos los datos del profesor.
	 * @return Todos los datos del profesor.
	 */
	public String toString() {
		return super.toString() + " " + disponibilidad + " " + StringUtils.replace(tipo, " ", "_");
	}

	/**
	 * Devuelve la disponibilidad del profesor.
	 * @return la disponibilidad del profesor.
	 */
	public boolean getDisponibilidad() {
		return disponibilidad;
	}

	/**
	 * Establece la disponibilidad del profesor.
	 * @param la disponibilidad del profesor.
	 */
	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	/**
	 * Devuelve el tipo del profesor.
	 * @param el tipo del profesor.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Establece el tipo del profesor.
	 * @param el tipo del profesor.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Llama al compareTo de persona.
	 */
	public int compareTo(Personal o) {
		return super.compareTo(o);		
	}
}
