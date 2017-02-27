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
	
	//select()
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
			sportart = new Sportart(id, sql);
			sportart.setId(resultSet.getInt("s.id"));
			sportart.setName(resultSet.getString("s.name"));
			mannschaft = new Mannschaft(id, sql, sportart);
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
	
	//select()
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
				sportart = new Sportart(0, sql);
				sportart.setId(resultSet.getInt("s.id"));
				sportart.setName(resultSet.getString("s.name"));
				mannschaft = new Mannschaft(0, sql, sportart);
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
	
	//delete()
	
}