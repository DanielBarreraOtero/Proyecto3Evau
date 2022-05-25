package gui.fichas.fichasCategoria;

import javax.swing.JOptionPane;

import clases.Categoria;
import repositorios.CategoriaBD;

public class MFichaCategoria {
	
	public static void deshabilitaFicha(FichaCategoria padre) {
		padre.textRecargo.setEnabled(false);
		padre.textRecargo.setText(null);
		padre.textAreaDescrp.setEnabled(false);
		padre.textAreaDescrp.setText(null);
	}
	
	public static void habilitaFicha(FichaCategoria padre) {
		padre.textRecargo.setEnabled(true);
		padre.textAreaDescrp.setEnabled(true);
		rellenaFicha(padre, padre.textCodigo.getText());
	}
	
	
	private static void rellenaFicha(FichaCategoria padre, String codigo) {
		if (CategoriaBD.CategoriaExiste(codigo)) {
			Categoria c = null;
			c = CategoriaBD.leerCategoria(codigo);

			padre.textAreaDescrp.setText(c.getDescripcion());
			padre.textRecargo.setText(c.getRecargo()+"");
		}
	}

	public static void estadoBotones(FichaCategoria padre) {
		if(padre.textCodigo.getText().length() == 1) {
			if (CategoriaBD.CategoriaExiste(padre.textCodigo.getText())) {
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
	
	public static void calculaEstadoFicha(FichaCategoria padre) {
		if(padre.textCodigo.getText().length() == 1) {
			habilitaFicha(padre);
		} else {
			deshabilitaFicha(padre);
		}
		estadoBotones(padre);
	}
	
	public static boolean compruebaCategoria(FichaCategoria padre) {
		boolean valido = true;
		double recargo = 0;
		
		try {
			recargo = Double.parseDouble(padre.textRecargo.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(padre,"Valor de recargo inválido.","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		if (padre.textCodigo.getText().length() != 1) {
			valido = false;
			JOptionPane.showMessageDialog(padre,"Longitud de código inválida.","Error",JOptionPane.ERROR_MESSAGE);
		}
		else if (recargo < 0 || recargo > 100) {
			valido = false;
			JOptionPane.showMessageDialog(padre,"Valor de recargo inválido.","Error",JOptionPane.ERROR_MESSAGE);
		}
			
		return valido;
	}
	
}
