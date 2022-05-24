package gui.listados.metodosDeListados;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import gui.listados.Listado;

public class MListadoOficinas extends MListados{
	
	
	public void CalculaEstadoFiltro(Listado padre)
	{
		if (padre.comboBoxFiltro.getSelectedIndex()==-1) {
			padre.textFieldFiltro.setEnabled(false);
			padre.textFieldFiltro.setVisible(false);
			padre.chckbxFiltro.setEnabled(false);
			padre.chckbxFiltro.setVisible(false);
			padre.textFieldFiltro.setText(null);
			padre.chckbxFiltro.setSelected(false);
			padre.btnReset.setVisible(false);
			
		}
		else if (padre.comboBoxFiltro.getSelectedItem().equals("ESTÁ EN AEROPUERTO")) {
			padre.textFieldFiltro.setEnabled(false);
			padre.textFieldFiltro.setVisible(false);
			padre.chckbxFiltro.setEnabled(true);
			padre.chckbxFiltro.setVisible(true);
			padre.textFieldFiltro.setText(null);
			padre.btnReset.setVisible(true);
		}
		else {
			padre.chckbxFiltro.setEnabled(false);
			padre.chckbxFiltro.setVisible(false);
			padre.textFieldFiltro.setEnabled(true);
			padre.textFieldFiltro.setVisible(true);
			padre.chckbxFiltro.setSelected(false);
			padre.btnReset.setVisible(true);
		}
		padre.contentPane.validate();
		padre.contentPane.repaint();
	}
	
	@SuppressWarnings({ "rawtypes", "serial" })
	public DefaultTableModel GeneraModeloTabla(Listado padre, Vector<String> nombres, Vector<Vector> data) {
		DefaultTableModel m = new DefaultTableModel(
				data,
				nombres
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, Boolean.class
				};
				@SuppressWarnings("unchecked")
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			
		return m;
	}
	
	@SuppressWarnings("rawtypes")
	public void ActualizaTabla(Listado padre, Vector<String> nombres, Vector<Vector> data) {
		padre.Tabla.setModel(GeneraModeloTabla(padre, nombres, data));
		padre.Tabla.getColumnModel().getColumn(0).setPreferredWidth(65);
		padre.Tabla.getColumnModel().getColumn(1).setPreferredWidth(115);
		padre.Tabla.getColumnModel().getColumn(2).setPreferredWidth(75);
		padre.Tabla.getColumnModel().getColumn(3).setPreferredWidth(75);
		padre.Tabla.getColumnModel().getColumn(4).setPreferredWidth(140);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		padre.Tabla.setDefaultRenderer(Object.class, centerRenderer);
	}
}
