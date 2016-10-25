package GUI.secretaria;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import modelo.dominio.gestion.Reserva;
import modelo.dominio.personas.Persona;

import org.freixas.jcalendar.JCalendarCombo;

import GUI.AbstractPanel;
import GUI.Imagen;
import GUI.observables.ReservasObserver;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz información de reserva de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class PanelConsultarReservas extends AbstractPanel implements ReservasObserver{

	private JCalendarCombo calendario;
	private JComboBox<String> profesorBox;
	private JComboBox<String> alumnoBox;
	private ButtonGroup buttonGroup;
	private JRadioButton profesorRadButton;
	private JRadioButton alumnoRadButton;
	private JTable tablaReservas;
	private TableModel modeloReservas;
	private JButton searchButton;
	private JButton deleteButton;
	private JButton modButton;
	private JButton listadoButton;
	private ModificarReserva modificarReserva;
	private ArrayList<Reserva> reservas;
	

	/**
	 * Constructora por defecto
	 * @param controlador
	 */
	public PanelConsultarReservas(Controlador controlador) {
		super(controlador);
		
		initPanelCalendario();
		
		this.setVisible(true);
		fixButtons();
	}

	/**
	 * Metodo encargado de inicializar el panel.
	 */
	private void initPanelCalendario() {
		this.setPreferredSize(new Dimension(1000,1000));
		
		this.setLayout(null);
		
		Font defaultFont = new Font("Calibri", Font.PLAIN, 14);
		
		Imagen logo = new Imagen("logoPaneles.jpg");
		logo.setBounds(10, 10, 130, 120);
		
		Imagen fondo = new Imagen("fondoSecretaria.jpg");
		fondo.setBounds(0, 0, 640, 480);
		
		JLabel titulo = new JLabel("Consultar el calendario de reservas");
		titulo.setForeground(Color.BLACK);
		titulo.setBounds(3,3, 250, 15);
		this.add(titulo);
		
		modificarReserva = new ModificarReserva(controlador);
		buttonGroup = new ButtonGroup();
		profesorRadButton = new JRadioButton("Profesor");
		profesorRadButton.setFont(defaultFont);
		profesorRadButton.setForeground(Color.BLACK);
		profesorRadButton.setBounds(155,97,80,20);
		buttonGroup.add(profesorRadButton);
		profesorRadButton.setContentAreaFilled(false);
		
		JLabel profesorLabel = new JLabel("Profesor");
		profesorLabel.setFont(defaultFont);
		profesorLabel.setForeground(Color.BLACK);
		profesorLabel.setBounds(251,43,54,20);
		profesorBox = new JComboBox<String>();
		profesorBox.setBounds(240,97,230,20);
	
		alumnoRadButton = new JRadioButton("Alumno");
		alumnoRadButton.setFont(defaultFont);
		alumnoRadButton.setForeground(Color.BLACK);
		alumnoRadButton.setBounds(155,70,80,20);
		alumnoRadButton.setContentAreaFilled(false);
		buttonGroup.add(alumnoRadButton);
		
		
		JLabel alumnoLabel = new JLabel("Alumno");		
		alumnoLabel.setFont(defaultFont);
		alumnoLabel.setForeground(Color.BLACK);
		alumnoLabel.setBounds(387,43,54,20);
		alumnoBox = new JComboBox<String>();
		alumnoBox.setBounds(240,70,230,20);
		
		
		calendario = new JCalendarCombo();
		calendario.setDate(new Date());
		calendario.setBounds(419, 12, 211, 20);
		
		listadoButton = new JButton("Listado");
		listadoButton.setBounds(330, 12, 80, 20);
		
		modButton = new JButton("Modificar");
		modButton.setBounds(510, 247, 90, 30);
		
		deleteButton = new JButton("Eliminar");
		deleteButton.setBounds(510, 308, 90, 30);
		searchButton = new JButton("Buscar");
		searchButton.setBounds(510, 80, 90, 30);
		
		
		modeloReservas = new TableModel();
		tablaReservas = new JTable(modeloReservas);

		tablaReservas.setFillsViewportHeight(true);
 
		tablaReservas.setFont(defaultFont);
		tablaReservas.setForeground(Color.BLACK);
		
		JPanel panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBounds(20, 150, 450, 260);
		JScrollPane scroll = new JScrollPane(tablaReservas);
		scroll.setBounds(0, 0, 450, 250);
		panelTabla.add(scroll);
		
		this.setLayout(null);
		this.add(logo);
		this.add(profesorRadButton);
		this.add(profesorBox);
		this.add(alumnoRadButton);
		this.add(alumnoBox);
		this.add(calendario);
		this.add(listadoButton);
		this.add(modButton);
		this.add(deleteButton);
		this.add(searchButton);
		this.add(panelTabla);
		this.add(fondo);
		this.updateUI();
		
		changeFieldsState(false);
	}
	
	/**
	 * Procesa los distintos eventos de los botones de la interfaz baja/modificar personal
	 */
	private void fixButtons() {
		fixListadoButtonListener();
		fixSearchButtonListener();
		fixModButtonListener();
		fixDeleteButtonListener();	
		fixRadioAlumoListener();
		fixRadioProfesorListener();
	}

	
	/**
	 * Permite editar los distintos JTextField para realizar modificaciones
	 * @param state
	 */
	private void changeFieldsState(boolean state){
		searchButton.setEnabled(state);
		alumnoRadButton.setEnabled(state);
		profesorBox.setEnabled(state);
		profesorRadButton.setEnabled(state);
		alumnoBox.setEnabled(state);
		modButton.setEnabled(state);
		deleteButton.setEnabled(state);
		profesorRadButton.setSelected(false);
		alumnoRadButton.setSelected(false);
	}

	/**
	 * Procesa el evento del boton "listado" de la interfaz informacion reservas
	 */
	private void fixListadoButtonListener() {
		listadoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fechaEscogida = calendario.getDate();
				DateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy");
				String fechaStr = fechaHora.format(fechaEscogida);
				
				controlador.mostrarReservas(fechaStr, "", "");
			}
		});
	}
	
	/**
	 * Procesa el evento del boton "buscar" de la interfaz informacion reservas
	 */
	private void fixSearchButtonListener() {
		searchButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				boolean correct = true;
				String messError = "";
				String[] arrayDatos;
				String dniPersona = "";
				if(alumnoRadButton.isSelected()){
					if(alumnoBox.getSelectedIndex() != 0){
						arrayDatos = ((String)alumnoBox.getSelectedItem()).split(" ");
						dniPersona = arrayDatos[arrayDatos.length - 1];
						controlador.filtrarReservas(reservas, dniPersona, "alumno");
					}
					else{
						correct = false;
						messError = "Por favor, seleccione un alumno";
					}
				}
				else if(profesorRadButton.isSelected()){
					if(profesorBox.getSelectedIndex() != 0){
						arrayDatos = ((String)profesorBox.getSelectedItem()).split(" ");
						dniPersona = arrayDatos[arrayDatos.length - 1];

						controlador.filtrarReservas(reservas, dniPersona, "profesor");
					}
					else{
						correct = false;
						messError = "Por favor, seleccione un profesor";
					}
				}
				else{
					correct = false;
					messError = "Seleccione un alumno o profesor";
				}
				
				if(!correct)
					showError("Error", messError);
			}
		});
	}

	/**
	 * Procesa el evento del boton "modificar" de la interfaz informacion reservas
	 */
	private void fixModButtonListener() {
		modButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tablaReservas.getSelectedRow();
				if(0 <= row){
					Reserva reserva = reservas.get(row);
					modificarReserva.mostrarModReserva(reserva);
				}
				else
					showError("Error", "Por favor, selecciona una reserva de la tabla");
				
			}
		});
	}
	
	/**
	 * Procesa el evento del boton "eliminar" de la interfaz informacion reservas
	 */
	private void fixDeleteButtonListener() {
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tablaReservas.getSelectedRow();
				if(0 <= row){
					Reserva reserva = reservas.get(row);

					if(confirmar(reserva.getFecha(), reserva.getHora()) == YES){
						controlador.anularReserva(reserva.getIdReserva());
						reservas.remove(row);
						modeloReservas.fireTableRowsDeleted(row, row);
					}
				}
				else
					showError("Error", "Por favor, selecciona una reserva de la tabla");
			}
		});
	}

	/**
	 * Procesa el evento del radiobutton "alumnos" de la interfaz informacion reservas
	 */
	private void fixRadioAlumoListener() {
		alumnoRadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alumnoBox.setEnabled(true);
				profesorBox.setEnabled(false);
			}
		});	
	}
	
	/**
	 * Procesa el evento del radiobutton "profesores" de la interfaz informacion reservas
	 */
	private void fixRadioProfesorListener() {
		profesorRadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alumnoBox.setEnabled(false);
				profesorBox.setEnabled(true);
			}
		});	
	}

	/**
	 * Ventana de confirmación de cambios SI/NO
	 * @param tittle
	 * @param operacion
	 * @return entero
	 */
	private int confirmar(String fecha, String hora){
		String mensaje = "¿Anular la reserva del día " + fecha + " a las " + hora + ":00?";
		
		return JOptionPane.showConfirmDialog(null, 
				mensaje, 
				"Anular reserva", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE, 
				null);
	}
	
	/**
	 * Clase que crea la tabla de la información del Personal 
	 * 
	 */
	public class TableModel extends AbstractTableModel {
		private String[] columnNames = { "IDReserva", "Hora", "Alumno", "Profesor" };

		public TableModel() {	
			super();
			reservas= new ArrayList<Reserva>();		
		}

		@Override
		public int getRowCount() {
			return reservas.size();
			
		}

		@Override
		public int getColumnCount() {		
			return columnNames.length;		
		}

		/**
		 * Devuelve el contenido de una celda de la columna de la tabla
		 * @param row
		 * @param col
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {		
			Object value = "-";
			
			if(!reservas.isEmpty()){
				Reserva reserva = reservas.get(rowIndex);
				Persona alumno 	 = null;
				String nombreAlumno = "";
				Persona profesor = null; 
				String nombreProfe = "";	
				
				nombreAlumno = "";
				alumno = controlador.buscarAlumno(reservas.get(rowIndex).getIdAlumno());
				if(alumno != null)
					nombreAlumno = alumno.getNombre() + " " + alumno.getApellido();
				
				nombreProfe = "";
				profesor = controlador.buscarPersonal(reservas.get(rowIndex).getIdProfesor()); 
				if(profesor != null)
					nombreProfe = profesor.getNombre() + " " + profesor.getApellido();			
				
				switch(columnIndex){
					case 0: 
						value = reserva.getIdReserva(); break;
					case 1: 
						value = reserva.getHora(); break;
					case 2: 
						value = nombreAlumno; break;
					case 3: 
						value = nombreProfe; break;
					default:
						value = "-";
				}
			}
			
			return value;		
		}

		public String getColumnName(int columnIndex) {		
			return columnNames[columnIndex];			
		}

		/**
		 * Rellena la tabla a partir del DAO de Reservas
		 * @param listaOrdenada
		 */
		public void rellenarTabla(ArrayList<Reserva> listaOrdenada) {		
			reservas = listaOrdenada;
			fireTableDataChanged();
		}
	}

	public void ocultarPanel() {
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

		TreeSet<String> treeSetAlum = new TreeSet<>();
		TreeSet<String> treeSetProf = new TreeSet<>();
			
		modeloReservas.rellenarTabla(listaReservas);
		Iterator<Reserva> it = listaReservas.iterator();
		
		Persona persona	  = null;
		String nombreCompleto = "";
		
		alumnoBox.removeAllItems();
		profesorBox.removeAllItems();
		alumnoBox.addItem("Seleccione un alumno...");
		profesorBox.addItem("Seleccione un profesor...");
		
		while(it.hasNext()){
			persona = controlador.buscarAlumno(it.next().getIdAlumno());
			if(persona != null){
				nombreCompleto = persona.getNombre() + " " + persona.getApellido() + " " + persona.getDni();
				treeSetAlum.add(nombreCompleto);
			}
		}
		
		for(String nombre : treeSetAlum)
			alumnoBox.addItem(nombre);
		
		it = listaReservas.iterator();
		
		while(it.hasNext()){
			persona = controlador.buscarPersonal(it.next().getIdProfesor());
			if(persona != null){
				nombreCompleto = persona.getNombre() + " " + persona.getApellido() + " " + persona.getDni();
				treeSetProf.add(nombreCompleto);
			}
		}
		
		for(String nombre : treeSetProf)
			profesorBox.addItem(nombre);
		
		if(treeSetAlum.isEmpty() && treeSetProf.isEmpty()){
			mensajeInfo("Aviso", "No hay ninguna reserva para hoy.");
		}
		else{
			changeFieldsState(true);
			profesorBox.setEnabled(false);
			alumnoBox.setEnabled(false);
		}
	}
	
	@Override
	public void mostrarReservasPersona(ArrayList<Reserva> listaReservas) {
		modeloReservas.rellenarTabla(listaReservas);
		changeFieldsState(false);
		modButton.setEnabled(true);
		deleteButton.setEnabled(true);
	}
	
}
