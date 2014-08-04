package net.franckbenault.dbtest.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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


		
		// initialize your database connection here
		IDatabaseConnection dc = new DatabaseConnection(dbManager.getConnection());
        // ...

        // initialize your dataset here 
		InputStream is = UserQueriesTestWithDbUnit.class.getResourceAsStream("/users.xml");
       
        IDataSet dataSet = new XmlDataSet(is);
        // ...

       DatabaseOperation.CLEAN_INSERT.execute(dc, dataSet);
       
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		dbManager.stopDB();
	}


	@Test
	public void testGetAllUsers() {
		List<User> users = userQueries.getAllUsers();
		assertNotNull(users);
		assertEquals(users.size(),2);
	}

}
