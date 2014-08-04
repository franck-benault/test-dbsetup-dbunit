package net.franckbenault.dbtest.sample;

public class Address {

	
	private String town;
	private String street;
	
	public Address(String town, String street) {
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
	
}
