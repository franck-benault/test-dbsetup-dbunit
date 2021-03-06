package net.franckbenault.dbtest.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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


public class UserQueriesTestWithDbUnit {

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
	public void testFindUserByLogin() {
		User user = userQueries.findUserByLogin("root");
		assertNotNull(user);
	}
	
	@Test
	public void testFindUserByLogin2() {
		User user = userQueries.findUserByLogin("guest");
		assertNotNull(user);
	}
	
	@Test
	public void testFindUserByLogin_NotFound() {
		User user = userQueries.findUserByLogin("doesNotExist");
		assertNull(user);
	}
	
	@Test
	public void testToString() {
		User user = new User(1,"MyLogin", "pwd");
		assertNotNull(user.toString());
		assertTrue(user.toString().contains("MyLogin"));
	}

	
	@Test
	public void testDeleteUsers() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		assertTrue(toDelete>0);
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());
	}
	
	@Test
	public void testDeleteUsers2() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		assertTrue(toDelete>0);
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
	
	@Test
	public void testDeleteUsers3() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		assertTrue(toDelete>0);
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
	
	@Test
	public void testDeleteUsers4() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		assertTrue(toDelete>0);
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
	
	@Test
	public void testDeleteUsers5() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		assertTrue(toDelete>0);
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
	
	@Test
	public void testDeleteUsers6() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		assertTrue(toDelete>0);
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
	
	@Test
	public void testDeleteUsers7() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		assertTrue(toDelete>0);
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
	
	@Test
	public void testDeleteUsers8() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		assertTrue(toDelete>0);
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
	
	@Test
	public void testDeleteUsers9() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		assertTrue(toDelete>0);
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
	
	@Test
	public void testDeleteUsers10() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		assertTrue(toDelete>0);
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
}
