package fr.coVoiturage.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class Frame extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 4705673393736915148L;
	
	private Panel panel_acc = new Panel();
	
	private String title;
	
	JScrollPane scrollBar = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("Accueil");
	JMenuItem menuItem0 = new JMenuItem("Accueil", KeyEvent.VK_T);
	JMenuItem menuItem1 = new JMenuItem("Utilisateurs", KeyEvent.VK_T);
	JMenuItem menuItem2 = new JMenuItem("Co-Voiturages", KeyEvent.VK_T);
	
	public Frame() {
		
		menuItem0.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem0.getAccessibleContext().setAccessibleDescription("Accueil");
		
		menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem1.getAccessibleContext().setAccessibleDescription("Administration Utilisateur");
		
		menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		menuItem2.getAccessibleContext().setAccessibleDescription("Administration Co-Voiturages");
		
		menuItem0.addActionListener(this);
		menuItem1.addActionListener(this);
		menuItem2.addActionListener(this);
		
		menu.add(menuItem0);
		menu.add(menuItem1);
		menu.add(menuItem2);
		
		menuBar.add(menu);
	}
	
	public void createWindow(String title, int width, int height) {
		
		this.title = title;
		
		setTitle(title + " - Accueil");
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(new Panel());
		setJMenuBar(menuBar);
		add(scrollBar);
		setVisible(true);
	}
	
	private void changePane(int panel) {
		JPanel contentPane = (JPanel) getContentPane();
		
		if(panel == 0) {
			
			contentPane.removeAll();
			contentPane.add(new Panel());
			contentPane.revalidate(); 
			contentPane.repaint();
			setTitle(title + " - Accueil");
			panel_acc.changePanel(true);
		} else if(panel == 1) {
			
			contentPane.removeAll();
			contentPane.add(new PanelUsers());
			contentPane.revalidate(); 
			contentPane.repaint();
			setTitle(title + " - Utilisateur");
			panel_acc.changePanel(false);
		} else if(panel == 2) {
			
			contentPane.removeAll();
			contentPane.add(new PanelCoVoiturages());
			contentPane.revalidate(); 
			contentPane.repaint();
			setTitle(title + " - Co-Voiturages");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(menuItem0)) changePane(0);
		if(e.getSource().equals(menuItem1)) changePane(1);
		if(e.getSource().equals(menuItem2)) changePane(2);
	}
}
