package Dao;

public class TwoLines {
	
	private String firstLine;
	private String secondLine;
	
	public TwoLines(String firstLine, String secondLine) {
		this.firstLine = firstLine;
		this.secondLine = secondLine;
	}

	public String getFirstLine() {
		return firstLine;
	}

	public String getSecondLine() {
		return secondLine;
	}
	
	@Override
	public String toString() {
		return firstLine + "\n" + secondLine;
	}
}
