package extra;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.LoanDAO;
import model.Loan;

@WebServlet("/ViewRepaymentServlet")
public class ViewRepaymentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("customer") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String email = (String) session.getAttribute("customer");

		LoanDAO dao = new LoanDAO();
		List<Loan> loans = dao.getActiveLoansByCustomer(email);

		request.setAttribute("loans", loans);

		request.getRequestDispatcher("payment.jsp").forward(request, response);
	}
}