package GUI.secretaria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import modelo.dominio.personas.Alumno;
import GUI.AbstractPanel;
import GUI.GUI;
import GUI.observables.AlumnosObserver;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz información de Alumno de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class PanelHistorialAlumnos extends AbstractPanel implements AlumnosObserver{
	private MiTableModel model;
	private JTable table;


	/**
	 * Constructora por defecto
	 * @param controlador
	 */
	public PanelHistorialAlumnos(Controlador controlador){
		super(controlador);
		
		initPanelAltaAlumnos();
		this.setVisible(true);	
	}


	/**
	 * Metodo encargado de inicializar el panel.
	 */
	private void initPanelAltaAlumnos() {
		Font defaultFont = new Font("Calibri", Font.BOLD, 16);
		this.setLayout(null);
		
		JPanel panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBounds(20,20 , 300, 300);
		
		this.setLayout(new BorderLayout());
		model = new MiTableModel();
		table = new JTable(model){
			private static final long serialVersionUID = 1L;
             URL url = GUI.class.getResource("imagenes/fondoSecretaria.jpg");
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

		table.setCellSelectionEnabled(false);
		table.setFillsViewportHeight(true);
		table.setOpaque(false); 
		table.setFont(defaultFont);
		table.setForeground(Color.BLACK);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0, 20, 635, 350);
		panelTabla.add(scroll);

		JLabel titulo = new JLabel("Ver el historial de alumnos");
		titulo.setBounds(3, 0, 250, 20);
		titulo.setForeground(Color.BLACK);
		titulo.setFont(defaultFont);
		
		this.add(titulo);
		this.add(panelTabla);
	}

	/**
	 * Clase que crea la tabla de la información de Alumno 
	 * 
	 */
	public class MiTableModel extends AbstractTableModel {
		private ArrayList<Alumno> elems;
		private String[] columnNames = { "Nombre", "Apellidos", "DNI", "Profesor", "Fecha matrícula"};

		public MiTableModel() {
			super();
			elems = new ArrayList<Alumno>();
		}

		@Override
		public int getRowCount() {
			return elems.size();		
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
		public Object getValueAt(int row, int col) {
			Object value = "-";
			
			if(!elems.isEmpty()){
				Alumno alumno = elems.get(row);
					
				switch(col){
					case 0: 
						value = alumno.getNombre(); break;
					case 1: 
						value = alumno.getApellido(); break;
					case 2: 
						value = alumno.getDni(); break;
					case 3: 
						value = alumno.getProfesor(); break;
					case 4: 
						value = alumno.getFechaMatricula(); break;
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
		 * Rellena la tabla a partir del DAO de Alumno
		 * @param listaOrdenada
		 */
		public void rellenarTabla(ArrayList<Alumno> listaOrdenada) {
			elems = listaOrdenada;
				for(int row = 0; row < elems.size(); row++){
					setValueAt(elems.get(row).getNombre()		, row, 0);
					setValueAt(elems.get(row).getApellido() 	, row, 1);
					setValueAt(elems.get(row).getDni()			, row, 2);
					setValueAt(elems.get(row).getProfesor()		, row, 3);
					setValueAt(elems.get(row).getFechaMatricula(), row, 4);
				}		
			
			fireTableDataChanged();
		}
	}

	@Override
	public void ocultarPanel() {
		controlador.removeAlumnosObserver(this);
	}
	
	@Override
	public void mostrarPanel() {
		controlador.addAlumnosObserver(this);
		controlador.historialAlumnos();
	}

	@Override
	public void mensajeInfo(String tipo, String mensaje) {	
	}

	@Override
	public void showError(String error, String mensaje) {
	}


	@Override
	public void infoAlumnos(ArrayList<Alumno> listaOrdenada) {
		model.rellenarTabla(listaOrdenada);
	}
}
