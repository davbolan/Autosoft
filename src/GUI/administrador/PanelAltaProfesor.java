package GUI.administrador;

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

import modelo.dominio.personas.Personal;
import modelo.dominio.personas.ProfesorPractico;
import modelo.dominio.personas.ProfesorTeorico;
import modelo.dominio.vehiculo.Vehiculo;
import utilidades.Utilidades;
import GUI.AbstractPanel;
import GUI.Imagen;
import GUI.observables.PersonalObserver;
import GUI.observables.VehiculosObserver;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz de alta profesor de la aplicación 
 * @author David Bolanios, Frank Julca
 *
 */
@SuppressWarnings("serial")
public class PanelAltaProfesor extends AbstractPanel implements PersonalObserver, VehiculosObserver{
	private JButton acceptButton;
	private JButton clearButton;
	private JTextField nombreText;
	private JTextField apellidoText;
	private JTextField dniText;
	private JTextField birthText;
	private JTextField adressText;
	private JTextField phoneText;
	private JTextField numSSText;
	private JTextField sueldoText;
	private JTextField horarioText;
	private JTextField aulaText;
	private JComboBox<String> typeBox;
	private JComboBox<String> vehiculoBox;
	
	
	/**
	 * Contructor de la interfaz alta profesor
	 * @param controlador
	 */
	public PanelAltaProfesor(Controlador controlador) {
		super(controlador);
		
		initPanelAltaPersonal();
		
		this.setVisible(true);
		fixButtons();
	}
	
	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz alta profesor
	 * 
	 */
	private void initPanelAltaPersonal() {
		this.setPreferredSize(new Dimension(640, 480));
		
		Font defaultFont = new Font("Calibri", Font.PLAIN, 14);
		
		Imagen logo = new Imagen("logoPaneles.jpg");
		logo.setBounds(10, 10, 130, 120);
		
		Imagen fondo = new Imagen("fondoAdmin.jpg");
		fondo.setBounds(0, 0, 640, 480);

		JLabel titulo = new JLabel("Dar de alta a un profesor");
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(3,3, 200, 15);
		this.add(titulo);
		
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
		horarioLabel.setBounds(430,330,60,20);
		horarioText = new JTextField();
		horarioText.setBounds(500,330,100,20);
		
		JLabel typeLabel = new JLabel("Tipo");
		typeLabel.setFont(defaultFont);
		typeLabel.setForeground(Color.WHITE);
		typeLabel.setBounds(200,290,40,20);
		typeBox = new JComboBox<String>();
		typeBox.addItem("Profesor de teoria");
		typeBox.addItem("Profesor de practicas");
		typeBox.setBounds(250,290,167,20);

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
			
		JLabel aulaLabel = new JLabel("Aula");
		aulaLabel.setFont(defaultFont);
		aulaLabel.setForeground(Color.WHITE);
		aulaLabel.setBounds(450,290,40,20);
		aulaText = new JTextField();
		aulaText.setText("0");
		aulaText.setBounds(500,290,30,20);
		
			
		JLabel vehiculoLabel = new JLabel("Vehículo");
		vehiculoLabel.setFont(defaultFont);
		vehiculoLabel.setForeground(Color.WHITE);
		vehiculoLabel.setBounds(175,330,65,20);
		vehiculoBox = new JComboBox<String>();
		vehiculoBox.addItem("Seleccione un vehículo...");
		vehiculoBox.setBounds(250,330,167,20);
		
		acceptButton = new JButton("Aceptar");
		acceptButton.setBounds(340, 380, 130, 30);
		clearButton = new JButton("Limpiar campos");
		clearButton.setBounds(180, 380, 130, 30);
		
		this.setLayout(null);
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
		this.add(adressText);
		this.add(phoneLabel);
		this.add(phoneText);
		this.add(numSSLabel);
		this.add(numSSText);
		this.add(sueldoLabel);
		this.add(sueldoText);
		this.add(horarioLabel);
		this.add(horarioText);
		this.add(aulaLabel);
		this.add(aulaText);
		this.add(typeLabel);
		this.add(typeBox);
		this.add(vehiculoLabel);
		this.add(vehiculoBox);
		this.add(acceptButton);	
		this.add(clearButton);
		this.add(fondo);
		this.updateUI();
	}
	
	/**
	 * Procesa los distintos eventos de los botones de la interfaz alta profesor
	 */
	private void fixButtons() {
		fixAcceptButtonListener();
		fixClearFieldsButtonListener();
	}

	/**
	 * Procesa el evento del boton "aceptar" de la interfaz alta profesor
	 */
	private void fixAcceptButtonListener() {
		acceptButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Personal profesor = null;
				boolean correct = true;
				String messageError = "";
				
				if(!camposVacios() && camposNumCorrectos()){										
					if (!Utilidades.fechaCorrecta(birthText.getText())){
						correct = false; 
						messageError = "Por favor, revise el formato de fecha (DD/MM/AAAA), además debe ser anterior a hoy.";
					}				
					else if(((String)typeBox.getSelectedItem()).equalsIgnoreCase("Profesor de teoria") && !(aulaText.getText().trim().isEmpty()) && Utilidades.isPositiveNumber(aulaText.getText().trim())){
						profesor = new ProfesorTeorico(
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
									true, 
									"ProfesorTeorico",
									Integer.parseInt(aulaText.getText().trim())
									);
					}
					else if(((String)typeBox.getSelectedItem()).equalsIgnoreCase("Profesor de practicas") && vehiculoBox.getSelectedIndex() != 0){
						String matricula = (((String)vehiculoBox.getSelectedItem()).split(" "))[0];
						Vehiculo vehiculo = controlador.buscarVehiculo(matricula);		
						profesor = new ProfesorPractico(
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
								true, 
								"ProfesorPractico",
								vehiculo
								);
					}
					
					else{
						correct = false; 
						messageError = "Por favor, seleccione el aula o el vehiculo según corresponda.";
					}							
				}	
				else{
					correct = false;
					messageError = "Por favor, revise los campos, no deben estar vacíos y deben ser números donde corresponda.";
				}
				
				if(correct){
					if(confirmar() == YES)
						controlador.altaPersonal(profesor);
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
		return (dniText.getText().trim().isEmpty() 	 
				|| nombreText.getText().trim().isEmpty() 
				|| apellidoText.getText().trim().isEmpty()
				|| sueldoText.getText().trim().isEmpty() 
				|| numSSText.getText().trim().isEmpty()  
				|| horarioText.getText().trim().isEmpty()
				|| birthText.getText().trim().isEmpty()  
				|| phoneText.getText().trim().isEmpty()  
				|| adressText.getText().trim().isEmpty());
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
	 * Procesa el evento del boton "limpiar campos" de la interfaz alta profesor
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
		aulaText.setText("");
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
		controlador.removeVehiculosObserver(this);
	}
	
	@Override
	public void mostrarPanel() {
		controlador.addPersonalObserver(this);
		controlador.addVehiculosObserver(this);
		controlador.rellenarMatriculas();
	}

	@Override
	public void infoPersonal(ArrayList<Personal> listaOrdenada) {	
	}

	@Override
	public void matriculaVehiculos(ArrayList<Vehiculo> matriculas) {
		vehiculoBox.removeAllItems();
		vehiculoBox.addItem("Seleccione un vehículo...");
		Iterator<Vehiculo> it = matriculas.iterator();
		while(it.hasNext()){
			Vehiculo veh = it.next();
			vehiculoBox.addItem(veh.getMatricula() + " " + veh.getMarca() + " " + veh.getModelo());
		}
	}

	@Override
	public void informacionVehiculos(ArrayList<Vehiculo> vehiculos) {
	}

	@Override
	public void profesorDisponibles(ArrayList<ProfesorTeorico> profesoresT,
			ArrayList<ProfesorPractico> profesoresP) {
	}

}
