package net.franckbenault.dbtest.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.DatabaseUnitException;
import org.dbunit.assertion.DbUnitAssert;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatDtdWriter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
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
	public void testDeleteUsers() throws DatabaseUnitException, SQLException, FileNotFoundException {
		List<User> users = userQueries.findAllUsers();
		int toDelete = users.size();
		
		int deleted = userQueries.deleteUsers();
		
		assertTrue(toDelete==deleted);
		
		users = userQueries.findAllUsers();
		assertTrue(users.isEmpty());
		
	      // Fetch database data after executing your code
		IDatabaseConnection dc = new DatabaseConnection(dbManager.getConnection());		
        IDataSet databaseDataSet = dc.createDataSet();
        ITable actualTable = databaseDataSet.getTable("USERS");

        // Load expected data from an XML dataset
		InputStream is = UserQueriesTestWithDbUnit.class.getResourceAsStream("/usersEmpty.xml");
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
        ITable expectedTable = expectedDataSet.getTable("USERS");

        // Assert actual database table match expected table
        new DbUnitAssert().assertEquals(expectedTable, actualTable);
	}
	
	@Test
	public void testDeleteUsersPerLogin() throws DatabaseUnitException, SQLException, FileNotFoundException {
		
		int deleted = userQueries.deleteUsersPerLogin("guest");
		
		assertTrue(deleted==1);
		

		
	      // Fetch database data after executing your code
		IDatabaseConnection dc = new DatabaseConnection(dbManager.getConnection());		
        IDataSet databaseDataSet = dc.createDataSet();
        ITable actualTable = databaseDataSet.getTable("USERS");

        // Load expected data from an XML dataset
		InputStream is = UserQueriesTestWithDbUnit.class.getResourceAsStream("/usersWithoutGuest.xml");
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
        ITable expectedTable = expectedDataSet.getTable("USERS");

        // Assert actual database table match expected table
        new DbUnitAssert().assertEquals(expectedTable, actualTable);
	}

}
