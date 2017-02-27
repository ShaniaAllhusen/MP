package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tabellenklassen.Mannschaft;
import tabellenklassen.Sportart;

public class MannschaftDao {

	private String CLASSNAME = "org.sqlite.JDBC";
	private static String datei;
	public static String CONNECTIONSTRING = "jdbc:sqlite:";

	public MannschaftDao() throws ClassNotFoundException {
		Class.forName(CLASSNAME);
		datei = this.getClass().getResource("testdatenbank.db").getPath();
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

	//select id
	public Mannschaft select(int id) {
		Connection conn = null;
		Sportart sportart;
		Mannschaft mannschaft = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = getConnection();
			String sql = "SELECT m.id, m.name, s.name FROM mannschaft m JOIN sportart s ON m.sportart_id = s.id WHERE m.id = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next(); 
			sportart = new Sportart();
			sportart.setId(resultSet.getInt("s.id"));
			sportart.setName(resultSet.getString("s.name"));
			mannschaft = new Mannschaft();
			mannschaft.setId(resultSet.getInt("m.id"));
			mannschaft.setName(resultSet.getString("m.name"));
			mannschaft.setSportart(sportart);
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
		return mannschaft;
	}

	//select name
	public Mannschaft select(String name) {
		Connection conn = null;
		Sportart sportart;
		Mannschaft mannschaft = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = getConnection();
			String sql = "SELECT m.id, m.name, s.name FROM mannschaft m JOIN sportart s ON m.sportart_id = s.id WHERE m.name = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next(); 
			sportart = new Sportart();
			sportart.setId(resultSet.getInt("s.id"));
			sportart.setName(resultSet.getString("s.name"));
			mannschaft = new Mannschaft();
			mannschaft.setId(resultSet.getInt("m.id"));
			mannschaft.setName(resultSet.getString("m.name"));
			mannschaft.setSportart(sportart);
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
		return mannschaft;
	}

	//delete
	public void delete(int id) {
		Connection conn = null;
		try { 
			conn = getConnection();
			String sql = "DELETE FROM mannschaft WHERE id = ?"; 
			PreparedStatement preparedStatement = conn.prepareStatement(sql); 
			preparedStatement.setInt(1, id);
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
	
	//update
	public void update(Mannschaft mannschaft) {
		Connection conn = null;
		try { 
			conn = getConnection();
			String sql = "UPDATE mannschaft SET name = ?, sportart = ? WHERE id = ?"; 
			PreparedStatement preparedStatement = conn.prepareStatement(sql); 
			preparedStatement.setString(1, mannschaft.getName());  
			preparedStatement.setInt(2, mannschaft.getSportart().getId());  
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
}