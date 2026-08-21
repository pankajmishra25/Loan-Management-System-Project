<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Customer"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Customer Profile</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

	<jsp:include page="/views/common/navbar.jsp" />

	<%
	Customer c = (Customer) request.getAttribute("customer");
	%>

	<div class="main">

		<h2>✏ Edit Customer Profile</h2>

		<%
		String success = request.getParameter("success");
		String error = request.getParameter("error");

		if (success != null) {
		%>
		<div class="success">✔ Customer details updated successfully</div>
		<%
		} else if (error != null) {
		%>
		<div class="error">❌ Failed to update customer</div>
		<%
		}
		%>

		<div class="form-card">
			<form action="<%=request.getContextPath()%>/UpdateCustomerServlet"
				method="post">

				<input type="hidden" name="customer_id"
					value="<%=c.getCustomerId()%>"> <label>Name</label> <input
					type="text" name="name" value="<%=c.getName()%>" required
					class="full"> <label>Gender</label> <select name="gender">
					<option <%="Male".equals(c.getGender()) ? "selected" : ""%>>Male</option>
					<option <%="Female".equals(c.getGender()) ? "selected" : ""%>>Female</option>
					<option <%="Other".equals(c.getGender()) ? "selected" : ""%>>Other</option>
				</select> <label>Email</label> <input type="text" value="<%=c.getEmail()%>"
					readonly> <input type="hidden" name="email"
					value="<%=c.getEmail()%>"> <label>Phone</label> <input
					type="text" name="phone" value="<%=c.getPhone()%>" required>

				<label>DOB</label> <input type="date" name="dob"
					value="<%=c.getDob()%>" required> <label>Address</label> <input
					type="text" name="address" value="<%=c.getAddress()%>" required
					class="full"> <label>Annual Income</label> <input
					type="text" name="salary" value="<%=c.getSalary()%>" required>

				<label>Occupation</label> <input type="text" name="occupation"
					value="<%=c.getOccupation()%>" required> <label>Status</label>
				<select name="status">
					<option
						<%=c.getAccountStatus().equals("Active") ? "selected" : ""%>>Active</option>
					<option
						<%=c.getAccountStatus().equals("Inactive") ? "selected" : ""%>>Inactive</option>
				</select>

				<div class="form-actions">
					<button class="btn">Update Details</button>
					<a href="<%=request.getContextPath()%>/CustomerListServlet"
						class="btn"> ← Back </a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>