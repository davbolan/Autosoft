package GUI.secretaria;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.dominio.gestion.Reserva;
import modelo.dominio.personas.Alumno;
import modelo.dominio.personas.Persona;
import modelo.dominio.personas.Profesor;
import utilidades.Utilidades;
import GUI.AbstractPanel;
import GUI.Imagen;
import GUI.observables.ReservasObserver;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz/panel de nueva reserva de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class PanelNuevaReserva extends AbstractPanel implements ReservasObserver{
	
	private JButton acceptButton;
	private JButton clearButton;
	private JButton searchButton;
	private JTextField dniSearchText;
	private JLabel alumnoText;
	private JLabel profesorText;
	private JTextField fechaText;
	private JTextField horaText;
	private String dniActual;
	private Persona alumnoSelected;
	private Persona profesorSelected;
	
	/**
	 * Contructor de la interfaz/panel nueva reserva
	 * @param controlador
	 */
	public PanelNuevaReserva(Controlador controlador) {
		super(controlador);
		
		initPanelAltaPersonal();
		
		this.setVisible(true);
		fixButtons();
	}
	
	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz/panel nueva reserva
	 * 
	 */
	private void initPanelAltaPersonal() {
		this.setPreferredSize(new Dimension(1000,1000));
		Font defaultFont = new Font("Calibri", Font.PLAIN, 14);
		
		Imagen logo = new Imagen("logoPaneles.jpg");
		logo.setBounds(10, 10, 130, 120);
		
		Imagen fondo = new Imagen("fondoSecretaria.jpg");
		fondo.setBounds(0, 0, 640, 480);
		
		JLabel dniSearchLabel = new JLabel("DNI ");		
		dniSearchLabel.setFont(defaultFont);
		dniSearchLabel.setForeground(Color.BLACK);
		dniSearchLabel.setBounds(267,88,35,20);
		dniSearchText = new JTextField();
		dniSearchText.setBounds(312,88,100,20);
		searchButton = new JButton("Buscar");
		searchButton.setBounds(422,86,80,25);
		
		JLabel profesorLabel = new JLabel("Profesor");		
		profesorLabel.setFont(defaultFont);
		profesorLabel.setForeground(Color.BLACK);
		profesorLabel.setBounds(195,212,60,20);
		profesorText = new JLabel("-");
		profesorText.setFont(new Font("Calibri", Font.BOLD, 16));
		profesorText.setBounds(280,212,235,20);
		
		JLabel nombreLabel = new JLabel("Alumno");		
		nombreLabel.setFont(defaultFont);
		nombreLabel.setForeground(Color.BLACK);
		nombreLabel.setBounds(195,169,60,20);
		alumnoText = new JLabel("-");
		alumnoText.setFont(new Font("Calibri", Font.BOLD, 16));
		alumnoText.setBounds(280,169,235,20);
		
		JLabel fechaLabel = new JLabel("Fecha");
		fechaLabel.setFont(defaultFont);
		fechaLabel.setForeground(Color.BLACK);
		fechaLabel.setBounds(210,256,50,20);
		fechaText = new JTextField();
		fechaText.setBounds(280,256,70,20);
		fechaText.setEnabled(false);
		
		JLabel horaLabel = new JLabel("Hora");
		horaLabel.setFont(defaultFont);
		horaLabel.setForeground(Color.BLACK);
		horaLabel.setBounds(218,299,50,20);
		horaText = new JTextField();
		horaText.setBounds(280,299,25,20);
		
		JTextField ceros = new JTextField();
		ceros.setText(":00");
		ceros.setEditable(false);
		ceros.setBounds(303,299,22,20);
		ceros.setBackground(Color.WHITE);
		
		acceptButton = new JButton("Aceptar");
		acceptButton.setBounds(370, 350, 90, 30);
		clearButton = new JButton("Cancelar");
		clearButton.setBounds(195, 350, 140, 30);
		
		JLabel titulo = new JLabel("Añadir una nueva reserva");
		titulo.setForeground(Color.BLACK);
		titulo.setBounds(3,3, 250, 15);
		this.add(titulo);
		
		this.setLayout(null);
		this.add(logo);
		this.add(profesorLabel);
		this.add(profesorText);
		this.add(dniSearchLabel);
		this.add(dniSearchText);
		this.add(searchButton);
		this.add(nombreLabel);
		this.add(alumnoText);
		this.add(fechaLabel);
		this.add(fechaText);
		this.add(horaLabel);
		this.add(horaText);
		this.add(acceptButton);	
		this.add(clearButton);
		this.add(ceros);
		this.add(fondo);
		
		this.updateUI();
		
		changeFieldsState(false);
	}
	
	/**
	 * Procesa los distintos eventos de los botones de la interfaz nueva reserva
	 */
	private void fixButtons() {
		fixBuscarButtonListener();
		fixAcceptButtonListener();
		fixClearFieldsButtonListener();
	}


	/**
	 * Procesa el evento del boton "buscar" de la interfaz nueva reserva
	 */
	private void fixBuscarButtonListener() {
		searchButton.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent arg0) {
				String dni = dniSearchText.getText().trim();
				
				if(dni.isEmpty())
					showError("Error", "Por favor, rellene el campo del DNI.");
				else{
					dniActual = dni;
					Alumno alumno = controlador.buscarAlumno(dniActual);
					
					if(alumno == null)
						showError("Error", "No se encuentra el alumno con DNI " + dni + ".");				
					else if(!alumno.esActivo()) // Las reservas solo las pueden realizar alumnos activos... 
						showError("Error", "El alumno con DNI " + dni + " no esta disponible.");
					else if(alumno.getNivel() != 2) // ...Y de nivel 2.
						showError("Error", "El alumno con DNI " + dni + " no esta cursando clases prácticas.");				
									
					else{
						Profesor profesor = (Profesor)controlador.buscarPersonal(alumno.getProfesor());
						String profesorStr = "Profesor no disponible";
						
						if(null != profesor && profesor.getDisponibilidad()){
							profesorSelected = profesor;
							profesorStr = profesor.getNombre() + " " + profesor.getApellido();
						}
						alumnoSelected = alumno;				
						alumnoText.setText(alumno.getNombre() + " " + alumno.getApellido() + " (" + dni + ")");		
						profesorText.setText(profesorStr);
						changeFieldsState(true);		
					}
				}
			}
		});	
	}

	/**
	 * Procesa el evento del boton "aceptar" de la interfaz nueva reserva
	 */
	private void fixAcceptButtonListener() {
		acceptButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reserva reserva = null;
				String fechaHoy = Utilidades.fechaHoy();
				boolean correct = true;
				String messageError = "";
				int hora = 0;
				
				if(!camposVacios() && camposNumCorrectos()){
					hora = Integer.parseInt(horaText.getText().trim());
					if (!Utilidades.esUnaFecha(fechaText.getText().trim()) || (Utilidades.compararFecha(fechaHoy, fechaText.getText().trim()) > 0)){
						correct = false; 
						messageError = "Por favor, revise el formato de fecha (DD/MM/AAAA), además no debe ser anterior a hoy.";
					}	
					else if(hora < 10 || 20 < hora){
						correct = false;
						messageError = "Por favor, seleccione una hora entre las 10.00 y las 20.00.";						
					}
					else if(profesorSelected == null){
						correct = false;
						messageError = "El profesor asignado al alumno no está disponible.";						
					}
					else{	
						reserva = new Reserva(
							dniActual, 
							profesorSelected.getDni(), 
							hora+"", 
							fechaText.getText().trim()
						);
					}
				}								
				else{
					correct = false;
					messageError = "Por favor, revise los campos, no deben estar vacíos y deben ser números donde corresponda.";
				}
				
				if(correct){
					if(confirmar(fechaText.getText().trim(), hora+"") == YES)
						controlador.nuevaReserva(reserva);
				}
				else
					showError("Error", messageError);
			}
		}
	);
	}

	/**
	 * Procesa el evento del boton "limpiar campos" de la interfaz nueva reserva
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
	 * Verifica que la hora sea un numero valido
	 */
	private boolean camposNumCorrectos() {
		 return Utilidades.isPositiveNumber(horaText.getText().trim());
	}
	
	/**
	 * Verifica que todos los campos estén rellenados
	 * @return boolean
	 */
	private boolean camposVacios(){
		return(fechaText.getText().trim().isEmpty() 
				|| horaText.getText().trim().isEmpty());
	}
	
	/**
	 * Limpia los campos de los distintos JtextField
	 */
	private void clearFields(){
		dniSearchText.setText("");
		alumnoText.setText("-");
		profesorText.setText("");
		fechaText.setText("");
		horaText.setText("");
		changeFieldsState(false);
		alumnoSelected = null;
		profesorSelected = null;
	}
	
	/**
	 * Permite editar los distintos JTextField para realizar modificaciones
	 * @param state
	 */
	private void changeFieldsState(boolean state){
		fechaText.setEnabled(state);
		horaText.setEnabled(state);
	}
	
	/**
	 *
	 * Ventana de confirmación de cambios SI/NO
	 * @param tittle
	 * @param operacion
	 * @return entero
	 */
	private int confirmar(String fecha, String hora){
		String mensaje = "¿Reservar una clase a " + alumnoSelected.getNombre() + " " + alumnoSelected.getApellido() +
				" con " + profesorSelected.getNombre() + " " + profesorSelected.getApellido() +
				" para el día " + fecha + " a las " + hora + ":00?";
		
		return JOptionPane.showConfirmDialog(null, 
				mensaje, 
				"Nueva reserva", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE, 
				null);
	}

	public void ocultarPanel() {
		clearFields();
		controlador.removeReservasObserver(this);
	}
	
	public void mostrarPanel() {
		controlador.addReservasObserver(this);
	}

	@Override
	public void mensajeInfo(String type, String message) {
		JOptionPane.showOptionDialog(null, message, type, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);	
	}
	
	@Override
	public void showError(String error, String mensaje){
		JOptionPane.showOptionDialog(null, mensaje, error, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);	
	}

	@Override
	public void mostrarReservas(ArrayList<Reserva> listaReservas) {
	}

	@Override
	public void mostrarReservasPersona(ArrayList<Reserva> listaReservas) {

	}
}
