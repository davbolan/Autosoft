package GUI.administrador;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import GUI.GUI;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz de Administrador de la aplicación 
 * @author David Bolanios, Frank Julca
 */
@SuppressWarnings("serial")
public class GUIAdministrador extends GUI{
		
	private JMenuItem itemAltaUsuario;
	private JMenuItem itemModificarUsuario;
	
	private JMenuItem itemAltaPersonal;
	private JMenuItem itemBajaModPersonal;
	private JMenuItem itemInformacionPersonal;
	
	private JMenuItem itemAltaProfesor;
	private JMenuItem itemBajaModProfesor;
	
	private JMenuItem itemNuevoEjercicio;
	private JMenuItem itemMostrarBalance;
		
	private PanelInfoAdmin infoPanel;
	private PanelAltaUsuario altaUsuario;
	private PanelBajaModUsuario bajaModUsuario;
	private PanelAltaProfesor altaProfesor;
	private PanelBajaModProfesor bajaModProfesor;
	private PanelAltaPersonal altaPersonal;
	private PanelBajaModPersonal bajaModPersonal;
	private PanelInfoPersonal informacionPersonal;
	private PanelNuevoEjercicio nuevoEjercicio;
	private PanelBalance mostrarBalance;
	
	public GUIAdministrador(Controlador controlador, String usuario, String rol){
		super("Menu Administrador", controlador);	
		initAdminFrame(controlador, usuario, rol);
	}

	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz Administrador
	 * @param controlador
	 * @param usuario
	 * @param rol
	 */
	private void initAdminFrame(Controlador controlador, String usuario, String rol) {		
		initMenu();
		
		infoPanel 			= new PanelInfoAdmin(usuario, rol);
		altaUsuario			= new PanelAltaUsuario(controlador);
		bajaModUsuario 		= new PanelBajaModUsuario(controlador);
		altaProfesor 		= new PanelAltaProfesor(controlador);
		bajaModProfesor 	= new PanelBajaModProfesor(controlador);
		altaPersonal 		= new PanelAltaPersonal(controlador);
		bajaModPersonal 	= new PanelBajaModPersonal(controlador);
		informacionPersonal = new PanelInfoPersonal(controlador);
		nuevoEjercicio 		= new PanelNuevoEjercicio(controlador);
		mostrarBalance 		= new PanelBalance(controlador);
		panelActual 		= infoPanel;
		
		container.add(menuBar, BorderLayout.NORTH);
		container.add(infoPanel, BorderLayout.CENTER);

		fixMenusListener();
		this.setVisible(true);
	}
	
	/**
	 * Inicializa el la barra de menu de la interfaz Administrador
	 */
	private void initMenu() {	
		JMenu menuUsuarios 	  = new JMenu("Usuarios");
		itemAltaUsuario 	  = new JMenuItem("Añadir usuario");
		itemModificarUsuario  = new JMenuItem("Eliminar/Modificar usuario");	
		menuUsuarios.add(itemAltaUsuario);
		menuUsuarios.add(itemModificarUsuario);
			
		JMenu menuPersonal 	  	 = new JMenu("Personal");	
		JMenu menuProfesor	  	 = new JMenu("Profesor");
		itemAltaProfesor 	  	 = new JMenuItem("Añadir profesor");
		itemBajaModProfesor 	 = new JMenuItem("Eliminar/Modificar profesor");
		menuProfesor.add(itemAltaProfesor);
		menuProfesor.add(itemBajaModProfesor);
		JMenu menuRestoEmpleados = new JMenu("Resto de empleados");
		itemAltaPersonal 	 	 = new JMenuItem("Añadir empleado");
		itemBajaModPersonal 	 = new JMenuItem("Eliminar/Modificar empleado");
		menuRestoEmpleados.add(itemAltaPersonal);
		menuRestoEmpleados.add(itemBajaModPersonal);
		itemInformacionPersonal  = new JMenuItem("Información personal");
		menuPersonal.add(menuProfesor);
		menuPersonal.add(menuRestoEmpleados);
		menuPersonal.add(itemInformacionPersonal);
		
		JMenu menuBalance	  	= new JMenu("Balance");
		itemNuevoEjercicio 	  	= new JMenuItem("Añadir Gasto/Ingreso");
		itemMostrarBalance 		= new JMenuItem("Mostrar Balance");
		menuBalance.add(itemNuevoEjercicio);
		menuBalance.add(itemMostrarBalance);
					
		menuBar.add(menuUsuarios);
		menuBar.add(menuPersonal);
		menuBar.add(menuBalance);
		menuBar.add(menuAyuda);
		menuBar.add(menuSalir);
	}	
	
	/**
	 * Procesa los distintos eventos del menu
	 */
	private void fixMenusListener() {
		fixAltaUsuarioListener();
		fixModBajaUsuarioListener();
		fixAltaProfesorListener();
		fixModBajaProfesorListener();
		fixAltaPersonalListener();
		fixModBajaPersonalListener();
		fixInformacionPersonal();
		fixMostrarBalanceListener();
		fixNuevoEjercicioListener();
		fixMiInfo();
	}

	/**
	 * Procesa el evento sobre la opción información personal del menu personal
	 */
	private void fixInformacionPersonal() {
		itemInformacionPersonal.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();			
				panelActual = informacionPersonal;
				mostrarNuevoPanel();
			}		
		});
	}
	
	/**
	 * Procesa el evento sobre la opción alta usuario del menu personal
	 */
	private void fixAltaUsuarioListener() {
		itemAltaUsuario.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();			
				panelActual = altaUsuario;
				mostrarNuevoPanel();		
			}		
		});
	}
	
	/**
	 * Procesa el evento sobre la opción modificar/baja usuario del menu personal
	 */
	private void fixModBajaUsuarioListener() {		
		itemModificarUsuario.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();			
				panelActual = bajaModUsuario;
				mostrarNuevoPanel();
			}		
		});
	}

	/**
	 * Procesa el evento sobre la opción alta personal del menu personal
	 */
	private void fixAltaPersonalListener() {
		itemAltaPersonal.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();			
				panelActual = altaPersonal;
				mostrarNuevoPanel();	
			}		
		});
	}

	/**
	 * Procesa el evento sobre la opción modificación/baja personal del menu personal
	 */
	private void fixModBajaPersonalListener() {
		itemBajaModPersonal.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();			
				panelActual = bajaModPersonal;
				mostrarNuevoPanel();		
			}		
		});
	}

	/**
	 * Procesa el evento sobre la opción alta profesor del menu personal
	 */
	private void fixAltaProfesorListener() {
		itemAltaProfesor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();			
				panelActual = altaProfesor;
				mostrarNuevoPanel();	
			}		
		});
	}

	/**
	 * Procesa el evento sobre la opción Modificar/baja de profesor del menu personal
	 */
	private void fixModBajaProfesorListener() {
		itemBajaModProfesor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();			
				panelActual = bajaModProfesor;
				mostrarNuevoPanel();		
			}		
		});
	}
	
	/**
	 * Procesa el evento sobre la opción añadir gasto/ingreso del menu contabilidad
	 */
	private void fixNuevoEjercicioListener() {
		itemNuevoEjercicio.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();			
				panelActual = nuevoEjercicio;
				mostrarNuevoPanel();			
			}		
		});
	}

	/**
	 * Procesa el evento sobre la opción mostrar balance del menu contabilidad
	 */
	private void fixMostrarBalanceListener() {
		itemMostrarBalance.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();			
				panelActual = mostrarBalance;
				mostrarNuevoPanel();				
			}		
		});
	}

	/**
	 * Procesa el evento sobre la interfaz principal 
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
}
