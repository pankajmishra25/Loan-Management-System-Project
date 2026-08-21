<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String base = request.getContextPath();
%>

<footer class="footer">

	<div class="footer-container">

		<div class="footer-grid">

			<!-- About -->
			<div class="footer-card">
				<h3>FinTrust</h3>
				<p>Smart loan management platform for handling loan
					applications, EMI calculations, repayments, and transparency.</p>
			</div>

			<!-- Features -->
			<div class="footer-card">
				<h4>Features</h4>
				<div>
					<p><a href="<%=base%>/InterestRatesServlet">Interest
							Rates</a></p>
					<p><a href="<%=base%>/EmiCalculatorServlet">EMI
							Calculator</a></p>
					<p><a href="<%=base%>/ApplyLoanServlet">Loan Application</a></p>
					<p><a href="<%=base%>/PaymentHistoryServlet">Payment
							Tracking</a></p>
				</div>
			</div>

			<!-- Support -->
			<div class="footer-card">
				<h4>Support</h4>
				<div>
					<p><a href="<%=base%>/ContactServlet">Contact Us</a></p>
					<p>support@fintrust.com</p>
					<p>+91 99999 88888</p>
					<p>Mon – Sat, 9:00 AM – 6:00 PM</p>
				</div>
			</div>

			<!-- Address -->
			<div class="footer-card">
				<h4>Address</h4>
				<div>
					<p>FinTrust HQ</p>
					<p>Dehradun, Uttarakhand</p>
					<p>India</p>
				</div>
			</div>

		</div>

		<div class="footer-bottom">
			<p>© 2026 FinTrust. All rights reserved.</p>
			<p><a href="<%=base%>/privacy.jsp">Privacy Policy</a> | <a
				href="<%=base%>/terms.jsp">Terms & Conditions</a></p>
		</div>

	</div>

</footer>