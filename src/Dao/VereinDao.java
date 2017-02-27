
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		datei = this.getClass().getResource("testdatenbank.db").toString();
		//		datei = "F:\\workspace\\MP\\bin\\dao\\testdatenbank.db";
		//		datei = "jdbc:sqlite:" + datei;
	}

	//Methoden

	private Connection getConnection() {
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(CONNECTIONSTRING + datei); 
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
			String sql = "SELECT * FROM benutzer WHERE username = ? AND passwort = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {				
				loggedIn = true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Benutzername oder Passwort ist falsch.", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
			}

		} catch(SQLException e) {
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



	//	public ArrayList<Training> select(){
	public DefaultTableModel select() {
		JTable data = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		ArrayList<Training> arrayListTraining = new ArrayList<Training>(); 
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = DriverManager.getConnection(CONNECTIONSTRING + datei); 
			String sql = "SELECT  m.name AS 'Mannschaft' , s.name AS 'Sportart',  h.name AS 'Halle', h.strasse AS 'Strasse', h.plz AS 'PLZ',"
					+ "  h.ort AS 'Ort' FROM training t "
					+ "JOIN mannschaft m ON t.mannschaft_id = m.id "
					+ "JOIN sportart s ON m.sportart_id = s.id "
					+ "JOIN halle h ON t.halle_id = h.id";
			//t.id as 'training', t.halle_id as 'halle', m.id as 'mannschaft',s.id as 'sportart',h.id,
			preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				//			Sportart sportart = new Sportart(resultSet.getInt("id"),resultSet.getString("name"));
				//				Mannschaft mannschaft = new Mannschaft(resultSet.getInt("id"), resultSet.getString("name"), sportart);
				//				Halle halle = new Halle(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("strasse"), 
				//						resultSet.getString("plz"), resultSet.getString("ort"));
				//				Training training = new Training(resultSet.getInt("id"), mannschaft, halle);
				//				arrayListTraining.add(training);
				model.addRow(new Object[] {resultSet.getString("Mannschaft"), resultSet.getString("Sportart")});
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
		return model;
		//		return arrayListTraining; 
	}

	public String[][] getTable() throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conn = DriverManager.getConnection(CONNECTIONSTRING + datei); 

		ArrayList<String[]> list = new ArrayList<String[]>();

		String sql = "select m.name as 'mannschaft', s.name as 'sportart' from mannschaft m "
				+ "join sportart s on s.id = m.sportart_id "
				+ "join training t on t.mannschaft_id = m.id "
				+ "join training_zeitblock tz on tz.training_id = t.id "
				+ "join zeitblock z on z.id = tz.zeitblock_id "
				+ "where z.wochentag = 'Montag'";
		preparedStatement = conn.prepareStatement(sql);
		//preparedStatement.setString(1, wochentag);

		ResultSet rs = preparedStatement.executeQuery(); //Ändern für Reihenfolge // Toolbar für Zeilenumbruch
		while (rs.next()) {
			String[] data = {rs.getString("mannschaft"), rs.getString("sportart")};
			list.add(data);
		}

		return list.toArray(new String[list.size()][list.get(0).length]);
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
					+ "JOIN zeitblock z ON tz.zeitblock_id = z.id AND uhrzeit_beginn LIKE '?*'"
					+ "WHERE h.id = ? AND z.wochentag = ? ";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, halleId); 
			preparedStatement.setString(2, wochentag);
			preparedStatement.setInt(1, uhrzeit); 
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Sportart sportart = new Sportart();
				sportart.setId(resultSet.getInt("s.id"));
				sportart.setName(resultSet.getString("s.name"));
				Mannschaft mannschaft = new Mannschaft();
				mannschaft.setId(resultSet.getInt("m.id"));
				mannschaft.setName(resultSet.getString("m.name"));
				mannschaft.setSportart(sportart);
				Halle halle = new Halle();
				halle.setId(resultSet.getInt("h.id"));
				halle.setName(resultSet.getString("h.name"));
				halle.setStrasse(resultSet.getString("h.strasse"));
				halle.setPlz(resultSet.getString("h.plz"));
				halle.setOrt(resultSet.getString("h.ort"));
				Training training = new Training();
				training.setId(resultSet.getInt("t.id"));
				training.setMannschaft(mannschaft);
				training.setHalle(halle);
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
	public Mannschaft insert (Mannschaft mannschaft) {
		PreparedStatement ps= null;
		String Mannschaft = null;
		Connection connection = null;
	
		try{
			
			String sql = "Insert into Mannschaft (id, name, sportart_id) VALUES (?, ?, ?)";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, mannschaft.getId());
			ps.setString(2, mannschaft.getName());
			//ps.setInt(3, mannschaft.getSportart_id());
			ps.executeUpdate();
			ResultSet resultSet = ps.getGeneratedKeys();
			resultSet.next();
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				ps.close();
				connection.close();

			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return mannschaft;
	}
}