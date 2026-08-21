<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*, model.EmiSchedule"%>
<%@ page import="util.FormatUtil"%>

<!DOCTYPE html>
<html>
<head>
<title>EMI Schedule</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<jsp:include page="/views/common/navbar.jsp" />
	<div class="main">
		<h2>📊 EMI Schedule</h2>
		<%
		List<EmiSchedule> list = (List<EmiSchedule>) request.getAttribute("schedule");

		if (list != null && !list.isEmpty()) {

			double totalInterest = 0;
			double totalPayment = 0;

			for (EmiSchedule e : list) {
				totalInterest += e.getInterest();
				totalPayment += e.getEmi();
			}
		%>
		<div class="card">
			<p>
				<strong>Total Paid:</strong>
				<%=FormatUtil.currency(totalPayment)%></p>
			<p>
				<strong>Total Interest:</strong>
				<%=FormatUtil.currency(totalInterest)%></p>
		</div>
		<div class="table-container">
			<table>
				<thead>
					<tr>
						<th>Month</th>
						<th>EMI</th>
						<th>Interest</th>
						<th>Principal</th>
						<th>Balance</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (EmiSchedule e : list) {
					%>

					<tr>
						<td><%=e.getMonth()%></td>
						<td><%=FormatUtil.currency(e.getEmi())%></td>
						<td><%=FormatUtil.currency(e.getInterest())%></td>
						<td><%=FormatUtil.currency(e.getPrincipal())%></td>
						<td><%=FormatUtil.currency(e.getBalance())%></td>
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

		<div class="empty">📭 No EMI schedule available.</div>

		<%
		}
		%>
	</div>
	<jsp:include page="/views/common/footer.jsp" />
</body>
</html>