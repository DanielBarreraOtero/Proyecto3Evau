package gui.listados.metodosDeListados;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import gui.listados.Listado;

public abstract class MListados {
	
	public abstract void CalculaEstadoFiltro(Listado padre);
	
	@SuppressWarnings({ "rawtypes", "serial" })
	public abstract DefaultTableModel GeneraModeloTabla(Listado padre, Vector<String> nombres, Vector<Vector> data) ;
	
	@SuppressWarnings("rawtypes")
	public abstract void ActualizaTabla(Listado padre, Vector<String> nombres, Vector<Vector> data) ;

}
