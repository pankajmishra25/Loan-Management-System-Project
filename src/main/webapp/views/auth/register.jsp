<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Registration</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
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
				❌ <%=error%></div>
		<%
}
%>
		<h2 class="form-title">Customer Registration</h2>
		<div class="form-card">
			<form action="<%=request.getContextPath()%>/RegisterServlet"
				method="post" class="grid">

				<label>Full Name</label> <input type="text" name="name"
					placeholder="Enter your full name" required class="full"> <label>Email</label>
				<input type="email" name="email" placeholder="Enter email address"
					required> <label>Password</label> <input type="password"
					name="password" placeholder="Enter password" required> <label>Gender</label>
				<select name="gender" required>
					<option value="">Select</option>
					<option>Male</option>
					<option>Female</option>
					<option>Other</option>
				</select> <label>Date of Birth</label> <input type="date" name="dob" required>
				<label>Phone</label> <input type="text" name="phone"
					placeholder="Enter mobile number" required> <label>Address
					Line</label>
				<textarea name="address" rows="2" class="full"
					placeholder="Street, Area, Landmark"></textarea>
				<label>Annual Salary</label> <input type="number" name="salary"
					placeholder="Enter annual income"> <label>Occupation</label>
				<input type="text" name="occupation" placeholder="Enter occupation">

				<button type="submit" class="btn">Register</button>

			</form>
		</div>
	</div>

</body>
</html>