package fr.coVoiturage.panel;

import java.util.ArrayList;

import javax.swing.JPanel;

import fr.coVoiturage.utils.GroupedInfoUsers;

public class PanelUsers extends JPanel{
	
	private static final long serialVersionUID = -946189683474513654L;
	
	private static GroupedInfoUsers infoUsers = new GroupedInfoUsers();
	
	public ArrayList<JPanel> showInfo = new ArrayList<JPanel>();
	
	public static PanelUsers getMainPanel;
	
	public PanelUsers() {
		
		setFocusable(true);
		requestFocus();
		
		getMainPanel = this;
		
		for(int i = 0; i < infoUsers.getSize(); i++) {
			
			showInfo.add(infoUsers.getCondencedInfo(i));
			add(showInfo.get(i));
		}
	}
	
	public void reshow() {
		
		removeAll();
		revalidate();
		
		for(int i = 0; i < showInfo.size(); i++) add(showInfo.get(i));
	}
}
