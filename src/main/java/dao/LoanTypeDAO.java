package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.LoanType;
import util.DBConnection;

public class LoanTypeDAO {
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
}
