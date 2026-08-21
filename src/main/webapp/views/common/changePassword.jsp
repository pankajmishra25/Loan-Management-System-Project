<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Change Password</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>
	<jsp:include page="/views/common/navbar.jsp" />
	<div class="main">
		<div class="form-card">
			<%
			String role = (String) session.getAttribute("role");
			String msg = request.getParameter("msg");
			Object msgObj = request.getAttribute("msg");

			if (msgObj != null) {
			%>
			<div class="error"><%=msgObj%></div>
			<%
			} else if ("success".equals(msg)) {
			%>
			<div class="success">✅ Password changed successfully! Please
				login again.</div>
			<%
			} else if ("failed".equals(msg)) {
			%>
			<div class="error">❌ Failed to update password</div>
			<%
			} else if ("error".equals(msg)) {
			%>
			<div class="error">❌ Invalid input or password mismatch</div>
			<%
			}
			%>

			<h2 class="form-title">
				🔒 Change Password
				<%
			if ("admin".equals(role)) {
			%>
				(Admin)
				<%
			} else {
			%>
				(Customer)
				<%
			}
			%>
			</h2>
			<form method="post"
				action="<%=request.getContextPath()%>/ChangePasswordServlet"
				class="login-form" onsubmit="return validatePassword()">

				<label>Old Password</label> <input type="password"
					name="oldPassword" placeholder="Enter old password" required>

				<label>New Password</label> <input type="password"
					name="newPassword" placeholder="Enter new password" required
					minlength="6"> <label>Confirm Password</label> <input
					type="password" name="confirmPassword"
					placeholder="Confirm new password" required minlength="6">

				<button class="btn">🔒 Update Password</button>
			</form>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/scripts/validatePassword()"></script>
	<%
	if ("customer".equals(role)) {
	%>
	<jsp:include page="/views/common/footer.jsp" />
	<%
}
%>
</body>
</html>