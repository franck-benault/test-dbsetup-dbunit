package net.franckbenault.dbtest.sample;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.ninja_squad.dbsetup.Operations;

public class UserManagerTestWithDbSetup {

	private static UserManager userManager;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userManager = new UserManager();
		userManager.connexionDB();
		
		Operation operation = 
				Operations.sequenceOf(
		                DBSetupCommonOperations.DELETE_ALL,
		                DBSetupCommonOperations.INSERT_USERS_DATA);
				
	
		DbSetup dbSetup = new DbSetup(new DataSourceDestination(userManager.getDataSource()), operation);
		dbSetup.launch();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		userManager.stopDB();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUsers() {
		List<User> users = userManager.getUsers();
		assertNotNull(users);
		assertEquals(users.size(),2);
	}

}
