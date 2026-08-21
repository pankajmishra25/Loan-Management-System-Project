package controller.admin;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.CustomerDAO;

@WebServlet("/ViewCustomerServlet")
public class ViewCustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession(false);
		String base = request.getContextPath();

		if (session == null || !"admin".equals(session.getAttribute("role"))) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		CustomerDAO dao = new CustomerDAO();
		request.setAttribute("customer", dao.getCustomerById(id));

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/viewCustomer.jsp");
		rd.forward(request, response);
	}
}
