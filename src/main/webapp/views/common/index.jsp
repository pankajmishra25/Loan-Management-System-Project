<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String base = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="<%=base%>/css/style.css">
</head>

<body>
	<jsp:include page="navbar.jsp" />
	<div class="main">
		<!-- Hero Section -->
		<div class="card">
			<h1>Welcome to FinTrust</h1>
			<p>
				<strong>Your Smart & Secure Loan Management System</strong>
			</p>
			<p>Apply, track, and manage your loans with ease.</p>

			<br> <a href="<%=base%>/LoginServlet" class="btn">Login</a> <a
				href="<%=base%>/RegisterServlet" class="btn">Get Started</a>
		</div>
		<!-- Key Features -->
		<div class="grid cards">

			<div class="card">
				<h3>📄 Easy Loan Application</h3>
				<p>Apply for loans quickly with a simple and user-friendly
					process.</p>
			</div>

			<div class="card">
				<h3>📊 EMI Calculator</h3>
				<p>Calculate your monthly installments instantly with accurate
					results.</p>
			</div>
			<div class="card">
				<h3>💳 Payment Tracking</h3>
				<p>Track all your payments and view detailed transaction
					history.</p>
			</div>
			<div class="card">
				<h3>🛠 Admin Management</h3>
				<p>Admins can manage users, approve loans, and monitor
					defaulters.</p>
			</div>
		</div>
		<!-- Why Choose Us -->
		<div class="card">
			<h2>Why Choose FinTrust?</h2>
			<div>
				<p>✔ Fast and secure loan processing</p>
				<p>✔ Real-time loan tracking</p>
				<p>✔ Transparent and reliable system</p>
				<p>✔ Easy-to-use interface</p>
			</div>
		</div>
		<!-- About Section -->
		<div class="card">
			<h2>About FinTrust</h2>
			<p>FinTrust is a web-based loan management system developed to
				simplify the process of applying, managing, and repaying loans. It
				provides a centralized platform for both customers and
				administrators to handle loan-related operations efficiently and
				securely.</p>
		</div>
		<!-- Support Section -->
		<div class="card">
			<h2>Support</h2>
			<p>
				Email: support@fintrust.com<br> Phone: +91 99999 88888<br>
				Hours: Mon–Sat, 9:00 AM – 6:00 PM<br> Address: FinTrust HQ,
				Dehradun
			</p>
		</div>
	</div>
	<jsp:include page="/views/common/footer.jsp" />
</body>
</html>