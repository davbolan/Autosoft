/**
 * 
 */
package ficheros.excepciones;

/**
 * Clase con las excepciones que se puede producir al leer un fichero de vehiculos
 * @author Fabricio Isaac Maldonado
 */
@SuppressWarnings("serial")
public class ExceptionLeerVehiculos extends Exception {

	/**
	 * Constructora por defecto de la excepcion
	 */
	public ExceptionLeerVehiculos() {
	}

	/**
	 * @param arg0
	 * Constructora con parametro String de la excepcion
	 */
	public ExceptionLeerVehiculos(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * Constructora con parametro Objeto Throwable de la excepcion
	 */
	public ExceptionLeerVehiculos(Throwable arg0) {
		super(arg0);
	}

}
