package net.franckbenault.dbtest.sample;

public class User {
	
	public User(int id, String login, String password, String description) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.description = description;
	}
	 
	private int id;
	
	private String login;
	
	private String password;
	
	private String description;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
