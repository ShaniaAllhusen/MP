package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tabellenklassen.Mannschaft;
import tabellenklassen.Sportart;

public class MannschaftDao {

	private String CLASSNAME = "org.sqlite.JDBC";
	private static String datei;
	public static String CONNECTIONSTRING = "jdbc:sqlite:";

	public MannschaftDao() throws ClassNotFoundException {
		Class.forName(CLASSNAME);
		datei = this.getClass().getResource("Datenbank.db").getPath().toString().replaceFirst("MP/bin/", "MP/src/");
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
	public Mannschaft select(int id) throws NoMannschaftFound {
		Connection conn = null;
		Mannschaft mannschaft = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = getConnection();
			String sql = "SELECT m.id AS 'm.id', m.name AS 'm.name', s.name AS 's.name', s.id AS 's.id' FROM mannschaft m JOIN sportart s ON m.sportart_id = s.id WHERE m.id = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			mannschaft = create(resultSet);
		} catch (SQLException e) {
			throw new NoMannschaftFound();
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
	public Mannschaft select(String name) throws NoMannschaftFound {
		Connection conn = null;
		Mannschaft mannschaft = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = getConnection();
			String sql = "SELECT m.id AS 'm.id', m.name AS 'm.name', s.name AS 's.name', s.id AS 's.id' FROM mannschaft m JOIN sportart s ON m.sportart_id = s.id WHERE m.name = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			mannschaft = create(resultSet);
		} catch (SQLException e) {
			throw new NoMannschaftFound();
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

	//insert
	public void insert(Mannschaft mannschaft) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try{
			conn = getConnection();
			String sql = "INSERT INTO mannschaft (name, sportart_id) VALUES (?, ?)";
			preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, mannschaft.getName());
			preparedStatement.setInt(2, mannschaft.getSportart().getId());
			preparedStatement.executeUpdate(); 
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			mannschaft.setId(resultSet.getInt(1));
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

	//erste Mannschaft
	public Mannschaft first() {
		Mannschaft first = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = getConnection();
			String sql = "SELECT m.id AS 'm.id', m.name AS 'm.name', s.name AS 's.name', s.id AS 's.id' FROM mannschaft m "
					+ "JOIN sportart s ON m.sportart_id = s.id "
					+ "where m.id = (SELECT MIN(id) from mannschaft)";
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

	private boolean resultSetPruefen(ResultSet resultSet) throws SQLException{
		if (resultSet.next()){
			return true;
		}
		else {
			return false;
		}
	}

	//vorherige Mannschaft 
	public Mannschaft previous(Mannschaft mannschaft) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		Mannschaft previous = null;
		int id = mannschaft.getId();
		try { 
			conn = getConnection();
			String sql = "SELECT m.id AS 'm.id', m.name AS 'm.name', s.name AS 's.name', s.id AS 's.id' "
					+ "FROM mannschaft m "
					+ "JOIN sportart s ON m.sportart_id = s.id "
					+ "WHERE m.id < ? ORDER BY m.id DESC"; 
			preparedStatement = conn.prepareStatement(sql); 
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery(); 
			boolean prüfen = resultSetPruefen(resultSet);
			System.out.println(prüfen);
			if(prüfen==false) {
				previous = mannschaft;
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

	//nächste Mannschaft
	public Mannschaft next(Mannschaft mannschaft) {
		Mannschaft next = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		int id = mannschaft.getId();
		try {
			conn = getConnection();
			String sql = "SELECT m.id AS 'm.id', m.name AS 'm.name', s.name AS 's.name', s.id AS 's.id' "
					+ "FROM mannschaft m "
					+ "JOIN sportart s ON m.sportart_id = s.id "
					+ "WHERE m.id > ? ORDER BY m.id ASC";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id); 
			ResultSet resultSet = preparedStatement.executeQuery();
			boolean prüfen = resultSetPruefen(resultSet);
			System.out.println(prüfen);
			if(prüfen==false) {
				next = mannschaft;
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

	//letzte Mannschaft
	public Mannschaft last() {
		Mannschaft last = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = getConnection();
			String sql = "SELECT m.id AS 'm.id', m.name AS 'm.name', s.name AS 's.name', s.id AS 's.id' "
					+ "FROM mannschaft m "
					+ "JOIN sportart s ON m.sportart_id = s.id "
					+ "WHERE m.id = (SELECT MAX(id) FROM mannschaft)";
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

	public Mannschaft create(ResultSet resultSet) throws SQLException {
		Sportart sportart = new Sportart();
		Mannschaft mannschaft = new Mannschaft();
		//resultSet.next();
		sportart.setId(resultSet.getInt("s.id"));
		sportart.setName(resultSet.getString("s.name"));
		mannschaft.setId(resultSet.getInt("m.id"));
		mannschaft.setName(resultSet.getString("m.name"));
		mannschaft.setSportart(sportart);
		return mannschaft;
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