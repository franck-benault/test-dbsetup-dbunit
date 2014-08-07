package net.franckbenault.dbtest.sample;


public class Vendor {
	
	public Vendor(int id, String code, String name, int id_product) {
		this.setId(id);
		this.setCode(code);
		this.setName(name);
		this.setId_product(id_product);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	private int id;
	
	private String code;
	
	private String name;
	
	private int id_product;

	
}
