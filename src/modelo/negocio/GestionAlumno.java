package modelo.negocio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import modelo.dominio.personas.Alumno;
import GUI.observables.AlumnosObserver;
import GUI.observables.Observable;
import dao.ComparadorPersona;
import dao.DAO;
import ficheros.CargarDatos;
import ficheros.GuardarDatos;
import ficheros.excepciones.ExceptionLeerAlumnos;

/**
 * Clase encargada de gestionar los alumnos
 * @author David Bolanios
 */
public class GestionAlumno extends Observable<AlumnosObserver>{
	
	private DAO<Alumno> daoAlumnos;
	
	/**
	 * Contructora por defecto
	 */
	public GestionAlumno(){
		daoAlumnos = new DAO<Alumno>();	
	}	
	
	/**
	 * Añade un alumno al dao de alumnos. Si se ha insertado, se notifica con mensaje de exito al 
	 * usuario.En caso contrario, error porque ya existe el alumno y se notifica de error.
	 * @param Alumno el alumno a insertar
	 */
	public void altaAlumno(Alumno alumno) {
		if(daoAlumnos.add(alumno))
			mostrarInfo("Exito", "El alumnno " + alumno.getNombre() + " " + alumno.getApellido() + " se ha matriculado.");
		else
			errorProducido("Error al insertar alumno", 
						   "El alumnno " + alumno.getNombre() + " " + alumno.getApellido() + " ya existe.");
		}

	/**
	 * Vuelve no disponible a  un alumno del dao de alumnos. Si se ha hace correctamente, se notifica con 
	 * mensaje de exito al  usuario. Error en caso contrario,  porque no existe el alumn, por lo que se notifica 
	 * de error.
	 * @param DNI del alumno a cambiar disponibilidad
	 */
	public void bajaAlumno(String DNI) {
		Alumno alumno = buscarAlumno(DNI);
		if(alumno != null){
			daoAlumnos.eliminarElemento(alumno);
			alumno.cambiarEstado();
			daoAlumnos.add(alumno);	
			mostrarInfo("Exito", "El alumno" + alumno.getNombre() + " " + alumno.getApellido() 
					+ " con DNI " + DNI + " se ha dado de baja con éxito.");
		}
		else
			errorProducido("Error al dar de baja el alumno", 
					   "El alumnno con DNI " + DNI + "no existe.");
	}
	
	/**
	 * Busca un alumno a partir del DNI en el DAO de alumnos 
	 * @param DNI del alumno
	 * @return el alumno. Null si no se encuentra
	 */
	public Alumno buscarAlumno(String DNI) {
		return daoAlumnos.cogerElemento(new Alumno(DNI));
	}
	
	/**
	 * Modifica un alumno del DAO de alumnos
	 * @param alumno a modificar
	 */
	public void modificarAlumno(Alumno alumno){

		if(daoAlumnos.contains(alumno)){
			daoAlumnos.eliminarElemento(alumno);
			daoAlumnos.add(alumno);	
			mostrarInfo("Exito", "Alumno modificado correctamente.");
		}
		else
			errorProducido("Error", "El alumno con DNI " + alumno.getDni() + " no se ha encontrado.");
	}


	/**
	 * Método encargado generar el listado de alumnos matriculados y disponibles y devolverlo de 
	 * forma ordenada a sus observadores.
	 */
	public void historialAlumnos() {
		ArrayList<Alumno> listaOrdenada = new ArrayList<Alumno>();
		Iterator<Alumno> it = daoAlumnos.iterator();
		Alumno alumno = null;

		while (it.hasNext()) {
			alumno = it.next();
			if (alumno.esActivo())
				listaOrdenada.add(alumno);
		}

		Collections.sort(listaOrdenada, new ComparadorPersona());

		Iterator<AlumnosObserver> iterador = this.iterator();
		while (iterador.hasNext()) {
			iterador.next().infoAlumnos(listaOrdenada);
		}
	}

	/**
	 * Metodo que avisa a los observadores con un mensaje de informacion
	 * @param titulo El titulo del mensaje
	 * @param mensaje el mensaje
	 */
	private void mostrarInfo(String titulo, String mensaje){
		Iterator<AlumnosObserver> iterador = this.iterator();
		while (iterador.hasNext()) {
			iterador.next().mensajeInfo(titulo, mensaje);
		}
	}
	
	/**
	 * Metodo que avisa a los observadores de un error
	 * @param error El titulo del error
	 * @param mensaje el mensaje de error
	 */
	private void errorProducido(String error, String mensaje) {
		Iterator<AlumnosObserver> iterador = this.iterator();
		while (iterador.hasNext()) {
			iterador.next().showError(error, mensaje);
		}
	}
	
	/**
	 * Método encargado de ejecutar la lectura de los alumnos
	 * @throws IOException si se produce algun error
	 * @throws ExceptionLeerAlumnos si hay incongruencias en los alumnos
	 */
	public void leerAlumnos() throws IOException, ExceptionLeerAlumnos{
		FileInputStream alumnos = null;
		
		try {
			alumnos = new FileInputStream("AutosoftDatos/Alumnos.txt");
			daoAlumnos = CargarDatos.leerAlumnos(alumnos);
		} 
		catch (FileNotFoundException e1) {
			System.err.println("Fichero Alumnos.txt no encontrado. Se comenzará con una lista vacía.");
		}
		catch (IOException | ExceptionLeerAlumnos e) {
			throw new ExceptionLeerAlumnos("Error en la lectura de los alumnos. Revise el formato de los datos.");
		}
		finally{
			if(alumnos != null)
				alumnos.close();
		}
	}

	/**
	 * Metodo que llama al metodo escribirAlumnos de la clase GuardarDatos, para
	 * guardar en el fichero el condenedor de los usuarios DAO&ltAlumno&gt
	 * @throws IOException
	 */
	public void guardarAlumnos() throws IOException{
		FileOutputStream alumnos = null;
		
		try {
			alumnos = new FileOutputStream("AutosoftDatos/Alumnos.txt");
			GuardarDatos.escribirEnFichero("EmpiezanAlumnos", "FinAlumnos", alumnos,daoAlumnos);
		} 
		catch (FileNotFoundException e1) {
			System.err.println("Fichero Alumnos.txt no encontrado. Se crea el fichero Alumnos.txt.");
		}

		finally{
			if(alumnos != null)
				alumnos.close();
		}
	}
	
	/**
	 * Elimina un observador de esta clase
	 * @param observer el observador a eliminar
	 */
	public void removeAlumnosObserver(AlumnosObserver observer) {
		this.removeObserver(observer);
	}

	/**
	 * Elimina todos los observadores de esta clase
	 */
	public void removeAllObservers() {
		super.removeAllObservers();
	}
	
	/**
	 * Registra un observador a esta clase.
	 * @param observer El observador que se quiere registrar.
	 */
	public void addAlumnosObserver(AlumnosObserver observer) {
		this.addObserver(observer);		
	}


}
