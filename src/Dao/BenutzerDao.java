package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tabellenklassen.Benutzer;
import tabellenklassen.Sportart;

public class BenutzerDao {

	private String CLASSNAME = "org.sqlite.JDBC";
	private static String datei;
	public static String CONNECTIONSTRING = "jdbc:sqlite:";

	public BenutzerDao() throws ClassNotFoundException {
		Class.forName(CLASSNAME);
		datei = this.getClass().getResource("Datenbank.db").getPath().toString().replaceFirst("bin/", "src/");
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
		public void insert(Benutzer benutzer) {
			Connection conn = null;
			PreparedStatement preparedStatement = null;
			try{
				conn = getConnection();
				String sql = "INSERT INTO benutzer (username, passwort) VALUES (?,?)";
				preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, benutzer.getUsername());
				preparedStatement.setString(2, benutzer.getPasswort());
				preparedStatement.executeUpdate(); 
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				resultSet.next();
				benutzer.setId(resultSet.getInt(1));
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
		
		//Datensatz aus der Tabelle ändern
		public void update(Benutzer benutzer) {
			Connection conn = null;
			try { 
				conn = getConnection();
				String sql = "UPDATE benutzer SET username = ?, passwort = ? WHERE id = ?"; 
				PreparedStatement preparedStatement = conn.prepareStatement(sql); 
				preparedStatement.setString(1, benutzer.getUsername());
				preparedStatement.setString(2, benutzer.getPasswort());
				preparedStatement.setInt(3, benutzer.getId());
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

	//erster Benutzer
	public Benutzer first() {
		Benutzer first = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = getConnection();
			String sql = "SELECT id, username, passwort from benutzer where id = (SELECT MIN(id) from benutzer)";
			preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			first = new Benutzer();
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
	
	//vorheriger benutzer
	public Benutzer previous(Benutzer benutzer) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		Benutzer previous = null;
		int id = benutzer.getId();
		try { 
			conn = getConnection();
			String sql = "SELECT id, username, passwort FROM benutzer WHERE id < ? ORDER BY id DESC"; 
			preparedStatement = conn.prepareStatement(sql); 
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery(); 
			boolean prüfen = resultSetPruefen(resultSet);
			System.out.println(prüfen);
			if(prüfen==false) {
				previous = benutzer;
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
	
	//nächster benutzer
		public Benutzer next(Benutzer benutzer) {
			Benutzer next = null;
			Connection conn = null;
			PreparedStatement preparedStatement = null;
			int id = benutzer.getId();
			try {
				conn = getConnection();
				String sql = "SELECT id, username, passwort from benutzer WHERE id > ? ORDER BY id ASC";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, id); 
				ResultSet resultSet = preparedStatement.executeQuery();
				boolean prüfen = resultSetPruefen(resultSet);
				System.out.println(prüfen);
				if(prüfen==false) {
					next = benutzer;
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
		
		//letzter benutzer
		public Benutzer last() {
			Benutzer last = null;
			Connection conn = null;
			PreparedStatement preparedStatement = null;
			try {
				conn = getConnection();
				String sql = "SELECT id, username, passwort from benutzer where id = (SELECT MAX(id) from benutzer)";
				preparedStatement = conn.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				resultSet.next();
				last = new Benutzer();
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
	
	private boolean resultSetPruefen(ResultSet resultSet) throws SQLException{
		if (resultSet.next()){
			return true;
		}
		else {
			return false;
		}
	}

	private Benutzer create(ResultSet resultSet) throws SQLException {
		Benutzer benutzer = new Benutzer();
		//resultSet.next();
		benutzer = new Benutzer();
		benutzer.setId(resultSet.getInt("id"));
		benutzer.setUsername(resultSet.getString("username"));
		benutzer.setPasswort(resultSet.getString("passwort"));
		return benutzer;
	}
}
