<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*, model.Payment, util.FormatUtil"%>

<!DOCTYPE html>
<html>
<head>
<title>Payment History</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<jsp:include page="/views/common/navbar.jsp" />
	<div class="main">
		<h2>💳 Payment History</h2>
		<p class="table-subtext">View all your EMI payments and download
			receipts.</p>
		<%
		List<Payment> payments = (List<Payment>) request.getAttribute("payments");

		if (payments != null && !payments.isEmpty()) {
			boolean isFirst = true;
		%>
		<div class="table-container">
			<table>
				<thead>
					<tr>
						<th>Payment ID</th>
						<th>Loan ID</th>
						<th>Loan Type</th>
						<th>Date</th>
						<th>Amount</th>
						<th>Receipt</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Payment p : payments) {
					%>
					<tr class="<%=isFirst ? "highlight-row" : ""%>">
						<td><%=p.getPaymentId()%></td>
						<td><%=p.getLoanId()%></td>
						<td><%=p.getLoanType()%></td>
						<td><%=p.getPaymentDate()%></td>
						<td><%=FormatUtil.currency(p.getAmountPaid())%></td>
						<td><a
							href="<%=request.getContextPath()%>/ReceiptServlet?id=<%=p.getPaymentId()%>"
							class="btn btn-green"> View</a></td>
					</tr>
					<%
					isFirst = false;
					}
					%>
				</tbody>
			</table>
		</div>
		<%
		} else {
		%>
		<div class="empty-box">📭 No payment history available.</div>
		<%
		}
		%>
	</div>
	<jsp:include page="/views/common/footer.jsp" />
</body>
</html>