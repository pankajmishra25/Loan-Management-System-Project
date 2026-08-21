package controller.admin;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.LoanDAO;

@WebServlet("/ApproveLoanServlet")
public class ApproveLoanServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String base = request.getContextPath();

		if (session == null || session.getAttribute("role") == null || !"admin".equals(session.getAttribute("role"))) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		int loanId = Integer.parseInt(request.getParameter("id"));
		String action = request.getParameter("action");

		LoanDAO dao = new LoanDAO();

		if ("approve".equals(action)) {
			dao.approveLoan(loanId);
		} else {
			dao.rejectLoan(loanId);
		}

		response.sendRedirect(request.getContextPath() + "/AdminLoansServlet?msg=updated");
	}
}