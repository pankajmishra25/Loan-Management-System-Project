<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*, model.LoanType"%>
<%@ page import="util.FormatUtil"%>

<%
String base = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<title>EMI Calculator</title>
<link rel="stylesheet" href="<%=base%>/css/style.css">
</head>
<body>
	<jsp:include page="/views/common/navbar.jsp" />
	<div class="main">
		<h2>🧮 EMI Calculator</h2>
		<div class="form-container">
			<div class="form-card">
				<form class="single" method="get"
					action="<%=base%>/EmiCalculatorServlet">

					<label>Loan Type</label> <select name="loanTypeId" id="loanType"
						onchange="showLoanDetails()" required>
						<option value="">--Select--</option>

						<%
						List<LoanType> loanTypes = (List<LoanType>) request.getAttribute("loanTypes");
						String selected = request.getParameter("loanTypeId");

						if (loanTypes != null) {
							for (LoanType lt : loanTypes) {
						%>

						<option value="<%=lt.getLoanTypeId()%>"
							data-type="<%=lt.getLoanType()%>"
							data-rate="<%=lt.getInterestRate()%>"
							data-maxAmount="<%=FormatUtil.currency(lt.getMaxAmount())%>"
							data-maxDuration="<%=lt.getMaxDuration()%>">
							<%=lt.getLoanType()%>
						</option>

						<%
						}
						}
						%>
					</select>

					<div id="loanDetails" class="card" style="display: none">
						<p>
							Rate: <span id="rate"></span>%
						</p>
						<p>
							Max Amount: ₹<span id="maxAmount"></span>
						</p>
						<p>
							Duration: <span id="maxDuration"></span> months
						</p>
					</div>

					<label>Amount</label> <input type="number" name="amount" placeholder="Enter amount" required
						value="<%=request.getParameter("amount") != null ? request.getParameter("amount") : ""%>">

					<label>Duration (Months)</label> <input type="number"
						name="duration" placeholder="Enter tenure in months" required
						value="<%=request.getParameter("duration") != null ? request.getParameter("duration") : ""%>">

					<button type="submit" class="btn">Calculate EMI</button>
				</form>
			</div>
		</div>

		<%
		LoanType lt = (LoanType) request.getAttribute("selectedLoan");

		if (lt != null) {
		%>

		<div class="card">
			<p>
				<strong>Loan Type:</strong>
				<%=lt.getLoanType()%>
			</p>

			<p>
				<strong>Rate:</strong>
				<%=lt.getInterestRate()%>%
			</p>
			<p>
				<strong>Max Amount:</strong>
				<%=FormatUtil.currency((double) lt.getMaxAmount())%></p>
			<p>
				<strong>Max Duration:</strong>
				<%=lt.getMaxDuration()%>
				months
			</p>
		</div>

		<%
		}

		if (request.getAttribute("error") != null) {
		%>

		<div class="error">
			❌
			<%=request.getAttribute("error")%>
		</div>

		<%
		}

		if (request.getAttribute("emi") != null) {
		%>

		<div class="success">
			💰 EMI:
			<%=FormatUtil.currency((double) request.getAttribute("emi"))%>
		</div>

		<%
		}
		%>
	</div>
	<jsp:include page="/views/common/footer.jsp" />
	<script src="<%=base%>/scripts/applyLoan.js"></script>
</body>
</html>
