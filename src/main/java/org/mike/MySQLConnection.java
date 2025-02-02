package org.mike;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQLConnection {
public static Connection connect() throws SQLException {

	try {

		Class.forName("com.mysql.cj.jdbc.Driver");

		// Get database credentials from org.mike.DatabaseConfig class
		var jdbcUrl = DatabaseConfig.getDbUrl();
		var user = DatabaseConfig.getDbUsername();
		var password = DatabaseConfig.getDbPassword();

		// Open a connection
		return DriverManager.getConnection(jdbcUrl, user, password);

	} catch (SQLException | ClassNotFoundException e) {
		System.err.println(e.getMessage());
		return null;
	}
}
}
