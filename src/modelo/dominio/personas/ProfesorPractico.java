package modelo.dominio.personas;

import org.apache.commons.lang3.StringUtils;

import modelo.dominio.vehiculo.Vehiculo;


/**
 * Clase que contiene los datos de un profesor practico y los métodos auxiliares necesarios.
 * @author Fabricio Isaac Maldonado, David Bolanios
 */
public class ProfesorPractico extends Profesor {

	private Vehiculo vehiculo;
	
	/**
	 * Crea un profesor de practicas a partir del DNI.
	 * @param dni DNI del profesor practico.
	 */
	public ProfesorPractico(String dni){
		super(dni);
	}
	
	/**
	 * Crea un profesor de practicas
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
	 * @param vehiculo
	 */
	public ProfesorPractico(String dni, String nombre, String apellido,
			int sueldo, int nSS, String horario, String fechaNacimiento,
			int telefono, String cargo, String direccion,
			boolean disponibilidad, String tipo, Vehiculo vehiculo) {
		super(dni, nombre, apellido, sueldo, nSS, horario, fechaNacimiento,
				telefono, cargo, direccion, disponibilidad, tipo);
		this.vehiculo=vehiculo;
	}

	/**
	 * Devuelve todos los datos del profesor practico.
	 * @return Todos los datos del profesor practico.
	 */
	@Override
	public String toString() {
		return "ProfesorPractico " + super.toString() + " " 
				+ StringUtils.replace(vehiculo.getMatricula(), " ", "_");
	}

	/**
	 * Devuelve el vehículo asignado al profesor practico
	 * @return el vehículo asignado al profesor practico
	 */
	public Vehiculo getVehiculo() {
		return vehiculo;
	}


	/**
	 * Establece el vehículo asignado al profesor practico
	 * @return el vehículo asignado al profesor practico
	 */
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	/**
	 * LLama al compareTo de personal.
	 */
	public int compareTo(Personal o) {
		return super.compareTo(o);		
	}

}
