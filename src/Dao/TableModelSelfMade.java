package Dao;

//Imports
import javax.swing.table.DefaultTableModel;

public class TableModelSelfMade extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private Object[][] daten;
	
	//Konstruktor
	public TableModelSelfMade(Object[][] daten) {
		this.daten = daten;
//		for (int z=0;z<daten.length;z++) {
//			for (int s=0;s<daten[z].length;s++) {
//				this.daten[z][s]=z*s;
//			}
//		}
	}

	public void setData(Object[][] daten) {
		this.daten = daten;
	}

	//Zählt die Zeilen
	public int getRowCount(Object[][] daten) {
		int rows = daten.length;
		return rows;
	}

	//Zählt die Spalten
	@Override
	public int getColumnCount() {
		return daten[0].length;
	}

	//Gibt den Spalten den Namen
	@Override
	public String getColumnName(int columnIndex) {
		switch( columnIndex ){
		case 0: return "Zeit";
		case 1: return "Montag";
		case 2: return "Dienstag";
		case 3: return "Mittwoch";
		case 4: return "Donnerstag";
		case 5: return "Freitag";
		case 6: return "Samstag";
		case 7: return "Sonntag";
		default: return null;
		}		
	}
	
	//Füllt das Array
	@Override
	public Object getValueAt(int row, int column) {
		System.out.println(daten[row][column]);
		return daten[row][column];
		
	}

	//Legt fest ob der Benutzer die Zellen ändern darf/kann
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	
}
