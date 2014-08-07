package net.franckbenault.dbtest.sample;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


public class UserQueriesTestWithDbUnit {

	private static DbManager dbManager;
	private static VendorQueries vendorQueries;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbManager = new DbManager();
		dbManager.connexionDB();
		vendorQueries = new VendorQueries(dbManager);
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
	public void testFindVendorByCode() {
		
		Vendor vendor = vendorQueries.findVendorByCode("AMA");
		assertNotNull(vendor);
	}

}
