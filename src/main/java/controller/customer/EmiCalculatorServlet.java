package controller.customer;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.LoanDAO;
import model.LoanType;

@WebServlet("/EmiCalculatorServlet")
public class EmiCalculatorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		HttpSession session = request.getSession();
//
//		String base = request.getContextPath();
//
//		if (session.getAttribute("customer") == null) {
//			response.sendRedirect(base + "/LoginServlet");
//			return;
//		}

		LoanDAO dao = new LoanDAO();

		// 🔹 Load dropdown
		List<LoanType> loanTypes = dao.getAllLoanTypes();
		request.setAttribute("loanTypes", loanTypes);

		String loanTypeIdStr = request.getParameter("loanTypeId");

		if (loanTypeIdStr != null && !loanTypeIdStr.isEmpty()) {

			int loanTypeId = Integer.parseInt(loanTypeIdStr);
			LoanType lt = dao.getLoanTypeById(loanTypeId);

			request.setAttribute("selectedLoan", lt);

			try {
				double P = Double.parseDouble(request.getParameter("amount"));
				int N = Integer.parseInt(request.getParameter("duration"));

				String error = null;

				if (P > lt.getMaxAmount()) {
					error = "Max allowed amount is ₹" + lt.getMaxAmount();
				} else if (N > lt.getMaxDuration()) {
					error = "Max duration is " + lt.getMaxDuration() + " months";
				}

				if (error != null) {
					request.setAttribute("error", error);
				} else {

					double R = lt.getInterestRate() / 12 / 100;

					double emi = (P * R * Math.pow(1 + R, N)) / (Math.pow(1 + R, N) - 1);

					request.setAttribute("emi", emi);
				}

			} catch (Exception e) {
				request.setAttribute("error", "Invalid input");
			}
		}

		request.getRequestDispatcher("/views/customer/emiCalculator.jsp").forward(request, response);
	}
}