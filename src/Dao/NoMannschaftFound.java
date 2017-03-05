package Dao;

public class NoMannschaftFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoMannschaftFound() {
	}
	
	@Override 
	public String getMessage() {
		return "Es wurde keine Mannschaft gefunden.";
	}
}
