package net.franckbenault.dbtest.sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCDataSource;

public class UserManager {
	
    private String requestCreateTablesUsers = "CREATE TABLE USERS ( idUser INTEGER IDENTITY, login VARCHAR(256), password VARCHAR(256))";
    
		 
		/** Service Connexion. */
		private JDBCDataSource dataSource;
		private Connection connexion;
	 
		/** driver JDBC. */
		private String jdbcDriver = "org.hsqldb.jdbcDriver";
	 
		/** memory mode. */
		private String database = "jdbc:hsqldb:mem:database";
	 
		/** user for DB connection. */
		private String user = "sa";
	 
		/** password for DB connection */
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
				dataSource = new JDBCDataSource();
		
				dataSource.setDatabase(database);
				connexion = dataSource.getConnection();
				executerRequest(requestCreateTablesUsers);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public DataSource getDataSource() {
		      return dataSource;

		      
		}
		
		public ResultSet executerRequest(String requete) throws SQLException {
			Statement statement;
			statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery(requete);
			return resultat;
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
			List<User> users = new ArrayList<User>();
			return users;
		}
}
