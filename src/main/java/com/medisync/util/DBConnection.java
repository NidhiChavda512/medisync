package com.medisync.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static DBConnection dbConnection;
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String username = "system";
	private final String password = "SYSTEM";
	private Connection connection;

	private DBConnection() {
		super();
	}

	public static DBConnection getDbConnection() {
		if (dbConnection == null) {
			synchronized (DBConnection.class) {
				if (dbConnection == null) {
					dbConnection = new DBConnection();
				}
			}

		}

		return dbConnection;
	}

	public Connection getConnection() {
		try {
			if (connection == null) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection(url, username, password);
				if (!connection.isClosed()) {
					System.out.println("In get Connection if");
					System.out.println(connection.isClosed());
					return connection;
				}
			}
		} catch (SQLException e) {
			System.out.println("Driver Error");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Driver Error");
			e.printStackTrace();
		}
		return connection;
	}

	public boolean closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
		return connection.isClosed();
	}
}
