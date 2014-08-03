package net.franckbenault.dbtest.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserManager {
		 
		/** Service Connexion. */
		private Connection connexion;
	 
		/** driver JDBC. */
		private String jdbcDriver = "org.hsqldb.jdbcDriver";
	 
		/** mode mémoire. */
		private String database = "jdbc:hsqldb:mem:database";
	 
		/** utilisateur qui se connecte à la base de données. */
		private String user = "sa";
	 
		/** mot de passe pour se connecter à la base de données. */
		private String password = "";
	 
		/**
		 * Fonction de connexion à la base de données.
		 */
		public void connexionDB() {
			try {
				// On commence par charger le driver JDBC d'HSQLDB
				Class.forName(jdbcDriver).newInstance();
			} catch (InstantiationException e) {
				System.out.println("ERROR: failed to load HSQLDB JDBC driver.");
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	 
			try {
				// Puis on se connecte à la base de données en mode mémoire
				connexion = DriverManager.getConnection(database, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	 
		/**
		 * Arrête correctement HSQLDB.
		 * @throws SQLException SQL exception
		 */
		public void stopDB() throws SQLException {
			Statement st = connexion.createStatement();
	 
			// On envoie l'instruction pour arreter proprement HSQLDB
			st.execute("SHUTDOWN");
			// On ferme la connexion
			connexion.close();
	 
		}
		
		public List<User> getUsers() {
			
			return null;
		}
}
