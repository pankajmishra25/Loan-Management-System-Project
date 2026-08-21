package controller.customer;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.LoanDAO;
import model.Payment;

@WebServlet("/PaymentHistoryServlet")
public class PaymentHistoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String base = request.getContextPath();

		if (session.getAttribute("customer") == null) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		String email = (String) session.getAttribute("customer");

		LoanDAO dao = new LoanDAO();
		List<Payment> payments = dao.getPaymentHistoryByCustomer(email);

		request.setAttribute("payments", payments);

		request.getRequestDispatcher("/views/customer/paymentHistory.jsp").forward(request, response);
	}
}