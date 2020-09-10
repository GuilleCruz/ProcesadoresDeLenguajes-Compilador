package tools;

import java.util.ArrayList;
import java.util.List;

public class Simbolo {
	public enum tipo_simbolo { PROGRAMA, VARIABLE, ACCION, PARAMETRO, CONSTANTE, EXPRESION, NULL_S };
	public enum tipo_variable { DESCONOCIDO, ENTERO, BOOLEANO, CHAR, CADENA, NULL_V };
	public enum clase_parametro { VAL, REF, NULL_P };
	
	private String nombre;
	private int nivel;
	private tipo_simbolo tipo;
	private tipo_variable variable;
	private clase_parametro parametro;
	private boolean visible;
	private List<Simbolo> lista_parametros;
	private int dir;
	private int valorStackFrame;
	private String etiqueta;
	
	public Simbolo() {
		nombre="";
		nivel=-1;
		tipo=tipo_simbolo.NULL_S;
		variable=tipo_variable.NULL_V;
		parametro=clase_parametro.NULL_P;
		visible=false;
		lista_parametros=new ArrayList<Simbolo>();
		dir=-1;
		valorStackFrame=-1;
		etiqueta="";
	}
	
	public void introducir_programa(String nombre, int nivel, tipo_simbolo tipo, int dir){
		this.nombre=nombre;
		this.nivel=nivel;
		this.tipo=tipo;
		this.dir=dir;
	}
	
	public void introducir_variable(String nombre, int nivel, tipo_simbolo tipo, tipo_variable variable, int dir){
		this.nombre=nombre;
		this.nivel=nivel;
		this.tipo=tipo;
		this.variable=variable;
		this.dir=dir;
	}
	
	public void introducir_accion(String nombre, int nivel, tipo_simbolo tipo, /*List<Simbolo> lista_parametros,*/ int dir){
		this.nombre=nombre;
		this.nivel=nivel;
		this.tipo=tipo;
		//this.lista_parametros=lista_parametros;
		this.dir=dir;
	}
	
	public void introducir_parametro(String nombre, int nivel, tipo_simbolo tipo, tipo_variable variable, clase_parametro parametro, int dir){
		this.nombre=nombre;
		this.nivel=nivel;
		this.tipo=tipo;
		this.variable=variable;
		this.parametro=parametro;
		this.dir=dir;
		this.visible=true;
	}
	
	public boolean es_variable() {
		return tipo==tipo_simbolo.VARIABLE;
	}
	
	public boolean es_accion() {
		return tipo==tipo_simbolo.ACCION;
	}
	
	public boolean es_parametro() {
		return tipo==tipo_simbolo.PARAMETRO;
	}
	
	public boolean es_valor() {
		return tipo==tipo_simbolo.PARAMETRO && parametro==clase_parametro.VAL;
	}
	
	public boolean es_referencia() {
		return tipo==tipo_simbolo.PARAMETRO && parametro==clase_parametro.REF;
	}
	
	public boolean es_programa() {
		return tipo==tipo_simbolo.PROGRAMA;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel=nivel;
	}
	
	public tipo_simbolo getTipo() {
		return tipo;
	}
	public void setTipo(tipo_simbolo tipo) {
		this.tipo=tipo;
	}
	
	public tipo_variable getVariable() {
		return variable;
	}
	public void setVariable(tipo_variable variable) {
		this.variable=variable;
	}
	
	public clase_parametro getParametro() {
		return parametro;
	}
	public void setParametro(clase_parametro parametro) {
		this.parametro=parametro;
	}
	
	public boolean getVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible=visible;
	}
	
	public List<Simbolo> getListaParametros() {
		return lista_parametros;
	}
	public void setListaParametros(List<Simbolo> lista_parametros) {
		this.lista_parametros.clear();
		for (Simbolo s : lista_parametros) {
			this.lista_parametros.add(s);
		}
	}
	
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir=dir;
	}
	
	public int getValorStackFrame() {
		return valorStackFrame;
	}
	public void setValorStackFrame(int valorStackFrame) {
		this.valorStackFrame=valorStackFrame;
	}
	
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta=etiqueta;
	}
}
