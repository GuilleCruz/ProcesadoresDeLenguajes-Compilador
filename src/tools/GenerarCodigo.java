package tools;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class GenerarCodigo {
	
	private PrintWriter writer=null;
	private File fichero=null;
	private int numEtiqueta;
	
	public GenerarCodigo(String nombre) throws FileNotFoundException {
		writer = new PrintWriter(nombre+".code");
		fichero = new File(nombre+".code");
		numEtiqueta=0;
	}
	
	public void escribir(String msg) {
		writer.println(msg);
	}
	
	public void cerrar() {
		writer.close();
	}
	
	public String nueva_etiqueta() {
		return "L" + numEtiqueta++;
	}
	
	public void eliminarFichero() {
		boolean exito = fichero.delete();
		if(!exito) {
			System.out.println("fichero de extensión .code no borrado con éxito.");
		}
	}
}
