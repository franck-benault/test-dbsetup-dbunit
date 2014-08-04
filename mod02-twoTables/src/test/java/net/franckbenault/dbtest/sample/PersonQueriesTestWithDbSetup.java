package net.franckbenault.dbtest.sample;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;

public class PersonQueriesTestWithDbSetup {
	
	private static DbManager dbManager;
	private static PersonQueries personQueries;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbManager = new DbManager();
		dbManager.connexionDB();
		personQueries = new PersonQueries(dbManager);
		
		Operation operation = 
				Operations.sequenceOf(
		                DBSetupCommonOperations.DELETE_ALL,
		                DBSetupCommonOperations.INSERT_PERSONS_DATA,
		                DBSetupCommonOperations.INSERT_ADDRESS_DATA);
				
	
		DbSetup dbSetup = new DbSetup(new DataSourceDestination(dbManager.getDataSource()), operation);
		dbSetup.launch();
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
