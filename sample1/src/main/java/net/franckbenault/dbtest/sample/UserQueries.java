package net.franckbenault.dbtest.sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserQueries {

	private static final String requestSelectUsers = "SELECT * FROM users";
	private static final String requestSelectUserByLogin = "SELECT * FROM users where login='%s'";

	
	private DbManager dbManager;
	
	public UserQueries(DbManager dbManager) {
		this.dbManager = dbManager;
	}

	public List<User> findAllUsers() {
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
	
	public User findUserByLogin(String loginInput) {
		User user = null;
		try {
			ResultSet resultSet = dbManager
					.executRequest(String.format(requestSelectUserByLogin,loginInput));
			while (resultSet.next()) {

				int id = resultSet.getInt(1);
				String login = resultSet.getString(2);
				String password = resultSet.getString(3);
				user = new User(id, login, password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
