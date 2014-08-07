package net.franckbenault.dbtest.sample;

import java.sql.ResultSet;
import java.sql.SQLException;



public class VendorQueries {
	
	private static final String requestSelectVendorByCode = "SELECT * FROM vendor where vcode='%s'";

	
	private DbManager dbManager;
	
	public VendorQueries(DbManager dbManager) {
		this.dbManager = dbManager;
	}


	
	public Vendor findVendorByCode(String codeInput) {
		Vendor vendor = null;
		try {
			ResultSet resultSet = dbManager
					.executRequest(String.format(requestSelectVendorByCode,codeInput));
			while (resultSet.next()) {
				vendor = getVendor(resultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vendor;
	}
	
	
	
	private Vendor getVendor(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt(1);
		String code = resultSet.getString(2);
		String name = resultSet.getString(3);
		int id_product = resultSet.getInt(4);
		return new Vendor(id, code, name, id_product);
	}
}
