package GUI;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controlador.Controlador;

/**
 * Clase abstracta, de la cual heredan los paneles de las ditintas GUIs,
 * presenta varios métodos comunes que son implentados por sus subclases.
 * @author David Bolanios
 */
@SuppressWarnings("serial")
public abstract class AbstractPanel extends JPanel {
	protected Controlador controlador;
	protected static final int YES = JOptionPane.YES_OPTION;
	
	/**
	 * Contrsuctor por defecto.
	 */
	public AbstractPanel() {
	}
	
	/**
	 * Contructor con parámetro del AbstractPanel
	 * @param controlador
	 */
	public AbstractPanel(Controlador controlador){
		this.controlador = controlador;
	}	

	/**
	 * Elimina el observador del panel correspondiente
	 */
	public abstract void ocultarPanel();
	
	/**
	 * Registra un observador del panel correspondiente
	 */
	public abstract void mostrarPanel();
}
