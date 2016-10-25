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
 * Clase que implementa la interfaz de nuevo vehiculo de la aplicación 
 * @author David Bolanios, Frank Julca
 *
 */
@SuppressWarnings("serial")
public class VehiculoNuevo extends AbstractPanel implements VehiculosObserver{
	private JTextField matriculaText;
	private JButton aceptarButton;
	private JButton backButton;
	private JTextField anioText;
	private JTextField marcaText;
	private JTextField modeloText;
	private JTextField itvText;
	private JComboBox<String> tipoBox;
	private JComboBox<String> estadoBox;
	
	/**
	 * Contructor de la interfaz alta usuario
	 * @param controlador
	 */
	public VehiculoNuevo(Controlador controlador) {
		super(controlador);
		
		initVehiculoNuevo();
		
		this.setVisible(true);
		fixButtons();
	}
	
	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz nuevo vehiculo
	 * 
	 */
	private void initVehiculoNuevo() {
		this.setPreferredSize(new Dimension(1000,1000));
		Font defaultFont = new Font("Calibri", Font.PLAIN, 14);
		
		Imagen logo = new Imagen("logoPaneles.jpg");
		logo.setBounds(10, 10, 130, 120);
		
		Imagen fondo = new Imagen("fondoSecretaria.jpg");
		fondo.setBounds(0, 0, 640, 480);
		
		JLabel matriculaLabel = new JLabel("Matrícula");		
		matriculaLabel.setFont(defaultFont);
		matriculaLabel.setBounds(189,90,72,20);
		matriculaText = new JTextField();
		matriculaText.setBounds(276,90,100,20);

		JLabel tipoLabel = new JLabel("Tipo");
		tipoLabel.setFont(defaultFont);
		tipoLabel.setBounds(219,130,47,20);
		tipoBox = new JComboBox<String>();
		tipoBox.addItem("Elige tipo de vehículo...");
		tipoBox.addItem("Auto");
		tipoBox.addItem("Moto");
		tipoBox.addItem("Camion");
		tipoBox.setBounds(276,130,168,20);
		
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
		
		aceptarButton = new JButton("Aceptar");
		aceptarButton.setBounds(334, 378, 80, 30);
		
		backButton = new JButton("Limpiar campos");
		backButton.setBounds(136, 378, 130, 30);
		
		JLabel titulo = new JLabel("Añadir un vehículo");
		titulo.setForeground(Color.BLACK);
		titulo.setBounds(3,3, 200, 15);
		
		this.setLayout(null);
		this.add(titulo);
		this.add(logo);
		this.add(matriculaLabel);
		this.add(matriculaText);
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
		this.add(aceptarButton);
		this.add(backButton);
		this.add(fondo);
		this.updateUI();
		
		matriculaText.setText("M9347YV");
		anioText.setText("2013");
		marcaText.setText("Seat");
		modeloText.setText("Toledo");
		itvText.setText("30/8/2014");
		tipoBox.setSelectedIndex(1);
		estadoBox.setSelectedIndex(1);

	}	
	
	/**
	 * Procesa los distintos eventos de los botones de la interfaz nuevo vehiculo
	 */
	private void fixButtons() {
		fixAcceptButtonListener();
		fixClearFieldsButtonListener();
	}
	
	/**
	 * Procesa el evento del boton "limpiar campos" de la interfaz nuevo vehiculo
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
	 * Procesa el evento del boton "aceptar" de la interfaz nuevo vehiculo
	 */
	private void fixAcceptButtonListener() {
		aceptarButton.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Vehiculo vehiculo = null;
					boolean correct = true;
					String messageError = "";
					int anioActual = Calendar.getInstance().get(Calendar.YEAR);
					
					if(!camposVacios() && camposNumCorrectos()){														
						int auxAnio = Integer.parseInt(anioText.getText());
						if(1950 <= auxAnio  && auxAnio <= anioActual){
							vehiculo = new Vehiculo(
										matriculaText.getText().trim(), 
										auxAnio, 
										marcaText.getText().trim(), 
										modeloText.getText().trim(),
										((String)estadoBox.getSelectedItem()).toLowerCase(),
										itvText.getText().trim(),
										((String)tipoBox.getSelectedItem()).toLowerCase(),
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
						messageError = "Por favor, revise los campos, no deben estar vacíos y deben ser números donde corresponda.";
					}
					
					if(correct){
						if(confirmar() == YES)
							controlador.nuevoVehiculo(vehiculo);
						}
					else
						showError("Error", messageError);
				}
			
			}
		);
	}

	/**
	 * Ventana de confirmación de cambios SI/NO
	 * @return entero
	 */
	private int confirmar(){
		return JOptionPane.showConfirmDialog(null, 
				"¿Estás seguro de añadir un " + marcaText.getText().trim() + " "+ modeloText.getText().trim() 
				+" con matrícula " + matriculaText.getText().trim() + "?", 
				"Alta personal", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE, 
				null);
	}
	
	
	/**
	 * Verifica que todos los campos estén rellenados
	 * @return boolean
	 */
	private boolean camposVacios() {
		return (matriculaText.getText().trim().isEmpty()
				|| (tipoBox.getSelectedIndex() == 0)
				|| marcaText.getText().trim().isEmpty()
				|| modeloText.getText().trim().isEmpty()
				|| itvText.getText().trim().isEmpty() 
				|| anioText.getText().trim().isEmpty());
	}
	
	/**
	 * Verifica que el anio sea un numero
	 */
	private boolean camposNumCorrectos() {
		 return Utilidades.isPositiveNumber(anioText.getText().trim());
	}
	
	/**
	 * Limpia los campos de los distintos JtextField
	 */
	private void clearFields() {
		matriculaText.setText("");
		tipoBox.setSelectedIndex(0);
		marcaText.setText("");
		modeloText.setText("");
		itvText.setText("");
		anioText.setText("");
	}

	
	@Override
	public void ocultarPanel() {
		controlador.removeVehiculosObserver(this);
	}
	
	@Override
	public void mostrarPanel() {
		controlador.addVehiculosObserver(this);
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
	public void informacionVehiculos(ArrayList<Vehiculo> vehiculos) {
	}
	
	@Override
	public void matriculaVehiculos(ArrayList<Vehiculo> matriculas) {
	}

}

