<%@page import="util.FormatUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*, model.Loan"%>

<%
String type = request.getParameter("type");
List<Loan> loans = (List<Loan>) request.getAttribute(type);
%>

<%
if (loans != null && !loans.isEmpty()) {
%>

<div class="table-container">
	<table>

		<thead>
			<tr>
				<th>ID</th>
				<th>Type</th>
				<th>Amount</th>
				<th>EMI</th>
				<th>Balance</th>
				<th>Due Date</th>
				<th>End Date</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>

			<%
			for (Loan l : loans) {

				String status = l.getStatus();
				String cls = "badge-gray";

				if ("Approved".equalsIgnoreCase(status)) {
					cls = "badge-green";
				} else if ("Pending".equalsIgnoreCase(status)) {
					cls = "badge-yellow";
				} else if ("Rejected".equalsIgnoreCase(status)) {
					cls = "badge-red";
				} else if ("Closed".equalsIgnoreCase(status)) {
					cls = "badge-blue";
				} else if ("Overdue".equalsIgnoreCase(status)) {
					cls = "badge-orange";
				}
			%>
			<tr>
				<td><%=l.getLoanId()%></td>
				<td><%=l.getLoanType()%></td>
				<td>₹<%=FormatUtil.formatIndianNumber(l.getAmount())%></td>
				<td>₹<%=FormatUtil.formatIndianNumber(l.getEmi())%></td>
				<td>₹<%=FormatUtil.formatIndianNumber(l.getBalance())%></td>
				<td><%=l.getDueDate()%></td>
				<td><%=l.getEndDate() != null ? l.getEndDate() : "-"%></td>
				<td><span class="badge <%=cls%>"><%=status%></span></td>
			</tr>

			<%
			}
			%>
		</tbody>
	</table>
</div>

<%
} else {
%>
<div class="empty-box">📭 No records found.</div>
<%
}
%>