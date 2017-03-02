package Dao;

public class NoBenutzerFound extends Exception {

	public NoBenutzerFound() {

	}

	@Override
	public String getMessage() {
		return "Es wurde kein Benutzer gefunden.";
	}
}
