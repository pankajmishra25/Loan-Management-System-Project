<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Loan"%>
<%@ page import="util.FormatUtil"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Loans</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>
	<jsp:include page="/views/common/navbar.jsp" />
	<div class="main">
		<h2>📄 My Loan History</h2>
		<%
		String success = request.getParameter("success");
		if (success != null) {
		%>
		<div class="success">
			🎉
			<%=success%></div>
		<%
		}
		%>
		<%
		List<Loan> loans = (List<Loan>) request.getAttribute("loans");

		if (loans != null && !loans.isEmpty()) {
		%>

		<div class="table-container">
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Type</th>
						<th>Amount</th>
						<th>EMI</th>
						<th>Balance</th>
						<th>Approved</th>
						<th>Due</th>
						<th>End</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Loan loan : loans) {
					%>
					<tr>
						<td><%=loan.getLoanId()%></td>
						<td><%=loan.getLoanType()%></td>
						<td><%=FormatUtil.currency(loan.getAmount())%></td>
						<td><%=FormatUtil.currency(loan.getEmi())%></td>
						<td><%=FormatUtil.currency(loan.getBalance())%></td>

						<td><%=loan.getApprovalDate() != null ? FormatUtil.date(loan.getApprovalDate()) : "-"%></td>

						<td><%=loan.getDueDate() != null ? FormatUtil.date(loan.getDueDate()) : "-"%></td>

						<td><%=loan.getEndDate() != null ? FormatUtil.date(loan.getEndDate()) : "-"%></td>

						<td>
							<%
							String status = loan.getStatus();
							String badgeClass = "badge-gray";

							if ("Approved".equals(status))
								badgeClass = "badge-green";
							else if ("Pending".equals(status))
								badgeClass = "badge-yellow";
							else if ("Rejected".equals(status))
								badgeClass = "badge-red";
							else if ("Closed".equals(status))
								badgeClass = "badge-blue";
							%> <span class="badge <%=badgeClass%>"><%=status%></span>
						</td>
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

		<div class="empty-box">📭 No loans available.</div>

		<%
		}
		%>
	</div>
	<jsp:include page="/views/common/footer.jsp" />
</body>
</html>