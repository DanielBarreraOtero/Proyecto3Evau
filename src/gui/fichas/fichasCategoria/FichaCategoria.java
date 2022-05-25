package gui.fichas.fichasCategoria;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class FichaCategoria extends JFrame {

	private static final long serialVersionUID = -2920225633248440089L;
	private JPanel contentPane;
	private HandlerFichaCategoria handler;
	private JPanel panelFicha;
	private JPanel panelInferior;
	public JTextField textCodigo;
	public JTextField textRecargo;
	public JButton btnCrear;
	public JButton btnModificar;
	public JButton btnBorrar;
	public JButton btnSalir;
	public JTextArea textAreaDescrp;


	/**
	 * Create the frame.
	 */
	public FichaCategoria() {
		handler = new HandlerFichaCategoria(this);
		
//		JFRAME
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 155, 625, 278);
		setTitle("Ficha de Categoría");
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
//		PANEL FICHA
		
		panelFicha = new JPanel();
		panelFicha.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelFicha, BorderLayout.CENTER);
		panelFicha.setLayout(null);
		
		JLabel lblTitulo = new JLabel("GESTI\u00D3N DE GATEGOR\u00CDAS");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(197, 8, 268, 42);
		panelFicha.add(lblTitulo);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setBounds(28, 86, 56, 14);
		panelFicha.add(lblCodigo);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(91, 83, 86, 20);
		panelFicha.add(textCodigo);
		textCodigo.setName("codigo");
		textCodigo.setColumns(10);
		textCodigo.addKeyListener(handler);
		
		JButton btnBusqueda = new JButton("");
		btnBusqueda.setActionCommand("BUSQUEDA");
		btnBusqueda.addActionListener(handler);
		btnBusqueda.setIcon(new ImageIcon(FichaCategoria.class.getResource("/imgs/icons/zoom.png")));
		btnBusqueda.setBounds(178, 83, 24, 19);
		panelFicha.add(btnBusqueda);
		
		JLabel lblDescrp = new JLabel("Descripci\u00F3n:");
		lblDescrp.setBounds(248, 74, 73, 14);
		panelFicha.add(lblDescrp);
		
		JScrollPane scrollPaneDescrp = new JScrollPane();
		scrollPaneDescrp.setBounds(332, 71, 234, 105);
		panelFicha.add(scrollPaneDescrp);
		
		textAreaDescrp = new JTextArea();
		scrollPaneDescrp.setViewportView(textAreaDescrp);
		
		JLabel lblRecargo = new JLabel("Recargo:");
		lblRecargo.setBounds(26, 137, 56, 14);
		panelFicha.add(lblRecargo);
		
		textRecargo = new JTextField();
		textRecargo.setBounds(91, 134, 86, 20);
		textRecargo.setName("recargo");
		panelFicha.add(textRecargo);
		textRecargo.setColumns(10);
		textRecargo.addKeyListener(handler);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(221, 61, 1, 147);
		panelFicha.add(separator);
		
		
		
//		PANEL INFERIOR
		
		panelInferior = new JPanel();
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
		
		MFichaCategoria.calculaEstadoFicha(this);
	}

	
	public static void LanzarFicha() {
		FichaCategoria f = new FichaCategoria();
		f.setVisible(true);
	}
	public static void LanzarFichaCategoria(String codigo) {
		FichaCategoria f = new FichaCategoria();
		f.textCodigo.setText(codigo);
		MFichaCategoria.calculaEstadoFicha(f);
		f.setVisible(true);
	}

	public void RellenarDatos(String codigo) {
		textCodigo.setText(codigo);
		MFichaCategoria.calculaEstadoFicha(this);
	}
}
