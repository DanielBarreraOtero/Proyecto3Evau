package excepciones;

public class ParámetroInálido extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ParámetroInálido() {
		super("El parámetro introducido no puede ser null.");
	}
	
	public ParámetroInálido(String msg) {
		super(msg);
	}
}
