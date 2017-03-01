package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Vector;


import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import tabellenklassen.Block;

public class TableModelSelfMade implements TableModel {
	// private Vector<Object> Block = new Vector<Object>();
	private ArrayList<Block> bloecke = new ArrayList<Block>();


	public int getRowCount(int index) throws SQLException {
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
		case 0: return "Montag";
		case 1: return "Dienstag";
		case 2: return "Mittwoch";
		case 3: return "Donnerstag";
		case 4: return "Freitag";
		case 5: return "Samstag";
		case 6: return "Sonntag";
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
		Block block = bloecke.get( rowIndex );
		switch( columnIndex ){
		case 0: return block.getMannschaft();
		case 1: return block.getSportart();
		case 2: return Integer.valueOf( block.getDauer() );
		case 3: return Integer.valueOf( block.getZeitbeginn());
		case 4: return Integer.valueOf(block.getWochentag());
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

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}



}
