package controller.admin;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.util.List;

import dao.LoanDAO;
import model.Loan;

@WebServlet("/ApprovedLoansServlet")
public class ApprovedLoansServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String base = request.getContextPath();

		if (session.getAttribute("admin") == null) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		LoanDAO dao = new LoanDAO();

		List<Loan> loans = dao.getApprovedLoans();

		request.setAttribute("loans", loans);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/approvedLoans.jsp");
		rd.forward(request, response);
	}
}