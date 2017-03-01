package Dao;

public class NoMannschaftFound extends Exception {

	public NoMannschaftFound() {
	}
	
	@Override 
	public String getMessage() {
		return "Es wurde keine Mannschaft gefunden.";
	}
}
