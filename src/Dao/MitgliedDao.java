package Dao;

//Imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tabellenklassen.Benutzer;
import tabellenklassen.Mitglied;

public class MitgliedDao {
	private String CLASSNAME = "org.sqlite.JDBC";
	private static String datei;
	public static String CONNECTIONSTRING = "jdbc:sqlite:";

	//Konstruktor
	public MitgliedDao() throws ClassNotFoundException {
		Class.forName(CLASSNAME);
		datei = this.getClass().getResource("Datenbank.db").getPath().toString().replaceFirst("bin/", "src/");
		datei = "jdbc:sqlite:" + datei;
	}
	//Connection aufbauen
	private Connection getConnection() {
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(datei);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//Mitgliedsdaten aus Datenbank abrufen
	public Mitglied select(int id) throws NoMitgliedFound { //TODO Muss noch überarbeitet werden, damit auch Mitglieder angezeigt werden können, die keinen Benutzer haben
		Connection conn = null;
		Mitglied mitglied = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = getConnection();
			String sql = "SELECT m.id, m.vorname, m.nachname, m.geburtsdatum, m.strasse, m.plz, m.ort, m.benutzer_id, b.username, b.passwort FROM mitglied m "
					+ "LEFT JOIN benutzer b ON m.benutzer_id = b.id WHERE m.id = ?";
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
	
	//Mitglied hinzufügen
	public void insertMitBenutzer(Mitglied mitglied) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try{
			conn = getConnection();
			String sql = "INSERT INTO mitglied (vorname, nachname, geburtsdatum, strasse, plz, ort, benutzer_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, mitglied.getVorname());
			preparedStatement.setString(2, mitglied.getNachname());
			preparedStatement.setString(3, mitglied.getGeburtsdatum());
			preparedStatement.setString(4, mitglied.getStrasse());
			preparedStatement.setString(5, mitglied.getPlz());
			preparedStatement.setString(6, mitglied.getOrt());
			preparedStatement.setInt(7, mitglied.getBenutzer().getId());
			preparedStatement.executeUpdate(); 
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			mitglied.setId(resultSet.getInt(1));
		} catch (SQLException e) { 
			e.printStackTrace(); } 
		finally { 
			try { 
				conn.close(); 
			} catch (SQLException e) { 
				e.printStackTrace(); 
			} 
		}
	}
	
	//Mitglied hinzufügen
		public void insertOhneBenutzer(Mitglied mitglied) {
			Connection conn = null;
			PreparedStatement preparedStatement = null;
			try{
				conn = getConnection();
				String sql = "INSERT INTO mitglied (vorname, nachname, geburtsdatum, strasse, plz, ort) VALUES (?, ?, ?, ?, ?, ?)";
				preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, mitglied.getVorname());
				preparedStatement.setString(2, mitglied.getNachname());
				preparedStatement.setString(3, mitglied.getGeburtsdatum());
				preparedStatement.setString(4, mitglied.getStrasse());
				preparedStatement.setString(5, mitglied.getPlz());
				preparedStatement.setString(6, mitglied.getOrt());
				preparedStatement.executeUpdate(); 
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				resultSet.next();
				mitglied.setId(resultSet.getInt(1));
			} catch (SQLException e) { 
				e.printStackTrace(); } 
			finally { 
				try { 
					conn.close(); 
				} catch (SQLException e) { 
					e.printStackTrace(); 
				} 
			}
		}
	
	//Mitglied löschen
		public void delete(Mitglied mitglied) {
			Connection conn = null;
			try { 
				conn = getConnection();
				String sql = "DELETE FROM mitglied WHERE id = ?"; 
				PreparedStatement preparedStatement = conn.prepareStatement(sql); 
				preparedStatement.setInt(1, mitglied.getId());
				preparedStatement.executeUpdate(); 
			} catch (SQLException e) { 
				e.printStackTrace(); } 
			finally { 
				try { 
					conn.close(); 
				} catch (SQLException e) { 
					e.printStackTrace(); 
				} 
			}
		}
		
		//Mitglied ändern
		public void update(Mitglied mitglied) {
			Connection conn = null;
			try { 
				conn = getConnection();
				String sql = "UPDATE mitglied SET vorname = ?, nachname = ?, geburtsdatum = ?, strasse = ?, plz = ?, ort = ?, benutzer_id = ? WHERE id = ?"; 
				PreparedStatement preparedStatement = conn.prepareStatement(sql); 
				preparedStatement.setString(1, mitglied.getVorname());  
				preparedStatement.setString(2, mitglied.getNachname());  
				preparedStatement.setString(3, mitglied.getGeburtsdatum());  
				preparedStatement.setString(4, mitglied.getStrasse());  
				preparedStatement.setString(5, mitglied.getPlz());  
				preparedStatement.setString(6, mitglied.getOrt());  
				preparedStatement.setInt(7, mitglied.getBenutzer().getId());  
				preparedStatement.setInt(8, mitglied.getId());  
				preparedStatement.executeUpdate(); 
			} catch (SQLException e) { 
				e.printStackTrace(); } 
			finally { 
				try { 
					conn.close(); 
				} catch (SQLException e) { 
					e.printStackTrace(); 
				} 
			}
		}

	
	//erstes Mitglied
	public Mitglied first() {
		Mitglied first = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = getConnection();
			String sql = "SELECT m.id, m.vorname, m.nachname, m.geburtsdatum, m.strasse, m.plz, m.ort, m.benutzer_id, b.username, b.passwort FROM mitglied m "
					+ "LEFT JOIN benutzer b ON m.benutzer_id = b.id "
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
						+ "LEFT JOIN benutzer b ON m.benutzer_id = b.id "
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
						+ "LEFT JOIN benutzer b ON m.benutzer_id = b.id "
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
						+ "LEFT JOIN benutzer b ON m.benutzer_id = b.id "
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
	
		//Neues Mitglied erstellen
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
