package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
			String sql = "INSERT INTO sportart (id, name) VALUES (?,?)";
			preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			preparedStatement.setInt(1, sportart.getId()); 
			preparedStatement.setString(2, sportart.getName());
			preparedStatement.executeUpdate(); 
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

}