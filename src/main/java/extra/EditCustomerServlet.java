package extra;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.CustomerDAO;
import model.Customer;

@WebServlet("/EditCustomerServlet")
public class EditCustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		// 🔐 Admin check
		if (session == null || session.getAttribute("admin") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));

		CustomerDAO dao = new CustomerDAO();
		Customer customer = dao.getCustomerById(id);

		request.setAttribute("customerData", customer);

		request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		// 🔐 Admin check
		if (session == null || session.getAttribute("admin") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		int id = Integer.parseInt(request.getParameter("customer_id"));
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String status = request.getParameter("account_status");

		String error = "";

		// ✅ Validation
		if (name == null || name.trim().isEmpty()) {
			error = "Name cannot be empty";
		} else if (!name.matches("[A-Za-z ]+")) {
			error = "Only letters allowed in name";
		} else if (!phone.matches("\\d{10}")) {
			error = "Phone must be 10 digits";
		} else if (address == null || address.length() < 5) {
			error = "Address must be at least 5 characters";
		}

		CustomerDAO dao = new CustomerDAO();

		if (!error.equals("")) {

			Customer c = dao.getCustomerById(id);

			request.setAttribute("msg", "❌ " + error);
			request.setAttribute("customerData", c);

			request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
			return;
		}

		// ✅ Update
		Customer c = new Customer();
		c.setCustomerId(id);
		c.setName(name);
		c.setPhone(phone);
		c.setAddress(address);
		c.setEmail(email);
		c.setAccountStatus(status);

		boolean updated = dao.updateCustomer(c);

		if (updated) {
			response.sendRedirect("customerList.jsp");
		} else {
			request.setAttribute("msg", "Update failed!");
			request.setAttribute("customerData", c);
			request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
		}
	}
}