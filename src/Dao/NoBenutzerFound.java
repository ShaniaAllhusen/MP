package Dao;

public class NoBenutzerFound extends Exception {

	/**
	 * Exception wenn der Benutzer nicht gefunden wurde
	 */
	private static final long serialVersionUID = 1L;

	public NoBenutzerFound() {

	}

	@Override
	public String getMessage() {
		return "Es wurde kein Benutzer gefunden.";
	}
}
