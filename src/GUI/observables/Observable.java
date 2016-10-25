package GUI.observables;

import java.util.ArrayList;
import java.util.Iterator;

public class Observable<T> implements Iterable<T> {

	private ArrayList<T> listObservers;
	
	/**
	 * Constructor con la lista de observadores
	 */
	public Observable() {
		this.listObservers = new ArrayList<T>();
	}
	/**
	 * Agrega un observador.
	 * @param observer El objeto observador
	 */
	public void addObserver(T observer) {
		if (!listObservers.contains(observer))
			listObservers.add(observer);
	}
	/**
	 * Elimina un observador.
	 * @param observer El objeto observador a eliminar.
	 */
	public void removeObserver(T observer) {
		if(listObservers.contains(observer))
			listObservers.remove(observer);
	}

	public void removeAllObservers(){
		listObservers.removeAll(listObservers);
	}
	
	@Override
	public Iterator<T> iterator() {
		return listObservers.iterator();
	}

}
