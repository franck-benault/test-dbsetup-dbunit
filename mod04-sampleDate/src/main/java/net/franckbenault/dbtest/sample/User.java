package net.franckbenault.dbtest.sample;

import java.util.Date;

public class User {
	
	public User(int id, String login, String password, Date deactivationDate) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.setDeactivationDate(deactivationDate);
	}
	
	private int id;
	
	private String login;
	
	private String password;
	
	private Date deactivationDate;

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

	public Date getDeactivationDate() {
		return deactivationDate;
	}

	public void setDeactivationDate(Date deactivationDate) {
		this.deactivationDate = deactivationDate;
	}
	
}
