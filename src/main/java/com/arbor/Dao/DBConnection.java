package com.arbor.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	 private static final String URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	    private static final String USERNAME = "ARBOR_GOLD";
	    private static final String PASSWORD = "arbor_gold";

	    public static Connection getConnection() throws SQLException {
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	        } catch (ClassNotFoundException e) {
	            throw new SQLException("Oracle JDBC Driver not found!", e);
	        }
	    }

}
