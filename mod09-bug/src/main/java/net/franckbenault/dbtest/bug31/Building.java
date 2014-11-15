package net.franckbenault.dbtest.bug31;


public class Building {
	
	public Building(int id, String name, long buildingNb) {
		this.id = id;
		this.name = name;
		this.setBuildingNb(buildingNb);
	}
	
	private int id;
	
	private String name;
	
	private long buildingNb;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String toString() {
		return "building ="+name;
	}

	public long getBuildingNb() {
		return buildingNb;
	}

	public void setBuildingNb(long buildingNb) {
		this.buildingNb = buildingNb;
	}

	
}
