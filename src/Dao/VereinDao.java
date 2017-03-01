package Dao;

import gui.ZeitBloecke;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;


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
	
	//Datensätze aus der Tabelle lesen
	public String[] getWerteWochentag() throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conn = DriverManager.getConnection(CONNECTIONSTRING + datei); 

		ArrayList<TwoLines> list = new ArrayList<TwoLines>();
		
		String sql = "select m.name as 'mannschaft', s.name as 'sportart', z.zeitbeginn, z.dauer, z.wochentag from mannschaft m "
				+ "join sportart s on s.id = m.sportart_id "
				+ "join training t on t.mannschaft_id = m.id "
				+ "join training_zeitblock tz on tz.training_id = t.id "
				+ "join zeitblock z on z.id = tz.zeitblock_id "
				+ "where z.wochentag = ?";
		
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			TwoLines twoLines = new TwoLines(rs.getString("mannschaft"), rs.getString("sportart"));
			list.add(twoLines);
		}

		String[] result = new String[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i).toString();
		}

		return result;
		// TwoLi[][] foobar = list.toArray(new String[list.size()][list.get(0).length]);
		// return foobar; // FIXME
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
//		// TwoLi[][] foobar = list.toArray(new String[list.size()][list.get(0).length]);
//		// return foobar; // FIXME
//	}
	
}