package Dao;

public class NoMannschaftFound extends Exception {

	/**
	 * Exception wenn die Mannschaft nicht gefunden wurde
	 */
	private static final long serialVersionUID = 1L;

	public NoMannschaftFound() {
	}
	
	@Override 
	public String getMessage() {
		return "Es wurde keine Mannschaft gefunden.";
	}
}
