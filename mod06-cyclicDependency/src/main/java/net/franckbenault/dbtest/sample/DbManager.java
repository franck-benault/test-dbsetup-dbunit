package net.franckbenault.dbtest.sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCDataSource;

public class DbManager {
	
	
	/*Operation insertVendorsAndProducts = 
	        sequenceOf(
	            insertInto("VENDOR")
	                .columns("ID", "CODE", "NAME")
	                .values(1L, "AMA", "AMAZON")
	                .build(),
	            insertInto("PRODUCT")
	                .columns("ID", "NAME", "VENDOR_ID")
	                .values(1L, "Kindle", "1L")
	                .build(),
	            sql("update VENDOR set FEATURED_PRODUCT_ID = 1 where ID = 1"));
	            */
	
    
    private String requestCreateTableVendors = "CREATE TABLE VENDOR ( ID INTEGER IDENTITY, VCODE VARCHAR(256), NAME VARCHAR(256), FEATURED_PRODUCT_ID INTEGER)";
    private String requestCreateTableProducts = "CREATE TABLE PRODUCT ( ID INTEGER IDENTITY, NAME VARCHAR(256), VENDOR_ID INTEGER, CONSTRAINT VENDOR_ID FOREIGN KEY(VENDOR_ID) REFERENCES VENDOR (ID))";
  
    private String requestUpdateTableProducts = ""
    		+ " ALTER TABLE VENDOR ADD CONSTRAINT FEATURED_PRODUCT_ID FOREIGN KEY(FEATURED_PRODUCT_ID) REFERENCES PRODUCT (ID)";
		 
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
				executRequest(requestCreateTableVendors);
				executRequest(requestCreateTableProducts);
				executRequest(requestUpdateTableProducts);
				//executRequest(requestUser1);
				//executRequest(requestUser2);
				//executRequest(requestUser3);
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

		public int executUpdate(String requete) throws SQLException {
			Statement statement;
			statement = connection.createStatement();
			int resultat = statement.executeUpdate(requete);
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
