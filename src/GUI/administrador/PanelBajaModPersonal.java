package GUI.administrador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
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
 * Clase que implementa la interfaz de baja/modificar personal de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class PanelBajaModPersonal extends AbstractPanel implements PersonalObserver{
	
	private JTextField nombreText;
	private JTextField apellidoText;
	private JButton modButton;
	private JButton clearButton;
	private JLabel dniText;
	private JTextField birthText;
	private JTextField addressText;
	private JTextField phoneText;
	private JTextField numSSText;
	private JTextField sueldoText;
	private JTextField horarioText;
	private JTextArea descripcionText;
	private JLabel cargoLabelEmp;
	private JButton deleteButton;
	private JButton searchButton;
	private JTextField dniSearchText;
	
	private String DNIActual = "";
	private Personal personalSelected;

	/**
	 * Contructor de la interfaz baja/modificar personal
	 * @param controlador
	 */
	public PanelBajaModPersonal(Controlador controlador) {
		super(controlador);
		
		initPanelBajaModPersonal();
		
		this.setVisible(true);
		fixButtons();
	}
	
	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz baja/modificar personal
	 * 
	 */
	private void initPanelBajaModPersonal() {

		this.setPreferredSize(new Dimension(640, 480));
		Font defaultFont = new Font("Calibri", Font.PLAIN, 14);
		
		Imagen logo = new Imagen("logoPaneles.jpg");
		logo.setBounds(10, 10, 130, 120);
		
		Imagen fondo = new Imagen("fondoAdmin.jpg");
		fondo.setBounds(0, 0, 640, 480);
		
		JLabel dniLabel1 = new JLabel("DNI: ");		
		dniLabel1.setFont(defaultFont);
		dniLabel1.setForeground(Color.WHITE);
		dniLabel1.setBounds(250,40,100,20);
		dniSearchText = new JTextField();
		dniSearchText.setBounds(300,40,100,20);
		searchButton = new JButton("Buscar");
		searchButton.setBounds(420,37,80,25);		

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
		dniText = new JLabel();
		dniText.setFont(new Font("Calibri", Font.BOLD, 16));
		dniText.setForeground(Color.WHITE);
		dniText.setBounds(250,170,100,20);
		
		JLabel addressLabel = new JLabel("Dirección");
		addressLabel.setFont(defaultFont);
		addressLabel.setForeground(Color.WHITE);
		addressLabel.setBounds(170,210,70,20);
		addressText = new JTextField();
		addressText.setBounds(250,210,150,20);
				
		JLabel cargoLabel = new JLabel("Cargo");		
		cargoLabel.setFont(defaultFont);
		cargoLabel.setForeground(Color.WHITE);
		cargoLabel.setBounds(200,330,40,20);
		cargoLabelEmp = new JLabel("-");
		cargoLabelEmp.setFont(new Font("Calibri", Font.BOLD, 16));
		cargoLabelEmp.setForeground(Color.WHITE);
		cargoLabelEmp.setBounds(250,330,150,20);
		
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

		JLabel numSSLabel = new JLabel("Nº SS");
		numSSLabel.setFont(defaultFont);
		numSSLabel.setForeground(Color.WHITE);
		numSSLabel.setBounds(190,250,50,20);
		numSSText = new JTextField();
		numSSText.setBounds(250,250,100,20);		

		JLabel sueldoLabel = new JLabel("Sueldo");
		sueldoLabel.setFont(defaultFont);
		sueldoLabel.setForeground(Color.WHITE);
		sueldoLabel.setBounds(440,250,50,20);
		sueldoText = new JTextField();
		sueldoText.setBounds(500,250,80,20);
		
		JLabel horarioLabel = new JLabel("Horario");
		horarioLabel.setFont(defaultFont);
		horarioLabel.setForeground(Color.WHITE);
		horarioLabel.setBounds(180,290,60,20);
		horarioText = new JTextField();
		horarioText.setBounds(250,290,100,20);
		
		JLabel descripcionLabel = new JLabel("Descripción");
		descripcionText = new JTextArea();
		JScrollPane scrollDescripcionText = new JScrollPane();
		scrollDescripcionText.setBounds(464, 300, 150, 60);
		scrollDescripcionText.setViewportView(descripcionText);
		descripcionLabel.setFont(defaultFont);
		descripcionLabel.setForeground(Color.WHITE);
		descripcionLabel.setBounds(390,300,70,20);
		
		deleteButton = new JButton("Eliminar");
		deleteButton.setBounds(445, 380, 130, 30);
		modButton = new JButton("Modificar");
		modButton.setBounds(281, 380, 130, 30);
		clearButton = new JButton("Limpiar campos");
		clearButton.setBounds(110, 380, 130, 30);
		
		JLabel titulo = new JLabel("Dar de baja o modificar a un empleado");
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(3,3, 250, 15);
		this.add(titulo);
		
		this.setLayout(null);
		
		this.add(logo);
		this.add(searchButton);
		this.add(dniLabel1);
		this.add(dniSearchText);
		this.add(cargoLabel);
		this.add(cargoLabelEmp);
		this.add(nombreLabel);
		this.add(nombreText);
		this.add(apellidoLabel);
		this.add(apellidoText);
		this.add(dniLabel);
		this.add(dniText);
		this.add(birthLabel);
		this.add(birthText);
		this.add(addressLabel);
		this.add(addressText);
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
		this.add(deleteButton);
		this.add(modButton);	
		this.add(clearButton);
		this.add(fondo);
		this.updateUI();
		
		changeFieldsState(false);
	}
	
	/**
	 * Permite editar los distintos JTextField para realizar modificaciones
	 * @param state
	 */
	private void changeFieldsState(boolean state){
		nombreText.setEnabled(state);	
		apellidoText.setEnabled(state);
		addressText.setEnabled(state);
		numSSText.setEnabled(state);
		horarioText.setEnabled(state);
		birthText.setEnabled(state);
		phoneText.setEnabled(state);
		sueldoText.setEnabled(state);
		descripcionText.setEnabled(state);
	}
	
	/**
	 * Procesa los distintos eventos de los botones de la interfaz baja/modificar personal
	 */
	private void fixButtons() {
		fixSearchButtonListener();
		fixClearFieldsButtonListener();
		fixModButtonListener();	
		fixDeleteButtonListener();	
	}

	/**
	 * Procesa el evento del boton "buscar" de la interfaz baja/modificar personal
	 */
	private void fixSearchButtonListener() {
		searchButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String dni = dniSearchText.getText().trim();
					if(!dni.isEmpty()){
						DNIActual = dni;
						Personal personal = controlador.buscarPersonal(DNIActual);
						String cargo = "";
						if(personal != null)
							cargo = personal.getCargo();
						
						if (!cargo.isEmpty() && (cargo.equalsIgnoreCase("profesor") || cargo.equalsIgnoreCase("secretaria") || cargo.equalsIgnoreCase("otros"))) {
							personalSelected = personal;
							changeFieldsState(true);
							nombreText.setText(personal.getNombre());
							apellidoText.setText(personal.getApellido());
							dniText.setText(personal.getDni());
							addressText.setText(personal.getDireccion());
							numSSText.setText(personal.getnSS() + "");
							horarioText.setText(personal.getHorario());
							birthText.setText(personal.getFechaNacimiento());
							phoneText.setText(personal.getTelefono() + "");
							sueldoText.setText(personal.getSueldo() + "");
							descripcionText.setText("Sin descripcion");
							switch (personal.getCargo().toLowerCase()) {
							case "secretaria":
								cargoLabelEmp.setText("Secretaria");
								break;
							case "administrador":
								cargoLabelEmp.setText("Administrador");
								break;
							case "profesor":
								if (((Profesor) personal).getTipo().equalsIgnoreCase("ProfesorPractico")){
									cargoLabelEmp.setText("Profesor de practicas");
								}
								else
									cargoLabelEmp.setText("Profesor de teoria");
								break;
							case "otros":
								descripcionText.setText(((Otros) personal).getDescripcion());
								cargoLabelEmp.setText("Otros");
							default:
								cargoLabelEmp.setText("-");;						
							}
							
						} 
						else
							showError("Error", "No hay ningun personal con DNI " + DNIActual + ".");
					} 
					else
						showError("Error", "Inserta un DNI a buscar.");
			}
		}
		);
	}
	
	/**
	 * Procesa el evento del boton "limpiar campos" de la interfaz baja/modificar personal
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
		dniText.setText("-");
		birthText.setText("");
		addressText.setText("");
		phoneText.setText("");
		numSSText.setText("");
		sueldoText.setText("");
		horarioText.setText("");
		descripcionText.setText("");
		DNIActual = "";
		personalSelected = null;
	}
	
	/**
	 * Procesa el evento del boton "eliminar" de la interfaz baja/modificar personal
	 */
	private void fixDeleteButtonListener() {
		deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String dni = dniText.getText().trim();
					if(!dni.isEmpty())
						if(confirmar("Dar de baja", "dar de baja") == YES)
							controlador.bajaPersonal(dni);
					else
						showError("Error", "El campo DNI no puede estar vacío.");
				}
		});			
	}
	
	/**
	 * Procesa el evento del boton "modificar" de la interfaz baja/modificar personal
	 */
	private void fixModButtonListener() {
		modButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Personal personal = null;
				boolean correct = true;
				String messageError = "";
								
				if(!camposVacios() && camposNumCorrectos()){										
					if(DNIActual.isEmpty()){
						correct = false;
						messageError = "Por favor, busque un personal para poder modificarlo correctamente.";
					}
					
					else if (!Utilidades.fechaCorrecta(birthText.getText())){
						correct = false; 
						messageError = "Por favor, revise el formato de fecha (DD/MM/AAAA). También debe ser anterior a hoy.";
					}
					
					else{
						String cargo = cargoLabelEmp.getText();
					
						switch(cargo.toLowerCase()){
						case "secretaria":
							personal = new Secretaria(
									personalSelected.getDni(), 
									nombreText.getText().trim(), 
									apellidoText.getText().trim(), 
									Integer.parseInt(sueldoText.getText().trim()),
									Integer.parseInt(numSSText.getText().trim()), 
									horarioText.getText().trim(),
									birthText.getText().trim(),
									Integer.parseInt(phoneText.getText().trim()),
									"Secretaria",
									addressText.getText().trim()
									);
							break;
						case "administrador":
							personal = new Administrador(
									personalSelected.getDni(), 
									nombreText.getText().trim(), 
									apellidoText.getText().trim(), 
									Integer.parseInt(sueldoText.getText().trim()),
									Integer.parseInt(numSSText.getText().trim()), 
									horarioText.getText().trim(),
									birthText.getText().trim(),
									Integer.parseInt(phoneText.getText().trim()),
									"Administrador",
									addressText.getText().trim()
									);
							break;
							case "profesor de teoria":
								personal = new ProfesorTeorico(
										personalSelected.getDni(), 
										nombreText.getText().trim(), 
										apellidoText.getText().trim(), 
										Integer.parseInt(sueldoText.getText().trim()),
										Integer.parseInt(numSSText.getText().trim()), 
										horarioText.getText().trim(),
										birthText.getText().trim(),
										Integer.parseInt(phoneText.getText().trim()),
										"Profesor",
										addressText.getText().trim(),
										((ProfesorTeorico)personalSelected).getDisponibilidad(), 
										"ProfesorTeorico",
										((ProfesorTeorico)personalSelected).getAula()
										);
								break;
							case "profesor de practicas":
								personal = new ProfesorPractico(
										personalSelected.getDni(), 
										nombreText.getText().trim(), 
										apellidoText.getText().trim(), 
										Integer.parseInt(sueldoText.getText().trim()),
										Integer.parseInt(numSSText.getText().trim()), 
										horarioText.getText().trim(),
										birthText.getText().trim(),
										Integer.parseInt(phoneText.getText().trim()),
										"Profesor",
										addressText.getText().trim(),
										((ProfesorPractico)personalSelected).getDisponibilidad(), 
										"ProfesorPractico",
										((ProfesorPractico)personalSelected).getVehiculo()
										);
								break;
							case "otros": 
								personal = new Otros(
										personalSelected.getDni(), 
										nombreText.getText().trim(), 
										apellidoText.getText().trim(), 
										Integer.parseInt(sueldoText.getText().trim()),
										Integer.parseInt(numSSText.getText().trim()), 
										horarioText.getText().trim(),
										birthText.getText().trim(),
										Integer.parseInt(phoneText.getText().trim()),
										"Otros",
										addressText.getText().trim(),		
										descripcionText.getText()
										);
								break;
							default:
						}
					}						
				}	
				else{
					correct = false;
					messageError = "Por favor, revise los campos, no deben estar vacios y deben ser números donde corresponda.";
				}
				
				if(correct){
					if(confirmar("Modificar", "modificar") == YES)
						controlador.modificarPersonal(personal);
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
		return(dniText.getText().trim().isEmpty() 	 
				|| nombreText.getText().trim().isEmpty() 
				|| apellidoText.getText().trim().isEmpty()
				|| sueldoText.getText().trim().isEmpty() 
				|| numSSText.getText().trim().isEmpty()  
				|| horarioText.getText().trim().isEmpty()	
				|| birthText.getText().trim().isEmpty()  
				|| phoneText.getText().trim().isEmpty()
				||  addressText.getText().trim().isEmpty());
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
	 *
	 * Ventana de confirmación de cambios SI/NO
	 * @param tittle
	 * @param operacion
	 * @return entero
	 */
	private int confirmar(String tittle, String operacion){
		return JOptionPane.showConfirmDialog(null, 
				"¿Estás seguro de " + operacion + " a " + personalSelected.getNombre() + " " + personalSelected.getApellido() + "?", 
				tittle, 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE, 
				null);
	}
	
	@Override
	public void mensajeInfo(String type, String message) {
		clearFields();
		JOptionPane.showOptionDialog(null, message, type, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);	
	}

	public void showError(String error, String mensaje){
		JOptionPane.showOptionDialog(null, mensaje, error, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);	
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
