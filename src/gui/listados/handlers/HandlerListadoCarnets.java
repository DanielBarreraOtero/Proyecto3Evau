package gui.listados.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JTable;

import excepciones.ParámetroInálido;
import gui.fichas.fichasCarnets.FichaCarnets;
import gui.listados.Listado;
import metodos2.Metodos2;
import repositorios.CarnetsBD;

public class HandlerListadoCarnets extends Handler {

	private Listado padre;
	private String[] Nombres = {"CODIGO","DESCRIPCIÓN"};
	@SuppressWarnings("unchecked")
	private Vector<String> ColNames = (Vector<String>) Metodos2.ArtoVector(Nombres);
	@SuppressWarnings("rawtypes")
	private Vector<Vector> data;
	

	public HandlerListadoCarnets(Listado padre) {
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
				data = CarnetsBD.GeneraVectorcarnet(CarnetsBD.leerCarnets());
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
				data = CarnetsBD.GeneraVectorcarnet(CarnetsBD.leerCarnets());
			} catch (ParámetroInálido e1) {
				e1.printStackTrace();
			}
			padre.metodos.ActualizaTabla(padre, ColNames, data);
			break;
		}
		case "AÑADIR": {
			FichaCarnets.LanzarFicha();
			break;
		}

		case "MODIFICAR": {
			SeleccionaCarnet();	
			break;
		}
		case "BORRAR": {
//			Obtenemos el dni del empleado seleccionado
			JTable tabla = padre.Tabla;
			int row = tabla.getSelectedRow();
			String valor = (String)tabla.getValueAt(row, 0);
//			Eliminamos el empleado
			CarnetsBD.eliminarCarnet(valor);
//			Actualizamos la tabla
			try {
				data = CarnetsBD.GeneraVectorcarnet(CarnetsBD.leerCarnets());
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
				SeleccionaCarnet();
			}
			break;
		}
	}	
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getSource().getClass().getName()) {
			case "javax.swing.JTable": {
				if (e.getKeyCode() == 10) 
				{
					SeleccionaCarnet();	
				}
				break;
			}
			case "javax.swing.JTextField": {
				switch (padre.comboBoxFiltro.getSelectedItem().toString()) {
					case "CODIGO": {
						try {
							data = CarnetsBD.GeneraVectorcarnet(CarnetsBD.leerCarnets("codigo", padre.textFieldFiltro.getText()));
						} catch (ParámetroInálido e1) {
							e1.printStackTrace();
						}
						padre.metodos.ActualizaTabla(padre, ColNames, data);
						break;
					}
					case "DESCRIPCIÓN": {
						try {
							data = CarnetsBD.GeneraVectorcarnet(CarnetsBD.leerCarnets("descrp", padre.textFieldFiltro.getText()));
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

	private void SeleccionaCarnet() {
		JTable tabla = padre.Tabla;
		int row = tabla.getSelectedRow();
		String valor = (String)tabla.getValueAt(row, 0);

		// Comprobamos si tenemos una ventana padre
		// Si no tenemos abrimos la ficha del Carnet
		if(padre.config.getFichaPadre() == null)
			FichaCarnets.LanzarFichaCarnet(valor);
		// Si tenemos, rellenamos padre con los datos seleccionados
		else {
			String nombrePadre = padre.config.getFichaPadre().getClass().getName();
			if (nombrePadre == "gui.fichas.fichasCarnets.FichaCarnets") {
				((FichaCarnets) padre.config.getFichaPadre()).RellenarDatos(valor);
				padre.dispose();
			}
			else {
				System.out.println(padre.config.getFichaPadre().getClass().getName());
			}
		}
	}
}
