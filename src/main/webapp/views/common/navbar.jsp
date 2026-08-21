<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>

<%
String role = (String) session.getAttribute("role");
String admin = (String) session.getAttribute("admin");
String customerName = (String) session.getAttribute("customerName");
String base = request.getContextPath();
%>

<nav class="nav">

	<div class="nav-left">
		<img src="<%=base%>/images/logo.png" alt="Logo">
		<div class="brand-box">
			<span class="brand"><strong>Fintrust</strong></span> <span
				class="tagline"><strong>Smart Loan Portal</strong></span>
		</div>
	</div>

	<div class="nav-right">
		<%
		if ("admin".equals(role)) {
		%>
		<span>Welcome, <%=admin%></span> <a
			href="<%=base%>/ChangePasswordServlet" class="btn">Change
			Password</a> <a href="<%=base%>/LogoutServlet" class="btn">Logout</a>
		<%
		} else if ("customer".equals(role)) {
		%>
		<span>Welcome, <%=customerName%></span> <a
			href="<%=base%>/ProfileServlet" class="btn">Profile</a> <a
			href="<%=base%>/LogoutServlet" class="btn">Logout</a>
		<%
		} else {
		%>
		<a href="<%=base%>/LoginServlet" class="btn">Login</a> <a
			href="<%=base%>/RegisterServlet" class="btn">Register</a>
		<%
		}
		%>
	</div>
</nav>

<div class="menu">
	<%
	if ("admin".equals(role)) {
	%>
	<a href="<%=base%>/AdminDashboardServlet">Dashboard</a> <a
		href="<%=base%>/AdminLoansServlet">Pending</a> <a
		href="<%=base%>/AllLoansServlet">All Loans</a> <a
		href="<%=base%>/CustomerListServlet">Customers</a> <a
		href="<%=base%>/DefaultersServlet">Defaulters</a> <a
		href="<%=base%>/CustomerLoanReportServlet">Reports</a>
	<%
	} else if ("customer".equals(role)) {
	%>
	<a href="<%=base%>/CustomerDashboardServlet">Dashboard</a> <a
		href="<%=base%>/EmiCalculatorServlet">EMI</a> <a
		href="<%=base%>/ApplyLoanServlet">Apply</a> <a
		href="<%=base%>/CustomerLoansServlet">Loans</a> <a
		href="<%=base%>/RepaymentControllerServlet?action=list">Pay EMI</a> <a
		href="<%=base%>/PaymentHistoryServlet">Payments</a> <a
		href="<%=base%>/ContactServlet">Contact</a>
	<%
	} else {
	%>
	<a href="<%=base%>/HomeServlet">Home</a> <a
		href="<%=base%>/AboutServlet">About</a> <a
		href="<%=base%>/ContactServlet">Contact</a>
	<%
	}
	%>
</div>

<script src="<%=base%>/scripts/navbar.js"></script>