package dao;

import java.sql.*;

import model.Customer;
import util.DBConnection;

public class AuthDAO {

	// =============================
	// 🔹 LOGIN (ADMIN + CUSTOMER)
	// =============================
	public Object login(String email, String password, String role) {

		try {
			Connection con = DBConnection.getConnection();

			if ("admin".equals(role)) {

				String sql = "SELECT * FROM admin WHERE username=? AND password=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, email);
				ps.setString(2, password);

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					return "ADMIN";
				}
			}

			else if ("customer".equals(role)) {

				String sql = "SELECT * FROM customer WHERE email=? AND password=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, email);
				ps.setString(2, password);

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {

					Customer c = new Customer();
					c.setCustomerId(rs.getInt("customer_id"));
					c.setName(rs.getString("name"));
					c.setEmail(rs.getString("email"));
					c.setAccountStatus(rs.getString("account_status"));

					return c;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// =============================
	// 🔹 CHECK PASSWORD
	// =============================
	public boolean checkPassword(String usernameOrEmail, String oldPassword, String role) {

		boolean match = false;

		try {
			Connection con = DBConnection.getConnection();

			String sql = "";

			if ("admin".equals(role)) {
				sql = "SELECT * FROM admin WHERE username=? AND password=?";
			} else {
				sql = "SELECT * FROM customer WHERE email=? AND password=?";
			}

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usernameOrEmail);
			ps.setString(2, oldPassword);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				match = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return match;
	}

	// =============================
	// 🔹 CHANGE PASSWORD
	// =============================
	public boolean changePassword(String usernameOrEmail, String newPassword, String role) {

		boolean status = false;

		try {
			Connection con = DBConnection.getConnection();

			String sql = "";

			if ("admin".equals(role)) {
				sql = "UPDATE admin SET password=? WHERE username=?";
			} else {
				sql = "UPDATE customer SET password=? WHERE email=?";
			}

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, newPassword);
			ps.setString(2, usernameOrEmail);

			int rows = ps.executeUpdate();

			status = rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}
}