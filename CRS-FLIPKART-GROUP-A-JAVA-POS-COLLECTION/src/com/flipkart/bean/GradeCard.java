package com.flipkart.bean;

import java.util.HashMap;

public class GradeCard {

	public GradeCard() {
		// TODO Auto-generated constructor stub
		super();
	}

	HashMap<String, String> grades = new HashMap<String, String>();
	private Integer studentID;
	private Integer semester;
	private Float cgpa;


	public GradeCard(HashMap<String, String> grades, Integer studentID, Integer semester, Float cgpa) {
		super();
		this.grades = grades;
		this.studentID = studentID;
		this.semester = semester;
		this.cgpa = cgpa;
	}

	public HashMap<String, String> getGrades() {
		return grades;
	}

	public void setGrades(HashMap<String, String> grades) {
		this.grades = grades;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public Float getCgpa() {
		return cgpa;
	}

	public void setCgpa(Float cgpa) {
		this.cgpa = cgpa;
	}
	
}
