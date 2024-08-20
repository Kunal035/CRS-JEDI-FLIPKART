package com.flipkart.bean;

import java.util.ArrayList;

public class RegisteredCourses {

	private Integer studentID;
	private Integer semesterID;
	private ArrayList<String> courseID = new ArrayList<String>();
	
	public RegisteredCourses(Integer studentID, Integer semesterID, ArrayList<String> courseID) {
		super();
		this.studentID = studentID;
		this.semesterID = semesterID;
		this.courseID = courseID;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public Integer getSemesterID() {
		return semesterID;
	}

	public void setSemesterID(Integer semesterID) {
		this.semesterID = semesterID;
	}

	public ArrayList<String> getCourseID() {
		return courseID;
	}

	public void setCourseID(ArrayList<String> courseID) {
		this.courseID = courseID;
	}

	public RegisteredCourses() {
		super();
	}
	
}
