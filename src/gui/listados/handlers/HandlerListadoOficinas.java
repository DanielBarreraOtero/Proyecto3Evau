package gui.listados.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JTable;

import excepciones.ParámetroInálido;
import gui.fichas.fichasEmpleados.FichaEmpleado;
import gui.fichas.fichasOficina.FichaOficina;
import gui.listados.Listado;
import metodos2.Metodos2;
import repositorios.OficinaBD;

public class HandlerListadoOficinas extends Handler {

	private Listado padre;
	private String[] Nombres = {"CÓDIGO","DESCRIPCIÓN","LOCALIDAD","PROVINCIA","ESTÁ EN AEROPUERTO"};
	@SuppressWarnings("unchecked")
	private Vector<String> ColNames = (Vector<String>) Metodos2.ArtoVector(Nombres);
	@SuppressWarnings("rawtypes")
	private Vector<Vector> data;

	public HandlerListadoOficinas(Listado padre) {
		this.padre = padre;
	}

	//	PUBLICAS

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (e.getSource().getClass().getName()){
		case "javax.swing.JTable": {
			if (e.getClickCount()>1) 
			{
				SeleccionaOficina();
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
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "cmbbxfiltro": {
			padre.metodos.CalculaEstadoFiltro(padre);
			break;
		}
		case "chckbxFiltro": {
			
			if (padre.chckbxFiltro.isSelected()) {
				try {
					data = OficinaBD.GeneraVectorOfi(OficinaBD.leerOficinas("esaeropuerto","1"));
				} catch (ParámetroInálido e1) {
					e1.printStackTrace();
				}
			} else {
				try {
					data = OficinaBD.GeneraVectorOfi(OficinaBD.leerOficinas("esaeropuerto","0"));
				} catch (ParámetroInálido e1) {
					e1.printStackTrace();
				}
			}
			padre.metodos.ActualizaTabla(padre, ColNames, data);
			break;
		}
		case "btnReset": {
			try {
				data = OficinaBD.GeneraVectorOfi(OficinaBD.leerOficinas());
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
				data = OficinaBD.GeneraVectorOfi(OficinaBD.leerOficinas());
			} catch (ParámetroInálido e1) {
				e1.printStackTrace();
			}
			padre.metodos.ActualizaTabla(padre, ColNames, data);
			break;
		}
		case "AÑADIR": {
			FichaOficina.LanzarFicha();
			break;
		}

		case "MODIFICAR": {
			SeleccionaOficina();	
			break;
		}
		case "BORRAR": {
//			Obtenemos el codigo de la oficina seleccionada
			JTable tabla = padre.Tabla;
			int row = tabla.getSelectedRow();
			String valor = (String)tabla.getValueAt(row, 0);
//			Eliminamos la oficina
			OficinaBD.eliminarOficina(valor);
//			Actualizamos la tabla
			try {
				data = OficinaBD.GeneraVectorOfi(OficinaBD.leerOficinas());
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
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getSource().getClass().getName()) {
		case "javax.swing.JTable": {
			if (e.getKeyCode() == 10) 
			{
				SeleccionaOficina();	
			}
			break;
		}
		case "javax.swing.JTextField": {
			switch (padre.comboBoxFiltro.getSelectedItem().toString()) {
			case "CÓDIGO": {
				try {
					data = OficinaBD.GeneraVectorOfi(OficinaBD.leerOficinas("codigo", padre.textFieldFiltro.getText()));
				} catch (ParámetroInálido e1) {
					e1.printStackTrace();
				}
				padre.metodos.ActualizaTabla(padre, ColNames, data);
				break;
			}
			case "DESCRIPCIÓN": {
				try {
					data = OficinaBD.GeneraVectorOfi(OficinaBD.leerOficinas("descrp", padre.textFieldFiltro.getText()));
				} catch (ParámetroInálido e1) {
					e1.printStackTrace();
				}
				padre.metodos.ActualizaTabla(padre, ColNames, data);
				break;
			}
			case "LOCALIDAD": {
				try {
					data = OficinaBD.GeneraVectorOfi(OficinaBD.leerOficinas("localidad", padre.textFieldFiltro.getText()));
				} catch (ParámetroInálido e1) {
					e1.printStackTrace();
				}
				padre.metodos.ActualizaTabla(padre, ColNames, data);
				break;
			}
			case "PROVINCIA": {
				try {
					data = OficinaBD.GeneraVectorOfi(OficinaBD.leerOficinas("provincia", padre.textFieldFiltro.getText()));
				} catch (ParámetroInálido e1) {
					e1.printStackTrace();
				}
				padre.metodos.ActualizaTabla(padre, ColNames, data);
				break;
			}
			}
			break;
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

	private void SeleccionaOficina() {
		JTable tabla = padre.Tabla;
		int row = tabla.getSelectedRow();
		String valor = (String)tabla.getValueAt(row, 0);
		

		// Comprobamos si tenemos una ventana padre
		// Si no tenemos abrimos la ficha de la oficina
		if(padre.config.getFichaPadre() == null)
			FichaOficina.LanzarFichaOficina(valor);
		// Si tenemos, rellenamos con los datos seleccionados
		else {
			String nombrePadre = padre.config.getFichaPadre().getClass().getName();
			if (nombrePadre == "gui.fichas.fichasOficina.FichaOficina") {
				((FichaOficina) padre.config.getFichaPadre()).RellenarDatos(valor);
				padre.dispose();
			}
			else if (nombrePadre == "gui.fichas.fichasEmpleados.FichaEmpleado") {
				
				System.out.println(valor);
				((FichaEmpleado) padre.config.getFichaPadre()).textOficina.setText(valor);
				padre.dispose();
			}
			else {
				System.out.println(padre.config.getFichaPadre().getClass().getName());
			}
		}
	}


}
