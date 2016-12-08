package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		datei = this.getClass().getResource("datenbank.db").getPath();
	}

	//Methoden
	public ArrayList<Training> select(){
		ArrayList<Training> arrayListTraining = new ArrayList<Training>(); 
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = DriverManager.getConnection(CONNECTIONSTRING + datei); 
			String sql = "SELECT t.id, t.halle_id, m.id, m.name , s.id , s.name, h.id, h.name, h.strasse, h.plz, h.ort FROM training t "
					+ "JOIN mannschaft m ON t.mannschaft_id = m.id "
					+ "JOIN sportart s ON m.sportart_id = s.id "
					+ "JOIN halle h ON t.halle_id = h.id";
			preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Sportart sportart = new Sportart(resultSet.getInt("s.id"),resultSet.getString("s.name"));
				Mannschaft mannschaft = new Mannschaft(resultSet.getInt("m.id"), resultSet.getString("m.name"), sportart);
				Halle halle = new Halle(resultSet.getInt("h.id"), resultSet.getString("h.name"), resultSet.getString("h.strasse"), 
						resultSet.getString("h.plz"), resultSet.getString("h.ort"));
				Training training = new Training(resultSet.getInt("t.id"), mannschaft, halle);
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
}