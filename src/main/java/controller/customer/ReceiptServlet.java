package controller.customer;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.LoanDAO;
import model.Payment;

@WebServlet("/ReceiptServlet")
public class ReceiptServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("customer") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}

		int paymentId = Integer.parseInt(request.getParameter("id"));

		LoanDAO dao = new LoanDAO();
		Payment payment = dao.getPaymentDetails(paymentId);

		if (payment != null) {
			request.setAttribute("payment", payment);
			request.getRequestDispatcher("/views/customer/receipt.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/PaymentHistoryServlet");
		}
	}
}