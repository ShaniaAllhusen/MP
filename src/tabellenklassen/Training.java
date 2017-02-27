package tabellenklassen;

public class Training {

	private int id;
	private Mannschaft mannschaft;
	private Halle halle;
	
	public Training() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Mannschaft getMannschaft() {
		return mannschaft;
	}
	public void setMannschaft(Mannschaft mannschaft) {
		this.mannschaft = mannschaft;
	}
	public Halle getHalle() {
		return halle;
	}
	public void setHalle(Halle halle) {
		this.halle = halle;
	}
}
