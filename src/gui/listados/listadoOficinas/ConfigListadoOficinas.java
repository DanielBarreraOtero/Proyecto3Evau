package gui.listados.listadoOficinas;

import javax.swing.JFrame;


public class ConfigListadoOficinas {
	
	private boolean bPanelHerramientas;
	private boolean blistado;
	private boolean bPanelInferior;
	private JFrame FichaPadre;
	
	public JFrame getFichaPadre() {
		return FichaPadre;
	}
	private void setFichaPadre(JFrame fichaPadre) {
		this.FichaPadre = fichaPadre;
	}
	public boolean isbPanelHerramientas() {
		return bPanelHerramientas;
	}
	private void setbPanelHerramientas(boolean bPanelHerramientas) {
		this.bPanelHerramientas = bPanelHerramientas;
	}
	public boolean isBlistado() {
		return blistado;
	}
	private void setBlistado(boolean blistado) {
		this.blistado = blistado;
	}
	public boolean isbPanelInferior() {
		return bPanelInferior;
	}
	private void setbPanelInferior(boolean bPanelInferior) {
		this.bPanelInferior = bPanelInferior;
	}
	
	/**
	 * 
	 * @param bPanelHerramientas
	 * @param blistado
	 * @param bPanelInferior
	 */
	public ConfigListadoOficinas(boolean bPanelHerramientas, boolean blistado, boolean bPanelInferior, JFrame fichaPadre) {
		setbPanelHerramientas(bPanelHerramientas);
		setBlistado(blistado);
		setbPanelInferior(bPanelInferior);
		setFichaPadre(fichaPadre);
	}
	
	public ConfigListadoOficinas() {
		setbPanelHerramientas(true);
		setBlistado(true);
		setbPanelInferior(true);
	}
	
}
