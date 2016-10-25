package GUI.administrador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import modelo.dominio.gestion.Contabilidad;
import utilidades.Utilidades;
import GUI.AbstractPanel;
import GUI.Imagen;
import GUI.observables.ContabilidadObserver;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz aniadir gasto/ingreso de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class PanelNuevoEjercicio extends AbstractPanel implements ContabilidadObserver{
	
	private JButton acceptButton;
	private JButton backButton;

	private ButtonGroup buttonGroup;

	private JRadioButton radioGasto;
	private JRadioButton radioIngreso;

	private JLabel todayLabel;
	private JTextField cantidadText;
	private JTextField conceptoText;


	/**
	 * Constructora por defecto
	 * @param controlador
	 */
	public PanelNuevoEjercicio(Controlador controlador) {
		super(controlador);
		
		initPanelAltaUsuario();
		
		this.setVisible(true);
		fixButtons();
	}


	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz aniadir gasto/ingreso
	 * 
	 */
	private void initPanelAltaUsuario() {
		this.setPreferredSize(new Dimension(640, 480));
		Font defaultFont = new Font("Calibri", Font.PLAIN, 17);
		
		Imagen logo = new Imagen("logoPaneles.jpg");
		logo.setBounds(10, 10, 130, 120);
		
		Imagen fondo = new Imagen("fondoAdmin.jpg");
		fondo.setBounds(0, 0, 640, 480);
		
		buttonGroup = new ButtonGroup();
		radioGasto = new JRadioButton("Gasto");
		radioGasto.setFont(defaultFont);
		radioGasto.setForeground(Color.RED);
		radioGasto.setBounds(280,70,70,20);
		radioGasto.setContentAreaFilled(false);
		
		radioIngreso = new JRadioButton("Ingreso");
		radioIngreso.setFont(defaultFont);
		radioIngreso.setForeground(Color.GREEN);
		radioIngreso.setBounds(380,70,80,20);
		radioIngreso.setContentAreaFilled(false);
		
		buttonGroup.add(radioGasto);
		buttonGroup.add(radioIngreso);
		
		JLabel cantidadLabel = new JLabel("Cantidad");
		cantidadLabel.setFont(defaultFont);
		cantidadLabel.setForeground(Color.WHITE);
		cantidadLabel.setBounds(215,160,100,20);
		cantidadText = new JTextField();
		cantidadText.setBounds(300,160,100,20);
		
		JLabel euros = new JLabel("€");
		euros.setBounds(410,160,100,20);
		euros.setForeground(Color.WHITE);
		
		JLabel conceptoLabel = new JLabel("Concepto");
		conceptoLabel.setFont(defaultFont);
		conceptoLabel.setForeground(Color.WHITE);
		conceptoLabel.setBounds(210,220,100,20);
		conceptoText = new JTextField();
		conceptoText.setBounds(300,220,200,20);
		
		
		JLabel dateLabel = new JLabel("Fecha");
		dateLabel.setFont(defaultFont);
		dateLabel.setForeground(Color.WHITE);
		dateLabel.setBounds(235,280,150,20);
		todayLabel = new JLabel(Utilidades.fechaHoy());
		todayLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		todayLabel.setForeground(Color.WHITE);
		todayLabel.setBounds(300,280,100,20);
		
		acceptButton = new JButton("Aceptar");
		acceptButton.setBounds(340, 350, 130, 30);
		backButton = new JButton("Limpiar campos");
		backButton.setBounds(163, 350, 130, 30);
		
		JLabel titulo = new JLabel("Añadir un gasto o un ingreso");
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(3,3, 200, 15);
		this.add(titulo);
		
		this.setLayout(null);
		this.add(logo);
		this.add(titulo);
		this.add(radioIngreso);
		this.add(radioGasto);
		this.add(cantidadLabel);
		this.add(cantidadText);
		this.add(euros);
		this.add(conceptoLabel);
		this.add(conceptoText);
		this.add(dateLabel);
		this.add(todayLabel);
		this.add(acceptButton);	
		this.add(backButton);
		this.add(fondo);
		this.updateUI();
	}
	
	/**
	 * Procesa los distintos eventos de los botones de la interfaz aniadir gasto/ingreso
	 */
	private void fixButtons() {
		fixAcceptButtonListener();
		fixClearFieldsButtonListener();
	}


	/**
	 * Procesa el evento del boton "aceptar" de la interfaz aniadir gasto/ingreso
	 */
	private void fixAcceptButtonListener() {
		acceptButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {				 
				if(!camposVacios()){
					String cant = cantidadText.getText().trim();
					if(Utilidades.isDouble(cant)){
						String tipoEjercicio = "Ingreso";
						String accion = "ingresar";
						double cantidad = Double.parseDouble(cant);
						if(radioGasto.isSelected()){
							accion = "gastar";
							tipoEjercicio = "Gasto";
						}
					
					 	Contabilidad ejercicio = new Contabilidad(
					 			tipoEjercicio,
								todayLabel.getText(),
								cantidad,
								conceptoText.getText().trim()
								);
					 	
						
					
						if(confirmar(accion, cant) == YES)
							controlador.nuevoEjercicio(ejercicio);
					}
					else
						showError("Error", "Por favor, seleccione una cantidad positiva.");
								
				}
				else
					showError("Error", "Por favor, revise los campos, no deben estar vacíos.");
			}

		}
	);
	}

	/**
	 * Ventana de confirmación de cambios SI/NO
	 * @param tipo
	 * @param cant
	 * @return
	 */
	private int confirmar(String tipo, String cant){
		return JOptionPane.showConfirmDialog(null, 
				"¿Estás seguro de " + tipo + " una cantidad de " + cant + "€?", 
				"Nuevo usuario", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE, 
				null);
	}
	
	/**
	 * Comrpueba que los campos esten rellenos
	 * @return true si se ha selecciona gato o ingreso y los campos cantidad y concepto esta rellenos.
	 * False en caso contrario. 
	 */
	private boolean camposVacios() {
		return (!radioIngreso.isSelected() && !radioGasto.isSelected()) 
				|| cantidadText.getText().trim().isEmpty() 
				|| conceptoText.getText().trim().isEmpty();
	}

	/**
	 * Procesa el evento del boton "limpiar campos" de la interfaz aniadir gasto/ingreso
	 */
	private void fixClearFieldsButtonListener() {
		backButton.addActionListener(new ActionListener() {
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
		radioGasto.setSelected(false);
		radioIngreso.setSelected(false);
		cantidadText.setText("");
		conceptoText.setText("");
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
		controlador.removeContabilidadObserver(this);
	}

	@Override
	public void mostrarPanel() {
		controlador.addContabilidadObserver(this);
	}

	@Override
	public void mostrarBalance(List<Contabilidad> ingresos,
			List<Contabilidad> gastos, double total,
			double subtotalIngresos, double subtotalGastos) {
		
	}
	
}
