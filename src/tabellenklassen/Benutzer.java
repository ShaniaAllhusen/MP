package tabellenklassen;

public class Benutzer {

	private int id;
	private String username;
	private String passwort;
	
	public Benutzer(int id, String username, String passwort) {
		this.id = id;
		this.username = username;
		this.passwort = passwort;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
}
