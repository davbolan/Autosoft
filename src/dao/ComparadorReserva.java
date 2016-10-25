package dao;

import java.util.Comparator;

import modelo.dominio.gestion.Reserva;

/**
 * Clase que implementa un nuevo comparador para reserva.
 * @author David Bolanios
 */
public class ComparadorReserva implements Comparator<Reserva>{

	/**
	 * Este método llama al compareTo alternativo de Reserva
	 * @param el resultado de la comparación
	 */
	@Override
	public int compare(Reserva reserva1, Reserva reserva2) {
		return reserva1.miCompareTo(reserva2);
	}
}
