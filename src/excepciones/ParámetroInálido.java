package excepciones;

public class Par�metroIn�lido extends Exception {
	
	private static final long serialVersionUID = 1L;

	public Par�metroIn�lido() {
		super("El par�metro introducido no puede ser null.");
	}
	
	public Par�metroIn�lido(String msg) {
		super(msg);
	}
}
