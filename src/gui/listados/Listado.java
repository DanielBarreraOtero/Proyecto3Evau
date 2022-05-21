package gui.listados;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import excepciones.ParámetroInálido;
import gui.listados.handlers.Handler;
import gui.listados.handlers.HandlerListadoOficinas;
import metodos2.Metodos2;
import repositorios.OficinaBD;

public class Listado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTable Tabla;
	public JTextField textFieldFiltro;
	public JComboBox<String> comboBoxFiltro;
	public JCheckBox chckbxFiltro;
	public JButton btnReset;
	@SuppressWarnings("rawtypes")
	public Vector<Vector> data;
	public ConfigListado config;
	public JButton btnAÑADIR;
	public JButton btnMODIFICAR;
	public JButton btnBORRAR;
	public JButton btnSALIR;
	private Handler handler;
	private JPanel panelRefresco;
	private JPanel panelFiltro;
	private JButton btnRefresh;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public Listado(ConfigListado confg) {
		this.config = confg;
		String[] Nombres = null;
		
		switch (config.getTipo()){
			case OFICINAS: {
				handler =  new  HandlerListadoOficinas(this);
				setTitle("Listado de Oficinas");
				String[] nms = {"CÓDIGO","DESCRIPCIÓN","LOCALIDAD","PROVINCIA","ESTÁ EN AEROPUERTO"};
				Nombres = nms.clone();
				break;
			}
			case EMPLEADOS: {
				break;
			}
		}
		
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
			btnRefresh.setIcon(new ImageIcon(Listado.class.getResource("/imgs/icons/refresh.png")));
			panelFiltro.add(btnRefresh);
			btnRefresh.addActionListener(handler);

			MListado.CalculaEstadoFiltro(this);
			comboBoxFiltro.addActionListener(handler);
		}
		
		if (config.isBlistado()) {

			JScrollPane scrollPanel = new JScrollPane();
			contentPane.add(scrollPanel, BorderLayout.CENTER);
			
			Tabla = new JTable();
			Tabla.addFocusListener(handler);
			Tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			Tabla.addMouseListener(handler);
			scrollPanel.setViewportView(Tabla);
			Tabla.addKeyListener(handler);
			
			
			
			@SuppressWarnings("unchecked")
			Vector<String> ColNames = (Vector<String>) Metodos2.ArtoVector(Nombres);

			try {
				data = OficinaBD.GeneraVectorOfi(OficinaBD.leerOficinas());
			} catch (ParámetroInálido e) {
				e.printStackTrace();
			}
			
			MListado.ActualizaTabla(this, ColNames, data);
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
	public static void LanzarListado(TipoList tipo) {
		Listado f = new Listado(new ConfigListado(tipo));
		f.setVisible(true);
	}

	/**
	 * 
	 */
	public static void LanzarListadoBusqueda(JFrame padre,TipoList tipo) {
		//Creamos un ListadoOficinas sin panel inferior
		ConfigListado config = new ConfigListado(true,true,false,padre,tipo);
		Listado f = new Listado(config);
		//Lo convertimos a JDialog
		JDialog frame = new JDialog(f, f.getTitle(), true);
		frame.getContentPane().add(f.getContentPane());
		frame.setBounds(f.getBounds());
		frame.setVisible(true);
	}
}
