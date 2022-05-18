package helpers;

import repositorios.OficinaBD;
import clases.Oficina;
import gui.fichas.fichasOficina.FichaOficina;

public class Helpers {

	/**Coge los parametro de la ficha de oficina y llama al metodo de grabaOficina.
	 * 
	 */
	public static void AñadirOficina(FichaOficina padre) {
		String codigo = padre.textCódigo.getText();
		String descrp = padre.textAreaDescrp.getText();
		String provincia = padre.textProvincia.getText();
		String localidad = padre.textLocalidad.getText();
		boolean esAeropuerto = padre.chckbxEsAeropuerto.isSelected();
		
		Oficina o = new Oficina(codigo, descrp, localidad, provincia, esAeropuerto);
		
		OficinaBD.grabaOficina(o);
	}

	
}
