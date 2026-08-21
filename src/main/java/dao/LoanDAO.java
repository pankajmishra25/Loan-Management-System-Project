package dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import model.Defaulter;
import model.EmiSchedule;
import model.Loan;
import model.LoanType;
import model.Payment;
import util.DBConnection;

public class LoanDAO {

	public boolean applyLoan(Loan loan) {

		boolean status = false;

		try {
			Connection con = DBConnection.getConnection();

			String sql = "INSERT INTO loan(customer_id, loan_type, amount, interest_rate, tenure, emi, purpose, status) VALUES (?,?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, loan.getCustomerId());
			ps.setString(2, loan.getLoanType());
			ps.setDouble(3, loan.getAmount());
			ps.setDouble(4, loan.getInterestRate());
			ps.setInt(5, loan.getDuration());
			ps.setDouble(6, loan.getEmi());
			ps.setString(7, loan.getPurpose());
			ps.setString(8, "Pending");

			status = ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

//	Method to fetch all loan types
	public List<LoanType> getAllLoanTypes() {

		List<LoanType> list = new ArrayList<>();

		try {
			Connection con = DBConnection.getConnection();

			String sql = "SELECT * FROM loan_type";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				LoanType lt = new LoanType();

				lt.setLoanTypeId(rs.getInt("loan_type_id"));
				lt.setLoanType(rs.getString("loan_type"));
				lt.setInterestRate(rs.getDouble("interest_rate"));
				lt.setMaxAmount(rs.getDouble("max_amount"));
				lt.setMaxDuration(rs.getInt("max_duration"));

				list.add(lt);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

//	Get loan type by id
	public LoanType getLoanTypeById(int id) {

		LoanType lt = null;

		try {
			Connection con = DBConnection.getConnection();

			String sql = "SELECT * FROM loan_type WHERE loan_type_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				lt = new LoanType();

				lt.setLoanTypeId(rs.getInt("loan_type_id"));
				lt.setLoanType(rs.getString("loan_type"));
				lt.setInterestRate(rs.getDouble("interest_rate"));
				lt.setMaxAmount(rs.getDouble("max_amount"));
				lt.setMaxDuration(rs.getInt("max_duration"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lt;
	}

//	Method for Approved Loans
	public List<Loan> getApprovedLoans() {
		List<Loan> list = new ArrayList<>();

		try {
			Connection con = DBConnection.getConnection();
			/* String query = "SELECT * FROM loan WHERE status='Approved'"; */
			String query = "SELECT l.loan_id, l.loan_type, l.amount, l.emi, l.balance, l.status, l.purpose, c.name "
					+ "FROM loan l " + "JOIN customer c ON l.customer_id = c.customer_id "
					+ "WHERE l.status = 'Approved'";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Loan loan = new Loan();

				loan.setLoanId(rs.getInt("loan_id"));
				loan.setCustomerId(rs.getInt("customer_id"));
				loan.setCustomerName(rs.getString("name"));
				loan.setLoanType(rs.getString("loan_type"));
				loan.setAmount(rs.getDouble("amount"));
				loan.setPurpose(rs.getString("purpose"));
				loan.setEmi(rs.getDouble("emi"));
				loan.setBalance(rs.getDouble("balance"));
				loan.setStatus(rs.getString("status"));

				list.add(loan);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

//	Method to get all  pending loans (By Admin)
	public List<Loan> getPendingLoans() {

		List<Loan> list = new ArrayList<>();

		try {
			Connection con = DBConnection.getConnection();

			String query = "SELECT l.*, c.name " + "FROM loan l "
					+ "LEFT JOIN customer c ON l.customer_id = c.customer_id " + "WHERE l.status = 'Pending'";

			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Loan loan = new Loan();

				loan.setLoanId(rs.getInt("loan_id"));
				loan.setCustomerId(rs.getInt("customer_id"));
				loan.setCustomerName(rs.getString("name"));
				loan.setLoanType(rs.getString("loan_type"));
				loan.setAmount((long) Math.round(rs.getDouble("amount")));
				loan.setPurpose(rs.getString("purpose"));
				loan.setBalance(rs.getDouble("balance"));
				loan.setEmi(rs.getDouble("emi"));
				loan.setStatus(rs.getString("status"));
				loan.setDueDate(rs.getDate("due_date"));
				loan.setEndDate(rs.getDate("end_date"));

				list.add(loan);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

//	Method to get loans by customer

	public List<Loan> getLoansByCustomer(int customerId) {

		List<Loan> list = new ArrayList<>();

		try {
			Connection con = DBConnection.getConnection();
			String sql = "SELECT loan_id, loan_type, amount, emi, balance, status, approval_date, due_date, end_date FROM loan WHERE customer_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, customerId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Loan loan = new Loan();

				loan.setLoanId(rs.getInt("loan_id"));
				loan.setLoanType(rs.getString("loan_type"));
				loan.setAmount(rs.getDouble("amount"));
				loan.setEmi(rs.getDouble("emi"));
				loan.setBalance(rs.getDouble("balance"));
				loan.setStatus(rs.getString("status"));

				loan.setApprovalDate(rs.getDate("approval_date"));
				loan.setDueDate(rs.getDate("due_date"));
				loan.setEndDate(rs.getDate("end_date"));

				list.add(loan);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

//	Method to approve loan (By Admin)
	public boolean approveLoan(int loanId) {

		boolean status = false;

		try {
			Connection con = DBConnection.getConnection();

			String sql = "UPDATE loan SET " + "status='Approved', " + "balance=amount, " + "approval_date=CURDATE(), "
					+ "due_date=DATE_ADD(CURDATE(), INTERVAL 1 MONTH), "
					+ "end_date=DATE_ADD(CURDATE(), INTERVAL tenure MONTH) " + "WHERE loan_id=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, loanId);

			status = ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

//	Method to reject loan (By Admin)
	public boolean rejectLoan(int loanId) {

		boolean status = false;

		try {
			Connection con = DBConnection.getConnection();

			String sql = "UPDATE loan SET status='Rejected' WHERE loan_id=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, loanId);

			status = ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

//	Get all loans (By Admin)
	public List<Loan> getAllLoans(String statusFilter) {

		List<Loan> list = new ArrayList<>();

		try {
			Connection con = DBConnection.getConnection();

			String sql;

			if (statusFilter != null && !statusFilter.isEmpty()) {
				sql = "SELECT l.*, c.name FROM loan l JOIN customer c ON l.customer_id = c.customer_id WHERE l.status=?";
			} else {
				sql = "SELECT l.*, c.name FROM loan l JOIN customer c ON l.customer_id = c.customer_id";
			}

			PreparedStatement ps = con.prepareStatement(sql);

			if (statusFilter != null && !statusFilter.isEmpty()) {
				ps.setString(1, statusFilter);
			}

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Loan loan = new Loan();

				loan.setLoanId(rs.getInt("loan_id"));
				loan.setLoanType(rs.getString("loan_type"));
				loan.setCustomerName(rs.getString("name"));
				loan.setAmount(rs.getDouble("amount"));
				loan.setPurpose(rs.getString("purpose"));
				loan.setEmi(rs.getDouble("emi"));
				loan.setBalance(rs.getDouble("balance"));
				loan.setStatus(rs.getString("status"));

				loan.setApprovalDate(rs.getDate("approval_date"));
				loan.setDueDate(rs.getDate("due_date"));
				loan.setEndDate(rs.getDate("end_date"));

				list.add(loan);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Loan getLoanById(int loanId) {

		Loan loan = null;

		try {
			Connection con = DBConnection.getConnection();

			String query = "SELECT * FROM loan WHERE loan_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, loanId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				loan = new Loan();

				loan.setLoanId(rs.getInt("loan_id"));
				loan.setLoanType(rs.getString("loan_type"));
				loan.setEmi(rs.getDouble("emi"));
				loan.setBalance(rs.getDouble("balance"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return loan;
	}

//	Method to get defaulter list
	public List<Defaulter> getDefaulters() {

		List<Defaulter> list = new ArrayList<>();

		try {
			Connection con = DBConnection.getConnection();

			String query = "SELECT l.*, c.name FROM loan l " + "JOIN customer c ON l.customer_id = c.customer_id "
					+ "WHERE l.due_date < CURDATE() AND l.status='Approved' AND l.balance > 0";

			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Defaulter d = new Defaulter();

				d.setLoanId(rs.getInt("loan_id"));
				d.setCustomerName(rs.getString("name"));
				d.setLoanType(rs.getString("loan_type"));
				d.setAmount(rs.getDouble("amount"));
				d.setDueDate(rs.getString("due_date"));
				d.setBalance(rs.getDouble("balance"));

				list.add(d);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

//	Get active loans by customer
	public List<Loan> getActiveLoansByCustomer(String email) {

		List<Loan> list = new ArrayList<>();

		try {
			Connection con = DBConnection.getConnection();

			String sql = "SELECT l.* FROM loan l " + "JOIN customer c ON l.customer_id=c.customer_id "
					+ "WHERE c.email=? AND l.status='Approved'";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Loan loan = new Loan();

				loan.setLoanId(rs.getInt("loan_id"));
				loan.setLoanType(rs.getString("loan_type"));
				loan.setAmount(rs.getDouble("amount"));
				loan.setBalance(rs.getDouble("balance"));
				loan.setEmi(rs.getDouble("emi"));

				list.add(loan);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

//	Method for processing payment
	public boolean processRepayment(int loanId, double amount) {

		boolean success = false;
		try {
			Connection con = DBConnection.getConnection();

			// 🔹 Get current balance
			PreparedStatement ps1 = con.prepareStatement("SELECT balance FROM loan WHERE loan_id=?");
			ps1.setInt(1, loanId);

			ResultSet rs = ps1.executeQuery();

			double balance = 0;

			if (rs.next()) {
				balance = rs.getDouble("balance");
			} else {
				return false; // loan not found
			}

			double newBalance = balance - amount;

			// 🔹 Update loan
			if (newBalance <= 0) {

				PreparedStatement ps2 = con.prepareStatement(
						"UPDATE loan SET status='Closed', balance=0, end_date=CURDATE() WHERE loan_id=?");
				ps2.setInt(1, loanId);
				ps2.executeUpdate();

			} else {

				PreparedStatement ps2 = con.prepareStatement("UPDATE loan SET balance=? WHERE loan_id=?");
				ps2.setDouble(1, newBalance);
				ps2.setInt(2, loanId);
				ps2.executeUpdate();

				// 🔥 FIXED SYNTAX ERROR HERE
				PreparedStatement ps4 = con.prepareStatement(
						"UPDATE loan SET due_date=DATE_ADD(due_date, INTERVAL 1 MONTH) WHERE loan_id=?");
				ps4.setInt(1, loanId);
				ps4.executeUpdate();
			}

			// 🔹 Insert payment
			PreparedStatement ps3 = con.prepareStatement(
					"INSERT INTO payment(loan_id, payment_date, amount_paid) VALUES(?, CURDATE(), ?)");
			ps3.setInt(1, loanId);
			ps3.setDouble(2, amount);
			ps3.executeUpdate();

			success = true;

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}

		return success;
	}

//	Get payment history by customer
	public List<Payment> getPaymentHistoryByCustomer(String email) {

		List<Payment> list = new ArrayList<>();

		try {
			Connection con = DBConnection.getConnection();

			String sql = "SELECT p.*, l.loan_type FROM payment p " + "JOIN loan l ON p.loan_id = l.loan_id "
					+ "JOIN customer c ON l.customer_id = c.customer_id "
					+ "WHERE c.email=? ORDER BY p.payment_date DESC";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Payment p = new Payment();

				p.setPaymentId(rs.getInt("payment_id"));
				p.setLoanId(rs.getInt("loan_id"));
				p.setAmountPaid(rs.getDouble("amount_paid"));
				p.setPaymentDate(rs.getDate("payment_date"));
				p.setLoanType(rs.getString("loan_type"));

				list.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

//    Method to calculate EMI schedule
	public List<EmiSchedule> getEmiSchedule(int loanId) {

		List<EmiSchedule> list = new ArrayList<>();

		try {
			Connection con = DBConnection.getConnection();

			// 🔹 Get loan details
			PreparedStatement ps = con
					.prepareStatement("SELECT amount, interest_rate, tenure FROM loan WHERE loan_id=?");
			ps.setInt(1, loanId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				double P = rs.getDouble("amount");
				double annualRate = rs.getDouble("interest_rate");
				int N = rs.getInt("tenure");

				double R = annualRate / 12 / 100;

				double emi = (P * R * Math.pow(1 + R, N)) / (Math.pow(1 + R, N) - 1);

				double balance = P;

				for (int i = 1; i <= N; i++) {

					double interest = balance * R;
					double principal = emi - interest;
					balance -= principal;

					EmiSchedule e = new EmiSchedule();

					e.setMonth(i);
					e.setEmi(emi);
					e.setInterest(interest);
					e.setPrincipal(principal);
					e.setBalance(Math.max(balance, 0));

					list.add(e);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int makePayment(int loanId, double amount) {
		int paymentId = -1;

		Connection con = null;
		PreparedStatement ps1 = null, ps2 = null, ps3 = null, ps4 = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);

			// 1. Get balance
			ps1 = con.prepareStatement("SELECT balance FROM loan WHERE loan_id=?");
			ps1.setInt(1, loanId);
			rs = ps1.executeQuery();

			double balance = 0;
			if (rs.next()) {
				balance = rs.getDouble("balance");
			}

			double newBalance = balance - amount;

			// 2. Update loan
			if (newBalance <= 0) {
				ps2 = con.prepareStatement(
						"UPDATE loan SET status='Closed', balance=0, end_date=CURDATE() WHERE loan_id=?");
				ps2.setInt(1, loanId);
			} else {
				ps2 = con.prepareStatement("UPDATE loan SET balance=? WHERE loan_id=?");
				ps2.setDouble(1, newBalance);
				ps2.setInt(2, loanId);
			}
			ps2.executeUpdate();

			// 3. Insert payment (GET GENERATED KEY)
			ps3 = con.prepareStatement(
					"INSERT INTO payment(loan_id, payment_date, amount_paid) VALUES(?, CURDATE(), ?)",
					Statement.RETURN_GENERATED_KEYS);

			ps3.setInt(1, loanId);
			ps3.setDouble(2, amount);
			ps3.executeUpdate();

			ResultSet keyRs = ps3.getGeneratedKeys();
			if (keyRs.next()) {
				paymentId = keyRs.getInt(1);
			}

			// 4. Update due date
			if (newBalance > 0) {
				ps4 = con.prepareStatement(
						"UPDATE loan SET due_date=DATE_ADD(due_date, INTERVAL 1 MONTH) WHERE loan_id=?");
				ps4.setInt(1, loanId);
				ps4.executeUpdate();
			}

			con.commit();

		} catch (Exception e) {
			try {
				if (con != null)
					con.rollback();
			} catch (Exception ex) {
			}
			e.printStackTrace();
			paymentId = -1;
		}

		return paymentId;
	}

	public Payment getPaymentDetails(int paymentId) {
		Payment p = null;

		try {
			Connection con = DBConnection.getConnection();

			String sql = "SELECT p.*, l.loan_type, c.name " + "FROM payment p "
					+ "JOIN loan l ON p.loan_id = l.loan_id " + "JOIN customer c ON l.customer_id = c.customer_id "
					+ "WHERE p.payment_id=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, paymentId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				p = new Payment();
				p.setPaymentId(rs.getInt("payment_id"));
				p.setLoanId(rs.getInt("loan_id"));
				p.setLoanType(rs.getString("loan_type"));
				p.setPaymentDate(rs.getDate("payment_date"));
				p.setAmountPaid(rs.getDouble("amount_paid"));
				p.setCustomerName(rs.getString("name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return p;
	}
}