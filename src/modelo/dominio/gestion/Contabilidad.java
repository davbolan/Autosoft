package modelo.dominio.gestion;

import org.apache.commons.lang3.StringUtils;

/**
 * Clase que contiene los datos un ejercicio de contabilidad y los métodos auxiliares necesarios.
 * @author Fabricio Isaac Maldonado, David Bolanios
 */
public class Contabilidad implements Comparable<Contabilidad>{

	public static int numEjercicio;

	private String tipo;
	private String fecha;
	private double cantidad;
	private String concepto;
	private int id;
	
	/**
	 * Crea un ejercicio de contabilidad apartir de un ID
	 * @param id
	 * @param tipoEjercicio
	 * @param fecha
	 * @param cantidad
	 * @param concepto
	 */
	public Contabilidad(int id, String tipoEjercicio,String fecha, double cantidad, String concepto) {
		this.id = id;
		this.tipo = tipoEjercicio;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.concepto = concepto;
	}
	
	/**
	 * Crea una contabilidad
	 * @param id
	 * @param tipoEjercicio
	 * @param fecha
	 * @param cantidad
	 * @param concepto
	 */
	public Contabilidad(String tipoEjercicio,String fecha, double cantidad, String concepto) {
		numEjercicio++;
		this.id = numEjercicio;
		this.tipo = tipoEjercicio;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.concepto = concepto;		
	}
	
	/**
	 * Reimplementa el compareTo
	 * @param la conatibildiad con la que comparara
	 * @return 0 si son iguales
	 */
	public int compareTo(Contabilidad contabilidad) {
		return id - contabilidad.id;
	}
	
	
	/**
	 * Devuelve la fecha en la que se realizó el ejercicio
	 * @return la fecha en la que se realizó el ejercicio
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Establece la fecha en la que se realizó el ejercicio
	 * @param la fecha en la que se realizó el ejercicio
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve la cantidad de dinero del ejercicio
	 * @return la cantidad de dinero del ejercicio
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * Establece la cantidad de dinero del ejercicio
	 * @param la cantidad de dinero del ejercicio
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Devuelve el concepto del ejercicio
	 * @return el concepto del ejercicio
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Establece el concepto del ejercicio
	 * @param el concepto del ejercicio
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * Devuelve el tipo de ejercicio
	 * @return el tipo de ejercicio
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Establece el tipo de ejercicio
	 * @param el tipo de ejercicio
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Devuelve el identificador del ejercicio
	 * @return el identificador del ejercicio
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el identificador del ejercicio
	 * @param el identificador del ejercicio
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Devuelve todos los datos del ejercicio.
	 * @return Todos los datos del ejercicio.
	 */
	public String toString() {
		return "Contabilidad " + 
				id 		 + " " + 
				tipo 	 + " " +  
				fecha 	 + " " + 
				cantidad + " " + 
				StringUtils.replace(concepto, " ", "_");
	}
}

