package GUI.alumno;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import GUI.GUI;
import controlador.Controlador;


/**
 * Clase que implementa de alumno de la aplicación 
 * @author David Bolaños, Frank Julca
 *
 */
@SuppressWarnings("serial")
public class GUIAlumno extends GUI {
	private JMenuItem itemHorario;
	private PanelInfoAlumno infoPanel;
	private PanelHorario panelHorario;


	/**
	 * Constructor de la interfaz alumno
	 * @param controlador
	 * @param usuario
	 * @param rol
	 */
	public GUIAlumno(Controlador controlador, String usuario, String rol) {
		super("Menu Alumno", controlador);	
		initAlumnoFrame(controlador,usuario,rol);
	}
	
	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz alumno
	 * 
	 */
	private void initAlumnoFrame(Controlador controlador, String usuario, String rol) { 
		initMenu();
        
        infoPanel = new PanelInfoAlumno(usuario,rol);
        panelActual = infoPanel;
        panelHorario= new PanelHorario(controlador);
        
        container.add(menuBar, BorderLayout.NORTH); 
        container.add(panelActual,BorderLayout.CENTER);
        
        fixMenusListener();
		this.setVisible(true);
	}

	/**
	 * Inicializa el la barra de menu de la interfaz alumno
	 */
	private void initMenu() {
	
		JMenu menuHorario 	  	= new JMenu("Horario");
		itemHorario	  			= new JMenuItem("Ver horario");	
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
	 * Procesa el evento sobre la opción "Mis datos" del menu
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
		
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();			
				panelActual = panelHorario;	// cambio de panel
				mostrarNuevoPanel();
			}
		});
		
	}
}

