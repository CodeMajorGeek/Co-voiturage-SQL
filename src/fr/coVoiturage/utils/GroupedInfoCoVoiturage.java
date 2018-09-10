package fr.coVoiturage.utils;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import fr.coVoiturage.panel.Main;
import fr.coVoiturage.panel.PanelCoVoiturages;

public class GroupedInfoCoVoiturage implements ActionListener{
	
	private static ArrayList<JButton> show = new ArrayList<JButton>();
	private static ArrayList<JButton> hide = new ArrayList<JButton>();
	private static ArrayList<JButton> delete = new ArrayList<JButton>();
	
	private static ArrayList<String> nom = new ArrayList<String>();
	private static ArrayList<String> prenom = new ArrayList<String>();
	private static ArrayList<Timestamp> date_depart = new ArrayList<Timestamp>();
	private static ArrayList<String> vehicule = new ArrayList<String>();
	private static ArrayList<Integer> prix = new ArrayList<Integer>();
	
	public static void addInfo(int id, Timestamp date_depart, String vehicule, int prix) {
		
		GroupedInfoCoVoiturage.nom.add(GroupedInfoUsers.getNom(id));
		GroupedInfoCoVoiturage.prenom.add(GroupedInfoUsers.getPrenom(id));
		GroupedInfoCoVoiturage.date_depart.add(date_depart);
		GroupedInfoCoVoiturage.vehicule.add(vehicule);
		GroupedInfoCoVoiturage.prix.add(prix);
	}
	
	public JPanel getAllGroupedInfo(int index) {
		JPanel panel = new JPanel();
		
		if(hide.size() >= index) {
			
			hide.add(new JButton("Moin"));
			hide.get(index).addActionListener(this);
			delete.add(new JButton("Supprimer"));
			delete.get(index).addActionListener(this);
		}
		
		panel.add(new JLabel("Nom: " + nom.get(index)));
		panel.add(new JLabel("Prénom: " + prenom.get(index)));
		panel.add(new JLabel("Départ: " + date_depart.get(index).toString()));
		panel.add(new JLabel("Vehicule: " + vehicule.get(index)));
		panel.add(new JLabel("Prix: " + prix.get(index)));
		
		panel.add(hide.get(index));
		panel.add(delete.get(index));
		
		panel.setBorder(new CompoundBorder(new LineBorder(Color.lightGray), new EmptyBorder(4,4,4,4)));
		
		return panel;
	}
	
	public JPanel getCondencedInfo(int index) {
		JPanel panel = new JPanel();
		if(show.size() >= index) {
			
			show.add(new JButton("Plus"));
			show.get(index).addActionListener(this);
		}
		
		panel.add(new JLabel(nom.get(index) + " " + prenom.get(index)));
		panel.add(show.get(index));
		
		panel.setBorder(new CompoundBorder(new LineBorder(Color.lightGray), new EmptyBorder(4,4,4,4)));
		
		return panel;
	}
	
	public int getSize() {
		
		return nom.size();
	}
	
	public static void removeAll() {
		
		nom.clear();
		prenom.clear();
		date_depart.clear();
		vehicule.clear();
		prix.clear();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i < getSize(); i++) {
			
			if(e.getSource().equals(show.get(i))) {
				
				PanelCoVoiturages.getMainPanel.showInfo.set(i, getAllGroupedInfo(i));
				PanelCoVoiturages.getMainPanel.reshow();
			}
			
			if(e.getSource().equals(hide.get(i))) {
				
				PanelCoVoiturages.getMainPanel.showInfo.set(i, getCondencedInfo(i));
				PanelCoVoiturages.getMainPanel.reshow();
			}
			
			if(e.getSource().equals(delete.get(i))) {
				
				Main.sql.sendCommand("DELETE FROM `covoiturages` WHERE `covoiturages`.`id` = " + (i + 1));
				removeAll();
				Main.sql.receiveUser("SELECT * FROM covoiturages");
				PanelCoVoiturages.getMainPanel.showInfo.clear();
				
				for(int u = 0; u < getSize(); u++) {
					
					PanelCoVoiturages.getMainPanel.showInfo.add(getCondencedInfo(u));
				}
				
				PanelCoVoiturages.getMainPanel.reshow();
			}
		}
	}
	
}
