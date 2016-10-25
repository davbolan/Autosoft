package GUI.observables;

import java.util.ArrayList;

import modelo.dominio.vehiculo.Vehiculo;

/**
 * Interfaz vehiculo observador
 * 
 */
public interface VehiculosObserver {

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
	 * Metodo que nuestra información del DAO Vehiculo
	 * @param vehiculos
	 */
	public void informacionVehiculos(ArrayList<Vehiculo> vehiculos);

	/**
	 * Metodo que nuestra información de las matriculas
	 * @param matriculas
	 */
	void matriculaVehiculos(ArrayList<Vehiculo> matriculas);

}
