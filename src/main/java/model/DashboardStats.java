package model;

public class DashboardStats {

	// Admin
	private int totalLoans;
	private int approved;
	private int rejected;
	private int pending;
	private int closed;
	private int overdue;
	private int defaulters;
	private double balance;

	// Customer
	private double totalPaid;

	// Getters & Setters

	public int getTotalLoans() {
		return totalLoans;
	}

	public void setTotalLoans(int totalLoans) {
		this.totalLoans = totalLoans;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public int getRejected() {
		return rejected;
	}

	public void setRejected(int rejected) {
		this.rejected = rejected;
	}

	public int getPending() {
		return pending;
	}

	public void setPending(int pending) {
		this.pending = pending;
	}

	public int getClosed() {
		return closed;
	}

	public void setClosed(int closed) {
		this.closed = closed;
	}

	public int getOverdue() {
		return overdue;
	}

	public void setOverdue(int overdue) {
		this.overdue = overdue;
	}

	public int getDefaulters() {
		return defaulters;
	}

	public void setDefaulters(int defaulters) {
		this.defaulters = defaulters;
	}

	public double getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}