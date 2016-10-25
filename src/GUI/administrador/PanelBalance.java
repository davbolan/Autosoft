package GUI.administrador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import modelo.dominio.gestion.Contabilidad;
import utilidades.Utilidades;
import GUI.AbstractPanel;
import GUI.Imagen;
import GUI.observables.ContabilidadObserver;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz balance de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class PanelBalance extends AbstractPanel implements ContabilidadObserver{
	
	private JTextField fechaInicioText;
	private JTextField fechaFinalText;
	private JButton buscarButton;
	private JTable ingresosTable;
	private MiTableModel ingresosTableModel;
	private JTable gastosTable;
	private MiTableModel gastosTableModel;	
	private JLabel subIngresosText;
	private JLabel subGastosText;
	private JLabel totalText;

	/**
	 * Contructor de la interfaz balance
	 * @param controlador
	 */
	public PanelBalance(Controlador controlador) {
		super(controlador);
		
		initPanelBalance();
		
		this.setVisible(true);
		fixButtons();
		
	}
	
	/**
	 * Procesa los distintos eventos de los botones de la interfaz baja/modificar personal
	 */
	private void fixButtons() {
		buscarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String fechaIni = "01/01/1000";
				String fechaFin = "31/12/9999";
				
				if(!fechaInicioText.getText().trim().isEmpty())
					fechaIni = fechaInicioText.getText().trim();
								
				if(!fechaFinalText.getText().trim().isEmpty())
					fechaFin =  fechaFinalText.getText().trim();
							
				if(Utilidades.esUnaFecha(fechaIni) && Utilidades.esUnaFecha(fechaFin)) 			
					controlador.mostrarBalance(fechaIni, fechaFin);						
				else
					showError("Error", "Las fechas tiene un formato incorrecto. (DD/MM/AAAA)");
			}	
		}
	);
	}


	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz balance
	 * 
	 */
	public void initPanelBalance() {
		
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setLayout(null);
		Font defaultFont = new Font("Calibri", Font.PLAIN, 14);
		
		Imagen logo = new Imagen("logoPaneles.jpg");
		logo.setBounds(10, 10, 130, 120);
		
		Imagen fondo = new Imagen("fondoAdmin.jpg");
		fondo.setBounds(0, 0, 640, 542);
		
		JLabel fechaInicioLabel = new JLabel("Fecha inicio:");		
		fechaInicioLabel.setFont(defaultFont);
		fechaInicioLabel.setForeground(Color.WHITE);
		fechaInicioLabel.setBounds(61,44,70,20);
		fechaInicioText = new JTextField();
		fechaInicioText.setBounds(141,44,87,20);
		
		JLabel fechaFinalLabel = new JLabel("Fecha final:");		
		fechaFinalLabel.setFont(defaultFont);
		fechaFinalLabel.setForeground(Color.WHITE);
		fechaFinalLabel.setBounds(283,44,70,20);
		fechaFinalText = new JTextField();
		fechaFinalText.setBounds(352,44,94,20);
		
		buscarButton = new JButton("Buscar");
		buscarButton.setBounds(505, 44, 80, 20);
		
		JLabel ingresosLabel = new JLabel("Ingresos");		
		ingresosLabel.setFont(defaultFont);
		ingresosLabel.setForeground(Color.WHITE);
		ingresosLabel.setBounds(127,82,56,20);
		
		JLabel gastosLabel = new JLabel("Gastos");		
		gastosLabel.setFont(defaultFont);
		gastosLabel.setForeground(Color.WHITE);
		gastosLabel.setBounds(455,82,48,20);
		
		
		ingresosTableModel = new MiTableModel();
		JPanel panelTablaIngresos = new JPanel();
		panelTablaIngresos.setLayout(null);
		panelTablaIngresos.setBounds(25, 110, 280, 250);
		ingresosTable = new JTable(ingresosTableModel);
		ingresosTable.getColumnModel().getColumn(0).setMinWidth(40);
		ingresosTable.getColumnModel().getColumn(1).setPreferredWidth(135);
		ingresosTable.getColumnModel().getColumn(3).setPreferredWidth(30);
		ingresosTable.setFont(defaultFont);
		JScrollPane scrollIngresos = new JScrollPane(ingresosTable);
		ingresosTable.setFillsViewportHeight(true);
		scrollIngresos.setBounds(0, 0, 280, 250);
		panelTablaIngresos.add(scrollIngresos);	
		
		gastosTableModel = new MiTableModel();
		JPanel panelTablaGastos = new JPanel();
		panelTablaGastos.setLayout(null);
		panelTablaGastos.setBounds(330, 110, 280, 250);
		gastosTable = new JTable(gastosTableModel);	
		gastosTable.getColumnModel().getColumn(0).setMinWidth(40);
		gastosTable.getColumnModel().getColumn(1).setPreferredWidth(135);
		gastosTable.getColumnModel().getColumn(3).setPreferredWidth(30);		
		gastosTable.setFont(defaultFont);
	
		JScrollPane scrollGastos = new JScrollPane(gastosTable);
		gastosTable.setFillsViewportHeight(true);
		scrollGastos.setBounds(0, 0, 280, 250);
		panelTablaGastos.add(scrollGastos);	
		
		JLabel subIngresosLabel = new JLabel("Subtotal:");		
		subIngresosLabel.setFont(defaultFont);
		subIngresosLabel.setForeground(Color.WHITE);
		subIngresosLabel.setBounds(35,358,56,20);
		subIngresosText = new JLabel("0.0 €");
		subIngresosText.setFont(defaultFont);
		subIngresosText.setForeground(Color.WHITE);
		subIngresosText.setBounds(100,358,205,20);

		
		JLabel subGastosLabel = new JLabel("Subtotal:");		
		subGastosLabel.setFont(defaultFont);
		subGastosLabel.setForeground(Color.WHITE);
		subGastosLabel.setBounds(340,358,56,20);
		subGastosText = new JLabel("0.0 €");
		subGastosText.setFont(defaultFont);
		subGastosText.setForeground(Color.WHITE);
		subGastosText.setBounds(405,358,205,20);
		
		JLabel totalLabel = new JLabel("Total:");		
		totalLabel.setFont(defaultFont);
		totalLabel.setForeground(Color.WHITE);
		totalLabel.setBounds(275,389,37,20);
		totalText = new JLabel("0.0 €");
		totalText.setFont(defaultFont);
		totalText.setForeground(Color.WHITE);
		totalText.setBounds(316,389,114,20);
		
		JLabel titulo = new JLabel("Mostrar los listados de gastos e ingresos");
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(3,3, 300, 15);
		
		this.add(titulo);
		this.add(fechaInicioLabel);
		this.add(fechaInicioText);
		this.add(fechaFinalLabel);
		this.add(fechaFinalText);
		this.add(buscarButton);
		this.add(ingresosLabel);
		this.add(gastosLabel);
		this.add(panelTablaIngresos);
		this.add(panelTablaGastos);
		this.add(subIngresosLabel);
		this.add(subGastosLabel);
		this.add(subIngresosText);
		this.add(subGastosText);
		this.add(totalLabel);
		this.add(totalText);
		this.add(fondo);
		
	}

	@Override
	public void mensajeInfo(String type, String mensaje) {
		JOptionPane.showOptionDialog(null, mensaje, type, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
	}

	@Override
	public void showError(String error, String mensaje) {
		JOptionPane.showOptionDialog(null, mensaje, error, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);	
		
	}

	@Override
	public void ocultarPanel() {
		clearFields();
		controlador.removeContabilidadObserver(this);
		
	}

	private void clearFields() {
		fechaInicioText.setText("");
		fechaFinalText.setText("");
		ingresosTableModel.clearTable();
		gastosTableModel.clearTable();
	}

	@Override
	public void mostrarPanel() {
		controlador.addContabilidadObserver(this);		
	}

	@Override
	public void mostrarBalance(List<Contabilidad> ingresos,List<Contabilidad> gastos, double total, double subtotalIngresos, double subtotalGastos) 
	{
		gastosTableModel.rellenarTabla(gastos);
		ingresosTableModel.rellenarTabla(ingresos);
		subGastosText.setText(subtotalGastos + " €");
		subIngresosText.setText(subtotalIngresos + " €");
		totalText.setText(total + " €");
	}
	
	
	/**
	 * Clase que crea la tabla del balance
	 * 
	 */
	public class MiTableModel extends AbstractTableModel {
		private List<Contabilidad> elems;
		private String[] columnNames = {"Fecha", "Concepto", "€uros", "Id"};

		public MiTableModel() {
			super();
			elems = new ArrayList<Contabilidad>();
		}

		@Override
		public int getRowCount() {
			return elems.size();		
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;		
		}

		@Override
		public Object getValueAt(int row, int col) {
			Object value = "-";
			
			if(!elems.isEmpty()){
				Contabilidad ejercicio = elems.get(row);
					
				switch(col){
					case 0: 
						value = ejercicio.getFecha(); break;
					case 1: 
						value = ejercicio.getConcepto(); break;
					case 2: 
						value = ejercicio.getCantidad(); break;
					case 3: 
						value = ejercicio.getId(); break;

					default:
						value = "-";
				}
			}
			return value;		
		}
		
		/**
		 * Devuelve el contenido de una columna
		 * @param columnIndex
		 * @return string
		 */
		public String getColumnName(int columnIndex) {
			return columnNames[columnIndex];
		}


		/**
		 * Rellena la tabla con el DAO de contabilidad
		 * @param lista
		 */
		public void rellenarTabla(List<Contabilidad> lista) {
			elems = lista;
				for(int row = 0; row < elems.size(); row++){
					setValueAt(elems.get(row).getFecha()	, row, 0);
					setValueAt(elems.get(row).getConcepto() , row, 1);
					setValueAt(elems.get(row).getCantidad()	, row, 2);
					setValueAt(elems.get(row).getId()		, row, 3);
				}		
			
			fireTableDataChanged();
		}

		public void clearTable() {
			elems.clear();
			fireTableDataChanged();
		}
	}
}
