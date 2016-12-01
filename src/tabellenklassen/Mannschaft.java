package tabellenklassen;

public class Mannschaft {	
	
	private int id;
	private String name;
	private Sportart sportart;
	
	public Mannschaft(int id, String name, Sportart sportart) {
		this.id = id;
		this.name = name;
		this.sportart = sportart;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Sportart getSportart() {
		return sportart;
	}

	public void setSportart(Sportart sportart) {
		this.sportart = sportart;
	}
}
