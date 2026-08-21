package extra;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import util.DBConnection;

@WebServlet("/DeactivateCustomerServlet")
public class DeactivateCustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session.getAttribute("admin") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));

		try {
			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement("UPDATE customer SET status='Inactive' WHERE id=?");
			ps.setInt(1, id);

			ps.executeUpdate();

			response.sendRedirect("customers.jsp?msg=deactivated");

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("customers.jsp?msg=error");
		}
	}
}