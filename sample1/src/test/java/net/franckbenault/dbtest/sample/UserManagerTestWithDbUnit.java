package net.franckbenault.dbtest.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.List;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.stream.IDataSetProducer;
import org.dbunit.dataset.stream.StreamingDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.InputSource;


public class UserManagerTestWithDbUnit {

	private static UserManager userManager;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userManager = new UserManager();
		userManager.connexionDB();

		
		// initialize your database connection here
		IDatabaseConnection dc = new DatabaseConnection(userManager.getConnection());
        // ...

        // initialize your dataset here 
		InputStream is = UserManagerTestWithDbUnit.class.getResourceAsStream("/users.xml");
       
        IDataSet dataSet = new XmlDataSet(is);
        // ...

       DatabaseOperation.CLEAN_INSERT.execute(dc, dataSet);
       
		
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
