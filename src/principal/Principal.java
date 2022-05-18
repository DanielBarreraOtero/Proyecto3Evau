package principal;

import gui.VentanaPrincipal;

public class Principal {

	public static void main(String[] args) {
		
		try {
			VentanaPrincipal frame = new VentanaPrincipal();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
