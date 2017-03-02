package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tabellenklassen.Benutzer;
import tabellenklassen.Sportart;

public class BenutzerDao {

	private String CLASSNAME = "org.sqlite.JDBC";
	private static String datei;
	public static String CONNECTIONSTRING = "jdbc:sqlite:";

	public BenutzerDao() throws ClassNotFoundException {
		Class.forName(CLASSNAME);
		datei = this.getClass().getResource("Datenbank.db").getPath().toString().replaceFirst("MP/bin/", "MP/src/");
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

	//Datensätze in der Tabelle suchen (id)
	public Benutzer select(int id) throws NoBenutzerFound {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		Benutzer benutzer = null;
		try {
			conn = getConnection();
			String sql = "SELECT id, username, passwort from benutzer where id = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			benutzer = new Benutzer();
			benutzer.setId(resultSet.getInt("id"));
			benutzer.setUsername(resultSet.getString("username"));
			benutzer.setPasswort(resultSet.getString("passwort"));

		} catch (SQLException e) {
			throw new NoBenutzerFound();
		} finally {
			try {
				preparedStatement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return benutzer;
	}
	
	
	//Datensätze in der Tabelle suchen (name)
		public Benutzer select(String name) throws NoBenutzerFound {
			Connection conn = null;
			PreparedStatement preparedStatement = null;
			Benutzer benutzer = null;
			try {
				conn = getConnection();
				String sql = "SELECT id, username, passwort from benutzer where username = ?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, name);
				ResultSet resultSet = preparedStatement.executeQuery();
				resultSet.next();
				benutzer = new Benutzer();
				benutzer.setId(resultSet.getInt("id"));
				benutzer.setUsername(resultSet.getString("username"));
				benutzer.setPasswort(resultSet.getString("passwort"));
			} catch (SQLException e) {
				throw new NoBenutzerFound();
			} finally {
				try {
					preparedStatement.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return benutzer;
		}
		

	public boolean eingabePruefen(String eingabe) {
		try {
			Integer.parseInt(eingabe);
			return true;
		} catch (NumberFormatException e) {
			return false;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}

	}
}
