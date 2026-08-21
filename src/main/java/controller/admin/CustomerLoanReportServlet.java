package controller.admin;

import java.io.IOException;
import java.util.*;
import java.util.Date;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.CustomerDAO;
import dao.LoanDAO;
import model.Loan;
import model.Customer;

@WebServlet("/CustomerLoanReportServlet")
public class CustomerLoanReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String base = request.getContextPath();

		if (session == null || !"admin".equals(session.getAttribute("role"))) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		CustomerDAO customerDAO = new CustomerDAO();
		LoanDAO loanDAO = new LoanDAO();

//		Fetch customers
		List<Customer> customers = customerDAO.getActiveCustomers();
		request.setAttribute("customers", customers);

		String customerIdStr = request.getParameter("customer_id");

		if (customerIdStr != null && !customerIdStr.isEmpty()) {
			int customerId = Integer.parseInt(customerIdStr);

			List<Loan> loans = loanDAO.getLoansByCustomer(customerId);

			List<Loan> activeLoans = new ArrayList<>();
			List<Loan> closedLoans = new ArrayList<>();
			List<Loan> overdueLoans = new ArrayList<>();
			List<Loan> rejectedLoans = new ArrayList<>();

			double totalDisbursed = 0;
			double totalBalance = 0;

			Date today = new Date();

			for (Loan loan : loans) {

				totalDisbursed += loan.getAmount();
				totalBalance += loan.getBalance();

				String status = loan.getStatus();

				if ("Rejected".equalsIgnoreCase(status)) {

					rejectedLoans.add(loan);

				} else if ("Closed".equalsIgnoreCase(status)) {

					closedLoans.add(loan);

				} else if ("Approved".equalsIgnoreCase(status)) {

					if (loan.getBalance() <= 0) {
						closedLoans.add(loan);

					} else if (loan.getDueDate() != null && loan.getDueDate().before(today)) {
						overdueLoans.add(loan);

					} else {
						activeLoans.add(loan);
					}
				}
			}

			double totalRepayments = totalDisbursed - totalBalance;

//				Customer Info
			for (Customer c : customers) {
				if (c.getCustomerId() == customerId) {
					request.setAttribute("custName", c.getName());
					request.setAttribute("custEmail", c.getEmail());
					request.setAttribute("custPhone", c.getPhone());
				}
			}

			request.setAttribute("activeLoans", activeLoans);
			request.setAttribute("closedLoans", closedLoans);
			request.setAttribute("overdueLoans", overdueLoans);
			request.setAttribute("rejectedLoans", rejectedLoans);

			request.setAttribute("totalDisbursed", totalDisbursed);
			request.setAttribute("totalBalance", totalBalance);
			request.setAttribute("totalRepayments", totalRepayments);
		}
		request.getRequestDispatcher("/views/admin/customerLoanReport.jsp").forward(request, response);
	}
}