<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Customer"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customers</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

	<jsp:include page="/views/common/navbar.jsp" />

	<div class="main">

		<h2>👥 Customer List</h2>
		<div class="table-container">
			<table>

				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>

					<%
					List<Customer> list = (List<Customer>) request.getAttribute("customers");

					if (list != null && !list.isEmpty()) {
						for (Customer c : list) {
							boolean active = "Active".equals(c.getAccountStatus());
					%>

					<tr>
						<td><%=c.getCustomerId()%></td>
						<td><%=c.getName()%></td>
						<td><%=c.getEmail()%></td>
						<td><%=c.getPhone()%></td>
						<td><span
							class="badge 
        <%="Active".equals(c.getAccountStatus()) ? "badge-green" : "badge-red"%>">
								<%=c.getAccountStatus()%>
						</span></td>

						<td><a
							href="<%=request.getContextPath()%>/ViewCustomerServlet?id=<%=c.getCustomerId()%>"
							class="btn small">Edit</a></td>
					</tr>
					<%
					}
					} else {
					%>

					<tr>
						<td colspan="6" class="empty-box">⚠ No customers found</td>
					</tr>

					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>