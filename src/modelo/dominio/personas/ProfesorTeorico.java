package modelo.dominio.personas;


/**
 * Clase que contiene los datos de un profesor teorico y los métodos auxiliares necesarios.
 * @author Fabricio Isaac Maldonado, David Bolanios
 */
public class ProfesorTeorico extends Profesor {

	private int aula;
	
	/**
	 * Crea un profesor de teoria a partir del DNI.
	 * @param dni DNI del profesor.
	 */
	public ProfesorTeorico(String dni){
		super(dni);
	}
	
	/**
	 * Crea un profesor de teoria
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
	 * @param aula
	 */
	public ProfesorTeorico(String dni, 
			String nombre, String apellido,
			int sueldo, int nSS, String horario, String fechaNacimiento,
			int telefono, String cargo, String direccion,
			boolean disponibilidad, String tipo, int aula) {
		super(dni, nombre, apellido, sueldo, nSS, horario, fechaNacimiento,
				telefono, cargo, direccion, disponibilidad, tipo);
		this.aula = aula;
	}

	/**
	 * Devuelve todos los datos del profesor de teoria.
	 * @return Todos los datos del profesor de teoria.
	 */
	public String toString() {
		return "ProfesorTeorico " + super.toString() + " " + aula;
	}
	
	/**
	 * Devuelve el aula asignado al profesor de teoria.
	 * @return el aula asignado al profesor de teoria.
	 */
	public int getAula() {
		return aula;
	}
	
	
	/**
	 * Establece el aula asignado al profesor de teoria.
	 * @param el aula asignado al profesor de teoria.
	 */
	public void setAula(int aula) {
		this.aula = aula;
	}
	
	/**
	 * LLama al compareTo de personal.
	 */
	public int compareTo(Personal o) {
		return super.compareTo(o);		
	}
}
