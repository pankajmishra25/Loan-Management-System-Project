<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.DashboardStats"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<jsp:include page="/views/common/navbar.jsp" />
	<div class="main">
		<%
		DashboardStats stats = (DashboardStats) request.getAttribute("stats");

		if (stats != null && stats.getDefaulters() > 0) {
		%>
		<div class="alert">
			⚠
			<%=stats.getDefaulters()%>
			Defaulter(s) found!
		</div>
		<%
		}
		%>

		<%
		if (stats != null) {
		%>

		<h2>📊 Admin Dashboard</h2>

		<div class="card-container">

			<div class="card blue">
				<h3><%=stats.getTotalLoans()%></h3>
				<p>Total Loans</p>
			</div>

			<div class="card orange">
				<h3><%=stats.getOverdue()%></h3>
				<p>Overdue Loans</p>
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

			<div class="card red">
				<h3><%=stats.getDefaulters()%></h3>
				<p>Defaulters</p>
			</div>

		</div>
		<%
		}
		%>
	</div>
</body>
</html>