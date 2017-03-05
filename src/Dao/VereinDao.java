package Dao;

import gui.ZeitBloecke;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import tabellenklassen.Zeitblock;
  

public class VereinDao {

	private String CLASSNAME = "org.sqlite.JDBC";
	private static String datei;
	public static String CONNECTIONSTRING = "jdbc:sqlite:";

	//Konstruktor
	public VereinDao() throws ClassNotFoundException {
		Class.forName(CLASSNAME);
		datei = this.getClass().getResource("Datenbank.db").toString();
	}

	//Connection aufbauen
	private Connection getConnection() {
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(CONNECTIONSTRING + datei); 
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//Methoden

	public int benutzerpruefen(String name) {
		int pruefen;
		if(name.startsWith("verwaltung")) {
			pruefen = 1;
		}
		else {
			pruefen = 2;
		}
		return pruefen;
	}
	
	// Methode zum einloggen
	public boolean login(String name, String password) {
		Connection conn = null;
		boolean loggedIn = false;
		try {
			conn = getConnection();
			String sql = "SELECT * FROM benutzer WHERE username = ? AND passwort = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {				
				loggedIn = true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Benutzername oder Passwort ist falsch.", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
			}

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		{
		}

		return loggedIn;
	}
	
	// Daten in Array
	public Object[][] getDaten() {
		Object[][] daten = new Object[96][8];
		List<List<Object>> datenList = new ArrayList<List<Object>>();

		try {
			Object[] list = getWerteWochentag(1);
			List<Object> tempList = new ArrayList<Object>();
			for (int j = 0; j < list.length; j++) {
				datenList.add(tempList);
			}
			
			for (int i = 0; i < list.length; i++) {
				for (int j = 1; j < 8; j++) {
					System.out.println(j);
					try {
						daten[i][j] = getWerteWochentag(j)[i];
					} catch (ArrayIndexOutOfBoundsException e) {
					
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		System.out.println(datenList);
//		daten = new Object[datenList.size()][datenList.get(0).size()];
//		
//		for (int i = 0; i < datenList.size(); i++) {
//			for (int j = 0; j < datenList.get(i).size(); j++) {
//				daten[i][j] = datenList.get(i).get(j); 
//			}
//		}

		return daten;
	}

	//Datensätze aus der Tabelle lesen
	public Object[] getWerteWochentag(int wochentagId) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conn = DriverManager.getConnection(CONNECTIONSTRING + datei);
		ArrayList<Object> list = new ArrayList<Object>();

		String sql = "SELECT z.[Id], m.name as 'mannschaft_name', s.[name] as 'sportart_name', z.[Dauer], z.[Zeitbeginn], z.[Wochentag]  FROM Zeitblock z "
				+ "LEFT JOIN Mannschaft m ON m.id = z.Mannschaft "
				+ "LEFT JOIN Sportart s ON s.[id] = m.[sportart_id] "
				+ "WHERE z.wochentag = ?";

		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, wochentagId);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			if (wochentagId == 0) {
				//FIXME List add fabis komische methode
			} else if (wochentagId != 0) {
				list.add(rs.getString("mannschaft_name"));
				list.add(rs.getString("sportart_name"));
			} 
		}
		
		return list.toArray(new Object[list.size()]);

	}

//	public ArrayList<TwoLines> getTable() throws SQLException { //Falsch
//		PreparedStatement preparedStatement = null;
//		Connection conn = DriverManager.getConnection(CONNECTIONSTRING + datei); 
//		ArrayList<TwoLines> list = new ArrayList<TwoLines>();
//
//		String sql = "select printf('%s\n%s', m.name, s.name) from mannschaft m "
//				+ "join sportart s on s.id = m.sportart_id "
//				+ "join training t on t.mannschaft_id = m.id "
//				+ "join training_zeitblock tz on tz.training_id = t.id "
//				+ "join zeitblock z on z.id = tz.zeitblock_id "
//				+ "where z.wochentag = 'Montag'";
//		preparedStatement = conn.prepareStatement(sql);
//
//		ResultSet rs = preparedStatement.executeQuery();
//		while (rs.next()) {
//			TwoLines twoLines = new TwoLines(rs.getString("mannschaft"), rs.getString("sportart"));
//			list.add(twoLines);
//		}
//
//		return list;
// FIXME 
//		public String zeitenBerechnen(int auswahl) {
//			Zeitblock zeitblock = new Zeitblock();
//
//			
//			int zeitbeginn = zeitblock.getZeitbeginn();
//			int zeitende = 
//			int zeitlaenge = zeitblock.getDauer();
//			int stunde,gesamtzeit;
//			String[] uhrzeit = new String[96];
//			
//			for (int i = 0; i < ; i++) {
//				gesamtzeit = gesamtzeit + min;
//				
//				if(gesamtzeit%zeitlaenge != 0) {
//					
//				}
//			}
//			return uhrzeit[auswahl];
//		}
	
}
