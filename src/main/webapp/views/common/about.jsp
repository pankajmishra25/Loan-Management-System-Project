<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About Us</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

	<jsp:include page="navbar.jsp" />

	<div class="main">

		<h2>About FinTrust</h2>

		<div class="card">

			<p>
				<strong>FinTrust</strong> is a modern web-based Loan Management
				System designed to simplify loan processing, EMI calculation, and
				repayment tracking.
			</p>

			<p>Our platform allows customers to apply for loans easily, track
				payments, and manage financial commitments efficiently.
				Administrators can monitor, approve, and manage loan activities
				securely.</p>

			<p>The system ensures transparency, accuracy, and user-friendly
				interaction, making loan management faster and more reliable.</p>

			<h3>Key Features</h3>

			<div>
				<p>✔Easy Loan Application</p>
				<p>✔EMI Calculation</p>
				<p>✔Payment Tracking</p>
				<p>✔Document Upload</p>
				<p>✔Admin Dashboard</p>
			</div>
		</div>
	</div>
	<jsp:include page="/views/common/footer.jsp" />
</body>
</html>