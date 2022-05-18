package excepciones;

public class CodigoInvalidoExcepcion extends Exception {

	private static final long serialVersionUID = 1L;

	public CodigoInvalidoExcepcion() {
		super("El Código Introducido no es váido.");
	}
	
	public CodigoInvalidoExcepcion(String msg) {
		super(msg);
	}
}
