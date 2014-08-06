package net.franckbenault.dbtest.sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonQueries {

	private static final String requestSelectPersons = "SELECT * FROM persons";
	private static final String requestSelectPersonsByFirstName = "SELECT * FROM persons where FIRST_NAME='%s'";
	private static final String requestSelectPersonsByLastName = "SELECT * FROM persons where LAST_NAME='%s'";
	private static final String requestSelectPersonsByFirstNameLastName = "SELECT * FROM persons where FIRST_NAME='%s' and LAST_NAME='%s'";
	private static final String requestSelectAddressesByPersonId = "SELECT * FROM address where ID_PERSON='%s'";

	
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

				Person person = getPerson(resultSet);
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

				Person person = getPerson(resultSet);
				persons.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persons;
	}
	
	public List<Person> findPersonsByFirstNameLastName(String firstNameInput,String lastNameInput) {
		List<Person> persons = new ArrayList<Person>();
		try {
			ResultSet resultSet = dbManager
					.executRequest(String.format(requestSelectPersonsByFirstNameLastName,firstNameInput,lastNameInput));
			while (resultSet.next()) {

				Person person = getPerson(resultSet);
				
				List<Address> addresses = findAddressesByPersonId(person.getId());
				person.setAddresses(addresses);
				
				persons.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persons;
	}
	
	public List<Address> findAddressesByPersonId(int personId) {
		List<Address> addresses = new ArrayList<Address>();
		try {
			ResultSet resultSet = dbManager
					.executRequest(String.format(requestSelectAddressesByPersonId,personId));
			while (resultSet.next()) {

				int id = resultSet.getInt(1);
				String town = resultSet.getString(2);
				String street = resultSet.getString(3);
				Address address = new Address(id, town, street);
				addresses.add(address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addresses;
	}
	
	private Person getPerson(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt(1);
		String firstName = resultSet.getString(2);
		String lastName = resultSet.getString(3);
		return new Person(id, firstName, lastName);

	}
}
