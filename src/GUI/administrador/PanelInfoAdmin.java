package GUI.administrador;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.AbstractPanel;
import GUI.Imagen;

/**
 * Clase que implementa la interfaz información de Administrador de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class PanelInfoAdmin extends AbstractPanel{
	
	private JPanel panelUsuario;
	private JPanel panelRol;
	private JLabel etiquetaUsuario;
	private JLabel etiquetaRol;
	
	/**
	 * Constructora por defecto
	 * @param rol 
	 * @param usuario 
	 * @param usuario -El usuario que esta registrado.
	 */
	public PanelInfoAdmin(String usuario, String rol){
		super();
		initInfoAdmin(usuario, rol);
	}

	/**
	 * Metodo encargado de inicializar el panel.
	 */
	private void initInfoAdmin(String usuario, String rol) {
		Imagen fondo = new Imagen("fondoAdmin.jpg");
		Imagen fondo2 = new Imagen("fondoBlanco.jpg");
		Imagen fondo3 = new Imagen("fondoBlanco.jpg");
		fondo2.setBounds(0,0,185,75);
		fondo3.setBounds(0,0,185,75);
		fondo.setBounds(0, 0, 640, 480);
		this.setLayout(null);
		panelUsuario = new JPanel();
		panelRol = new JPanel();
		
		panelUsuario.setLayout(null);
		panelRol.setLayout(null);
		
		JLabel eUsuario = new JLabel("Usuario");
		eUsuario.setBounds(10, 10, 100, 10);
		etiquetaUsuario = new JLabel(usuario);
		etiquetaUsuario.setBounds(55,40,100,20);
		
		JLabel eRol = new JLabel("Rol");
		eRol.setBounds(10, 10, 100, 10);
		etiquetaRol = new JLabel(rol);
		etiquetaRol.setBounds(55,40,100,20);
		
		panelUsuario.add(etiquetaUsuario);
		panelUsuario.add(eUsuario);
		panelUsuario.add(fondo2);
	
		panelRol.add(etiquetaRol);
		panelRol.add(eRol);
		panelRol.add(fondo3);
		JPanel panelRolNombre = new JPanel();
		panelRolNombre.setLayout(new GridLayout(1,2));
		panelRolNombre.add(panelUsuario);
		panelRolNombre.add(panelRol);
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
