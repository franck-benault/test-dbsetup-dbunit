package net.franckbenault.dbtest.sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCDataSource;

public class DbManager {
	
    private String requestCreateTablesUsers = "CREATE TABLE USERS ( ID INTEGER IDENTITY, LOGIN VARCHAR(256), PASSWORD VARCHAR(256))";
		 
		/** Service Connexion. */
		private JDBCDataSource dataSource;
		private Connection connection;
	 
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
				connection = dataSource.getConnection(user, password);
				executRequest(requestCreateTablesUsers);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public Connection getConnection() {
		      return connection;     
		}
		
		public DataSource getDataSource() {
		      return dataSource;
		}
		
		public ResultSet executRequest(String requete) throws SQLException {
			Statement statement;
			statement = connection.createStatement();
			ResultSet resultat = statement.executeQuery(requete);
			return resultat;
		}
	 
		/**
		 * Arrête correctement HSQLDB.
		 * @throws SQLException SQL exception
		 */
		public void stopDB() throws SQLException {
			Statement st = connection.createStatement();
	 
			// On envoie l'instruction pour arreter proprement HSQLDB
			st.execute("SHUTDOWN");
			// On ferme la connexion
			connection.close();
	 
		}
}
