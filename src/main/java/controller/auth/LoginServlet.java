package controller.auth;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.AuthDAO;
import model.Customer;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String role = request.getParameter("role");

		String base = request.getContextPath();

		HttpSession session = request.getSession(true);

		AuthDAO dao = new AuthDAO();
		Object result = dao.login(email, password, role);

		// 🔹 ADMIN LOGIN
		if ("admin".equals(role) && "ADMIN".equals(result)) {

			session.setAttribute("role", "admin");
			session.setAttribute("admin", email);

			response.sendRedirect(base + "/AdminDashboardServlet");
			return;
		}

		// 🔹 CUSTOMER LOGIN
		if ("customer".equals(role) && result instanceof Customer) {

			Customer customer = (Customer) result;

			if ("Inactive".equals(customer.getAccountStatus())) {
				response.sendRedirect(base + "/LoginServlet?error=Account disabled");
				return;
			}

			session.setAttribute("role", "customer");
			session.setAttribute("customerId", customer.getCustomerId());
			session.setAttribute("customerName", customer.getName());
			session.setAttribute("customer", customer.getEmail());

			response.sendRedirect(base + "/CustomerDashboardServlet");
			return;
		}

		// ❌ INVALID LOGIN
		response.sendRedirect(base + "/LoginServlet?error=Invalid Credentials");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Forward to login page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/auth/login.jsp");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
		}
	}
}