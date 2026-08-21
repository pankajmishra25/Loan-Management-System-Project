<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String base = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="<%=base%>/css/style.css">
</head>

<body>

	<div class="main">
		<%
		String success = request.getParameter("success");
		String error = request.getParameter("error");

		if (success != null) {
		%>
		<div class="success">
			✅
			<%=success%></div>
		<%
}

if (error != null) {
%>
		<div class="error">
			❌
			<%=error%></div>
		<%
}
%>
		<div class="form-card">

			<%
			String msg = request.getParameter("msg");
			if ("success".equals(msg)) {
			%>
			<div class="success">✅ Password updated. Please login again.</div>
			<%
			}
			%>

			<h2 class="form-title">🔐 Welcome Back</h2>
			<p class="form-subtitle">Login to your account</p>
			<form action="<%=base%>/LoginServlet" method="post"
				onsubmit="return validateLogin()" class="login-form">

				<label>Login As:</label>

				<div class="role-tabs">
					<button type="button" class="role-tab active"
						onclick="selectRole('customer', this)">Customer</button>

					<button type="button" class="role-tab"
						onclick="selectRole('admin', this)">Admin</button>
				</div>

				<input type="hidden" name="role" id="role" value="customer">

				<div id="roleError" class="error"></div>
				<label>Email/Username:</label> <input type="text" id="email"
					name="email" placeholder="Enter your email/username">
				<div id="emailError" class="error"></div>

				<label>Password:</label> <input type="password" id="password"
					name="password" placeholder="Enter your password">
				<div id="passError" class="error"></div>

				<div id="roleError" class="error"></div>

				<button type="submit" class="btn">Login</button>

				<p class="link" id="registerBox">
					New User? <a href="<%=base%>/RegisterServlet">Register Here</a>
				</p>

			</form>
		</div>
	</div>

	<script src="<%=base%>/scripts/login.js"></script>

</body>
</html>