package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Clase general que servirá de contenedor para el modelo de dominio, que contiene las operaciones básicas
 * que se podrán realizar, como inserciones, borrado o búsqueda.
 * @author David Bolaños
 */
public class DAO<T extends Comparable<T>> implements Collection<T>{
	private List<T> contenedor;
	
	/**
	 * Crea un nuevo contenedor.
	 */
	public DAO(){
		contenedor = new ArrayList<T>();
	}

	/**
	 * Devuelve el elemento i-ésimo del contenedor.
	 * @param i posicion del elemento a buscar.
	 * @return El elemento de la posición i. Es null si no hay nada.
	 */
	public T cogerElemento(int i){
		if(i > contenedor.size())
			return null;
		else 
			return contenedor.get(i);
	}
	
	/**
	 * Buscar en el contenedor el elemento pasado por parámetro.
	 * @param T elem El elemento a obtener.
	 * @return El elemento del contenedor. Es null si no está.
	 */
	public T cogerElemento (T elem){
		int i = Collections.binarySearch(contenedor, elem);
		if (i >= 0)
			return contenedor.get(i);
		else
			return null;
	}

	/**
	 * Añade un nuevo elemento a la lista.
	 * @return true Si el elemento se ha añadido con éxito. False en caso contrario (ya existe).
	 */
	@Override
	public boolean add(T elem) {
		boolean anadido = false;
		if (!contains(elem)){
			int pos = Collections.binarySearch(contenedor, elem);
			pos = (pos+1) * -1;
			
			if(pos == contenedor.size())
				contenedor.add(elem);		
			else	
				contenedor.add(pos, elem);
			
			anadido = true;
		}

		return anadido;
	}
	
	/**
	 * Añade un nuevo elemento a la lista sin tener en cuenta si ya existe.
	 * * @return true Si el elemento se ha añadido con éxito. False en caso contrario.
	 */
	public boolean addWinthoutRepeating(T elem) {
		return contenedor.add(elem);		
	}
	
	/**
	 * Elimina del contenedor el elemento introducido por parámetro.
	 * @param elem Elemento a eliminar.
	 * @return true si el elemento se ha eliminado con éxito. False en caso contrario.
	 */
	public boolean eliminarElemento(T elem){
		boolean ret = false;
		int pos = Collections.binarySearch(contenedor, elem);
		
		if (pos >= 0){
			contenedor.remove(pos);
			ret = true;
		}
		
		return ret;
	}	
	
	@Override
	/**
	 * Este método no está permitido en esta aplicación.
	 */
	public boolean addAll(Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Elimina todos los elementos de la colección.
	 */
	@Override
	public void clear() {
		contenedor.clear();	
	}

	/**
	 * Método que dice si existe en el contenedor el elemento introducido por parámetro. 
	 * @return true si existe el elemento en el contenedor. False en caso contrario.
	 */
	@Override
	public boolean contains(Object object) {
		return contenedor.contains(object);
	}

	/**
	 * Método que añade una colección entera de elementos al contenedor.
	 * @return true si la operación tuvo éxito. False en caso contrario.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return contenedor.containsAll(c);
	}

	/**
	 * Método que dice si el contenedor esta vacio.
	 * @return true si el contenedor esta vacio. False en caso contrario.
	 */
	@Override
	public boolean isEmpty() {
		return contenedor.isEmpty();
	}

	/**
	 * Devuelve el iterador del contenedor.
	 * @return el iterador.
	 */
	@Override
	public Iterator<T> iterator() {
		return contenedor.iterator();
	}

	/**
	 * Método que elimina el elemento introducido por parámetro del contenedor.
	 * @return true si se ha eliminado del contenedor. False en caso contrario.
	 */
	@Override
	public boolean remove(Object o) {
		return contenedor.remove(o);
	}

	/**
	 * Método que elimina la colección introducida por parámetro del contenedor.
	 * @return true si se elimina con éxito. False en caso contrario.
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return contenedor.removeAll(c);
	}

	/**
	 * Elimina los elementos del contenedor que no esta dentro de la colección pasada
	 * por parámetro.
	 * @return true si la operación se realizó con éxito. False en caso contrario.
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return contenedor.retainAll(c);
	}

	@Override
	/**
	 * Método que devuelve el tamaño del contenedor.
	 * @return el tamañoo del contenedor.
	 */
	public int size() {
		return contenedor.size();
	}

	@Override
	/**
	 * Método que devuelve el contenedor en forma de array.
	 * @return El array con los elementos.
	 */
	public Object[] toArray() {
		return contenedor.toArray();
	}

	/**
	 * Método que devuelve el contenedor en forma de array.
	 * @return El array con los elementos.
	 */
	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {
		return contenedor.toArray(a);
	}

	/**
	 * Método que devuelve el contenedor en forma de lista
	 * @return La lista con los elementos.
	 */
	public ArrayList<T> listarElementos() {
		ArrayList<T> listElems = new ArrayList<T>();
		if(!contenedor.isEmpty()){
			listElems.addAll(contenedor);
		}
		return listElems;
	}	
}
