package tools;

import tools.Simbolo.clase_parametro;
import tools.Simbolo.tipo_simbolo;
import tools.Simbolo.tipo_variable;

public class Lista_enlazada {
	
	private class Nodo{
		public Simbolo simbolo;
		public Nodo siguiente;
		
		public Nodo(final Simbolo simbolo, Nodo siguiente){
			this.simbolo=simbolo;
			this.siguiente=siguiente;
		}
	}
	
	private Nodo elPrimero;
	private int numDatos;
	private Nodo iter;
	
	public Lista_enlazada() {
		elPrimero=null;
		numDatos=0;
		iter=null;
	}
	
	public Simbolo primero() {
		if(elPrimero==null) {
			return null;
		}
		else {
			return elPrimero.simbolo;
		}
	}
	
	public void anyadirIzquierda(Simbolo simbolo) {
		this.elPrimero=new Nodo(simbolo, elPrimero);
		numDatos=numDatos+1;
	}
	
	public boolean esVacia() {
		return elPrimero==null;
	}
	
	public void eliminarPrograma() {
		while(elPrimero != null && elPrimero.simbolo.getTipo() == tipo_simbolo.PROGRAMA) {
			elPrimero=elPrimero.siguiente;
		}
		
		if(elPrimero != null) {
			Nodo auxAnterior = elPrimero;
			Nodo aux = elPrimero.siguiente;
			while(aux != null) {
				if(aux.simbolo.getTipo() == tipo_simbolo.PROGRAMA) {
					auxAnterior.siguiente = aux.siguiente;
					aux = aux.siguiente;
				}
				else {
					auxAnterior = aux;
					aux = aux.siguiente;
				}
			}
		}
	}
	
	public void eliminarVariables(int nivel) {
		while(elPrimero != null && elPrimero.simbolo.getTipo() == tipo_simbolo.VARIABLE && elPrimero.simbolo.getNivel() == nivel) {
			elPrimero=elPrimero.siguiente;
		}
		
		if(elPrimero != null) {
			Nodo auxAnterior = elPrimero;
			Nodo aux = elPrimero.siguiente;
			while(aux != null) {
				if(aux.simbolo.getTipo() == tipo_simbolo.VARIABLE && aux.simbolo.getNivel() == nivel) { //Eliminar
					auxAnterior.siguiente = aux.siguiente;
					aux = aux.siguiente;
				}
				else {
					auxAnterior = aux;
					aux = aux.siguiente;
				}
			}
		}
	}
	
	public void ocultarParametros(int nivel) {
		Nodo aux = elPrimero;
		
		while(aux != null) {
			if(aux.simbolo.getTipo() == tipo_simbolo.PARAMETRO && aux.simbolo.getNivel() == nivel) {
				aux.simbolo.setVisible(false);
			}
			aux = aux.siguiente;
		}
	}
	
	public void eliminarParametrosOcultos(int nivel) {
		while(elPrimero != null && elPrimero.simbolo.getTipo() == tipo_simbolo.PARAMETRO && 
								   elPrimero.simbolo.getNivel() == nivel && !elPrimero.simbolo.getVisible()) {
			elPrimero=elPrimero.siguiente;
		}
		
		if(elPrimero != null) {
			Nodo auxAnterior = elPrimero;
			Nodo aux = elPrimero.siguiente;
			while(aux != null) {
				if(aux.simbolo.getTipo() == tipo_simbolo.PARAMETRO && aux.simbolo.getNivel() == nivel
																   && !aux.simbolo.getVisible()) { //Eliminar
					auxAnterior.siguiente = aux.siguiente;
					aux = aux.siguiente;
				}
				else {
					auxAnterior = aux;
					aux = aux.siguiente;
				}
			}
		}
	}
	
	public void eliminarAcciones(int nivel) {
		while(elPrimero != null && elPrimero.simbolo.getTipo() == tipo_simbolo.ACCION && elPrimero.simbolo.getNivel() == nivel) {
			elPrimero=elPrimero.siguiente;
		}
		
		if(elPrimero != null) {
			Nodo auxAnterior = elPrimero;
			Nodo aux = elPrimero.siguiente;
			while(aux != null) {
				if(aux.simbolo.getTipo() == tipo_simbolo.ACCION && aux.simbolo.getNivel() == nivel) { //Eliminar
					auxAnterior.siguiente = aux.siguiente;
					aux = aux.siguiente;
				}
				else {
					auxAnterior = aux;
					aux = aux.siguiente;
				}
			}
		}
	}
	
	/////////////////////////////////////// FUNCIONES ITERADOR //////////////////////////////////////////
	public void iniciarIterador() {
		iter=elPrimero;
	}
	
	public boolean existeSiguiente() {
		return iter!=null;
	}
	
	//Si existe siguiente devuelve el simbolo resultante, si no existe devuelve null
	public Simbolo siguiente() {
		if(existeSiguiente()) {
			Simbolo res=iter.simbolo;
			iter=iter.siguiente;
			return res;
		}
		else {
			return null;
		}
	}
}
