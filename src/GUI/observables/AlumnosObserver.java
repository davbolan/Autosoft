package GUI.observables;

import java.util.ArrayList;

import modelo.dominio.personas.Alumno;

/**
 * Interfaz alumnos observador
 * 
 */
public interface AlumnosObserver {

	/**
	 * Metodo que avisa a los observadores con un mensaje de informacion
	 * @param titulo El titulo del mensaje
	 * @param mensaje el mensaje
	 */
	public void mensajeInfo(String tipo, String mensaje);

	/**
	 * Metodo que avisa a los observador de que se produjo un error
	 * @param error
	 * @param mensaje
	 */
	public void showError(String error, String mensaje);
	
	/**
	 * Metodo que nuestra información del DAO Alumnos
	 * @param listaOrdenada
	 */
	public void infoAlumnos(ArrayList<Alumno> listaOrdenada);
}
