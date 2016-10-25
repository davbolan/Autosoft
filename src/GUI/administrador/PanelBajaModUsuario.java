package GUI.administrador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import modelo.dominio.usuarios.Usuario;
import GUI.AbstractPanel;
import GUI.Imagen;
import GUI.observables.UsuariosObserver;
import controlador.Controlador;


/**
 * Clase que implementa la interfaz de baja/modificar usuario de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class PanelBajaModUsuario extends AbstractPanel implements UsuariosObserver{
	private JTextField dniText;
	private JButton searchButton;
	private JLabel rolText;
	private JLabel usuarioText;
	private JPasswordField passText;
	private JPasswordField passRepText;
	private JButton deleteButton;
	private JButton modButton;
	private JButton clearButton;
	private Usuario usuarioSelected;
	
	private String DNIActual = "";


	/**
	 * Contructor de la interfaz baja/modificar usuario
	 * @param controlador
	 */
	public PanelBajaModUsuario(Controlador controlador) {
		super(controlador);
		
		initPanelBajaModUsuario();
		
		this.setVisible(true);
		fixButtons();
	}
	
	
	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz baja/modificar usuario
	 * 
	 */
	private void initPanelBajaModUsuario() {
		this.setPreferredSize(new Dimension(640, 480));
		Font defaultFont = new Font("Calibri", Font.PLAIN, 17);
		
		Imagen logo = new Imagen("logoPaneles.jpg");
		logo.setBounds(10, 10, 130, 120);

		JLabel dniLabel = new JLabel("DNI: ");		
		dniLabel.setFont(defaultFont);
		dniLabel.setForeground(Color.WHITE);
		dniLabel.setBounds(250,40,100,20);
		dniText = new JTextField();
		dniText.setBounds(300,40,100,20);
		searchButton = new JButton("Buscar");
		searchButton.setBounds(420,37,80,25);
		
		JLabel rolLabel = new JLabel("Cargo");		
		rolLabel.setFont(defaultFont);
		rolLabel.setForeground(Color.WHITE);
		rolLabel.setBounds(230,100,100,20);
		rolText = new JLabel("-");
		rolText.setFont(new Font("Calibri", Font.BOLD, 16));
		rolText.setForeground(Color.WHITE);
		rolText.setBounds(300,100,150,20);
		
		JLabel usuarioLabel = new JLabel("DNI Usuario");
		usuarioLabel.setFont(defaultFont);
		usuarioLabel.setForeground(Color.WHITE);
		usuarioLabel.setBounds(190,160,100,20);
		usuarioText = new JLabel("-");;
		usuarioText.setFont(new Font("Calibri", Font.BOLD, 16));
		usuarioText.setForeground(Color.WHITE);
		usuarioText.setBounds(300,160,150,20);
		
		JLabel passLabel = new JLabel("Contraseña");
		passLabel.setFont(defaultFont);
		passLabel.setForeground(Color.WHITE);
		passLabel.setBounds(190,220,100,20);
		passText = new JPasswordField();
		passText.setBounds(300,220,150,20);
		
		JLabel passRepLabel = new JLabel("Repetir contraseña");
		passRepLabel.setFont(defaultFont);
		passRepLabel.setForeground(Color.WHITE);
		passRepLabel.setBounds(136,280,150,20);
		passRepText = new JPasswordField();
		passRepText.setBounds(300,280,150,20);
		
		deleteButton = new JButton("Eliminar");
		deleteButton.setBounds(431, 350, 130, 30);
		modButton = new JButton("Modificar");
		modButton.setBounds(267, 350, 130, 30);
		clearButton = new JButton("Limpiar campos");
		clearButton.setBounds(100, 350, 130, 30);
		
		JLabel titulo = new JLabel("Dar de baja o modificar a un usuario");
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(3,3, 250, 15);
		this.add(titulo);
		
		Imagen fondo = new Imagen("fondoAdmin.jpg");
		fondo.setBounds(0, 0, 640, 480);
		
		this.setLayout(null);
		this.add(logo);
		this.add(dniLabel);
		this.add(dniText);
		this.add(searchButton);
		this.add(rolLabel);
		this.add(rolText);
		this.add(usuarioLabel);
		this.add(usuarioText);
		this.add(passLabel);
		this.add(passText);
		this.add(passRepLabel);
		this.add(passRepText);
		this.add(deleteButton);
		this.add(modButton);	
		this.add(clearButton);
		this.add(fondo);
		this.updateUI();
	}
	
	/**
	 * Permite editar los distintos JTextField para realizar modificaciones
	 * @param state
	 */
	private void changeFieldsState(boolean state){
		passText.setEnabled(state);
		passRepText.setEnabled(state);
	}
	
	
	/**
	 * Procesa los distintos eventos de los botones de la interfaz baja/modificar usuario
	 */
	private void fixButtons() {
		fixModButtonListener();
		fixClearFieldsButtonListener();
		fixSearchButtonListener();
		fixDeleteButtonListener();
	}


	/**
	 * Procesa el evento del boton "eliminar" de la interfaz baja/modificar usuario
	 */
	private void fixDeleteButtonListener() {
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dni = dniText.getText().trim();
				if(!dni.isEmpty())
					controlador.bajaUsuario(dni);
				else
					showError("Error", "El campo DNI no puede estar vacío.");
			}
		});		
		
	}

	/**
	 * Procesa el evento del boton "buscar" de la interfaz baja/modificar usuario
	 */
	private void fixSearchButtonListener() {
		searchButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String dni = dniText.getText();
				if(dni == null || dni.isEmpty())
					showError("Error", "Inserta un DNI a buscar.");
				else{
					DNIActual = dni;
					Usuario usuario = controlador.buscarUsuario(DNIActual);
					if(usuario == null)
						showError("Error", "No se ha encontrado el usuario con dni " + DNIActual);
					else{
						changeFieldsState(true);
						usuarioSelected = usuario;
						usuarioText.setText(usuario.getDni());
						rolText.setText(usuario.getRol());
						passText.setText(usuario.getContrasenia());
						passRepText.setText(usuario.getContrasenia());					
					}
				}
			}
		}
	);
	}
	
	/**
	 * Procesa el evento del boton "modificar" de la interfaz baja/modificar usuario
	 */
	private void fixModButtonListener() {
		modButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String pass 	= String.copyValueOf(passText.getPassword());
				String passRep  = String.copyValueOf(passRepText.getPassword());

				if(pass.isEmpty() || passRep.isEmpty() )
					showError("Error", "Por favor, rellene los campos de las contraseñas");
				else if(!pass.equalsIgnoreCase(passRep))
					showError("Error", "Las contraseñas no coindicen.");
				else{
					if(confirmar("Modificar", "modificar") == YES){
						Usuario usuario = new Usuario(pass, usuarioText.getText(), rolText.getText());
						controlador.modificarUsuario(usuario);
					}
				}
			}
		}
	);
	}

	/**
	 *
	 * Ventana de confirmación de cambios SI/NO
	 * @param tittle
	 * @param operacion
	 * @return entero
	 */
	private int confirmar(String tittle, String operacion){
		return JOptionPane.showConfirmDialog(null, 
				"¿Estás seguro de " + operacion + " al usuario con DNI " + usuarioSelected.getDni() + "?", 
				tittle, 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE, 
				null);
	}
	
	/**
	 * Procesa el evento del boton "limpiar campos" de la interfaz baja/modificar usuario
	 */
	private void fixClearFieldsButtonListener() {
		clearButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					clearFields();
				}
			}
		);
	}
	
	/**
	 * Limpia los campos de los distintos JtextField
	 */
	private void clearFields() {
		dniText.setText("");
		rolText.setText("-");
		usuarioText.setText("-");
		passText.setText("");
		passRepText.setText("");
		changeFieldsState(false);
		usuarioSelected = null;
		DNIActual = "";
	}

	@Override
	public void abrirVentanaUsuario(String type, String message) {}

	@Override
	public void showError(String type, String message) {
		JOptionPane.showOptionDialog(null, message, type, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);	
	}
	
	@Override
	public void mensajeInfo(String type, String mensaje) {
		clearFields();
		JOptionPane.showOptionDialog(null, mensaje, type, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);		
	}
	
	@Override
	public void mostrarPanel() {
		controlador.addUsuariosObserver(this);
	}
	
	@Override
	public void ocultarPanel() {
		clearFields();
		controlador.removeUsuariosObserver(this);
	}

}
