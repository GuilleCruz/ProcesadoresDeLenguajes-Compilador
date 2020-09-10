package excepciones;

public class YaExisteSimboloException extends Exception{
	public String msg;
	
	private static final long serialVersionUID = 1L;
	
	public YaExisteSimboloException(String msg) {
		super();
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
}
