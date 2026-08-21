package controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.LoanDAO;
import model.Loan;

@WebServlet("/AdminLoansServlet")
public class AdminLoansServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String base = request.getContextPath();

		if (session.getAttribute("role") == null || !"admin".equals(session.getAttribute("role"))) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		LoanDAO dao = new LoanDAO();
		List<Loan> loans = dao.getPendingLoans();

		request.setAttribute("loans", loans);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/viewPendingApplications.jsp");
		rd.forward(request, response);
	}
}