<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Loan"%>
<%@ page import="util.FormatUtil"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
String today = sdf.format(new Date());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Make Payment</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<jsp:include page="/views/common/navbar.jsp" />
	<div class="main">
		<h2>💳 Complete Payment</h2>
		<!-- Messages -->
		<%
		String msg = (String) request.getAttribute("msg");

		if ("success".equals(msg)) {
		%>
		<div class="success">✔ Payment successful!</div>
		<%
		} else if ("failed".equals(msg)) {
		%>
		<div class="error">❌ Payment failed</div>
		<%
		}
		%>
		<%
		Loan loan = (Loan) request.getAttribute("loan");

		if (loan != null) {
		%>
		<div class="card">
			<p>
				<strong>Loan ID:</strong>
				<%=loan.getLoanId()%></p>
			<p>
				<strong>Loan Type:</strong>
				<%=loan.getLoanType()%></p>
			<p>
				<strong>Payment Date:</strong>
				<%=today%></p>
			<p>
				<strong>Amount:</strong>
				<%=FormatUtil.currency(loan.getEmi())%></p>
		</div>
		<%
		if (loan.getBalance() <= 0) {
		%>
		<div class="success">🎉 Loan already paid</div>
		<%
		} else {
		%>
		<div class="form-card">
			<form action="<%=request.getContextPath()%>/RepaymentServlet"
				method="post" onsubmit="return confirmPayment()">

				<input type="hidden" name="loanId" value="<%=loan.getLoanId()%>">
				<input type="hidden" name="amount" value="<%=loan.getEmi()%>">
				<input type="hidden" id="amount" value="<%=loan.getEmi()%>">

				<button type="submit" class="btn" id="payBtn">💳 Confirm
					Payment</button>

			</form>
		</div>
		<%
		}
		%>
		<%
		} else {
		%>
		<div class="error">❌ Loan details not found</div>
		<%
		}
		%>
	</div>
	<jsp:include page="/views/common/footer.jsp" />
	<script src="<%=request.getContextPath()%>/scripts/payment.js"></script>
</body>
</html>