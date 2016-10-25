package main;


import modelo.negocio.Autoescuela;
import GUI.Login;
import controlador.Controlador;

/**
 * Clase principal de la acplicación
 * @author Bolaños
 *
 */
public class Main {

	/**
	 * Método dencargado de crear y enlazar el modelo, vista y controlador
	 * @param args sin argumentos
	 */
	public static void main(String[] args) {
		Autoescuela autoescuela = new Autoescuela();
		Controlador controlador = new Controlador(autoescuela);
		new Login(controlador);
	}
}
