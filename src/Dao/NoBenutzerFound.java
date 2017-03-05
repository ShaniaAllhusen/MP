package Dao;

public class NoBenutzerFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoBenutzerFound() {

	}

	@Override
	public String getMessage() {
		return "Es wurde kein Benutzer gefunden.";
	}
}
