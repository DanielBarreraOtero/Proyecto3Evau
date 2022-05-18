package gui.fichas.fichasEmpleados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import metodos.Metodos;

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
		alternaDniInvalid();
	}
	
	public void alternaDniInvalid() {
//		Comprueba si el DNI es valido
		if (Metodos.ValidarDNI(padre.textDNI.getText())) {
//		Si es asi apaga el indicador
		padre.lblDniInvalid.setVisible(false);
//			TODO comprueba si el dni ya existe
			if (true) {
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
		} else {
//			Si no lo es enciende el indicador
			padre.lblDniInvalid.setVisible(true);
		}
	}
	
}
