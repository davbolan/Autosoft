package GUI.alumno;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.AbstractPanel;
import GUI.Imagen;


/**
 * Clase que implementa la interfaz información alumno de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class PanelInfoAlumno extends AbstractPanel{

	private JPanel panelUsuario;
	private JPanel panelrol;
	private JLabel etiquetaUsuario;
	private JLabel etiquetaRol;

	/**
	 * Constructor de la interfaz información alumno
	 * @param usuario
	 * @param rol
	 */
	public PanelInfoAlumno(String usuario, String rol) {	
		initInfoAlumno(usuario,rol);
	}
	
	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz infromación alumno
	 * 
	 */
	private void initInfoAlumno(String usuario, String rol) {
		Imagen fondo= new Imagen("fondoAlumno.jpg");
		Imagen fondo2 = new Imagen("fondoBlanco.jpg");
		Imagen fondo3 = new Imagen("fondoBlanco.jpg");
		fondo2.setBounds(0,0,185,75);
		fondo3.setBounds(0,0,185,75);
		fondo.setBounds(0, 0, 640, 480);
		
		this.setLayout(null);
		panelUsuario= new JPanel();
		panelrol =new JPanel();
		
		panelUsuario.setLayout(null);
		panelrol.setLayout(null);
				
		JLabel eUsuario = new JLabel("Usuario");
		eUsuario.setBounds(10, 10, 100, 10);
		etiquetaUsuario = new JLabel(usuario);
		etiquetaUsuario.setBounds(55,40,100,20);
		
		JLabel eRol = new JLabel("Rol");
		eRol.setBounds(10, 10, 100, 10);
		etiquetaRol = new JLabel(rol);
		etiquetaRol.setBounds(55,40,100,20);
		
		panelUsuario.add(eUsuario);
		panelUsuario.add(etiquetaUsuario);
		panelUsuario.add(fondo2);
	
		panelrol.add(eRol);
		panelrol.add(etiquetaRol);
		panelrol.add(fondo3);
		JPanel panelRolNombre = new JPanel();
		panelRolNombre.setLayout(new GridLayout(1,2));

		panelRolNombre.add(panelUsuario);
		panelRolNombre.add(panelrol);
		panelRolNombre.setBounds(130, 110, 380, 80);
		
		JPanel logoPanel = new JPanel();
		logoPanel.setLayout(new FlowLayout());
		Imagen logo = new Imagen("logo.jpg");
		logoPanel.add(logo);
		logoPanel.setBounds(240,240, 150, 120);
		this.add(panelRolNombre);
		this.add(logoPanel);
		this.add(fondo);	
	}

	@Override
	public void ocultarPanel() {}
	
	@Override
	public void mostrarPanel() {}

}
