package fr.coVoiturage.utils;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import fr.coVoiturage.panel.Main;
import fr.coVoiturage.panel.PanelUsers;

public class GroupedInfoUsers implements ActionListener{
	
	private static ArrayList<JButton> show = new ArrayList<JButton>();
	private static ArrayList<JButton> hide = new ArrayList<JButton>();
	private static ArrayList<JButton> delete = new ArrayList<JButton>();
	
	private static ArrayList<Integer> id = new ArrayList<Integer>();
	private static ArrayList<String> email = new ArrayList<String>();
	private static ArrayList<Timestamp> created_at = new ArrayList<Timestamp>();
	private static ArrayList<String> nom = new ArrayList<String>();
	private static ArrayList<String> prenom = new ArrayList<String>();
	private static ArrayList<String> genre = new ArrayList<String>();
	private static ArrayList<Date> date_nais = new ArrayList<Date>();
	private static ArrayList<String> num_tel = new ArrayList<String>();
	
	public static void addInfo(int id, String email, Timestamp created_at, String nom, String prenom, String genre, Date date_nais, String num_tel) {
		
		GroupedInfoUsers.id.add(id);
		GroupedInfoUsers.email.add(email);
		GroupedInfoUsers.created_at.add(created_at);
		GroupedInfoUsers.nom.add(nom);
		GroupedInfoUsers.prenom.add(prenom);
		GroupedInfoUsers.genre.add(genre);
		GroupedInfoUsers.date_nais.add(date_nais);
		GroupedInfoUsers.num_tel.add(num_tel);
	}
	
	public JPanel getAllGroupedInfo(int index) {
		JPanel panel = new JPanel();
		
		if(hide.size() >= index) {
			
			hide.add(new JButton("Moin"));
			hide.get(index).addActionListener(this);
			delete.add(new JButton("Supprimer"));
			delete.get(index).addActionListener(this);
		}
		
		panel.add(new JLabel("Email: " + email.get(index)));
		panel.add(new JLabel("Crée le: " + created_at.get(index).toString()));
		panel.add(new JLabel("Nom: " + nom.get(index)));
		panel.add(new JLabel("Prénom: " + prenom.get(index)));
		panel.add(new JLabel("Sex: " + genre.get(index)));
		panel.add(new JLabel("Date de naissance: " + date_nais.get(index)));
		panel.add(new JLabel("numéro de téléphone: " + num_tel.get(index)));
		
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
		
		return email.size();
	}
	
	public static void removeAll() {
		
		email.clear();
		created_at.clear();
		nom.clear();
		prenom.clear();
		genre.clear();
		date_nais.clear();
		num_tel.clear();
	}
	
	public static String getNom(int id) {
		
		return nom.get(id - 1);
	}
	
	public static String getPrenom(int id) {
		
		return prenom.get(id - 1);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i < getSize(); i++) {
			
			if(e.getSource().equals(show.get(i))) {
				
				PanelUsers.getMainPanel.showInfo.set(i, getAllGroupedInfo(i));
				PanelUsers.getMainPanel.reshow();
			}
			
			if(e.getSource().equals(hide.get(i))) {
				
				PanelUsers.getMainPanel.showInfo.set(i, getCondencedInfo(i));
				PanelUsers.getMainPanel.reshow();
			}
			
			if(e.getSource().equals(delete.get(i))) {
				
				Main.sql.sendCommand("DELETE FROM `users` WHERE `users`.`id` = " + (i + 1));
				removeAll();
				Main.sql.receiveUser("SELECT * FROM users");
				PanelUsers.getMainPanel.showInfo.clear();
				
				for(int u = 0; u < getSize(); u++) {
					
					PanelUsers.getMainPanel.showInfo.add(getCondencedInfo(u));
				}
				
				PanelUsers.getMainPanel.reshow();
			}
		}
	}
}
