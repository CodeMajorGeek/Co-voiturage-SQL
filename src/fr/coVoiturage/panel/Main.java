package fr.coVoiturage.panel;

import fr.coVoiturage.SQL.SQLConnection;

public class Main {
	
	public static Frame frame = new Frame();
	
	public static SQLConnection sql = new SQLConnection();
	
	public static void main(String[] args) {
		
		frame.createWindow("Co-Voiturage", 1400, 800);
		
		sql.connection("jdbc:mysql://localhost:3306/covoiturages?verifyServerCertificate=false&useSSL=false", "root", "");
		sql.receiveUser("SELECT * FROM users");
		sql.receiveCoVoiturage("SELECT * FROM covoiturages");
	}

}
