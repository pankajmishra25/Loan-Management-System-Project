package controller.auth;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Customer;
import dao.CustomerDAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String base = request.getContextPath();

		try {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			String occupation = request.getParameter("occupation");

			double salary = 0;
			String salaryStr = request.getParameter("salary");

			if (salaryStr != null && !salaryStr.isEmpty()) {
				salary = Double.parseDouble(salaryStr);
			}

//		Create object
			Customer c = new Customer();
			c.setName(name);
			c.setEmail(email);
			c.setPassword(password);
			c.setPhone(phone);
			c.setAddress(address);
			c.setGender(gender);
			c.setDob(dob);
			c.setSalary(salary);
			c.setOccupation(occupation);

			CustomerDAO dao = new CustomerDAO();
			boolean status = dao.register(c);

			if (status) {
				response.sendRedirect(base + "/LoginServlet?success=Registered Successfully!");
			} else {
				response.sendRedirect(base + "/RegisterServlet?error=Registration Failed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(base + "/RegisterServlet?error=Server Error");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Forward to register page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/auth/register.jsp");
		dispatcher.forward(request, response);
	}
}
