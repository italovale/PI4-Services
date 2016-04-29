package br.com.senac.pi4.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseUtil {

	private static DatabaseUtil instance = null;
	
	private DatabaseUtil () {};
	
	public static DatabaseUtil get () {
		if (instance == null)
			instance = new DatabaseUtil();
		return instance;
	}
	
	public Connection conn () throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://koo2dzw5dy.database.windows.net;user=TSI@koo2dzw5dy.database.windows.net;password=SistemasInternet123;database=SenaQuiz");
		return conn;
	}
	
}
