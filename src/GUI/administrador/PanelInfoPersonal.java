package GUI.administrador;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import modelo.dominio.personas.*;
import GUI.AbstractPanel;
import GUI.GUI;
import GUI.observables.PersonalObserver;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz información de Personal de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class PanelInfoPersonal extends AbstractPanel implements PersonalObserver{
	
	private JTable table;
	private MiTableModel model;

	/**
	 * Constructora por defecto
	 * @param controlador
	 */
	public PanelInfoPersonal(Controlador controlador){
		super(controlador);
		
		initPanelAltaPersonal();
		this.setVisible(true);	
	}

	/**
	 * Metodo encargado de inicializar el panel.
	 */
	private void initPanelAltaPersonal() {
		Font defaultFont = new Font("Calibri", Font.BOLD, 16);
		this.setLayout(null);
		
		JPanel panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBounds(20,20 , 300, 300);
		
		this.setLayout(new BorderLayout());
		model = new MiTableModel();
		table = new JTable(model){
			private static final long serialVersionUID = 1L;
             URL url = GUI.class.getResource("imagenes/fondoAdmin.jpg");
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
		table.setForeground(Color.WHITE);	

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0, 20, 635, 350);
		panelTabla.add(scroll);
		JLabel titulo = new JLabel("Ver la información de los empleados");
		titulo.setBounds(3, 0, 250, 20);
		titulo.setForeground(Color.BLACK);
		titulo.setFont(defaultFont);
		
		this.add(titulo);
		this.add(panelTabla);
	}

	/**
	 * Clase que crea la tabla de la información del Personal 
	 * 
	 */
	public class MiTableModel extends AbstractTableModel {
		private ArrayList<Personal> vehiculos;
		private String[] columnNames = { "DNI", "Apellidos", "Nombre",  "Cargo", "Nº SS", "Teléfono", "Horario" };

		public MiTableModel() {
			super();
			vehiculos = new ArrayList<Personal>();
		}

		@Override
		public int getRowCount() {
			return vehiculos.size();		
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
			
			if(!vehiculos.isEmpty()){
				Personal personal = vehiculos.get(row);
					
				switch(col){
					case 0: 
						value = personal.getDni(); break;
					case 1: 
						value = personal.getApellido(); break;
					case 2: 
						value = personal.getNombre(); break;
					case 3: 
						value = personal.getCargo(); break;
					case 4: 
						value = personal.getnSS(); break;
					case 5: 
						value = personal.getTelefono(); break;
					case 6: 
						value = personal.getHorario(); break;
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
		 * Rellena la tabla a partir del DAO de Personal
		 * @param listaOrdenada
		 */
		public void rellenarTabla(ArrayList<Personal> listaOrdenada) {
			vehiculos = listaOrdenada;
				for(int row = 0; row < vehiculos.size(); row++){
					setValueAt(vehiculos.get(row).getDni()		, row, 0);
					setValueAt(vehiculos.get(row).getApellido() , row, 1);
					setValueAt(vehiculos.get(row).getNombre()	, row, 2);
					setValueAt(vehiculos.get(row).getApellido()	, row, 3);
					setValueAt(vehiculos.get(row).getApellido()	, row, 4);
				}		
			
			fireTableDataChanged();
		}
	}

	@Override
	public void showError(String error, String mensaje){
		JOptionPane.showOptionDialog(null, mensaje, error, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);	
	}

	@Override
	public void mensajeInfo(String type, String mensaje) {
		JOptionPane.showOptionDialog(null, mensaje, type, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);	
		
	}

	@Override
	public void ocultarPanel() {
		controlador.removePersonalObserver(this);
	}
	
	@Override
	public void mostrarPanel() {
		controlador.addPersonalObserver(this);
		controlador.informacionPersonal();
	}

	@Override
	public void infoPersonal(ArrayList<Personal> listaOrdenada) {
		model.rellenarTabla(listaOrdenada);
	}

	@Override
	public void profesorDisponibles(ArrayList<ProfesorTeorico> profesoresT,
			ArrayList<ProfesorPractico> profesoresP) {
	}
}
