<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Loan"%>
<%@ page import="util.FormatUtil"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Approved Loans</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

	<jsp:include page="/views/common/navbar.jsp" />

	<div class="main">

		<h2>✅ Approved Loans Overview</h2>

		<%
		List<Loan> loans = (List<Loan>) request.getAttribute("loans");

		if (loans != null && !loans.isEmpty()) {
		%>

		<div class="table-container">
		<table>

			<thead>
				<tr>
					<th>ID</th>
					<th>Customer ID</th>
					<th>Name</th>
					<th>Type</th>
					<th>Amount</th>
					<th>Purpose</th>
					<th>EMI</th>
					<th>Balance</th>
					<th>Status</th>
				</tr>
			</thead>

			<tbody>

				<%
				for (Loan loan : loans) {
					String status = loan.getStatus();
				%>

				<tr>
					<td><%=loan.getLoanId()%></td>
					<td><%=loan.getCustomerId()%></td>
					<td><%=loan.getCustomerName()%></td>
					<td><%=loan.getLoanType()%></td>
					<td>₹<%=FormatUtil.formatIndianNumber(Math.round(loan.getAmount()))%></td>
					<td><%=loan.getPurpose()%></td>
					<td>₹<%=FormatUtil.formatIndianNumber(Math.round(loan.getEmi()))%></td>
					<td>₹<%=FormatUtil.formatIndianNumber(Math.round(loan.getBalance()))%></td>
					<td><span class="badge ok"><%=status%></span></td>
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

		<div class="empty">⚠ No approved loans available.</div>

		<%
		}
		%>

	</div>
</body>
</html>