package GUI.profesor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import modelo.dominio.gestion.Reserva;
import modelo.dominio.personas.Persona;
import utilidades.Utilidades;
import GUI.AbstractPanel;
import GUI.GUI;
import GUI.observables.ReservasObserver;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz horario de profesor de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class PanelHorario extends AbstractPanel  implements ReservasObserver{
	private TableModel model;
	private JTable table;
	private JButton todayButton;
	private JButton allButton;

	/**
	 * Constructor de la interfaz horario
	 * @param controlador
	 */
	public PanelHorario(Controlador controlador) {
		super(controlador);
		initPanelHorario();
		this.setVisible(true);
		fixButtons();
	}
	
	/**
	 * Procesa los distintos eventos de los botones de la interfaz horario
	 */
	private void fixButtons() {
		fixTodayButtonListener();
		fixAllButtonListener();
	}

	/**
	 * Procesa el evento del boton "Hoy" de la interfaz horario
	 */
	private void fixTodayButtonListener() {
		todayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.mostrarMisReservas("hoy");
			}
		});
	}

	/**
	 * Procesa el evento del boton "Todas" de la interfaz horario
	 */
	private void fixAllButtonListener() {
		allButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.mostrarMisReservas("todas");
			}
		});
	}
	
	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz horario
	 * 
	 */
	private void initPanelHorario() {
		Font defaultFont = new Font("Calibri", Font.PLAIN, 14);
		this.setLayout(null);
		JPanel panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBounds(20,20 , 300, 300);
		this.setLayout(new BorderLayout());
		model = new TableModel();
		table = new JTable(model){
			private static final long serialVersionUID = 1L;
             URL url = GUI.class.getResource("imagenes/fondoProfesor.jpg");
             ImageIcon imageBackground;
                @Override
                public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                    final Component c = super.prepareRenderer(renderer, row, column);
                    if (c instanceof JComponent){
                        ((JComponent) c).setOpaque(false);                   
                    }
                    return c;
                }
                @Override
                public void paint(Graphics graphics) {
                	if(url != null){
                		imageBackground = new ImageIcon(url);
                		graphics.drawImage(imageBackground.getImage(), 0, 0,getWidth(),getHeight(),null);
                		super.paint(graphics);
                	}
                }
		};

		table.setFillsViewportHeight(true);
		table.setOpaque(false); 
		table.setFont(defaultFont);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0, 20, 635, 350);
		panelTabla.add(scroll);
		JLabel titulo = new JLabel(Utilidades.fechaHoy());
		titulo.setBounds(3, 0, 250, 20);
		titulo.setForeground(Color.black);
		titulo.setFont(defaultFont);

		todayButton = new JButton("Hoy");
		todayButton.setBounds(200, 380, 80, 30);
		
		allButton = new JButton("Todas");
		allButton.setBounds(350, 380, 80, 30);
		
		this.add(titulo);
		this.add(todayButton);
		this.add(allButton);
		this.add(panelTabla);
	}


	/**
	 * Clase que crea la tabla para mostrar el horario 
	 *
	 */
	public class TableModel extends AbstractTableModel {

		private String[] columnNames = {"Fecha", "Hora", "Alumno"};
		private ArrayList<Reserva> misReservas;
		
		public TableModel() {	
			super();	
			misReservas = new ArrayList<Reserva>();
		}

		
		public int getRowCount() {
			return misReservas.size();
		}

		
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Devuelve el contenido de una celda de la tabla
		 * @param columnIndex
		 * @param rowIndex
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {			
			Object value = "-";
				
			if(!misReservas.isEmpty()){
				Reserva Reserva = misReservas.get(rowIndex);
					
				Persona alumno = null; 
				String nombreAlumn = "No disponible";			
				alumno = controlador.buscarAlumno(misReservas.get(rowIndex).getIdAlumno()); 
				if(alumno != null)
					nombreAlumn = alumno.getNombre() + " " + alumno.getApellido() + "(" + alumno.getDni()+")";		
				
				switch(columnIndex){
					case 0: 
						value = Reserva.getFecha(); 	 break;
					case 1: 
						value = Reserva.getHora()+":00"; break;
					case 2: 
						value = nombreAlumn; 			 break;
					default:
						value = "-";
				}
			}
			return value;
		}

		public String getColumnName(int columnIndex) {	
			return columnNames[columnIndex];		
		}

		public void rellenarTabla(ArrayList<Reserva> listaOrdenada) {
			misReservas = listaOrdenada;
			fireTableDataChanged();
		}
	}

	@Override
	public void ocultarPanel() {
		controlador.removeReservasObserver(this);
	}
	
	@Override
	public void mostrarPanel() {
		controlador.addReservasObserver(this);
		controlador.mostrarMisReservas("hoy");		
	}

	@Override
	public void mensajeInfo(String type, String message) {}

	@Override
	public void showError(String type, String message) {}

	@Override
	public void mostrarReservas(ArrayList<Reserva> listaReservas) {}

	@Override
	public void mostrarReservasPersona(ArrayList<Reserva> misReservas) {
		model.rellenarTabla(misReservas);
	}
}
