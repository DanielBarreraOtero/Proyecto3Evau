package gui.listados.metodosDeListados;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import gui.listados.Listado;

public class MListadoEmpleados extends MListados {

	@Override
	public void CalculaEstadoFiltro(Listado padre) {
		if (padre.comboBoxFiltro.getSelectedIndex()==-1) {
			padre.textFieldFiltro.setEnabled(false);
			padre.textFieldFiltro.setVisible(false);
			padre.textFieldFiltro.setText(null);
			padre.chckbxFiltro.setEnabled(false);
			padre.chckbxFiltro.setVisible(false);
			padre.chckbxFiltro.setSelected(false);
			padre.dtChFechFiltro.setEnabled(false);
			padre.dtChFechFiltro.setVisible(false);
			padre.dtChFechFiltro.setDate(null);
			padre.btnBusqueda.setVisible(false);
			
			padre.btnReset.setVisible(false);
		}
		else if(padre.comboBoxFiltro.getSelectedItem().equals("FECHA NAC")||
				padre.comboBoxFiltro.getSelectedItem().equals("FECHA ALTA")||padre.comboBoxFiltro.getSelectedItem().equals("FECHA BAJA")) {
			
				padre.textFieldFiltro.setEnabled(false);
				padre.textFieldFiltro.setVisible(false);
				padre.textFieldFiltro.setText(null);
				padre.chckbxFiltro.setEnabled(false);
				padre.chckbxFiltro.setVisible(false);
				padre.dtChFechFiltro.setEnabled(true);
				padre.dtChFechFiltro.setVisible(true);
				padre.btnBusqueda.setVisible(true);
				
				padre.btnReset.setVisible(true);
			}
		else {
			padre.dtChFechFiltro.setEnabled(false);
			padre.dtChFechFiltro.setVisible(false);
			padre.btnBusqueda.setVisible(false);
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

	@SuppressWarnings({ "serial", "rawtypes" })
	@Override
	public DefaultTableModel GeneraModeloTabla(Listado padre, Vector<String> nombres, Vector<Vector> data) {
		DefaultTableModel m = new DefaultTableModel(
				data,
				nombres
			) {
				Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
				};
				@SuppressWarnings("unchecked")
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			
		return m;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void ActualizaTabla(Listado padre, Vector<String> nombres, Vector<Vector> data) {
		padre.Tabla.setModel(GeneraModeloTabla(padre, nombres, data));
		padre.Tabla.getColumnModel().getColumn(0).setPreferredWidth(85);
		padre.Tabla.getColumnModel().getColumn(1).setPreferredWidth(115);
		padre.Tabla.getColumnModel().getColumn(2).setPreferredWidth(115);
		padre.Tabla.getColumnModel().getColumn(3).setPreferredWidth(115);
		padre.Tabla.getColumnModel().getColumn(4).setPreferredWidth(90);
		padre.Tabla.getColumnModel().getColumn(5).setPreferredWidth(75);
		padre.Tabla.getColumnModel().getColumn(6).setPreferredWidth(90);
		padre.Tabla.getColumnModel().getColumn(7).setPreferredWidth(90);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		padre.Tabla.setDefaultRenderer(Object.class, centerRenderer);
	}

}
