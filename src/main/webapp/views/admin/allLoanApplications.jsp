<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Loan"%>
<%@ page import="util.FormatUtil"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Loan Applications</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

	<jsp:include page="/views/common/navbar.jsp" />

	<div class="main">

		<h2>📋 All Loan Applications</h2>

		<!-- Filter -->
		<form action="<%=request.getContextPath()%>/AllLoansServlet"
			method="get" class="inline">
			<label>Filter:</label> <select name="status"
				onchange="this.form.submit()">
				<option value=""
					<%=request.getAttribute("selectedStatus") == null ? "selected" : ""%>>All</option>
				<option value="Pending"
					<%="Pending".equals(request.getAttribute("selectedStatus")) ? "selected" : ""%>>
					Pending</option>
				<option value="Approved"
					<%="Approved".equals(request.getAttribute("selectedStatus")) ? "selected" : ""%>>Approved</option>
				<option value="Rejected"
					<%="Rejected".equals(request.getAttribute("selectedStatus")) ? "selected" : ""%>>Rejected</option>
				<option value="Closed"
					<%="Closed".equals(request.getAttribute("selectedStatus")) ? "selected" : ""%>>Closed</option>
			</select>
		</form>

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
						<th>Name</th>
						<th>Amount</th>
						<th>Purpose</th>
						<th>EMI</th>
						<th>Balance</th>
						<th>Approval</th>
						<th>Due</th>
						<th>End</th>
						<th>Status</th>
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
						<td><%=loan.getLoanType()%></td>
						<td><%=loan.getCustomerName()%></td>

						<td><%=FormatUtil.currency(loan.getAmount())%></td>
						<td><%=loan.getPurpose()%></td>
						<td><%=FormatUtil.currency(loan.getEmi())%></td>
						<td><%=FormatUtil.currency(loan.getBalance())%></td>

						<td><%=FormatUtil.date(loan.getApprovalDate())%></td>
						<td><%=FormatUtil.date(loan.getDueDate())%></td>
						<td><%=FormatUtil.date(loan.getEndDate())%></td>

						<td><span class="badge <%=badgeClass%>"><%=status%></span></td>
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

		<div class="empty-box">⚠ No loans found</div>

		<%
		}
		%>

	</div>
</body>
</html>