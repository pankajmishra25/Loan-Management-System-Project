<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Customer"%>
<%@ page import="model.Loan"%>
<%@ page import="util.FormatUtil"%>

<!DOCTYPE html>
<html>
<head>
<title>Customer Loan Report</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

	<jsp:include page="/views/common/navbar.jsp" />

	<div class="main">

		<h2>📊 Customer Loan Report</h2>

		<!-- Filter -->
		<div class="form-card inline-form">
			<form method="get"
				action="<%=request.getContextPath()%>/CustomerLoanReportServlet"
				class="inline">

				<label><strong>Select Customer:</strong></label> <select
					name="customer_id" onchange="this.form.submit()">
					<option value="">-- Select Customer --</option>

					<%
					String selected = request.getParameter("customer_id");
					List<Customer> customers = (List<Customer>) request.getAttribute("customers");

					if (customers != null) {
						for (Customer c : customers) {
					%>

					<option value="<%=c.getCustomerId()%>"
						<%=String.valueOf(c.getCustomerId()).equals(selected) ? "selected" : ""%>>
						<%=c.getName()%>
					</option>

					<%
					}
					}
					%>
				</select>

			</form>
		</div>

		<%
		if (request.getParameter("customer_id") != null && request.getAttribute("activeLoans") != null) {
		%>

		<!-- Summary -->
		<div class="grid cards">

			<div class="card">
				<h3>Customer</h3>
				<p>
					<strong><%=request.getAttribute("custName")%></strong>
				</p>
				<p><%=request.getAttribute("custEmail")%></p>
				<p><%=request.getAttribute("custPhone")%></p>
			</div>

			<%
			Double disbursed = (Double) request.getAttribute("totalDisbursed");
			Double repayments = (Double) request.getAttribute("totalRepayments");
			Double balance = (Double) request.getAttribute("totalBalance");
			%>

			<div class="card">
				<h3>Summary</h3>
				<p>
					Disbursed: ₹<%=FormatUtil.formatIndianNumber(disbursed != null ? disbursed : 0)%></p>
				<p>
					Repayments: ₹<%=FormatUtil.formatIndianNumber(repayments != null ? repayments : 0)%></p>
				<p>
					Balance: ₹<%=FormatUtil.formatIndianNumber(balance != null ? balance : 0)%></p>
			</div>

		</div>

		<!-- Loan Sections -->
		<h3 class="section-title">🟢 Active Loans</h3>
		<jsp:include page="loanTable.jsp">
			<jsp:param name="type" value="activeLoans" />
		</jsp:include>

		<h3 class="section-title">🟠 Overdue Loans</h3>
		<jsp:include page="loanTable.jsp">
			<jsp:param name="type" value="overdueLoans" />
		</jsp:include>

		<h3 class="section-title">⚪ Closed Loans</h3>
		<jsp:include page="loanTable.jsp">
			<jsp:param name="type" value="closedLoans" />
		</jsp:include>

		<h3 class="section-title">🔴 Rejected Loans</h3>
		<jsp:include page="loanTable.jsp">
			<jsp:param name="type" value="rejectedLoans" />
		</jsp:include>

		<%
		}
		%>
	</div>
</body>
</html>