package GUI.observables;

import java.util.ArrayList;

import modelo.dominio.personas.Personal;
import modelo.dominio.personas.ProfesorPractico;
import modelo.dominio.personas.ProfesorTeorico;

/**
 * Interfaz Personal observador
 * 
 */
public interface PersonalObserver {

	/**
	 * Metodo que avisa a los observadores con un mensaje de informacion
	 * @param titulo El titulo del mensaje
	 * @param mensaje el mensaje
	 */
	public void mensajeInfo(String type, String message);
	
	/**
	 * Metodo que avisa a los observador de que se produjo un error
	 * @param error
	 * @param mensaje
	 */
	public void showError(String type, String message);

	/**
	 *Metodo que nuestra información del DAOPersonal
	 * @param listaOrdenada
	 */
	public void infoPersonal(ArrayList<Personal> listaOrdenada);
	
	/**
	 * Metodo que muesta los profesores disponibles por tipo
	 * @param profesoresT
	 * @param profesoresP
	 */
	public void profesorDisponibles(ArrayList<ProfesorTeorico> profesoresT,
								    ArrayList<ProfesorPractico> profesoresP);
}
