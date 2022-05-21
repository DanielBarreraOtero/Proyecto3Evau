package gui.fichas.fichasEmpleados;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;


import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;

public class FichaEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textNombre;
	public JTextField textAP1;
	public JTextField textAP2;
	public JTextField textDNI;
	public JTextField textOficina;
	private JButton btnSalir;
	public JButton btnBorrar;
	public JButton btnModificar;
	public JButton btnCrear;
	private HandlerFichaEmpleado handler;
	public JLabel lblDniInvalid;
	public JDateChooser dtChFechNac;
	public JDateChooser dtChFechAlt;
	public JCheckBox chckbxDespedido;
	public JDateChooser dtChFechBaj;
	public JButton btnBusquedaOfi;


	/**
	 * Create the frame.
	 */
	public FichaEmpleado() {
		
//		JFRAME
		
		handler = new HandlerFichaEmpleado(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 155, 569, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
//		PANEL FICHA
		
		JPanel panelFicha = new JPanel();
		panelFicha.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelFicha, BorderLayout.CENTER);
		panelFicha.setLayout(null);
		
		JLabel lblTitulo = new JLabel("GESTI\u00D3N DE EMPLEADOS");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(139, 11, 263, 33);
		panelFicha.add(lblTitulo);
		
		textDNI = new JTextField();
		textDNI.setBounds(131, 61, 86, 20);
		textDNI.addKeyListener(handler);
		panelFicha.add(textDNI);
		textDNI.setColumns(10);
		
		lblDniInvalid = new JLabel("X");
		lblDniInvalid.setToolTipText("El DNI insertado no es v\u00E1lido");
		lblDniInvalid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDniInvalid.setForeground(Color.RED);
		lblDniInvalid.setVisible(false);
		lblDniInvalid.setBounds(116, 63, 14, 14);
		panelFicha.add(lblDniInvalid);
		
		JButton btnBusquedaEmple = new JButton("");
		btnBusquedaEmple.setIcon(new ImageIcon(FichaEmpleado.class.getResource("/imgs/icons/zoom.png")));
		btnBusquedaEmple.setActionCommand("BUSQUEDAEMPLE");
		btnBusquedaEmple.setBounds(217, 61, 24, 20);
		panelFicha.add(btnBusquedaEmple);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(305, 61, 57, 14);
		panelFicha.add(lblNombre);
		
		JLabel lblPrimer_Apellido = new JLabel("Primer Apellido:");
		lblPrimer_Apellido.setBounds(282, 86, 96, 14);
		panelFicha.add(lblPrimer_Apellido);
		
		JLabel lblSegundo_Apellido = new JLabel("Segundo Apellido:*");
		lblSegundo_Apellido.setBounds(270, 115, 113, 14);
		panelFicha.add(lblSegundo_Apellido);
		
		textNombre = new JTextField();
		textNombre.setBounds(385, 55, 120, 20);
		panelFicha.add(textNombre);
		textNombre.setColumns(10);
		
		textAP1 = new JTextField();
		textAP1.setBounds(385, 83, 120, 20);
		panelFicha.add(textAP1);
		textAP1.setColumns(10);
		
		textAP2 = new JTextField();
		textAP2.setBounds(385, 112, 120, 20);
		panelFicha.add(textAP2);
		textAP2.setColumns(10);
		
		JLabel lblDNI = new JLabel("DNI: ");
		lblDNI.setBounds(50, 64, 32, 14);
		panelFicha.add(lblDNI);
		
		
		JLabel lblOficina = new JLabel("Oficina actual:");
		lblOficina.setBounds(20, 115, 86, 14);
		panelFicha.add(lblOficina);
		
		textOficina = new JTextField();
		textOficina.setBounds(131, 114, 86, 20);
		panelFicha.add(textOficina);
		textOficina.setColumns(10);
		
		btnBusquedaOfi = new JButton("");
		btnBusquedaOfi.setActionCommand("BUSQUEDAOFI");
		btnBusquedaOfi.setIcon(new ImageIcon(FichaEmpleado.class.getResource("/imgs/icons/zoom.png")));
		btnBusquedaOfi.setBounds(217, 114, 24, 19);
		panelFicha.add(btnBusquedaOfi);
		
		JLabel lblFechNac = new JLabel("Fecha nacimiento:");
		lblFechNac.setBounds(20, 87, 103, 14);
		panelFicha.add(lblFechNac);
		
		dtChFechNac = new JDateChooser("dd/MM/yyyy","##/##/####",'-');
		dtChFechNac.setBounds(131, 86, 107, 20);
		dtChFechNac.getJCalendar().setTodayButtonText("Hoy");
		dtChFechNac.getJCalendar().setTodayButtonVisible(true);
		dtChFechNac.getJCalendar().setWeekOfYearVisible(false);
		panelFicha.add(dtChFechNac);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(19, 148, 497, 1);
		panelFicha.add(separator);
		
		JLabel lblFechAlt = new JLabel("Fecha Alta:");
		lblFechAlt.setBounds(38, 162, 67, 14);
		panelFicha.add(lblFechAlt);
		
		dtChFechAlt = new JDateChooser("dd/MM/yyyy", "##/##/####", '-');
		dtChFechAlt.setBounds(110, 160, 107, 20);
		dtChFechAlt.getJCalendar().setTodayButtonText("Hoy");
		dtChFechAlt.getJCalendar().setTodayButtonVisible(true);
		dtChFechAlt.getJCalendar().setWeekOfYearVisible(false);
		panelFicha.add(dtChFechAlt);
		
		JLabel lblFechBaj = new JLabel("Fecha Baja:");
		lblFechBaj.setBounds(288, 162, 67, 14);
		panelFicha.add(lblFechBaj);
		
		dtChFechBaj = new JDateChooser("dd/MM/yyyy", "##/##/####", '-');
		dtChFechBaj.setBounds(393, 160, 107, 20);
		dtChFechBaj.getJCalendar().setTodayButtonText("Hoy");
		dtChFechBaj.getJCalendar().setTodayButtonVisible(true);
		dtChFechBaj.getJCalendar().setWeekOfYearVisible(false);
		dtChFechBaj.setEnabled(false);
		panelFicha.add(dtChFechBaj);
		
		chckbxDespedido = new JCheckBox("");
		chckbxDespedido.setActionCommand("DESPEDIDO");
		chckbxDespedido.addActionListener(handler);
		chckbxDespedido.setBackground(Color.LIGHT_GRAY);
		chckbxDespedido.setBounds(358, 158, 23, 23);
		panelFicha.add(chckbxDespedido);
		
		MFichaEmpleado.deshabilitaFicha(this);
//		PANEL INFERIOR
		
		JPanel panelInferior = new JPanel();
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
	}
	
	public static void LanzarFicha() {
		FichaEmpleado f = new FichaEmpleado();
		f.setVisible(true);
	}
}
