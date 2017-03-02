package Dao;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import tabellenklassen.Zeitblock;

public class TableModelSelfMade implements TableModel {
	// private Vector<Object> Block = new Vector<Object>();
	private ArrayList<Zeitblock> bloecke = new ArrayList<Zeitblock>();

	@Override
	public int getRowCount() {
		int rows = bloecke.size();
		return rows;
	}

	@Override
	public int getColumnCount() {
		return 8;
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
	public Class<?> getColumnClass(int columnIndex) {
		switch( columnIndex ){
		case 0: return String.class;
		case 1: return String.class;
		case 2: return String.class;
		case 3: return String.class;
		case 4: return String.class;
		case 5: return String.class;
		case 6: return String.class;
		case 7: return String.class;
		default: return null;
		}   

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	//	@Override
	//	public Object getValueAt(int rowIndex, int columnIndex) {
	//       Sportart sportart = new Sportart();
	//       Mannschaft mannschaft = new Mannschaft();
	//       Block block = new Block();
	//       	        
	//	        switch( columnIndex ){
	//	            case 0: return sportart.getName();
	//	            case 1: return mannschaft.getName();
	//	            case 2: return new Integer( block.getZeitbeginn() );
	//	            case 3: return new Integer( block.getDauer());
	//	            case 4: return new Integer( block.getWochentag());
	//	            default: return null;
	//	        }
	//	}
	public Object getValueAt( int rowIndex, int columnIndex ) {
		Zeitblock zeitblock = bloecke.get( rowIndex );
		switch( columnIndex ){
		case 0: return zeitblock.getMannschaft();
		case 1: return zeitblock.getSportart();
		case 2: return Integer.valueOf( Zeitblock.getDauer() );
		case 3: return Integer.valueOf( zeitblock.getZeitbeginn());
		case 4: return Integer.valueOf(zeitblock.getWochentag());
		default: throw new IllegalArgumentException( "Wrong column" );
		}
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	
}
