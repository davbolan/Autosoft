package GUI.profesor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import GUI.GUI;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz de Profesor de la aplicación 
 * @author David Bolanios, Frank Julca
 *
 */
@SuppressWarnings("serial")
public class GUIProfesor extends GUI {
	private JMenuItem itemHorario;
	private PanelInfoProfesor infoPanel;
	private PanelHorario panelHorario;

	/**
	 * Constructor de la interfaz profesor
	 * @param controlador
	 * @param usuario
	 * @param rol
	 */
	public GUIProfesor(Controlador controlador, String usuario, String rol) {
		super("Menu Profesor", controlador);
		
		initProfesorFrame(controlador,usuario,rol);
	}
	
	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz profesor
	 * @param controlador
	 * @param usuario
	 * @param rol
	 */
	private void initProfesorFrame(Controlador controlador, String usuario, String rol) {
		this.setVisible(true);
		initMenu();
        
        infoPanel = new PanelInfoProfesor(usuario,rol); 
        panelActual = infoPanel;
        panelHorario = new PanelHorario(controlador);
                
        container.add(menuBar, BorderLayout.NORTH); 
        container.add(panelActual,BorderLayout.CENTER);
        
        fixMenusListener();
    	this.setVisible(true);
	}

	/**
	 * Inicializa el la barra de menu de la interfaz profesor
	 */
	private void initMenu() {
		
		JMenu menuHorario 	  	 = new JMenu("Horario");
		itemHorario	 			 = new JMenuItem("Ver horario");	
		menuHorario.add(itemHorario);
		
		menuBar.add(menuHorario);
		menuBar.add(menuAyuda);
		menuBar.add(menuSalir);
	}
	
	/**
	 * Procesa los distintos eventos del menu
	 */
	private void fixMenusListener() {
		fixHorarioListener();
		fixMiInfo();
	}


	/**
	 * Procesa el evento sobre la opción "Mis datos" del menu ayuda
	 */
	private void fixMiInfo() {
		itemMiInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();
				panelActual = infoPanel;
				mostrarNuevoPanel();
			}
		});	
	}

	/**
	 * Procesa el evento sobre la opción "Horario" del menu
	 */
	private void fixHorarioListener() {
		
		this.itemHorario.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg) {
				ocultarAntiguoPanel();			
				panelActual = panelHorario;	// cambio de panel
				mostrarNuevoPanel();	
			}
		});
	}
}

