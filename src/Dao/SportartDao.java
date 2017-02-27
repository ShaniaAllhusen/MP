package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import java.sql.Statement;



import tabellenklassen.Sportart;

public class SportartDao {

	private String CLASSNAME = "org.sqlite.JDBC";
	private static String datei;
	public static String CONNECTIONSTRING = "jdbc:sqlite:";

	public SportartDao() throws ClassNotFoundException {
		Class.forName(CLASSNAME);
		datei = this.getClass().getResource("testdatenbank.db").getPath();
		datei = "jdbc:sqlite:" + datei;
		System.out.println(datei);
	}

	//Verbindungsaufbau
	private Connection getConnection() {
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(datei);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	//Datensatz in die Tabelle einfügen
	public void insert(Sportart sportart) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try{
			conn = DriverManager.getConnection(CONNECTIONSTRING + datei); 
			String sql = "INSERT INTO sportart (name) VALUES (?)";
			preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, sportart.getName());
			preparedStatement.executeUpdate(); 
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			sportart.setId(resultSet.getInt(1));
		} catch (SQLException e) { 
			e.printStackTrace(); } 
		finally { 
			try { 
				//Verbindung zur Datenbank schließen
				conn.close(); 
			} catch (SQLException e) { 
				e.printStackTrace(); 
			} 
		}
	}
	
	//Datensatz aus der Tabelle löschen
	public void delete(Sportart sportart) {
		Connection conn = null;
		try { 
			conn = DriverManager.getConnection(CONNECTIONSTRING + datei); 
			String sql = "DELETE FROM sportart WHERE name = ?"; 
			PreparedStatement preparedStatement = conn.prepareStatement(sql); 
			preparedStatement.setString(1, sportart.getName());
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
	
	//Datensatz aus der Tabelle ändern
	public void update(Sportart sportart) {
		Connection conn = null;
		try { 
			conn = DriverManager.getConnection(CONNECTIONSTRING + datei); 
			String sql = "UPDATE sportart SET name = ? WHERE id = ?"; 
			PreparedStatement preparedStatement = conn.prepareStatement(sql); 
			preparedStatement.setString(1, sportart.getName());  
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

//Datensätze in der Tabelle suchen
	public Sportart select(String name) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		Sportart sportart = null;
		try {
			conn = getConnection();
			String sql = "SELECT id, name from sportart where name = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			sportart = new Sportart();
			sportart.setId(resultSet.getInt("id"));
			sportart.setName(resultSet.getString("name"));
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
		return sportart;
	}

}