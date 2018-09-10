package fr.coVoiturage.panel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panel extends JPanel{
	
	public static boolean isAccueil = true;
	
	private static final long serialVersionUID = -946189683474513654L;
	
	private Font titleFont = new Font("AR JULIAN", Font.BOLD, 17);
	
	public Panel() {
		
		setFocusable(true);
		requestFocus();
	}
	
	public void changePanel(boolean acc) {
		
		isAccueil = acc;
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		
		if(isAccueil) {
			
			g2.setFont(titleFont);
			g2.drawString("Logiciel d'administration", 20, 25);
		}
	}
}
