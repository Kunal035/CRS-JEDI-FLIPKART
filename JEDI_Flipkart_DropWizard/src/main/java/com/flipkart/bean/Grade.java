package com.flipkart.bean;

import java.io.Serializable;

public class Grade implements Serializable {
	private String courseId;
	private int studentId;
	private String grade;

	// Default constructor
	public Grade() {
	}

	// Parameterized constructor
	public Grade(String courseId, int studentId, String grade) {
		this.courseId = courseId;
		this.studentId = studentId;
		this.grade = grade;
	}

	// Getter and setter for courseId
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	// Getter and setter for studentId
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	// Getter and setter for grade
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	// Override toString() method for easy printing
	@Override
	public String toString() {
		return "Grade{" +
				"courseId='" + courseId + '\'' +
				", studentId=" + studentId +
				", grade='" + grade + '\'' +
				'}';
	}
}
