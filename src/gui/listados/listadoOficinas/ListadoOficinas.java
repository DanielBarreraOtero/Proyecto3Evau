package gui.listados.listadoOficinas;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import excepciones.ParámetroInálido;
import metodos2.Metodos2;
import repositorios.OficinaBD;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import gui.fichas.fichasOficina.FichaOficina;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.Dimension;

public class ListadoOficinas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTable TablaOficinas;
	public JTextField textFieldFiltro;
	public JComboBox<String> comboBoxFiltro;
	public JCheckBox chckbxFiltro;
	public JButton btnReset;
	@SuppressWarnings("rawtypes")
	public Vector<Vector> data;
	public ConfigListadoOficinas config;
	public JButton btnAÑADIR;
	public JButton btnMODIFICAR;
	public JButton btnBORRAR;
	public JButton btnSALIR;
	private HandlerListadoOficinas handler;
	private JPanel panelRefresco;
	private JPanel panelFiltro;
	private JButton btnRefresh;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public ListadoOficinas(ConfigListadoOficinas confg) {
		handler = new HandlerListadoOficinas(this);
		this.config = confg;
		setTitle("Listado de Oficinas");
		String[] Nombres = {"CÓDIGO","DESCRIPCIÓN","LOCALIDAD","PROVINCIA","ESTÁ EN AEROPUERTO"};
		
		setBounds(100, 155, 530, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		if (config.isbPanelHerramientas()) {
			JPanel PanelHerramientas = new JPanel();

			contentPane.add(PanelHerramientas, BorderLayout.NORTH);
			PanelHerramientas.setLayout(new BorderLayout(0, 0));

			panelRefresco = new JPanel();
			panelRefresco.setBounds(new Rectangle(0, 0, 25, 0));
			PanelHerramientas.add(panelRefresco, BorderLayout.WEST);

			comboBoxFiltro = new JComboBox<String>();
			comboBoxFiltro.setActionCommand("cmbbxfiltro");
			comboBoxFiltro.setName("");
			panelRefresco.add(comboBoxFiltro);

			comboBoxFiltro.setModel(new DefaultComboBoxModel<String>(Nombres));
			comboBoxFiltro.setSelectedIndex(-1);

			textFieldFiltro = new JTextField();
			textFieldFiltro.addKeyListener(handler);
			textFieldFiltro.setActionCommand("textFieldFiltro");
			panelRefresco.add(textFieldFiltro);
			textFieldFiltro.setColumns(10);

			chckbxFiltro = new JCheckBox("");
			chckbxFiltro.addActionListener(handler);
			chckbxFiltro.setActionCommand("chckbxFiltro");
			panelRefresco.add(chckbxFiltro);

			btnReset = new JButton("X");
			btnReset.addActionListener(handler);
			btnReset.setActionCommand("btnReset");
			panelRefresco.add(btnReset);
			
			
			
			panelFiltro = new JPanel();
			PanelHerramientas.add(panelFiltro, BorderLayout.EAST);
			
			btnRefresh = new JButton();
			btnRefresh.setActionCommand("Refresh");
			btnRefresh.setPreferredSize(new Dimension(25, 25));
			btnRefresh.setIcon(new ImageIcon(ListadoOficinas.class.getResource("/imgs/icons/refresh.png")));
			panelFiltro.add(btnRefresh);
			btnRefresh.addActionListener(handler);

			MListadoOficina.CalculaEstadoFiltro(this);
			comboBoxFiltro.addActionListener(handler);
		}
		
		if (config.isBlistado()) {

			JScrollPane scrollPanel = new JScrollPane();
			contentPane.add(scrollPanel, BorderLayout.CENTER);
			
			TablaOficinas = new JTable();
			TablaOficinas.addFocusListener(handler);
			TablaOficinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			TablaOficinas.addMouseListener(handler);
			scrollPanel.setViewportView(TablaOficinas);
			TablaOficinas.addKeyListener(handler);
			
			
			
			@SuppressWarnings("unchecked")
			Vector<String> ColNames = (Vector<String>) Metodos2.ArtoVector(Nombres);

			try {
				data = OficinaBD.GeneraVectorOfi(OficinaBD.leerOficinas());
			} catch (ParámetroInálido e) {
				e.printStackTrace();
			}
			
			MListadoOficina.ActualizaTabla(this, ColNames, data);
		}
		
		if (config.isbPanelInferior()) {
			JPanel PanelInferior = new JPanel();
			FlowLayout flowLayout_1 = (FlowLayout) PanelInferior.getLayout();
			flowLayout_1.setAlignment(FlowLayout.RIGHT);
			contentPane.add(PanelInferior, BorderLayout.SOUTH);
			
			btnAÑADIR = new JButton("A\u00D1ADIR");
			PanelInferior.add(btnAÑADIR);
			btnAÑADIR.addActionListener(handler);
			
			btnMODIFICAR = new JButton("MODIFICAR");
			PanelInferior.add(btnMODIFICAR);
			btnMODIFICAR.setVisible(false);
			btnMODIFICAR.addActionListener(handler);
			
			btnBORRAR = new JButton("BORRAR");
			PanelInferior.add(btnBORRAR);
			btnBORRAR.setVisible(false);
			btnBORRAR.addActionListener(handler);
			
			btnSALIR = new JButton("SALIR");
			btnSALIR.addActionListener(handler);
			PanelInferior.add(btnSALIR);
		}
		
	}

	/**
	 * 
	 */
	public static void LanzarListado() {
		ListadoOficinas f = new ListadoOficinas(new ConfigListadoOficinas());
		f.setVisible(true);
	}

	/**
	 * 
	 */
	public static void LanzarListadoBusqueda(FichaOficina padre) {
		//Creamos un ListadoOficinas sin panel inferior
		ConfigListadoOficinas config = new ConfigListadoOficinas(true,true,false,padre);
		ListadoOficinas f = new ListadoOficinas(config);
		//Lo convertimos a JDialog
		JDialog frame = new JDialog(f, f.getTitle(), true);
		frame.getContentPane().add(f.getContentPane());
		frame.setBounds(f.getBounds());
		frame.setVisible(true);
	}
}
