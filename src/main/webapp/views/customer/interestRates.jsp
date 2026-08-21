<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.LoanType"%>
<%@ page import="util.FormatUtil"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Interest Rates</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<jsp:include page="/views/common/navbar.jsp" />
	<div class="main">
		<h2>📊 Loan Interest Rates</h2>
		<%
		List<LoanType> list = (List<LoanType>) request.getAttribute("loanTypes");

		if (list != null && !list.isEmpty()) {
		%>
		<div class="table-container">
			<table>
				<thead>
					<tr>
						<th>Type</th>
						<th>Rate (%)</th>
						<th>Max Amount</th>
						<th>Max Duration</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (LoanType lt : list) {
					%>
					<tr>
						<td><%=lt.getLoanType()%></td>
						<td><%=lt.getInterestRate()%>%</td>
						<td><%=FormatUtil.currency(lt.getMaxAmount())%></td>
						<td><%=lt.getMaxDuration()%></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
		<div class="card">
			<a href="<%=request.getContextPath()%>/ApplyLoanServlet" class="btn">
				Apply Now </a>
		</div>
		<%
		} else {
		%>
		<div class="empty">📭 No interest rates available</div>
		<%
		}
		%>
	</div>
	<jsp:include page="/views/common/footer.jsp" />
</body>
</html>