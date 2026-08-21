package controller.customer;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.LoanDAO;
import model.Loan;

@WebServlet("/CustomerLoansServlet")
public class CustomerLoansServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String base = request.getContextPath();

		if (session == null || session.getAttribute("customerId") == null) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		int customerId = (int) session.getAttribute("customerId");

		LoanDAO dao = new LoanDAO();
		List<Loan> loans = dao.getLoansByCustomer(customerId);

		request.setAttribute("loans", loans);

		String success = request.getParameter("success");
		request.setAttribute("success", success);

		RequestDispatcher rd = request.getRequestDispatcher("/views/customer/customerLoans.jsp");
		rd.forward(request, response);
	}
}