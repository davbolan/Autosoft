package GUI.secretaria;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.dominio.personas.Alumno;
import modelo.dominio.personas.Personal;
import modelo.dominio.personas.ProfesorPractico;
import modelo.dominio.personas.ProfesorTeorico;
import utilidades.Utilidades;
import GUI.AbstractPanel;
import GUI.Imagen;
import GUI.observables.AlumnosObserver;
import GUI.observables.PersonalObserver;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz/panel de alta alumno de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class PanelAltaAlumno extends AbstractPanel implements AlumnosObserver, PersonalObserver {
	private JTextField nombreText;
	private JTextField apellidoText;
	private JButton aceptarButton;
	private JButton clearButton;
	private JTextField dniText;
	private JTextField birthText;
	private JTextField addressText;
	private JTextField phoneText;
	private JLabel matriculaText;
	private JLabel nivelText;
	private JComboBox<String> profesorBox;
	private JComboBox<String> permisoBox;

	/**
	 * Contructor de la interfaz/panel alta personal
	 * @param controlador
	 */
	public PanelAltaAlumno(Controlador controlador) {
		super(controlador);
		initPanelAltaAlumno();
		
		this.setVisible(true);
		fixButtons();
	}
	
	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz/panel alta alumno
	 * 
	 */
	private void initPanelAltaAlumno() {
		this.setPreferredSize(new Dimension(1000,1000));
		Font defaultFont = new Font("Calibri", Font.PLAIN, 14);
		
		Imagen logo = new Imagen("logoPaneles.jpg");
		logo.setBounds(10, 10, 130, 120);
		
		Imagen fondo = new Imagen("fondoSecretaria.jpg");
		fondo.setBounds(0, 0, 640, 480);
		
		JLabel nombreLabel = new JLabel("Nombre");		
		nombreLabel.setFont(defaultFont);
		nombreLabel.setBounds(150,80,50,20);
		nombreText = new JTextField();
		nombreText.setBounds(218,80,171,20);
		
		JLabel apellidoLabel = new JLabel("Apellidos");		
		apellidoLabel.setFont(defaultFont);
		apellidoLabel.setBounds(150,120,50,20);
		apellidoText = new JTextField();
		apellidoText.setBounds(218,120,171,20);
		
		JLabel dniLabel = new JLabel("DNI");
		dniLabel.setFont(defaultFont);
		dniLabel.setBounds(179,160,35,20);
		dniText = new JTextField();
		dniText.setBounds(218,160,72,20);
		
		JLabel birthLabel = new JLabel("Fecha de Nacimiento");
		birthLabel.setFont(defaultFont);
		birthLabel.setBounds(84,200,130,20);
		birthText = new JTextField();
		birthText.setBounds(218,200,72,20);
		
		JLabel adressLabel = new JLabel("Dirección");
		adressLabel.setFont(defaultFont);
		adressLabel.setBounds(150,240,60,20);
		addressText = new JTextField();
		addressText.setBounds(218,240,140,20);
		
		JLabel phoneLabel = new JLabel("Teléfono");
		phoneLabel.setFont(defaultFont);
		phoneLabel.setBounds(150,280,53,20);
		phoneText = new JTextField();
		phoneText.setBounds(217,280,84,20);
		
		JLabel permisoLabel = new JLabel("Permiso");
		permisoLabel.setFont(defaultFont);
		permisoLabel.setBounds(390,160,50,20);
		permisoBox = new JComboBox<String>();
		permisoBox.addItem("Seleccione un permiso...");
		permisoBox.addItem("Permiso A");
		permisoBox.addItem("Permiso B");
		permisoBox.addItem("Permiso C");
		permisoBox.setBounds(444,160,170,20);
		
		JLabel matriculaLabel = new JLabel("Fecha de matrícula");
		matriculaLabel.setFont(defaultFont);
		matriculaLabel.setBounds(90,322,122,20);
		matriculaText = new JLabel(Utilidades.fechaHoy());
		matriculaText.setFont(new Font("Calibri", Font.BOLD, 16));
		matriculaText.setBounds(218,322,100,20);

		JLabel profesorLabel = new JLabel("Profesor");
		profesorLabel.setFont(defaultFont);
		profesorLabel.setBounds(390,200,50,20);
		profesorBox = new JComboBox<String>();
		profesorBox.addItem("Seleccione un permiso...");
		profesorBox.setBounds(444,200,170,20);
		
		JLabel nivelLabel = new JLabel("Nivel");
		nivelLabel.setFont(defaultFont);
		nivelLabel.setBounds(405,240,35,20);
		nivelText = new JLabel("1");
		nivelText.setFont(new Font("Calibri", Font.BOLD, 16));
		nivelText.setBounds(444,240,50,20);
			
		aceptarButton = new JButton("Aceptar");
		aceptarButton.setBounds(374, 373, 80, 30);
		clearButton = new JButton("Limpiar campos");
		clearButton.setBounds(200, 373, 140, 30);
		
		JLabel titulo = new JLabel("Dar de alta a un alumno");
		titulo.setForeground(Color.BLACK);
		titulo.setBounds(3,3, 200, 15);
		this.add(titulo);
		
		this.setLayout(null);
		this.add(titulo);
		this.add(logo);
		this.add(nombreLabel);
		this.add(nombreText);
		this.add(apellidoLabel);
		this.add(apellidoText);
		this.add(dniLabel);
		this.add(dniText);
		this.add(birthLabel);
		this.add(birthText);
		this.add(adressLabel);
		this.add(addressText);
		this.add(phoneLabel);
		this.add(phoneText);
		this.add(permisoLabel);
		this.add(permisoBox);
		this.add(matriculaLabel);
		this.add(matriculaText);
		this.add(profesorLabel);
		this.add(profesorBox);
		this.add(nivelLabel);
		this.add(nivelText);
		this.add(aceptarButton);
		this.add(clearButton);
		this.add(fondo);
		this.updateUI();
	}
	
	/**
	 * Procesa los distintos eventos de los botones de la interfaz alta alumno
	 */
	private void fixButtons() {
		fixAcceptButtonListener();
		fixClearFieldsButtonListener();
	}

	/**
	 * Procesa el evento del boton "aceptar" de la interfaz alta alumno
	 */
	private void fixAcceptButtonListener() {
		aceptarButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
					Alumno alumno = null;
					boolean correct = true;
					String messageError = "";
					
					if(!camposVacios() && camposNumCorrectos()){										
						if (!Utilidades.fechaCorrecta(birthText.getText())){
							correct = false; 
							messageError = "Por favor, revise el formato de fecha (DD/MM/AAAA), además debe ser anterior a hoy.";
						}	
						else if(permisoBox.getSelectedIndex() == 0){
							correct = false;
							messageError = "Por favor, seleccione un tipo de permiso.";						
						}
						else if(profesorBox.getSelectedIndex() == 0){
							correct = false;
							messageError = "Por favor, seleccione un profesor para el alumno.";						
						}
						else{
							int auxnivel = Integer.parseInt(nivelText.getText());
							int phone = Integer.parseInt(phoneText.getText());
							String[] dniProfesor = ((String)profesorBox.getSelectedItem()).split(" ");
	
							alumno = new Alumno(
									dniText.getText().trim(),
									nombreText.getText().trim(), 
									apellidoText.getText().trim(),
									birthText.getText().trim(),
									auxnivel, 
									((String)permisoBox.getSelectedItem()), 
									addressText.getText().trim(), 
									dniProfesor[dniProfesor.length-1],
									matriculaText.getText().trim(),
									phone, 
									true
							);
						}
					}								
					else{
						correct = false;
						messageError = "Por favor, revise los campos, no deben estar vacíos y deben ser números donde corresponda.";
					}
					
					if(correct){
						if(confirmar() == YES)
							controlador.altaAlumno(alumno);
					}
					else
						showError("Error", messageError);
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
				"¿Estás seguro de matricular a " + nombreText.getText().trim() + " "+ apellidoText.getText().trim() 
				+" con DNI " + dniText.getText().trim() + "?", 
				"Alta personal", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE, 
				null);
	}
	
	
	/**
	 * Limpia los campos de los distintos JtextField
	 */
	private boolean camposVacios(){
		return(nombreText.getText().trim().isEmpty() 
				|| apellidoText.getText().trim().isEmpty()
				|| dniText.getText().trim().isEmpty() 
				|| addressText.getText().trim().isEmpty());
	}
	/**
	 * Verifica que el campo telefono sea un numero valido
	 */
	private boolean camposNumCorrectos(){	
		return (Utilidades.isPositiveNumber(phoneText.getText().trim()));		
	}
	
	/**
	 * Procesa el evento del boton "limpiar campos" de la interfaz alta personal
	 */
	private void fixClearFieldsButtonListener() {
		clearButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					matriculaText.setText("");
					nombreText.setText("");
					apellidoText.setText("");
					dniText.setText("");
					birthText.setText("");
					phoneText.setText("");
					addressText.setText("");
					permisoBox.setSelectedIndex(0);
					profesorBox.setSelectedIndex(0);
				}
			}
		);
	}
	

	public void ocultarPanel() {
		controlador.removeAlumnosObserver(this);
		controlador.removePersonalObserver(this);
	}
	
	public void mostrarPanel() {
		controlador.addPersonalObserver(this);
		controlador.addAlumnosObserver(this);
		controlador.rellenarProfesor("", "ProfesorTeorico");
	}
	
	@Override
	public void mensajeInfo(String type, String mensaje) {
		JOptionPane.showOptionDialog(null, mensaje, type, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);			
	}
	
	@Override
	public void showError(String error, String mensaje){
		JOptionPane.showOptionDialog(null, mensaje, error, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);	
	}
	
	@Override
	public void infoPersonal(ArrayList<Personal> listaOrdenada) {}
	
	@Override
	public void profesorDisponibles(ArrayList<ProfesorTeorico> profesoresT,
			ArrayList<ProfesorPractico> profesoresP) {

		ProfesorTeorico profT;

		Iterator<ProfesorTeorico> itProfeT = profesoresT.iterator();
		while(itProfeT.hasNext()){
			profT = itProfeT.next();
			profesorBox.addItem("(T) " + profT.getNombre() + " " + profT.getApellido() + " " + profT.getDni());
		}	
	}
	
	@Override
	public void infoAlumnos(ArrayList<Alumno> listaOrdenada) {}
}

