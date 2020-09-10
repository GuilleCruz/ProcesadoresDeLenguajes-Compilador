package excepciones;

public class SimboloNoEncontradoException extends Exception{
	public String msg;
	
	private static final long serialVersionUID = 1L;
	
	public SimboloNoEncontradoException(String msg) {
		super();
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
}