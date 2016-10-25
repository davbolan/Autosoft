package modelo.negocio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.dominio.gestion.Contabilidad;
import utilidades.Utilidades;
import GUI.observables.ContabilidadObserver;
import GUI.observables.Observable;
import dao.DAO;
import ficheros.CargarDatos;
import ficheros.GuardarDatos;
import ficheros.excepciones.ExceptionLeerContabilidad;

/**
 * Clase encargada de gestionar la contabilidad
 * @author David Bolanios
 */
public class GestionContabilidad extends Observable<ContabilidadObserver>{

	private DAO<Contabilidad> daoContabilidad;
	
	/**
	 * Contructora por defecto
	 */
	public GestionContabilidad(){
		daoContabilidad = new DAO<Contabilidad>();
	}
	
	/**
	 * Añade un ejercicio de contabilidad al dao de contabilidad. Si se ha insertado, se notifica 
	 * con mensaje de exito al usuario. En caso contrario, error porque ya existe el ejercicio y 
	 * se notifica de error.
	 * @param contabilidad el ejercicio a insertar
	 */
	public void nuevoEjercicio(Contabilidad contabilidad){
		daoContabilidad.addWinthoutRepeating(contabilidad);
		ejercicioAniadido(contabilidad);
	}
	
	/**
	 * Metodo que avisa a los observadores de que se ha añadido un mensaje correctamente.
	 * @param titulo El titulo del mensaje
	 * @param mensaje el mensaje
	 */
	private void ejercicioAniadido(Contabilidad contabilidad){
		Iterator<ContabilidadObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().mensajeInfo("Éxito", contabilidad.getTipo() + " realizado con éxito.");
		}
	}

	/**
	 * Este método va recorriendo la lista entera, cogiendo cada elemento que cumpla
	 * las condiciones de estar entra las fechas indicadas y añadiendolo a su lista
	 * correspondiente, lista de gastos o ingresos.
	 * @param fechaIni Fecha que debe igualar o superar un ejercicio para ser mostrado.
	 * @param fechaFin Fecha que debe igualar o no superar un ejercicio para ser mostrado.
	 */
	public void mostrarBalance(String fechaIni, String fechaFin){
		Contabilidad ejercicio = null;
		String fechaEjer = "";
		double subtotalIngresos = 0;
		double subtotalGastos 	= 0;
		List<Contabilidad> ingresos = new ArrayList<Contabilidad>();
		List<Contabilidad> gastos 	= new ArrayList<Contabilidad>();

		for (int j = 0; j < daoContabilidad.size(); j++) {
			ejercicio =  daoContabilidad.cogerElemento(j);
			fechaEjer = ejercicio.getFecha();
			if ((Utilidades.compararFecha(fechaIni, fechaEjer) <= 0) && (Utilidades.compararFecha(fechaEjer, fechaFin) <= 0)) {
				if (ejercicio.getTipo().equalsIgnoreCase("gasto")) {
					subtotalGastos += ejercicio.getCantidad();
					gastos.add(ejercicio);
				} 
				else {
					subtotalIngresos += ejercicio.getCantidad();
					ingresos.add(ejercicio);
				}
			}
		}

		infoBalance(subtotalIngresos, subtotalGastos, ingresos, gastos);
	}

	/**
	 * Metodo que avisa a los observadores con con los datos de los ejercicios
	 * @param subtotalIngresos subTotal de los ingresos
	 * @param subtotalGastos subTotal de los gastos
	 * @param ingresos Lista de ingresos
	 * @param gastos Lista de gastos
	 */
	private void infoBalance(double subtotalIngresos, double subtotalGastos,
			List<Contabilidad> ingresos, List<Contabilidad> gastos) {
		
		double total = subtotalIngresos - subtotalGastos;
		Iterator<ContabilidadObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().mostrarBalance(ingresos, gastos, total, subtotalIngresos, subtotalGastos);
		}
		
	}

	/**
	 * Método encargado de ejecutar la lectura de la contabilidad
	 * @throws IOException si se produce algun error
	 * @throws ExceptionLeerContabilidad si hay incongruencias en la contabilidad
	 */
	public void leerContabilidad() throws IOException, ExceptionLeerContabilidad {
		FileInputStream ejercicios = null;
		
		try {
			ejercicios = new FileInputStream("AutosoftDatos/Contabilidad.txt");
			daoContabilidad= CargarDatos.leerContabilidad(ejercicios);
		} 
		catch (FileNotFoundException e1) {
			System.err.println("Fichero Contabilidad.txt no encontrado. Se comenzará con una lista vacía");
		}
		catch (IOException | ExceptionLeerContabilidad e) {
			throw new ExceptionLeerContabilidad("Error en la lectura de la contabilidad. Revise el formato de los datos.");
		}
		finally{
			if(ejercicios != null)
				ejercicios.close();
		}
	}
	
	/**
	 * Metodo que llama al metodo escribirContabilidad de la clase GuardarDatos,
	 * para guardar en el fichero el condenedor de los usuarios DAO&ltContabilidad&gt
	 * 
	 * @throws IOException
	 */
	public void guardarContabilidad() throws IOException{
		FileOutputStream ejercicios = null;
		
		try {
			ejercicios = new FileOutputStream("AutosoftDatos/Contabilidad.txt");
			GuardarDatos.escribirEnFichero("EmpiezanContabilidad", "FinContabilidad", ejercicios,daoContabilidad);
		} 
		catch (FileNotFoundException e1) {
			System.err.println("Fichero Contabilidad.txt no encontrado. Se crea el fichero Contabilidad.txt.");
		}
		finally{
			if(ejercicios != null)
				ejercicios.close();
		}
	}
	
	/**
	 * Registra un observador a esta clase.
	 * @param observer El observador que se quiere registrar.
	 */
	public void addContabilidadObserver(ContabilidadObserver observer) {
		this.addObserver(observer);
	}
	
	/**
	 * Elimina un observador de esta clase
	 * @param observer el observador a eliminar
	 */
	public void removeContabilidadObserver(ContabilidadObserver observer) {
		this.removeObserver(observer);	
	}

	/**
	 * Elimina todos los observadores de esta clase
	 */
	public void removeAllObservers() {
		super.removeAllObservers();
	}


}
