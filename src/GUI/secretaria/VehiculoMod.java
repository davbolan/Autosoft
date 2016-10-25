package GUI.secretaria;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.dominio.vehiculo.Vehiculo;
import utilidades.Utilidades;
import GUI.AbstractPanel;
import GUI.Imagen;
import GUI.observables.VehiculosObserver;
import controlador.Controlador;

/**
 * Clase que implementa la interfaz de baja/modificar vehiculo de la aplicación 
 * @author David Bolanios
 *
 */
@SuppressWarnings("serial")
public class VehiculoMod extends AbstractPanel implements VehiculosObserver{

	private JLabel miMatriculaLabel;
	private JTextField matriculaSearch;
	private JButton buscarButton;
	private JButton deleteButton;
	private JButton modButton;
	private JButton clearButton;
	private JTextField anioText;
	private JTextField marcaText;
	private JTextField modeloText;
	private JTextField itvText;
	private JComboBox<String> tipoBox;
	private JComboBox<String> estadoBox;
	
	private String matriculaActual = "";
	private Vehiculo vehiculoSelected;
	
	/**
	 * Contructor de la interfaz baja/modificar vehiculo
	 * @param controlador
	 */
	public VehiculoMod(Controlador controlador) {
		super(controlador);
		
		initPanelBajaModVehiculo();
		
		this.setVisible(true);
		fixButtons();
	}
	
	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz baja/modificar vehiculo
	 * 
	 */
	private void initPanelBajaModVehiculo() {
		this.setPreferredSize(new Dimension(1000,1000));
		Font defaultFont = new Font("Calibri", Font.PLAIN, 14);
		
		Imagen logo = new Imagen("logoPaneles.jpg");
		logo.setBounds(10, 10, 130, 120);
		Imagen fondo = new Imagen("fondoSecretaria.jpg");
		fondo.setBounds(0, 0, 640, 480);
		
		JLabel buscarMatricula = new JLabel("Matricula");		
		buscarMatricula.setFont(defaultFont);
		buscarMatricula.setBounds(189,54,72,20);
		matriculaSearch = new JTextField();
		matriculaSearch.setBounds(276,54,100,20);
		
		JLabel matriculaLabel = new JLabel("Matricula");		
		matriculaLabel.setFont(defaultFont);
		matriculaLabel.setBounds(189,90,72,20);
		miMatriculaLabel = new JLabel("-");
		miMatriculaLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		miMatriculaLabel.setBounds(276,90,85,20);

		JLabel tipoLabel = new JLabel("Tipo");
		tipoLabel.setFont(defaultFont);
		tipoLabel.setBounds(219,130,47,20);
		tipoBox = new JComboBox<String>();
		tipoBox.addItem("Seleccione un vehículo...");
		tipoBox.addItem("Auto");
		tipoBox.addItem("Moto");
		tipoBox.addItem("Camion");
		tipoBox.setBounds(276,130,123,20);
		
		JLabel anioLabel = new JLabel("Año");
		anioLabel.setFont(defaultFont);
		anioLabel.setBounds(220,170,37,20);
		anioText = new JTextField();
		anioText.setBounds(276,170,59,20);
		
		JLabel marcaLabel = new JLabel("Marca");		
		marcaLabel.setFont(defaultFont);
		marcaLabel.setBounds(210,210,47,20);
		marcaText = new JTextField();
		marcaText.setBounds(276,210,100,20);
		
		JLabel modeloLabel = new JLabel("Modelo");		
		modeloLabel.setFont(defaultFont);
		modeloLabel.setBounds(204,250,53,20);
		modeloText = new JTextField();
		modeloText.setBounds(276,250,85,20);
		
		JLabel estadoLabel = new JLabel("Estado");
		estadoLabel.setFont(defaultFont);
		estadoLabel.setBounds(210,290,47,20);
		estadoBox = new JComboBox<String>();
		estadoBox.addItem("Activo");
		estadoBox.addItem("Inactivo");
		estadoBox.setBounds(277,290,117,20);
		
		JLabel itvLabel = new JLabel("Fecha ITV");		
		itvLabel.setFont(defaultFont);
		itvLabel.setBounds(194,330,72,20);
		itvText = new JTextField();
		itvText.setBounds(277,330,117,20);
		
		modButton = new JButton("Modificar");
		modButton.setBounds(418, 378, 91, 30);
		
		buscarButton = new JButton("Buscar");
		buscarButton.setBounds(399, 52, 80, 25);
		
		deleteButton = new JButton("Eliminar");
		deleteButton.setBounds(303, 378, 80, 30);
		
		clearButton = new JButton("Limpiar campos");
		clearButton.setBounds(136, 378, 130, 30);
		
		JLabel titulo = new JLabel("Modificar o eliminar un vehículo");
		titulo.setForeground(Color.BLACK);
		titulo.setBounds(3,3, 200, 15);
		
		this.setLayout(null);
		this.add(titulo);
		this.add(logo);
		this.add(buscarMatricula);
		this.add(matriculaSearch);
		this.add(matriculaLabel);
		this.add(miMatriculaLabel);
		this.add(tipoLabel);
		this.add(tipoBox);
		this.add(anioLabel);
		this.add(anioText);
		this.add(marcaLabel);
		this.add(marcaText);
		this.add(modeloLabel);
		this.add(modeloText);
		this.add(estadoLabel);
		this.add(estadoBox);
		this.add(itvLabel);
		this.add(itvText);
		this.add(modButton);
		this.add(buscarButton);
		this.add(deleteButton);
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
		marcaText.setEnabled(state);
		modeloText.setEnabled(state);
		itvText.setEnabled(state);
		estadoBox.setEnabled(state);
		tipoBox.setEnabled(state);
		anioText.setEnabled(state);
	}
	
	/**
	 * Procesa los distintos eventos de los botones de la interfaz baja/modificar vehiculo
	 */
	private void fixButtons() {
		fixModButtonListener();
		fixBuscarButtonListener();
		fixDeleteButtonListener();
		fixClearFieldsButtonListener();
	}
	
	private void fixDeleteButtonListener() {
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String matricula = miMatriculaLabel.getText().trim();
					if(!matricula.isEmpty()){
						if(confirmar("Dar de baja", "dar de baja") == YES)
							controlador.bajaVehiculo(matricula);
					}
					else
						showError("Error", "El campo DNI no puede estar vacío.");
				}
		});				
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
				"¿Estás seguro de " + operacion + " el vehículo con matrícula " + miMatriculaLabel.getText().trim() + "?", 
				tittle, 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE, 
				null);
	}

	/**
	 * Procesa el evento del boton "buscar" de la interfaz baja/modificar vehiculo
	 */
	private void fixBuscarButtonListener() {
		buscarButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String matricula = matriculaSearch.getText().trim();
			
			if(matricula.isEmpty())
				showError("Error", "Por favor, rellene el campo de la matrícula");
			else{
				matriculaActual = matricula;
				Vehiculo vehiculo = controlador.buscarVehiculo(matriculaActual);
				if(vehiculo == null)
					showError("Error", "No se encuentra el vehículo con esa matrícula " + matriculaSearch.getText());
				else{
					vehiculoSelected = vehiculo;
					changeFieldsState(true);
					miMatriculaLabel.setText(matriculaActual);		
					anioText.setText(vehiculo.getAnio()+"");
					marcaText.setText(vehiculo.getMarca());
					modeloText.setText(vehiculo.getModelo());
					itvText.setText(vehiculo.getITV());
					tipoBox.setSelectedItem(vehiculo.getTipo());
					estadoBox.setSelectedItem(vehiculo.getEstado());
				}
			}
		}
	});
		
	}
	
	/**
	 * Procesa el evento del boton "modificar" de la interfaz baja/modificar vehiculo
	 */
	private void fixModButtonListener() {
		modButton.addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Vehiculo vehiculo = null;
						boolean correct = true;
						String messageError = "";
						
						if(!camposVacios() && camposNumCorrectos()){										
							if(tipoBox.getSelectedIndex() != 0){
								int auxAnio = Integer.parseInt(anioText.getText());
								int anioActual = Calendar.getInstance().get(Calendar.YEAR);
								if(1950 <= auxAnio  && auxAnio <= anioActual){
									vehiculo = new Vehiculo(
									vehiculoSelected.getMatricula(), 
									auxAnio, 
									marcaText.getText().trim(), 
									modeloText.getText().trim(),
									((String)estadoBox.getSelectedItem()),
									itvText.getText().trim(),
									((String)tipoBox.getSelectedItem()),
									correct
									);
								}
								else{
									correct = false;
									messageError = "La antigüedad del vehículo debe estar comprendida entre 1950 y " + anioActual + ".";
								}
							}
							else{
								correct = false;
								messageError = "Por favor, seleccione un tipo de vehículo.";
							}							
						}	
						else{
							correct = false;
							messageError = "Por favor, revise los campos, no deben estar vacíos y deben ser números donde corresponda.";
						}
						
						if(correct){
							if(confirmar("Modificar", "modificar") == YES)
								controlador.modificarVehiculo(vehiculo);
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
		return(matriculaSearch.getText().trim().isEmpty() 
				|| (tipoBox.getSelectedIndex() == 0)
				|| modeloText.getText().trim().isEmpty() 
				|| itvText.getText().trim().isEmpty() 
				|| anioText.getText().trim().isEmpty() 
				|| marcaText.getText().trim().isEmpty());
	}
	
	/**
	 * Verifica que el campo anio sea un numero valido
	 */
	private boolean camposNumCorrectos() {
		 return Utilidades.isPositiveNumber(anioText.getText().trim());
	}
	
	/**
	 * Procesa el evento del boton "limpiar campos" de la interfaz baja/modificar vehiculo
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
		miMatriculaLabel.setText("-");
		matriculaSearch.setText("");
		marcaText.setText("");
		modeloText.setText("");
		anioText.setText("");
		itvText.setText("");
		vehiculoSelected = null;
		changeFieldsState(false);
	}
	
	public void ocultarPanel() {
		controlador.removeVehiculosObserver(this);
	}
	
	public void mostrarPanel() {
		controlador.addVehiculosObserver(this);
	}

	@Override
	public void mensajeInfo(String type, String message) {
		JOptionPane.showOptionDialog(null, message, type, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);	
	}

	@Override
	public void showError(String error, String message) {
		JOptionPane.showOptionDialog(null, message, error, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);	
	}

	@Override
	public void informacionVehiculos(ArrayList<Vehiculo> vehiculos) {	
	}

	@Override
	public void matriculaVehiculos(ArrayList<Vehiculo> matriculas) {
	}
}
