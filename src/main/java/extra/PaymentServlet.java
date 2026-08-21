package extra;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.LoanDAO;
import model.Loan;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("customer") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String loanIdStr = request.getParameter("loanId");

		if (loanIdStr == null || loanIdStr.isEmpty()) {
			response.sendRedirect("ViewRepaymentServlet");
			return;
		}
		int loanId = Integer.parseInt("loanIdStr");

		LoanDAO dao = new LoanDAO();
		Loan loan = dao.getLoanById(loanId);

		request.setAttribute("loan", loan);

		RequestDispatcher rd = request.getRequestDispatcher("payment.jsp");
		rd.forward(request, response);
	}
}