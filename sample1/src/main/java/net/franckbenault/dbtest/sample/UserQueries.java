package net.franckbenault.dbtest.sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserQueries {

	private static final String requestSelectUsers = "SELECT * FROM users";
	
	private DbManager dbManager;
	
	public UserQueries(DbManager dbManager) {
		this.dbManager = dbManager;
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		try {
			ResultSet resultSet = dbManager
					.executRequest(requestSelectUsers);
			while (resultSet.next()) {

				int id = resultSet.getInt(1);
				String login = resultSet.getString(2);
				String password = resultSet.getString(3);
				User user = new User(id, login, password);
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
}
