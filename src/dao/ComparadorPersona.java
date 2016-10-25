package dao;

import java.util.Comparator;

import modelo.dominio.personas.Persona;

/**
 * Clase que implementa un nuevo comparador para persona.
 * @author David Bolanios
 */
public class ComparadorPersona implements Comparator<Persona>{

	/**
	 * Este método llama al compareTo alternativo de Persona
	 * @param el resultado de la comparación
	 */
	@Override
	public int compare(Persona persona1, Persona persona2) {
		return persona1.miCompareTo(persona2);	
	}

}
