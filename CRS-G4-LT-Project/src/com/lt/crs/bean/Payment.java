package com.lt.crs.bean;

public class Payment {
	private int paymentId;
	private String paymentMode;
	private String courseAmount;
	private int studentId;
	private String studentUsername;
	
	public String getStudentUsername() {
		return studentUsername;
	}
	public void setStudentUsername(String studentUsername) {
		this.studentUsername = studentUsername;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getCourseAmount() {
		return courseAmount;
	}
	public void setCourseAmount(String courseAmount) {
		this.courseAmount = courseAmount;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
}
