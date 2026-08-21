package extra;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.CustomerDAO;

@WebServlet("/CustomerChangePasswordServlet")
public class CustomerChangePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String base = request.getContextPath();

		if (session.getAttribute("customer") == null) {
			response.sendRedirect(base + "/views/auth/login.jsp");
			return;
		}

		String email = (String) session.getAttribute("customer");

		String oldPass = request.getParameter("oldPassword");
		String newPass = request.getParameter("newPassword");
		String confirmPass = request.getParameter("confirmPassword");

		String error = "";

		CustomerDAO dao = new CustomerDAO();

		// 🔴 Validate old password
		if (!dao.checkPassword(email, oldPass)) {
			error = "Old password is incorrect";
		}

		// 🔴 Validate new password length
		else if (newPass.length() < 6) {
			error = "Password must be at least 6 characters";
		}

		// 🔴 Confirm password match
		else if (!newPass.equals(confirmPass)) {
			error = "Passwords do not match";
		}

		if (!error.equals("")) {
//			request.setAttribute("msg", "❌ " + error);
			response.sendRedirect(base + "/views/customer/customerChangePassword.jsp?msg=error");
			return;
		}

		// ✅ Update password
		boolean updated = dao.changePassword(email, newPass);

		if (updated) {
			session.invalidate();
			response.sendRedirect(base + "/views/auth/login.jsp?msg=success");
		} else {
			response.sendRedirect(base + "/views/customer/customerChangePassword.jsp?msg=failed");
		}
//		if (updated) {
//			request.setAttribute("msg", "✅ Password changed successfully!");
//		} else {
//			request.setAttribute("msg", "❌ Failed to update password");
//		}

//		request.getRequestDispatcher("login.jsp").forward(request, response);
//		response.sendRedirect("login.jsp?msg=success");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Forward to login page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/customer/customerChangePassword.jsp");
		dispatcher.forward(request, response);
	}
}