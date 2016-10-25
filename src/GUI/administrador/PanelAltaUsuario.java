package GUI.administrador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
 * Clase que implementa la interfaz de alta usuario de la aplicación 
 * @author David Bolanios, Frank Julca
 *
 */
@SuppressWarnings("serial")
public class PanelAltaUsuario extends AbstractPanel implements UsuariosObserver{

	private JComboBox<String> rolBox;
	private JTextField usuarioText;
	private JPasswordField passText;
	private JPasswordField passRepText;
	
	private JButton acceptButton;
	private JButton clearButton;
	
	/**
	 * Contructor de la interfaz alta usuario
	 * @param controlador
	 */
	public PanelAltaUsuario(Controlador controlador) {
		super(controlador);
		
		initPanelAltaUsuario();
		
		this.setVisible(true);
		fixButtons();
	}


	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz alta usuario
	 * 
	 */
	private void initPanelAltaUsuario() {
		Font defaultFont = new Font("Calibri", Font.PLAIN, 17);
		setPreferredSize(new Dimension(1000, 1000));
		Imagen logo = new Imagen("logoPaneles.jpg");
		logo.setBounds(10, 10, 130, 120);
		
		Imagen fondo = new Imagen("fondoAdmin.jpg");
		fondo.setBounds(0, 0, 640, 480);
		
		JLabel rolLabel = new JLabel("Cargo");		
		rolLabel.setFont(defaultFont);
		rolLabel.setForeground(Color.WHITE);
		rolLabel.setBounds(230,100,100,20);
		rolBox = new JComboBox<String>();
		rolBox.addItem("Seleccione un rol...");
		rolBox.addItem("Administrador");
		rolBox.addItem("Secretaria");
		rolBox.addItem("Profesor");
		rolBox.addItem("Alumno");
		rolBox.setBounds(300,100,150,20);
		
		JLabel usuarioLabel = new JLabel("Usuario");
		usuarioLabel.setFont(defaultFont);
		usuarioLabel.setForeground(Color.WHITE);
		usuarioLabel.setBounds(215,160,100,20);
		usuarioText = new JTextField();
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
		
		acceptButton = new JButton("Aceptar");
		acceptButton.setBounds(344, 350, 130, 30);
		clearButton = new JButton("Limpiar campos");
		clearButton.setBounds(176, 350, 130, 30);
		
		JLabel titulo = new JLabel("Dar de alta a un usuario");
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(3,3, 200, 15);
		this.add(titulo);
		
		this.setLayout(null);
		
		this.add(logo);
		this.add(rolLabel);
		this.add(rolBox);
		this.add(usuarioLabel);
		this.add(usuarioText);
		this.add(passLabel);
		this.add(passText);
		this.add(passRepLabel);
		this.add(passRepText);
		this.add(acceptButton);	
		this.add(clearButton);
		this.add(fondo);
		this.updateUI();
	}
	
	/**
	 * Procesa los distintos eventos de los botones de la interfaz alta usuario
	 */
	private void fixButtons() {
		fixAcceptButtonListener();
		fixClearFieldsButtonListener();
	}

	/**
	 * Procesa el evento del boton "aceptar" de la interfaz alta usuario
	 */
	private void fixAcceptButtonListener() {
		acceptButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String pass 	= String.copyValueOf(passText.getPassword()).trim();
				String passRep  = String.copyValueOf(passRepText.getPassword()).trim();

				if(rolBox.getSelectedIndex() == 0)
					showError("Error", "Por favor, seleccione un rol del usuario.");
				else if(usuarioText.getText().isEmpty())
					showError("Error", "Por favor, rellene el campo del usuario");
				else if(pass.isEmpty() || passRep.isEmpty() )
					showError("Error", "Por favor, rellene los campos de las contraseñas");
				else if(!pass.equalsIgnoreCase(passRep))
					showError("Error", "Las contraseñas no coindicen.");
				else{
					if(confirmar() == YES){
						Usuario usuario = new Usuario(pass, usuarioText.getText(), (String)rolBox.getSelectedItem());
						controlador.nuevoUsuario(usuario);
					}
				}
			}
		}
	);
	}

	/**
	 * Ventana de confirmación de cambios SI/NO
	 * @return entero
	 */
	private int confirmar(){
		return JOptionPane.showConfirmDialog(null, 
				"¿Estás seguro de crear el usuario con DNI " + usuarioText.getText() + "?", 
				"Nuevo usuario", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE, 
				null);
	}
	
	/**
	 * Procesa el evento del boton "limpiar campos" de la interfaz alta usuario
	 */
	private void fixClearFieldsButtonListener() {
		clearButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					rolBox.setSelectedIndex(0);
					usuarioText.setText("");
					passText.setText("");
					passRepText.setText("");
				}
			}
		);
	}
	
	/**
	 * Limpia los campos de los distintos JtextField
	 */
	private void clearFields(){
		rolBox.setSelectedIndex(0);
		usuarioText.setText("");
		passText.setText("");
		passRepText.setText("");
	}

	@Override
	public void abrirVentanaUsuario(String usuario, String rol) {
	}


	@Override
	public void showError(String error, String mensaje){
		JOptionPane.showOptionDialog(null, mensaje, error, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);	
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
