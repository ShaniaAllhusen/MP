package tabellenklassen;

public class Zeitblock {
	private int id;
	private String mannschaft;
	private String sportart;
	private int zeitbeginn;
	private int dauer;
	private int wochentag;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMannschaft() {
		return mannschaft;
	}
	public void setMannschaft(String mannschaft) {
		this.mannschaft = mannschaft;
	}
	public String getSportart() {
		return sportart;
	}
	public void setSportart(String sportart) {
		this.sportart = sportart;
	}
	public int getZeitbeginn() {
		return zeitbeginn;
	}
	public void setZeitbeginn(int zeitbeginn) {
		this.zeitbeginn = zeitbeginn;
	}
	public  int getDauer() {
		return dauer;
	}
	public void setDauer(int dauer) {
		this.dauer = dauer;
	}
	public int getWochentag() {
		return wochentag;
	}
	public void setWochentag(int wochentag) {
		this.wochentag = wochentag;
	}
}


