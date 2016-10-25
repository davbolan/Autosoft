package modelo.dominio.personas;

import org.apache.commons.lang3.StringUtils;

import utilidades.Utilidades;

/**
 * Clase que contiene los datos básicos de una persona y los métodos auxiliares necesarios.
 * @author Fabricio Isaac Maldonado, David Bolanios
 */
public abstract class Persona{
	protected String nombre;
	protected String apellidos;
	protected String dni;
	
	/**
	 * Crea una persona a partir del NIF de la persona.
	 * @param nif Nif de la persona.
	 */
	public Persona(String dni){
		this.dni = dni.toUpperCase();
		this.nombre = "";
		this.apellidos = "";
	}
	
	/**
	 * Constructor de persona
	 * @param String dni
	 * @param String nombre
	 * @param String apellido
	 */
	public Persona(String dni, String nombre, String apellidos) {
		this.dni = dni.toUpperCase();
		this.nombre = Utilidades.capitalize(nombre);
		this.apellidos = Utilidades.capitalize(apellidos);
	}

	/**
	 * Devuelve el DNI de la persona
	 * @return el DNI de la persona
	 */
	public String getDni() {
		return dni = dni.toUpperCase();
	}

	/**
	 * Establece el DNI de la persona
	 * param el DNI de la persona
	 */
	public void setDni(String dni) {
		this.dni = dni.toUpperCase();
	}

	/**
	 * Devuelve el nombre de la persona
	 * @return el nombre de la persona
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre de la persona
	 * @param el nombre de la persona
	 */
	public void setNombre(String nombre) {
		this.nombre = Utilidades.capitalize(nombre);
	}

	/**
	 * Devuelve los apellidos de la persona
	 * @return los apellidos de la persona
	 */
	public String getApellido() {
		return apellidos;
	}

	/**
	 * Establece los apellidos de la persona
	 * @param los apellidos de la persona
	 */
	public void setApellido(String apellidos) {
		this.apellidos = Utilidades.capitalize(apellidos);
	}
	
	/**
	 * Devuelve todos los datos de la persona.
	 * @return Todos los datos de la persona.
	 */
	public String toString(){
		return StringUtils.replace(dni, " ", "_") + " " 
				+ StringUtils.replace(nombre, " ", "_") + " " 
				+ StringUtils.replace(apellidos, " ", "_");
	}
	
	/**
	 * Redefinimos el metodo equals para comparar el atributo String dni
	 * @param Object o, que será de la clase Persona
	 */
	public boolean equals(Object o){
		return this.dni.equalsIgnoreCase(((Persona)o).dni);
	}
	
	/**
	 * Este método reimplementa el compareTo, para que se puedan comparar dos personas en función 
	 * de sus DNIs. 
	 * @param o La persona con el que comparar el DNI.
	 * @return 0 si los DNIs son iguales.
	 */
	public int compareTo(Persona persona) {
		return dni.compareTo(persona.dni);	
	}

	/**
	 * Este método implementa un compareTo, utilizado para ordenar la lista de Personas correspondiente.
	 * Primero se ordena por apellido, luego por nombre y por último, por DNI.
	 * @param o La persona con el que comparar el DNI.
	 * @return 0 si los DNIs son iguales.
	 */
	public int miCompareTo(Persona persona) {
		int compApellidos = apellidos.compareToIgnoreCase(persona.apellidos);
		int compNombre 	  = nombre.compareToIgnoreCase(persona.nombre);
		int compDNI 	  = dni.compareToIgnoreCase(persona.dni);
		
		 if(compApellidos == 0) {            
	            if(compNombre == 0) 
	                return compDNI;	            
	            else 
	                return compNombre;
		 }       
		 else 
			 return compApellidos;
	}
}
