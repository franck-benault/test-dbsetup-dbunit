package net.franckbenault.dbtest.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.util.List;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class UserQueriesTestWithDbUnit {

	private static DbManager dbManager;
	private static UserQueries userQueries;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbManager = new DbManager();
		dbManager.connexionDB();
		userQueries = new UserQueries(dbManager);
		
		IDatabaseConnection dc = new DatabaseConnection(dbManager.getConnection());
		InputStream is = UserQueriesTestWithDbUnit.class.getResourceAsStream("/users.xml");
        IDataSet dataSet = new XmlDataSet(is);
       DatabaseOperation.CLEAN_INSERT.execute(dc, dataSet);
       
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		dbManager.stopDB();
	}


	@Test
	public void testFindAllUsers() {
		List<User> users = userQueries.findAllUsers();
		assertNotNull(users);
		assertEquals(users.size(),3);
	}
	
	@Test
	public void testFindAllActiveUsers() {
		List<User> users = userQueries.findAllActiveUsers();
		assertNotNull(users);
		assertEquals(users.size(),2);
	}
	
	@Test
	public void testFindUserByLogin() {
		User user = userQueries.findUserByLogin("root");
		assertNotNull(user);
	}
	
	@Test
	public void testFindUserByLogin2() {
		User user = userQueries.findUserByLogin("guest");
		assertNotNull(user);
		System.out.println(user.getDeactivationDate());
	}
	
	@Test
	public void testFindUserByLogin_NotFound() {
		User user = userQueries.findUserByLogin("doesNotExist");
		assertNull(user);
	}

}
