package net.franckbenault.dbtest.sample;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.ninja_squad.dbsetup.Operations;

public class UserQueriesTestWithDbSetup {

	private static DbManager dbManager;
	private static UserQueries userQueries;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbManager = new DbManager();
		dbManager.connexionDB();
		userQueries = new UserQueries(dbManager);
		
		Operation operation = 
				Operations.sequenceOf(
		                DBSetupCommonOperations.DELETE_ALL,
		                DBSetupCommonOperations.INSERT_USERS_DATA);
				
	
		DbSetup dbSetup = new DbSetup(new DataSourceDestination(dbManager.getDataSource()), operation);
		dbSetup.launch();
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
	}
	
	@Test
	public void testFindUserByLogin_NotFound() {
		User user = userQueries.findUserByLogin("doesNotExist");
		assertNull(user);
	}
	
	
}
