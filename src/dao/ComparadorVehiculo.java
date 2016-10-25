package dao;

import java.util.Comparator;

import modelo.dominio.vehiculo.Vehiculo;

/**
 * Clase que implementa un nuevo comparador para vehículo.
 * @author David Bolanios
 */
public class ComparadorVehiculo implements Comparator<Vehiculo>{

	/**
	 * Este método llama al compareTo alternativo de Vehiculo
	 * @param el resultado de la comparación
	 */
	@Override
	public int compare(Vehiculo vehiculo1, Vehiculo vehiculo2) {
		return vehiculo1.miCompareTo(vehiculo2);
	}
}
