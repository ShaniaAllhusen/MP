package Dao;

import java.sql.Connection;
import java.sql.Statement;

public class VereinDao {

	private final String Classname = ("org.sqlite.JDBC");
	private String datei;
	private final String CONNECTIONSSTRING = ("jdbc:sqlite:");
	private static String path = null;
	private static Halle halle;
	private static Connection connection;
	private static Statement statement;

	public VereinDao() {
		try {
			Class.forName(Classname);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		path = CONNECTIONSSTRING + this.getClass().getResource("mittelstufenprojekt_datenbank.db").getPath();
	}
}

	