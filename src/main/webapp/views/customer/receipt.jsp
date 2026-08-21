<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="model.Payment, java.text.SimpleDateFormat, util.FormatUtil"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Receipt</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<jsp:include page="/views/common/navbar.jsp" />
	<%
	Payment payment = (Payment) request.getAttribute("payment");
	String formattedDate = "";
	String formattedAmount = "";

	if (payment != null) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		formattedDate = sdf.format(payment.getPaymentDate());
		formattedAmount = FormatUtil.currency(payment.getAmountPaid());
	}
	%>
	<div class="main">
		<%
		if (payment == null) {
		%>
		<div class="error-box">❌ Payment details not found</div>
		<%
		} else {
		%>
		<h2>🧾 Payment Receipt</h2>
		<div class="receipt-area">
			<div class="form-card">

				<!-- Header -->
				<div style="display: flex; align-items: center; gap: 15px;">
					<img src="<%=request.getContextPath()%>/images/logo.png" width="50">
					<div>
						<h3>FinTrust</h3>
						<p>Smart Loan Portal</p>
						<p>Dehradun, India</p>
					</div>
				</div>
				<br>
				<div class="success-box">✔ Payment Completed Successfully</div>

				<!-- Receipt Details -->
				<div class="info-box">
					<p>
						<strong>Transaction ID:</strong> TXN<%=payment.getPaymentId()%></p>
					<p>
						<strong>Payment ID:</strong>
						<%=payment.getPaymentId()%></p>
					<p>
						<strong>Loan ID:</strong>
						<%=payment.getLoanId()%></p>
					<p>
						<strong>Customer:</strong>
						<%=payment.getCustomerName()%></p>
					<p>
						<strong>Loan Type:</strong>
						<%=payment.getLoanType()%></p>
					<p>
						<strong>Payment Date:</strong>
						<%=formattedDate%></p>
				</div>
				<!-- Amount Highlight -->
				<div class="result-box">
					💰 Amount Paid: <strong><%=formattedAmount%></strong>
				</div>
				<br>
				<p style="text-align: center;">Thank you for your payment!</p>

				<!-- Actions -->
				<div class="form-actions">
					<button class="btn no-print" onclick="window.print()">🖨 Print /
						Save PDF</button>
					<a
						href="<%=request.getContextPath()%>/RepaymentControllerServlet?action=list"
						class="btn btn-blue"> ⬅ Back to Loans </a>
				</div>
			</div>
		</div>
		<%
		}
		%>
	</div>
	<jsp:include page="/views/common/footer.jsp" />
</body>
</html>