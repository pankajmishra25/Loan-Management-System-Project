<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.DashboardStats"%>
<%@ page import="util.FormatUtil"%>

<%
DashboardStats stats = (DashboardStats) request.getAttribute("stats");
if (stats == null) {
	stats = new DashboardStats();
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Dashboard</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<jsp:include page="/views/common/navbar.jsp" />
	<div class="main">
		<%
		if (stats != null && stats.getOverdue() > 0) {
		%>
		<div class="alert">
			⚠
			<%=stats.getOverdue()%>
			Overdue Loan(s) found!
		</div>
		<%
		}
		%>
		<h2>📊 Customer Dashboard</h2>
		<div class="card-container">
			<div class="card blue">
				<h3><%=stats.getTotalLoans()%></h3>
				<p>Total Loans</p>
			</div>

			<div class="card green">
				<h3><%=stats.getApproved()%></h3>
				<p>Approved Loans</p>
			</div>

			<div class="card red">
				<h3><%=stats.getRejected()%></h3>
				<p>Rejected Loans</p>
			</div>

			<div class="card yellow">
				<h3><%=stats.getPending()%></h3>
				<p>Pending Loans</p>
			</div>

			<div class="card blue">
				<h3><%=stats.getClosed()%></h3>
				<p>Closed Loans</p>
			</div>

			<div class="card green">
				<h3><%=FormatUtil.currency(stats.getTotalPaid())%></h3>
				<p>Total Paid</p>
			</div>

			<div class="card red">
				<h3><%=FormatUtil.currency(stats.getBalance())%></h3>
				<p>Remaining Balance</p>
			</div>
		</div>
	</div>
	<jsp:include page="/views/common/footer.jsp" />
</body>
</html>