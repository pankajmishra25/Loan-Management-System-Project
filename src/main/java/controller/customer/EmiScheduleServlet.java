package controller.customer;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.LoanDAO;
import model.EmiSchedule;

@WebServlet("/EmiScheduleServlet")
public class EmiScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String base = request.getContextPath();

		if (session.getAttribute("customer") == null) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		int loanId = Integer.parseInt(request.getParameter("loanId"));

		LoanDAO dao = new LoanDAO();
		List<EmiSchedule> schedule = dao.getEmiSchedule(loanId);

		request.setAttribute("schedule", schedule);

		request.getRequestDispatcher("/views/customer/emiSchedule.jsp").forward(request, response);
	}
}