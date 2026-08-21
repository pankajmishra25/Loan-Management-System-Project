package filter;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

@WebFilter("/*")
public class AuthFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String uri = request.getRequestURI();
		String context = request.getContextPath();

		HttpSession session = request.getSession(false);

		// ===============================
		// 1. Allow Static Resources
		// ===============================
		if (uri.contains("/css/") || uri.contains("/js/") || uri.contains("/images/") || uri.contains("/scripts/")) {

			chain.doFilter(req, res);
			return;
		}

		// ===============================
		// 2. Public Pages
		// ===============================
		if (uri.endsWith("index.jsp") || uri.endsWith("login.jsp") || uri.endsWith("register.jsp")
				|| uri.contains("LoginServlet") || uri.contains("RegisterServlet") || uri.contains("HomeServlet")
				|| uri.contains("AboutServlet") || uri.contains("ContactServlet")
				|| uri.contains("EmiCalculatorServlet") || uri.contains("InterestRatesServlet")
				|| uri.contains("LogoutServlet")) {

			chain.doFilter(req, res);
			return;
		}

		// ===============================
		// 3. Session Info
		// ===============================
		String role = null;

		if (session != null) {
			role = (String) session.getAttribute("role");
		}

		// ===============================
		// 4. Admin Protected Pages
		// ===============================
		if (uri.contains("Admin") || uri.contains("CustomerListServlet") || uri.contains("CustomerLoanReportServlet")
				|| uri.contains("DefaultersServlet") || uri.contains("AllLoansServlet")) {

			if (!"admin".equals(role)) {
				response.sendRedirect(context + "/LoginServlet");
				return;
			}
		}

		// ===============================
		// 5. Customer Protected Pages
		// ===============================
		if (uri.contains("CustomerDashboardServlet") || uri.contains("ApplyLoanServlet")
				|| uri.contains("CustomerLoansServlet") || uri.contains("PaymentHistoryServlet")
				|| uri.contains("RepaymentControllerServlet") || uri.contains("ProfileServlet")) {

			if (!"customer".equals(role)) {
				response.sendRedirect(context + "/LoginServlet");
				return;
			}
		}

		// ===============================
		// 6. Allow Request
		// ===============================
		chain.doFilter(req, res);
	}
}