package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.fichas.fichasCategoria.FichaCategoria;
import gui.fichas.fichasEmpleados.FichaEmpleado;
import gui.fichas.fichasOficina.FichaOficina;
import gui.listados.Listado;
import gui.listados.TipoList;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel Contenido;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setTitle("Alquiler de vehiculos");
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 327);
		
		Contenido = new JPanel();
		Contenido.setBackground(Color.LIGHT_GRAY);
		Contenido.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Contenido);
		Contenido.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		JMenu mnFichas = new JMenu("Fichas");
		mnFichas.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mnFichas);
		
		JMenuItem mntmFichaOficina = new JMenuItem("Oficinas");
		mntmFichaOficina.setHorizontalAlignment(SwingConstants.TRAILING);
		mntmFichaOficina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FichaOficina.LanzarFicha();
			}
		});
		mnFichas.add(mntmFichaOficina);
		
		JMenuItem mntmFichaEmpleados = new JMenuItem("Empleados");
		mntmFichaEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FichaEmpleado.LanzarFicha();
			}
		});
		mnFichas.add(mntmFichaEmpleados);
		
		JMenuItem mntmFichaCategoria = new JMenuItem("Categor\u00EDas");
		mntmFichaCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FichaCategoria.LanzarFicha();
			}
		});
		mnFichas.add(mntmFichaCategoria);
		
		JMenu mnListados = new JMenu("Listados");
		mnListados.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mnListados);
		
		JMenuItem mntmListadoOficinas = new JMenuItem("Oficinas");
		mntmListadoOficinas.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Listado.LanzarListado(TipoList.OFICINAS);
			}
		});
		mnListados.add(mntmListadoOficinas);
		
		JMenuItem mntmListadoEmpleados = new JMenuItem("Empleados");
		mntmListadoEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Listado.LanzarListado(TipoList.EMPLEADOS);
			}
		});
		mnListados.add(mntmListadoEmpleados);
		
		JMenuItem mntmListadoCategorias = new JMenuItem("Categorias");
		mntmListadoCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Listado.LanzarListado(TipoList.CATEGORIAS);
			}
		});
		mnListados.add(mntmListadoCategorias);
		
		
		
	}
}
