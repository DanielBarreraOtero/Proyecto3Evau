package gui.listados.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import excepciones.ParámetroInálido;
import gui.fichas.fichasCategoria.FichaCategoria;
import gui.fichas.fichasEmpleados.FichaEmpleado;
import gui.listados.Listado;
import metodos2.Metodos2;
import repositorios.CategoriaBD;
import repositorios.EmpleadoBD;

public class HandlerListadoCategorias extends Handler {

	private Listado padre;
	private String[] Nombres = {"CODIGO","DESCRIPCIÓN","RECARGO"};
	@SuppressWarnings("unchecked")
	private Vector<String> ColNames = (Vector<String>) Metodos2.ArtoVector(Nombres);
	@SuppressWarnings("rawtypes")
	private Vector<Vector> data;
	
	public HandlerListadoCategorias(Listado padre) {
		this.padre=padre;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "cmbbxfiltro": {
			padre.metodos.CalculaEstadoFiltro(padre);
			break;
		}
		case "btnReset": {
			try {
				data = CategoriaBD.GeneraVectorCate(CategoriaBD.leerCategorias());
			} catch (ParámetroInálido e1) {
				e1.printStackTrace();
			}

			padre.comboBoxFiltro.setSelectedIndex(-1);
			padre.metodos.CalculaEstadoFiltro(padre);

			padre.metodos.ActualizaTabla(padre, ColNames, data);

			break;
		}
		case "Refresh": {
			try {
				data = CategoriaBD.GeneraVectorCate(CategoriaBD.leerCategorias());
			} catch (ParámetroInálido e1) {
				e1.printStackTrace();
			}
			padre.metodos.ActualizaTabla(padre, ColNames, data);
			break;
		}
		case "AÑADIR": {
			FichaCategoria.LanzarFicha();
			break;
		}
		case "MODIFICAR": {
			SeleccionaCategoria();	
			break;
		}
		case "BORRAR": {
//			Obtenemos el codigo de la categoria seleccionada
			JTable tabla = padre.Tabla;
			int row = tabla.getSelectedRow();
			String valor = (String)tabla.getValueAt(row, 0);
//			Eliminamos la categoria
			CategoriaBD.eliminarCategoria(valor);
//			Actualizamos la tabla
			try {
				data = CategoriaBD.GeneraVectorCate(CategoriaBD.leerCategorias());
			} catch (ParámetroInálido e1) {
				e1.printStackTrace();
			}
			padre.metodos.ActualizaTabla(padre, ColNames, data);
//			Escondemos los botenes de borrar y modificar
			padre.btnMODIFICAR.setVisible(false);
			padre.btnBORRAR.setVisible(false);
			break;
		}
		case "SALIR": {
			padre.dispose();
			break;
		}
	}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (e.getSource().getClass().getName()){
			case "javax.swing.JTable": {
				if (e.getClickCount()>1) 
				{
					SeleccionaCategoria();
				}
				break;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getComponent().getName().equals("textFieldFiltro") && padre.comboBoxFiltro.getSelectedItem().toString().equals("RECARGO"))
		{
			char c = e.getKeyChar();
			if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_PERIOD)) {
				e.consume();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getSource().getClass().getName()) {
			case "javax.swing.JTable": {
				if (e.getKeyCode() == 10) 
				{
					SeleccionaCategoria();	
				}
				break;
			}
			case "javax.swing.JTextField": {
				switch (padre.comboBoxFiltro.getSelectedItem().toString()) {
					case "CODIGO": {
						try {
							data = CategoriaBD.GeneraVectorCate(CategoriaBD.leerCategorias("codigo", padre.textFieldFiltro.getText()));
						} catch (ParámetroInálido e1) {
							e1.printStackTrace();
						}
						padre.metodos.ActualizaTabla(padre, ColNames, data);
						break;
					}
					case "DESCRIPCIÓN": {
						try {
							data = CategoriaBD.GeneraVectorCate(CategoriaBD.leerCategorias("descrp", padre.textFieldFiltro.getText()));
						} catch (ParámetroInálido e1) {
							e1.printStackTrace();
						}
						padre.metodos.ActualizaTabla(padre, ColNames, data);
						break;
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getComponent().getName().equals("textFieldFiltro") && padre.comboBoxFiltro.getSelectedItem().toString().equals("RECARGO"))
		{
			char c = e.getKeyChar();
			if (!((c < '0') || (c > '9')) || !(c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_PERIOD)) {
				try {
					double recargo = Double.parseDouble(padre.textFieldFiltro.getText());
					data = CategoriaBD.GeneraVectorCate(CategoriaBD.leerCategorias("recargo", padre.textFieldFiltro.getText()));
				} catch (Exception e1) {
					JOptionPane.showInternalMessageDialog(null, e1, "Error", JOptionPane.ERROR_MESSAGE);
				}
				padre.metodos.ActualizaTabla(padre, ColNames, data);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		try {
			padre.btnMODIFICAR.setVisible(true);
			padre.btnBORRAR.setVisible(true);
		} catch (Exception e2) {
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		try {
			padre.btnMODIFICAR.setVisible(false);
			padre.btnBORRAR.setVisible(false);
		} catch (Exception e2) {
		}
	}

	private void SeleccionaCategoria() {
		JTable tabla = padre.Tabla;
		int row = tabla.getSelectedRow();
		String valor = (String)tabla.getValueAt(row, 0);

		// Comprobamos si tenemos una ventana padre
		// Si no tenemos abrimos la ficha de la categoria
		if(padre.config.getFichaPadre() == null)
			FichaCategoria.LanzarFichaCategoria(valor);
		// Si tenemos, rellenamos padre con los datos seleccionados
		else {
//			TODO CAMBIAR
			String nombrePadre = padre.config.getFichaPadre().getClass().getName();
			if (nombrePadre == "gui.fichas.fichasCategoria.FichaCategoria") {
				((FichaCategoria) padre.config.getFichaPadre()).RellenarDatos(valor);
				padre.dispose();
			}
			else {
				System.out.println(padre.config.getFichaPadre().getClass().getName());
			}
		}
	}
}
