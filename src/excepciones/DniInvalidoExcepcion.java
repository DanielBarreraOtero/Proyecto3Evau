package excepciones;

public class DniInvalidoExcepcion extends Exception{

	private static final long serialVersionUID = 1L;

	public DniInvalidoExcepcion() {
		super("El Dni Introducido no es v�ido.");
	}
	
	public DniInvalidoExcepcion(String msg) {
		super(msg);
	}
}
