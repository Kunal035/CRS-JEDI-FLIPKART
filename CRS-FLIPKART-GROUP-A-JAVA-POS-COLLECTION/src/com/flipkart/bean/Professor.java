package com.flipkart.bean;

public class Professor extends User{

	private Integer professorId;
	private String department;
	private String designation;
	public Integer getprofessorId() {
		return professorId;
	}
	public void setprofessorId(Integer professorId) {
		this.professorId = professorId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Professor(String userName, String name, String role, String password,Integer professorId, String department, String designation) {
		super(userName,name,role,password);
		this.professorId = professorId;
		this.department = department;
		this.designation = designation;
	}
	public Professor() {
		super();
	}
	
}
