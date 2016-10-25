package modelo.dominio.gestion;

import utilidades.Utilidades;

/**
 * Clase que contiene los datos de una reserva y los métodos auxiliares necesarios.
 * @author Fabricio Isaac Maldonado, David Bolanios
 */
public class Reserva implements Comparable<Reserva>{

	public static int numReserva;
	
	private int idReserva;
	private String idAlumno;
	private String idProfesor;
	private String hora;
	private String fecha;
		
	/**
	 * Crea una reserva a partir de un identificador
	 * @param idReserva
	 */
	public Reserva(int idReserva){
		this.idReserva = idReserva;
	}
	
	/**
	 * Crea una reserva a partir de sus datos, asignándosele automaticamente un identificador
	 */
	public Reserva(String idAlumno, String idProfesor, String hora, String fecha){
		numReserva++;
		this.idReserva 	= numReserva;
		this.idAlumno 	= idAlumno;
		this.idProfesor = idProfesor;
		this.hora 		= hora;
		this.fecha 		= fecha;
	}
	
	/**
	 * Crea una reserva a partir de un identificador y sus datos
	 */
	public Reserva(int idReserva, String idAlumno, String idProfesor, String hora, String fecha){
		this.idReserva 	= idReserva;
		this.idAlumno 	= idAlumno;
		this.idProfesor = idProfesor;
		this.hora 		= hora ;
		this.fecha 		= fecha;
	}
	
	
	/**
	 * Dos reservas son iguales si sin de la misma clase y bien, tiene el mismo id de reserva,
	 * o bien coinciden en la fecha, hora y profesor y alumnos asignados.
	 */
	public boolean equals(Object o){
		return (this.getClass() == o.getClass() && ((idReserva == ((Reserva)o).idReserva))
						
				|| (idAlumno.equalsIgnoreCase(((Reserva)o).idAlumno) 
								&& idProfesor.equalsIgnoreCase(((Reserva)o).idProfesor) 
								&& hora.equalsIgnoreCase(((Reserva)o).hora) 
								&& fecha.equalsIgnoreCase(((Reserva)o).fecha)
							)
			   );
	}
	
	
	/**
	 * Este método implementa el compareTo , para que se puedan comparar dos reservas según sus identificadores.
	 * @param o La reserva con el que comparar.
	 * @return 0 si los identificadores son iguales.
	 */	
	public int compareTo(Reserva reserva) {
		return idReserva - reserva.idReserva;
	}

	/**
	 * Devuelve el identificador de la reserva
	 * @return identificador de la reserva
	 */
	public int getIdReserva() {
		return idReserva;
	}

	/**
	 * Establece el identificador de la reserva
	 * @param identificador de la reserva
	 */
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	/**
	 * Devuelve el DNI del alumno que realizo de la reserva
	 * @return el DNI del alumno que realizo de la reserva
	 */
	public String getIdAlumno() {
		return idAlumno;
	}

	/**
	 * Establece el DNI del alumno que realizo de la reserva
	 * @param el DNI del alumno que realizo de la reserva
	 */
	public void setIdAlumno(String idAlumno) {
		this.idAlumno = idAlumno;
	}

	/**
	 * Devuelve la hora de la reserva
	 * @return la hora de la reserva
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Establece la hora de la reserva
	 * @param la hora de la reserva
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	/**
	 * Devuelve la fecha de la reserva
	 * @return la fecha de la reserva
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Establece la fecha de la reserva
	 * @param la fecha de la reserva
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve el DNI del profesor asignado a la reserva
	 * @return el DNI del profesor asignado a la reserva
	 */
	public String getIdProfesor() {
		return idProfesor;
	}

	/**
	 * Establece el DNI del profesor asignado a la reserva
	 * @param el DNI del profesor asignado a la reserva
	 */
	public void setIdProfesor(String idProfesor) {
		this.idProfesor = idProfesor;
	}
	
	/**
	 * Devuelve todos los datos de la reserva.
	 * @return Todos los datos de la reserva.
	 */
	public String toString() {
		return "Reserva " + idReserva + " " + idAlumno + " " + idProfesor + " " +  hora + " " + fecha;
	}

	/**
	 * Este método implementa un compareTo alternativo, para que se puedan comparar dos reservas según sus fechas,
	 * horas e identificadores.
	 * @param o  reserva con la que comparar.
	 * @return 0 si los identificadores son iguales.
	 */	
	public int miCompareTo(Reserva reserva) {
		int compFecha = Utilidades.compararFecha(fecha, reserva.fecha);
		int compHora  = Integer.parseInt(hora) - Integer.parseInt(reserva.hora);
		
		if(compFecha == 0)
			return compHora;
		else
			return compFecha;
	}

}
