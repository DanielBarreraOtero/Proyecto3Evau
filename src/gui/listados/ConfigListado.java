package gui.listados;

import javax.swing.JFrame;


public class ConfigListado {
	
	private boolean bPanelHerramientas;
	private boolean blistado;
	private boolean bPanelInferior;
	private JFrame FichaPadre;
	private TipoList Tipo;
	
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
	public TipoList getTipo() {
		return Tipo;
	}
	public void setTipo(TipoList tipo) {
		Tipo = tipo;
	}
	
	/**
	 * 
	 * @param bPanelHerramientas
	 * @param blistado
	 * @param bPanelInferior
	 * @param fichaPadre
	 * @param tipo
	 */
	public ConfigListado(boolean bPanelHerramientas, boolean blistado, boolean bPanelInferior, JFrame fichaPadre, TipoList tipo) {
		setbPanelHerramientas(bPanelHerramientas);
		setBlistado(blistado);
		setbPanelInferior(bPanelInferior);
		setFichaPadre(fichaPadre);
		setTipo(tipo);
	}
	/**
	 * 
	 * @param tipo
	 */
	public ConfigListado(TipoList tipo) {
		setbPanelHerramientas(true);
		setBlistado(true);
		setbPanelInferior(true);
		setTipo(tipo);
	}
	
}

