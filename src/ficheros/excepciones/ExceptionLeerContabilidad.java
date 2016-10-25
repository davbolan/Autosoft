package ficheros.excepciones;

/**
 * Clase con las excepciones que se puede producir al leer un fichero de contabilidad
 * @author Fabricio Isaac Maldonado
 */
@SuppressWarnings("serial")
public class ExceptionLeerContabilidad extends Exception {
	/**
	 * Constructora por defecto de la excepcion
	 */
	public ExceptionLeerContabilidad() {
	}

	/**
	 * @param arg0
	 * Constructora con parametro String de la excepcion
	 */
	public ExceptionLeerContabilidad(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * Constructora con parametro Objeto Throwable de la excepcion
	 */
	public ExceptionLeerContabilidad(Throwable arg0) {
		super(arg0);
	}
}
