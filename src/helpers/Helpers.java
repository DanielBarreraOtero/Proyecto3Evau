package helpers;

import repositorios.CategoriaBD;
import repositorios.EmpleadoBD;
import repositorios.OficinaBD;

import java.time.LocalDate;
import java.util.Date;

import clases.Categoria;
import clases.Empleado;
import clases.Oficina;
import gui.fichas.fichasCategoria.FichaCategoria;
import gui.fichas.fichasEmpleados.FichaEmpleado;
import gui.fichas.fichasOficina.FichaOficina;
import metodos2.Metodos2;

public class Helpers {

	/**Coge los parametro de la ficha de oficina y llama al metodo de grabaOficina.
	 * 
	 * @param padre
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
	/**Coge los parametro de la ficha de oficina y llama al metodo de modificarOficina.
	 * 
	 * @param padre
	 */
	public static void ModificaOficina(FichaOficina padre) {
		String codigo = padre.textCódigo.getText();
		String descrp = padre.textAreaDescrp.getText();
		String provincia = padre.textProvincia.getText();
		String localidad = padre.textLocalidad.getText();
		boolean esaeropuerto = padre.chckbxEsAeropuerto.isSelected();

		OficinaBD.modificarOficina(codigo,descrp,provincia,localidad,esaeropuerto);
	}
	/**Coge los parametro de la ficha de empleado y llama al metodo de grabaEmpleado.
	 * 
	 * @param padre
	 */
	public static void AñadirEmpleado(FichaEmpleado padre) {
		String dni = padre.textDNI.getText().toUpperCase();
		String nombre = padre.textNombre.getText();
		String ap1 = padre.textAP1.getText();
		String ap2 = padre.textAP2.getText();
		Date fechaNac = padre.dtChFechNac.getDate();
		Date fechaAlt = padre.dtChFechAlt.getDate();
		String codOfi = padre.textOficina.getText();
		Date fechaBaj = padre.dtChFechBaj.getDate();
		
		LocalDate fN = Metodos2.DateToLocalDate(fechaNac);
		LocalDate fA = Metodos2.DateToLocalDate(fechaAlt);
		LocalDate fB = null;
		if (fechaBaj != null)
			fB = Metodos2.DateToLocalDate(fechaBaj);
		Oficina o = OficinaBD.leerOficina(codOfi);
		
		Empleado e = new Empleado(dni, nombre, ap1, ap2, fN, fA, o, fB);
		EmpleadoBD.grabaEmpleado(e);
	}
	
	/**Coge los parametro de la ficha de empleado y llama al metodo de modificarEmpleado.
	 * 
	 * @param padre
	 */
	public static void ModificaEmpelado(FichaEmpleado padre) {
		String dni = padre.textDNI.getText().toUpperCase();
		String nombre = padre.textNombre.getText();
		String ap1 = padre.textAP1.getText();
		String ap2 = padre.textAP2.getText();
		Date fechaNac = padre.dtChFechNac.getDate();
		Date fechaAlt = padre.dtChFechAlt.getDate();
		String codOfi = padre.textOficina.getText();
		Date fechaBaj = padre.dtChFechBaj.getDate();	
		
		EmpleadoBD.modificarEmpleado(dni, nombre, ap1, ap2, fechaNac, fechaAlt, codOfi, fechaBaj);
	}
	
	public static void AñadirCategoria(FichaCategoria padre) {
		String codigo = padre.textCodigo.getText().toUpperCase();
		String descrp = padre.textAreaDescrp.getText();
		double recargo = Double.parseDouble(padre.textRecargo.getText());
		
		Categoria c = new Categoria(codigo, descrp, recargo);
		CategoriaBD.grabaCategoria(c);
	}
	
	public static void ModificaCategoria(FichaCategoria padre) {
		String codigo = padre.textCodigo.getText();
		String descrp = padre.textAreaDescrp.getText();
		
		
		double recargo = 0;
		try {
			recargo = Double.parseDouble(padre.textRecargo.getText());
			CategoriaBD.modificarCategoria(codigo, descrp, recargo);
		} catch (Exception e) {
		}
		
		
	}
}
