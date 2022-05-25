package gui.listados.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTable;

import excepciones.ParámetroInálido;
import gui.fichas.fichasEmpleados.FichaEmpleado;
import gui.listados.Listado;
import metodos2.Metodos2;
import repositorios.EmpleadoBD;

public class HandlerListadoEmpleados extends Handler{
	
	private Listado padre;
	private String[] Nombres = {"DNI","NOMBRE","APELLIDO 1","APELLIDO 2","FECHA NAC","OFICINA","FECHA ALTA","FECHA BAJA"};
	@SuppressWarnings("unchecked")
	private Vector<String> ColNames = (Vector<String>) Metodos2.ArtoVector(Nombres);
	@SuppressWarnings("rawtypes")
	private Vector<Vector> data;
	

	public HandlerListadoEmpleados(Listado padre) {
		this.padre=padre;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "cmbbxfiltro": {
				padre.metodos.CalculaEstadoFiltro(padre);
				break;
			}
			case "BUSQUEDA": {
				switch (padre.comboBoxFiltro.getSelectedItem().toString()) {
					case "FECHA NAC": {
						Date fecha = padre.dtChFechFiltro.getDate();
						try {
							data = EmpleadoBD.GeneraVectorEmple(EmpleadoBD.leerEmpleados("fecha_nac", Metodos2.DateToString(fecha)));
						} catch (ParámetroInálido e1) {
							e1.printStackTrace();
						}
						padre.metodos.ActualizaTabla(padre, ColNames, data);
						break;
					}
					case "FECHA ALTA": {
						Date fecha = padre.dtChFechFiltro.getDate();
						try {
							data = EmpleadoBD.GeneraVectorEmple(EmpleadoBD.leerEmpleados("fecha_alta", Metodos2.DateToString(fecha)));
						} catch (ParámetroInálido e1) {
							e1.printStackTrace();
						}
						padre.metodos.ActualizaTabla(padre, ColNames, data);
						break;
					}
					case "FECHA BAJA": {
						Date fecha = padre.dtChFechFiltro.getDate();
						try {
							data = EmpleadoBD.GeneraVectorEmple(EmpleadoBD.leerEmpleados("fecha_baja", Metodos2.DateToString(fecha)));
						} catch (ParámetroInálido e1) {
							e1.printStackTrace();
						}
						padre.metodos.ActualizaTabla(padre, ColNames, data);
						break;
					}
				}
				break;
			}
			case "btnReset": {
				try {
					data = EmpleadoBD.GeneraVectorEmple(EmpleadoBD.leerEmpleados());
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
					data = EmpleadoBD.GeneraVectorEmple(EmpleadoBD.leerEmpleados());
				} catch (ParámetroInálido e1) {
					e1.printStackTrace();
				}
				padre.metodos.ActualizaTabla(padre, ColNames, data);
				break;
			}
			case "AÑADIR": {
				FichaEmpleado.LanzarFicha();
				break;
			}

			case "MODIFICAR": {
				SeleccionaEmpleado();	
				break;
			}
			case "BORRAR": {
//				Obtenemos el dni del empleado seleccionado
				JTable tabla = padre.Tabla;
				int row = tabla.getSelectedRow();
				String valor = (String)tabla.getValueAt(row, 0);
//				Eliminamos el empleado
				EmpleadoBD.eliminarEmpleado(valor);
//				Actualizamos la tabla
				try {
					data = EmpleadoBD.GeneraVectorEmple(EmpleadoBD.leerEmpleados());
				} catch (ParámetroInálido e1) {
					e1.printStackTrace();
				}
				padre.metodos.ActualizaTabla(padre, ColNames, data);
//				Escondemos los botenes de borrar y modificar
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
					SeleccionaEmpleado();
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
					SeleccionaEmpleado();	
				}
				break;
			}
			case "javax.swing.JTextField": {
				switch (padre.comboBoxFiltro.getSelectedItem().toString()) {
				case "DNI": {
					try {
						data = EmpleadoBD.GeneraVectorEmple(EmpleadoBD.leerEmpleados("dni", padre.textFieldFiltro.getText()));
					} catch (ParámetroInálido e1) {
						e1.printStackTrace();
					}
					padre.metodos.ActualizaTabla(padre, ColNames, data);
					break;
				}
				case "NOMBRE": {
					try {
						data = EmpleadoBD.GeneraVectorEmple(EmpleadoBD.leerEmpleados("nombre", padre.textFieldFiltro.getText()));
					} catch (ParámetroInálido e1) {
						e1.printStackTrace();
					}
					padre.metodos.ActualizaTabla(padre, ColNames, data);
					break;
				}
				case "APELLIDO 1": {
					try {
						data = EmpleadoBD.GeneraVectorEmple(EmpleadoBD.leerEmpleados("ap1", padre.textFieldFiltro.getText()));
					} catch (ParámetroInálido e1) {
						e1.printStackTrace();
					}
					padre.metodos.ActualizaTabla(padre, ColNames, data);
					break;
				}
				case "APELLIDO 2": {
					try {
						data = EmpleadoBD.GeneraVectorEmple(EmpleadoBD.leerEmpleados("ap2", padre.textFieldFiltro.getText()));
					} catch (ParámetroInálido e1) {
						e1.printStackTrace();
					}
					padre.metodos.ActualizaTabla(padre, ColNames, data);
					break;
				}
				case "OFICINA": {
					try {
						data = EmpleadoBD.GeneraVectorEmple(EmpleadoBD.leerEmpleados("cod_oficina", padre.textFieldFiltro.getText()));
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

	private void SeleccionaEmpleado() {
		JTable tabla = padre.Tabla;
		int row = tabla.getSelectedRow();
		String valor = (String)tabla.getValueAt(row, 0);

		// Comprobamos si tenemos una ventana padre
		// Si no tenemos abrimos la ficha del empleado
		if(padre.config.getFichaPadre() == null)
			FichaEmpleado.LanzarFichaEmpleado(valor);
		// Si tenemos, rellenamos padre con los datos seleccionados
		else {
			String nombrePadre = padre.config.getFichaPadre().getClass().getName();
			if (nombrePadre == "gui.fichas.fichasEmpleados.FichaEmpleado") {
				((FichaEmpleado) padre.config.getFichaPadre()).RellenarDatos(valor);
				padre.dispose();
			}
			else {
				System.out.println(padre.config.getFichaPadre().getClass().getName());
			}
		}
	}
	
	
}
