package net.franckbenault.dbtest.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;


public class UserQueriesTestWithDbUnitDbSetup {

	private static DbManager dbManager;
	private static UserQueries userQueries;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbManager = new DbManager();
		dbManager.connexionDB();
		userQueries = new UserQueries(dbManager);
	}
	
	@Before
	public void setUp() throws DatabaseUnitException, SQLException {
		
		IDatabaseConnection dc = new DatabaseConnection(dbManager.getConnection());
		InputStream is = UserQueriesTestWithDbUnitDbSetup.class.getResourceAsStream("/users.xml");
        IDataSet dataSet = new XmlDataSet(is);
        DatabaseOperation.CLEAN_INSERT.execute(dc, dataSet);
       
		Operation operation = 
				Operations.sequenceOf(
		                DBSetupCommonOperations.INSERT_USERS_DATA);
				
	
		DbSetup dbSetup = new DbSetup(new DataSourceDestination(dbManager.getDataSource()), operation);
		dbSetup.launch();
       
		dbManager.executUpdate("INSERT INTO USERS(ID,LOGIN,PASSWORD) VALUES('7','guest7', 'guest')");
		
	}
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		dbManager.stopDB();
	}


	@Test
	public void testFindAllUsers() {
		List<User> users = userQueries.findAllUsers();
		assertNotNull(users);
		assertEquals(users.size(),7);
	}
	

	
	@Test
	public void testFindUserByLogin() {
		User user = userQueries.findUserByLogin("root");
		assertNotNull(user);
	}
	

	@Test
	public void testFindUserByLogin_NotFound() {
		User user = userQueries.findUserByLogin("doesNotExist");
		assertNull(user);
	}
	

}
