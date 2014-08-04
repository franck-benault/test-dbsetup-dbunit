package net.franckbenault.dbtest.sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonQueries {

	private static final String requestSelectPersons = "SELECT * FROM persons";
	private static final String requestSelectPersonsByFirstName = "SELECT * FROM persons where FIRST_NAME='%s'";
	private static final String requestSelectPersonsByLastName = "SELECT * FROM persons where LAST_NAME='%s'";
	private static final String requestSelectPersonsByFirstNameLastName = "SELECT * FROM persons where FIRST_NAME='%s'";

	
	private DbManager dbManager;
	
	public PersonQueries(DbManager dbManager) {
		this.dbManager = dbManager;
	}
	
	
	public List<Person> findAllPersons() {
		List<Person> persons = new ArrayList<Person>();
		try {
			ResultSet resultSet = dbManager
					.executRequest(requestSelectPersons);
			while (resultSet.next()) {

				int id = resultSet.getInt(1);
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				Person person = new Person(id, firstName, lastName);
				persons.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persons;
	}
	
	public List<Person> findPersonsByFirstName(String firstNameInput) {
		List<Person> persons = new ArrayList<Person>();
		try {
			ResultSet resultSet = dbManager
					.executRequest(String.format(requestSelectPersonsByFirstName,firstNameInput));
			while (resultSet.next()) {

				int id = resultSet.getInt(1);
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				Person person = new Person(id, firstName, lastName);
				persons.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persons;
	}
	
	public List<Person> findPersonsByLastName(String lastNameInput) {
		List<Person> persons = new ArrayList<Person>();
		try {
			ResultSet resultSet = dbManager
					.executRequest(String.format(requestSelectPersonsByLastName,lastNameInput));
			while (resultSet.next()) {

				int id = resultSet.getInt(1);
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				Person person = new Person(id, firstName, lastName);
				persons.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persons;
	}
	
	public List<Person> findPersonsByFirstNameLastName(String firstName,String lastName) {
		return null;
	}
}
