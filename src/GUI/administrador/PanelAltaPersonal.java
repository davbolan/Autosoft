package GUI.administrador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.dominio.personas.*;
import utilidades.Utilidades;
import GUI.AbstractPanel;
import GUI.Imagen;
import GUI.observables.PersonalObserver;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz/panel de alta personal de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class PanelAltaPersonal extends AbstractPanel implements PersonalObserver{
	private JTextField nombreText;
	private JTextField apellidoText;
	private JButton acceptButton;
	private JButton clearButton;
	private JTextField dniText;
	private JTextField birthText;
	private JTextField adressText;
	private JTextField phoneText;
	private JTextField numSSText;
	private JTextField sueldoText;
	private JTextField horarioText;
	private JTextArea descripcionText;
	private JComboBox<String> cargoBox;
	
	/**
	 * Contructor de la interfaz/panel alta personal
	 * @param controlador
	 */
	public PanelAltaPersonal(Controlador controlador) {
		super(controlador);
		
		initPanelAltaPersonal();
		
		this.setVisible(true);
		fixButtons();
	}
	
	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz/panel alta personal
	 * 
	 */
	private void initPanelAltaPersonal() {
		this.setPreferredSize(new Dimension(640, 480));
		
		Font defaultFont = new Font("Calibri", Font.PLAIN, 14);
		
		Imagen logo = new Imagen("logoPaneles.jpg");
		logo.setBounds(10, 10, 130, 120);
		
		Imagen fondo = new Imagen("fondoAdmin.jpg");
		fondo.setBounds(0, 0, 640, 480);
			
		JLabel nombreLabel = new JLabel("Nombre");		
		nombreLabel.setFont(defaultFont);
		nombreLabel.setForeground(Color.WHITE);
		nombreLabel.setBounds(180,90,60,20);
		nombreText = new JTextField();
		nombreText.setBounds(250,90,200,20);
		
		JLabel apellidoLabel = new JLabel("Apellidos");		
		apellidoLabel.setFont(defaultFont);
		apellidoLabel.setForeground(Color.WHITE);
		apellidoLabel.setBounds(180,130,60,20);
		apellidoText = new JTextField();
		apellidoText.setBounds(250,130,200,20);
				
		JLabel dniLabel = new JLabel("DNI");
		dniLabel.setFont(defaultFont);
		dniLabel.setForeground(Color.WHITE);
		dniLabel.setBounds(200,170,40,20);
		dniText = new JTextField();
		dniText.setBounds(250,170,80,20);
		
		JLabel adressLabel = new JLabel("Dirección");
		adressLabel.setFont(defaultFont);
		adressLabel.setForeground(Color.WHITE);
		adressLabel.setBounds(170,210,70,20);
		adressText = new JTextField();
		adressText.setBounds(250,210,150,20);
		
		JLabel numSSLabel = new JLabel("Nº SS");
		numSSLabel.setFont(defaultFont);
		numSSLabel.setForeground(Color.WHITE);
		numSSLabel.setBounds(190,250,50,20);
		numSSText = new JTextField();
		numSSText.setBounds(250,250,100,20);
		
		JLabel horarioLabel = new JLabel("Horario");
		horarioLabel.setFont(defaultFont);
		horarioLabel.setForeground(Color.WHITE);
		horarioLabel.setBounds(180,290,60,20);
		horarioText = new JTextField();
		horarioText.setBounds(250,290,100,20);
		
		JLabel cargoLabel = new JLabel("Cargo");		
		cargoLabel.setFont(defaultFont);
		cargoLabel.setForeground(Color.WHITE);
		cargoLabel.setBounds(200,330,40,20);
		cargoBox = new JComboBox<String>();
		cargoBox.addItem("Seleccione cargo...");
		cargoBox.addItem("Secretaria");
		cargoBox.addItem("Administrador");
		cargoBox.addItem("Profesor de practicas");
		cargoBox.addItem("Profesor de teoria");
		cargoBox.addItem("Otros");
		cargoBox.setBounds(250,330,150,20);
	
		JLabel birthLabel = new JLabel("Fecha de Nacimiento");
		birthLabel.setFont(defaultFont);
		birthLabel.setForeground(Color.WHITE);
		birthLabel.setBounds(360,170,120,20);
		birthText = new JTextField();
		birthText.setBounds(500,170,75,20);
		
		JLabel phoneLabel = new JLabel("Teléfono");
		phoneLabel.setFont(defaultFont);
		phoneLabel.setForeground(Color.WHITE);
		phoneLabel.setBounds(430,210,60,20);
		phoneText = new JTextField();
		phoneText.setBounds(500,210,80,20);	

		JLabel sueldoLabel = new JLabel("Sueldo");
		sueldoLabel.setFont(defaultFont);
		sueldoLabel.setForeground(Color.WHITE);
		sueldoLabel.setBounds(440,250,50,20);
		sueldoText = new JTextField();
		sueldoText.setBounds(500,250,80,20);
			
		JLabel descripcionLabel = new JLabel("Descripción");
		descripcionLabel.setFont(defaultFont);
		descripcionLabel.setForeground(Color.WHITE);
		descripcionLabel.setBounds(390,300,70,20);
		JScrollPane scrollDescripcionText = new JScrollPane();
		scrollDescripcionText.setBounds(464, 300, 150, 60);
		descripcionText = new JTextArea();
		scrollDescripcionText.setViewportView(descripcionText);
		
		
		acceptButton = new JButton("Aceptar");
		acceptButton.setBounds(340, 380, 130, 30);
		clearButton = new JButton("Limpiar campos");
		clearButton.setBounds(180, 380, 130, 30);
		
		JLabel titulo = new JLabel("Dar de alta a un empleado");
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(3,3, 200, 15);
		this.add(titulo);
		
		this.setLayout(null);
		
		this.add(logo);
		this.add(cargoLabel);
		this.add(cargoBox);
		this.add(apellidoLabel);
		this.add(apellidoText);
		this.add(nombreLabel);
		this.add(nombreText);
		this.add(dniLabel);
		this.add(dniText);
		this.add(birthLabel);
		this.add(birthText);
		this.add(adressLabel);
		this.add(adressText);
		this.add(phoneLabel);
		this.add(phoneText);
		this.add(numSSLabel);
		this.add(numSSText);
		this.add(sueldoLabel);
		this.add(sueldoText);
		this.add(horarioLabel);
		this.add(horarioText);
		this.add(descripcionLabel);
		this.add(scrollDescripcionText);	
		this.add(acceptButton);	
		this.add(clearButton);
		this.add(fondo);
		this.updateUI();
	}

	/**
	 * Procesa los distintos eventos de los botones de la interfaz alta personal
	 */
	private void fixButtons() {
		fixAcceptButtonListener();
		fixClearFieldsButtonListener();
	}
	
	/**
	 * Procesa el evento del boton "aceptar" de la interfaz alta personal
	 */
	private void fixAcceptButtonListener() {
		acceptButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0)  {
				Personal personal = null;
				boolean correct = true;
				String messageError = "";

				if(!camposVacios() && camposNumCorrectos()){										
					if(Utilidades.fechaCorrecta(birthText.getText())){
						personal = new Personal(
							dniText.getText().trim(), 
							nombreText.getText().trim(), 
							apellidoText.getText().trim(), 
							Integer.parseInt(sueldoText.getText().trim()),
							Integer.parseInt(numSSText.getText().trim()), 
							horarioText.getText().trim(),
							birthText.getText().trim(),
							Integer.parseInt(phoneText.getText().trim()),
							"Personal",
							adressText.getText().trim()
						);
						switch(((String)cargoBox.getSelectedItem()).toLowerCase()){
							case "profesor de teoria":{
								
								personal = new ProfesorTeorico(
											dniText.getText().trim(), 
											nombreText.getText().trim(), 
											apellidoText.getText().trim(), 
											Integer.parseInt(sueldoText.getText().trim()),
											Integer.parseInt(numSSText.getText().trim()), 
											horarioText.getText().trim(),
											birthText.getText().trim(),
											Integer.parseInt(phoneText.getText().trim()),
											"Profesor",
											adressText.getText().trim(),
											false, 
											"ProfesorTeorico",
											0
											);
								break;
							}
							case "profesor de practicas":{
								personal = new ProfesorPractico(
										dniText.getText().trim(), 
										nombreText.getText().trim(), 
										apellidoText.getText().trim(), 
										Integer.parseInt(sueldoText.getText().trim()),
										Integer.parseInt(numSSText.getText().trim()), 
										horarioText.getText().trim(),
										birthText.getText().trim(),
										Integer.parseInt(phoneText.getText().trim()),
										"Profesor",
										adressText.getText().trim(),
										false, 
										"ProfesorPractico",
										null
										);
								break;
							}
							case "secretaria":{
								personal = new Secretaria(
										dniText.getText().trim(), 
										nombreText.getText().trim(), 
										apellidoText.getText().trim(), 
										Integer.parseInt(sueldoText.getText().trim()),
										Integer.parseInt(numSSText.getText().trim()), 
										horarioText.getText().trim(),
										birthText.getText().trim(),
										Integer.parseInt(phoneText.getText().trim()),
										"Secretaria",
										adressText.getText().trim()
										);
								break;
							}
							case "administrador":{
								personal = new Administrador(
										dniText.getText().trim(), 
										nombreText.getText().trim(), 
										apellidoText.getText().trim(), 
										Integer.parseInt(sueldoText.getText().trim()),
										Integer.parseInt(numSSText.getText().trim()), 
										horarioText.getText().trim(),
										birthText.getText().trim(),
										Integer.parseInt(phoneText.getText().trim()),
										"Administrador",
										adressText.getText().trim()
										);
								break;
							}
							case "otros":{
								String descripcion = descripcionText.getText().trim();
								if(descripcion.isEmpty())
									descripcion = "Sin descripcion";
								personal = new Otros(
										dniText.getText().trim(), 
										nombreText.getText().trim(), 
										apellidoText.getText().trim(), 
										Integer.parseInt(sueldoText.getText().trim()),
										Integer.parseInt(numSSText.getText().trim()), 
										horarioText.getText().trim(),
										birthText.getText().trim(),
										Integer.parseInt(phoneText.getText().trim()),
										"Otros",
										adressText.getText().trim(),
										descripcion
										);
								break;
							}
							default: 
								correct = false;
								messageError = "Por favor, selecciona un cargo.";			
						}
					}			
					else{
						correct = false; 
						messageError = "Por favor, revise el formato de fecha (DD-MM-AAAA), además debe ser anterior a hoy.";
					}
						
				}	
				else{
					correct = false;
					messageError = "Por favor, revise los campos, no deben estar vacios y deben ser números donde corresponda.";
				}
				
				if(correct){
					if(confirmar() == YES)
						controlador.altaPersonal(personal);		
				}
				else
					showError("Error", messageError);
			}
		}
	);
	}
	
	/**
	 * Verifica que todos los campos estén rellenados
	 * @return boolean
	 */
	private boolean camposVacios(){
		return (dniText.getText().trim().isEmpty() 	|| 
			nombreText.getText().trim().isEmpty() 	|| 
			apellidoText.getText().trim().isEmpty() ||
			sueldoText.getText().trim().isEmpty() 	|| 
			numSSText.getText().trim().isEmpty()  	|| 
			horarioText.getText().trim().isEmpty()	||
			birthText.getText().trim().isEmpty()  	|| 
			phoneText.getText().trim().isEmpty()  	|| 
			adressText.getText().trim().isEmpty());
	}
	
	/**
	 * Ventana de confirmación de cambios SI/NO
	 * @return entero
	 */
	private int confirmar(){
		return JOptionPane.showConfirmDialog(null, 
				"¿Estás seguro de dar de alta a " + nombreText.getText().trim() + " " + apellidoText.getText().trim() + "?", 
				"Alta personal", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE, 
				null);
	}

	/**
	 * Verifica que el campo sueldo, num SS y telefono sean numeros validos
	 */
	private boolean camposNumCorrectos(){	
		return (Utilidades.isPositiveNumber(sueldoText.getText().trim()) && 
				Utilidades.isPositiveNumber(numSSText.getText().trim())  &&
				Utilidades.isPositiveNumber(phoneText.getText().trim())
				);
	}

	/**
	 * Procesa el evento del boton "limpiar campos" de la interfaz alta personal
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
	private void clearFields(){
		nombreText.setText("");
		apellidoText.setText("");
		dniText.setText("");
		birthText.setText("");
		adressText.setText("");
		phoneText.setText("");
		numSSText.setText("");
		sueldoText.setText("");
		horarioText.setText("");
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
	public void ocultarPanel() {
		clearFields();
		controlador.removePersonalObserver(this);
	}
	
	@Override
	public void mostrarPanel() {
		controlador.addPersonalObserver(this);
	}

	@Override
	public void infoPersonal(ArrayList<Personal> listaOrdenada) {
	}

	@Override
	public void profesorDisponibles(ArrayList<ProfesorTeorico> profesoresT,
			ArrayList<ProfesorPractico> profesoresP) {
	}
	
}
