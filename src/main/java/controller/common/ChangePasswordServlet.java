package controller.common;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.AuthDAO;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String base = request.getContextPath();

		// ❌ No session
		if (session == null || session.getAttribute("role") == null) {
			response.sendRedirect(base + "/LoginServlet");
			return;
		}

		String role = (String) session.getAttribute("role");

		String oldPass = request.getParameter("oldPassword");
		String newPass = request.getParameter("newPassword");
		String confirmPass = request.getParameter("confirmPassword");

		String error = "";
		boolean updated = false;

		// 🔹 Get username/email based on role
		String usernameOrEmail = null;

		if ("admin".equals(role)) {
			usernameOrEmail = (String) session.getAttribute("admin");
		} else {
			usernameOrEmail = (String) session.getAttribute("customer");
		}

		// 🔹 Use AuthDAO
		AuthDAO dao = new dao.AuthDAO();

		// =============================
		// 🔴 VALIDATIONS (CORRECT ORDER)
		// =============================

		if (newPass.length() < 6) {
			error = "Password must be at least 6 characters";
		} else if (!newPass.equals(confirmPass)) {
			error = "Passwords do not match";
		} else if (!dao.checkPassword(usernameOrEmail, oldPass, role)) {
			error = "Old password is incorrect";
		}

		// =============================
		// ✅ UPDATE PASSWORD
		// =============================

		if (error.equals("")) {
			updated = dao.changePassword(usernameOrEmail, newPass, role);
		}

		// =============================
		// 🔁 RESPONSE HANDLING
		// =============================

		if (!error.equals("")) {
			request.setAttribute("msg", "❌ " + error);
			request.getRequestDispatcher("/views/common/changePassword.jsp").forward(request, response);
			return;
		}

		if (updated) {
			session.invalidate(); // logout after password change
			response.sendRedirect(base + "/LoginServlet?msg=success");
		} else {
			response.sendRedirect(base + "/ChangePasswordServlet?msg=failed");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("role") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}

		request.getRequestDispatcher("/views/common/changePassword.jsp").forward(request, response);
	}
}