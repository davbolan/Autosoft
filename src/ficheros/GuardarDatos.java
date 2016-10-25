
package ficheros;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import dao.DAO;

/**
 * Clase encargada del volcado en archivos de los datos
 * @author David Bolanios, Fabricio Isaac Maldonado
 */
public class GuardarDatos {
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * Metodo generico encargado de guardar los datos en ficheros
	 * @param empiezan Palabra por la que debe empezar el fichero
	 * @param fin Palabra por la que debe terminar el fichero
	 * @param file El archivo donde guardar los datos.
	 * @param daoDatos Contenedor con los datos a escribir en el fichero
	 * @throws IOException si hay algun error de escritura
	 */
	public static <T extends Comparable<T>> void escribirEnFichero(String empiezan, String fin, OutputStream file, DAO<T> daoDatos) throws IOException {
		BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(file));
		Iterator<T> iterador = daoDatos.iterator();
		
		buffer.write(empiezan + LINE_SEPARATOR);						// Palabra inicio
		while (iterador.hasNext()) 
			buffer.write(iterador.next().toString() + LINE_SEPARATOR);	// Los datos.
		buffer.write(fin);												// Palabra fin
		
		if (buffer != null)
			buffer.close();
	}

}
