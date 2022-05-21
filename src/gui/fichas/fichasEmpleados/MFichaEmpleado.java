package gui.fichas.fichasEmpleados;

import clases.Empleado;
import metodos.Metodos;
import metodos2.Metodos2;
import repositorios.EmpleadoBD;

public class MFichaEmpleado {
	
	/**Rellena la ficha con los datos de la oficina otorgada.
	 * 
	 * @param codigo
	 */
	public static void rellenaFicha(FichaEmpleado padre, String dni) {
		if (EmpleadoBD.empleadoExiste(padre.textDNI.getText())) {
			Empleado e = null;
			e = EmpleadoBD.leerEmpleado(dni);

			padre.textNombre.setText(e.getNombre());
			padre.textAP1.setText(e.getAp1());
			if (e.getAp2() != null)
				padre.textAP2.setText(e.getAp2());
			padre.textOficina.setText(e.getOficinaActual().getCodigo());

			padre.dtChFechNac.setDate(Metodos2.LocalDateToDate(e.getFechaNac()));
			padre.dtChFechAlt.setDate(Metodos2.LocalDateToDate(e.getFechaAlta()));
			if (e.getFechaBaja() != null) {
				padre.chckbxDespedido.setSelected(true);
				estadoFechaBaj(padre);
				padre.dtChFechBaj.setDate(Metodos2.LocalDateToDate(e.getFechaBaja()));
			}
		}
	}
	public static void alternaDniInvalid(FichaEmpleado padre) {
//		Comprueba si el DNI es valido
		if (Metodos.ValidarDNI(padre.textDNI.getText())) {
//		Si es asi apaga el indicador
		padre.lblDniInvalid.setVisible(false);
		} else {
//			Si no lo es enciende el indicador
			padre.lblDniInvalid.setVisible(true);
		}
		estadoBotones(padre);
	}
	
	public static void deshabilitaFicha(FichaEmpleado padre) {
		padre.textNombre.setEnabled(false);
		padre.textNombre.setText(null);
		padre.textAP1.setEnabled(false);
		padre.textAP1.setText(null);
		padre.textAP2.setEnabled(false);
		padre.textAP2.setText(null);
		padre.textOficina.setEnabled(false);
		padre.textOficina.setText(null);
		padre.btnBusquedaOfi.setEnabled(false);
		padre.dtChFechNac.setEnabled(false);
		padre.dtChFechNac.setDate(null);
		padre.dtChFechAlt.setEnabled(false);
		padre.dtChFechAlt.setDate(null);
		padre.chckbxDespedido.setEnabled(false);
		padre.chckbxDespedido.setSelected(false);
		estadoFechaBaj(padre);
	}
	
	public static void habilitaFicha(FichaEmpleado padre) {
		padre.textNombre.setEnabled(true);
		padre.textAP1.setEnabled(true);
		padre.textAP2.setEnabled(true);
		padre.textOficina.setEnabled(true);
		padre.btnBusquedaOfi.setEnabled(true);
		padre.dtChFechNac.setEnabled(true);
		padre.dtChFechAlt.setEnabled(true);
		padre.chckbxDespedido.setEnabled(true);
		rellenaFicha(padre, padre.textDNI.getText());
		estadoFechaBaj(padre);
	}
	
	
	public static void estadoFechaBaj(FichaEmpleado padre) {
		if (padre.chckbxDespedido.isSelected())
			padre.dtChFechBaj.setEnabled(true);
		else {
			padre.dtChFechBaj.setEnabled(false);
			padre.dtChFechBaj.setDate(null);
		}
	}
	
	public static void estadoBotones(FichaEmpleado padre) {
		if(padre.textDNI.getText().length() == 9 && Metodos.ValidarDNI(padre.textDNI.getText())) {
			if (EmpleadoBD.empleadoExiste(padre.textDNI.getText())) {
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
	
	public static void calculaEstadoFicha(FichaEmpleado padre) {
		if(padre.textDNI.getText().length() == 9 && Metodos.ValidarDNI(padre.textDNI.getText())) {
			habilitaFicha(padre);
		} else {
			deshabilitaFicha(padre);
		}
		estadoBotones(padre);
	}
}
