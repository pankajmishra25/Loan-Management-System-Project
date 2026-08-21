package dao;

import java.sql.*;
import model.DashboardStats;
import util.DBConnection;

public class DashboardDAO {

	public DashboardStats getAdminStats() {

		DashboardStats stats = new DashboardStats();

		try {
			Connection con = DBConnection.getConnection();

			stats.setTotalLoans(getCount(con, "SELECT COUNT(*) FROM loan"));
			stats.setApproved(getCount(con, "SELECT COUNT(*) FROM loan WHERE status='Approved'"));
			stats.setRejected(getCount(con, "SELECT COUNT(*) FROM loan WHERE status='Rejected'"));
			stats.setPending(getCount(con, "SELECT COUNT(*) FROM loan WHERE status='Pending'"));
			stats.setClosed(getCount(con, "SELECT COUNT(*) FROM loan WHERE status='Closed'"));
			stats.setOverdue(
					getCount(con, "SELECT COUNT(*) FROM loan WHERE due_date <= CURDATE() AND status='Approved'"));
			stats.setDefaulters(getCount(con,
					"SELECT COUNT(*) FROM loan WHERE due_date < CURDATE() AND status='Approved' AND balance > 0"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stats;
	}

	public DashboardStats getCustomerStats(int customerId) {

		DashboardStats stats = new DashboardStats();

		try {
			Connection con = DBConnection.getConnection();

			stats.setTotalLoans(getCount(con, "SELECT COUNT(*) FROM loan WHERE customer_id=" + customerId));
			stats.setOverdue(getCount(con,
					"SELECT COUNT(*) FROM loan WHERE due_date <= CURDATE() AND customer_id=" + customerId));
			stats.setApproved(getCount(con,
					"SELECT COUNT(*) FROM loan WHERE customer_id=" + customerId + " AND status='Approved'"));
			stats.setRejected(getCount(con,
					"SELECT COUNT(*) FROM loan WHERE customer_id=" + customerId + " AND status='Rejected'"));
			stats.setPending(getCount(con,
					"SELECT COUNT(*) FROM loan WHERE customer_id=" + customerId + " AND status='Pending'"));
			stats.setClosed(getCount(con,
					"SELECT COUNT(*) FROM loan WHERE customer_id=" + customerId + " AND status='Closed'"));

//			Total paid
			PreparedStatement ps = con.prepareStatement(
					"SELECT IFNULL(SUM(p.amount_paid), 0) FROM payment p JOIN loan l ON p.loan_id=l.loan_id WHERE l.customer_id=?");
			ps.setInt(1, customerId);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				stats.setTotalPaid((long) Math.round(rs.getDouble(1)));
			}

//			Remaining balance
			PreparedStatement ps1 = con.prepareStatement(
					"SELECT IFNULL(SUM(balance), 0) FROM loan WHERE customer_id=? AND status='Approved'");
			ps1.setInt(1, customerId);

			ResultSet rs1 = ps1.executeQuery();

			if (rs1.next()) {
				stats.setBalance((long) Math.round(rs1.getDouble(1)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stats;
	}

	private int getCount(Connection con, String query) throws Exception {
		ResultSet rs = con.createStatement().executeQuery(query);
		rs.next();
		return rs.getInt(1);
	}
}