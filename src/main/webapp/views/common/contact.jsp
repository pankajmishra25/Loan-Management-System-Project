<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Us</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

	<jsp:include page="navbar.jsp" />

	<div class="main">

		<h2>📞 Contact Us</h2>

		<div class="contact-container">

			<!-- Contact Info -->
			<div class="card">
				<h3>Contact Details</h3>
				<p>
					<strong>Email:</strong> support@fintrust.com
				</p>
				<p>
					<strong>Phone:</strong> +91 99999 88888
				</p>
				<p>
					<strong>Location:</strong> Dehradun, Uttarakhand, India
				</p>
			</div>

			<!-- Form -->
			<div class="form-card">

				<h3>Send Message</h3>

				<form action="<%=request.getContextPath()%>/ContactServlet" method="post" class="form-grid">

					<label>Name</label> <input type="text" name="name" required
						class="full"> <label>Email</label> <input type="email"
						name="email" required class="full"> <label>Message</label>
					<textarea name="message" rows="4" required class="full"></textarea>

					<button type="submit" class="btn">Send Message</button>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>