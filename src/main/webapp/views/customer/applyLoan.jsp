<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.LoanType"%>
<%@ page import="util.FormatUtil"%>

<%
String base = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<title>Apply Loan</title>
<link rel="stylesheet" href="<%=base%>/css/style.css">
</head>
<body>
	<jsp:include page="/views/common/navbar.jsp" />
	<div class="main">
		<%
		String success = request.getParameter("success");
		String error = request.getParameter("error");

		if (success != null) {
		%>
		<div class="success">
			🎉
			<%=success%></div>
		<%
		} else if (error != null) {
		%>
		<div class="error">
			❌
			<%=error%></div>
		<%
		}
		%>
		<h2>💳 Apply for Loan</h2>
		<%
		List<LoanType> loanTypes = (List<LoanType>) request.getAttribute("loanTypes");
		%>
		<div class="form-card">
			<form action="<%=base%>/ApplyLoanServlet" class="single"
				method="post"
				onsubmit="return validateLoan() && confirmSubmission()">

				<label>Loan Type</label> <select name="loanTypeId" id="loanType"
					onchange="showLoanDetails(); updatePurpose()">
					<option value="">--Select--</option>

					<%
					if (loanTypes != null) {
						for (LoanType l : loanTypes) {
					%>

					<option value="<%=l.getLoanTypeId()%>"
						data-type="<%=l.getLoanType()%>"
						data-rate="<%=l.getInterestRate()%>"
						data-maxAmount="<%=FormatUtil.currency(l.getMaxAmount())%>"
						data-maxDuration="<%=l.getMaxDuration()%>">
						<%=l.getLoanType()%>
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

				<label>Amount</label> <input type="number" name="amount"
					placeholder="Enter amount" required> <label>Duration</label>
				<input type="number" name="duration"
					placeholder="Enter tenure in months" required> <label>Purpose</label>
				<select name="purpose" id="purpose" required>
					<option value="">--Select--</option>
				</select>

				<button type="submit" class="btn">Submit Application</button>
			</form>
		</div>
	</div>
	<jsp:include page="/views/common/footer.jsp" />
	<script src="<%=base%>/scripts/applyLoan.js"></script>
</body>
</html>