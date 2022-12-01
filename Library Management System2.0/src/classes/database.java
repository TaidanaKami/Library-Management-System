package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.util.ArrayList;
import java.util.logging.*;

public class database {
	
	private static String servername = "localhost";
	private static String username = "root";
	private static String dbname = "library management system 2.0";
	private static Integer portNumber = 3306;
	private static String password = "";
	
	public static Connection getConnection() {
		
		Connection connection = null;
		
		MysqlDataSource datasource = new MysqlDataSource();
		
		datasource.setServerName(servername);
		datasource.setUser(username);
		datasource.setDatabaseName(dbname);
		datasource.setPortNumber(portNumber);
		datasource.setServerName(password);
		
		try {
			
			connection = datasource.getConnection();
			
		}catch(SQLException ex){
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
		return connection;
		
	}

}


