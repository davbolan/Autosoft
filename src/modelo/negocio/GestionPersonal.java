package modelo.negocio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import modelo.dominio.personas.Personal;
import modelo.dominio.personas.Profesor;
import modelo.dominio.personas.ProfesorPractico;
import modelo.dominio.personas.ProfesorTeorico;
import GUI.observables.Observable;
import GUI.observables.PersonalObserver;
import dao.ComparadorPersona;
import dao.DAO;
import ficheros.CargarDatos;
import ficheros.GuardarDatos;
import ficheros.excepciones.ExceptionLeerPersonal;

/**
 * Clase encargada de gestionar el personal
 * @author David Bolanios
 */
public class GestionPersonal extends Observable<PersonalObserver>{
	
	private DAO<Personal> daoPersonal;
	
	/**
	 * Contructora por defecto
	 */
	public GestionPersonal(){
		 daoPersonal = new DAO<Personal>();
	}
	
	/**
	 * Añade un personal al dao de personal. Si se ha insertado, se notifica con mensaje de exito al 
	 * usuario. En caso contrario, error porque ya existe el personal y se notifica de error.
	 * @param Personal el personal a insertar
	 */
	public void altaPersonal(Personal personal) {
		if(daoPersonal.add(personal))
			avisarObservadores("Exito", personal.getNombre() + " " + personal.getApellido() + 
								" con DNI " + personal.getDni() + " a sido añadido correctamente.");
		else
			errorProducido("Error", "Ya existe un personal con DNI " + personal.getDni() + ".");
	}
	
	/**
	 * Elimina un personal del dao de personal. Si se ha eliminado, se notifica con mensaje de exito al 
	 * usuario, En caso contrario, es porque no el vehiculo, por lo que se notifica de error.
	 * @param matricula del vehiculo a eliminar
	 */
	public void bajaPersonal(String DNI) {
		if(daoPersonal.eliminarElemento(new Personal(DNI)))
			avisarObservadores("Exito", "El personal con DNI " + DNI + " se ha eliminado.");	
		else
			errorProducido("Error", "El personal con DNI " + DNI + " no se ha encontrado.");
	}
	
	/**
	 * Busca un personal a partir del DNI en el DAO de personal 
	 * @param DNI del personal
	 * @return el personal. Null si no se encuentra
	 */
	public Personal buscarPersonal(String DNI) {
		Personal per = daoPersonal.cogerElemento(new Personal(DNI));
		return per;
	}
	
	/**
	 * Modifica un personal del DAO de personal
	 * @param personal a modificar
	 */
	public void modificarPersonal(Personal personal) {

		if(daoPersonal.contains(personal)){
			daoPersonal.eliminarElemento(personal);
			daoPersonal.add(personal);	
			avisarObservadores("Exito", personal.getCargo() + " modificado correctamente.");
		}
		else
			errorProducido("Error", "El personal con DNI " + personal.getDni() + " no se ha encontrado.");
	}
	
	/**
	 * Metodo que avisa a los observadores con la lista de personal
	 */
	public void informacionPersonal() {
		ArrayList<Personal> listaOrdenada = daoPersonal.listarElementos();
		Collections.sort(listaOrdenada, new ComparadorPersona());
		Iterator<PersonalObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().infoPersonal(listaOrdenada);
		}
	}
	
	
	/**
	 * Método encargado de ejecutar la lectura del personal
	 * @throws IOException si se produce algun error
	 * @throws ExceptionLeerPersonal si hay incongruencias en el personal
	 */
	public void leerPersonal() throws IOException, ExceptionLeerPersonal {
		FileInputStream personal = null;
		
		try {
			personal = new FileInputStream("AutosoftDatos/Personal.txt");
			daoPersonal = CargarDatos.leerPersonal(personal);
		} 
		catch (FileNotFoundException e1) {
			System.err.println("Fichero Personal.txt no encontrado. Se comenzar� con una lista vac�a");
		}
		catch (IOException | ExceptionLeerPersonal e) {
			throw new ExceptionLeerPersonal("Error en la lectura del personal. Revise el formato de los datos.");
		}
		finally{
			if(personal != null)
				personal.close();
		}
	}

	/**
	 * Metodo que llama al metodo escribirPersonal de la clase GuardarDatos,
	 * para guardar en el fichero el condenedor de los usuarios DAO&ltPersonal&gt
	 * @throws IOException
	 */
	public void guardarPersonal() throws IOException{
		FileOutputStream personal = null;
		
		try {
			personal = new FileOutputStream("AutosoftDatos/Personal.txt");
			GuardarDatos.escribirEnFichero("EmpiezanTrabajadores", "FinTrabajadores", personal, daoPersonal);
		} 
		catch (FileNotFoundException e1) {
			System.err.println("Fichero Personal.txt no encontrado. Se crea el fichero Personal.txt.");
		}
		finally{
			if(personal != null)
				personal.close();
		}
	}
	
	
		
	/**
	 * Metodo que avisa a los observadores con un mensaje de informacion
	 * @param titulo El titulo del mensaje
	 * @param mensaje el mensaje
	 */
	private void avisarObservadores(String type, String message) {
		Iterator<PersonalObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().mensajeInfo(type, message);
		}
	}

	/**
	 * Metodo que avisa a los observadores de un error
	 * @param error El titulo del error
	 * @param mensaje el mensaje de error
	 */
	public void errorProducido(String error, String mensaje) {
		Iterator<PersonalObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().showError(error, mensaje);
		}
	}

	/**
	 * Método que genera dos listas con profesores los profesores del tipo indicados por
	 * los parámetros y que estén disponibles, para poder aisgnarselo a un alumno
	 * @param profeP tendrá varlor "practico" si se quieren los profesores que imparten práctica
	 * @param profeT tendrá varlor "teorico" si se quieren los profesores que imparten teoria
	 */ 
	public void rellenarProfesores(String profeP, String profeT) {
		ArrayList<ProfesorTeorico> profesoresT 	= new ArrayList<ProfesorTeorico>();
		ArrayList<ProfesorPractico> profesoresP = new ArrayList<ProfesorPractico>();
		
		Personal personal = null;
		Profesor profesor = null;
		
		Iterator<Personal> it = daoPersonal.iterator();
		while(it.hasNext()){
			personal = it.next();
			if(personal.getCargo().equalsIgnoreCase("Profesor")){
				profesor = (Profesor)personal;
				if(profesor.getDisponibilidad()){
					if(profesor.getTipo().equalsIgnoreCase(profeP))
						profesoresP.add((ProfesorPractico) personal);
					else if(profesor.getTipo().equalsIgnoreCase(profeT))
						profesoresT.add((ProfesorTeorico) personal);
				}
			}
		}

		mostrarProfesor(profesoresT, profesoresP);
	}

	/**
	 * Metodo auxiliar que avisa a los observadores con los profesores disponibles
	 * @param profesoresT Lista de profesores de teoria
	 * @param profesoresP Lista de profesores practicos
	 */
	private void mostrarProfesor(ArrayList<ProfesorTeorico> profesoresT,
			ArrayList<ProfesorPractico> profesoresP) {
		Iterator<PersonalObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().profesorDisponibles(profesoresT, profesoresP);
		}
		
	}
	
	/**
	 * Elimina un observador de esta clase
	 * @param observer el observador a eliminar
	 */
	public void removePersonalObserver(PersonalObserver observer) {
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
	public void addPersonalObserver(PersonalObserver observer) {
		this.addObserver(observer);
	}
}
