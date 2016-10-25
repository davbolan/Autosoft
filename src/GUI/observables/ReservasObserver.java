package GUI.observables;

import java.util.ArrayList;

import modelo.dominio.gestion.Reserva;

/**
 * Interfaz Reserva observador
 * 
 */
public interface ReservasObserver {

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
	 * Metodo que nuestra información del DAO Reserva
	 * @param listaReservas
	 */
	public void mostrarReservas(ArrayList<Reserva> listaReservas);

	/**
	 * Metodo que nuestra información del las reservas de una persona
	 * @param listaReservas
	 */
	public void mostrarReservasPersona(ArrayList<Reserva> listaReservas);
}
