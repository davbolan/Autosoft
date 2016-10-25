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
 * Clase que implementa la interfaz de baja/modificar alumno de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class PanelModAlumno extends AbstractPanel implements AlumnosObserver, PersonalObserver{
	private JTextField nombreText;
	private JTextField apellidoText;
	private JButton deleteButton;
	private JButton modButton;
	private JButton clearButton;
	private JButton searchButton;
	private JTextField dniSearchText;
	private JLabel dniText;
	private JTextField birthText;
	private JTextField addressText;
	private JTextField phoneText;
	private JLabel matriculaText;
	private JComboBox<String> nivelBox;
	private JComboBox<String> profesorBox;
	private JComboBox<String> permisoBox;
	private JComboBox<String> estadoBox;
	
	private String DNIActual = "";
	private Alumno alumnoSelected;
	
	/**
	 * Contructor de la interfaz baja/modificar alumno
	 * @param controlador
	 */
	public PanelModAlumno(Controlador controlador) {
		super(controlador);
		
		initPanelModAlumno();
		
		this.setVisible(true);
		fixButtons();
	}
	
	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz baja/modificar alumno
	 * 
	 */
	private void initPanelModAlumno() {
		this.setPreferredSize(new Dimension(1000,1000));
		Font defaultFont = new Font("Calibri", Font.PLAIN, 14);
		
		Imagen logo = new Imagen("logoPaneles.jpg");
		logo.setBounds(10, 10, 130, 120);
		
		Imagen fondo = new Imagen("fondoSecretaria.jpg");
		fondo.setBounds(0, 0, 640, 480);
		
		JLabel DNISearchLabel = new JLabel("DNI");		
		DNISearchLabel.setFont(defaultFont);
		DNISearchLabel.setBounds(238,38,35,12);
		dniSearchText = new JTextField();
		dniSearchText.setBounds(287,34,100,20);
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
		dniText = new JLabel("-");
		dniText.setFont(new Font("Calibri", Font.BOLD, 16));
		dniText.setBounds(218,160,72,20);
		
		JLabel birthLabel = new JLabel("Fecha de Nacimiento");
		birthLabel.setFont(defaultFont);
		birthLabel.setBounds(84,200,130,20);
		birthText = new JTextField();
		birthText.setBounds(218,200,72,20);
		
		JLabel addressLabel = new JLabel("Dirección");
		addressLabel.setFont(defaultFont);
		addressLabel.setBounds(150,240,60,20);
		addressText = new JTextField();
		addressText.setBounds(218,240,140,20);
		
		JLabel phoneLabel = new JLabel("Teléfono");
		phoneLabel.setFont(defaultFont);
		phoneLabel.setBounds(150,280,53,20);
		phoneText = new JTextField();
		phoneText.setBounds(218,280,84,20);
		
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
		matriculaLabel.setBounds(95,320,131,20);
		matriculaText = new JLabel("-");
		matriculaText.setFont(new Font("Calibri", Font.BOLD, 16));
		matriculaText.setBounds(218,320,100,20);
	
		JLabel profesorLabel = new JLabel("Profesor");
		profesorLabel.setFont(defaultFont);
		profesorLabel.setBounds(390,200,50,20);
		profesorBox = new JComboBox<String>();	
		profesorBox.addItem("Seleccione un profesor...");
		profesorBox.setBounds(444,200,170,20);
			
		JLabel nivelLabel = new JLabel("Nivel");
		nivelLabel.setFont(defaultFont);
		nivelLabel.setBounds(407,240,35,20);
		nivelBox = new JComboBox<String>();
		nivelBox.addItem("Seleccione un nivel...");
		nivelBox.addItem("1");
		nivelBox.addItem("2");
		nivelBox.addItem("3");	
		nivelBox.setBounds(444,240,150,20);
		
		JLabel estadoLabel = new JLabel("Estado");
		estadoLabel.setFont(defaultFont);
		estadoLabel.setBounds(396,280,44,20);
		estadoBox = new JComboBox<String>();
		estadoBox.addItem("Activo");
		estadoBox.addItem("Inactivo");	
		estadoBox.setBounds(444,280,72,20);
			
		searchButton = new JButton("Buscar");
		searchButton.setBounds(407, 34, 75, 21);
		deleteButton = new JButton("Borrar");
		deleteButton.setBounds(444, 373, 80, 30);
		modButton = new JButton("Modificar");
		modButton.setBounds(310, 373, 93, 30);
		clearButton = new JButton("Limpiar campos");
		clearButton.setBounds(140, 373, 140, 30);
		
		JLabel titulo = new JLabel("Modificar o eliminar a un alumno");
		titulo.setForeground(Color.BLACK);
		titulo.setBounds(3,3, 200, 15);
		this.add(titulo);
		
		this.setLayout(null);
		this.add(titulo);
		this.add(logo);
		this.add(DNISearchLabel);
		this.add(dniSearchText);
		this.add(searchButton);
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
		this.add(permisoLabel);
		this.add(permisoBox);
		this.add(matriculaLabel);
		this.add(matriculaText);
		this.add(profesorLabel);
		this.add(profesorBox);
		this.add(nivelLabel);
		this.add(nivelBox);
		this.add(estadoLabel);
		this.add(estadoBox);
		this.add(deleteButton);	
		this.add(modButton);
		this.add(clearButton);
		this.add(fondo);
		this.updateUI();
		
		changeFieldsState(false);
	}
	
	private void changeFieldsState(boolean state){
		nombreText.setEnabled(state);	
		apellidoText.setEnabled(state);
		addressText.setEnabled(state);
		birthText.setEnabled(state);
		phoneText.setEnabled(state);
		nivelBox.setEnabled(state);
		profesorBox.setEnabled(state);
		permisoBox.setEnabled(state);
		estadoBox.setEnabled(state);
	}
	
	/**
	 * Procesa los distintos eventos de los botones de la interfaz baja/modificar alumno
	 */
	private void fixButtons() {
		fixSearchButtonListener();
		fixDeleteButtonListener();
		fixModButtonListener();
		fixClearFieldsButtonListener();
		fixProfesorComboboxListener();
	}

	/**
	 * Procesa el evento del boton "profesorcombo" de la interfaz baja/modificar alumno
	 */
	private void fixProfesorComboboxListener() {
		nivelBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					profesorBox.removeAllItems();
					profesorBox.addItem("Seleccione un nivel...");
					switch(nivelBox.getSelectedIndex()){
						case 1: 
							controlador.rellenarProfesor("", "ProfesorTeorico"); break;
						case 2: 
							controlador.rellenarProfesor("ProfesorPractico", ""); break;
						case 3:{
							profesorBox.removeAllItems();
							profesorBox.addItem("Seleccione un profesor...");
							profesorBox.addItem("Sin profesor");
						} break;
						default: break;
					}
				}
			}
		);
	}

	/**
	 * Procesa el evento del boton "buscar" de la interfaz baja/modificar alumno
	 */
	private void fixSearchButtonListener(){
		searchButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String dni = dniSearchText.getText().trim();
				if(!dni.isEmpty()){
					Alumno alumno = controlador.buscarAlumno(dniSearchText.getText());
					if(alumno != null){
						alumnoSelected = alumno;
						changeFieldsState(true);
						
						nombreText.setText(alumno.getNombre());
						apellidoText.setText(alumno.getApellido());
						dniText.setText(alumno.getDni());
						addressText.setText(alumno.getDireccion());

						birthText.setText(alumno.getFechaNacimiento());
						phoneText.setText(alumno.getTelefono() + "");
						permisoBox.setSelectedItem(alumno.getPermiso());
						estadoBox.setSelectedItem(alumno.esActivo() ? "Activo" : "Inactivo");
						// El actionListener de nivelBox ya se encarga de rellenar profesorBox
						nivelBox.setSelectedItem(alumno.getNivel()+"");
					}
					
					else{
						showError("Error", "No hay ningun alumno con DNI " + DNIActual + ".");
					}
				}
				else
					showError("Error", "Inserta un DNI a buscar.");
			}
			
		});
			
	}
	
	/**
	 * Procesa el evento del boton "eliminar" de la interfaz baja/modificar alumno
	 */
	private void fixDeleteButtonListener() {
		deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String dni = dniText.getText().trim();
					if(!dni.isEmpty()){
						if(confirmar("Dar de baja", "dar de baja") == YES)
							controlador.bajaAlumno(dni);
					}
					else
						showError("Error", "El campo DNI no puede estar vacío.");
				}
		});	
	}
	
	/**
	 * Procesa el evento del boton "modificar" de la interfaz baja/modificar alumno
	 */
	private void fixModButtonListener() {
		modButton.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Alumno alumno = null;
					boolean correct = true;
					String messageError = "";
					
					if(!camposVacios() && camposNumCorrectos()){										
						if(nivelBox.getSelectedIndex() == 0){
							correct = false;
							messageError = "Por favor, seleccione un nivel del alumno (1 = Teorico, 2 = práctico, 3 = carné adquirido).";						
						}
						else if(permisoBox.getSelectedIndex() == 0){
							correct = false;
							messageError = "Por favor, seleccione un tipo de permiso.";						
						}
						else if(profesorBox.getSelectedIndex() == 0){
							correct = false;
							messageError = "Por favor, seleccione un profesor para el alumno.";						
						}
						else if (!Utilidades.fechaCorrecta(birthText.getText())){
							correct = false; 
							messageError = "Por favor, revise el formato de fecha (DD/MM/AAAA), además debe ser anterior a hoy.";
						}
						else{
							String dniProfesor[] = ((String)profesorBox.getSelectedItem()).split(" ");
							int nivel = Integer.parseInt((String)nivelBox.getSelectedItem());
							String estado = (String)estadoBox.getSelectedItem();
							alumno = new Alumno(
									alumnoSelected.getDni(), 
									nombreText.getText().trim(), 
									apellidoText.getText().trim(),  
									birthText.getText().trim(),
									nivel, 
									(String)permisoBox.getSelectedItem(), 
									addressText.getText().trim(), 
									dniProfesor[dniProfesor.length - 1], 
									alumnoSelected.getFechaMatricula(), 
									Integer.parseInt(phoneText.getText().trim()),
									estado.equalsIgnoreCase("Activo")
									);
						}							
					}	
					else{
						correct = false;
						messageError = "Por favor, revise los campos, no deben estar vacíos y deben ser números donde corresponda.";
					}
					
					if(correct){
						if(confirmar("Modificar", "modificar") == YES)
							controlador.modificarAlumno(alumno);
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
	private boolean camposVacios() {
		return(nombreText.getText().trim().isEmpty() 
				|| apellidoText.getText().trim().isEmpty()
				|| dniText.getText().trim().isEmpty() 
				|| addressText.getText().trim().isEmpty());
	}
	
	/**
	 * Verifica que el campo sueldo, num SS y telefono sean numeros validos
	 */
	private boolean camposNumCorrectos(){	
		return (Utilidades.isPositiveNumber(phoneText.getText().trim()));		
	}
	
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
		matriculaText.setText("");
		nombreText.setText("");
		apellidoText.setText("");
		dniSearchText.setText("");
		dniText.setText("-");
		birthText.setText("");
		phoneText.setText("");
		addressText.setText("");
		nivelBox.setSelectedIndex(0);
		profesorBox.setSelectedIndex(0);
		permisoBox.setSelectedIndex(0);
		DNIActual = "";
		alumnoSelected = null;
		changeFieldsState(false);
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
				"¿Estás seguro de " + operacion + " a " + alumnoSelected.getNombre() + " " + alumnoSelected.getApellido() + "?", 
				tittle, 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE, 
				null);
	}
	
	public void ocultarPanel() {
		clearFields();
		controlador.removeAlumnosObserver(this);
		controlador.removePersonalObserver(this);
	}
	
	public void mostrarPanel() {
		controlador.addPersonalObserver(this);
		controlador.addAlumnosObserver(this);
	}

	@Override
	public void mensajeInfo(String tipo, String mensaje) {
		clearFields();
		JOptionPane.showOptionDialog(null, mensaje, tipo, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);			
	}
	
	@Override
	public void showError(String error, String mensaje){
		JOptionPane.showOptionDialog(null, mensaje, error, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);	
	}

	@Override
	public void infoPersonal(ArrayList<Personal> listaOrdenada) {	
	}

	@Override
	public void profesorDisponibles(ArrayList<ProfesorTeorico> profesoresT,
			ArrayList<ProfesorPractico> profesoresP) {
		ProfesorPractico profP;
		ProfesorTeorico profT;
		String dni = "";
		String dniProfDeAlu = alumnoSelected.getProfesor();
		int index = 0;
		int i = 1;
		
		profesorBox.removeAllItems();
		profesorBox.addItem("Seleccione un profesor...");
		
		Iterator<ProfesorPractico> itProfeP = profesoresP.iterator();
		while(itProfeP.hasNext()){
			profP = itProfeP.next();
			dni = profP.getDni();
			if(dniProfDeAlu.equalsIgnoreCase(dni))
				index = i;
			profesorBox.addItem("(P) " + profP.getNombre() + " " + profP.getApellido() + " " + dni);
			i++;
		}
		
		Iterator<ProfesorTeorico> itProfeT = profesoresT.iterator();
		while(itProfeT.hasNext()){
			profT = itProfeT.next();
			dni = profT.getDni();
			if(dniProfDeAlu.equalsIgnoreCase(dni))
				index = i;
			profesorBox.addItem("(T) " + profT.getNombre() + " " + profT.getApellido() + " " + dni);
			i++;
		}
		
		profesorBox.setSelectedIndex(index);
	}
	

	@Override
	public void infoAlumnos(ArrayList<Alumno> listaOrdenada) {
	}
}
