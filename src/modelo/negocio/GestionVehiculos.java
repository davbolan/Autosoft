package modelo.negocio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import modelo.dominio.vehiculo.Vehiculo;
import GUI.observables.Observable;
import GUI.observables.VehiculosObserver;
import dao.ComparadorVehiculo;
import dao.DAO;
import ficheros.CargarDatos;
import ficheros.GuardarDatos;
import ficheros.excepciones.ExceptionLeerVehiculos;

/**
 * Clase encargada de gestionar los vehiculos
 * @author David Bolanios
 */
public class GestionVehiculos extends Observable<VehiculosObserver>{
	
	private DAO<Vehiculo> daoVehiculos;

	/**
	 * Contructora por defecto
	 */
	public GestionVehiculos() {
		daoVehiculos = new DAO<Vehiculo>();
	}

	/**
	 * Añade un vehiculo al dao de vehiculos. Si se ha insertado, se notifica con mensaje de exito al usuario
	 * En caso contrario, error porque ya existe el vehiculo y se notifica de error.
	 * @param Vehiculo el vehiculo a insertar
	 */
	public void nuevoVehiculo(Vehiculo vehiculo) {
		if(daoVehiculos.add(vehiculo))
			avisarObservadores("Exito", vehiculo.getMarca() + " " + vehiculo.getModelo() + 
					" con matrícula " + vehiculo.getMatricula() + " a sido añadido correctamente.");
		else
			errorProducido("Error", "Ya existe un vehículo con matrícula " + vehiculo.getMatricula() + ".");
	}

	/**
	 * Elimina un vehiculo del dao de vehiculos. Si se ha eliminado, se notifica con mensaje de exito al 
	 * usuario. En caso contrario, error porque no existe el vehiculo y se notifica de error.
	 * @param matricula del vehiculo a eliminar
	 */
	public void bajaVehiculo(String matricula) {
		if(daoVehiculos.eliminarElemento(new Vehiculo(matricula)))
			avisarObservadores("Exito", "El vehículo con matrícula " + matricula + " se ha eliminado.");	
		else
			errorProducido("Error", "El vehículo con matrícula " + matricula + " no se ha encontrado.");
	}

	/**
	 * Busca un vehiculo a partir de la matricula en el DAO de vehicuo 
	 * @param matricula del vehiculo
	 * @return el vehiculo. Null si no se encuentra
	 */
	public Vehiculo buscarVehiculo(String matricula) {
		return daoVehiculos.cogerElemento(new Vehiculo(matricula));
	}

	/**
	 * Modifica un vehiculo del DAO de vehiculos
	 * @param vehiculo a modificar
	 */
	public void modificarVehiculo(Vehiculo vehiculo) {
		if(daoVehiculos.contains(vehiculo)){
			daoVehiculos.eliminarElemento(vehiculo);
			daoVehiculos.add(vehiculo);		
			avisarObservadores("Exito", "Vehículo modificado correctamente.");
		}
		else
			errorProducido("Error", "El vehículo con matrícula " + vehiculo.getMatricula() + " no se ha encontrado.");
	}
	
	/**
	 * Metodo que avisa a los observadores con la lista de vehículos disponibles
	 */
	public void informacionVehiculos() {
		ArrayList<Vehiculo> listaOrdenada = daoVehiculos.listarElementos();
		Collections.sort(listaOrdenada, new ComparadorVehiculo());
		Iterator<VehiculosObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().informacionVehiculos(listaOrdenada);
		}
	}

	/**
	 * Método encargado de ejecutar la lectura de los vehiculos
	 * @throws IOException si se produce algun error
	 * @throws ExceptionLeerVehiculos si hay incongruencias en los vehiculos
	 */
	public void leerVehiculos() throws IOException, ExceptionLeerVehiculos  {
		FileInputStream vehiculos = null;
		
		try {
			vehiculos = new FileInputStream("AutosoftDatos/Vehiculos.txt");
			daoVehiculos = CargarDatos.leerVehiculos(vehiculos);
		} 
		catch (FileNotFoundException e1) {
			System.err.println("Fichero Vehiculos.txt no encontrado. Se comenzará con una lista vacía");
		}
		catch (IOException | ExceptionLeerVehiculos e) {
			throw new ExceptionLeerVehiculos("Error en la lectura de los vehículos. Revise el formato de los datos.");
		}
		finally{
			if(vehiculos != null)
				vehiculos.close();
		}
	}

	/**
	 * Metodo que llama al metodo escribirVehiculos de la clase GuardarDatos, para guardar en el 
	 * fichero el condenedor de los vehiculos DAO&ltVehiculo&gt
	 * @throws IOException
	 */
	public void guardarVehiculos() throws IOException{
		FileOutputStream vehiculos = null;
		
		try {
			vehiculos = new FileOutputStream("AutosoftDatos/Vehiculos.txt");
			GuardarDatos.escribirEnFichero("EmpiezanVehiculos", "FinVehiculos", vehiculos,daoVehiculos);
		} 
		catch (FileNotFoundException e1) {
			System.err.println("Fichero Vehiculos.txt no encontrado. Se crea el fichero Vehiculos.txt.");
		}
		finally{
			if(vehiculos != null)
				vehiculos.close();
		}
	}
	
	/**
	 * Metodo que avisa a los observadores con un mensaje de informacion
	 * @param titulo El titulo del mensaje
	 * @param mensaje el mensaje
	 */
	private void avisarObservadores(String type, String message) {
		Iterator<VehiculosObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().mensajeInfo(type, message);
		}
	}

	/**
	 * Metodo que avisa a los observadores de un error
	 * @param error El titulo del error
	 * @param mensaje el mensaje de error
	 */
	private void errorProducido(String error, String mensaje) {
		Iterator<VehiculosObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().showError(error, mensaje);
		}
	}
	
	
	/**
	 * Método encargado una lista con vehiculos vehiculos disponibles para poder asignarle
	 * a un profesor
	 */
	public void rellenarMatriculas() {
		ArrayList<Vehiculo> vehiculosDisponibles = new ArrayList<Vehiculo>();
		Vehiculo veh = null;
		
		Iterator<Vehiculo> it = daoVehiculos.iterator();
		while(it.hasNext()){
			veh = it.next();
			if(veh.getEstado().equalsIgnoreCase("Activo"))
				vehiculosDisponibles.add(veh);
		}

		mostrarMatriculas(vehiculosDisponibles);
	}
	
	/**
	 * Metodo auxiliar que avisa a los observadores con la lista de vehiculos disponibles
	 * @param vehiculosDisponibles lista de vehiculos disponibles
	 */
	private void mostrarMatriculas(ArrayList<Vehiculo> vehiculosDisponibles) {
		Iterator<VehiculosObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().matriculaVehiculos(vehiculosDisponibles);
		}
	}
	
	/**
	 * Elimina un observador de esta clase
	 * @param observer el observador a eliminar
	 */
	public void removeVehiculosObserver(VehiculosObserver observer) {
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
	public void addVehiculosObserver(VehiculosObserver observer) {
		this.addObserver(observer);
	}
}
