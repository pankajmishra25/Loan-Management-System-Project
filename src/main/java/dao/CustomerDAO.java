package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import util.DBConnection;

public class CustomerDAO {

//	Method to register as customer
	public boolean register(Customer c) {

		boolean status = false;

		try {
			Connection con = DBConnection.getConnection();

			String sql = "INSERT INTO customer(name,email,password,phone,address,gender,dob,annual_salary,occupation,account_status) VALUES(?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail());
			ps.setString(3, c.getPassword());
			ps.setString(4, c.getPhone());
			ps.setString(5, c.getAddress());
			ps.setString(6, c.getGender());
			ps.setString(7, c.getDob());
			ps.setDouble(8, c.getSalary());
			ps.setString(9, c.getOccupation());
			ps.setString(10, "Active"); // default

			status = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

//	Method to get customer by email id
	public int getCustomerIdByEmail(String email) {

		int id = 0;

		try {
			Connection con = DBConnection.getConnection();
			String sql = "SELECT customer_id FROM customer WHERE email=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getInt("customer_id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return id;
	}

//	Method to get customer by email
	public Customer getCustomerByEmail(String email) {

		Customer c = null;

		try {
			Connection con = DBConnection.getConnection();

			String sql = "SELECT * FROM customer WHERE email=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				c = new Customer();

				c.setCustomerId(rs.getInt("customer_id"));
				c.setName(rs.getString("name"));
				c.setEmail(rs.getString("email"));
				c.setPhone(rs.getString("phone"));
				c.setAddress(rs.getString("address"));
				c.setAccountStatus(rs.getString("account_status"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return c;
	}

//	Method to fetch all customer list
	public List<Customer> getAllCustomers() {

		List<Customer> list = new ArrayList<>();

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM customer");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Customer c = new Customer();

				c.setCustomerId(rs.getInt("customer_id"));
				c.setName(rs.getString("name"));
				c.setEmail(rs.getString("email"));
				c.setPhone(rs.getString("phone"));
				c.setAccountStatus(rs.getString("account_status"));

				list.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

//	Method to get customer by ID
	public Customer getCustomerById(int id) {

		Customer c = null;

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM customer WHERE customer_id=?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				c = new Customer();

				c.setCustomerId(rs.getInt("customer_id"));
				c.setName(rs.getString("name"));
				c.setEmail(rs.getString("email"));
				c.setPhone(rs.getString("phone"));
				c.setAddress(rs.getString("address"));
				c.setGender(rs.getString("gender"));
				c.setDob(rs.getString("dob"));
				c.setSalary(rs.getDouble("annual_salary"));
				c.setOccupation(rs.getString("occupation"));
				c.setAccountStatus(rs.getString("account_status"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return c;
	}

//	Method to update customer (By admin & customer)
	public boolean updateCustomer(Customer c) {

		boolean status = false;

		try {
			Connection con = DBConnection.getConnection();

			String sql = "UPDATE customer SET name=?, phone=?, address=?, email=?, gender=?, dob=?, annual_salary=?, occupation=?, account_status=? WHERE customer_id=?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, c.getName());
			ps.setString(2, c.getPhone());
			ps.setString(3, c.getAddress());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getGender());
			ps.setString(6, c.getDob());
			ps.setDouble(7, c.getSalary());
			ps.setString(8, c.getOccupation());
			ps.setString(9, c.getAccountStatus());
			ps.setInt(10, c.getCustomerId());

			status = ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

//	Method to get active customers

	public List<Customer> getActiveCustomers() {

		List<Customer> list = new ArrayList<>();

		try {
			Connection con = DBConnection.getConnection();

			String sql = "SELECT customer_id, name, email, phone FROM customer WHERE account_status='Active'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Customer c = new Customer();

				c.setCustomerId(rs.getInt("customer_id"));
				c.setName(rs.getString("name"));
				c.setEmail(rs.getString("email"));
				c.setPhone(rs.getString("phone"));

				list.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
