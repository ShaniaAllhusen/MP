package tabellenklassen;

public class Zeitblock {
	private int Id;
	private String mannschaft;
	private String sportart;
	private int zeitbeginn;
	private static int dauer;
	private int wochentag;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
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
	public static int getDauer() {
		return dauer;
	}
	public void setDauer(int dauer) {
		Zeitblock.dauer = dauer;
	}
	public int getWochentag() {
		return wochentag;
	}
	public void setWochentag(int wochentag) {
		this.wochentag = wochentag;
	}
}


