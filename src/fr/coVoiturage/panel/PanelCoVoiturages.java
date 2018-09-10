package fr.coVoiturage.panel;

import java.util.ArrayList;

import javax.swing.JPanel;

import fr.coVoiturage.utils.GroupedInfoCoVoiturage;

public class PanelCoVoiturages extends JPanel{
	
	private static final long serialVersionUID = 2049981527357058448L;
	
	private static GroupedInfoCoVoiturage infoCoVoiturage = new GroupedInfoCoVoiturage();
	
	public ArrayList<JPanel> showInfo = new ArrayList<JPanel>();
	
	public static PanelCoVoiturages getMainPanel;
	
	public PanelCoVoiturages() {
		
		setFocusable(true);
		requestFocus();
		
		getMainPanel = this;
		
		for(int i = 0; i < infoCoVoiturage.getSize(); i++) {
			
			showInfo.add(infoCoVoiturage.getCondencedInfo(i));
			add(showInfo.get(i));
		}
	}
	
	public void reshow() {
		
		removeAll();
		revalidate();
		
		for(int i = 0; i < showInfo.size(); i++) add(showInfo.get(i));
	}
}
