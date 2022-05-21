package gui.fichas.fichasEmpleados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import helpers.Helpers;
import repositorios.EmpleadoBD;
import repositorios.OficinaBD;


public class HandlerFichaEmpleado implements ActionListener, KeyListener{
	
	private FichaEmpleado padre;
	
	public HandlerFichaEmpleado(FichaEmpleado padre) {
		this.padre = padre;
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "a": {
			
			break;
		}
		case "DESPEDIDO": {
			MFichaEmpleado.estadoFechaBaj(padre);
			break;
		}
		case "CREAR": {
//			Comprobamos que los datos sean correctos
			boolean condicion = OficinaBD.OficinaExiste(padre.textOficina.getText()) && padre.dtChFechNac.getDate() != null && padre.dtChFechAlt.getDate() != null;
			if (condicion) {
				Helpers.AñadirEmpleado(padre);
				MFichaEmpleado.estadoBotones(padre);
			} else {
//				TODO JDIALOG CON ERROR
			}	
			break;
		}
		case "MODIFICAR": {
//			Comprobamos que los datos sean correctos
			boolean condicion = OficinaBD.OficinaExiste(padre.textOficina.getText()) && padre.dtChFechNac.getDate() != null && padre.dtChFechAlt.getDate() != null;
			if (condicion) {
				Helpers.ModificaEmpelado(padre);
			} else {
//				TODO JDIALOG CON ERROR
			}
			break;
		}
		case "BORRAR": {
			String dni = padre.textDNI.getText();
			EmpleadoBD.eliminarEmpleado(dni);
			padre.textDNI.setText("");
			MFichaEmpleado.calculaEstadoFicha(padre);
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
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		MFichaEmpleado.alternaDniInvalid(padre);
		MFichaEmpleado.calculaEstadoFicha(padre);
	}

	
}
