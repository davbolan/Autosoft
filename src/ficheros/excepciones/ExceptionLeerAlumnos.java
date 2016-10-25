package ficheros.excepciones;

/**
 * Clase con las excepciones que se puede producir al leer un fichero de alumnos
 * @author Fabricio Isaac Maldonado
 */
@SuppressWarnings("serial")
public class ExceptionLeerAlumnos extends Exception {

	/**
	 * Constructora por defecto de la excepcion
	 */
	public ExceptionLeerAlumnos() {
	}

	/**
	 * @param arg0
	 * Constructora con parametro String de la excepcion
	 */
	public ExceptionLeerAlumnos(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * Constructora con parametro Objeto Throwable de la excepcion
	 */
	public ExceptionLeerAlumnos(Throwable arg0) {
		super(arg0);
	}

}
