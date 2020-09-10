package tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import compiler.SimpleCharStream;
import tools.Simbolo.clase_parametro;
import tools.Simbolo.tipo_simbolo;
import tools.Simbolo.tipo_variable;
import excepciones.YaExisteSimboloException;
import excepciones.SimboloNoEncontradoException;

public class Tabla_simbolos {
	private final int TABLA_LENGTH = 256;
	private int T[]; //Para funcion hash
	
	private Lista_enlazada tabla[];
	
	public Tabla_simbolos() {
		T=new int[TABLA_LENGTH];
		for (int i=0; i<TABLA_LENGTH; i++) {
			T[i]=i;
		}
		shuffleArray(T);
		
		tabla=new Lista_enlazada[TABLA_LENGTH];
	}
	
	//Para hash
	private void shuffleArray(int[] array) {
		List<Integer> list = new ArrayList<>();
		for (int i : array) {
			list.add(i);
		}

		Collections.shuffle(list);
		
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
	}
	
	//Funcion hash
	private int pearson(String cadena) {
		int h=0;
		for(int i=0; i<cadena.length(); i++) {
			h=T[(h ^ cadena.charAt(i)) % TABLA_LENGTH];	//XOR //La operacion modulo es por si el tamaño de la tabla es menor a 256 elementos
		}
		return h;
	}
	
	
	
	//Crea una tabla de símbolos vacía. Este procedimiento debe invocarse
	//antes de hacer ninguna operación con la tabla de símbolos.
	public void inicializar_tabla(){
		for(int i=0; i<TABLA_LENGTH; i++) {
			tabla[i]=new Lista_enlazada();
		}
	}
	
	//Busca en la tabla el símbolo de mayor nivel cuyo nombre coincida
	//con el del parámetro (se distinguen minúsculas y mayúsculas). Si
	//existe, devuelve un puntero como resultado, de lo contrario devuelve
	//NULL o lanza una excepción (se deja a vuestra elección un mecanismo
	//u otro).
	public Simbolo buscar_simbolo(String nombre) throws SimboloNoEncontradoException {
		Simbolo res=null;
		boolean encontrado = false;
		int indice = pearson(nombre);		
		tabla[indice].iniciarIterador();
		
		//El primero que encontremos sera el de mayor nivel
		while(tabla[indice].existeSiguiente() && !encontrado) {
			res=tabla[indice].siguiente();
			if((res.getNombre()).equals(nombre)) {
				encontrado = true;
			}
		}
		
		if(!encontrado) {
			throw new SimboloNoEncontradoException("ERROR SEMÁNTICO("+SimpleCharStream.getBeginLine()+
					  ", "+SimpleCharStream.getBeginColumn()+"): El nombre " + 
					  nombre + " no ha sido declarado en esta sección");
		}
		
		return res;
	}
	
	//Busca en la tabla el símbolo de mayor nivel cuyo nombre coincida
	//con el del parámetro (se distinguen minúsculas y mayúsculas) y además
	//el tipo de variable coincida con variable. 
	//Si existe, devuelve un puntero como resultado, de lo contrario devuelve
	//NULL o lanza una excepción (se deja a vuestra elección un mecanismo
	//u otro).
	public Simbolo buscar_simbolo(String nombre, tipo_simbolo variable) throws SimboloNoEncontradoException {
		Simbolo res=null;
		boolean encontrado = false;
		int indice = pearson(nombre);		
		tabla[indice].iniciarIterador();
		
		//El primero que encontremos sera el de mayor nivel
		while(tabla[indice].existeSiguiente() && !encontrado) {
			res=tabla[indice].siguiente();
			if((res.getNombre()).equals(nombre) && res.getTipo()==variable) {
				encontrado = true;
			}
		}
		
		if(!encontrado) {
			throw new SimboloNoEncontradoException("ERROR SEMÁNTICO("+SimpleCharStream.getBeginLine()+
					  ", "+SimpleCharStream.getBeginColumn()+"): El nombre " + 
					  nombre + " no ha sido declarado en esta sección");
		}
		
		return res;
	}
	
	//Busca en la tabla el símbolo cuyo nombre coincida
	//con el del parámetro (se distinguen minúsculas y mayúsculas) y además
	//su nivel sea igual o inferior al del parametro nivel (para variables globales).
	//Para no operar con simbolos de un nivel superior, no se puede
	//Si existe, devuelve un puntero como resultado, de lo contrario devuelve
	//NULL o lanza una excepción (se deja a vuestra elección un mecanismo
	//u otro).
	public Simbolo buscar_simbolo(String nombre, int nivel) throws SimboloNoEncontradoException {
		Simbolo res=null;
		boolean encontrado = false;
		int indice = pearson(nombre);
		
		while (nivel>=0 && !encontrado) {
			tabla[indice].iniciarIterador();
			
			while(tabla[indice].existeSiguiente() && !encontrado) {
				res=tabla[indice].siguiente();
				if((res.getNombre()).equals(nombre) && res.getNivel()==nivel) {
					encontrado = true;
				}
			}
			nivel--;
		}
		
		if(!encontrado) {
			throw new SimboloNoEncontradoException("ERROR SEMÁNTICO("+SimpleCharStream.getBeginLine()+
					  ", "+SimpleCharStream.getBeginColumn()+"): El nombre " + 
					  nombre + " no ha sido declarado en esta sección");
		}
		
		return res;
	}
	
	//Introduce en la tabla un simbolo PROGRAMA, con el nombre
	//del parametro, de nivel 0, con la direccion del parametro. Dado que debe
	//ser el primer simbolo a introducir, NO SE VERIFICA QUE EL SIMBOLO YA
	//EXISTA.
	public Simbolo introducir_programa(String nombre, int dir){
		int indice=pearson(nombre);
		Simbolo programa=new Simbolo();
		programa.introducir_programa(nombre, 0, tipo_simbolo.PROGRAMA, dir);
		
		tabla[indice].anyadirIzquierda(programa);
		
		return programa;
	}
	
	//Si existe un símbolo en la tabla del mismo nivel y con el mismo
	//nombre, devuelve NULL (o una excepción, esto se deja a vuestra
	//elección. De lo contrario, introduce un símbolo VARIABLE (simple)
	//con los datos de los argumentos.
	public Simbolo introducir_variable(String nombre, tipo_variable variable, int nivel, int dir) 
									   throws YaExisteSimboloException {
		int indice=pearson(nombre);
		Simbolo var=null;
		
		Simbolo sAux;
		tabla[indice].iniciarIterador();
		while(tabla[indice].existeSiguiente()) {
			sAux=tabla[indice].siguiente();
			if(sAux.getNivel()==nivel &&  (sAux.getNombre()).equals(nombre)) {
				throw new YaExisteSimboloException("ERROR SEMÁNTICO("+SimpleCharStream.getBeginLine()+
										  ", "+SimpleCharStream.getBeginColumn()+"): El nombre " + 
										  nombre + " ya está siendo utilizado y no se puede definir la variable.");
			}
		}
		
		var=new Simbolo();
		var.introducir_variable(nombre, nivel, tipo_simbolo.VARIABLE, variable, dir);
		tabla[indice].anyadirIzquierda(var);
		
		return var;
	}
	
	//Si existe un símbolo en la tabla del mismo nivel y con el mismo
	//nombre, devuelve NULL. De lo contrario, introduce un símbolo
	//ACCION con los datos de los argumentos.
	public Simbolo introducir_accion (String nombre, int nivel, int dir)
									  throws YaExisteSimboloException {
		
		int indice=pearson(nombre);
		Simbolo accion=null;
		
		Simbolo sAux;
		tabla[indice].iniciarIterador();
		while(tabla[indice].existeSiguiente()) {
			sAux=tabla[indice].siguiente();
			if(sAux.getNivel()==nivel && (sAux.getNombre()).equals(nombre)) {
				throw new YaExisteSimboloException("ERROR SEMÁNTICO("+SimpleCharStream.getBeginLine()+
						  ", "+SimpleCharStream.getBeginColumn()+"): El nombre " + 
						  nombre + " ya está siendo utilizado y no se puede definir la accion.");
			}
		}
		
		accion=new Simbolo();
		accion.introducir_accion(nombre, nivel, tipo_simbolo.ACCION, dir);
		tabla[indice].anyadirIzquierda(accion);
		
		return accion;
	}
	
	//Si existe un símbolo en la tabla del mismo nivel y con el mismo
	//nombre, devuelve NULL. De lo contrario, introduce un símbolo
	//PARAMETRO con los datos de los argumentos.
	public Simbolo introducir_parametro (String nombre, tipo_variable variable, clase_parametro parametro, int nivel, int dir)
										 throws YaExisteSimboloException {
		int indice=pearson(nombre);
		Simbolo par=null;
		
		Simbolo sAux;
		tabla[indice].iniciarIterador();
		while(tabla[indice].existeSiguiente()) {
			sAux=tabla[indice].siguiente();
			if(sAux.getNivel()==nivel && (sAux.getNombre()).equals(nombre) && sAux.getVisible()) { //Si es un parametro visible error
				throw new YaExisteSimboloException("ERROR SEMÁNTICO("+SimpleCharStream.getBeginLine()+
						  ", "+SimpleCharStream.getBeginColumn()+"): El nombre " + 
						  nombre + " ya está siendo utilizado y no se puede definir el parámetro.");
			}
		}
		
		par=new Simbolo();
		par.introducir_parametro(nombre, nivel, tipo_simbolo.PARAMETRO, variable, parametro, dir);
		tabla[indice].anyadirIzquierda(par);
		
		return par;
	}
	
	//Elimina de la tabla todos los símbolos PROGRAMA de nivel 0 (debería
	//haber uno solo).
	public void eliminar_programa() {
		for(int i=0; i<TABLA_LENGTH; i++) {
			tabla[i].eliminarPrograma();
		}
	}
	
	//Elimina de la tabla todas las variables que sean del nivel del argumento.
	//NO ELIMINA PARÁMETROS.
	public void eliminar_variables(int nivel) {
		for(int i=0; i<TABLA_LENGTH; i++) {
			tabla[i].eliminarVariables(nivel);
		}
	}
	
	//Marca todos los parámetros de un nivel como ocultos para que no puedan
	//ser encontrados, pero se mantenga la definición completa de la
	//acción donde están declarados para verificación de
	//invocaciones a la acción.
	public void ocultar_parametros(int nivel) {
		for(int i=0; i<TABLA_LENGTH; i++) {
			tabla[i].ocultarParametros(nivel);
		}
	}
	
	//Elimina de la tabla todas los parámetros que hayan sido ocultados
	//previamente. LOS PROCEDIMIENTOS Y FUNCIONES DONDE ESTABAN DECLARADOS
	//DEBEN SER ELIMINAODS TAMBIEN PARA MANTENER LA COHERENCIA DE LA TABLA.
	public void eliminar_parametros_ocultos(int nivel) {
		for(int i=0; i<TABLA_LENGTH; i++) {
			tabla[i].eliminarParametrosOcultos(nivel);
		}
	}
	
	//Elimina de la tabla todas los procedimientos de un nivel.
	//LOS PARAMETROS DE ESTAS ACCIONES
	//DEBEN SER ELIMINADOS TAMBIEN PARA MANTENER LA COHERENCIA DE LA TABLA.
	public void eliminar_acciones(int nivel) {
		for(int i=0; i<TABLA_LENGTH; i++) {
			tabla[i].eliminarAcciones(nivel);
		}
	}
	
	public void mostrarTabla() {
		System.out.println("******************************** TABLA DE SIMBOLOS ********************************");
		Simbolo s = null;
		for (int i=0; i<TABLA_LENGTH; i++) {
			tabla[i].iniciarIterador();
			while(tabla[i].existeSiguiente()) {
				s = tabla[i].siguiente();
				if (s.es_variable()) {
					System.out.println("VARIABLE: " +s.getNombre() +", "+ s.getNivel() +", "+ s.getTipo() + ", " +s.getVariable() + ", " + s.getParametro() + ", " + s.getVisible() + ", " + s.getDir());
					List<Simbolo> lista_parametros = s.getListaParametros();
					if (!lista_parametros.isEmpty()) {
						for (Simbolo ss : lista_parametros) {
							System.out.println("PARAMETRO DE VARIABLE: " +ss.getNombre() +", "+ ss.getNivel() +", "+ ss.getTipo() + ", " +ss.getVariable() + ", " + ss.getParametro() + ", " + ss.getVisible() + ", " + ss.getDir());
						}
					}
					else {
						System.out.println("No parametros en variable.");
					}
				}
				else if (s.es_accion()) {
					System.out.println("ACCION: " +s.getNombre() +", "+ s.getNivel() +", "+ s.getTipo() + ", " +s.getVariable() + ", " + s.getParametro() + ", " + s.getVisible() + ", " + s.getDir());
					List<Simbolo> lista_parametros = s.getListaParametros();
					if (!lista_parametros.isEmpty()) {
						for (Simbolo ss : lista_parametros) {
							System.out.println("PARAMETRO DE ACCION: " +ss.getNombre() +", "+ ss.getNivel() +", "+ ss.getTipo() + ", " +ss.getVariable() + ", " + ss.getParametro() + ", " + ss.getVisible() + ", " + ss.getDir());
						}
					}
					else {
						System.out.println("No parametros en accion.");
					}
				}
				else if (s.es_parametro()) {
					System.out.println("PARAMETRO: " +s.getNombre() +", "+ s.getNivel() +", "+ s.getTipo() + ", " +s.getVariable() + ", " + s.getParametro() + ", " + s.getVisible() + ", " + s.getDir());
					List<Simbolo> lista_parametros = s.getListaParametros();
					if (!lista_parametros.isEmpty()) {
						for (Simbolo ss : lista_parametros) {
							System.out.println("PARAMETRO DE PARAMETRO: " +ss.getNombre() +", "+ ss.getNivel() +", "+ ss.getTipo() + ", " +ss.getVariable() + ", " + ss.getParametro() + ", " + ss.getVisible() + ", " + ss.getDir());
						}
					}
					else {
						System.out.println("No parametros en parametro.");
					}
				}
				else if(s.es_programa()) {
					System.out.println("PROGRAMA: " +s.getNombre() +", "+ s.getNivel() +", "+ s.getTipo() + ", " +s.getVariable() + ", " + s.getParametro() + ", " + s.getVisible() + ", " + s.getDir());
					List<Simbolo> lista_parametros = s.getListaParametros();
					if (!lista_parametros.isEmpty()) {
						for (Simbolo ss : lista_parametros) {
							System.out.println("PARAMETRO DE PARAMETRO: " +ss.getNombre() +", "+ ss.getNivel() +", "+ ss.getTipo() + ", " +ss.getVariable() + ", " + ss.getParametro() + ", " + ss.getVisible() + ", " + ss.getDir());
						}
					}
					else {
						System.out.println("No parametros en programa.");
					}
				}
				else {
					System.out.println(s.getNombre() + " no se ha introducido bien");
				}
			}
		}
		System.out.println("*************************************** FIN ***************************************");
	}

}
