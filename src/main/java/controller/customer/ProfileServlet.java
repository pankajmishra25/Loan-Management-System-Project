package controller.customer;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.CustomerDAO;
import model.Customer;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String email = (String) session.getAttribute("customer");

		CustomerDAO dao = new CustomerDAO();
		Customer customer = dao.getCustomerByEmail(email);

		request.setAttribute("customerData", customer);

		request.getRequestDispatcher("/views/customer/profile.jsp").forward(request, response);
	}
}