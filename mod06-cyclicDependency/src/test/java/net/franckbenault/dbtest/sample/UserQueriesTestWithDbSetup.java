package net.franckbenault.dbtest.sample;

import static org.junit.Assert.*;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.ninja_squad.dbsetup.Operations;

public class UserQueriesTestWithDbSetup {

	private static DbManager dbManager;
	private static VendorQueries vendorQueries;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbManager = new DbManager();
		dbManager.connexionDB();
		vendorQueries = new VendorQueries(dbManager);
	}
	
	@Before
	public void setUp() {	
		Operation operation = 
				Operations.sequenceOf(
		                DBSetupCommonOperations.DELETE_ALL,
		                DBSetupCommonOperations.insertVendorsAndProducts);
				
	
		DbSetup dbSetup = new DbSetup(new DataSourceDestination(dbManager.getDataSource()), operation);
		dbSetup.launch();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		dbManager.stopDB();
	}


	@Test
	public void testFindVendorByCode() {
		
		Vendor vendor = vendorQueries.findVendorByCode("AMA");
		assertNotNull(vendor);
	}
}
