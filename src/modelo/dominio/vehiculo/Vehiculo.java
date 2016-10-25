package modelo.dominio.vehiculo;

import org.apache.commons.lang3.StringUtils;

/**
 * Clase que contiene los datos de un vehiculo y los métodos auxiliares necesarios.
 * @author Fabricio Isaac Maldonado, David Bolanios
 */
public class Vehiculo implements Comparable<Vehiculo>{
	private String matricula;
	private int anio;
	private String marca;
	private String modelo;
	private String estado;
	private String ITV;
	private String tipo;
	private boolean disponibilidad;
	
	/**
	 * Crea un nuevo vehículo
	 * @param matricula
	 */
	public Vehiculo(String matricula) {
		this.matricula = matricula;
	}
	
	/**
	 * Crea un nuevo vehículo
	 * @param matricula
	 * @param anio
	 * @param marca
	 * @param modelo
	 * @param estado
	 * @param iTV
	 * @param tipo
	 * @param disponibilidad
	 */
	public Vehiculo(String matricula, int anio, String marca, String modelo,
			String estado, String iTV, String tipo, boolean disponibilidad) {
		this.matricula = matricula;
		this.anio = anio;
		this.marca = marca;
		this.modelo = modelo;
		this.estado = estado;
		this.ITV = iTV;
		this.tipo = tipo;
		this.disponibilidad = disponibilidad;
	}

	

	/**
	 * Devuelve todos los datos del vehículo.
	 * @return Todos los datos del vehículo.
	 */
	public String toString() {
		return "Vehiculo " 
				+ StringUtils.replace(matricula, " ", "_") + " " 
				+ anio + " " 
				+ StringUtils.replace(marca, " ", "_") 	+ " " 
				+ StringUtils.replace(modelo, " ", "_") + " " 
				+ StringUtils.replace(estado, " ", "_") + " " 
				+ StringUtils.replace(ITV, " ", "_") 	+ " " 
				+ StringUtils.replace(tipo, " ", "_") 	+ " " 
				+ disponibilidad;
		
	}
	
	/**
	 * Este método reimplementa el método equals de la clase Object, para que se pueda comprobar si dos vehiculos
	 * son iguales, en función de su matrícula.
	 * @param Object o (Vehículo) El vehiculo con el que comprobar la matricula
	 * @return true si las matrículas coinciden. False en caso contrario. 
	 */
	public boolean equals (Object o) {
		return ((this.getClass()==o.getClass()) && matricula.equalsIgnoreCase(((Vehiculo)o).matricula));
	}
	
	/**
	 * Este método implementa el compareTo , para que se puedan comparar dos vehiculos según su matrícula.
	 * @param o El vehiculo con el que comparar.
	 * @return 0 si las matrículas son iguales.
	 */	
	@Override
	public int compareTo(Vehiculo vehiculo) {
		return matricula.compareToIgnoreCase(vehiculo.matricula);
	}
	
	/**
	 * Este método implementa el compareTo alternativo, para que se puedan comparar dos vehiculos según su marca,
	 * mode y matricula.
	 * @param o El vehiculo con el que comparar.
	 * @return 0 si las matrículas son iguales.
	 */	
	public int miCompareTo(Vehiculo vehiculo) {
		int compMarca 	  = marca.compareToIgnoreCase(vehiculo.marca);
		int compModelo 	  = modelo.compareToIgnoreCase(vehiculo.modelo);
		int compMatricula = matricula.compareToIgnoreCase(vehiculo.matricula);

		if (compMarca == 0) {
			if (compModelo == 0)
				return compMatricula;
			else
				return compModelo;
		} 
		else
			return compMarca;
	}
	
	
	/**
	 * Devuelve la matricula del vehiculo
	 * @return la matricula del vehiculo
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Establece la matricula del vehiculo
	 * @param la matricula del vehiculo
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * Devuelve la antigüedad del vehiculo
	 * @return la antigüedad del vehiculo
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * Establece la antigüedad del vehiculo
	 * param la antigüedad del vehiculo
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}

	/**
	 * Devuelve la marca del vehiculo
	 * @return la marca del vehiculo
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Establece la marca del vehiculo
	 * param la marca del vehiculo
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Devuelve el modelo del vehiculo
	 * @return el modelo del vehiculo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * Establece el modelo del vehiculo
	 * @param el modelo del vehiculo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * Devuelve el estado del vehiculo
	 * @return el modelo del vehiculo
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Establece el estado del vehiculo
	 * @param el modelo del vehiculo
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Devuelve la proxima fecha para de revisión de la ITV del vehiculo
	 * @return la proxima fecha para de revisión de la ITV  del vehiculo
	 */
	public String getITV() {
		return ITV;
	}

	/**
	 * Establece la proxima fecha para de revisión de la ITV del vehiculo
	 * param la proxima fecha para de revisión de la ITV  del vehiculo
	 */
	public void setITV(String iTV) {
		ITV = iTV;
	}

	/**
	 * Devuelve el tipo de vehiculo
	 * @return el tipo de vehiculo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Establece el tipo de vehiculo
	 * @param el tipo de vehiculo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Devuelve la disponibilidad del vehiculo
	 * @return la disponibilidad del vehiculo
	 */
	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	/**
	 * Establece la disponibilidad del vehiculo
	 * param la disponibilidad del vehiculo
	 */
	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}


}
