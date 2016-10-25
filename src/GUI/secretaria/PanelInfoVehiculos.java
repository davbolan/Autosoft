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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import modelo.dominio.vehiculo.Vehiculo;
import GUI.AbstractPanel;
import GUI.GUI;
import GUI.observables.VehiculosObserver;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz información de Vehiculo de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class PanelInfoVehiculos extends AbstractPanel implements VehiculosObserver{
	
	private MiTableModel model;
	private JTable table;

	/**
	 * Constructora por defecto
	 * @param controlador
	 */
	public PanelInfoVehiculos(Controlador controlador){
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
		//panelTabla.setBounds(5, 30, 600, 450);
		JLabel titulo = new JLabel("Ver la información de los vehículos");
		titulo.setBounds(3, 0, 250, 20);
		titulo.setForeground(Color.BLACK);
		titulo.setFont(defaultFont);
		
		this.add(titulo);
		this.add(panelTabla);
	}

	/**
	 * Clase que crea la tabla de la información de Vehiculo 
	 * 
	 */
	public class MiTableModel extends AbstractTableModel {
		private ArrayList<Vehiculo> elems;
		private String[] columnNames = { "Tipo", "Marca", "Modelo",  "Matrícula", "ITV", "Estado"};

		public MiTableModel() {
			super();
			elems = new ArrayList<Vehiculo>();
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
				Vehiculo vehiculo = elems.get(row);
					
				switch(col){
					case 0: 
						value = vehiculo.getTipo(); break;
					case 1: 
						value = vehiculo.getMarca(); break;
					case 2: 
						value = vehiculo.getModelo(); break;
					case 3: 
						value = vehiculo.getMatricula(); break;
					case 4: 
						value = vehiculo.getITV(); break;
					case 5: 
						value = vehiculo.getEstado(); break;
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
		 * Rellena la tabla a partir del DAO de Vehiculo
		 * @param listaOrdenada
		 */
		public void rellenarTabla(ArrayList<Vehiculo> listaOrdenada) {
			elems = listaOrdenada;
			fireTableDataChanged();
		}
	}

	@Override
	public void ocultarPanel() {
		controlador.removeVehiculosObserver(this);
		
	}

	@Override
	public void mostrarPanel() {
		controlador.addVehiculosObserver(this);
		controlador.informacionVehiculos();
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
	public void informacionVehiculos(ArrayList<Vehiculo> vehiculos) {
		model.rellenarTabla(vehiculos);
	}

	@Override
	public void matriculaVehiculos(ArrayList<Vehiculo> matriculas) {
	}
}
