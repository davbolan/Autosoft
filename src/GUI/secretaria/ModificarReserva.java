package GUI.secretaria;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.dominio.gestion.Reserva;
import modelo.dominio.personas.Persona;
import modelo.dominio.personas.Profesor;
import utilidades.Utilidades;
import GUI.GUI;
import GUI.Imagen;
import controlador.Controlador;

/**
 * Clase que implementa la ventana de baja/modificar reserva de la aplicación 
 * @author David Bolanios, Frank Julca
 *
 */
@SuppressWarnings("serial")
public class ModificarReserva extends JFrame{
	
	protected static final int YES = JOptionPane.YES_OPTION;
	private Controlador controlador;
	private JLabel idReservaText;
	private JLabel profesorText;
	private JLabel alumnoText;
	private JTextField fechaText;
	private JTextField horaText;
	private JButton acceptButton;
	private JButton cancelButton;
	private Reserva reserva;
	
	/**
	 * Contructor de la interfaz baja/modificar reserva
	 * @param controlador
	 */
	public ModificarReserva(Controlador controlador) {
		super("Modificar reserva");
		this.controlador = controlador;

		initModificarFrame(controlador);
		
		fixButtons();
	}

	/**
	 * Inicializa los atributos y disposición de los elementos de la interfaz baja/modificar reserva
	 * 
	 */
	private void initModificarFrame(Controlador controlador) {
		Font defaultFont = new Font("Calibri", Font.PLAIN, 14);
		
		Imagen fondo = new Imagen("fondoSecretaria.jpg");
		fondo.setBounds(0, 0, 480, 320);
		
		JLabel idLabel = new JLabel("ID Reserva");
		idLabel.setFont(defaultFont);
		idLabel.setForeground(Color.BLACK);
		idLabel.setBounds(126,55,76,20);
		idReservaText = new JLabel();
		idReservaText.setBounds(226,55,230,20);
		
		JLabel nombreLabel = new JLabel("Alumno");		
		nombreLabel.setFont(defaultFont);
		nombreLabel.setForeground(Color.BLACK);
		nombreLabel.setBounds(140,95,60,20);
		alumnoText = new JLabel();
		alumnoText.setBounds(226,95,230,20);
		
		JLabel profesorLabel = new JLabel("Profesor");		
		profesorLabel.setFont(defaultFont);
		profesorLabel.setForeground(Color.BLACK);
		profesorLabel.setBounds(140,135,60,20);
		profesorText = new JLabel();
		profesorText.setBounds(226,135,230,20);
		
		JLabel fechaLabel = new JLabel("Fecha");
		fechaLabel.setFont(defaultFont);
		fechaLabel.setForeground(Color.BLACK);
		fechaLabel.setBounds(150,175,50,20);
		fechaText = new JTextField();
		fechaText.setBounds(226,175,70,20);
		
		JLabel horaLabel = new JLabel("Hora");
		horaLabel.setFont(defaultFont);
		horaLabel.setForeground(Color.BLACK);
		horaLabel.setBounds(157,215,28,20);
		horaText = new JTextField();
		horaText.setBounds(226,215,25,20);
		
		JTextField ceros = new JTextField();
		ceros.setText(":00");
		ceros.setEditable(false);
		ceros.setBounds(250,215,22,20);
		ceros.setBackground(Color.WHITE);

		acceptButton = new JButton("Aceptar");
		acceptButton.setBounds(126, 253, 90, 30);
		cancelButton = new JButton("Cancelar");
		cancelButton.setBounds(276, 253, 90, 30);
		
		JLabel titulo = new JLabel("Modificar una reserva");
		titulo.setForeground(Color.BLACK);
		titulo.setBounds(3,3, 250, 15);
		getContentPane().add(titulo);
		
		getContentPane().setLayout(null);
		getContentPane().add(idLabel);
		getContentPane().add(idReservaText);
		getContentPane().add(profesorLabel);
		getContentPane().add(profesorText);
		getContentPane().add(nombreLabel);
		getContentPane().add(alumnoText);
		getContentPane().add(fechaLabel);
		getContentPane().add(fechaText);
		getContentPane().add(horaLabel);
		getContentPane().add(horaText);
		getContentPane().add(acceptButton);	
		getContentPane().add(cancelButton);
		getContentPane().add(ceros);
		getContentPane().add(fondo);
		
		this.setIconImage(createImage("logo.jpg").getImage());
		this.setSize(480, 344);
		this.setResizable(false);
		setLocationRelativeTo(null);
	}

	
	/**
	 * Procesa los distintos eventos de los botones de la interfaz baja/modificar reserva
	 */
	private void fixButtons() {
		fixAcceptButton();
		fixCancelButton();
	}

	/**
	 * Procesa el evento del boton "aceptar" de la interfaz baja/modificar reserva
	 */
	private void fixAcceptButton() {
		acceptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean correct = true;
				Reserva reservaMod = null;
				String messageError = "";
				String fecha = fechaText.getText().trim();
				int hora = 0;
				if(!camposVacios() && camposNumCorrectos()){
					hora = Integer.parseInt(horaText.getText().trim());
					if (!Utilidades.esUnaFecha(fecha) && Utilidades.compararFecha(fecha, Utilidades.fechaHoy()) <= 0){
						correct = false; 
						messageError = "Por favor, revise el formato de fecha (DD/MM/AAAA), además debe ser anterior a hoy.";
					}
					else if (hora < 10 || 20 < hora){
						correct = false; 
						messageError = "Por favor, revise el formato de fecha (DD/MM/AAAA), además debe ser anterior a hoy.";
					
					}
					else{
						reservaMod = new Reserva(
								reserva.getIdReserva(), 
								reserva.getIdAlumno(), 
								reserva.getIdProfesor(),
								horaText.getText().trim(),
								fechaText.getText().trim()
								);
					}							
				}	
				else{
					correct = false;
					messageError = "Por favor, revise los campos, no deben estar vacíos y deben ser números donde corresponda.";
				}
				
				if(correct){
					if(confirmar(fecha, hora+"") == YES)
						controlador.modificarReserva(reservaMod);
				}
				else
					showError("Error", messageError);
	
			}
		}
	);
	}

	
	/**
	 * Oculta la ventana de la interfaz baja/modificar reserva
	 */
	private void fixCancelButton() {
		cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
				}
			}
		);
	}
	
	public ImageIcon createImage(String name) {

		ImageIcon icono = null;
		URL url = GUI.class.getResource("imagenes/" + name);
		if (url != null) {
			icono = new ImageIcon(url);
		}
		return icono;

	}
	
	
	public void mostrarModReserva(Reserva reserva){
		this.reserva = reserva;
		Persona alumno = controlador.buscarAlumno(reserva.getIdAlumno());
		Persona profesor = (Profesor)controlador.buscarPersonal(reserva.getIdProfesor());

		idReservaText.setText(reserva.getIdReserva()+"");
		profesorText.setText(profesor.getNombre() + " " + profesor.getApellido() + ", " + profesor.getDni());
		alumnoText.setText(alumno.getNombre() + " " + alumno.getApellido() + ", " + alumno.getDni());
		horaText.setText(reserva.getHora());
		fechaText.setText(reserva.getFecha());
		this.setVisible(true);
	}
	
	private int confirmar(String fecha, String hora){
		String mensaje = "¿Cambiar la reserva de la clase para las " + hora + ":00 del día " + fecha + "?";
		
		return JOptionPane.showConfirmDialog(null, 
				mensaje, 
				"Modificar reserva", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE, 
				null);
	}
	
	private boolean camposNumCorrectos() {
		 return Utilidades.isPositiveNumber(horaText.getText().trim());
	}
	
	private boolean camposVacios(){
		return(fechaText.getText().trim().isEmpty() 
				|| horaText.getText().trim().isEmpty());
	}
	
	private void showError(String error, String mensaje){
		JOptionPane.showOptionDialog(null, mensaje, error, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);	
	}
}
