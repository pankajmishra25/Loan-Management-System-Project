package controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.LoanDAO;
import model.Loan;

@WebServlet("/AllLoansServlet")
public class AllLoansServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String base = request.getContextPath();

		if (session.getAttribute("role") == null || !"admin".equals(session.getAttribute("role"))) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		String status = request.getParameter("status");

		LoanDAO dao = new LoanDAO();
		List<Loan> loans = dao.getAllLoans(status);

		request.setAttribute("loans", loans);
		request.setAttribute("selectedStatus", status);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/allLoanApplications.jsp");
		rd.forward(request, response);
	}
}