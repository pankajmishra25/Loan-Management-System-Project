<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="model.Loan"%>
<%@page import="util.FormatUtil"%>

<%
if (session == null || session.getAttribute("role") == null || !"admin".equals(session.getAttribute("role"))) {
	response.sendRedirect(request.getContextPath() + "/views/auth/login.jsp");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pending Applications</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

	<jsp:include page="/views/common/navbar.jsp" />

	<div class="main">

		<%
		String msg = request.getParameter("msg");
		if ("updated".equals(msg)) {
		%>
		<div class="success">✔ Loan status updated successfully!</div>
		<%
		}
		%>

		<h2>🕒 Pending Loan Applications</h2>

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
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Loan loan : loans) {

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
					%>
					<tr>
						<td><%=loan.getLoanId()%></td>
						<td><%=loan.getCustomerId()%></td>
						<td><%=loan.getCustomerName()%></td>
						<td><%=loan.getLoanType()%></td>
						<td><%=FormatUtil.currency(loan.getAmount())%></td>
						<td><%=loan.getPurpose()%></td>
						<td><%=FormatUtil.currency(loan.getEmi())%></td>
						<td><span class="badge <%=badgeClass%>"><%=status%></span></td>

						<td><a
							href="<%=request.getContextPath()%>/ApproveLoanServlet?id=<%=loan.getLoanId()%>&action=approve"
							class="btn" onclick="return confirm('Approve this loan?')">✔</a>

							<a
							href="<%=request.getContextPath()%>/ApproveLoanServlet?id=<%=loan.getLoanId()%>&action=reject"
							class="btn" onclick="return confirm('Reject this loan?')">✖</a>
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

		<div class="empty-box">📭 No pending applications.</div>

		<%
		}
		%>
	</div>
</body>
</html>