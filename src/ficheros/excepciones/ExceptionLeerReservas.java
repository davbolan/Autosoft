/**
 * 
 */
package ficheros.excepciones;

/**
 * Clase con las excepciones que se puede producir al leer un fichero de reservas
 * @author Fabricio Isaac Maldonado
 */
@SuppressWarnings("serial")
public class ExceptionLeerReservas extends Exception {

	/**
	 * Constructora por defecto de la excepcion
	 */
	public ExceptionLeerReservas() {
	}

	/**
	 * @param arg0
	 * Constructora con parametro String de la excepcion
	 */
	public ExceptionLeerReservas(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * Constructora con parametro Objeto Throwable de la excepcion
	 */
	public ExceptionLeerReservas(Throwable arg0) {
		super(arg0);
	}
}
