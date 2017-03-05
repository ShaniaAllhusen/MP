package Dao;

public class NoMitgliedFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoMitgliedFound() {
	}
	
	@Override 
	public String getMessage() {
		return "Es wurde kein Mitglied gefunden.";
	}
}
