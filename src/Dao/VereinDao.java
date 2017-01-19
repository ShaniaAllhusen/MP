package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tabellenklassen.Halle;
import tabellenklassen.Mannschaft;
import tabellenklassen.Sportart;
import tabellenklassen.Training;


public class VereinDao {

	private String CLASSNAME = "org.sqlite.JDBC";
	private static String datei;
	public static String CONNECTIONSTRING = "jdbc:sqlite:";

	public VereinDao() throws ClassNotFoundException {
		Class.forName(CLASSNAME);
		datei = this.getClass().getResource("testdatenbank.db").getPath();
		datei = "jdbc:sqlite:" + datei;
		System.out.println(datei);
	
	}

	//Methoden
	
	private Connection getConnection() {
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(datei);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public boolean login(String name, String password) {
		Connection conn = null;
		boolean loggedIn = false;
		try {
			conn = getConnection();
			// String sql = "SELECT * FROM benutzer WHERE username =?";
			String sql = "SELECT * FROM benutzer";
			PreparedStatement statement = conn.prepareStatement(sql);
			// statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {				
				System.out.println(rs.getString("passwort"));
				System.out.println("password");
				if(rs.getString("passwort").equals(password)) {
					loggedIn = true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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



	public ArrayList<Training> select(){
		ArrayList<Training> arrayListTraining = new ArrayList<Training>(); 
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = DriverManager.getConnection(CONNECTIONSTRING + datei); 
			String sql = "SELECT t.id, t.halle_id, m.id, m.name , s.id , s.name, h.id, h.name, h.strasse, h.plz, h.ort FROM training t "
					+ "JOIN mannschaft m ON t.mannschaft_id = m.id "
					+ "JOIN sportart s ON m.sportart_id = s.id "
					+ "JOIN halle h ON t.halle_id = h.id";
			preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Sportart sportart = new Sportart(resultSet.getInt("s.id"),resultSet.getString("s.name"));
				Mannschaft mannschaft = new Mannschaft(resultSet.getInt("m.id"), resultSet.getString("m.name"), sportart);
				Halle halle = new Halle(resultSet.getInt("h.id"), resultSet.getString("h.name"), resultSet.getString("h.strasse"), 
						resultSet.getString("h.plz"), resultSet.getString("h.ort"));
				Training training = new Training(resultSet.getInt("t.id"), mannschaft, halle);
				arrayListTraining.add(training); 
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
		return arrayListTraining; 
	}
	
	
	public ArrayList<Training> select (int halleId, String wochentag, int uhrzeit) {
		ArrayList<Training> arrayListTraining = new ArrayList<Training>(); 
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = DriverManager.getConnection(CONNECTIONSTRING + datei); 
			String sql = "SELECT t.id, t.halle_id, m.id, m.name , s.id , s.name, h.id, h.name, h.strasse, h.plz, h.ort FROM training t "
					+ "JOIN mannschaft m ON t.mannschaft_id = m.id "
					+ "JOIN sportart s ON m.sportart_id = s.id "
					+ "JOIN halle h ON t.halle_id = h.id"
					+ "JOIN training_zeitblock tz ON t.id = tz.training_id"
					+ "JOIN zeitblock z ON tz.zeitblock_id = z.id"
					+ "WHERE h.id = ? AND z.wochentag = ? AND z.uhrzeit_beginn LIKE ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, halleId); 
			preparedStatement.setString(2, wochentag);
			preparedStatement.setInt(1, uhrzeit); 
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Sportart sportart = new Sportart(resultSet.getInt("s.id"),resultSet.getString("s.name"));
				Mannschaft mannschaft = new Mannschaft(resultSet.getInt("m.id"), resultSet.getString("m.name"), sportart);
				Halle halle = new Halle(resultSet.getInt("h.id"), resultSet.getString("h.name"), resultSet.getString("h.strasse"), 
						resultSet.getString("h.plz"), resultSet.getString("h.ort"));
				Training training = new Training(resultSet.getInt("t.id"), mannschaft, halle);
				arrayListTraining.add(training); 
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
		return arrayListTraining; 
	}
}

