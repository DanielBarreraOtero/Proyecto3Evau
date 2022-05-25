package gui.fichas.fichasCategoria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.listados.Listado;
import gui.listados.TipoList;
import helpers.Helpers;
import repositorios.CategoriaBD;


public class HandlerFichaCategoria implements ActionListener, KeyListener{
	
private FichaCategoria padre;
	
	public HandlerFichaCategoria(FichaCategoria padre) {
		this.padre = padre;
	}

	@Override
	public void keyTyped(KeyEvent e) {
//		Comprobamos que en recargo solo pueda escribir numeros y puntos
		if (e.getComponent().getName().equals("recargo")) {
			char c = e.getKeyChar();
	        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_PERIOD)) {
	            e.consume();
	        }
		}
			
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getComponent().getName().equals("codigo"))
			MFichaCategoria.calculaEstadoFicha(padre);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "BUSQUEDA": {
				Listado.LanzarListadoBusqueda(padre, TipoList.CATEGORIAS);
				break;
			}
			case "CREAR": {
//				Comprobamos que los datos sean correctos
				if (MFichaCategoria.compruebaCategoria(padre)) {
					Helpers.AñadirCategoria(padre);
					MFichaCategoria.estadoBotones(padre);
				} 
				break;
			}
			case "MODIFICAR": {
//				Comprobamos que los datos sean correctos
				if (MFichaCategoria.compruebaCategoria(padre)) {
					Helpers.ModificaCategoria(padre);
				} 
				break;
			}
			case "BORRAR": {
				String codigo = padre.textCodigo.getText();
				CategoriaBD.eliminarCategoria(codigo);
				padre.textCodigo.setText("");
				MFichaCategoria.calculaEstadoFicha(padre);
				break;
			}
			case "SALIR": {
				padre.dispose();
				break;
			}
		}
	}

}
