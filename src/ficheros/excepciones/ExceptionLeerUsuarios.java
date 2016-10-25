/**
 * 
 */
package ficheros.excepciones;

/**
 * Clase con las excepciones que se puede producir al leer un fichero de usuarios
 * @author Fabricio Isaac Maldonado
 */
@SuppressWarnings("serial")
public class ExceptionLeerUsuarios extends Exception {

	/**
	 * Constructora por defecto de la excepcion
	 */
	public ExceptionLeerUsuarios() {
	}

	/**
	 * @param arg0
	 * Constructora con parametro String de la excepcion
	 */
	public ExceptionLeerUsuarios(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * Constructora con parametro Objeto Throwable de la excepcion
	 */
	public ExceptionLeerUsuarios(Throwable arg0) {
		super(arg0);
	}

}
