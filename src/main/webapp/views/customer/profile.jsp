<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="model.Customer"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<jsp:include page="/views/common/navbar.jsp" />
	<div class="main">
		<h2>👤 My Profile</h2>
		<%
		Customer c = (Customer) request.getAttribute("customerData");
		if (c != null) {
		%>
		<div class="form-container">
			<div class="form-card">
				<h3><%=c.getName()%></h3>
				<hr><br>
				<div class="form-grid">
					<div class="form-group">
						<label>Customer ID</label> <input type="text"
							value="<%=c.getCustomerId()%>" readonly>
					</div>

					<div class="form-group">
						<label>Email</label> <input type="text" value="<%=c.getEmail()%>"
							readonly>
					</div>

					<div class="form-group">
						<label>Phone</label> <input type="text" value="<%=c.getPhone()%>"
							readonly>
					</div>

					<div class="form-group full">
						<label>Address</label> <input type="text"
							value="<%=c.getAddress()%>" readonly>
					</div>

					<%
					String status = c.getAccountStatus();
					String badgeClass = status.equals("Active") ? "badge-green" : "badge-red";
					%>

					<div class="form-group">
						<label>Account Status</label> <span class="badge <%=badgeClass%>"><%=status%></span>
				</div>

				</div>

				<div class="form-actions">
					<a
						href="<%=request.getContextPath()%>/ChangePasswordServlet"
						class="btn"> 🔒 Change Password </a>
				</div>
			</div>
		</div>
		<%
		if (request.getAttribute("msg") != null) {
		%>
		<div class="success-box">
			<%=request.getAttribute("msg")%>
		</div>
		<%
		}
		%>
		<%
		} else {
		%>
		<div class="empty-box">📭 Profile not found.</div>
		<%
		}
		%>
	</div>
	<jsp:include page="/views/common/footer.jsp" />
</body>
</html>