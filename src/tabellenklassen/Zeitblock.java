package tabellenklassen;

public class Zeitblock {

	private int id;
	private String datum;
	private String wochentag;
	private String uhrzeit_beginn;
	private String uhrzeit_ende;
	
	public Zeitblock() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getWochentag() {
		return wochentag;
	}
	public void setWochentag(String wochentag) {
		this.wochentag = wochentag;
	}
	public String getUhrzeit_beginn() {
		return uhrzeit_beginn;
	}
	public void setUhrzeit_beginn(String uhrzeit_beginn) {
		this.uhrzeit_beginn = uhrzeit_beginn;
	}
	public String getUhrzeit_ende() {
		return uhrzeit_ende;
	}
	public void setUhrzeit_ende(String uhrzeit_ende) {
		this.uhrzeit_ende = uhrzeit_ende;
	}
}
