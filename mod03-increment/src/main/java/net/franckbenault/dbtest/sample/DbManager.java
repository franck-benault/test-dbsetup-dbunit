package net.franckbenault.dbtest.sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCDataSource;

public class DbManager {
	
    private String requestCreateTablesUsers = "CREATE TABLE USERS ( ID INTEGER IDENTITY, LOGIN VARCHAR(256), PASSWORD VARCHAR(256), DESCRIPTION VARCHAR(256))";
    private String requestUser1 = "INSERT INTO USERS(ID,LOGIN,PASSWORD) VALUES('1','straumat', 'straumat16')";
    private String requestUser2 = "INSERT INTO USERS(ID,LOGIN,PASSWORD) VALUES('2','jgoncalves', 'jgoncalves16')";
    private String requestUser3 = "INSERT INTO USERS(ID,LOGIN,PASSWORD) VALUES('3','sgoumard', 'sgoumard16')";
		 
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
		 * Function connection to the db
		 */
		public void connexionDB() {
			try {
				// loading JDBC driver for HSQLDB
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
		 * stop correctly HSQLDB.
		 * @throws SQLException SQL exception
		 */
		public void stopDB() throws SQLException {
			Statement st = connection.createStatement();
	 
			// send the order to stop correctly HSQLDB
			st.execute("SHUTDOWN");
			// close the connection
			connection.close();
	 
		}
}
