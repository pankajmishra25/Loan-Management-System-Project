package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static Connection con;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "pankaj", "MySql@123");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}