//package dao;
//
//import java.sql.*;
//
//import util.DBConnection;
//
//public class AdminDAO {
//
////	Method to login by admin
//	public boolean login(String username, String password) {
//		try {
//			Connection con = DBConnection.getConnection();
//			String sql = "SELECT * FROM admin WHERE username=? AND password=?";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, username);
//			ps.setString(2, password);
//
//			ResultSet rs = ps.executeQuery();
//			return rs.next();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
////	Method to check password
//	public boolean checkPassword(String username, String oldPassword) {
//
//		boolean match = false;
//
//		try {
//			Connection con = DBConnection.getConnection();
//
//			String sql = "SELECT * FROM admin WHERE username=? AND password=?";
//			PreparedStatement ps = con.prepareStatement(sql);
//
//			ps.setString(1, username);
//			ps.setString(2, oldPassword);
//
//			ResultSet rs = ps.executeQuery();
//
//			if (rs.next()) {
//				match = true;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return match;
//	}
//	
////	Method to update/change password
//	public boolean changePassword(String username, String newPassword) {
//
//		boolean status = false;
//
//		try {
//			Connection con = DBConnection.getConnection();
//
//			String sql = "UPDATE admin SET password=? WHERE username=?";
//			PreparedStatement ps = con.prepareStatement(sql);
//
//			ps.setString(1, newPassword);
//			ps.setString(2, username);
//
//			int rows = ps.executeUpdate();
//
//			status = rows > 0;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return status;
//	}
//}
package extra;

