package GUI.secretaria;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import GUI.GUI;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz de Secretaria de la aplicación 
 * @author David Bolanios, Frank Julca
 *
 */
@SuppressWarnings("serial")
public class GUISecretaria extends GUI {


	private JMenuItem itemAltaAlumno;
	private JMenuItem itemModificarAlumno;
	private JMenuItem itemHistorialAlumnos;
	private JMenuItem itemNuevoVehiculo;
	private JMenuItem itemBajaVehiculo;
	private JMenuItem itemInfoVehiculo;
	private JMenuItem itemNuevaReserva;
	private JMenuItem itemConsultarReservas;

	private PanelInfoSecre infoPanel;
	private PanelAltaAlumno altaAlumno;
	private PanelModAlumno modAlumno;
	private PanelHistorialAlumnos historialAlumnos;
	private VehiculoNuevo vehiculoNuevo;
	private VehiculoMod vehiculoMod;
	private PanelInfoVehiculos infoVehiculos;
	private PanelNuevaReserva nuevaReserva;
	private PanelConsultarReservas consultarReservas;

	/**
	 * Contructor de la interfaz Administrador
	 * @param controlador
	 * @param usuario
	 * @param rol
	 */
	public GUISecretaria(Controlador controlador, String usuario, String rol) {
		super("Menu Secretaria", controlador);
		initSecreFrame(controlador, usuario, rol);
	}

	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz Secretaria
	 * @param controlador
	 * @param usuario
	 * @param rol
	 */
	private void initSecreFrame(Controlador controlador, String usuario, String rol) {
		this.setVisible(true);
		initMenu();

		infoPanel = new PanelInfoSecre(usuario, rol);
		altaAlumno = new PanelAltaAlumno(controlador);
		modAlumno = new PanelModAlumno(controlador);
		historialAlumnos = new PanelHistorialAlumnos(controlador);
		vehiculoNuevo = new VehiculoNuevo(controlador);
		vehiculoMod = new VehiculoMod(controlador);
		infoVehiculos = new PanelInfoVehiculos(controlador);
		nuevaReserva = new PanelNuevaReserva(controlador);
		consultarReservas = new PanelConsultarReservas(controlador);

		panelActual = infoPanel;

		container.add(menuBar, BorderLayout.NORTH);
		container.add(infoPanel, BorderLayout.CENTER);

		fixMenuListener();
		this.setVisible(true);
	}

	/**
	 * Inicializa el la barra de menu de la interfaz secretaria
	 */
	private void initMenu() {
		JMenu menuSecretaria = new JMenu("Alumnos");
		itemAltaAlumno = new JMenuItem("Añadir alumno");
		itemModificarAlumno = new JMenuItem("Eliminar/Modificar alumno");
		itemHistorialAlumnos = new JMenuItem("Historial alumnos");
		menuSecretaria.add(itemAltaAlumno);
		menuSecretaria.add(itemModificarAlumno);
		menuSecretaria.add(itemHistorialAlumnos);

		JMenu menuVehiculo = new JMenu("Vehículos");
		itemNuevoVehiculo = new JMenuItem("Añadir vehículo");
		itemBajaVehiculo = new JMenuItem("Eliminar/Modificar vehículo");
		itemInfoVehiculo = new JMenuItem("Información vehículos");
		menuVehiculo.add(itemNuevoVehiculo);
		menuVehiculo.add(itemBajaVehiculo);
		menuVehiculo.add(itemInfoVehiculo);
		
		JMenu menuCalendario = new JMenu("Calendario");
		itemNuevaReserva = new JMenuItem("Nueva Reserva");
		menuCalendario.add(itemNuevaReserva);
		itemConsultarReservas = new JMenuItem("Consultar reservas");
		menuCalendario.add(itemConsultarReservas);

		menuBar.add(menuSecretaria);
		menuBar.add(menuVehiculo);
		menuBar.add(menuCalendario);
		menuBar.add(menuAyuda);
		menuBar.add(menuSalir);
	}

	/**
	 * Procesa los distintos eventos del menu
	 */
	private void fixMenuListener() {
		fixAltaAlumnoListener();
		fixModBajaAlumnoListener();
		fixVehiculoNuevoListener();
		fixVehiculoModListener();
		fixConsultarReservas();
		fixNuevaReserva();
		fixMiInfo();
		fixInfoVehiculos();
		fixHistorialAlumnos();
	}

	/**
	 * Procesa el evento sobre la opción historial alumnos del menu alumnos
	 */
	private void fixHistorialAlumnos() {
		itemHistorialAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();	
				panelActual = historialAlumnos;
				mostrarNuevoPanel();
			}
		});	
	}

	/**
	 * Procesa el evento sobre la opción información vehículos del menu Vehículo
	 */
	private void fixInfoVehiculos() {
		itemInfoVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();	
				panelActual = infoVehiculos;
				mostrarNuevoPanel();
			}
		});
	}

	/**
	 * Procesa el evento sobre la opción nueva reserva  del menu Calendario
	 */
	private void fixNuevaReserva() {
		itemNuevaReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();	
				panelActual = nuevaReserva;
				mostrarNuevoPanel();
			}
		});
	}

	/**
	 * Procesa el evento sobre la opción consultar reserva  del menu Calendario
	 */
	private void fixConsultarReservas() {
		itemConsultarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();
				panelActual = consultarReservas;
				mostrarNuevoPanel();
			}
		});	
	}

	/**
	 * Procesa el evento sobre la opción Mis datos  del menu Ayuda
	 */
	private void fixMiInfo() {
		itemMiInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();
				panelActual = infoPanel;
				mostrarNuevoPanel();
			}
		});
	}

	/**
	 * Procesa el evento sobre la opción modificar/eliminar del menu vehiculoa
	 */
	private void fixVehiculoModListener() {
		itemBajaVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();
				panelActual = vehiculoMod;
				mostrarNuevoPanel();
			}
		});
	}

	/**
	 * Procesa el evento sobre la opción aniadir vehiculo del menu vehiculo
	 */
	private void fixVehiculoNuevoListener() {
		itemNuevoVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();
				panelActual = vehiculoNuevo;
				mostrarNuevoPanel();
			}
		});
	}

	/**
	 * Procesa el evento sobre la opción aniadir alumno  del menu alumno
	 */
	private void fixAltaAlumnoListener() {
		itemAltaAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();
				panelActual = altaAlumno;
				mostrarNuevoPanel();
			}
		});
	}

	/**
	 * Procesa el evento sobre la opción baja/modificar alumno  del menu alumno
	 */
	private void fixModBajaAlumnoListener() {
		itemModificarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ocultarAntiguoPanel();
				panelActual = modAlumno;
				mostrarNuevoPanel();
			}
		});
	}
}
