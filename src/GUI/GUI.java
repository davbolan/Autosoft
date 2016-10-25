package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controlador.Controlador;


/**
 * Clase abstracta de la cual heredan las distintas GUIs, 
 * presenta varios métodos que son comunes a las distintas GUIs.
 * @author David Bolanios
 */
@SuppressWarnings("serial")
public abstract class GUI extends JFrame{
	
	protected static final int YES = JOptionPane.YES_OPTION;
	protected Container container;
	protected Controlador controlador;
	protected AbstractPanel panelActual;
	protected final static String imageFolder = "imagenes";
	

	protected JMenuBar menuBar = new JMenuBar();
	protected JMenu menuAyuda;
	protected JMenuItem itemMiInfo;
	protected JMenu menuSalir = new JMenu("Salir");
	protected JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión");
	
	/**
	 * Contructor de la GUI, a partir de sus parametros.
	 * @param titulo titulo de la GUI
	 * @param controlador
	 */
	public GUI(String titulo, Controlador controlador){
		super(titulo);
		this.controlador = controlador;
		this.container = this.getContentPane();
		this.container.setLayout(new BorderLayout());
		this.setIconImage(new ImageIcon(GUI.class.getResource(imageFolder + "/logoLogin.jpg")).getImage());
		this.setVisible(false);
		this.setSize(640, 480);
		this.setResizable(false);
		setLocationRelativeTo(null);
		initMenuItemComunes();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Inicicializa los distintos botones, menus y oyentes de una ventana de GUI genérica.
	 */
	private void initMenuItemComunes() {
		menuAyuda = new JMenu("Ayuda");
		itemMiInfo = new JMenuItem("Mis Datos");
		menuAyuda.add(itemMiInfo);
			
		menuSalir = new JMenu("Salir");
		itemCerrarSesion = new JMenuItem("Cerrar sesión");
		menuSalir.add(itemCerrarSesion);
		
		fixCerrarSesionListener();
	}
	
	/**
	 * Establece el cierre de la ventana por defecto para que cuando se pulse, se pregunte
	 * al usuario si realemtne quiere salir. Si es asi, se guardan datos y se sale de la aplicación.
	 */
	protected void processWindowEvent(WindowEvent e) {	 
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			int selection = JOptionPane.showConfirmDialog(null,
					"¿Realmente deseas salir?", "Salir",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
					null);
			if (YES == selection) {
				controlador.escribirDatos();
				System.exit(0);
			}
		}	
	}
	
	/**
	 * Elimina el observador del panel actual y modifica la visibilidad de panel actual
	 */
	protected void ocultarAntiguoPanel(){
		panelActual.ocultarPanel();							 	// me oculto
		panelActual.setVisible(false);						 	// oculto el panel actual
	}
	
	/**
	 * Registra un observador al panel actual, modifica la visibilidad del panel actual 
	 * y añade el panel actual a la interfaz actual. 
	 */
	protected void mostrarNuevoPanel(){
		panelActual.mostrarPanel();					 		 	// añado como observador el panel actual
		panelActual.setVisible(true);						 	// me muestro
		container.add(panelActual, BorderLayout.CENTER); 		// me añado a la GUI
		validate();
	}
	
	/**
	 * Procesa el evento de cerrar sesión, a través del menu "Cerrar Sesión".
	 */
	private void fixCerrarSesionListener() {	
		itemCerrarSesion.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent arg) {
				int selection = JOptionPane.showConfirmDialog(null, "¿Deseas cerrar sesión actual?", "Salir",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
						null);
				if (selection == YES) {
					controlador.removeAllObservers();
					new Login(controlador);
					dispose();
				}
			}		
		});
	}
}
