package Dao;

//Imports
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

public class TestRenderer extends JTextArea implements TableCellRenderer {
	
	private static final long serialVersionUID = 1L;
	private List<List<Integer>> rowColHeight = new ArrayList<List<Integer>>();

	//Konstruktor
	public TestRenderer() {
		setLineWrap(true);
		setWrapStyleWord(true);
		setOpaque(true);
	}

	// Farben setzen
	public Component getTableCellRendererComponent(
			JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		isSelected = true;
		if (isSelected) {
			setBackground(Color.YELLOW);

		} else {
			setBackground(Color.RED);
		}
		setFont(table.getFont());
		if (hasFocus) {
			setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
			if (table.isCellEditable(row, column)) {
				setForeground(UIManager.getColor("Table.focusCellForeground"));
				setBackground(UIManager.getColor("Table.focusCellBackground"));
			}
		} else {
			setBorder(new EmptyBorder(1, 2, 1, 2));
		}
		if (value != null) {
			setText(value.toString());
		} else {
			setText("");
		}
		adjustRowHeight(table, row, column);
		return this;
	}
	
	
	//Bestimmt die Höhe
	private void adjustRowHeight(JTable table, int row, int column) {
		int Width = table.getTableHeader().getColumnModel().getColumn(column).getWidth();
		setSize(new Dimension(Width, 1000));
		int prefH = getPreferredSize().height;
		while (rowColHeight.size() <= row) {
			rowColHeight.add(new ArrayList<Integer>(column));
		}
		List<Integer> colHeights = rowColHeight.get(row);
		while (colHeights.size() <= column) {
			colHeights.add(0);
		}
		colHeights.set(column, prefH);
		int maxH = prefH;
		for (Integer colHeight : colHeights) {
			if (colHeight > maxH) {
				maxH = colHeight;
			}
		}
		if (table.getRowHeight(row) != maxH) {
			table.setRowHeight(row, maxH);
		}
	}
}