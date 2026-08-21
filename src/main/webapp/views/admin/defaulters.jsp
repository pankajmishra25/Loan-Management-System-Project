<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Defaulter"%>
<%@ page import="java.util.*"%>
<%@ page import="util.FormatUtil"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Defaulters</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

	<jsp:include page="/views/common/navbar.jsp" />

	<div class="main">

		<h2>⚠ Defaulters List</h2>

		<div class="alert">⚠ These customers have overdue payments.
			Immediate attention required.</div>

		<%
		List<Defaulter> list = (List<Defaulter>) request.getAttribute("defaulters");

		if (list != null && !list.isEmpty()) {
		%>

		<div class="table-container">
		<table>
			<thead>
				<tr>
					<th>Loan ID</th>
					<th>Customer Name</th>
					<th>Loan Type</th>
					<th>Amount</th>
					<th>Due Date</th>
					<th>Balance</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Defaulter d : list) {
				%>
				<tr>
					<td><%=d.getLoanId()%></td>
					<td><%=d.getCustomerName()%></td>
					<td><%=d.getLoanType()%></td>
					<td>₹<%=FormatUtil.formatIndianNumber(d.getAmount())%></td>
					<td><%=d.getDueDate()%></td>
					<td>₹<%=FormatUtil.formatIndianNumber(d.getBalance())%></td>
				</tr>

				<%
				}
				%>
			</tbody>
		</table>
		</div>
		<%
		} else {
		%>
		<div class="empty">✅ No defaulters found</div>
		<%
		}
		%>
	</div>
</body>
</html>