package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import modelo.dominio.usuarios.Usuario;
import GUI.administrador.GUIAdministrador;
import GUI.alumno.GUIAlumno;
import GUI.observables.UsuariosObserver;
import GUI.profesor.GUIProfesor;
import GUI.secretaria.GUISecretaria;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz de Login de la aplicación 
 * @author David Bolanios
 */
public class Login extends GUI implements UsuariosObserver{

	private JLabel usuarioLabel;
	private JLabel contrasenaLabel;
	private JTextField usuarioText;
	private JPasswordField contrasenaText;
	private static final long serialVersionUID = 1L;
	private JButton iniciarSesionButton;
	
	/**
	 * Constructor de la interfaz Login
	 * @param controlador
	 */
	public Login(Controlador controlador){
		super("AutoSoft", controlador);
		initLogin();
		controlador.addUsuariosObserver(this);
	}

	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz Login
	 */
	public void initLogin(){
		JLabel fondo = new JLabel(new ImageIcon (GUI.class.getResource(imageFolder+"/fondo.jpg")));
		fondo.setBounds(0, 0, 600, 300);
		JLabel icono = new JLabel(new ImageIcon (GUI.class.getResource(imageFolder+"/logoLogin.jpg")));
		icono.setBounds(20,30, 250, 200);
		JPanel prueba = new JPanel();
		prueba.setLayout(new FlowLayout());
		
		usuarioLabel = new JLabel("Usuario");
		usuarioLabel.setBounds(300, 20, 100, 100);
		usuarioLabel.setForeground(Color.WHITE);
		usuarioText = new JTextField(10);
		usuarioText.setBounds(300, 80, 120, 20);
			
		contrasenaLabel = new JLabel("Contraseña");
		contrasenaLabel.setBounds(300, 80, 100, 100);
		contrasenaLabel.setForeground(Color.WHITE);
		contrasenaText = new JPasswordField();
		contrasenaText.setBounds(300, 140, 120, 20);
		
		iniciarSesionButton = new JButton("Iniciar Sesión");
		iniciarSesionButton.setBounds(300, 180, 120, 30);
		
		container.add(icono);
		container.add(usuarioLabel);
		container.add(usuarioText);
		container.add(contrasenaLabel);
		container.add(contrasenaText);
		container.add(iniciarSesionButton);
		container.add(fondo);
		
		fixLogIn();
		fixKeyListener();
		this.setSize(480,300);
		this.setVisible(true);
		setLocationRelativeTo(null);
	}

	/**
	 * Procesa el evento de loguearse con un usuario y contraseña cuando se pulsa enter
	 * en algunos de los campso disponibles
	 */
	private void fixKeyListener(){
		usuarioText.addKeyListener(new KeyAdapter() { 
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c == '\n')
					iniciarSesionButton.doClick();
			}
		});
		
		contrasenaText.addKeyListener(new KeyAdapter() { 
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c == '\n')
					iniciarSesionButton.doClick();
			}
		});
	}
	
	/**
	 * Implementa el oyente del boton "Iniciar Sesión"
	 */
	private void fixLogIn(){
		iniciarSesionButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				String DNIuser = usuarioText.getText().trim();
				String pass = String.copyValueOf(contrasenaText.getPassword()).trim();
				
				if(DNIuser != null && !DNIuser.isEmpty() && pass != null && !pass.isEmpty()){	
					try {
						controlador.login(new Usuario(pass, DNIuser));
					} 
					catch (Exception e1) {
						showError("Error de lectura.", e1.getMessage());
						System.exit(1);
					}
				}
				else
					showError("Error de login", "Por favor, rellene los campos vacíos.");
			}
		});
	}

	/**
	 * Abre una ventana/interfaz en función del usuario y su rol.
	 * @param usuario
	 * @param rol
	 */
	@Override
	public void abrirVentanaUsuario(String usuario, String rol) {

		switch(rol.toLowerCase()){
		case "secretaria": 
			controlador.removeUsuariosObserver(this);
			this.dispose();
			new GUISecretaria(controlador, usuario, rol);
			break;
		case "administrador":
			controlador.removeUsuariosObserver(this);
			this.dispose();
			new GUIAdministrador(controlador, usuario, rol);			
			break;
		case "alumno": 
			this.dispose();
			new GUIAlumno(controlador, usuario, rol);
			break;
		case "profesor": 
			this.dispose();
			new GUIProfesor(controlador, usuario, rol);
			break;
		default:
			showError("Error", "Rol del usuario no identificado");
		}
	}
	
	@Override
	public void showError(String type, String message){
			JOptionPane.showOptionDialog(null, message, type, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);	
	}

	@Override
	public void mensajeInfo(String type, String mensaje) {
	}
	
}
