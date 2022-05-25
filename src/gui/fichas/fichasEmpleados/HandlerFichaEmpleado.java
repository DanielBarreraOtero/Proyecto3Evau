package gui.fichas.fichasEmpleados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.listados.Listado;
import gui.listados.TipoList;
import helpers.Helpers;
import repositorios.EmpleadoBD;


public class HandlerFichaEmpleado implements ActionListener, KeyListener{
	
	private FichaEmpleado padre;
	
	public HandlerFichaEmpleado(FichaEmpleado padre) {
		this.padre = padre;
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "BUSQUEDAEMPLE": {
				Listado.LanzarListadoBusqueda(padre, TipoList.EMPLEADOS);
				break;
			}
			case "BUSQUEDAOFI": {
				Listado.LanzarListadoBusqueda(padre, TipoList.OFICINAS);
				break;
			}
			case "DESPEDIDO": {
				MFichaEmpleado.estadoFechaBaj(padre);
				break;
			}
			case "CREAR": {
	//			Comprobamos que los datos sean correctos
				if (MFichaEmpleado.compruebaEmpleado(padre)) {
					Helpers.AñadirEmpleado(padre);
					MFichaEmpleado.estadoBotones(padre);
				} 
				break;
			}
			case "MODIFICAR": {
	//			Comprobamos que los datos sean correctos
				if (MFichaEmpleado.compruebaEmpleado(padre)) {
					Helpers.ModificaEmpelado(padre);
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
