package net.franckbenault.dbtest.sample;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class PersonQueriesTestWithDbUnit {
	
	private static DbManager dbManager;
	private static PersonQueries personQueries;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbManager = new DbManager();
		dbManager.connexionDB();
		personQueries = new PersonQueries(dbManager);
		
		IDatabaseConnection dc = new DatabaseConnection(dbManager.getConnection());
		InputStream is = PersonQueriesTestWithDbUnit.class.getResourceAsStream("/persons.xml");
        IDataSet dataSet = new XmlDataSet(is);
       DatabaseOperation.CLEAN_INSERT.execute(dc, dataSet);
       
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dbManager.stopDB();
	}

	@Test
	public void testFindAllPersons() {
		List<Person> persons = personQueries.findAllPersons();
		assertNotNull(persons);
		assertEquals(persons.size(),4);
	}

	@Test
	@Ignore
	public void testFindPersonsByFirstName() {
		List<Person> persons = personQueries.findPersonsByFirstName("Sahra");
		assertNotNull(persons);
		assertEquals(persons.size(),2);
	}

	@Test
	@Ignore
	public void testFindPersonsByFirstName_NotFound() {
		List<Person> persons = personQueries.findPersonsByFirstName("DoesNotExist");
		assertNotNull(persons);
		assertEquals(persons.size(),0);
	}
	
	@Test
	@Ignore
	public void testFindPersonsByLastName() {
		List<Person> persons = personQueries.findPersonsByLastName("Smith");
		assertNotNull(persons);
		assertEquals(persons.size(),3);
	}

	@Test
	@Ignore
	public void testFindPersonsByFirstNameLastName() {
		List<Person> persons = personQueries.findPersonsByFirstNameLastName("Sahra","Smith");
		assertNotNull(persons);
		assertEquals(persons.size(),1);		
	}

}
