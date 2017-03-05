package Dao;

//Imports
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

	//Konstruktor
	public SportartDao() throws ClassNotFoundException {
		Class.forName(CLASSNAME);
		datei = this.getClass().getResource("Datenbank.db").getPath().toString().replaceFirst("bin/", "src/");
		datei = "jdbc:sqlite:" + datei;
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

	//Datensatz in die Tabelle einf�gen
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
				//Verbindung zur Datenbank schlie�en
				conn.close(); 
			} catch (SQLException e) { 
				e.printStackTrace(); 
			} 
		}
	}

	//Datensatz aus der Tabelle l�schen
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

	//Datensatz aus der Tabelle �ndern
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

	//Datens�tze in der Tabelle suchen (name)
	public Sportart select(String name) throws NoSportartFoundException {
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
			throw new NoSportartFoundException();
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

	//Datens�tze in der Tabelle suchen (id)
	public Sportart select(int id) throws NoSportartFoundException {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		Sportart sportart = null;
		try {
			conn = getConnection();
			String sql = "SELECT id, name from sportart where id = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			sportart = new Sportart();
			sportart.setId(resultSet.getInt("id"));
			sportart.setName(resultSet.getString("name"));

		} catch (SQLException e) {
			throw new NoSportartFoundException();
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
			boolean pr�fen = resultSetPruefen(resultSet);
			System.out.println(pr�fen);
			if(pr�fen==false) {
				previous = sportart;
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

	//n�chste Sportart
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
			boolean pr�fen = resultSetPruefen(resultSet);
			System.out.println(pr�fen);
			if(pr�fen==false) {
				next = sportart;
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
	// Methode eingabePruefen() -> Pr�ft die Eingaben des Benutzers
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
	
	//Pr�ft ResultSet
	private boolean resultSetPruefen(ResultSet resultSet) throws SQLException{
		if (resultSet.next()){
			return true;
		}
		else {
			return false;
		}
	}
	
	//Methode create() erstellt neue Sportart
	private Sportart create(ResultSet resultSet) throws SQLException {
		Sportart sportart = new Sportart();
		//resultSet.next();
		sportart = new Sportart();
		sportart.setId(resultSet.getInt("id"));
		sportart.setName(resultSet.getString("name"));
		return sportart;
	}
}