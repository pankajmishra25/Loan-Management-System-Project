package extra;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import util.DBConnection;

@WebServlet("/RestoreCustomerServlet")
public class RestoreCustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		try {
			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement("UPDATE customer SET status='Active' WHERE id=?");
			ps.setInt(1, id);

			ps.executeUpdate();

			response.sendRedirect("inactiveCustomers.jsp?msg=restored");

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("inactiveCustomers.jsp?msg=error");
		}
	}
}