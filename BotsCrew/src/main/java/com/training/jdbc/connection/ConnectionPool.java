package com.training.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

	private static Connection connection = null;

	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			String jdbcUrl = "jdbc:mysql://localhost:3306/botscrew_library?user=root&password=*****";
			connection = DriverManager.getConnection(jdbcUrl);
		}
		return connection;
	}

	public static boolean closeConnection() throws SQLException {
		if (connection != null)
			connection.close();
		return connection.isClosed();
	}
}
