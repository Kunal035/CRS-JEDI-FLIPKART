package com.flipkart.bean;

public class Payment {

	public Payment() {
		// TODO Auto-generated constructor stub
		super();
	}

	private Integer studentID;
	private Integer paymentID;
	private Boolean paymentStatus;
	private Integer amount;

	public Payment(Integer studentID, Integer paymentID, Boolean paymentStatus, Integer amount) {
		super();
		this.studentID = studentID;
		this.paymentID = paymentID;
		this.paymentStatus = paymentStatus;
		this.amount = amount;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public Integer getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(Integer paymentID) {
		this.paymentID = paymentID;
	}

	public Boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
