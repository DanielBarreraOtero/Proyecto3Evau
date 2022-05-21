package gui.fichas.fichasOficina;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gui.listados.Listado;
import gui.listados.TipoList;
import helpers.Helpers;
import repositorios.OficinaBD;

public class HandlerFichaOficina implements ActionListener, MouseListener, KeyListener {
	
	private FichaOficina padre;
	
	public HandlerFichaOficina(FichaOficina padre) {
		this.padre = padre;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

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
			case "CREAR": {
				Helpers.AñadirOficina(padre);
				MFichaOficina.calculaEstadoFicha(padre);
				break;
			}
			case "MODIFICAR": {
				Helpers.ModificaOficina(padre);
				MFichaOficina.calculaEstadoFicha(padre);
				break;
			}
			case "BORRAR": {
				String codigo = padre.textCódigo.getText();
				OficinaBD.eliminarOficina(codigo);
				padre.textCódigo.setText("");
				MFichaOficina.calculaEstadoFicha(padre);
				break;
			}
			case "SALIR": {
				padre.dispose();
				break;
			}
			case "BUSQUEDA": {
				Listado.LanzarListadoBusqueda(padre, TipoList.OFICINAS);
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
		MFichaOficina.calculaEstadoFicha(padre);
	}

}
