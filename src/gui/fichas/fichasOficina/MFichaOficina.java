package gui.fichas.fichasOficina;


import repositorios.OficinaBD;

import java.awt.Color;


import clases.Oficina;

public class MFichaOficina {


	
	/**
	 * 
	 */
	public static void habilitarFicha(FichaOficina padre) {
		padre.textProvincia.setEditable(true);
		padre.textProvincia.setEnabled(true);
		padre.textLocalidad.setEditable(true);
		padre.textLocalidad.setEnabled(true);
		padre.textAreaDescrp.setEnabled(true);
		padre.textAreaDescrp.setBackground(Color.white);
		padre.textAreaDescrp.setEditable(true);
		padre.chckbxEsAeropuerto.setEnabled(true);
	}
	/**
	 * 
	 */
	public static void deshabilitarFicha(FichaOficina padre) {
		padre.textProvincia.setEditable(false);
		padre.textProvincia.setEnabled(false);
		padre.textProvincia.setText(null);
		padre.textLocalidad.setEditable(false);
		padre.textLocalidad.setEnabled(false);
		padre.textLocalidad.setText(null);
		padre.textAreaDescrp.setEnabled(false);
		padre.textAreaDescrp.setBackground(Color.getColor("disabled"));
		padre.textAreaDescrp.setEditable(false);
		padre.textAreaDescrp.setText(null);
		padre.chckbxEsAeropuerto.setEnabled(false);
		padre.chckbxEsAeropuerto.setSelected(false);
	}
	/**Rellena la ficha con los datos de la oficina otorgada.
	 * 
	 * @param codigo
	 */
	public static void rellenaFicha(FichaOficina padre, String codigo) {
		Oficina o = null;
		o = OficinaBD.leerOficina(codigo);
		
		padre.textProvincia.setText(o.getProvincia());
		padre.textLocalidad.setText(o.getLocalidad());
		padre.textAreaDescrp.setText(o.getDescrp());
		padre.chckbxEsAeropuerto.setSelected(o.isAeropuerto());
	}
	
	public static void calculaEstadoFicha(FichaOficina padre) {

		String codigo = padre.textCódigo.getText();

		if(codigo.length() == 4) 
		{
			habilitarFicha(padre);

			if(OficinaBD.OficinaExiste(codigo)) 
			{
				padre.btnModificar.setEnabled(true);
				padre.btnModificar.setVisible(true);
				padre.btnBorrar.setEnabled(true);
				padre.btnBorrar.setVisible(true);
				padre.btnCrear.setEnabled(false);
				padre.btnCrear.setVisible(false);

				rellenaFicha(padre, codigo);
			} 
			else 
			{
				padre.btnCrear.setEnabled(true);
				padre.btnCrear.setVisible(true);
				padre.btnModificar.setEnabled(false);
				padre.btnModificar.setVisible(false);
				padre.btnBorrar.setEnabled(false);
				padre.btnBorrar.setVisible(false);
			}
		}
		else 
		{
			deshabilitarFicha(padre);
			padre.btnCrear.setEnabled(false);
			padre.btnCrear.setVisible(false);
			padre.btnModificar.setEnabled(false);
			padre.btnModificar.setVisible(false);
			padre.btnBorrar.setEnabled(false);
			padre.btnBorrar.setVisible(false);
		}
	}
}
