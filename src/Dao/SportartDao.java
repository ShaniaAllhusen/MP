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
		datei = this.getClass().getResource("testdatenbank.db").getPath().toString().replaceFirst("MP/bin/", "MP/src/");
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
			conn = getConnection();
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
			conn = getConnection();
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
			conn = getConnection();
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
	
	//erste Sportart
	public Sportart first() {
		Sportart first = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = getConnection();
			String sql = "SELECT name, id from sportart where id = (SELECT MIN(id) from sportart)";
			preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			first = new Sportart();
			first.setId(resultSet.getInt("id"));
			first.setName(resultSet.getString("name"));
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

	//vorherige Sportart 
	public Sportart previous(Sportart sportart) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		Sportart previous = null;
		int id = sportart.getId();
		try { 
			conn = getConnection();
			String sql = "SELECT name, id FROM sportart WHERE id < ? ORDER BY id DESC"; 
			preparedStatement = conn.prepareStatement(sql); 
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery(); 
			resultSet.next();
			previous = new Sportart();
			previous.setId(resultSet.getInt("id"));
			previous.setName(resultSet.getString("name"));
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

	//nächste Sportart
	public Sportart next(Sportart sportart) {
		Sportart next = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		int id = sportart.getId();
		try {
			conn = getConnection();
			String sql = "SELECT name, id from sportart WHERE id > ? ORDER BY id ASC";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id); 
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			next = new Sportart();
			next.setId(resultSet.getInt("id"));
			next.setName(resultSet.getString("name"));
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

	//letzte Sportart
	public Sportart last() {
		Sportart last = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = getConnection();
			String sql = "SELECT name, id from sportart where id = (SELECT MAX(id) from sportart)";
			preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			last = new Sportart();
			last.setId(resultSet.getInt("id"));
			last.setName(resultSet.getString("name"));
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
}