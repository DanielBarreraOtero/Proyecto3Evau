package gui.fichas.fichasOficina;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class FichaOficina extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textCódigo;
	public JTextField textProvincia;
	public JTextField textLocalidad;
	public JTextArea textAreaDescrp;
	public JCheckBox chckbxEsAeropuerto;
	private JButton btnSalir;
	public JButton btnBorrar;
	public JButton btnModificar;
	public JButton btnCrear;
	public JPanel panelFicha;
	private HandlerFichaOficina handler;


	/**
	 * Create the frame.
	 */
	public FichaOficina() {
		handler = new HandlerFichaOficina(this);
		
//		JFRAME
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 155, 689, 278);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
//		PANEL FICHA

		panelFicha = new JPanel();
		panelFicha.setForeground(Color.LIGHT_GRAY);
		panelFicha.setSize(490, 257);
		panelFicha.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(panelFicha, BorderLayout.CENTER);
		panelFicha.setLayout(null);

		JLabel lblTitutlo = new JLabel("GESTI\u00D3N DE OFICINAS");
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitutlo.setBounds(225, 11, 213, 42);
		panelFicha.add(lblTitutlo);

		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigo.setBounds(24, 78, 74, 20);
		panelFicha.add(lblCodigo);

		textCódigo = new JTextField();
		textCódigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textCódigo.setBounds(108, 78, 119, 20);
		panelFicha.add(textCódigo);
		textCódigo.setColumns(10);

		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProvincia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProvincia.setBounds(24, 120, 74, 20);
		panelFicha.add(lblProvincia);

		textProvincia = new JTextField();
		textProvincia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textProvincia.setEditable(false);
		textProvincia.setEnabled(false);
		textProvincia.setColumns(10);
		textProvincia.setBounds(108, 118, 119, 20);
		panelFicha.add(textProvincia);

		JLabel lblLocalidad = new JLabel("Localidad:");
		lblLocalidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLocalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLocalidad.setBounds(24, 161, 74, 20);
		panelFicha.add(lblLocalidad);

		textLocalidad = new JTextField();
		textLocalidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textLocalidad.setEditable(false);
		textLocalidad.setEnabled(false);
		textLocalidad.setColumns(10);
		textLocalidad.setBounds(108, 159, 119, 20);
		panelFicha.add(textLocalidad);

		JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescripcion.setBounds(290, 78, 90, 20);
		panelFicha.add(lblDescripcion);

		JLabel lblEsAeropuerto = new JLabel("Esta en aeropuerto:");
		lblEsAeropuerto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEsAeropuerto.setBounds(290, 161, 148, 20);
		panelFicha.add(lblEsAeropuerto);

		JScrollPane scrollPanelDesc = new JScrollPane();
		scrollPanelDesc.setBounds(390, 78, 213, 70);
		panelFicha.add(scrollPanelDesc);

		textAreaDescrp = new JTextArea();
		textAreaDescrp.setEnabled(false);
		textAreaDescrp.setLineWrap(true);
		textAreaDescrp.setColumns(25);
		textAreaDescrp.setBackground(Color.getColor("disabled"));
		scrollPanelDesc.setViewportView(textAreaDescrp);

		chckbxEsAeropuerto = new JCheckBox("");
		chckbxEsAeropuerto.setEnabled(false);
		chckbxEsAeropuerto.setBackground(Color.LIGHT_GRAY);
		chckbxEsAeropuerto.setBounds(430, 160, 26, 23);
		panelFicha.add(chckbxEsAeropuerto);

		JButton btnBusqueda = new JButton("");
		btnBusqueda.setActionCommand("BUSQUEDA");
		btnBusqueda.addActionListener(handler);
		btnBusqueda.setIcon(new ImageIcon(FichaOficina.class.getResource("/imgs/icons/zoom.png")));
		btnBusqueda.setBounds(225, 78, 24, 19);
		panelFicha.add(btnBusqueda);
		
//		PANEL INFERIOR
		
		JPanel paneInferior = new JPanel();
		FlowLayout flowLayout = (FlowLayout) paneInferior.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		paneInferior.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(paneInferior, BorderLayout.SOUTH);

		btnCrear = new JButton("CREAR");
		btnCrear.addActionListener(handler);
		btnCrear.setEnabled(false);
		btnCrear.setVisible(false);
		paneInferior.add(btnCrear);

		btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(handler);
		btnModificar.setEnabled(false);
		btnModificar.setVisible(false);
		paneInferior.add(btnModificar);


		btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(handler);
		btnBorrar.setEnabled(false);
		btnBorrar.setVisible(false);
		paneInferior.add(btnBorrar);

		btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(handler);
		paneInferior.add(btnSalir);
		
	}

	public static void LanzarFicha() {
		FichaOficina f = new FichaOficina();
		f.setVisible(true);
	}

	public static void LanzarFichaOficina(String codigo) {
		FichaOficina f = new FichaOficina();
		f.setVisible(true);
		f.textCódigo.setText(codigo);
		MFichaOficina.calculaEstadoFicha(f);
	}

	public void RellenarDatos(String codigo){
		this.textCódigo.setText(codigo);
		MFichaOficina.calculaEstadoFicha(this);
	}
}
