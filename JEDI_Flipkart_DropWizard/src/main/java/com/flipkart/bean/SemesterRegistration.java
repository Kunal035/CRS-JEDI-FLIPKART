package com.flipkart.bean;

public class SemesterRegistration {

	public SemesterRegistration() {
		// TODO Auto-generated constructor stub
		super();
	}
	private Integer studentID;
	private String dateOfRegistration;
	private Boolean isApproved;
	private Integer semesterID;
	public Integer getStudentID() {
		return studentID;
	}
	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}
	public String getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(String dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	public Boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
	public Integer getSemesterID() {
		return semesterID;
	}
	public void setSemesterID(Integer semesterID) {
		this.semesterID = semesterID;
	}
	public SemesterRegistration(Integer studentID, String dateOfRegistration, Boolean isApproved,
			Integer semesterID) {
		super();
		this.studentID = studentID;
		this.dateOfRegistration = dateOfRegistration;
		this.isApproved = isApproved;
		this.semesterID = semesterID;
	}
	
}
