package excepciones;

public class CodigoInvalidoExcepcion extends Exception {

	private static final long serialVersionUID = 1L;

	public CodigoInvalidoExcepcion() {
		super("El C�digo Introducido no es v�ido.");
	}
	
	public CodigoInvalidoExcepcion(String msg) {
		super(msg);
	}
}
