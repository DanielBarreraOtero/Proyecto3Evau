package gui.fichas.fichasCarnets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.listados.Listado;
import gui.listados.TipoList;
import helpers.Helpers;
import repositorios.CarnetsBD;

public class HandlerFichaCarnets implements ActionListener, KeyListener{

private FichaCarnets padre;
	
	public HandlerFichaCarnets(FichaCarnets padre) {
		this.padre = padre;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getComponent().getName().equals("codigo")) {
			MFichaCarnets.calculaEstadoFicha(padre);	
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "BUSQUEDA": {
			Listado.LanzarListadoBusqueda(padre, TipoList.CARNETS);
			break;
		}
		case "CREAR": {
//			Comprobamos que los datos sean correctos
			if (MFichaCarnets.compruebaCarnet(padre)) {
				Helpers.AñadirCarnet(padre);
				MFichaCarnets.estadoBotones(padre);
			} 
			break;
		}
		case "MODIFICAR": {
//			Comprobamos que los datos sean correctos
			if (MFichaCarnets.compruebaCarnet(padre)) {
				Helpers.ModificaCarnet(padre);
			} 
			break;
		}
		case "BORRAR": {
			String codigo = padre.textCodigo.getText();
			CarnetsBD.eliminarCarnet(codigo);
			padre.textCodigo.setText("");
			MFichaCarnets.calculaEstadoFicha(padre);
			break;
		}
		case "SALIR": {
			padre.dispose();
			break;
		}
	}
	}
	
	
	
	
	
}
