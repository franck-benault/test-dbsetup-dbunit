package net.franckbenault.dbtest.sample;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;

public class UserQueriesRepeatingValue {

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
		                DBSetupCommonOperations.INSERT_USERS_DATA_REPEATING);
				
	
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
		assertEquals(users.size(),10);
	}

	@Test
	public void testFindUserByLogin() {
		User user = userQueries.findUserByLogin("user-5");
		assertNotNull(user);
		assertEquals(user.getId(),5);
		assertEquals(user.getPassword(),"pwd-5");
	}
}
