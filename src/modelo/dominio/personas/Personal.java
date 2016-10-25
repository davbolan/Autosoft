package modelo.dominio.personas;

import org.apache.commons.lang3.StringUtils;


/**
 * Clase que contiene los datos básicos de un personal y los métodos auxiliares necesarios.
 * @author Fabricio Isaac Maldonado, David Bolanios
 */
public class Personal extends Persona implements Comparable<Personal> {

	protected int sueldo;
	protected int nSS;
	protected String horario;
	protected String fechaNacimiento;
	protected int telefono;
	protected String cargo;
	protected String direccion;
	
	
	/**
	 * Crea un personal a partir del DNI.
	 * @param dni DNI del personal.
	 */
	public Personal(String dni){
		super(dni);
	}
	
	
	/**
	 * Crea un personal a partir del DNI.
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
	public Personal(String dni, String nombre, String apellido, 
			int sueldo, int nSS, String horario, String fechaNacimiento, 
			int telefono, String cargo, String direccion) {
		super(dni, nombre, apellido);
		this.sueldo=sueldo;
		this.nSS=nSS;
		this.horario=horario;
		this.fechaNacimiento=fechaNacimiento;
		this.telefono=telefono;
		this.cargo=cargo;
		this.direccion=direccion;
	}

	
	/**
	 * Devuelve todos los datos del personal.
	 * @return Todos los datos del personal.
	 */
	public String toString() {
		return super.toString() + " " 
				+ sueldo + " " 
				+ nSS + " "
				+ StringUtils.replace(horario, " ", "_") + " " 
				+ StringUtils.replace(fechaNacimiento, " ", "_") + " " 
				+ telefono + " "
				+ StringUtils.replace(cargo, " ", "_") + " " 
				+ StringUtils.replace(direccion, " ", "_");
	}
	

	/**
	 * Llama al compareTo de persona.
	 */
	public int compareTo(Personal o) {
		return super.compareTo(o);		
	}


	/**
	 * Devuelve el sueldo del personal.
	 * @return sueldo del personal
	 */
	public int getSueldo() {
		return sueldo;
	}

	/**
	 * Establece el sueldo del personal.
	 * @param sueldo del personal
	 */
	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}


	/**
	 * Devuelve el número de seguridad social del personal.
	 * @param el número de seguridad social del personal
	 */
	public int getnSS() {
		return nSS;
	}


	/**
	 * Establece el número de seguridad social del personal.
	 * @param el número de seguridad social del personal
	 */
	public void setnSS(int nSS) {
		this.nSS = nSS;
	}

	/**
	 * Devuelve el horario del personal.
	 * @return sueldo del personal
	 */
	public String getHorario() {
		return horario;
	}

	/**
	 * Establece el horario del personal.
	 * @param sueldo del personal
	 */
	public void setHorario(String horario) {
		this.horario = horario;
	}

	/**
	 * Devuelve la fecha de nacimiento del personal.
	 * @return la fecha de nacimiento del personal
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Establece la fecha de nacimiento del personal.
	 * @param la fecha de nacimiento del personal
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Devuelve el teléfono del personal.
	 * @return teléfono del personal
	 */
	public int getTelefono() {
		return telefono;
	}

	/**
	 * Establece el teléfono del personal.
	 * @param teléfono del personal
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	/**
	 * Devuelve el cargo del personal.
	 * @return cargo del personal
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * Establece el cargo del personal.
	 * @param cargo del personal
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * Devuelve la direccion del personal.
	 * @return la direccion del personal
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Establece la direccion del personal.
	 * @param la direccion del personal
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
