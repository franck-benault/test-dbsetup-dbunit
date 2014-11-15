package net.franckbenault.dbtest.bug31;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BuildingQueries {

	private static final String requestSelectUsers = "SELECT * FROM users";

	private static final String requestDeleteUsers = "delete FROM users";
	private static final String requestDeleteUsersPerLogin = "DELETE FROM users where login='%s'";

	
	private static final String requestSelectUserByLogin = "SELECT * FROM users where login='%s'";

	
	private DbManager dbManager;
	
	public BuildingQueries(DbManager dbManager) {
		this.dbManager = dbManager;
	}

	public List<Building> findAllUsers() {
		List<Building> users = new ArrayList<Building>();
		try {
			ResultSet resultSet = dbManager
					.executRequest(requestSelectUsers);
			while (resultSet.next()) {

				Building building = getBuilding(resultSet);
				users.add(building);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	

	
	public int deleteUsers() {
		int res=-1;
		try {
			res = dbManager
					.executUpdate(requestDeleteUsers);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public int deleteUsersPerLogin(String login) {
		int res=-1;
		try {
			res = dbManager
					.executUpdate(String.format(requestDeleteUsersPerLogin,login));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	private Building getBuilding(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt(1);
		String name = resultSet.getString(2);
		long buildingNb = resultSet.getLong(3);
		return new Building(id, name, buildingNb);
	}
}
