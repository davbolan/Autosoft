package modelo.dominio.personas;

import org.apache.commons.lang3.StringUtils;


/**
 * Clase que contiene los datos de otro tipo de personal y los métodos auxiliares necesarios.
 * @author Fabricio Isaac Maldonado, David Bolanios
 */
public class Otros extends Personal {

	private String descripcion;
	
	
	/**
	 * Crea un personal de otro tipo a partir del DNI.
	 * @param dni DNI del personal otros.
	 */
	public Otros(String dni){
		super(dni);
	}
	
	
	/**
	 * Crea un personal de otro tipo
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
	 * @param descripcion
	 */
	public Otros(String dni, String nombre, String apellido, int sueldo,
			int nSS, String horario, String fechaNacimiento, int telefono,
			String cargo, String direccion, String descripcion) {
		super(dni, nombre, apellido, sueldo, nSS, horario, fechaNacimiento,
				telefono, cargo, direccion);
		this.descripcion=descripcion;
	}


	/**
	 * Devuelve todos los datos del personal de otro tipo.
	 * @return Todos los datos del personal de otro tipo.
	 */
	public String toString() {
		return "Otros " + super.toString()+ " " + StringUtils.replace(descripcion, " ", "_");
	}


	/**
	 * Devuelve la descripcion del personal
	 * @return la descripcion del personal
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripcion del personal
	 * @param la descripcion del personal
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * LLama al compareTo de Personal
	 */
	public int compareTo(Personal o) {
		return super.compareTo(o);		
	}

}
