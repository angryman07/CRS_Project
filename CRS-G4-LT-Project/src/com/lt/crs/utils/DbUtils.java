package com.lt.crs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";

	static final String USER = "root";
	static final String PASS = "root";
	
	public Connection createConnection() {

		Connection conn = null;

		try{			   
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeConnection(Connection conn) {
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
