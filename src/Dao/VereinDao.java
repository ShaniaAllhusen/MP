package Dao;

import java.sql.Connection;
import java.sql.Statement;

public class VereinDao {

	private String CLASSNAME = "org.sqlite.JDBC";
	private static String datei;
	public static String CONNECTIONSTRING = "jdbc:sqlite:";

	public VereinDao() throws ClassNotFoundException {
		Class.forName(CLASSNAME);
		datei = this.getClass().getResource("mittelstufenprojekt_datenbank.db").getPath();
	}

	//Methoden
	
}

