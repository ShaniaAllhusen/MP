package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tabellenklassen.Benutzer;
import tabellenklassen.Mannschaft;
import tabellenklassen.Mitglied;

public class MitgliedDao {
	private String CLASSNAME = "org.sqlite.JDBC";
	private static String datei;
	public static String CONNECTIONSTRING = "jdbc:sqlite:";

	public MitgliedDao() throws ClassNotFoundException {
		Class.forName(CLASSNAME);
		datei = this.getClass().getResource("Datenbank.db").getPath().toString().replaceFirst("MP/bin/", "MP/src/");
		datei = "jdbc:sqlite:" + datei;
		System.out.println(datei);
	}

	private Connection getConnection() {
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(datei);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public Mitglied select(int id) throws NoMitgliedFound {
		Connection conn = null;
		Mitglied mitglied = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = getConnection();
			String sql = "SELECT m.id, m.vorname, m.nachname, m.geburtsdatum, m.strasse, m.plz, m.ort, m.benutzer_id, b.username, b.passwort FROM mitglied m "
					+ "JOIN benutzer b ON m.benutzer_id = b.id WHERE m.id = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			mitglied = create(resultSet);
		} catch (SQLException e) {
			throw new NoMitgliedFound();
		} finally {
			try {
				preparedStatement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mitglied;
	}
	
	//erstes Mitglied
	public Mitglied first() {
		Mitglied first = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = getConnection();
			String sql = "SELECT m.id, m.vorname, m.nachname, m.geburtsdatum, m.strasse, m.plz, m.ort, m.benutzer_id, b.username, b.passwort FROM mitglied m "
					+ "JOIN benutzer b ON m.benutzer_id = b.id "
					+ "where m.id = (SELECT MIN(id) from mitglied)";
			preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			first = create(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return first;
	}
	
	//vorheriges Mitglied 
		public Mitglied previous(Mitglied mitglied) {
			Connection conn = null;
			PreparedStatement preparedStatement = null;
			Mitglied previous = null;
			int id = mitglied.getId();
			try { 
				conn = getConnection();
				String sql = "SELECT m.id, m.vorname, m.nachname, m.geburtsdatum, m.strasse, m.plz, m.ort, m.benutzer_id, b.username, b.passwort FROM mitglied m "
						+ "JOIN benutzer b ON m.benutzer_id = b.id "
						+ "WHERE m.id < ? ORDER BY m.id DESC"; 
				preparedStatement = conn.prepareStatement(sql); 
				preparedStatement.setInt(1, id);
				ResultSet resultSet = preparedStatement.executeQuery(); 
				boolean prüfen = resultSetPruefen(resultSet);
				System.out.println(prüfen);
				if(prüfen==false) {
					previous = mitglied;
				}
				else {
					previous = create(resultSet);
				}
			} 
			catch (SQLException e) { 
				e.printStackTrace(); 
			} finally { 
				try { 
					preparedStatement.close(); 
					conn.close(); 
				} catch (SQLException e) { 
					e.printStackTrace(); 
				} 
			} 
			return previous; 
		}

		//nächstes Mitglied
		public Mitglied next(Mitglied mitglied) {
			Mitglied next = null;
			Connection conn = null;
			PreparedStatement preparedStatement = null;
			int id = mitglied.getId();
			try {
				conn = getConnection();
				String sql = "SELECT m.id, m.vorname, m.nachname, m.geburtsdatum, m.strasse, m.plz, m.ort, m.benutzer_id, b.username, b.passwort FROM mitglied m "
						+ "JOIN benutzer b ON m.benutzer_id = b.id "
						+ "WHERE m.id > ? ORDER BY m.id ASC";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, id); 
				ResultSet resultSet = preparedStatement.executeQuery();
				boolean prüfen = resultSetPruefen(resultSet);
				System.out.println(prüfen);
				if(prüfen==false) {
					next = mitglied;
				}
				else {
					next = create(resultSet);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					preparedStatement.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return next;
		}
		
	private boolean resultSetPruefen(ResultSet resultSet) throws SQLException{
		if (resultSet.next()){
			return true;
		}
		else {
			return false;
		}
	}
	
	//letztes Mitglied
		public Mitglied last() {
			Mitglied last = null;
			Connection conn = null;
			PreparedStatement preparedStatement = null;
			try {
				conn = getConnection();
				String sql =  "SELECT m.id, m.vorname, m.nachname, m.geburtsdatum, m.strasse, m.plz, m.ort, m.benutzer_id, b.username, b.passwort FROM mitglied m "
						+ "JOIN benutzer b ON m.benutzer_id = b.id "
						+ "WHERE m.id = (SELECT MAX(id) FROM mitglied)";
				preparedStatement = conn.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				last = create(resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					preparedStatement.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return last;
		}
	
	public Mitglied create(ResultSet resultSet) throws SQLException {
		Benutzer benutzer = new Benutzer();
		Mitglied mitglied = new Mitglied();
		benutzer.setId(resultSet.getInt("benutzer_id"));
		benutzer.setUsername(resultSet.getString("username"));
		benutzer.setPasswort(resultSet.getString("passwort"));
		mitglied.setBenutzer(benutzer);
		mitglied.setId(resultSet.getInt("id"));
		mitglied.setVorname(resultSet.getString("vorname"));
		mitglied.setNachname(resultSet.getString("nachname"));
		mitglied.setGeburtsdatum(resultSet.getString("geburtsdatum"));
		mitglied.setStrasse(resultSet.getString("strasse"));
		mitglied.setPlz(resultSet.getString("plz"));
		mitglied.setOrt(resultSet.getString("ort"));
		return mitglied;
	}
}
