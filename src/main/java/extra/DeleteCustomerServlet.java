package extra;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import util.DBConnection;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session.getAttribute("admin") == null) {
		    response.sendRedirect("login.jsp");
		    return;
		}
		
		int customerId = Integer.parseInt(request.getParameter("id"));

		try {
			Connection con = DBConnection.getConnection();

			// Only delete customer → cascade will handle rest
			PreparedStatement ps = con.prepareStatement("DELETE FROM customer WHERE id=?");
			ps.setInt(1, customerId);

			int rows = ps.executeUpdate();

			if (rows > 0) {
				response.sendRedirect("customers.jsp?msg=deleted");
			} else {
				response.sendRedirect("customers.jsp?msg=error");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("customers.jsp?msg=error");
		}
	}
}