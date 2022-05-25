package gui.fichas.fichasCarnets;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gui.fichas.fichasCategoria.FichaCategoria;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.FlowLayout;

public class FichaCarnets extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JButton btnCrear;
	public JButton btnModificar;
	public JButton btnBorrar;
	public JButton btnSalir;
	private HandlerFichaCarnets handler;
	public JTextField textCodigo;
	public JTextArea textAreaDescrp;

	public FichaCarnets() {
		handler = new HandlerFichaCarnets(this);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 155, 549, 278);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelFicha = new JPanel();
		panelFicha.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelFicha, BorderLayout.CENTER);
		panelFicha.setLayout(null);
		
		JLabel lblTitulo = new JLabel("GESTI\u00D3N DE CARNETS DE CONDUCIR");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(81, 8, 361, 42);
		panelFicha.add(lblTitulo);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(35, 83, 46, 14);
		panelFicha.add(lblCodigo);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(85, 81, 86, 20);
		textCodigo.setName("codigo");
		panelFicha.add(textCodigo);
		textCodigo.setColumns(10);
		textCodigo.addKeyListener(handler);
		
		JButton btnBusqueda = new JButton("");
		btnBusqueda.setActionCommand("BUSQUEDA");
		btnBusqueda.addActionListener(handler);
		btnBusqueda.setIcon(new ImageIcon(FichaCategoria.class.getResource("/imgs/icons/zoom.png")));
		btnBusqueda.setBounds(171, 81, 24, 19);
		panelFicha.add(btnBusqueda);
		
		JLabel lblDescrp = new JLabel("Descripci\u00F3n:");
		lblDescrp.setBounds(216, 83, 76, 14);
		panelFicha.add(lblDescrp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(304, 77, 178, 88);
		panelFicha.add(scrollPane);
		
		textAreaDescrp = new JTextArea();
		textAreaDescrp.setLocation(0, 83);
		scrollPane.setViewportView(textAreaDescrp);
		
		
//		PANEL INFERIOR
		
		JPanel panelInferior = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelInferior.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelInferior.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		
		btnCrear = new JButton("CREAR");
		btnCrear.addActionListener(handler);
		btnCrear.setEnabled(false);
		btnCrear.setVisible(false);
		panelInferior.add(btnCrear);

		btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(handler);
		btnModificar.setEnabled(false);
		btnModificar.setVisible(false);
		panelInferior.add(btnModificar);


		btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(handler);
		btnBorrar.setEnabled(false);
		btnBorrar.setVisible(false);
		panelInferior.add(btnBorrar);

		btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(handler);
		panelInferior.add(btnSalir);
		
		MFichaCarnets.calculaEstadoFicha(this);
	}
	
	public static void LanzarFicha() {
		FichaCarnets f = new FichaCarnets();
		f.setVisible(true);
	}

	public static void LanzarFichaCarnet(String valor) {
		FichaCarnets f = new FichaCarnets();
		f.setVisible(true);
		f.textCodigo.setText(valor);
		MFichaCarnets.calculaEstadoFicha(f);
	}

	public void RellenarDatos(String valor) {
		textCodigo.setText(valor);
		MFichaCarnets.calculaEstadoFicha(this);
	}
}
