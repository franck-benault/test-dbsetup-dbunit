package net.franckbenault.dbtest.bug31;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;


public class UserQueriesTestWithDbUnitDbSetup {

	private static DbManager dbManager;
	private static BuildingQueries buildingQueries;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbManager = new DbManager();
		dbManager.connexionDB();
		buildingQueries = new BuildingQueries(dbManager);
	}
	
	@Before
	public void setUp() {
		
	      
		Operation operation = 
				Operations.sequenceOf(
						DBSetupCommonOperations.DELETE_ALL,
		                DBSetupCommonOperations.INSERT_BUILDING_DATA);
				
	
		DbSetup dbSetup = new DbSetup(new DataSourceDestination(dbManager.getDataSource()), operation);
		dbSetup.launch();
       
		
	}
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		dbManager.stopDB();
	}


	@Test
	public void testFindAllBuilings() {
		List<Building> buildings = buildingQueries.findAllBuildings();
		assertNotNull(buildings);
		assertEquals(buildings.size(),1);
	}
	
	

}
