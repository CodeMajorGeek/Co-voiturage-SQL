package fr.coVoiturage.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import fr.coVoiturage.utils.GroupedInfoCoVoiturage;
import fr.coVoiturage.utils.GroupedInfoUsers;

public class SQLConnection {
	
	private Connection connection;
	private Statement statement;
	
	public void connection(String host, String user, String passwd) {
		
		try {
			
			connection = DriverManager.getConnection(host, user, passwd);
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Erreur de connetion à la base de donnée SQL !", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void sendCommand(String command) {
		
		try {
			
			statement = connection.createStatement();
			statement.execute(command);
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Erreur d'envoi de commande à la base de donnée SQL !", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void receiveUser(String command) {
		
		try {
			
			statement = connection.createStatement();
			ResultSet res = statement.executeQuery(command);
			
			while(res.next()){
				
				GroupedInfoUsers.addInfo(res.getInt("id"), res.getString("email"), res.getTimestamp("created_at"), res.getString("nom"), res.getString("prenom"), res.getString("genre"), res.getDate("date_nais"), res.getString("num_tel"));
			}
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Erreur d'envoi de commande à la base de donnée SQL !", "Error (User)", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void receiveCoVoiturage(String command) {
		
		try {
			
			statement = connection.createStatement();
			ResultSet res = statement.executeQuery(command);
			
			while(res.next()){
				
				GroupedInfoCoVoiturage.addInfo(res.getInt("id"), res.getTimestamp("date_depart"), res.getString("vehicule"), res.getInt("prix"));
			}
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Erreur d'envoi de commande à la base de donnée SQL !", "Error (CoVoiturage)", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
