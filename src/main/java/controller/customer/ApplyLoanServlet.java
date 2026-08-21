package controller.customer;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.LoanDAO;
import model.Loan;
import model.LoanType;
import util.FormatUtil;

@WebServlet("/ApplyLoanServlet")
public class ApplyLoanServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String base = request.getContextPath();

		if (session == null || session.getAttribute("customerId") == null) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		try {

			int loanTypeId = Integer.parseInt(request.getParameter("loanTypeId"));
			double amount = Double.parseDouble(request.getParameter("amount"));
			int duration = Integer.parseInt(request.getParameter("duration"));
			String purpose = (String) request.getParameter("purpose");

			int customerId = (int) session.getAttribute("customerId");
//			String email = (String) session.getAttribute("customer");

//			CustomerDAO customerDAO = new CustomerDAO();
//			int customerId = customerDAO.getCustomerIdByEmail(email);

			LoanDAO loanDAO = new LoanDAO();
			LoanType loanTypeDetails = loanDAO.getLoanTypeById(loanTypeId);

			double rate = loanTypeDetails.getInterestRate();

//		EMI Calculation
			double monthlyRate = rate / 12 / 100;
			double emi = (amount * monthlyRate * Math.pow(1 + monthlyRate, duration))
					/ (Math.pow(1 + monthlyRate, duration) - 1);

//		Set loan object
			Loan loan = new Loan();
			loan.setCustomerId(customerId);
			loan.setLoanType(loanTypeDetails.getLoanType());
			loan.setAmount(amount);
			loan.setPurpose(purpose);
			loan.setInterestRate(rate);
			loan.setDuration(duration);
			loan.setEmi(emi);

			boolean status = loanDAO.applyLoan(loan);

			if (status) {
//				String message = "Loan applied successfully! Your EMI is " + FormatUtil.currency(emi);
//				response.sendRedirect(base + "/CustomerLoansServlet?success=" + message);
				String message = "Loan applied successfully! Your EMI is " + FormatUtil.currency(emi);

				String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);

				response.sendRedirect(base + "/CustomerLoansServlet?success=" + encodedMessage);
			} else {
				response.sendRedirect(base + "/ApplyLoanServlet?error=Failed to apply loan");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/ApplyLoanServlet?error=Something went wrong");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String base = request.getContextPath();

		// ✅ ADD THIS CHECK
		if (session == null || session.getAttribute("customerId") == null) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		LoanDAO dao = new LoanDAO();
		request.setAttribute("loanTypes", dao.getAllLoanTypes());

		RequestDispatcher rd = request.getRequestDispatcher("/views/customer/applyLoan.jsp");
		rd.forward(request, response);
	}
}
