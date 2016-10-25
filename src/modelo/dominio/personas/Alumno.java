package modelo.dominio.personas;

import org.apache.commons.lang3.StringUtils;



/**
 * @author samsung
 *
 */
public class Alumno extends Persona implements Comparable<Alumno>{

	private int nivel;
	private String permiso;
	private String direccion;
	private String profesor;
	private String fechaMatricula;
	private String fechaNacimiento;
	private boolean activo;
	private int telefono;

	
	
	

	/**
	 * Crea un alumno a partir del DNI.
	 * @param dni DNI del alumno.
	 */
	public Alumno(String dni){
		super(dni);
	}
	
	/**
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param nivel
	 * @param permiso
	 * @param direccion
	 * @param profesor
	 * @param fechaMatricula
	 * @param activo
	 */
	public Alumno(String dni, String nombre, String apellido, String fechaNacimiento,
			int nivel, String permiso, String direccion, 
			String profesor, String fechaMatricula, int telefono, boolean activo) {
		super(dni, nombre, apellido);
		this.fechaNacimiento = fechaNacimiento;
		this.nivel = nivel;
		this.permiso = permiso;
		this.direccion = direccion;
		this.profesor = profesor;
		this.fechaMatricula = fechaMatricula;
		this.telefono = telefono;
		this.activo = activo;
	}

	
	@Override
	public String toString() {
		
		return "Alumno " + super.toString() + " " 
				+ StringUtils.replace(fechaNacimiento, " ", "_") + " " 
				+ nivel + " " 
				+ StringUtils.replace(permiso, " ", "_") + " " 
				+ StringUtils.replace(direccion, " ", "_") + " " 
				+ StringUtils.replace(profesor, " ", "_")  + " " 
				+ StringUtils.replace(fechaMatricula, " ", "_") + " " 
				+ telefono + " " 
				+ activo;
	}
	
	/**
	 * Cambia atributo activo, si esta en true lo hace false y viceversa
	 */
	public void cambiarEstado() {
		activo = !activo;
	}
	
	@Override
	public int compareTo(Alumno o) {
		return super.compareTo(o);	
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getPermiso() {
		return permiso;
	}

	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	public String getFechaMatricula() {
		return fechaMatricula;
	}

	public void setFechaMatricula(String fechaMatricula) {
		this.fechaMatricula = fechaMatricula;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	/**
	 * 
	 * @return atributo activo
	 */
	public boolean esActivo() {
		return this.activo;
	}
	
	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}
