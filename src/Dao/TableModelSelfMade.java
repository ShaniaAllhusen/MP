package Dao;

import javax.swing.table.DefaultTableModel;

public class TableModelSelfMade extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private Object[][] daten;
	
	public TableModelSelfMade(Object[][] daten) {
		System.out.println(daten[0][0] + " Object [][]");
		this.daten = daten;
	}
	
	public void setData(Object[][] daten) {
		this.daten = daten;
	}

	public int getRowCount(Object[][] daten) {
		int rows = daten.length;
		return rows;
	}

	@Override
	public int getColumnCount() {
		return daten[0].length;
	}

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
	
	@Override
	public Object getValueAt(int row, int column) {
		System.out.println(daten[row][column]);
		return daten[row][column];
		
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	
}
