package net.franckbenault.dbtest.sample;

public class Address {

	private int id;
	private String town;
	private String street;
	
	public Address(int id, String town, String street) {
		this.setId(id);
		this.town = town;
		this.street = street;
	}
	
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
