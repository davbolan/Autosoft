package utilidades;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * Clase que contiene métodos que facilitan las operaciones en el modelo de negocio asi como en la GUI
 * @author David Bolanios
 */
public class Utilidades {
	

	/**
	 * Método que devuelve la fecha actual en formato String (dd/MM/aaaa)
	 * @return
	 */
	public static String fechaHoy(){
		String fecha;
		Calendar calendario = Calendar.getInstance();
		
		int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        
        String mesStr = mes+"";
        if(mes < 10)
        	mesStr = "0" + mes;
		
        fecha = dia + "/" + mesStr + "/" + anio;
		
		return fecha;
		
	}
	
	/**
	 * Método que comprueba que una cadena es un número entero positivo
	 * @param string la cadena  a comprobar
	 * @return true si la cadena es un número entero positivo. False en caso contrario
	 */
	public static boolean isPositiveNumber(String string) {
		boolean is = (!(string == null || string.isEmpty()));
			
	    if(is){ 
	    	int i = 0;
		    while(is && i < string.length()) {
		    	is = Character.isDigit(string.charAt(i));	        
		    	i++;
		    }
	    }
	    
	    return is;
	}
	
	/**
	 * Comprueba si un string es un double positivo.
	 * @return false si se cumple alguno de estos casos. <br>
	 * - La cadena es vacia.<br>
	 * - La cadena empieza o por termina por el caracter '.' Ej: ".434" o "434."<br>
	 * - La cadena contiene mas de un caracter '.'<br>
	 * - La cadena contiene un caracter distinto de un digito o de '.'<br>
	 * - Se generan mas de dos subcadenas al separar por '.'<br>
	 * - Si alguna de las subcadenas no es un numero positivo.<br>
	 * true en otro caso.
	 */
	public static boolean isDouble(String string) {
		if(string == null || string.isEmpty())
			return false;

		StringBuilder stringB = new StringBuilder(string);
	   
		if(string.startsWith(".") || 
		  (stringB.indexOf(".") != stringB.lastIndexOf(".")) || 
		  (stringB.lastIndexOf(".") == stringB.length() -1))
	    	return false;
	    	
	    String[] array = new String[2];

	    if(stringB.indexOf(".") != -1)
	    	array = StringUtils.split(string, '.');    
	    else
	    	array = new String[]{string};
	    	
	   	if(array.length == 1)					
	   		return isPositiveNumber(array[0]);
	   	
	   	else if(array.length == 2)
	   		return isPositiveNumber(array[0]) && isPositiveNumber(array[1]);
	   	
	   	else
	   		return false;
	}

	/**
	 * Método que comprueba si una fecha es correcta, esto es, que cumpla el formato dd/MM/aaaa
	 * y que sea posterior a la fecha actual.
	 * @param fecha la fecha a comprobar
	 * @return true si es correcta, false en caso contrario
	 */
	public static boolean fechaCorrecta(String fecha) {
		String[] arrayFecha = fecha.trim().split("/");
		
		boolean correct = false;
		
		if(arrayFecha.length != 3)
			return false;
		
		if(!Utilidades.isPositiveNumber(arrayFecha[0]) || 
		   !Utilidades.isPositiveNumber(arrayFecha[1]) ||
		   !Utilidades.isPositiveNumber(arrayFecha[2]))
			return false;
		
		Calendar calAc  = Calendar.getInstance();
		int diaAc    = (calAc.get(Calendar.DATE));
		int mesAc    = (calAc.get(Calendar.MONTH) + 1);
		int anioAc   = (calAc.get(Calendar.YEAR));
		
		int diaNac   = Integer.parseInt(arrayFecha[0]);
		int mesNac   = Integer.parseInt(arrayFecha[1]);
		int anioNac  = Integer.parseInt(arrayFecha[2]);
		
		if(anioNac < 1000)
			correct = false;
		else if(anioNac < anioAc)
			correct = true;
		else if(anioNac == anioAc){
			if(mesNac < mesAc)
				correct = true;
			else if(mesNac == mesAc){
				if(diaNac < diaAc)
					correct = true;
				else if(diaNac == diaAc){
					correct = true;
				}
			}
		}
		
		return correct;
	} 
	
	/**
	 * Método que compara dos fechas
	 * @param fechaIni La primera fecha
	 * @param fechaFin La segunda fecha
	 * @return un entero negativo si FechaIni es anterior a FechaFin. 0 si son iguales.
	 * Un entero positivo si FechaIni es porterior a FechaFin
	 */
	public static int compararFecha(String fechaIni, String fechaFin) {		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		
		Date fecha1 = sdf.parse(fechaIni , new ParsePosition(0));  
		Date fecha2 = sdf.parse(fechaFin , new ParsePosition(0));
		 
		int comp = fecha1.compareTo(fecha2);
		return comp;
	}
	
	public static boolean esUnaFecha(String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		Date date = sdf.parse(fecha , new ParsePosition(0));			
		return (date != null);
	} 
	
	/***
     * Convierte la primera letra de cada palabra de una cadena a mayúsculas y las restantes en minúsculas
     * @param textoSinFormato
     * @return String Cadena capitalizada
     */
    public static String capitalize(String textoSinFormato){
        String[] palabras = textoSinFormato.split("\\s+");
        String textoConFormato = "";
        
        for(String palabra : palabras)
        	textoConFormato += StringUtils.capitalize(palabra.toLowerCase() + " ");
        
        return textoConFormato.trim();        
    }
}
