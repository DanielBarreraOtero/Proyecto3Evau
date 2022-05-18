package gui.listados.listadoOficinas;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class MListadoOficina {
	
	
	public static void CalculaEstadoFiltro(ListadoOficinas padre)
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
	public static DefaultTableModel GeneraModeloTabla(ListadoOficinas padre, Vector<String> nombres, Vector<Vector> data) {
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
	public static void ActualizaTabla(ListadoOficinas padre, Vector<String> nombres, Vector<Vector> data) {
		padre.TablaOficinas.setModel(GeneraModeloTabla(padre, nombres, data));
		padre.TablaOficinas.getColumnModel().getColumn(0).setPreferredWidth(65);
		padre.TablaOficinas.getColumnModel().getColumn(1).setPreferredWidth(115);
		padre.TablaOficinas.getColumnModel().getColumn(2).setPreferredWidth(75);
		padre.TablaOficinas.getColumnModel().getColumn(3).setPreferredWidth(75);
		padre.TablaOficinas.getColumnModel().getColumn(4).setPreferredWidth(140);
	}
}
