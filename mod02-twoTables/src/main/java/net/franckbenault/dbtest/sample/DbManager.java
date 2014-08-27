package net.franckbenault.dbtest.sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCDataSource;

public class DbManager {
	
    private String requestCreateTablesUsers = "CREATE TABLE PERSONS ( ID INTEGER IDENTITY, LAST_NAME VARCHAR(256), FIRST_NAME VARCHAR(256))";
    private String requestUser1 = "INSERT INTO PERSONS(ID,LAST_NAME,FIRST_NAME) VALUES('1','Smith', 'William')";
    private String requestUser2 = "INSERT INTO PERSONS(ID,LAST_NAME,FIRST_NAME) VALUES('2','Dupond', 'Harry')";
    private String requestUser3 = "INSERT INTO PERSONS(ID,LAST_NAME,FIRST_NAME) VALUES('3','Dupont', 'Sarah')";
 
    private String requestCreateTablesAddresses = "CREATE TABLE ADDRESS ( ID INTEGER IDENTITY, TOWN VARCHAR(256), STREET VARCHAR(256), ID_PERSON INTEGER, CONSTRAINT ID_PERSON FOREIGN KEY(ID_PERSON) REFERENCES PERSONS (ID))";
    private String requestAddress1 = "INSERT INTO ADDRESS(ID,TOWN,STREET,ID_PERSON) VALUES('1','Paris', 'rue du pont',1)";
    private String requestAddress2 = "INSERT INTO ADDRESS(ID,TOWN,STREET,ID_PERSON) VALUES('2','Lille', 'rue de la poire',1)";
   
    
    
		/** Service Connection. */
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
		 * connection to the database
		 */
		public void connexionDB() {
			try {
				//loading JDBC driver for HSQLDB
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
				executRequest(requestUser1);
				executRequest(requestUser2);
				executRequest(requestUser3);
				executRequest(requestCreateTablesAddresses);
				executRequest(requestAddress1);
				executRequest(requestAddress2);
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
		 * stop HSQLDB.
		 * @throws SQLException SQL exception
		 */
		public void stopDB() throws SQLException {
			Statement st = connection.createStatement();
	 
			// stop order to HSQLDB
			st.execute("SHUTDOWN");
			// close the connection
			connection.close();
	 
		}
}
