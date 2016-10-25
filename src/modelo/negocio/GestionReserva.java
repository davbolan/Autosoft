package modelo.negocio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import modelo.dominio.gestion.Reserva;
import utilidades.Utilidades;
import GUI.observables.Observable;
import GUI.observables.ReservasObserver;
import dao.ComparadorReserva;
import dao.DAO;
import ficheros.CargarDatos;
import ficheros.GuardarDatos;
import ficheros.excepciones.ExceptionLeerReservas;

/**
 * Clase encargada de gestionar las reservas
 * @author David Bolanios
 */
public class GestionReserva extends Observable<ReservasObserver>{
	
	private DAO<Reserva> daoReservas;
	
	/**
	 * Contructora por defecto
	 */
	public GestionReserva(){
		daoReservas = new DAO<Reserva>();
	}
	
	/**
	 * Añade una reserva al dao de reservas. Si se ha insertado, se notifica con mensaje de exito al 
	 * usuario. En caso contrario, error porque ya existe ya existia la reserva y se notifica de error.
	 * @param Reserva la reserva a insertar
	 */
	public void nuevaReserva(Reserva reserva) {
	
		if(daoReservas.add(reserva))
			mostrarInfo("Nueva reserva", "Reserva realizada con éxito");
		else{
			Reserva.numReserva--;  // Reduzco el numero de reservas, pues se habia incrementado al hacer el new Reserva
			errorProducido("Error", "Ya hay un reserva realizada para esa hora.");
		}
	}
	
	/**
	 * Elimina una reserva del dao de reservas. Si se ha eliminado, se notifica con mensaje de exito al 
	 * usuario. En caso contrario, error porque no existe la reserva, por lo que se notifica de error.
	 * @param idReserva de la reserva a eliminar
	 */
	public void anularReserva(int idReserva) {
		if(daoReservas.eliminarElemento(new Reserva(idReserva)))
			mostrarInfo("Nueva reserva", "Reserva anulada con éxito.");	
		else
			errorProducido("Error", "Ya hay un reserva realizada para esa hora.");
	}
	
	/**
	 * Busca una reserva a partir del numero de reserva en el DAO de reservas 
	 * @param numReserva de la reserva
	 * @return la reserva. Null si no se encuentra
	 */
	public Reserva buscarReserva(int numReserva) {
		return daoReservas.cogerElemento(new Reserva(numReserva));
	}
	
	/**
	 * Modifica una reserva del DAO de reservas
	 * @param reserva a modificar
	 */
	public void modificarReserva(Reserva reserva) {
		Reserva obsReserva;
		if(daoReservas.contains(reserva)){
			obsReserva = buscarReserva(reserva.getIdReserva()); 						// Me guardo la antigua reserva
			daoReservas.eliminarElemento(obsReserva);									// La elimino
			if(daoReservas.add(reserva)){												// Si se puede añadir la modifica..
				mostrarInfo("Nueva reserva", "Reserva modificada con éxito");			// ... aviso
			}	
			else{
				daoReservas.add(obsReserva);											// ...si no restauro la antigua
				errorProducido("Error", "Ya hay un reserva realizada para esa hora.");	// y notifico de error
			}
				
		}
		else
			errorProducido("Error", "La reserva ya esta realizada");
	}
	
	/**
	 * Metodo que avisa a los observadores con un mensaje de informacion
	 * @param titulo El titulo del mensaje
	 * @param mensaje el mensaje
	 */
	private void mostrarInfo(String titulo, String mensaje){
		Iterator<ReservasObserver> iterador = this.iterator();
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
		Iterator<ReservasObserver> iterador = this.iterator();
		while (iterador.hasNext()) {
			iterador.next().showError(error, mensaje);
		}
	}
	
	/**
	 * Método encargado de generar una lista con las reservas que tenga un alumno o profesor
	 * del dia actual o de todas, según el valor del parámetro rango.
	 * @param idPersona persona que debe estar en la reserva
	 * @param rol rol de la persona
	 * @param rango con valor "hoy" se mostrarán las reservas del día actual. "todas" se mostrarán
	 * todas las reservas de hoy en adelante
	 */
	public void mostrarMisReservas(String idPersona, String rol, String rango) {
		ArrayList<Reserva> misReservas = new ArrayList<Reserva>();
		Reserva reserva = null;
		boolean cond;
		String fechaRes;
		Iterator<Reserva> it = daoReservas.iterator();
		
		while(it.hasNext()){
			reserva = it.next();
			fechaRes = reserva.getFecha();
			
			if(rango.equalsIgnoreCase("hoy"))
				cond = (Utilidades.compararFecha(fechaRes, Utilidades.fechaHoy()) == 0);
			else if (rango.equalsIgnoreCase("todas"))
				cond = (Utilidades.compararFecha(fechaRes, Utilidades.fechaHoy()) >= 0);
			else
				cond = false;
			
			if(cond){
				if(rol.equalsIgnoreCase("profesor")){
					if(reserva.getIdProfesor().equals(idPersona))
						misReservas.add(reserva);
				}
				else if(rol.equalsIgnoreCase("alumno")){
					if(reserva.getIdAlumno().equals(idPersona))
						misReservas.add(reserva);
				}
			}
		}
		
		Collections.sort(misReservas, new ComparadorReserva());
		
		listarReservasPersona(misReservas);
		
	}


	/**
	 * Método encargado de ejecutar la lectura de las reservas
	 * @throws IOException si se produce algun error
	 * @throws ExceptionLeerReservas si hay incongruencias en las reservas
	 */
	public void leerReservas() throws IOException, ExceptionLeerReservas {
		FileInputStream reservas = null;
		
		try {
			reservas = new FileInputStream("AutosoftDatos/Reservas.txt");
			daoReservas = CargarDatos.leerReservas(reservas);
		} 
		catch (FileNotFoundException e1) {
			System.err.println("Fichero Reservas.txt no encontrado. Se comenzar� con una lista vacía.");
		}
		catch (IOException | ExceptionLeerReservas e) {
			throw new ExceptionLeerReservas("Error en la lectura de las reservas. Revise el formato de los datos.");
		}
		finally{
			if(reservas != null)
				reservas.close();
		}
	}

	/**
	 * Metodo que llama al metodo escribirReservas de la clase GuardarDatos, para guardar en el 
	 * fichero el condenedor de los usuarios DAO&ltReserva&gt
	 * @throws IOException
	 */
	public void guardarReservas() throws IOException{
		FileOutputStream reservas = null;
		
		try {
			reservas = new FileOutputStream("AutosoftDatos/Reservas.txt");
			GuardarDatos.escribirEnFichero("EmpiezanReservas", "FinReservas", reservas,daoReservas);
		} 
		catch (FileNotFoundException e1) {
			System.err.println("Fichero Reservas.txt no encontrado. Se crea el fichero Reservas.txt.");
		}
		finally{
			if(reservas != null)
				reservas.close();
		}
	}
	

	/**
	 * Método encargado de gener una lista con llas reservas que incluyan el profesor y 
	 * alumno indicados, asi como la fecha.
	 * @param fechaEscogida la fecha que debe tener la reserva
	 * @param dniAlumno el DNI del alumno que ha realizado la reserva.
	 * @param dniProfesor el DNI del profesor que está en la reserva
	 */
	public void mostrarReservas(String fechaEscogida, String dniAlumno, String dniProfesor) {
		ArrayList<Reserva> listReservas = new ArrayList<Reserva>();
		Reserva reserva = null;
		boolean mismaFecha = false;
		Iterator<Reserva> it = daoReservas.iterator();
		
		if(dniAlumno.isEmpty() && dniProfesor.isEmpty()){
			while(it.hasNext()){
				reserva = it.next();
				mismaFecha = (Utilidades.compararFecha(reserva.getFecha(), fechaEscogida) == 0);		
				if(mismaFecha)
					listReservas.add(reserva);
			}
		}
		else if(!dniAlumno.isEmpty()){
			while(it.hasNext()){
				reserva = it.next();
				mismaFecha = (Utilidades.compararFecha(reserva.getFecha(), fechaEscogida) == 0);
				if(mismaFecha && reserva.getIdAlumno().equalsIgnoreCase(dniAlumno))
					listReservas.add(reserva);
			
			}
		}
		else if(!dniProfesor.isEmpty()){
			while(it.hasNext()){
				reserva = it.next();
				mismaFecha = (Utilidades.compararFecha(reserva.getFecha(), fechaEscogida) == 0);
				if(mismaFecha && reserva.getIdProfesor().equalsIgnoreCase(dniProfesor))
					listReservas.add(reserva);
			}
		}
		
		listarReservas(listReservas);
		
	}

	/**
	 * Metodo auxiliar que avisa a los observadores con una lista de reservas
	 * @param listaReservas lista de reservas
	 */
	private void listarReservas(ArrayList<Reserva> listaReservas) {
		Iterator<ReservasObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().mostrarReservas(listaReservas);
		}	
	}

	/**
	 * Método encargado que filtrar una lista de reservas para dejar aquellas que pertenezca
	 * a una persona.
	 * @param reservas la lista de reservas a filtrar
	 * @param dniPersona el DNI de la persona a filtrar
	 * @param persona el tipo de persona: "alumno" o "profesor"
	 */
	public void filtrarReservas(ArrayList<Reserva> reservas, String dniPersona, String persona) {
		Iterator<Reserva> it = reservas.iterator();
		ArrayList<Reserva> reservasPersona = new ArrayList<Reserva>();
		Reserva reserva = null;
		while(it.hasNext()){
			reserva = it.next();
			if(persona.equalsIgnoreCase("profesor") && reserva.getIdProfesor().equalsIgnoreCase(dniPersona))
				reservasPersona.add(reserva);
			else if(persona.equalsIgnoreCase("alumno") && reserva.getIdAlumno().equalsIgnoreCase(dniPersona))
				reservasPersona.add(reserva);
		}
		
		listarReservasPersona(reservasPersona);
	}
	
	/**
	 * Metodo auxiliar que avisa a los observadores con una lista de reservas de una personaa
	 * @param listaReservas lista de reservas
	 */
	private void listarReservasPersona(ArrayList<Reserva> listaReservas) {
		Iterator<ReservasObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().mostrarReservasPersona(listaReservas);
		}	
	}
	
	/**
	 * Elimina un observador de esta clase
	 * @param observer el observador a eliminar
	 */
	public void removeReservasObserver(ReservasObserver observer) {
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
	public void addReservasObserver(ReservasObserver observer) {
		this.addObserver(observer);
	}
}
