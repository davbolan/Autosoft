package GUI;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Clase que implementa el tratamiento de imagenes.
 * @author David Bolanios
 */
public class Imagen extends JLabel{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de una imagen a través de su directorio
	 * @param name
	 */
	public Imagen(String name){
		super(createImage(name));
	}
	
	/**
	 * Crea el icono de una imagen a través de su directorio
	 * @param name
	 * @return
	 */
	public static ImageIcon createImage(String name){
		ImageIcon icono = null;
		URL url = GUI.class.getResource("imagenes/" + name);
		if (url != null) {
			icono = new ImageIcon(url);
		}
		return icono;
	}
	
}
