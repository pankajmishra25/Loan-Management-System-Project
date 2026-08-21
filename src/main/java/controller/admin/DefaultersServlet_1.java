package controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.LoanDAO;
import model.Defaulter;

@WebServlet("/DefaultersServlet")
public class DefaultersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Admin check
		HttpSession session = request.getSession(false);

		String base = request.getContextPath();

		if (session == null || !"admin".equals(session.getAttribute("role"))) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		LoanDAO dao = new LoanDAO();

		List<Defaulter> list = dao.getDefaulters();

		request.setAttribute("defaulters", list);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/defaulters.jsp");
		rd.forward(request, response);
	}
}