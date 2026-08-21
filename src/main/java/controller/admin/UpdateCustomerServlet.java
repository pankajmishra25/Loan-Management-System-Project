package controller.admin;

import java.io.IOException;

import dao.CustomerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;

@WebServlet("/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String base = request.getContextPath();

		Customer c = new Customer();

		c.setCustomerId(Integer.parseInt(request.getParameter("customer_id")));
		c.setName(request.getParameter("name"));
		c.setPhone(request.getParameter("phone"));
		c.setEmail(request.getParameter("email"));
		c.setAddress(request.getParameter("address"));
		c.setAccountStatus(request.getParameter("status"));
		c.setGender(request.getParameter("gender"));
		c.setDob(request.getParameter("dob"));
		c.setSalary(Double.parseDouble(request.getParameter("salary")));
		c.setOccupation(request.getParameter("occupation"));

		CustomerDAO dao = new CustomerDAO();

		if (dao.updateCustomer(c)) {
			response.sendRedirect(base + "/ViewCustomerServlet?id=" + c.getCustomerId() + "&success=1");
		} else {
			response.sendRedirect(base + "/ViewCustomerServlet?id=" + c.getCustomerId() + "&error=1");
		}
	}
}
