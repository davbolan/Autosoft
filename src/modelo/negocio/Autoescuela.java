package modelo.negocio;


import java.io.IOException;
import java.util.ArrayList;

import ficheros.excepciones.*;
import GUI.observables.*;
import modelo.dominio.gestion.*;
import modelo.dominio.personas.*;
import modelo.dominio.usuarios.Usuario;
import modelo.dominio.vehiculo.Vehiculo;

/**
 * Clase que hace de función de fachada que contiene todos los modelos de negocio, asi como todas
 * las operaciones que estas puedan realizar
 * @author David Bolanios
 */
public class Autoescuela {
	private GestionAlumno gestionAlumnos;
	private GestionUsuarios gestionUsuarios;
	private GestionPersonal gestionPersonal;
	private GestionVehiculos gestionVehiculos;
	private GestionReserva gestionReserva;
	private GestionContabilidad gestionContabilidad;

	private boolean datosCargados;
	private String idPersona;

	/**
	 * Contructora de la autoescuela que inicializa el modelo de negocio.
	 */
	public Autoescuela(){
		gestionAlumnos 		= new GestionAlumno();
		gestionUsuarios 	= new GestionUsuarios();
		gestionPersonal		= new GestionPersonal();
		gestionVehiculos 	= new GestionVehiculos();
		gestionReserva 		= new GestionReserva();
		gestionContabilidad = new GestionContabilidad();
		datosCargados = false;
	}
	
	/**
	 * Lee los datos de los ficheros. Solo se leen cuando se produce el primer login.
	 * @throws Exception Si hay algun error en la lectura o en el formato de los ficheros. 
	 */
	public void leerDatos() throws Exception{
		try{
			if(!datosCargados){						
				gestionAlumnos.leerAlumnos();
				gestionUsuarios.leerUsuarios();
				gestionVehiculos.leerVehiculos();
				gestionPersonal.leerPersonal();
				gestionReserva.leerReservas();
				gestionContabilidad.leerContabilidad();
				datosCargados = true;
			}
		}
		catch(IOException | ExceptionLeerAlumnos | ExceptionLeerUsuarios | ExceptionLeerVehiculos | 
			  ExceptionLeerPersonal | ExceptionLeerReservas | ExceptionLeerContabilidad e){
			throw new Exception(e.getMessage());
		}	
	}
	
	/**
	 * Escribe los datos en los ficheros. Solo se escriben si previamente se han cargado.
	 */
	public void escribirDatos() {
		try {
			if(datosCargados){						
				gestionAlumnos.guardarAlumnos();
				gestionUsuarios.guardarUsuarios();
				gestionVehiculos.guardarVehiculos();
				gestionPersonal.guardarPersonal();
				gestionReserva.guardarReservas();
				gestionContabilidad.guardarContabilidad();
			}
		} 
		catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
	
	// Usuarios
	/**
	 * Método encargado de pedir a gestionUsuarios el login de un usuario.
	 * @param DNI El usuario a loguear.
	 */
	public void login(Usuario usuario) throws Exception{
		idPersona = usuario.getDni();
		leerDatos();
		gestionUsuarios.login(usuario);
	}
	
	/**
	 * Método encargado de pedir a gestionUsuarios la inserción de un nuevo usuario.
	 * @param Usuario el usuario a dar de alta
	 */
	public void nuevoUsuario(Usuario usuario){
		String DNI = usuario.getDni();
		boolean correct = true;
		String rol = usuario.getRol().toLowerCase();
		if(rol.equalsIgnoreCase("alumno")){
			if(gestionAlumnos.buscarAlumno(DNI) == null){
				correct = false;
				gestionUsuarios.errorProducido("Error", "El usuario alumno debe estar previamente matriculado con DNI "+ DNI + ".");
			}	
		}
		else if(rol.equalsIgnoreCase("profesor") || rol.equalsIgnoreCase("secretaria")){
			if(gestionPersonal.buscarPersonal(DNI) == null){
				correct = false;
				gestionUsuarios.errorProducido("Error", "El usuario " + rol + " debe estar previamente dado de alta como profesor con DNI "+ DNI + ".");
			}	
		} 
		if(correct)
			gestionUsuarios.nuevoUsuario(usuario);
	}
	
	/**
	 * Método encargado de pedir a gestionUsuarios la baja de un usuario.
	 * @param String el DNI del usuario a dar de baja
	 */
	public void bajaUsuario(String DNI) {
		gestionUsuarios.bajaUsuario(DNI);
	}
	
	/**
	 * Método encargado de pedir a gestionUsuarios la búsqueda de un usuario.
	 * @param DNI El DNI del usuario a buscar.
	 * @return el usuario
	 */
	public Usuario buscarUsuario(String DNI) {
		return gestionUsuarios.buscarUsuario(DNI);
	}
	
	/**
	 * Método encargado de pedir a gestionUsuarios la modificación de un vehiculo.
	 * @param Usuario el usuario modificado
	 */
	public void modificarUsuario(Usuario usuario) {		
		gestionUsuarios.modificarUsuario(usuario);
	}

	
	
	
	// Alumnos
	/**
	 * Método encargado de pedir a gestionAlumnos la inserción de un nuevo alumno.
	 * @param Alumno el alumno a dar de alta
	 */
	public void altaAlumno(Alumno alumno) {	
		gestionAlumnos.altaAlumno(alumno);
	}
	
	/**
	 * Método encargado de pedir a gestionAlumnos la baja de un alumno.
	 * @param String el DNI del alumno a dar de baja
	 */
	public void bajaAlumno(String DNI) {
		gestionAlumnos.bajaAlumno(DNI);
	}
	
	/**
	 * Método encargado de pedir a gestionAlumnos la búsqueda de un usuario.
	 * @param DNI El DNI del alumno a buscar.
	 * @return el alumno
	 */
	public Alumno buscarAlumno(String DNI) {
		return gestionAlumnos.buscarAlumno(DNI);
	}
	
	/**
	 * Método encargado de pedir a gestionAlumnos la modificación de un alumno.
	 * @param Alumno el alumno modificado
	 */
	public void modificarAlumno(Alumno alumno) {		
		gestionAlumnos.modificarAlumno(alumno);
	}

	/**
	 * Método encargado de pedir a gestionAlumnos los profesores del tipo indicados por
	 * los parámetros y que estén disponibles, para poder aisgnarselo a un alumno
	 * @param profeP tendrá varlor "practico" si se quieren los profesores que imparten práctica
	 * @param profeT tendrá varlor "teorico" si se quieren los profesores que imparten teoria
	 */
	public void rellenarProfesores(String profeP, String profeT) {
		gestionPersonal.rellenarProfesores(profeP, profeT);	
	}
	
	/**
	 * Método encargado de pedir a gestionAlumnos el listado de alumnos matriculados y disponibles
	 */
	public void historialAlumnos() {
		gestionAlumnos.historialAlumnos();
	}
	
	// Personal
	/**
	 * Método encargado de pedir a gestionPersonal la inserción de un nuevo personal.
	 * @param Personal el personal a dar de alta
	 */
	public void altaPersonal(Personal personal) {	
		gestionPersonal.altaPersonal(personal);
	}
	
	/**
	 * Método encargado de pedir a gestionPersonal la baja de un personal.
	 * @param String el DNI del personal a dar de baja
	 */
	public void bajaPersonal(String DNI) {
		gestionPersonal.bajaPersonal(DNI);
	}
	
	/**
	 * Método encargado de pedir a gestionPersonal la búsqueda de un personal.
	 * @param DNI El DNI del personal a buscar
	 * @return el personal
	 */
	public Personal buscarPersonal(String DNI) {
		return gestionPersonal.buscarPersonal(DNI);
	}
	
	/**
	 * Método encargado de pedir a gestionPersonal la modificación de un personal.
	 * @param Personal el personal modificado
	 */
	public void modificarPersonal(Personal personal) {
		gestionPersonal.modificarPersonal(personal);
	}
	
	/**
	 * Método encargado de pedir a gestionPersonal el listado de personal para mostrar la
	 * información
	 */
	public void informacionPersonal() {
		gestionPersonal.informacionPersonal();
	}

	/**
	 * Método encargado de pedir a gestionPersonal los vehiculos disponibles para poder asignarle
	 * a un profesor
	 */
	public void rellenarMatriculas() {
		gestionVehiculos.rellenarMatriculas();
	}
	
	// Vehiculos
	/**
	 * Método encargado de pedir a gestionVehiculos la inserción de un nuevo vehículo.
	 * @param Vehiculo el vehículo a dar de alta
	 */
	public void nuevoVehiculo(Vehiculo vehiculo) {
		gestionVehiculos.nuevoVehiculo(vehiculo);
	}

	/**
	 * Método encargado de pedir a gestionVehiculos la baja de un vehículo.
	 * @param String la matrícula del vehículo a dar de baja
	 */
	public void bajaVehiculo(String vehiculo) {
		gestionVehiculos.bajaVehiculo(vehiculo);
	}

	/**
	 * Método encargado de pedir a gestionVehiculos la búsqueda de un vehículo.
	 * @param matricula La matrícula del vehículo a buscar.
	 * @return el vehículo
	 */
	public Vehiculo buscarVehiculo(String matricula) {
		return gestionVehiculos.buscarVehiculo(matricula);
	}
	
	/**
	 * Método encargado de pedir a gestionVehiculos la modificación de un vehiculo.
	 * @param Vehiculo el vehiculo modificado
	 */
	public void modificarVehiculo(Vehiculo vehiculo) {
		gestionVehiculos.modificarVehiculo(vehiculo);
	}

	/**
	 * Método encargado de pedir a gestionVehiculos el listado de vehículo para mostrar la
	 * información
	 */
	public void informacionVehiculos() {
		gestionVehiculos.informacionVehiculos();
	}

	
	 // Contabilidad 
	/**
	 * Método encargado de pedir a gestionVehiculos la inserción de un nuevo ejercicio.
	 * @param contabilidad el ejercicio a realizar.
	 */
	public void nuevoEjercicio(Contabilidad contabilidad){ 
		gestionContabilidad.nuevoEjercicio(contabilidad); 
	}
	 
	/**
	 * Método encargado de pedir a gestionVehiculos el listado de ejercicios en un itervalo
	 * indicado por los parámetros
	 * @param fechaIni la fecha inicial del intervalo
	 * @param fechaIni la fecha final del intervalo
	 */
	public void mostrarBalance(String fechaIni, String fechaFin){ 
		gestionContabilidad.mostrarBalance(fechaIni, fechaFin); 
	}
	 
	
	// Reserva
	/**
	 * Método encargado de pedir a gestionReserva la inserción de una nueva reserva.
	 * @param Reserva la reserva a realizar.
	 */
	public void nuevaReserva(Reserva reserva) { 
		gestionReserva.nuevaReserva(reserva); 
	}
	  
	/**
	 * Método encargado de pedir a gestionReserva la anulación de una reserva.
	 * @param String el identificador de la reserva a anular
	 */
	public void anularReserva(int idReserva) { 
		gestionReserva.anularReserva(idReserva); 
	}
	 
	/**
	 * Método encargado de pedir a gestionReserva la modificación de una reserva.
	 * @param Reserva la reserva modificada
	 */
	public void modificarReserva(Reserva reserva) { 
		gestionReserva.modificarReserva(reserva); 
	}
	  
	/**
	 * Método encargado de pedir a gestionReserva las reservas que tenga un alumno o profesor
	 * del dia actual o de todas, según el valor del parámetro.
	 * @param rango con valor "hoy" se mostrarán las reservas del día actual. "todas" se mostrarán
	 * todas las reservas de hoy en adelante
	 */
	public void mostrarMisReservas(String rango) {
		String rol = gestionUsuarios.buscarUsuario(idPersona).getRol();
		gestionReserva.mostrarMisReservas(idPersona, rol, rango);
	}

	/**
	 * Método encargado de pedir al modelo de negocio las reservas que incluyan el profesor y 
	 * alumno indicados, asi como la fecha
	 * @param fechaEscogida la fecha que debe tener la reserva
	 * @param dniAlumno el DNI del alumno que ha realizado la reserva.
	 * @param dniProfesor el DNI del profesor que está en la reserva
	 */
	public void mostrarReservas(String fechaEscogida, String dniAlumno ,String dniProfesor) {
		gestionReserva.mostrarReservas(fechaEscogida, dniAlumno , dniProfesor);
	}

	/**
	 * Método encargado que filtrar una lista de reservas para dejar aquellas que pertenezca
	 * a una persona.
	 * @param reservas la lista de reservas a filtrar
	 * @param dniPersona el DNI de la persona a filtrar
	 * @param persona el tipo de persona: "alumno" o "profesor"
	 */
	public void filtrarReservas(ArrayList<Reserva> reservas, String dniPersona, String persona) {
		gestionReserva.filtrarReservas(reservas, dniPersona, persona);
	}

	//Observadores
	/**
	 * Registra un observador a gestionUsuarios.
	 * @param observer El observador que se quiere registrar
	 */
	public void addUsuariosObserver(UsuariosObserver observer) {
		this.gestionUsuarios.addUsuariosObserver(observer);
	}

	/**
	 * Registra un observador a gestionAlumnos.
	 * @param observer El observador que se quiere registrar.
	 */
	public void addAlumnosObserver(AlumnosObserver observer) {
		this.gestionAlumnos.addAlumnosObserver(observer);
	}

	/**
	 * Registra un observador a gestionPersonal.
	 * @param observer El observador que se quiere registrar
	 */
	public void addPersonalObserver(PersonalObserver observer) {
		this.gestionPersonal.addPersonalObserver(observer);
	}

	/**
	 * Registra un observador a gestionVehiculos.
	 * @param observer El observador que se quiere registrar
	 */
	public void addVehiculosObserver(VehiculosObserver observer) {
		this.gestionVehiculos.addVehiculosObserver(observer);
	}
	
	/**
	 * Registra un observador a gestionReservas.
	 * @param observer El observador que se quiere registrar
	 */
	public void addReservasObserver(ReservasObserver observer) {
		gestionReserva.addReservasObserver(observer);
	}

	/**
	 * Registra un observador a gestionContabilidad.
	 * @param observer El observador que se quiere registrar
	 */
	public void addContabilidadObserver(ContabilidadObserver observer) {
		gestionContabilidad.addContabilidadObserver(observer);
	}
	/**
	 * Elimina un observador de gestionUsuarios.
	 * @param observer El observador que se quiere eliminar.
	 */
	public void removeUsuariosObserver(UsuariosObserver observer) {
		gestionUsuarios.removeUsuariosObserver(observer);
	}
	
	/**
	 * Elimina un observador de gestionAlumnos.
	 * @param observer El observador que se quiere eliminar.
	 */
	public void removeAlumnosObserver(AlumnosObserver observer) {
		gestionAlumnos.removeAlumnosObserver(observer);
	}
	
	/**
	 * Elimina un observador de gestionPersonal.
	 * @param observer El observador que se quiere eliminar.
	 */
	public void removePersonalObserver(PersonalObserver observer) {
		gestionPersonal.removePersonalObserver(observer);
	}
	
	/**
	 * Elimina un observador de gestionReservas.
	 * @param observer El observador que se quiere eliminar.
	 */
	public void removeReservasObserver(ReservasObserver observer) {
		gestionReserva.removeReservasObserver(observer);
	}
	

	/**
	 * Elimina un observador de gestionContabilidad.
	 * @param observer El observador que se quiere eliminar.
	 */
	public void removeContabilidadObserver(ContabilidadObserver observer) {
		gestionContabilidad.removeContabilidadObserver(observer);
	}

	/**
	 * Elimina un observador de gestionVehiculos.
	 * @param observer El observador que se quiere eliminar.
	 */
	public void removeVehiculosObserver(VehiculosObserver observer) {
		gestionVehiculos.removeVehiculosObserver(observer);
	}

	/**
	 * Elimina todos los observadores de todas las gestiones.
	 */
	public void removeAllObservers() {
		gestionUsuarios.removeAllObservers();
		gestionAlumnos.removeAllObservers();
		gestionVehiculos.removeAllObservers();
		gestionPersonal.removeAllObservers();
		gestionReserva.removeAllObservers();
		gestionContabilidad.removeAllObservers();
	}

	

	
}
