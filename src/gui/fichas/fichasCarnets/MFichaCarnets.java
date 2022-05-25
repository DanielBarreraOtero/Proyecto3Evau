package gui.fichas.fichasCarnets;

import javax.swing.JOptionPane;

import clases.CarnetConducir;
import repositorios.CarnetsBD;

public class MFichaCarnets {

	public static void deshabilitaFicha(FichaCarnets padre) {
		padre.textAreaDescrp.setEnabled(false);
		padre.textAreaDescrp.setText(null);
	}
	
	public static void habilitaFicha(FichaCarnets padre) {
		padre.textAreaDescrp.setEnabled(true);
		rellenaFicha(padre, padre.textCodigo.getText());
	}
	
	
	private static void rellenaFicha(FichaCarnets padre, String codigo) {
		if (CarnetsBD.CarnetExiste(codigo)) {
			CarnetConducir c = null;
			c = CarnetsBD.leerCarnet(codigo);

			padre.textAreaDescrp.setText(c.getDescrp());
		} else {
			padre.textAreaDescrp.setText("");
		}
	}

	public static void estadoBotones(FichaCarnets padre) {
		if(padre.textCodigo.getText().length() > 0 && padre.textCodigo.getText().length() < 5) {
			if (CarnetsBD.CarnetExiste(padre.textCodigo.getText())) {
				padre.btnCrear.setVisible(false);
				padre.btnCrear.setEnabled(false);
				padre.btnModificar.setVisible(true);
				padre.btnModificar.setEnabled(true);
				padre.btnBorrar.setVisible(true);
				padre.btnBorrar.setEnabled(true);
			} else {
				padre.btnCrear.setVisible(true);
				padre.btnCrear.setEnabled(true);
				padre.btnModificar.setVisible(false);
				padre.btnModificar.setEnabled(false);
				padre.btnBorrar.setVisible(false);
				padre.btnBorrar.setEnabled(false);
			}
		}else {
			padre.btnCrear.setVisible(false);
			padre.btnCrear.setEnabled(false);
			padre.btnModificar.setVisible(false);
			padre.btnModificar.setEnabled(false);
			padre.btnBorrar.setVisible(false);
			padre.btnBorrar.setEnabled(false);
		}

	}
	
	public static void calculaEstadoFicha(FichaCarnets padre) {
		if(padre.textCodigo.getText().length() > 0 && padre.textCodigo.getText().length() < 5) {
			habilitaFicha(padre);
		} else {
			deshabilitaFicha(padre);
		}
		estadoBotones(padre);
	}
	
	public static boolean compruebaCarnet(FichaCarnets padre) {
		boolean valido = true;
		
		if (padre.textCodigo.getText().length() < 1  || padre.textCodigo.getText().length() > 4) {
			valido = false;
			JOptionPane.showMessageDialog(padre,"Longitud de código inválida.","Error",JOptionPane.ERROR_MESSAGE);
		}
			
		return valido;
	}
}
