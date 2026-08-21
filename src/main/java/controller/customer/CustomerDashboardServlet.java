package controller.customer;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.DashboardDAO;

@WebServlet("/CustomerDashboardServlet")
public class CustomerDashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String base = request.getContextPath();

		if (session.getAttribute("customer") == null) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		Integer customerId = (Integer) session.getAttribute("customerId");

		if (customerId == null) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		DashboardDAO dao = new DashboardDAO();
		request.setAttribute("stats", dao.getCustomerStats(customerId));

		RequestDispatcher rd = request.getRequestDispatcher("/views/customer/customerDashboard.jsp");
		rd.forward(request, response);
	}
}
