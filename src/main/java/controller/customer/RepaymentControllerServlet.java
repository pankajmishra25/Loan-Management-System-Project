package controller.customer;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.LoanDAO;
import model.Loan;

@WebServlet("/RepaymentControllerServlet")
public class RepaymentControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String base = request.getContextPath();

		if (session == null || session.getAttribute("customer") == null) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		String action = request.getParameter("action");
		LoanDAO dao = new LoanDAO();

		// 🔹 SHOW LIST
		if (action == null || action.equals("list")) {

			String email = (String) session.getAttribute("customer");
			List<Loan> loans = dao.getActiveLoansByCustomer(email);

			request.setAttribute("loans", loans);
			request.getRequestDispatcher("/views/customer/repayment.jsp").forward(request, response);

		// 🔹 SHOW PAYMENT PAGE
		}else if (action.equals("pay")) {

			int loanId = Integer.parseInt(request.getParameter("loanId"));
			Loan loan = dao.getLoanById(loanId);
			
			String msg = request.getParameter("msg");
			request.setAttribute("msg", msg);

			request.setAttribute("loan", loan);
			request.getRequestDispatcher("/views/customer/payment.jsp").forward(request, response);
		}
	}
}
