package GUI.observables;

/**
 * Interfaz Usuario observador
 * 
 */
public interface UsuariosObserver {

	/**
	 * Abre interfaz de usuario
	 * @param usuario
	 * @param rol
	 */
	public void abrirVentanaUsuario(String usuario, String rol);
	
	/**
	 * Metodo que avisa a los observador de que se produjo un error
	 * @param error
	 * @param mensaje
	 */
	public void showError(String type, String message);

	/**
	 * Metodo que avisa a los observadores con un mensaje de informacion
	 * @param titulo El titulo del mensaje
	 * @param mensaje el mensaje
	 */
	public void mensajeInfo(String type, String message);
}
