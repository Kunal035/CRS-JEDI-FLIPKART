package com.flipkart.bean;

import java.util.HashMap;

// In GradeCard.java
public class GradeCard {
	private String courseId;
	private String courseName;
	private String grade;

	public GradeCard(String courseId, String courseName, String grade) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.grade = grade;
	}

	public String getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getGrade() {
		return grade;
	}
}

