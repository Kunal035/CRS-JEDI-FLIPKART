package com.flipkart.bean;

import java.util.ArrayList;

public class Student extends User{

	private Integer studentID;
	private String department;
	private ArrayList<String> RegisteredCourses = new ArrayList<String>();
	public Student(String userName, String name, String role, String password,Integer studentID, String department, ArrayList<String> RegisteredCourses,Integer userId) {
		super(userName,name,role,password,userId);
		this.studentID = studentID;
		this.department = department;
		this.RegisteredCourses = RegisteredCourses;
	}
	public Student() {
		super();
	}

	public Student(String userName) {
		super();
		this.setUserName(userName);
		// Initialize other fields with default values or leave them as null
		this.studentID = null;
		this.department = null;
		this.RegisteredCourses = new ArrayList<>();
	}
	public Student(Integer studentID, String userName) {
		super();
		this.setUserName(userName);
		// Initialize other fields with default values or leave them as null
		this.studentID = studentID;
		this.department = null;
		this.RegisteredCourses = new ArrayList<>();
	}



	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public ArrayList<String> getRegisteredCourses() {
		return RegisteredCourses;
	}

	public void setRegisteredCourses(ArrayList<String> registeredCourses) {
		this.RegisteredCourses = registeredCourses;
	}
	
	
}
