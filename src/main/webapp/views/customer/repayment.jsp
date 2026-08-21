<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*, model.Loan, util.FormatUtil"%>

<!DOCTYPE html>
<html>
<head>
<title>Loan Repayment</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<jsp:include page="/views/common/navbar.jsp" />
	<div class="main">
		<h2>💳 Loan Repayment</h2>
		<p class="table-subtext">Securely pay your EMI</p>
		<%
		List<Loan> loans = (List<Loan>) request.getAttribute("loans");

		if (loans != null && !loans.isEmpty()) {
		%>
		<div class="table-container">

			<table>
				<thead>
					<tr>
						<th>Loan ID</th>
						<th>Loan Type</th>
						<th>Amount</th>
						<th>Balance</th>
						<th>EMI</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Loan l : loans) {
					%>

					<tr>
						<td><%=l.getLoanId()%></td>
						<td><%=l.getLoanType()%></td>
						<td><%=FormatUtil.currency(l.getAmount())%></td>
						<td><%=FormatUtil.currency(l.getBalance())%></td>
						<td><%=FormatUtil.currency(l.getEmi())%></td>

						<td><a
							href="<%=request.getContextPath()%>/RepaymentControllerServlet?action=pay&loanId=<%=l.getLoanId()%>"
							class="btn btn-green"> 💰 Pay </a> <a
							href="<%=request.getContextPath()%>/EmiScheduleServlet?loanId=<%=l.getLoanId()%>"
							class="btn btn-blue"> 📊 Schedule </a></td>
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
		<div class="empty-box">📭 No active loans available.</div>
		<%
		}
		%>
	</div>
	<jsp:include page="/views/common/footer.jsp" />
</body>
</html>