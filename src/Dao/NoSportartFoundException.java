package Dao;

public class NoSportartFoundException extends Exception{

	public NoSportartFoundException() {
		
	}
	
	@Override
	public String getMessage() {
		return "Es wurde keine Sportart gefunden.";
	}
}
