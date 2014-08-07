package net.franckbenault.dbtest.sample;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.ninja_squad.dbsetup.Operations;

public class UserQueriesTestWithDbSetup {

	private static DbManager dbManager;
	private static UserQueries userQueries;
	private static DbSetupTracker dbSetupTracker = new DbSetupTracker();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbManager = new DbManager();
		dbManager.connexionDB();
		userQueries = new UserQueries(dbManager);
	}
	
	@Before
	public void setUp() {	
		Operation operation = 
				Operations.sequenceOf(
		                DBSetupCommonOperations.DELETE_ALL,
		                DBSetupCommonOperations.INSERT_USERS_DATA);
				
	
		DbSetup dbSetup = new DbSetup(new DataSourceDestination(dbManager.getDataSource()), operation);
		dbSetupTracker.launchIfNecessary(dbSetup);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		dbManager.stopDB();
	}


	@Test
	public void testFindAllUsers() {
		dbSetupTracker.skipNextLaunch();
		
		List<User> users = userQueries.findAllUsers();
		assertNotNull(users);
		assertEquals(users.size(),3);
	}
	

	@Test
	public void testFindUserByLogin() {
		dbSetupTracker.skipNextLaunch();
		
		User user = userQueries.findUserByLogin("root");
		assertNotNull(user);
	}
	
	@Test
	public void testFindUserByLogin2() {
		dbSetupTracker.skipNextLaunch();
		
		User user = userQueries.findUserByLogin("guest");
		assertNotNull(user);
	}
	
	@Test
	public void testFindUserByLogin_NotFound() {
		dbSetupTracker.skipNextLaunch();
		
		User user = userQueries.findUserByLogin("doesNotExist");
		assertNull(user);
	}
	
	@Test
	public void testToString() {
		dbSetupTracker.skipNextLaunch();
		
		User user = new User(1,"MyLogin", "pwd");
		assertNotNull(user.toString());
		assertTrue(user.toString().contains("MyLogin"));
	}
	

	@Test
	public void testDeleteUsers() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());
	}
	
	@Test
	public void testDeleteUsers2() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
	
	@Test
	public void testDeleteUsers3() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
	
	@Test
	public void testDeleteUsers4() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
	
	@Test
	public void testDeleteUsers5() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}

	
	@Test
	public void testDeleteUsers6() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
	
	@Test
	public void testDeleteUsers7() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
	
	@Test
	public void testDeleteUsers8() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
	
	@Test
	public void testDeleteUsers9() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
	
	@Test
	public void testDeleteUsers10() {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());	
	}
}
