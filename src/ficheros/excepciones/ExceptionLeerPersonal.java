/**
 * 
 */
package ficheros.excepciones;

/**
 * Clase con las excepciones que se puede producir al leer un fichero de personal
 * @author Fabricio Isaac Maldonado
 */
@SuppressWarnings("serial")
public class ExceptionLeerPersonal extends Exception {

	/**
	 * Constructora por defecto de la excepcion
	 */
	public ExceptionLeerPersonal() {
	}

	/**
	 * @param arg0
	 * Constructora con parametro String de la excepcion
	 */
	public ExceptionLeerPersonal(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * Constructora con parametro Objeto Throwable de la excepcion
	 */
	public ExceptionLeerPersonal(Throwable arg0) {
		super(arg0);
	}

}
