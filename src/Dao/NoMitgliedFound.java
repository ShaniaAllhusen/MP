package Dao;

public class NoMitgliedFound extends Exception {

	public NoMitgliedFound() {
	}
	
	@Override 
	public String getMessage() {
		return "Es wurde kein Mitglied gefunden.";
	}
}
