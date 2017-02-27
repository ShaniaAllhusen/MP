package tabellenklassen;

public class Mannschaft {	
	
	private int id;
	private String name;
	private Sportart sportart;
	
	public Mannschaft() {
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
