package GUI.observables;


import java.util.List;

import modelo.dominio.gestion.Contabilidad;

/**
 * Interfaz contabilidad observador
 * 
 */
public interface ContabilidadObserver {

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
	 * Metodo que nuestra información del DAO Contabilidad
	 * @param ingresos
	 * @param gastos
	 * @param total
	 * @param subtotalIngresos
	 * @param subtotalGastos
	 */
	public void mostrarBalance(List<Contabilidad> ingresos, List<Contabilidad> gastos, 
								double total, double subtotalIngresos, double subtotalGastos);


}
