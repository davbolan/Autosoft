package controlador;

import java.util.ArrayList;

import GUI.observables.*;
import modelo.dominio.gestion.*;
import modelo.dominio.personas.*;
import modelo.dominio.usuarios.Usuario;
import modelo.dominio.vehiculo.Vehiculo;
import modelo.negocio.Autoescuela;

/**
 * Clase encargada de mediar entre el modelo y la vista, que contiene todas aquellas operaciones que 
 * se puedan realizar sobre el modelo.
 * @author David Bolaños
 */
public class Controlador {
	private Autoescuela autoescuela;
	
	/**
	 * Crea un controlador con un modelo en el interior.
	 * @param autoescuela
	 */
	public Controlador(Autoescuela autoescuela){
		this.autoescuela = autoescuela;
	}
	
	// Observadores
	/**
	 * Registra un observador a gestionUsuarios del modelo de negocio.
	 * @param observer El observador que se quiere registrar
	 */
	public void addUsuariosObserver(UsuariosObserver observer) {
		this.autoescuela.addUsuariosObserver(observer);
	}

	/**
	 * Registra un observador a gestionAlumnos del modelo de negocio.
	 * @param observer El observador que se quiere registrar
	 */
	public void addAlumnosObserver(AlumnosObserver observer) {
		this.autoescuela.addAlumnosObserver(observer);
	}

	/**
	 * Registra un observador a gestionPersonal del modelo de negocio.
	 * @param observer El observador que se quiere registrar
	 */
	public void addPersonalObserver(PersonalObserver observer) {
		this.autoescuela.addPersonalObserver(observer);
	}
	
	/**
	 * Registra un observador a gestionReservas del modelo de negocio.
	 * @param observer El observador que se quiere registrar
	 */
	public void addReservasObserver(ReservasObserver observer) {
		autoescuela.addReservasObserver(observer);
	}

	/**
	 * Registra un observador a gestionContabilidad del modelo de negocio.
	 * @param observer El observador que se quiere registrar
	 */
	public void addContabilidadObserver(ContabilidadObserver observer) {
		autoescuela.addContabilidadObserver(observer);
	}

	/**
	 * Registra un observador a gestionVehiculos del modelo de negocio.
	 * @param observer El observador que se quiere registrar
	 */
	public void addVehiculosObserver(VehiculosObserver observer) {
		this.autoescuela.addVehiculosObserver(observer);
	}
	
	/**
	 * Elimina un observador de gestionUsuarios del modelo de negocio.
	 * @param observer El observador que se quiere eliminar.
	 */
	public void removeUsuariosObserver(UsuariosObserver observer) {
		autoescuela.removeUsuariosObserver(observer);
	}
	
	/**
	 * Elimina un observador de gestionAlumnos del modelo de negocio.
	 * @param observer El observador que se quiere eliminar.
	 */
	public void removeAlumnosObserver(AlumnosObserver observer) {
		autoescuela.removeAlumnosObserver(observer);
	}
	
	/**
	 * Elimina un observador de gestionPersonal del modelo de negocio.
	 * @param observer El observador que se quiere eliminar.
	 */
	public void removePersonalObserver(PersonalObserver observer) {
		autoescuela.removePersonalObserver(observer);
	}
	
	/**
	 * Elimina un observador de gestionReservas del modelo de negocio.
	 * @param observer El observador que se quiere eliminar.
	 */
	public void removeReservasObserver(ReservasObserver observer) {
		autoescuela.removeReservasObserver(observer);
	}

	/**
	 * Elimina un observador de gestionContabilidad del modelo de negocio.
	 * @param observer El observador que se quiere eliminar.
	 */
	public void removeContabilidadObserver(ContabilidadObserver observer) {
		autoescuela.removeContabilidadObserver(observer);
	}

	/**
	 * Elimina un observador de gestionVehiculos del modelo de negocio.
	 * @param observer El observador que se quiere eliminar.
	 */
	public void removeVehiculosObserver(VehiculosObserver observer) {
		this.autoescuela.removeVehiculosObserver(observer);
	}
	
	/**
	 * Elimina todos los observadores del modelo de negocio.
	 */
	public void removeAllObservers() {
		autoescuela.removeAllObservers();
	}
	
	// Datos
	/**
	 * Método encargada de pedir al modelo de negocio el volcado de los datos en ficheros.
	 */
	public void escribirDatos() {
		autoescuela.escribirDatos();
	}
	
	// Usuarios
	/**
	 * Método encargado de pedir al modelo de negocio la búsqueda de un usuario.
	 * @param DNI El DNI del usuario a buscar.
	 * @return el usuario
	 */
	public Usuario buscarUsuario(String DNI) {
		return autoescuela.buscarUsuario(DNI);
	}

	/**
	 * Método encargado de pedir al modelo de negocio el login de un usuario.
	 * @param DNI El usuario a loguear.
	 */
	public void login(Usuario usuario) throws Exception {
		autoescuela.login(usuario);
	}

	/**
	 * Método encargado de pedir al modelo de negocio la inserción de un nuevo usuario.
	 * @param Usuario el usuario a dar de alta
	 */
	public void nuevoUsuario(Usuario usuario) {
		autoescuela.nuevoUsuario(usuario);
	}

	/**
	 * Método encargado de pedir al modelo de negocio la baja de un usuario.
	 * @param String el DNI del usuario a dar de baja
	 */
	public void bajaUsuario(String DNI) {
		autoescuela.bajaUsuario(DNI);
	}

	/**
	 * Método encargado de pedir al modelo de negocio la modificación de un vehiculo.
	 * @param Usuario el usuario modificado
	 */
	public void modificarUsuario(Usuario usuario) {
		autoescuela.modificarUsuario(usuario);
	}

	// Alumnos
	/**
	 * Método encargado de pedir al modelo de negocio la inserción de un nuevo alumno.
	 * @param Alumno el alumno a dar de alta
	 */
	public void altaAlumno(Alumno alumno) {
		autoescuela.altaAlumno(alumno);
	}

	/**
	 * Método encargado de pedir al modelo de negocio la búsqueda de un usuario.
	 * @param DNI El DNI del alumno a buscar.
	 * @return el alumno
	 */
	public Alumno buscarAlumno(String DNI) {
		return autoescuela.buscarAlumno(DNI);
	}
	
	/**
	 * Método encargado de pedir al modelo de negocio la baja de un alumno.
	 * @param String el DNI del alumno a dar de baja
	 */
	public void bajaAlumno(String DNI) {
		autoescuela.bajaAlumno(DNI);
	}

	/**
	 * Método encargado de pedir al modelo de negocio la modificación de un alumno.
	 * @param Alumno el alumno modificado
	 */
	public void modificarAlumno(Alumno alumno) {
		autoescuela.modificarAlumno(alumno);
	}

	/**
	 * Método encargado de pedir al modelo de negocio los profesores del tipo indicados por
	 * los parámetros y que estén disponibles, para poder aisgnarselo a un alumno
	 * @param profeP tendrá varlor "practico" si se quieren los profesores que imparten práctica
	 * @param profeT tendrá varlor "teorico" si se quieren los profesores que imparten teoria
	 */
	public void rellenarProfesor(String profeP, String profeT) {
		autoescuela.rellenarProfesores(profeP, profeT);	
	}
	
	/**
	 * Método encargado de pedir al modelo de negocio el listado de alumnos matriculados y disponibles
	 */
	public void historialAlumnos() {
		autoescuela.historialAlumnos();
	}
	
	// Personal
	/**
	 * Método encargado de pedir al modelo de negocio la inserción de un nuevo personal.
	 * @param Personal el personal a dar de alta
	 */
	public void altaPersonal(Personal personal) {
		autoescuela.altaPersonal(personal);
	}

	/**
	 * Método encargado de pedir al modelo de negocio la baja de un personal.
	 * @param String el DNI del personal a dar de baja
	 */
	public void bajaPersonal(String DNI) {
		autoescuela.bajaPersonal(DNI);
	}

	/**
	 * Método encargado de pedir al modelo de negocio la búsqueda de un personal.
	 * @param DNI El DNI del personal a buscar
	 * @return el personal
	 */
	public Personal buscarPersonal(String DNI) {
		return autoescuela.buscarPersonal(DNI);
	}
	
	/**
	 * Método encargado de pedir al modelo de negocio la modificación de un personal.
	 * @param Personal el personal modificado
	 */
	public void modificarPersonal(Personal personal) {
		autoescuela.modificarPersonal(personal);
	}

	/**
	 * Método encargado de pedir al modelo de negocio el listado de personal para mostrar la
	 * información
	 */
	public void informacionPersonal() {
		autoescuela.informacionPersonal();
	}

	/**
	 * Método encargado de pedir al modelo de negocio los vehiculos disponibles para poder asignarle
	 * a un profesor
	 */
	public void rellenarMatriculas() {
		autoescuela.rellenarMatriculas();
	}
	
	// Vehiculos
	/**
	 * Método encargado de pedir al modelo de negocio la inserción de un nuevo vehículo.
	 * @param Vehiculo el vehículo a dar de alta
	 */
	public void nuevoVehiculo(Vehiculo vehiculo) {
		autoescuela.nuevoVehiculo(vehiculo);
	}

	/**
	 * Método encargado de pedir al modelo de negocio la baja de un vehículo.
	 * @param String la matrícula del vehículo a dar de baja
	 */
	public void bajaVehiculo(String vehiculo) {
		autoescuela.bajaVehiculo(vehiculo);
	}

	/**
	 * Método encargado de pedir al modelo de negocio la búsqueda de un vehículo.
	 * @param matricula La matrícula del vehículo a buscar.
	 * @return el vehículo
	 */
	public Vehiculo buscarVehiculo(String matricula) {
		return autoescuela.buscarVehiculo(matricula);
	}
	
	/**
	 * Método encargado de pedir al modelo de negocio la modificación de un vehiculo.
	 * @param Vehiculo el vehiculo modificado
	 */
	public void modificarVehiculo(Vehiculo vehiculo) {
		autoescuela.modificarVehiculo(vehiculo);
	}

	/**
	 * Método encargado de pedir al modelo de negocio el listado de vehículo para mostrar la
	 * información
	 */
	public void informacionVehiculos() {
		autoescuela.informacionVehiculos();
	}
	
	// Contabilidad 
	/**
	 * Método encargado de pedir al modelo de negocio la inserción de un nuevo ejercicio.
	 * @param contabilidad el ejercicio a realizar.
	 */
	public void nuevoEjercicio(Contabilidad contabilidad){ 
		autoescuela.nuevoEjercicio(contabilidad); 
	}
	 
	/**
	 * Método encargado de pedir al modelo de negocio el listado de ejercicios en un itervalo
	 * indicado por los parámetros
	 * @param fechaIni la fecha inicial del intervalo
	 * @param fechaIni la fecha final del intervalo
	 */
	public void mostrarBalance(String fechaIni, String fechaFin){ 
		autoescuela.mostrarBalance(fechaIni, fechaFin); 
	}
	 
	
	//Reservas
	/**
	 * Método encargado de pedir al modelo de negocio la inserción de una nueva reserva.
	 * @param Reserva la reserva a realizar.
	 */
	public void nuevaReserva(Reserva reserva) { 
		autoescuela.nuevaReserva(reserva); 
	}
	  
	/**
	 * Método encargado de pedir al modelo de negocio la anulación de una reserva.
	 * @param String el identificador de la reserva a anular
	 */
	public void anularReserva(int idReserva) { 
		autoescuela.anularReserva(idReserva); 
	}
	 
	/**
	 * Método encargado de pedir al modelo de negocio la modificación de una reserva.
	 * @param Reserva la reserva modificada
	 */
	public void modificarReserva(Reserva reserva) { 
		autoescuela.modificarReserva(reserva); 
	}
	  
	/**
	 * Método encargado de pedir al modelo de negocio las reservas que tenga un alumno o profesor
	 * del dia actual o de todas, según el valor del parámetro.
	  * @param rango con valor "hoy" se mostrarán las reservas del día actual. "todas" se mostrarán
	 * todas las reservas de hoy en adelante
	 */
	public void mostrarMisReservas(String rango) { 
		autoescuela.mostrarMisReservas(rango); 
	}

	/**
	 * Método encargado de pedir al modelo de negocio las reservas que incluyan el profesor y 
	 * alumno indicados, asi como la fecha
	 * @param fechaEscogida la fecha que debe tener la reserva
	 * @param dniAlumno el DNI del alumno que ha realizado la reserva.
	 * @param dniProfesor el DNI del profesor que está en la reserva
	 */
	public void mostrarReservas(String fechaEscogida, String dniAlumno ,String dniProfesor) {
		autoescuela.mostrarReservas(fechaEscogida, dniAlumno , dniProfesor);
	}

	/**
	 * Método encargado de pedir al modelo de negocio que filtre una lista de reservas para dejar aquellas que pertenezca
	 * a una persona.
	 * @param reservas la lista de reservas a filtrar
	 * @param dniPersona el DNI de la persona a filtrar
	 * @param persona el tipo de persona: "alumno" o "profesor"
	 */
	public void filtrarReservas(ArrayList<Reserva> reservas, String dniPersona, String persona) {
		autoescuela.filtrarReservas(reservas, dniPersona, persona);
	}
}
