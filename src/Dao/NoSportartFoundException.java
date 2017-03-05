package Dao;

public class NoSportartFoundException extends Exception{

	/**
	 * Exception wenn die Sportart nicht gefunden wurde
	 */
	private static final long serialVersionUID = 1L;

	public NoSportartFoundException() {
		
	}
	
	@Override
	public String getMessage() {
		return "Es wurde keine Sportart gefunden.";
	}
}
