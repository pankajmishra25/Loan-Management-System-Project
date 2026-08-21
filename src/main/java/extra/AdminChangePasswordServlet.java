package extra;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.AdminDAO;

@WebServlet("/AdminChangePasswordServlet")
public class AdminChangePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String base = request.getContextPath();

		if (session.getAttribute("admin") == null) {
			response.sendRedirect(base + "/views/auth/login.jsp");
			return;
		}

		String username = (String) session.getAttribute("admin");

		String oldPass = request.getParameter("oldPassword");
		String newPass = request.getParameter("newPassword");
		String confirmPass = request.getParameter("confirmPassword");

		String error = "";

		AdminDAO dao = new AdminDAO();

		// 🔴 Old password check
		if (!dao.checkPassword(username, oldPass)) {
			error = "Old password is incorrect";
		}

		// 🔴 Length check
		else if (newPass.length() < 6) {
			error = "Password must be at least 6 characters";
		}

		// 🔴 Match check
		else if (!newPass.equals(confirmPass)) {
			error = "Passwords do not match";
		}

		if (!error.equals("")) {
			request.setAttribute("msg", "❌ " + error);
			request.getRequestDispatcher("/views/admin/adminChangePassword.jsp").forward(request, response);
			return;
		}

		// ✅ Update
		boolean updated = dao.changePassword(username, newPass);

		if (updated) {
			request.setAttribute("msg", "✅ Password changed successfully!");
		} else {
			request.setAttribute("msg", "❌ Update failed!");
		}

		request.getRequestDispatcher("/views/admin/adminChangePassword.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Forward to login page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/adminChangePassword.jsp");
		dispatcher.forward(request, response);
	}

}