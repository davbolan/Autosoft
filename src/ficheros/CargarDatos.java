package ficheros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

import modelo.dominio.gestion.*;
import modelo.dominio.personas.*;
import modelo.dominio.usuarios.Usuario;
import modelo.dominio.vehiculo.Vehiculo;
import dao.DAO;
import ficheros.excepciones.ExceptionLeerAlumnos;
import ficheros.excepciones.ExceptionLeerContabilidad;
import ficheros.excepciones.ExceptionLeerReservas;
import ficheros.excepciones.ExceptionLeerPersonal;
import ficheros.excepciones.ExceptionLeerUsuarios;
import ficheros.excepciones.ExceptionLeerVehiculos;

/**
 * Clase que contiene todos los métodos para la carga de datos de un fichero
 * @author David Bolanios, Fabricio Isaac Maldonado
 */
public class CargarDatos {
	private static DAO<Usuario> 	 daoUsuarios 	 = new DAO<Usuario>();
	private static DAO<Alumno> 	 	 daoAlumnos		 = new DAO<Alumno>();
	private static DAO<Vehiculo> 	 daoVehiculos	 = new DAO<Vehiculo>();
	private static DAO<Personal> 	 daoPersonal	 = new DAO<Personal>();
	private static DAO<Reserva> 	 daoReservas 	 = new DAO<Reserva>();
	private static DAO<Contabilidad> daoContabilidad = new DAO<Contabilidad>(); 
	
	/**
	 * Constructora que inicializa los DAOs que contendrá el negocio
	 */
	public CargarDatos(){
		daoUsuarios 	= new DAO<Usuario>(); 
		daoAlumnos 		= new DAO<Alumno>(); 
		daoVehiculos 	= new DAO<Vehiculo>(); 
		daoPersonal 	= new DAO<Personal>(); 
		daoReservas 	= new DAO<Reserva>(); 
		daoContabilidad = new DAO<Contabilidad>(); 
	}
	
	/**
	 * Método encargado de leer los usuarios del fichero indicado por parámetro
	 * @param file El archivo a leer los usuarios
	 * @return el dao de Usuarios ya relleno
	 * @throws IOException si se ha producido algún error de lectura
	 * @throws ExceptionLeerUsuarios si hay alguna incongruencia en los usuarios
	 */
	public static DAO<Usuario> leerUsuarios(InputStream file) throws IOException, ExceptionLeerUsuarios{
		BufferedReader buffer = new BufferedReader(new InputStreamReader(file));

		String fileLine = buffer.readLine();
		
		if (fileLine == null || !fileLine.equalsIgnoreCase("EmpiezanUsuarios")) 
			throw new ExceptionLeerUsuarios();
		
		fileLine = buffer.readLine();
		while(!fileLine.equalsIgnoreCase("FinUsuarios")) {
			String[] usuarioLine = fileLine.split(" ");
			if(usuarioLine.length != 4)
				throw new ExceptionLeerUsuarios();
			
			if(usuarioLine[0].equalsIgnoreCase("Usuario"))
				daoUsuarios.add(new Usuario(
						StringUtils.replace(usuarioLine[1], "_", " "), 
						StringUtils.replace(usuarioLine[2], "_", " "), 
						StringUtils.replace(usuarioLine[3], "_", " ")
				));
			else 
				throw new ExceptionLeerUsuarios();
			
			fileLine = buffer.readLine();
		}	
		
		if(buffer != null)
			buffer.close();
		
		return daoUsuarios;
	}
	
	/**
	 * Método encargado de leer los alumnos del fichero indicado por parámetro
	 * @param file El archivo a leer los alumnos
	 * @return el dao de Alumnos ya relleno
	 * @throws IOException si se ha producido algún error de lectura
	 * @throws ExceptionLeerAlumnos si hay alguna incongruencia en los alumnos
	 */
	public static DAO<Alumno> leerAlumnos(InputStream file) throws IOException, ExceptionLeerAlumnos{	
		BufferedReader buffer = new BufferedReader(new InputStreamReader(file));

		String fileLine = buffer.readLine();
		
		if (fileLine==null || !fileLine.equalsIgnoreCase("EmpiezanAlumnos")) 
			throw new ExceptionLeerAlumnos();
		fileLine = buffer.readLine();
		while(!fileLine.equalsIgnoreCase("FinAlumnos")) {
			String [] alumnoLine = fileLine.split(" ");
			
			if(alumnoLine.length != 12)
				throw new ExceptionLeerAlumnos();
			
			if(alumnoLine[0].equalsIgnoreCase("Alumno"))
				daoAlumnos.add(new Alumno(
						StringUtils.replace(alumnoLine[1], "_", " "), 
						StringUtils.replace(alumnoLine[2], "_", " "), 
						StringUtils.replace(alumnoLine[3], "_", " "), 
						StringUtils.replace(alumnoLine[4], "_", " "), 
						Integer.parseInt(alumnoLine[5]),
						StringUtils.replace(alumnoLine[6], "_", " "), 
						StringUtils.replace(alumnoLine[7], "_", " "), 
						StringUtils.replace(alumnoLine[8], "_", " "),
						StringUtils.replace(alumnoLine[9], "_", " "),
						Integer.parseInt(alumnoLine[10]),
						Boolean.parseBoolean(alumnoLine[11])
					));
			else 
				throw new ExceptionLeerAlumnos();
			
			fileLine = buffer.readLine();
		}

		if(buffer != null)
			buffer.close();
		
		return daoAlumnos;
	}

	/**
	 * Método encargado de leer los vehículos del fichero indicado por parámetro
	 * @param file El archivo a leer los vehículos
	 * @return el dao de vehículos ya relleno
	 * @throws IOException si se ha producido algún error de lectura
	 * @throws ExceptionLeerUsuarios si hay alguna incongruencia en los vehículos
	 */
	public static DAO<Vehiculo> leerVehiculos(InputStream file) throws IOException, ExceptionLeerVehiculos{
		BufferedReader buffer = new BufferedReader(new InputStreamReader(file));

		String fileLine = buffer.readLine();

		if (fileLine == null || !fileLine.equalsIgnoreCase("EmpiezanVehiculos"))
			throw new ExceptionLeerVehiculos();
		
		fileLine = buffer.readLine();
		while (!fileLine.equalsIgnoreCase("FinVehiculos")) {
			String[] vehiculoLine = fileLine.split(" ");
			if (vehiculoLine.length != 9) 
				throw new ExceptionLeerVehiculos();

			if (vehiculoLine[0].equalsIgnoreCase("Vehiculo"))
				daoVehiculos.add(new Vehiculo(
						StringUtils.replace(vehiculoLine[1], "_", " "), 
						Integer.parseInt(vehiculoLine[2]), 
						StringUtils.replace(vehiculoLine[3], "_", " "), 
						StringUtils.replace(vehiculoLine[4], "_", " "), 
						StringUtils.replace(vehiculoLine[5], "_", " "), 
						StringUtils.replace(vehiculoLine[6], "_", " "), 
						StringUtils.replace(vehiculoLine[7], "_", " "), 
						Boolean.parseBoolean(vehiculoLine[8]))
				);
			else
				throw new ExceptionLeerVehiculos();

			fileLine = buffer.readLine();
		}

		if(buffer != null)
			buffer.close();
		
		return daoVehiculos;
	}

	/**
	 * Método encargado de leer el personal del fichero indicado por parámetro
	 * @param file El archivo a leer el personal
	 * @return el dao de personal ya relleno
	 * @throws IOException si se ha producido algún error de lectura
	 * @throws ExceptionLeerPersonal si hay alguna incongruencia en el personal
	 */
	public static DAO<Personal> leerPersonal(InputStream file) throws IOException, ExceptionLeerPersonal{

		BufferedReader buffer = new BufferedReader(new InputStreamReader(file));

		String fileLine = buffer.readLine();
		
		if (fileLine == null || !fileLine.equalsIgnoreCase("EmpiezanTrabajadores")) 
			throw new ExceptionLeerPersonal();
		fileLine = buffer.readLine();
		while(!fileLine.equalsIgnoreCase("FinTrabajadores")) {
			String [] trabajadorLine = fileLine.split(" ");
			int length = trabajadorLine.length;
			if( length != 11 && length != 12 && length != 14)
				throw new ExceptionLeerPersonal();
	

			if(trabajadorLine[0].equalsIgnoreCase("Administrador") && trabajadorLine.length == 11)
				daoPersonal.add(new Administrador(
						StringUtils.replace(trabajadorLine[1], "_", " "),
						StringUtils.replace(trabajadorLine[2], "_", " "),
						StringUtils.replace(trabajadorLine[3], "_", " "),
						Integer.parseInt(trabajadorLine[4]),
						Integer.parseInt(trabajadorLine[5]),
						StringUtils.replace(trabajadorLine[6], "_", " "),
						StringUtils.replace(trabajadorLine[7], "_", " "),
						Integer.parseInt(trabajadorLine[8]),
						StringUtils.replace(trabajadorLine[9], "_", " "),
						StringUtils.replace(trabajadorLine[10], "_", " ")
				));
			
			else if (trabajadorLine[0].equalsIgnoreCase("Secretaria") && trabajadorLine.length == 11)
				daoPersonal.add(new Secretaria(
						StringUtils.replace(trabajadorLine[1], "_", " "),
						StringUtils.replace(trabajadorLine[2], "_", " "),
						StringUtils.replace(trabajadorLine[3], "_", " "),
						Integer.parseInt(trabajadorLine[4]),
						Integer.parseInt(trabajadorLine[5]),
						StringUtils.replace(trabajadorLine[6], "_", " "),
						StringUtils.replace(trabajadorLine[7], "_", " "),
						Integer.parseInt(trabajadorLine[8]),
						StringUtils.replace(trabajadorLine[9], "_", " "),
						StringUtils.replace(trabajadorLine[10], "_", " ")
				));
			
			else if (trabajadorLine[0].equalsIgnoreCase("Otros") && trabajadorLine.length == 12)
				daoPersonal.add(new Otros(
						StringUtils.replace(trabajadorLine[1], "_", " "),
						StringUtils.replace(trabajadorLine[2], "_", " "),
						StringUtils.replace(trabajadorLine[3], "_", " "),
						Integer.parseInt(trabajadorLine[4]), 
						Integer.parseInt(trabajadorLine[5]), 
						StringUtils.replace(trabajadorLine[6], "_", " "),
						StringUtils.replace(trabajadorLine[7], "_", " "),
						Integer.parseInt(trabajadorLine[8]), 
						StringUtils.replace(trabajadorLine[9], "_", " "), 
						StringUtils.replace(trabajadorLine[10], "_", " "),
						StringUtils.replace(trabajadorLine[11], "_", " ")
					));
			
			else if (trabajadorLine[0].equalsIgnoreCase("ProfesorTeorico") && trabajadorLine.length == 14)
				daoPersonal.add(new ProfesorTeorico(
						StringUtils.replace(trabajadorLine[1], "_", " "),
						StringUtils.replace(trabajadorLine[2], "_", " "),
						StringUtils.replace(trabajadorLine[3], "_", " "),
						Integer.parseInt(trabajadorLine[4]),
						Integer.parseInt(trabajadorLine[5]), 
						StringUtils.replace(trabajadorLine[6], "_", " "),
						StringUtils.replace(trabajadorLine[7], "_", " "),
						Integer.parseInt(trabajadorLine[8]),
						StringUtils.replace(trabajadorLine[9], "_", " "),
						StringUtils.replace(trabajadorLine[10], "_", " "),
						Boolean.parseBoolean(trabajadorLine[11]),
						StringUtils.replace(trabajadorLine[12], "_", " "),
						Integer.parseInt(trabajadorLine[13])
				));

			else if (trabajadorLine[0].equalsIgnoreCase("ProfesorPractico") && trabajadorLine.length < 15)
				daoPersonal.add(new ProfesorPractico(
						StringUtils.replace(trabajadorLine[1], "_", " "),
						StringUtils.replace(trabajadorLine[2], "_", " "),
						StringUtils.replace(trabajadorLine[3], "_", " "),
						Integer.parseInt(trabajadorLine[4]), 
						Integer.parseInt(trabajadorLine[5]), 
						StringUtils.replace(trabajadorLine[6], "_", " "),
						StringUtils.replace(trabajadorLine[7], "_", " "),
						Integer.parseInt(trabajadorLine[8]), 
						StringUtils.replace(trabajadorLine[9], "_", " "), 
						StringUtils.replace(trabajadorLine[10], "_", " "),
						Boolean.parseBoolean(trabajadorLine[11]), 
						StringUtils.replace(trabajadorLine[12], "_", " "),
						daoVehiculos.cogerElemento(new Vehiculo(StringUtils.replace(trabajadorLine[13], "_", " ")))
				));
			
			else
				throw new ExceptionLeerPersonal();
			
			fileLine = buffer.readLine();
		}	

		if(buffer != null)
			buffer.close();
		
		return daoPersonal;
	}

	/**
	 * Método encargado de leer las reservas del fichero indicado por parámetro
	 * @param file El archivo a leer el reservas
	 * @return el dao de reservas ya relleno
	 * @throws IOException si se ha producido algún error de lectura
	 * @throws ExceptionLeerPersonal si hay alguna incongruencia en las reservas
	 */
	public static DAO<Reserva> leerReservas(InputStream file) throws IOException, ExceptionLeerReservas {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(file));

		String fileLine = buffer.readLine();

		if (fileLine == null || !fileLine.equalsIgnoreCase("EmpiezanReservas"))
			throw new ExceptionLeerReservas();

		fileLine = buffer.readLine();

		while (!fileLine.equalsIgnoreCase("FinReservas")) {
			String[] reservaLine = fileLine.split(" ");

			if (reservaLine.length != 6) 
				throw new ExceptionLeerReservas();

			if (reservaLine[0].equalsIgnoreCase("Reserva"))
				daoReservas.add(new Reserva(
						Integer.parseInt(reservaLine[1]),
						StringUtils.replace(reservaLine[2], "_", " "),
						StringUtils.replace(reservaLine[3], "_", " "),
						StringUtils.replace(reservaLine[4], "_", " "),
						StringUtils.replace(reservaLine[5], "_", " ")
				));
			else
				throw new ExceptionLeerReservas();
			
			fileLine = buffer.readLine();
		}

		if(buffer != null)
			buffer.close();
		if(!daoReservas.isEmpty())
			Reserva.numReserva = daoReservas.cogerElemento(daoReservas.size()-1).getIdReserva();
		return daoReservas;
	}
	
	
	/**
	 * Método encargado de leer la contabilidad del fichero indicado por parámetro
	 * @param file El archivo a leer la contabilidad
	 * @return el dao de reservas ya relleno
	 * @throws IOException si se ha producido algún error de lectura
	 * @throws ExceptionLeerPersonal si hay alguna incongruencia en la contabilidad
	 */
	public static DAO<Contabilidad> leerContabilidad(InputStream file) throws IOException, ExceptionLeerContabilidad {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(file));

		String fileLine = buffer.readLine();

		if (fileLine == null || !fileLine.equalsIgnoreCase("EmpiezanContabilidad"))
			throw new ExceptionLeerContabilidad();

		fileLine = buffer.readLine();

		while (!fileLine.equalsIgnoreCase("FinContabilidad")) {
			String[] reservaLine = fileLine.split(" ");
			if (reservaLine.length != 6) 
				throw new ExceptionLeerContabilidad();

			if (reservaLine[0].equalsIgnoreCase("Contabilidad")){
				daoContabilidad.add(new Contabilidad(
						Integer.parseInt(reservaLine[1]),
						StringUtils.replace(reservaLine[2], "_", " "),
						StringUtils.replace(reservaLine[3], "_", " "),
						Double.parseDouble(reservaLine[4]),
						StringUtils.replace(reservaLine[5], "_", " ")
				));
			}
			else
				throw new ExceptionLeerContabilidad();
			
			fileLine = buffer.readLine();
		}

		if(!daoContabilidad.isEmpty())
			Contabilidad.numEjercicio = daoContabilidad.cogerElemento(daoContabilidad.size()-1).getId();
		
		if(buffer != null)
			buffer.close();
		
		return daoContabilidad;
	}
}
