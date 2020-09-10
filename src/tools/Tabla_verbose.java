package tools;

import java.util.Enumeration;
import java.util.Hashtable;

public class Tabla_verbose {
	static private Hashtable<String, Integer> table;
	static private boolean verboseTable;
	
	public Tabla_verbose () {
		table = new Hashtable<String, Integer>();
		verboseTable = false;
	}
	
	static public void actualizar (String token) {
		if(table.containsKey(token)) {
			int apariciones = table.get(token);
			table.put(token, apariciones+1);
		}
		else {
			table.put(token, 1);
		}
	}
	
	static public void setVerboseTable (boolean valor) {
		verboseTable = valor;
	}
	
	static public boolean getVerboseTable (){
		return verboseTable;
	}
	
	static public void mostrar () {
		Enumeration<String> claves = table.keys();
		String aux[] = new String[10];
		int i = 0;
		
		System.out.println("-------------TABLA RESUMEN APARICIONES-------------");
		System.out.println("---------------------------------------------------");
		System.out.println("----------------PALABRAS RESERVADAS----------------");
		System.out.println("-----------VALOR-------------APARICIONES-----------");
		
		while (claves.hasMoreElements()) {
			String claveActual = claves.nextElement();
			if(compararOtroValor(claveActual)) {
				aux[i] = claveActual; i++;
				aux[i] = Integer.toString(table.get(claveActual)); i++;
			}
			else {
				System.out.format("%14s %20s\n", claveActual, table.get(claveActual));
			}
		}
		
		System.out.println("-------------------OTROS VALORES-------------------");
		System.out.println("-----------VALOR-------------APARICIONES-----------");
		int j=0;
		
		while(j<i) {
			System.out.format("%14s %20s\n", aux[j], aux[j+1]);
			j = j+2;
		}
	}
	
	private static boolean compararOtroValor(String clave) {
		boolean  res = false;
		if(clave.equals("valorBooleano")) {
			res = true;
		}
		else if(clave.equals("identificador")) {
			res = true;
		}
		else if(clave.equals("valorEntero")) {
			res = true;
		}
		else if(clave.equals("valorCaracter")) {
			res = true;
		}
		else if(clave.equals("valorCadena")) {
			res = true;
		}
		
		return res;
	}
}
