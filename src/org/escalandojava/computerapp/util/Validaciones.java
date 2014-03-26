package org.escalandojava.computerapp.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {
	
	
	
	public static boolean esDatoNumerico(String nro){
		try{
			Integer.parseInt(nro);
		}catch(Exception e){
			return false;
		}
		
		return true;
	}

	public static boolean esDatoFormatoFecha(String text, String string) {
	
		SimpleDateFormat sdf = new SimpleDateFormat(string);
		
		try {
			sdf.parse(text);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static boolean esDatoDecimal(String text) {
		
		try{
			Double.parseDouble(text);
		}catch(Exception e){
			return false;
		}
		
		return true;
	}

	
	public static boolean esCadenaVacia(String texto){
		return texto == null || "".equals(texto);
	}
	

}
