package controller.customer;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.LoanTypeDAO;
import model.LoanType;

@WebServlet("/InterestRatesServlet")
public class InterestRatesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LoanTypeDAO dao = new LoanTypeDAO();

		List<LoanType> list = dao.getAllLoanTypes();

		request.setAttribute("loanTypes", list);

		RequestDispatcher rd = request.getRequestDispatcher("/views/customer/interestRates.jsp");
		rd.forward(request, response);
	}
}