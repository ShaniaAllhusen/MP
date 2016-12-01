package tabellenklassen;

public class Halle {
	
	private int id;
	private String name;
	private String strasse;
	private String plz;
	private String ort;
	
	public Halle(int id, String name, String strasse, String plz, String ort) {
		this.id = id;
		this.name = name;
		this.strasse = strasse;
		this.plz = plz;
		this.ort = ort;
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
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
}
