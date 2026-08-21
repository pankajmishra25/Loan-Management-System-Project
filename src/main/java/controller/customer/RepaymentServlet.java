package controller.customer;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.LoanDAO;

@WebServlet("/RepaymentServlet")
public class RepaymentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String loanIdStr = request.getParameter("loanId");

        if (loanIdStr == null) {
            response.sendRedirect(request.getContextPath() + "/RepaymentControllerServlet?action=list");
            return;
        }

        int loanId = Integer.parseInt(loanIdStr);
        double amount = Double.parseDouble(request.getParameter("amount"));

        LoanDAO dao = new LoanDAO();
        int paymentId = dao.makePayment(loanId, amount);

        if (paymentId > 0) {
            response.sendRedirect(request.getContextPath()
                + "/ReceiptServlet?id=" + paymentId);
        } else {
            response.sendRedirect(request.getContextPath()
                + "/RepaymentControllerServlet?action=pay&loanId=" + loanId + "&msg=failed");
        }
    }
}