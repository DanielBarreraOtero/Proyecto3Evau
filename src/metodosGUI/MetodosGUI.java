package metodosGUI;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

import metodos2.Metodos2;

public class MetodosGUI {
	
	public static void deshabilitaPanel(JPanel panel, JComponent[] excepciones, boolean setInvisible) {
		
		Component[] componentes = panel.getComponents();
		
		ArrayList<Object> excps = Metodos2.arrayToArrayList(excepciones);
		
		for (Component i: componentes) {
			if (i.getClass().getName().equals("javax.swing.JPanel")) {
				deshabilitaPanel((JPanel) i, excepciones, setInvisible);
			}
			
			if (!excps.contains(i) && !i.getClass().getName().equals("javax.swing.JLabel")) {
				i.setEnabled(false);
				if (setInvisible) {
					i.setVisible(false);
				}
			}
		}
		
	}
	
}
